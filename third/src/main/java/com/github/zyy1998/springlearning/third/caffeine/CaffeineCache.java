package com.github.zyy1998.springlearning.third.caffeine;

import com.github.benmanes.caffeine.cache.*;

import java.util.concurrent.TimeUnit;

public class CaffeineCache {
    /**
     * 最多100个数据，一分钟后过期
     */
    public static Cache<String, DataObject> getCache1() {
        Cache<String, DataObject> cache = Caffeine.newBuilder()
                // entry is expired after period is passed since the last read or write occurs
                .expireAfterAccess(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .build();
        return cache;
    }

    /**
     * LoadingCache接收一个函数用于初始化值
     */
    public static LoadingCache<String, DataObject> getCache2() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .build(k -> DataObject.get("Data for " + k));
        return cache;
    }


    /**
     * This strategy works the same as the previous but performs operations
     * asynchronously and returns a CompletableFuture holding the actual value
     */
    public static AsyncLoadingCache<String, DataObject> getCache3() {
        AsyncLoadingCache<String, DataObject> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .buildAsync(k -> DataObject.get("Data for " + k));
        return cache;
    }

    /**
     * 最大缓存数量为1
     */
    public static LoadingCache<String, DataObject> getCache4() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
                .maximumSize(1)
                .build(k -> DataObject.get("Data for " + k));
        return cache;
    }

    /**
     * entry is expired after period is passed since the last write occurs
     * The WeakRefence usage allows garbage-collection of objects when there
     * are not any strong references to the object.
     * SoftReference allows objects to be garbage-collected based on the global
     * Least-Recently-Used strategy of the JVM. More details about references in Java can be found here.
     *
     * see https://www.baeldung.com/java-weakhashmap
     */
    public static LoadingCache<String, DataObject> getCache5() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .weakKeys()
                .weakValues()
                .build(k -> DataObject.get("Data for " + k));
        return cache;
    }

    /**
     * To initialize a custom policy, we need to implement the Expiry interface:
     */
    public static LoadingCache<String, DataObject> getCache6() {
        LoadingCache<String, DataObject> cache = Caffeine.newBuilder().expireAfter(
                new Expiry<String, DataObject>() {
                    @Override
                    public long expireAfterCreate(
                            String key, DataObject value, long currentTime) {
                        return value.getData().length() * 1000;
                    }

                    @Override
                    public long expireAfterUpdate(
                            String key, DataObject value, long currentTime, long currentDuration) {
                        return currentDuration;
                    }

                    @Override
                    public long expireAfterRead(
                            String key, DataObject value, long currentTime, long currentDuration) {
                        return currentDuration;
                    }
                }).build(k -> DataObject.get("Data for " + k));
        return cache;
    }
}

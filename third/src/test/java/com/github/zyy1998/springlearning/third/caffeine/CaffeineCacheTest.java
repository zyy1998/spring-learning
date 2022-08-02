package com.github.zyy1998.springlearning.third.caffeine;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

class CaffeineCacheTest {

    @Test
    public void testGetCache1() {
        Cache<String, DataObject> cache1 = CaffeineCache.getCache1();
        String key = "A";
        // 如果不存在指，则返回null
        DataObject dataObject = cache1.getIfPresent(key);
        assertNull(dataObject);

        // 手动往里添加数据
        dataObject = DataObject.get(key);
        cache1.put(key, dataObject);
        assertNotNull(dataObject);

        // 可以传入一个函数，当所需的key不在缓存中时，会调用这个函数，key已经在了话这个函数无效
        dataObject = cache1.get("key not exists", k -> DataObject.get("Data for A"));
        assertNotNull(dataObject);
        assertEquals("Data for A", dataObject.getData());

        //手动让缓存失效
        cache1.invalidate(key);
        dataObject = cache1.getIfPresent(key);
        assertNull(dataObject);

    }

    @Test
    public void testGetCache2() {
        LoadingCache<String, DataObject> cache2 = CaffeineCache.getCache2();
        String key = "A";
        // A不在缓存中，取不到数据，会调用LoadingCache中定义的函数自动生成一个
        DataObject dataObject = cache2.get(key);
        assertNotNull(dataObject);
        assertEquals("Data for " + key, dataObject.getData());

        // 批量获取数据
        Map<String, DataObject> dataObjectMap
                = cache2.getAll(Arrays.asList("A", "B", "C"));
        assertEquals(3, dataObjectMap.size());
    }

    @Test
    public void testGetCache3() {
        AsyncLoadingCache<String, DataObject> cache3 = CaffeineCache.getCache3();
        String key = "A";
        CompletableFuture<DataObject> completableFuture = cache3.get(key);
        completableFuture.thenAccept(dataObject -> {
            assertNotNull(dataObject);
            assertEquals("Data for " + key, dataObject.getData());
        });

        cache3.getAll(Arrays.asList("A", "B", "C"))
                .thenAccept(stringDataObjectMap -> assertEquals(3, stringDataObjectMap.size()));
    }

    @Test
    public void testGetCache4() {
        LoadingCache<String, DataObject> cache4 = CaffeineCache.getCache4();
        // estimatedSize获取缓存中的数量
        assertEquals(0,cache4.estimatedSize());
        cache4.get("A");
        assertEquals(1,cache4.estimatedSize());
        cache4.get("B");
        // It is worth mention that we call the cleanUp method before getting the cache size.
        // This is because the cache eviction is executed asynchronously, and this method helps to
        // await the completion of the eviction.
        cache4.cleanUp();
        assertEquals(1,cache4.estimatedSize());
    }
}
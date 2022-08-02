package com.github.zyy1998.springlearning.third.caffeine;

/**
 * 用于存储数据的类
 */
public class DataObject {
    private final String data;
    private static int objectCounter = 0;

    public DataObject(String data) {
        this.data = data;
    }

    public static DataObject get(String data) {
        objectCounter++;
        return new DataObject(data);
    }

    public String getData() {
        return this.data;
    }

}

package com.github.zyy1998.springlearning.springbase;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zyy1998.springlearning.springbase.jackson.JacksonModel1;
import com.github.zyy1998.springlearning.springbase.jackson.JacksonModel2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JacksonTest {

    /**
     * 测试模型1的json化
     */
    @Test
    public void test1() throws JsonProcessingException {
        JacksonModel1 model1 = new JacksonModel1();
        model1.setURL("https://www.baidu.com");
        model1.setAppId(2039221492L);
        model1.setTeST("TestValue");

        /**
         * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
         * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
         * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
         * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
         * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
         * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
         */
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(model1);
        // 需要注意的是，在JacksonModel1中字段名为URL，但是转成json时却被映射成了小写的url
        // 对于不规则的命名方式，都应该使用注解指定它的映射值
        assertEquals(json, "{\"appId\":2039221492,\"url\":\"https://www.baidu.com\",\"teST\":\"TestValue\"}");
    }

    /**
     * 测试模型w的json化
     */
    @Test
    public void test2() throws JsonProcessingException {
        JacksonModel2 model2 = new JacksonModel2();
        model2.setURL("https://www.baidu.com");
        model2.setAppId(2039222492L);
        model2.setTeST("TestValue");

        /**
         * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
         * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
         * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
         * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
         * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
         * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
         */
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(model2);
        // 需要注意的是，在JacksonModel1中字段名为URL，但是转成json时却被映射成了小写的url
        // 对于不规则的命名方式，都应该使用注解指定它的映射值
        assertEquals(json, "{\"appId\":2039222492,\"URL\":\"https://www.baidu.com\",\"TeST\":\"TestValue\"}");
    }
}

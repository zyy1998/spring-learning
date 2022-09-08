package com.github.zyy1998.springlearning.springbase.jackson;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JacksonTest {

    /**
     * 测试模型1的json化，关注字段大小写问题
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
         * 参考 https://blog.csdn.net/accountwcx/article/details/24585987
         */
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(model1);
        // 需要注意的是，在JacksonModel1中字段名为URL，但是转成json时却被映射成了小写的url
        // 对于不规则的命名方式，都应该使用注解指定它的映射值
        assertEquals(json, "{\"appId\":2039221492,\"url\":\"https://www.baidu.com\",\"teST\":\"TestValue\"}");
    }

    /**
     * 测试模型2的json化，关注字段大小写问题
     */
    @Test
    public void test2() throws JsonProcessingException {
        JacksonModel2 model2 = new JacksonModel2();
        model2.setURL("https://www.baidu.com");
        model2.setAppId(2039222492L);
        model2.setTeST("TestValue");

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(model2);
        // 需要注意的是，在JacksonModel1中字段名为URL，但是转成json时却被映射成了小写的url
        // 对于不规则的命名方式，都应该使用注解指定它的映射值
        assertEquals(json, "{\"appId\":2039222492,\"URL\":\"https://www.baidu.com\",\"TeST\":\"TestValue\"}");
    }

    /*
     *  测试json转模型
     */
    @Test
    public void test3() throws JsonProcessingException {
        String json = "{\"appId\":2039222492,\"URL\":\"https://www.baidu.com\",\"TeST\":\"TestValue\"}";

        /**
         * ObjectMapper支持从byte[]、File、InputStream、字符串等数据的JSON反序列化。
         */
        ObjectMapper mapper = new ObjectMapper();
        JacksonModel2 model2 = mapper.readValue(json, JacksonModel2.class);
        assertEquals(2039222492, model2.getAppId());
        assertEquals("https://www.baidu.com", model2.getURL());
        assertEquals("TestValue", model2.getTeST());
    }

    /*
     *  测试模型列表转json
     */
    @Test
    public void test4() throws JsonProcessingException {
        JacksonModel2 model1 = new JacksonModel2();
        model1.setURL("url1");
        model1.setAppId(1L);
        model1.setTeST("test1");

        JacksonModel2 model2 = new JacksonModel2();
        model2.setURL("url1");
        model2.setAppId(2L);
        model2.setTeST("test2");

        List<JacksonModel2> list = new ArrayList<>();
        list.add(model1);
        list.add(model2);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);
        assertEquals("[{\"appId\":1,\"URL\":\"url1\",\"TeST\":\"test1\"},{\"appId\":2,\"URL\":\"url1\",\"TeST\":\"test2\"}]", json);
    }

    /*
     * 测试json转模型列表
     * 参考 https://stackoverflow.com/questions/6349421/how-to-use-jackson-to-deserialise-an-array-of-objects
     */
    @Test
    public void test5() throws JsonProcessingException {
        String json = "[{\"appId\":1,\"URL\":\"url1\",\"TeST\":\"test1\"},{\"appId\":2,\"URL\":\"url1\",\"TeST\":\"test2\"}]";
        ObjectMapper mapper = new ObjectMapper();
        List<JacksonModel2> list = mapper.readValue(json, new TypeReference<List<JacksonModel2>>() {
        });
        assertEquals(2, list.size());
        assertEquals(1L, list.get(0).getAppId());
        assertEquals(2L, list.get(1).getAppId());
    }

}

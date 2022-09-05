package com.github.zyy1998.springlearning.cloud.tencent.wechat.pay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@SpringBootTest(classes = WechatPayApplication.class)
public class NativePayTest {
    private CloseableHttpClient httpClient;

    @Autowired
    private WechatPayService service;
    @Autowired
    private WechatPayProperties properties;
    private String mchId;

    @BeforeEach
    public void setup() throws IOException {
        httpClient = service.generateHttpClient();
        mchId = properties.getMCH_ID();
    }

    @Test
    public void CreateOrder() throws Exception {
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/native");
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type", "application/json; charset=utf-8");

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("mchid", properties.getMCH_ID())
                .put("appid", properties.getAPP_ID())
                .put("description", "Image形象店-深圳腾大-QQ公仔")
//                .put("notify_url", "https://weixin.qq.com/")
                .put("notify_url", properties.getNOTIFY_URL())
                .put("out_trade_no", "1217752501201407043233368013");
        rootNode.putObject("amount")
                .put("total", 1);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        objectMapper.writeValue(bos, rootNode);
        httpPost.setEntity(new StringEntity(bos.toString("UTF-8"), "UTF-8"));

        //完成签名并执行请求
        CloseableHttpResponse response = httpClient.execute(httpPost);
        String bodyAsString = EntityUtils.toString(response.getEntity());
        System.out.println(bodyAsString);
    }

    /**
     * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_4_2.shtml
     */
    @Test
    public void SearchOrderByOutTradeNo() throws Exception {
        String outTradeNo = "1217752501201407033233368013";
        HttpGet httpGet = new HttpGet("https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/" + outTradeNo + "?mchid=" + mchId);
        httpGet.addHeader("Accept", "application/json");
        httpGet.addHeader("Content-type", "application/json; charset=utf-8");

        CloseableHttpResponse response = httpClient.execute(httpGet);
        String bodyAsString = EntityUtils.toString(response.getEntity());
        System.out.println(bodyAsString);
    }

    /**
     * 关闭订单
     * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_4_3.shtml
     */
    @Test
    public void CloseOrder() throws Exception {
        String outTradeNo = "1217752501201407033233368024";
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/out-trade-no/" + outTradeNo + "/close");
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type", "application/json; charset=utf-8");

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("mchid", properties.getMCH_ID());
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        objectMapper.writeValue(bos, rootNode);
        httpPost.setEntity(new StringEntity(bos.toString("UTF-8"), "UTF-8"));

        CloseableHttpResponse response = httpClient.execute(httpPost);
        String status = String.valueOf(response.getStatusLine());
        System.out.println(status);
    }


    @AfterEach
    public void after() throws IOException {
        httpClient.close();
    }
}

package com.github.zyy1998.springlearning.cloud.tencent.wechat.pay;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.PrivateKey;

public class NativePayTest {
    private CloseableHttpClient httpClient;
    private String apiV3Key = "";
    private String mchId = "";

    private String appId = "";
    private String mchSerialNo = "";
    private String merchantPrivateKeyPath = "";

    @BeforeEach
    public void setup() throws IOException {
        // 加载商户私钥
        // @see https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient#%E5%A6%82%E4%BD%95%E5%8A%A0%E8%BD%BD%E5%95%86%E6%88%B7%E7%A7%81%E9%92%A5
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
                new FileInputStream(merchantPrivateKeyPath));

        // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(mchId, new PrivateKeySigner(mchSerialNo, merchantPrivateKey)), apiV3Key.getBytes("utf-8"));

        // 初始化httpClient
        httpClient = WechatPayHttpClientBuilder.create()
                .withMerchant(mchId, mchSerialNo, merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier)).build();
    }

    @Test
    public void CreateOrder2() {
        System.out.println("xx");
    }

    @Test
    public void CreateOrder() throws Exception {
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/v3/pay/transactions/native");
        httpPost.addHeader("Accept", "application/json");
        httpPost.addHeader("Content-type", "application/json; charset=utf-8");

        ObjectMapper objectMapper = new ObjectMapper();

        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("mchid", mchId)
                .put("appid", appId)
                .put("description", "Image形象店-深圳腾大-QQ公仔")
//                .put("notify_url", "https://weixin.qq.com/")
                .put("notify_url", "http://daylight.ga:7002/wechat/callback")
                .put("out_trade_no", "1217752501201407033233368013");
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
        rootNode.put("mchid", mchId);
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

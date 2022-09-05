package com.github.zyy1998.springlearning.cloud.tencent.wechat.pay;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.AutoUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;

@Service
public class WechatPayService {
    private final WechatPayProperties properties;

    public WechatPayService(WechatPayProperties properties) {
        this.properties = properties;
    }

    /**
     * @throws IOException
     * @link https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient#%E5%A6%82%E4%BD%95%E5%8A%A0%E8%BD%BD%E5%95%86%E6%88%B7%E7%A7%81%E9%92%A5
     */
    public CloseableHttpClient generateHttpClient() throws IOException {
        // 加载商户私钥
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(
                Files.newInputStream(Paths.get(properties.getMCH_PRIVATE_KEY_PATH()))
        );

        // 加载平台证书（mchId：商户号,mchSerialNo：商户证书序列号,apiV3Key：V3密钥）
        AutoUpdateCertificatesVerifier verifier = new AutoUpdateCertificatesVerifier(
                new WechatPay2Credentials(
                        properties.getMCH_ID(),
                        new PrivateKeySigner(properties.getMCH_SERIAL_NO(), merchantPrivateKey)
                ),
                properties.getAPI_V3_KEY().getBytes(StandardCharsets.UTF_8)
        );

        // 初始化httpClient
        return WechatPayHttpClientBuilder.create()
                .withMerchant(properties.getMCH_ID(), properties.getMCH_SERIAL_NO(), merchantPrivateKey)
                .withValidator(new WechatPay2Validator(verifier)).build();
    }

}

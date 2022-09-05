package com.github.zyy1998.springlearning.cloud.alibaba.pay;

import com.alipay.api.*;
import org.springframework.stereotype.Service;

@Service
public class AliPayService {
    private final AliPayProperties properties;

    public AliPayService(AliPayProperties properties) {
        this.properties = properties;
    }

    /**
     * 公钥方式获取client
     *
     * @link https://opendocs.alipay.com/support/01raz6
     */
    public AlipayClient generateClientByPublicKey() throws AlipayApiException {
        AlipayConfig alipayConfig = new AlipayConfig();
        //设置网关地址
        alipayConfig.setServerUrl(properties.getURL());
        //设置应用Id
        alipayConfig.setAppId(properties.getAPPID());
        //设置应用私钥
        alipayConfig.setPrivateKey(properties.getPRIVATE_KEY());
        //设置请求格式，固定值json
        alipayConfig.setFormat(properties.getFORMAT());
        //设置字符集
        alipayConfig.setCharset(properties.getCHARSET());
        //设置支付宝公钥
        alipayConfig.setAlipayPublicKey(properties.getALIPAY_PUBLIC_KEY());
        //设置签名类型
        alipayConfig.setSignType(properties.getSIGN_TYPE());

        return new DefaultAlipayClient(alipayConfig);
    }


    /**
     * 公钥证书方式获取client，返回的参数可以直接传入new DefaultAlipayClient()中
     *
     * @link https://github.com/alipay/alipay-sdk-java-all
     */
    public AlipayClient generateClientByCert() throws AlipayApiException {
        CertAlipayRequest certParams = new CertAlipayRequest();
        certParams.setServerUrl(properties.getURL());
        //请更换为您的AppId
        certParams.setAppId(properties.getAPPID());
        //请更换为您的PKCS8格式的应用私钥
        certParams.setPrivateKey(properties.getPRIVATE_KEY());
        //请更换为您使用的字符集编码，推荐采用utf-8
        certParams.setCharset(properties.getCHARSET());
        certParams.setFormat(properties.getFORMAT());
        certParams.setSignType(properties.getSIGN_TYPE());

        //请更换为您的应用公钥证书文件路径
        certParams.setCertPath("/home/foo/appCertPublicKey_2019091767145019.crt");
        //请更换您的支付宝公钥证书文件路径
        certParams.setAlipayPublicCertPath("/home/foo/alipayCertPublicKey_RSA2.crt");
        //更换为支付宝根证书文件路径
        certParams.setRootCertPath("/home/foo/alipayRootCert.crt");

        return new DefaultAlipayClient(certParams);
    }

}

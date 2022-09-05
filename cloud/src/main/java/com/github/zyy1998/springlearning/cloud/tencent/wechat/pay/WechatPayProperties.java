package com.github.zyy1998.springlearning.cloud.tencent.wechat.pay;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "cloud.tencent.wechat.pay")
public class WechatPayProperties {
    // V3密钥
    private String API_V3_KEY;
    // 商户号
    private String MCH_ID;
    private String APP_ID;
    // 商户证书序列号
    private String MCH_SERIAL_NO;
    // 私钥证书存放位置
    // https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient#%E5%A6%82%E4%BD%95%E5%8A%A0%E8%BD%BD%E5%95%86%E6%88%B7%E7%A7%81%E9%92%A5
    private String MCH_PRIVATE_KEY_PATH;
    private String NOTIFY_URL;
}

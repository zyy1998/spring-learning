package com.github.zyy1998.springlearning.cloud.alibaba.pay;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ToString
@Configuration
@ConfigurationProperties(prefix = "cloud.alibaba.pay")
public class AliPayProperties {
    private String URL;
    private String APPID;
    private String PRIVATE_KEY;
    // 支付宝公钥，不是应用公钥
    private String ALIPAY_PUBLIC_KEY;
    private String SIGN_TYPE;
    private String FORMAT;
    private String CHARSET;
    private String RETURN_URL;
    private String NOTIFY_URL;
}

package com.github.zyy1998.springlearning.http;

import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;

public class SignTest {

    private static final String API = "https://apis.map.qq.com";

    @Test
    public void x() {
//        String url = "/ip?ip=111.206.145.41&key=4BYBZ-7MT6S-PUAOA-6BNWL-FJUD7-UUFXT";
//        String sk = "zhNKVrJK6UPOhqIjn8AQvG37b9sz6";
//        String sign = SecureUtil.md5(url + sk);
//        System.out.println(sign);
        String key = "4BYBZ-7MT6S-PUAOA-6BNWL-FJUD7-UUFXT";
        String url = "/ws/location/v1/ip?key=" + key + "&ip=" + "111.206.145.41";
        String sk = "zhNKVrJK6UPOhqIjn8AQvG37b9sz6";
        String sign = SecureUtil.md5(url + sk);
        url = API + url + "&sign=" + sign;
        System.out.println(url);
    }
}

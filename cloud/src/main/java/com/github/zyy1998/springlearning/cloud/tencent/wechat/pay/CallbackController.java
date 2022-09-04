package com.github.zyy1998.springlearning.cloud.tencent.wechat.pay;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 调用微信支付接口后微信的回调地址
 */
@RestController
@RequestMapping("/wechat/callback")
public class CallbackController {

//    @PostMapping()
//    public String callBack(@RequestBody String body, @RequestHeader(name = "Wechatpay-Signature") String header) {
//        System.out.println("got it");
//        return "ok";
//    }

    @RequestMapping()
    public void notify(HttpServletRequest request) {
        System.out.println("got it");
        System.out.println(request);
    }

    @GetMapping("/hello")
    public String test() {
        return "Hello World";
    }
}

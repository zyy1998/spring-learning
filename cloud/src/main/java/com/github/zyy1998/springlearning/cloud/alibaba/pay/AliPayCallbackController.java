package com.github.zyy1998.springlearning.cloud.alibaba.pay;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 调用支付宝支付接口后的回调地址
 */
@RestController
@RequestMapping("/alipay/callback")
public class AliPayCallbackController {
    @RequestMapping("/return")
    public void returnURL(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("got it return");
        System.out.println(request);
    }

    @RequestMapping("/notify")
    public void notifyURL(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("got it notify");
        System.out.println(request);
    }

    @GetMapping
    public String test() {
        return "Hello World";
    }

}

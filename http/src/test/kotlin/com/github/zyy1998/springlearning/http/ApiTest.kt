package com.github.zyy1998.springlearning.http

import cn.hutool.crypto.SecureUtil
import com.github.zyy1998.springlearning.http.Api
import org.junit.jupiter.api.Test

class ApiTest {

    @Test
    fun generateURL() {
        val baseURL = "https://apis.map.qq.com"
        // key和sk是固定的
//        val sk = "zhNKVrJK6UPOhqIjn8AQvG37b9sz6"
//        我的sk
        val sk = "ZIOIexDc4qDaRIMU4lOrBTTRcfnIhX"
//        val key = "4BYBZ-7MT6S-PUAOA-6BNWL-FJUD7-UUFXT"
//        我的key
        val key = "Y6RBZ-27OWX-V544O-TA6E3-VDXGH-N7B2F"
        // 要检测的ip
        val ip = "111.206.145.41"
        val url = "/ws/location/v1/ip?ip=${ip}&key=${key}"
//        see https://lbs.qq.com/faq/serverFaq/webServiceKey
        val sign = SecureUtil.md5(url + sk)
        // 拼接成最后要请求的URL
        val requestURL = "${baseURL}${url}&sign=${sign}"
        println(requestURL)
    }


    @Test
    fun `can get payment method`() {
        val baseUrl = "https://apis.map.qq.com"
        // call the api
        val api = Api.create(baseUrl)
        val ip = "111.206.145.41"
        val key = "4BYBZ-7MT6S-PUAOA-6BNWL-FJUD7-UUFXT"
        val sk = "zhNKVrJK6UPOhqIjn8AQvG37b9sz6"
        var call = api.getPaymentMethod(ip, key, null)
        val url = call.request().url().toString()
        val sign = SecureUtil.md5(url + sk)

        call = api.getPaymentMethod(ip, key, sign)
        call.execute()

//        xx.execute()

//        println(xx)


//        println(response)

//        var resJson = GsonBuilder().setPrettyPrinting().create().toJson(response.body())
//        println(resJson)

//        println(response.body())
//
//        println(Gson().toJson(response.body()))

        // verify the response is OK
//        assertEquals(response.code(), 200)
    }
}
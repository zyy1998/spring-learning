package com.github.zyy1998.springlearning.http

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    //   @see https://lbs.qq.com/service/webService/webServiceGuide/webServiceIp
    @GET("/ws/location/v1/ip")
    fun getPaymentMethod(
//            @Header("authorization") bearerAuth: String
            @Query("ip") ip: String,
            @Query("key") key: String,
            @Query("sign") sign: String?,
    ): Call<ResponseBody>

    // factory methods
    companion object {

        fun create(baseUrl: String): Api {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                    // here we set the base url of our API
                    .baseUrl(baseUrl)
                    // 为了能够打印出json，see https://stackoverflow.com/questions/32514410/logging-with-retrofit-2/33256827#33256827
                    .client(client)
                    // add the JSON dependency so we can handle json APIs
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            // here we pass a reference to our API interface
            // and get back a concrete instance
            return retrofit.create(Api::class.java)
        }
    }
}
package com.github.zyy1998.springlearning.cloud.alibaba.pay;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayOpenOperationOpenbizmockBizQueryModel;
import com.alipay.api.request.AlipayOpenOperationOpenbizmockBizQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayOpenOperationOpenbizmockBizQueryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = AliPayApplication.class)
public class AliPayServiceTest {
    @Autowired
    private AliPayService service;
    @Autowired
    private AliPayProperties properties;
    private String returnURL;
    private String notifyURL;

    @BeforeEach
    public void setup() {
        returnURL = properties.getRETURN_URL();
        notifyURL = properties.getNOTIFY_URL();
    }

    @Test
    public void testPublicKey() {
        try {
            // 1. 创建AlipayClient实例
            AlipayClient alipayClient = service.generateClientByPublicKey();
            // 2. 创建使用的Open API对应的Request请求对象
            AlipayOpenOperationOpenbizmockBizQueryRequest request = getRequest();
            // 3. 发起请求并处理响应
            AlipayOpenOperationOpenbizmockBizQueryResponse response;
            // 公钥方式执行
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功。");
            } else {
                System.out.println("调用失败，原因：" + response.getMsg() + "，" + response.getSubMsg());
            }
        } catch (Exception e) {
            System.out.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Test
    public void testCert() {
        try {
            // 1. 创建AlipayClient实例
            AlipayClient alipayClient = service.generateClientByCert();
            // 2. 创建使用的Open API对应的Request请求对象
            AlipayOpenOperationOpenbizmockBizQueryRequest request = getRequest();
            // 3. 发起请求并处理响应
            AlipayOpenOperationOpenbizmockBizQueryResponse response;
            // 公钥证书方式执行
            response = alipayClient.certificateExecute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功。");
            } else {
                System.out.println("调用失败，原因：" + response.getMsg() + "，" + response.getSubMsg());
            }
        } catch (Exception e) {
            System.out.println("调用遭遇异常，原因：" + e.getMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 测试生成订单
     *
     * @link https://opendocs.alipay.com/open/270/105899
     */
    @Test
    public void testPay() throws AlipayApiException {
        AlipayClient alipayClient = service.generateClientByPublicKey();
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl(returnURL);
        alipayRequest.setNotifyUrl(notifyURL); //在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\"20150320010101001\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":1.88," +
                "    \"subject\":\"Iphone6 16G\"," +
                "    \"body\":\"Iphone6 16G\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }" +
                "  }"); //填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        System.out.println(form);
//        httpResponse.setContentType("text/html;charset=" + CHARSET);
//        httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
//        httpResponse.getWriter().flush();
//        httpResponse.getWriter().close();
    }

    private AlipayOpenOperationOpenbizmockBizQueryRequest getRequest() {
        // 初始化Request，并填充Model属性。实际调用时请替换为您想要使用的API对应的Request对象。
        AlipayOpenOperationOpenbizmockBizQueryRequest request = new AlipayOpenOperationOpenbizmockBizQueryRequest();
        AlipayOpenOperationOpenbizmockBizQueryModel model = new AlipayOpenOperationOpenbizmockBizQueryModel();
        model.setBizNo("test");
        request.setBizModel(model);
        return request;
    }

}

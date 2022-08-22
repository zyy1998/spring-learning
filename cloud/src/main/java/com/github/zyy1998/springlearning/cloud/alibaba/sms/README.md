- https://dysms.console.aliyun.com

# 发送短信文档示例

该项目为调用SendSms接口发送短信，发送前请申请短信签名和短信模板，并确保签名和模板已审核通过。文档示例，该示例**无法在线调试**，如需调试可下载到本地后替换 [AK](https://usercenter.console.aliyun.com/#/manage/ak) 以及参数后进行调试。

## 运行条件

- 下载并解压需要语言的代码;

- 在阿里云帐户中获取您的 [凭证](https://usercenter.console.aliyun.com/#/manage/ak)并通过它替换下载后代码中的 ACCESS_KEY_ID 以及 ACCESS_KEY_SECRET;

- 执行对应语言的构建及运行语句

## 执行步骤
下载的代码包，在根据自己需要更改代码中的参数和 AK 以后，可以在**解压代码所在目录下**按如下的步骤执行

- Java
*JDK 版本要求 1.8*
```sh
mvn clean package
java -jar target/sample-1.0.0-jar-with-dependencies.jar
```

## 使用的 API

-  SendSms 调用SendSms接口发送短信，发送前请申请短信签名和短信模板，并确保签名和模板已审核通过。文档示例，可以参考：[文档](https://next.api.aliyun.com/document/Dysmsapi/2017-05-25/SendSms)

## 返回示例

*实际输出结构可能稍有不同，属于正常返回；下列输出值仅作为参考，以实际调用为准*

- JSON 格式 
```json
{
    "Message": "OK",
    "RequestId": "F655A8D5-B967-440B-8683-DAD6FF8DE990",
    "Code": "OK",
    "BizId": "900619746936498440^0"
}
```
- XML 格式 
```xml
<SendSmsResponse>
      <Message>OK</Message>
      <RequestId>44DF7A95-603F-4651-9298-BE1850BEB53F</RequestId>
      <BizId>336006646937050335^0</BizId>
      <Code>OK</Code>
</SendSmsResponse>
```
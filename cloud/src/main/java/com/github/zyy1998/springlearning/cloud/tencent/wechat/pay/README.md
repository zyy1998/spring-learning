- appid是[公众号](https://mp.weixin.qq.com/)id, mchild是[商户](https://pay.weixin.qq.com/)id
- mchild要绑定appid后才能使用。在[商户平台->产品中心->AppID账号管理](https://pay.weixin.qq.com/index.php/extend/merchant_appid/mapay_platform/account_manage)发起申请，进行AppID关联。
- 在[公众平台->微信支付](https://mp.weixin.qq.com/misc/pluginloginpage?pluginuin=10010)通过审核。

微信支付有两套接口，一套是服务商接口，一套是商户接口。商户既可以自己开发，也可以接入服务商进行开发。


- [Native支付开发指引](https://pay.weixin.qq.com/wiki/doc/apiv3_partner/open/pay/chapter2_7_2.shtml)
- [Native下单API](https://pay.weixin.qq.com/wiki/doc/apiv3_partner/apis/chapter4_4_1.shtml)
- [wechatpay-apache-httpclient](https://github.com/wechatpay-apiv3/wechatpay-apache-httpclient)


## 内网穿透
### frp
- 文档: https://gofrp.org/docs/setup/
- 下载 [frp_0.44.0_windows_amd64.zip](https://github.com/fatedier/frp/releases/download/v0.44.0/frp_0.44.0_windows_amd64.zip) 和 [frp_0.44.0_linux_amd64.tar.gz](https://github.com/fatedier/frp/releases/download/v0.44.0/frp_0.44.0_linux_amd64.tar.gz)
- 服务器配置
  ```bash
  tar -zxvf frp_0.44.0_linux_amd64.tar.gz
  ```
  ```ini
  # frps.ini
  # 需要打开7000 7001 和 7002 端口的防火墙
  
  [common]
  bind_addr = 0.0.0.0
  bind_port = 7000
  # 配置web域名的时候可能会用到, @see https://gofrp.org/docs/examples/vhost-http/
  # vhost_http_port = 7002
  dashboard_addr = 0.0.0.0
  dashboard_port = 7001
  dashboard_user = admin
  dashboard_pwd = 密码
  token = 密码
  ```
  ```bash
  ./frps -c frps.ini
  ```
- 客户端配置
  ```ini
  # frpc.ini
  
  [common]
  server_addr = 腾讯云ip
  server_port = 7000
  admin_addr = 127.0.0.1
  # 客户端的看板
  admin_port = 7001
  admin_user = admin
  admin_pwd = 密码
  token = 跟服务器token一致 

  # 应用名，把本地8080绑定到服务器7002上
  [callback]
  type = tcp
  local_ip = 127.0.0.1
  local_port = 8080
  remote_port = 7002
  ```
  ```shell
  .\frpc.exe -c .\frpc.ini
  ```
### https
- https://cloud.tencent.com/developer/article/1411028?from=15425

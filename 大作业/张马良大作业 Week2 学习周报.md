# 云计算大作业学习周报Week 2

16340291 张马良

本周我实现了Http服务器的GET方法。

**语言**：Java

**运行环境**：Eclipse

**测试环境**：Windows Chrome浏览器

**项目地址**：[Java Http Server](https://github.com/PeanutADi/JavaSocketHttpServer)

## 实验过程

实现Http协议主要要解析请求头 那么了解请求头的内容之后用字符串方法对其进行解析即可。

首先使用Socket进行连接，将字符串切分后获得请求的方法和请求的内容。

之后将请求的文件通过socket以字节形式发给请求的client。

详细代码请参考github！

## 实验结果

1. 在实验中碰到的问题：

   浏览器会自动发送一个Get请求获取一个favicon.ico文件，因此会报错。百度后发现是浏览器标题那里现实的一个icon，而我的目录下没有设置。

   ![get favicon.png](https://i.loli.net/2019/06/17/5d06ff33ac8a288720.png)


![favicon.png](https://i.loli.net/2019/06/17/5d06ff33684c567763.png)

2. Console中显示的请求内容和我的Server的response：

   Get请求：

   ![get请求.png](https://i.loli.net/2019/06/17/5d06ff33b2d7c82628.png)

   Response：witeHtml即发送字节流

   ![response.png](https://i.loli.net/2019/06/17/5d06ff3379fc934253.png)

3. 最终Browser的页面：

   ![Get方法最终页面.png](https://i.loli.net/2019/06/17/5d06ff33ec52528585.png)


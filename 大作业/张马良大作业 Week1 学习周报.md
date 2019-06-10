# 云计算大作业 Week1 学习周报

姓名：张马良 学号：16340291

选题：Http服务器

## Http协议

设计一个Restful风格的Http服务器需要关注两点：

- 请求的数据怎么按照http的协议解析出来

- 如何按照http协议，返回数据

所以需要知道Http数据格式的规范，包括request header & response header。

![请求头](https://upload-images.jianshu.io/upload_images/1405936-a6d0869ffc2dfa9e.jpg?imageMogr2/auto-orient/)

![回应头](https://upload-images.jianshu.io/upload_images/1405936-4cd765051a0bf854.jpg?imageMogr2/auto-orient/)

不管是请求消息还是相应消息，都可以划分为三部分，这就为我们后面的处理简化了很多

- 第一行：状态行

- 第二行到第一个空行：header（请求头/相应头)
- 剩下所有：正式内容（Body）

## Socket编程基础

我准备使用Java Socket完成编程，使用ServerSocket来绑定端口。

ServerSocket提供tcp服务。

ServerSocket的服务方法如下：

- 创建ServerSocket对象，绑定监听端口
- 通过accept()方法监听客户端请求
- 连接建立后，通过输入流读取客户端发送的请求信息
- 通过输出流向客户端发送乡音信息
- 关闭相关资源

```java
ServerSocket serverSocket = new ServerSocket(port, ip)
serverSocket.accept();
// 接收请求数据
socket.getInputStream();

// 返回数据给请求方
out = socket.getOutputStream()
out.print(xxx)
out.flush();;

// 关闭连接
socket.close()
```


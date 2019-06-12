## 实现http服务器（一）

### http协议

>  http（超文本传输协议）是一个基于请求与响应模式的、无状态的、应用层的协议，常基于TCP的连接方式，HTTP1.1版本中给出一种持续连接的机制，绝大多数的Web开发，都是构建在HTTP协议之上的Web应用。

### Request

#### 请求报文

一个HTTP请求报文由请求行（request line）、请求头部（header）、空行和请求数据4个部分组成。

![img](https://pic002.cnblogs.com/images/2012/426620/2012072810301161.png)

#### 请求方法

请求行由请求方法字段、URL字段和HTTP协议版本字段3个字段组成，它们用空格分隔。

请求方法的解释如下：

> GET     请求获取Request-URI所标识的资源
>
> POST    在Request-URI所标识的资源后附加新的数据
>
> HEAD    请求获取由Request-URI所标识的资源的响应消息报头
>
> PUT     请求服务器存储一个资源，并用Request-URI作为其标识
>
> DELETE  请求服务器删除Request-URI所标识的资源
>
> TRACE   请求服务器回送收到的请求信息，主要用于测试或诊断
>
> CONNECT 保留将来使用
>
> OPTIONS 请求查询服务器的性能，或者查询与资源相关的选项和需求

#### 请求头部

请求报头允许客户端向服务器端传递请求的附加信息以及客户端自身的信息。

常用的请求报头

> * Accept	
>
>   请求报头域用于指定客户端接受哪些类型的信息。
>
> * Accept-Charset	
>
>   请求报头域用于指定客户端接受的字符集。
>
> * Accept-Encoding	
>
>   请求报头域类似于Accept，但是它是用于指定可接受的内容编码。
>
> * Accept-Language	
>
>   请求报头域类似于Accept，但是它是用于指定一种自然语言。
>
> * Authorization	
>
>   请求报头域主要用于证明客户端有权查看某个资源。当浏览器访问一个页面时，如果收到服务器的响应代码为401（未授权），可以发送一个包含Authorization请求报头域的请求，要求服务器对其进行验证。
>
> * Host	
>
>   请求报头域主要用于指定被请求资源的Internet主机和端口号，它通常从HTTP URL中提取出来的。
>
> * User-Agent
>
>   User-Agent请求报头域允许客户端将它的操作系统、浏览器和其它属性告诉服务器。

#### 请求数据

请求数据不在GET方法中使用，而是在POST方法中使用。POST方法适用于需要客户填写表单的场合。与请求数据相关的最常使用的请求头是Content-Type和Content-Length。



### http报文

#### 报文格式

HTTP响应由三个部分组成，分别是：状态行、消息报头、响应正文。

> ＜status-line＞
>
> ＜headers＞
>
> ＜blank line＞
>
> [＜response-body＞]

在响应中唯一真正的区别在于第一行中用状态信息代替了请求信息。状态行（status line）通过提供一个状态码来说明所请求的资源情况。

#### 状态行

状态行格式如下：

>  HTTP-Version Status-Code Reason-Phrase CRLF

其中，HTTP-Version表示服务器HTTP协议的版本；Status-Code表示服务器发回的响应状态代码；Reason-Phrase表示状态代码的文本描述。

状态代码有三位数字组成，第一个数字定义了响应的类别，且有五种可能取值：

> 1xx：指示信息--表示请求已接收，继续处理
>
> 2xx：成功--表示请求已被成功接收、理解、接受
>
> 3xx：重定向--要完成请求必须进行更进一步的操作
>
> 4xx：客户端错误--请求有语法错误或请求无法实现
>
> 5xx：服务器端错误--服务器未能实现合法的请求

常见状态代码、状态描述、说明：

> 200 OK      //客户端请求成功
>
> 400 Bad Request  //客户端请求有语法错误，不能被服务器所理解
>
> 401 Unauthorized //请求未经授权，这个状态代码必须和WWW-Authenticate报头域一起使用 
>
> 403 Forbidden  //服务器收到请求，但是拒绝提供服务
>
> 404 Not Found  //请求资源不存在，eg：输入了错误的URL
>
> 500 Internal Server Error //服务器发生不可预期的错误
>
> 503 Server Unavailable  //服务器当前不能处理客户端的请求，一段时间后可能恢复正常

 #### 响应报头

响应报头允许服务器传递不能放在状态行中的附加响应信息，以及关于服务器的信息和对Request-URI所标识的资源进行下一步访问的信息。

常用的响应报头

> * Location
>
>   Location响应报头域用于重定向接受者到一个新的位置。Location响应报头域常用在更换域名的时候。
>
> * Server
>
>   Server响应报头域包含了服务器用来处理请求的软件信息。与User-Agent请求报头域是相对应的。
>
> * WWW-Authenticate
>
>   WWW-Authenticate响应报头域必须被包含在401（未授权的）响应消息中，客户端收到401响应消息时候，并发送Authorization报头域请求服务器对其进行验证时，服务端响应报头就包含该报头域。
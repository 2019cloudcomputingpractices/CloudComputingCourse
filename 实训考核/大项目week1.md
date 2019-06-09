# 本周具体工作计划
+ socket概念学习
+ 初步实现一个简单的socket访问的服务器

# 本周主要工作内容
+ 学习socket概念（假装之前不知道）
+ 用go语言开发一个socket访问的服务器demo，并且能提取出http报文，能用postman和浏览器访问

## 已完成工作

### socket介绍
Socket起源于Unix，而Unix基本哲学之一就是“一切皆文件”，都可以用“打开open –> 读写write/read –> 关闭close”模式来操作。Socket就是该模式的一个实现，网络的Socket数据传输是一种特殊的I/O，Socket也是一种文件描述符。Socket也具有一个类似于打开文件的函数调用：Socket()，该函数返回一个整型的Socket描述符，随后的连接建立、数据传输等操作都是通过该Socket实现的。

常用的Socket类型有两种：流式Socket（SOCK_STREAM）和数据报式Socket（SOCK_DGRAM）。流式是一种面向连接的Socket，针对于面向连接的TCP服务应用；数据报式Socket是一种无连接的Socket，对应于无连接的UDP服务应用。

要注意socket并不是什么新的协议，它只是一种api接口，本质上是对TCP和UDP的封装，比如把双方的ip和端口号记下来然后在每次发报文的时候就不用手动再去规定报文的各项参数了，所以什么流式Socket三次握手四次挥手和TCP是基本一模一样的，一般我们使用都是使用流式socket，因为流式socket是基于TCP的，而TCP可以保证报文100%按序到达，只有在传递一些不是很重要的信息，丢包了也无所谓的信息上面，才有可能会使用数据报式socket，也就是基于UDP。我们本次实验当然也是使用基于TCP的流式socket。

### 实验过程
首先在go中一个TCP的地址信息表示如下：
```golang
type TCPAddr struct {
	IP IP
	Port int
	Zone string // IPv6 scoped addressing zone
}
```
IP是[]byte类型的，可以认为是c++里的char[]，存储ip信息，Port存储端口号，Zone存储ipv6特有的scoped addressing zone(本次实验只用ipv4，基本不用管)。

然后就可以用net.ResolveTCPAddr函数把类似ip:port的字符串解析成TCPAddr：
```golang
tcpAddr, err := net.ResolveTCPAddr("tcp4", service)
```

然后用net.DialTCP发起连接请求即可：
```golang
conn, err := net.DialTCP("tcp", nil, tcpAddr)
```

这样就可以使用socket的Read,write操作了：
```golang
_, err = conn.Write([]byte("HEAD / HTTP/1.0\r\n\r\n"))
```

客户端的所有代码如下（仅供熟悉概念使用，本次实验客户端是浏览器和postman）：
```golang
package main

import (
	"fmt"
	"io/ioutil"
	"net"
	"os"
)

func main() {
	if len(os.Args) != 2 {
		fmt.Fprintf(os.Stderr, "Usage: %s host:port ", os.Args[0])
		os.Exit(1)
	}
	service := os.Args[1]
	tcpAddr, err := net.ResolveTCPAddr("tcp4", service)
	checkError(err)
	conn, err := net.DialTCP("tcp", nil, tcpAddr)
	checkError(err)
	_, err = conn.Write([]byte("HEAD / HTTP/1.0\r\n\r\n"))
	checkError(err)
	result, err := ioutil.ReadAll(conn)
	checkError(err)
	fmt.Println(string(result))
	os.Exit(0)
}
func checkError(err error) {
	if err != nil {
		fmt.Fprintf(os.Stderr, "Fatal error: %s", err.Error())
		os.Exit(1)
	}
}
```

上面就是客户端的操作，下面说一下服务端,服务端的话golang提供了一个很方便的listen函数，直接监听某个端口，然后从listener中可以直接获得socket的connection对象，先开始监听并获得listener：
```golang
	addr := "0.0.0.0:7777" //表示监听本地所有ip的8080端口，也可以这样写：addr := ":8080"
	listener, err := net.Listen("tcp", addr)
```

然后就可以用一个for循环不断接受连接请求，并且获得此次连接的connection对象：
```golang
	for {
		conn, err := listener.Accept() //用conn接收链接
		if err != nil {
			log.Fatal(err)
		}
		go Handle_conn(conn) //将接受来的链接交给该函数去处理。
	}
```

这里的conn就是一个和客户端交互的对象了，正如前面所说，socket是用read和write操作来进行交互的，之所以要用go Handle_conn函数主要是为了提高并发性，go语句就是一个创建go程的操作（可以理解为线程，尽管实际上它比线程还要小）。然后具体处理每个connection的函数就都可以在单独的go程中完成而不会阻塞for循环，从而可以同时处理多个连接

这里Handle_conn函数也很简单：
```golang
func Handle_conn(conn net.Conn) {
	fmt.Println("handle one connection")
	buf := make([]byte, 1024)
	_, err := conn.Read(buf)
	fmt.Println("get result success")
	if err != nil {
		fmt.Println(err.Error())
		conn.Close()
	}
	fmt.Println(string(buf))
	conn.Write([]byte(content)) //将html的代码返回给客户端，这样客户端在web上访问就可以拿到指定字符。
	conn.Close()
}
```

可以看到上面的代码就是把客户端write的东西从read里面取出来，然后把http报文再write回去，这里的content就是随便找了个报文头，再加了个很简单的报文体：
```golang
var content = `HTTP/1.1 200 OK
Date: Sat, 29 Jul 2017 06:18:23 GMT
Content-Type: text/html
Connection: Keep-Alive
Server: BWS/1.1
X-UA-Compatible: IE=Edge,chrome=1
BDPAGETYPE: 3
Set-Cookie: BDSVRTM=0; path=/

test
`
```

所以整体的服务端demo代码如下：
```golang
package main

import (
	"fmt"
	"log"
	"net"
)

var content = `HTTP/1.1 200 OK
Date: Sat, 29 Jul 2017 06:18:23 GMT
Content-Type: text/html
Connection: Keep-Alive
Server: BWS/1.1
X-UA-Compatible: IE=Edge,chrome=1
BDPAGETYPE: 3
Set-Cookie: BDSVRTM=0; path=/

test
`

func Handle_conn(conn net.Conn) { //这个是在处理客户端会阻塞。
	fmt.Println("handle one connection")
	buf := make([]byte, 1024)
	_, err := conn.Read(buf)
	fmt.Println("get result success")
	if err != nil {
		fmt.Println(err.Error())
		conn.Close()
	}
	fmt.Println(string(buf))
	conn.Write([]byte(content)) //将html的代码返回给客户端，这样客户端在web上访问就可以拿到指定字符。
	conn.Close()
}

func main() {
	addr := "0.0.0.0:7777" //表示监听本地所有ip的8080端口，也可以这样写：addr := ":8080"
	listener, err := net.Listen("tcp", addr)
	if err != nil {
		log.Fatal(err)
	}
	defer listener.Close()

	for {
		conn, err := listener.Accept() //用conn接收链接
		if err != nil {
			log.Fatal(err)
		}
		go Handle_conn(conn) //将接受来的链接交给该函数去处理。
	}
}
```

然后我们运行测试一下，先用浏览器测试：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/1.png)

可以看到可以正常显示出内容，我们再看下服务器发过来的报文：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/2.png)

我们可以看到浏览器发出了两次get请求，一次是/一次是/favicon.ico，然后就是请求头的内容，请求体为空。

我们再用postman测试：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/3.png)

可以看到也可以正确显示http报文体的内容，这时postman的报文如下：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/4.png)

可以看到其实也差不多，只不过只get了一次，说明我们的服务器基本架构是没有问题的，接下来要实现的内容就是把浏览器和postman发过来的报文解析出来，并且返回相应的内容。


## 未完成工作
只是完成了socket部分，具体解析http报文这部分还没做
## 问题与困难
整体没什么大困难，就是看了一个很坑的教程，用了一个ioutil.ReadAll方法，这个方法只有在遇到EOF的时候才会返回，在客户端用如果服务端每次都close掉了连接就无所谓，但是如果在服务端用，不直接用Read的话，因为客户端是不会close的，所以就会一直卡在那里，而且这个函数很坑的一点是它至少会分配512bytes的空间，这其实是很浪费的，请求量少无所谓，请求量一大很容易炸，所以能不用还是不用的好。

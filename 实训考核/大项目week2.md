# 本周具体工作计划
+ 用socket实现http报文的解析和发送
+ 实现一个支持post,get,head请求的web服务器

# 本周主要工作内容
+ 学习http概念（假装之前不知道）
+ 用go语言开发一个socket访问的服务器,解析发过来的http报文，并进行解析，并且根据发过来的http报文内容返回对应的http报文

## 已完成工作

### http报文介绍
HTTP报文：它是HTTP应用程序之间发送的数据块。这些数据块以一些文本形式的元信息开头，这些信息描述了报文的内容及含义，后面跟着可选的数据部分。这些报文都是在客户端、服务器和代理之间流动。
 
HTTP报文的流动方向：一次HTTP请求，HTTP报文会从“客户端”流到“代理”再流到“服务器”，在服务器工作完成之后，报文又会从“服务器”流到“代理”再流到“客户端”

报文的语法：所有的HTTP报文都可以分为两类，请求报文和响应报文。请求和响应报文的基本报文结构大致是相同的，只有起始行的语法有所不同。

#### 请求报文
请求报文会向web服务器请求一个动作，格式如下：
```
起始行： <method> <request-URL> <version>

头部：   <headers>

主体：   <entity-body>
```

下面是对各部分的简要描述：

+ 方法(method)：客户端希望服务器对资源执行的动作，是一个单独的词，比如，GET、POST或HEAD

+ 请求URL(request-URL)：要直接与服务器进行对话，只要请求URL是资源的绝对路径就可以了，服务器可以假定自己是URL的主机/端口，比如/login等

+ 版本(version)：报文所使用的HTTP版本。其格式：HTTP/<主要版本号>.<次要版本号>，最常见的是HTTP/1.1

+ 头部(header)：可以有零个或多个头部，每个首部都包含一个名字，后面跟着一个冒号(:)，然后是一个可选的空格，接着是一个值，最后是一个CRLF首部是由一个空行(CRLF)结束的，表示了头部列表的结束和实体主体部分的开始

+ 实体的主体部分(entity-body)：实体的主体部分包含一个由任意数据组成的数据块，并不是所有的报文都包含实体的主体部分，有时，报文只是以一个CRLF结束。

#### 响应报文
响应报文会将请求的结果返回给客户端，格式和请求报文类似但是稍有不同，格式如下：
```
起始行：  <version> <status> <reason-phrase>

头部：    <headers>

主体：    <entity-body>
```
这里和请求报文不同的地方主要是多了status和reason-phrase，这两部分的作用如下：

+ 状态码(status-code)：状态码是三位数字，描述了请求过程中所发生的情况。每个状态码的第一位数字都用于描述状态的一般类别(比如，“成功”、“出错”等等)

+ 原因短语(reason-phrase)：数字状态码的可读版本，包含行终止序列之前的所有文本。原因短语只对人类有意义，因此，尽管响应行HTTP/1.0 200 NOT OK和HTTP/1.0 200 OK中原因短语的含义不同，但同样都会被当作成功指示处理

还有一点不同就是头部的常见字段通常不太一样，比如请求报文中一般会含Cookie字段，而响应报文中则是Set-Cookie字段，但是格式一般是一样的。

### http解析过程
首先我们根据上面介绍的报文结构，定义一些结构体（也可以认为是类，在golang中两者并没有区别，准确的来说golang没有类的概念，一般用接口来代替）：
请求起始行：
```golang
type Requestline struct {
	method  string
	url     string
	version string
}
```

头部：
```golang
type Header map[string]string
```

请求体：
```golang
type Requestbody string
```

响应起始行：
```golang
type Responseline struct {
	version     string
	status      int
	description string
}
```

响应体：
```golang
type Responsebody string
```

所以完整的请求报文如下：
```golang
type RequestMessage struct {
	requestline Requestline
	header      Header
	requestbody Requestbody
}
```

同理完整的相应报文如下：
```golang
type ResponseMessage struct {
	responseline Responseline
	header       Header
	responsebody Responsebody
}
```

建立了这些结构体后，我们就要将报文的内容解析到这些结构体或类型内，上周我们在Handle_conn函数中已经获取了整个报文的string类型buf，所以我们创建ParseRequestMessage函数来解析请求报文：
```golang
func ParseRequestMessage(mess_str string) (RequestMessage, error) {
	var RequestMessage RequestMessage
	lines := strings.Split(mess_str, "\n")
	//parse request line
	var err error
	RequestMessage.requestline, err = ParseRequestLine(lines[0])
	if err != nil {
		return RequestMessage, err
	}
	//find header content
	index := 1
	for ; index < len(lines) && strings.Trim(lines[index], " \n\t\r") != ""; index++ {
		continue
	}
	//parse header
	RequestMessage.header, err = ParseHeader(lines[1:index])
	for ; index < len(lines) && strings.Trim(lines[index], " \n\t\r") == ""; index++ {
		continue
	}
	//parse body
	RequestMessage.requestbody = Requestbody(strings.Join(lines[index:], "\n"))
	return RequestMessage, err
}
```

其中为了避免函数变的太过臃肿，还分成了ParseRequestLine和ParseHeader等函数，代码这里就不贴了，可以去代码仓库查看

通过这个函数我们就得到了一个RequestMessage类型的对象，这里我们先创建一个router.go文件，并且在其中添加HandleMessage函数：
```golang
func HandleMessage(mess *httpparser.RequestMessage) string {
	if mess.GetUrl() == "/register" {
		return controller.HandleRegister(mess)
	} else if mess.GetUrl() == "/login" {
		return controller.HandleLogin(mess)
	} else {
		return controller.HandleStatic(mess)
	}
}
```
这个函数主要就是起到一个路由的作用，判断报文的url分别引导到不同的controller中，controller则主要负责对于某个具体的url，根据报文的内容返回相应的内容，以登录为例，对应的url为/login，对应的controller中的函数是HandleLogin:
```golang
func HandleLogin(mess *httpparser.RequestMessage) string {
	if mess.GetMethod() == "GET" || mess.GetMethod() == "HEAD" {
		content, err := ioutil.ReadFile("./static/login.html")
		if err != nil {
			fmt.Println("file not found")
			return NotFound()
		}
		responseline := httpparser.GetResponseLine("HTTP/1.1", 200, "OK")
		header := make(map[string]string)
		header["Content-Type"] = "text/html; charset=utf-8"
		responsebody := httpparser.Responsebody("")
		if mess.GetMethod() == "GET" {
			responsebody = httpparser.Responsebody(content)
		}
		mess := httpparser.GetResponseMessage(responseline, httpparser.Header(header), responsebody)
		return mess.ToString()
	} else if mess.GetMethod() == "POST" {
		fmt.Println("controller handle post user")
		formMap := httpparser.ParseForm(mess.GetBody())
		username := formMap["username"]
		password := formMap["password"]
		fmt.Println("username: "+username+", password: ", password)
		err := model.Login(username, password)
		info := "登录成功"
		if err != nil {
			info = err.Error()
		}
		responseline := httpparser.GetResponseLine("HTTP/1.1", 200, "OK")
		header := make(map[string]string)
		header["Content-Type"] = "text/html; charset=utf-8"
		t, _ := template.ParseFiles("static/info.html")
		buf := bytes.NewBufferString("")
		t.Execute(buf, info)
		responsebody := httpparser.Responsebody(buf.String())
		mess := httpparser.GetResponseMessage(responseline, httpparser.Header(header), responsebody)
		return mess.ToString()
	} else {
		return NotFound()
	}
}
```
在HandleLogin中，首先先判断请求报文的method，如果method为GET或者HEAD，则为获取登录的界面，因此先读login.html文件，并且响应报文的起始行是HTTP/1.1 200, OK，然后头部中Content-Type设为text/html; charset=utf-8，然后因为HEAD请求并不需要body的信息，所以在这里再进一步判断method具体是GET还是HEAD,决定是否把login.html的内容放入body中，如果是HEAD,body就可以为空。

而如果method为POST，则为用户登录，所以要先调用httpparser.ParseForm（具体实现见源码仓库，不贴）将body中含有的username和password提取出来，然后调用model.Login进行判断是否登录成功（这里的model类主要处理一些数据库相关逻辑，不是本次项目的重点，所以就不展开描述了）。不过返回的内容我们用一个info.html的模版，然后用golang自带的template库将提示信息插入模版中，然后将最终的内容放入响应报文的body中。

其他静态文件或者是404 not found的相应报文也和上面描述的差不多，只不过一些字段的内容有所修改而已，因此不再赘述

### 测试结果

注册界面：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/5.png)

登录界面：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/6.png)

注册成功：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/7.png)

登录成功：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/8.png)

登录失败：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/9.png)

可以看到服务器已经可以成功和浏览器对接，说明我们开发的web服务器可以正确处理报文并且返回正确的http报文


## 未完成工作
还未在虚拟机上进行部署，并发量方面还有优化的空间
## 问题与困难
整体没什么大困难，就是报告忘了写了，剩下的就是各种自己脑抽写了些很降智的代码，不过慢慢debug都找出问题了

附上源代码仓库：
[源码仓库](https://github.com/2019cloudcomputingpractices/socketserver)


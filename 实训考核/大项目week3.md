# 本周具体工作计划
+ 进行服务器的部署工作

# 本周主要工作内容
+ 将用go语言实现的socket服务器部署到openstack配置的虚拟机上

## 已完成工作
首先因为并不知道ubuntu-server镜像的用户名密码，所以我配置了一个密钥对并且将公钥添加到了我所创建的实例中：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/10.png)

![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/11.png)

然后我们就可以通过生成密钥对时下载下来的私钥来登录ubuntu-server的虚拟机：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/12.png)

然后运行sudo su，但是这时我们发现好像虚拟服务器不知道他自己的名字，导致出现了time out的错误，所以我们在host里面配置了一下，让ubuntu-server可以知道它的名字：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/13.png)


然后我们进行golang的配置，首先将go1.12.6.linux-amd64.tar.gz下载到/usr/local,并解压，这样就会生成/usr/local/go目录：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/14.png)

然后在/root下创建go目录，当做GOPATH，并且创建src,bin,models,package然后我们进入/root/.bashrc，添加GOPATH和GOROOT的路径，并且将GOPATH和GOROOT的bin目录添加到PATH中：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/17.png)


![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/15.png)

然后我们运行go env，可以看到我们的配置没有问题：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/16.png)

然后我们运行:
```
go get github.com/2019cloudcomputingpractices/socketserver
```
这里遇到了无法解析github.com域名的问题，很显然是dns有问题，我们把dns设成10.8.4.4：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/20.png)

然后我们再运行上面的go get命令就没有问题了

这样项目源码就被下载到了/root/go/src/github.com/2019cloudcomputingpractices/socketserver下，进入socketserver文件夹，运行go build生成可执行文件，就可以生成socketserver的可执行文件了，然后我们运行这个可执行文件：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/18.png)

然后从浏览器访问：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/19.png)

可以看到可以正常访问，说明我们的配置已经成功了，当然为了能在关掉终端后还是可以继续运行，我们用nohup命令让其在后台运行即可：
![](https://raw.githubusercontent.com/2019cloudcomputingpractices/CloudComputingCourse/16340147-%E5%88%98%E6%81%92%E4%BC%9F/实训考核/image/21.png)

## 未完成工作
应该没了吧，收工了

## 问题与困难
因为之前就配过所以基本没什么困难，就是一开始那个sudo su出现问题查了一下，其他的配置都比较顺利


附上源代码仓库：
[源码仓库](https://github.com/2019cloudcomputingpractices/socketserver)


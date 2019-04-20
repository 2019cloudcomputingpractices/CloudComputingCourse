# 第三周学习周报

## img,iso和qcow2
### **iso**
国际标准光盘文件系统格式。符合ISO 9660标准的光盘镜像文件格式，文件扩展名通常为iso。这种文件可以简单的理解为复制光盘上全部信息而形成的镜像文件。

### **img**
是一种主要是为了创建软盘的镜像文件，它可以用来压缩整个软盘或整片光盘的内容，可视为.ISO格式的一种超集合。由于.ISO只能压缩使用ISO9660和UDF这两种文件系统的存储媒介，意即.ISO只能拿来压缩CD或DVD，因此才发展出了.IMG，它是以.ISO格式为基础另外新增可压缩使用其它文件系统的存储媒介的能力，.IMG可向后兼容于.ISO，如果是拿来压缩CD或DVD，则使用.IMG和.ISO这两种格式所压缩出来的内容是一样的。打开方式可以是光盘刻录，也可以用软件解压。

### **qcow2**
qcow磁盘映像的一个主要特性是，这种格式的文件可以随着数据的添加而增长。
qcow格式还允许使用“复制即写”(copy on write)将只读基础映像存储在单独的qcow文件上。


## 指令
1,查看运行的虚拟机

virsh list

2,查看所有的虚拟机（关闭和运行的虚拟机）

virsh list --all

3,连接虚拟机

virsh console +域名（虚拟机的名称）

4，退出虚拟机

ctrl+]

5,关闭虚拟机

5.1    virsh shutdown +域名  
5.2  virsh destroy +域名

这种方式的关闭，是一种删除的方式，只是在virsh list中删除了该虚拟机。

 

6，挂起虚拟机

virsh suspend +域名

 

7，恢复被挂起的虚拟机

virsh resume +域名

8，子机随宿主主机（母机）启动而启动

virsh autostart + 域名

9，取消自动启动

virsh auotstart --disable +域名

10，彻底删除虚拟机

1, 删除虚拟机   virsh destroy +域名

2，解除标记     virsh undefine +域名

3，删除虚拟机文件  

 11,启动虚拟机并进入该虚拟机

virsh start 域名 --console

## 操作过程：
按照群里的task3操作指南进行操作
先登录SSH创建帐号

![创建帐号rinhua.png](https://i.loli.net/2019/04/20/5cbb088cf3efc.png)

![帐号信息.png](https://i.loli.net/2019/04/20/5cbb088cf19ef.png)

查询网络状态

![default网络.png](https://i.loli.net/2019/04/20/5cbb088cf1a27.png)

复制、安装完镜像后在VNC中安装

![安装.png](https://i.loli.net/2019/04/20/5cbb088f344c6.png)

安装成功后上传

![上传镜像.png](https://i.loli.net/2019/04/20/5cbb088d12230.png)

创建实例

![实例创建成功.png](https://i.loli.net/2019/04/20/5cbb088ee2eba.png)

查询镜像的ip

![ip.png](https://i.loli.net/2019/04/20/5cbb088de1382.png)

尚未解决的问题：镜像ping不通外网，该如何联网、如何远程操控




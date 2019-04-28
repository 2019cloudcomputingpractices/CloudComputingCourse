## Task3

### ssh连接服务器

使用命令**ssh cloud2019p3@172.18.228.17**连接服务器

密码为**p3admincloud2019**



### 进入root角色

输入命令**su root**

密码为**p3admincloud2019**



### 创建自己的用户角色，并将角色加入sudo用户组

创建角色使用命令**adduser yhao**，接下来设置密码并填写个人信息

使用**user -aG sudo yhao**将个人用户加入到sudo用户组中，并用**su yhao**切换用户，最后用**sudo -l**查看是否已获得sudo权限

![Step 1.png](https://i.loli.net/2019/04/28/5cc5802fb3a63.png)



### 检查服务器设置

* 检查服务器是否装好了**qemu-img，virsh，virt-install**

![1556287817367.png](https://i.loli.net/2019/04/28/5cc5806c269d8.png)

* 检查virt是否有default网络

![1556287840201.png](https://i.loli.net/2019/04/28/5cc580a9b26c4.png)



### 制作centos镜像

* 先在**~**目录下创建image目录

![1556287906557.png](https://i.loli.net/2019/04/28/5cc580a9b2206.png)

* 将在群里下载的centos安装文件上传到image文件夹中

![1556346944735.png](https://i.loli.net/2019/04/28/5cc580a9b1cfb.png)

* 创建qcow2文件

![1556347175787.png](https://i.loli.net/2019/04/28/5cc581047c708.png)

* 创建vm，安装centos7

![1556353423561.png](https://i.loli.net/2019/04/28/5cc5810419bdd.png)

* 创建成功后可以看到centos-yhao正在运行

![1556353464159.png](https://i.loli.net/2019/04/28/5cc581047e103.png)

* 使用vnc-viewer连接vm

![1556353564540.png](https://i.loli.net/2019/04/28/5cc5810488242.png)

* 选择中文，确定安装位置，最后设置root密码并创建用户，等待安装

![1556353600581.png](https://i.loli.net/2019/04/28/5cc58347ac4e7.png)

![1556353639430.png](https://i.loli.net/2019/04/28/5cc58347bf4e2.png)

![1556353789223.png](https://i.loli.net/2019/04/28/5cc58347c8da8.png)

安装完成后手动重启

* 使用vnc-viewer连接前启动centos-yhao

![1556354480700.png](https://i.loli.net/2019/04/28/5cc5834741a8b.png)

* 使用自己创建的用户登录，创建一个以自己学号姓名命名的文件

![1556354686125.png](https://i.loli.net/2019/04/28/5cc583477fe8e.png)

* 将qcow2上传到openstack

![1556354870495.png](https://i.loli.net/2019/04/28/5cc5834764d93.png)

* 登录openstack查看镜像

![1556354989611.png](https://i.loli.net/2019/04/28/5cc583478bf90.png)

* 创建一个实例，创建成功

![1556358999284.png](https://i.loli.net/2019/04/28/5cc5834782bb7.png)

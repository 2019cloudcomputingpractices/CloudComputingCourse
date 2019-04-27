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

![1556287794191](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556287794191.png)



### 检查服务器设置

* 检查服务器是否装好了**qemu-img，virsh，virt-install**

![1556287817367](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556287817367.png)

* 检查virt是否有default网络

![1556287840201](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556287840201.png)



### 制作centos镜像

* 先在**~**目录下创建image目录

![1556287906557](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556287906557.png)

* 将在群里下载的centos安装文件上传到image文件夹中

![1556346944735](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556346944735.png)

* 创建qcow2文件

![1556347175787](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556347175787.png)

* 创建vm，安装centos7

![1556353423561](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556353423561.png)

* 创建成功后可以看到centos-yhao正在运行

![1556353464159](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556353464159.png)

* 使用vnc-viewer连接vm

![1556353564540](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556353564540.png)

* 选择中文，确定安装位置，最后设置root密码并创建用户，等待安装

![1556353600581](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556353600581.png)

![1556353639430](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556353639430.png)

![1556353789223](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556353789223.png)

安装完成后手动重启

* 使用vnc-viewer连接前启动centos-yhao

![1556354480700](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556354480700.png)

* 使用自己创建的用户登录，创建一个以自己学号姓名命名的文件

![1556354686125](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556354686125.png)

* 将qcow2上传到openstack

![1556354870495](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556354870495.png)

* 登录openstack查看镜像

![1556354989611](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556354989611.png)

* 创建一个实例，创建成功

![1556358999284](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\1556358999284.png)
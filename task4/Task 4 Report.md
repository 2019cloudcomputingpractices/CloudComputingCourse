# Task 4 Report

## 任务：

**安装云桌面**

1. 提供vindesk部署安装包，根据安装文档来安装（忽略激活管理系统部分），注意配置localrc的时候，按服务器小组分配情况配置openstack的ip地址和密码。
2. 安装完成无报错，并且能够打开系统并截图即为任务成功
3. 可以自行探索下相关的功能

**云桌面功能体验**


## 操作过程

### 1. 安装云桌面

**使用ssh将脚本拷贝到服务器，之后解压并执行脚本完成安装：**

使用puTTY进行ssh连接，使用tar命令解压脚本，编辑localrc文件，之后运行脚本安装：

![1.png](https://i.loli.net/2019/05/12/5cd82b4f91beb.png)

检查各个服务是否正常：

![2.png](https://i.loli.net/2019/05/12/5cd82b4f91ca1.png)

之后可以通过浏览器在web端访问管理系统，输入IP地址即可 。用默认的admin账户登陆：

![3.png](https://i.loli.net/2019/05/12/5cd82b50d4e80.png)

### 2.云桌面功能体验

登陆后，查看我的主页：

![4.png](https://i.loli.net/2019/05/12/5cd82b50d38ad.png)

创建教师：

![5.png](https://i.loli.net/2019/05/12/5cd82b50d287d.png)

创建学生：

![6.png](https://i.loli.net/2019/05/12/5cd82b5086189.png)

查看课程桌面：

![7.png](https://i.loli.net/2019/05/12/5cd82b50d3bed.png)

固定桌面：

![83.png](https://i.loli.net/2019/05/12/5cd82b50d415a.png)

再返回客户端重新连接即可进入上面创建好的固定桌面：

![9.png](https://i.loli.net/2019/05/12/5cd82b50858a9.png)

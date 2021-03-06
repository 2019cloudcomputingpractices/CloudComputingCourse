## 任务

**安装云桌面**（小组，在操作文档中体现）

- 提供vindesk部署安装包，根据安装文档来安装(忽略**激活管理系统**部分)，注意配置localrc的时候，按服务器小组分配情况配置openstack的ip地址和密码。
- 安装完成无报错，并且能够打开系统并截图即为任务成功
- 可以自行探索下相关的功能

**云桌面系统功能体验**(小组，在操作文档中体现)

- 创建固定桌面
- 创建一个课程，并创建一个对应的课程桌面，课程名称请命名为自己的分支

**了解云桌面相关背景知识**(个人，在学习周报中体现，作为本次评分重点，请认真自学，不要复制黏贴)

- VDI概念解释以及实现原理（个人理解总结、工作流程，如果通过画图来体现，最好自己画图）
- 实现的协议介绍（spice、vnc、rdp），进行对比

------

## 时间控制

起始时间：2018.04.28
截止时间：2018.05.11 晚上12:00

------

## 提交内容

- 每个人提交学习周报（可以记录遇到的、已经解决的或者未解决的问题，解决问题的过程记录；希望可以简单谈一下截止至课程目前大家对openstack的理解，可以结合openstack与云桌面的联系来谈一下）
- 小组提交部署文档

------

## 注意事项

1. 部署云桌面的过程中，在修改配置文件localrc的时候，请把桌面网关部分关闭，我们不需要这个功能
2. 部署文档中**激活管理系统**部分可以忽略，不影响功能的使用
3. 如果创建win7的桌面，因为我们原来制作镜像的时候没有设置好win7的防火墙（没有开启icmp），导致云桌面系统创建桌面时ping网络测试不通过，会显示异常状态，但这时候其实是可以登上云桌面的
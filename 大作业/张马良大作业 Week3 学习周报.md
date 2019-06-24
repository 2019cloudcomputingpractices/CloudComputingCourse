# 云计算大作业学习周报Week 3

16340291 张马良

本周我实现了Http服务器的POST方法，增加了对异常情况的处理。

**语言**：Java

**运行环境**：Eclipse

**测试环境**：Postman

**项目地址**：[Java Http Server](https://github.com/PeanutADi/JavaSocketHttpServer)

## 实验过程

1. 增加对 请求方法的判断，如果是POST则返回一个POST的html界面。
2. 处理了文件不存在、uri为空、请求头不标准的异常情况，能够完整地输出报错信息，返回400的Response，输出报错Html。
3. 更新了Thread的使用方法，更新增加并发性。
4. 因为使用Postman测试post方法容易且直观，所以使用Postman测试。

## 实验结果

Get请求不存在的文件：
![FileNotFound.png](https://i.loli.net/2019/06/25/5d10f57981e0f12341.png)

Post请求发送的body为null：
![Post null.png](https://i.loli.net/2019/06/25/5d10f5799777429413.png)

Post请求发送body有东西：
![Post test.png](https://i.loli.net/2019/06/25/5d10f5799750939280.png)


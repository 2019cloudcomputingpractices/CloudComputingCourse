
# OpenStack学习周报
## 本周已完成工作内容：学习OpenStack基本概念
## 未完成：无
## 问题与困难：无
### 1.OpenStack简介
OpenStack是一个开源的云计算管理平台项目，由几个主要的组件组合起来完成具体工作。OpenStack支持几乎所有类型的云环境，项目目标是提供实施简单、可大规模扩展、丰富、标准统一的云计算管理平台。OpenStack通过各种互补的服务提供了基础设施即服务（IaaS）的解决方案，每个服务提供API以进行集成。
OpenStack是一个旨在为公共及私有云的建设与管理提供软件的开源项目。它的社区拥有超过130家企业及1350位开发者，这些机构与个人都将OpenStack作为基础设施即服务（IaaS）资源的通用前端。OpenStack项目的首要任务是简化云的部署过程并为其带来良好的可扩展性。
### 2.OpenStack运用范围
    OpenStack是IaaS(基础设施即服务)组件，让任何人都可以自行建立和提供云端运算服务。
此外，OpenStack也用作建立防火墙内的“私有云”（Private Cloud），提供机构或企业内各部门共享资源。

### 3.Openstack架构

![avatar](https://images2015.cnblogs.com/blog/907596/201608/907596-20160826100016851-888912023.png)
 
### 4.OpenStack各个服务名称对应
![avatar](https://images2015.cnblogs.com/blog/907596/201608/907596-20160826100103179-894439631.png)
![avatar](https://images2015.cnblogs.com/blog/907596/201608/907596-20160826100118507-1580173894.png)
### 5.运行所需组件
#### 1)centOS 7.2系统 2台 
node1 即作为控制节点，也作为计算节点；
node2 就只是计算节点
控制节点去操控计算节点，计算节点上可以创建虚拟机
#### 2)域名解析和关闭防火墙
#### 3)安装配置 OpenStack
#### 官方文档 http://docs.openstack.org/
#### 学习参考博客 https://www.cnblogs.com/kevingrace/p/5707003.html

## 部署OpenStack
根据群文件的视频成功部署openstack
ip地址：
![ip.png](https://i.loli.net/2019/04/16/5cb544b7e87cd.png)
dashboard界面：
![task1部署.png](https://i.loli.net/2019/04/16/5cb544b804d0e.png)
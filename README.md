# dpp-backend

[![Typing SVG](https://readme-typing-svg.herokuapp.com?font=Fira+Code&pause=1000&color=000000&vCenter=true&repeat=false&width=435&lines=The+five+boxing+wizards+jump+quickly)](https://git.io/typing-svg)
![GitHub License](https://img.shields.io/github/license/solanej/dpp-backend)
![GitHub last commit](https://img.shields.io/github/last-commit/solanej/dpp-backend)
![GitHub commit activity](https://img.shields.io/github/commit-activity/w/solanej/dpp-backend)

---

## 项目结构及主要模块

```text
├── dpp-common      通用模块
|   ├── src/
|       ├── utils/
|           ├── HashUtil.java       哈希工具类
|           ├── TokenUtil.java      Token工具类

├── dpp-gateway     网关模块[8080]
|   ├── src/
|       ├── config/
|           ├── GatewayConfiguration.java       网关功能配置类
|           ├── LoadBalancerConfiguration.java  负载均衡功能配置类

├── dpp-service     业务模块
|   ├── dpp-auth        认证模块[9006]
|   ├── dpp-order       订单模块[9007]
|   ├── dpp-system      系统模块[9008]

```

## 运行环境及技术栈

| 软件       | 版本                     | 备注        |
|----------|------------------------|-----------|
| JDK      | Amazon Corretto 21.0.6 | Java开发工具包 |
| MySQL    | 8.0.41                 | 关系型数据库    |
| Redis    | 7.0.15                 | 内存数据库     |
| RabbitMQ | 3.12.1                 | 消息队列      |
| Nacos    | 2.5.1                  | 服务注册与发现   |
| MinIO    | 20250408               | 对象存储服务    |        

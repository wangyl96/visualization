# 进入项目目录
cd vue-admin-beautiful
# 安装依赖
npm install
# 本地开发 启动项目
npm run serve

frontend
|
├──src 
|  |
|  ├──api --接口地址配置
|     |
|     ├──visualization --数据可视化api
|     |
|     ├──platform-common-aspect --aop模块
|     |
|     ├──platform-common-api --统一API返参模块
|     |
|     ├──platform-common-util --工具类模块
|
├──platform-components --中台组件模块
|  |
|  ├──platform-components-api --接口
|  |
|  ├──platform-components-blueking --蓝鲸相关功能
|  |
|  ├──platform-components-push --推送相关功能
|  |
|  ├──platform-components-tce --腾讯tce产品相关功能
|  |
|  ├──platform-components-consumer --服务消费者
|
├──platform-demo --demo模块
|  |
|  ├──platform-dubbo-api --接口
|  |
|  ├──platform-dubbo-consumer --dubbo消费者
|  |
|  ├──platform-dubbo-provider --dubbo服务提供者
|  |
|  ├──platform-service-consumer --restTemplate消费者
|  |
|  ├──platform-service-provider --restTemplate服务提供者
|
├──platform-logplatform --ELK功能扩展模块
|  |
|  ├──platform-logplatform-api --接口
|  |
|  ├──platform-logplatform-checker --组件监控检查相关功能
|  |
|  ├──platform-logplatform-downloader --日志下载平台相关功能
|  |
|  ├──platform-logplatform-groker --日志规则校验相关功能
|  |
|  ├──platform-logplatform-consumer --服务消费者
|
├──platform-nacos-server --nacos注册中心
|
├──platform-tool --工具
|  |
|  ├──platform-monitor --监控中心
|
├──platform-web --前端web
            
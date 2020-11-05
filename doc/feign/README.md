
#open feign / github地址: https://github.com/spring-cloud/spring-cloud-openfeign

# 是什么
Feign是一个声明式的Web服务客户端, 让编写Web服务客户端变得非常容易,只需创建一个接口并在接口上添加相应注解即可

# 能干什么
Feign皆在使编写java http客户端更加简单
类似于Ribbon + RestTemplate(负载+服务调用)

#Feign 与 OpenFeign
1.Feign是SpringCloud组件中的一个轻量级RESTful的http服务客户端,内置Ribbon,用来做客户端负载均衡,Feign的使用方式是:
使用Feign的注解定义接口,调用这个接口,就可以调用服务注册中心的服务
2.OpenFeign是SpringCloud在Feign的基础上支持了SpringMVC的注解
### 框架选型
SpringBoot + Mybatis
### 目录结构
- resource
    - mapper mybatis的mapper文件
    - application.properties SpringBoot配置文件
    - logback-boot.xml 日志配置使用logback
    - mybatis-config mybatis配置
    - ehcache.xml 缓存配置
- java/com/pas/edu
    - aop
    - api 接口包
    - config 配置文件
    - dao
    - entity  实体类
    - exception 异常类
    - service 业务层
    - utils 工具包
    - web Web相关类（拦截器等）
    - ChildrenSunshineApplication.java   SpringBoot启动类

### 其他技术框架
##### 缓存
- ehcache

##### json序列化
- fastJson

##### 接口测试
- Swagger2
- 测试地址 /swagger-ui.html

##### 分页插件
- PageHelper
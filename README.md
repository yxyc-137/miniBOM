## miniBOM后台设计

### 1.环境配置

Maven：3.8.1
Jdk：1.8.0_144
IDE : IntelliJ IDEA

### 2.后端架构

后端的整体架构大约分为3层：Feign、Service和Controller层
(1)Feign接口：使用Feign封装iDME的全量数据服务API
(2)Service层：利用数据接口实现业务逻辑需求，使其可以被Controller层所调用
(3)Controller层：响应前端的请求，并返回所需的数据

### 3.系统运行

(1)启动后端服务
Run MiniBomApplication
(2)启动前端服务
npm install
npm run serve
(3)访问http://localhost:81/#/login即可注册登录进入系统
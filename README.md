# bookstore电商书城系统说明  
- ## 依赖环境jdk1.8,maven,mysql(创建bookstore数据库后，运行resource下的sql脚本，记得改数据库密码,登录的账号和密码自行查看user表)
- ## 后端是springboot+mybatis+shiro，前端界面使用bootstrap框架搭建  
- ## 该系统分为前台展示和后台管理两大模块。  
  - 前台主要是为消费者服务。该子系统实现了注册，登录，以及从浏览、下单到支付的整个流程，支付使用的是支付宝的沙箱环境，属于模拟环境。  
  - 后台主要是为了商家服务，商实现了权限管理，店铺、商品和订单等的管理，以及生成一些简单的报表信息。  
- ## 运行项目
  - 方法一：在ide(推荐idea)运行项目,配置好启动环境，直接运行main方法
  - 方法二: 在项目根目录下,运行以下maven命令  
    ```mvn spring-boot:run```
  - 方法三: 在ide或直接用maven打成的war包放到tomcat运行,具体操作可以百度，google
  - 方法四: 使用命令运行jar或war  
    ```java -jar xxx.jar```
  - 具体可以自行搜索spring-boot项目的启动方式

# bookstore电商书城系统说明
## 目的与缺陷
  - 目的: 在校学习，进一步熟悉Spring Boot开发模式，熟悉开发流程。
  - 缺陷: 购物车和订单不能分店铺统计和付款；前端界面较为简陋，许多地方的用户体验性也不好,后台API并没有符合restful风格等等。总的来说，功能较为简单，**许多功能还没有完成(如物流，第三方登录等)**。当然，已经完成的功能中或多或少会存在bug(没有很好的判断输入域的边界值)。
## 系统划分与功能
  - 该系统分为前台展示和后台管理两大模块。  
  - 前台主要是为消费者服务。该子系统实现了注册，登录，以及从浏览、下单到支付的整个流程，支付使用的是支付宝的沙箱环境，属于模拟环境。需要注册沙箱账号才能付款(可用支付账号:ynkltg9762@sandbox.com,密码:111111)。  
  - 后台主要是为商家服务，实现了权限，店铺，商品和订单等的管理，以及生成一些简单的报表信息。访问`/admin`进入后台    
## 依赖环境
  - jdk1.8,maven,mysql
  - 注意事项
    - 在数据库中创建名为`bookstore`数据库,然后运行项目的`resource`目录下的sql脚本，记得在`application.properties`改数据库配置信息
    - 登录系统的账号和密码，请自行查看数据库下的`user`表 (管理员账号：admin 密码：123)
    - `application.properties`中的邮箱配置要改成自己，否则不能注册系统账号
    -  使用沙箱环境的支付宝才能扫码支付，`application.properties`中的my.ip要改成自己，否则支付成功回调出错
## 使用框架
  - 后台主要是springboot+mybatis+shiro...，前端界面主要使用bootstrap框架搭建，并使用了ueditor富文本编辑器、highcharts图表库  
## 运行项目
  - 方法一：在ide(推荐idea)运行项目,配置好启动环境，先去掉继承的类SpringBootServletInitializer和方法，然后运行main方法
  - 方法二：在项目的根目录下执行maven命令  
    ``` mvn spring-boot:run```
  - 方法三: 在ide或直接用maven打成的war包放到tomcat运行,此时如果访问需要加上项目名的话，支付宝回调地址需要加上项目名称  
    ```mvn package -Dmaven.test.skip=true```
  - 方法四: 使用命令运行jar或war，因为也是一个传统JavaWeb项目，打成jar会无法访问静态资源，所以只能打成war,把WEN-INF下面的东西也打包好)  
    ```java -jar xxx.war```
  - 具体可以自行百度搜索`Spring Boot`项目的启动方式
## 云端访问地址
  - 请使用主流浏览器(Chrome,Firefox...)打开，ie可能样式会乱。
  - 首页 ```http://47.97.205.3:8080```
  - 后台管理 ```http://47.97.205.3:8080/admin```

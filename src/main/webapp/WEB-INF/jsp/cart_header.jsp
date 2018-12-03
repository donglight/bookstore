<%--
  Created by IntelliJ IDEA.
  User: ANS
  Date: 2018/11/7
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; %>
<html>
<head>
    <title>购物车头部</title>
</head>
<body>

<jsp:include page="top.jsp"/>
<div class="container">
    <!--
        购物车头部
    -->
    <div class="row" style="padding-left: 100px;">
        <img src="img/2018102616575937187.jpg" alt="广告"/>
    </div>
    <div class="row cartheader">
        <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12 img_div">
            <a href="index.html" target="_blank"><img src="img/dd.jpg" width="90%" alt="dd.com"/></a>
        </div>
        <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12 cart_text_div">
            <span class="cart_text_span">购物车</span>
        </div>
        <div class="col-lg-8 col-md-12 col-sm-12 col-xs-12 process">
            <span class="cart_text" id="this_process">我的购物车</span>
            <span class="cart_text">填写订单</span>
            <span class="cart_text">完成订单</span>
        </div>
    </div>
    <div class="clear"></div>

</div>
<div style="height: 3px; background-color: #ff2832;"></div>
</body>
</html>

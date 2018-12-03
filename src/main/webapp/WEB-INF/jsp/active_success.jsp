<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="<%=basePath%>">
    <title>注册成功</title>
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bs.css"/>
    <link rel="stylesheet" href="css/addcart.css"/>

</head>
<body>
<jsp:include page="top.jsp"/>
<div class="container" style="border: 4px solid #f5f5f5;">
    <div class="tip_div">
        <div id="success">
            <span class="success_text">激活成功！</span>
        </div>
        <div class="things">
            <p>
                <span>您好，${username}，恭喜您激活成功！</span>
            </p>
        </div>
    </div>
    <div class="next_div">
        <a href="page/login" class="toCart">前往登录</a>
        <a href="" class="toShopping">返回首页</a>
    </div>
</div>
</body>
</html>
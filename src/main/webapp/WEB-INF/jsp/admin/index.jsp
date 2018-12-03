<%--suppress ALL --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>dd书城后台管理系统</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="css/bs.css"/>
</head>
<body style="height: 1000px">
<iframe src="admin/home" style="width: 100%;height: 100%;margin: 0;padding: 0"></iframe>
</body>
</html>

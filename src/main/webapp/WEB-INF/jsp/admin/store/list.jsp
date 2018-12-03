<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>店铺列表</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bs.css"/>
</head>
<script type="text/javascript">
    
    function editStore(storeId) {
        location.href = "<%=basePath%>admin/store/"+storeId;
    }

    function deleteStore(storeId) {
        if(confirm("真的要删除？")){
            location.href = "<%=basePath%>admin/store/deletion/"+storeId;
        }
    }
    
</script>
<body>
<div class="container" style="border: 1px solid #CCCCCC;height: 1000px;">
    <div style="height:100px;border-bottom: 1px solid #CCCCCC;padding-top:30px;margin-bottom: 10px">
        <h2 class="h2">店铺列表</h2>
    </div>

    <table class="table table-hover">
        <caption>店铺列表</caption>
        <thead>
        <tr>
            <th>序号</th>
            <th>店主</th>
            <th>商店名称</th>
            <th>手机号码</th>
            <th>固定电话</th>
            <th>地址</th>
            <th>创建时间</th>
            <th>更新时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${stores}" var="store" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${store.storeManagerName}</td>
                <td>${store.storeName}</td>
                <td>${store.storePhoneNum}</td>
                <td>${store.storeTelephone}</td>
                <td>${store.storePosition}</td>
                <td><fmt:formatDate value="${store.created}" pattern="yyyy-MM-dd" /></td>
                <td><fmt:formatDate value="${store.updated}" pattern="yyyy-MM-dd" /></td>
                <td>
                    <button type="button" class="btn btn-xs btn-info" onclick="editStore(${store.storeId})">
                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                    </button>
                    <button type="button" class="btn btn-xs btn-danger" onclick="deleteStore(${store.storeId})">
                        <span class='glyphicon glyphicon-remove'></span> 删除
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

</body>
</html>

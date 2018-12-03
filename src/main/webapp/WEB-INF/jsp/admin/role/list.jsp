<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色列表</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bs.css"/>
</head>

<script type="text/javascript">
    function toEditRole(roleId) {
        location.href = "<%=basePath%>admin/role/echo/"+roleId;
    }
    function deleteRole(roleId) {
        if(confirm("确认删除该角色吗?")){
            location.href = "<%=basePath%>admin/role/deletion/"+roleId;
        }
    }
    function toEditPrivilege(roleId) {
        location.href = "<%=basePath%>admin/privilege/toEdit/"+roleId;
    }
</script>
<body>
<div class="container" style="border: 1px solid #CCCCCC;height: 1000px;">
    <div style="height: 100px;border-bottom: 1px solid #CCCCCC;"></div>

    <table class="table table-hover">
        <caption>角色列表</caption>
        <thead>
        <tr>
            <th>序号</th>
            <th>角色名称</th>
            <th>角色代码</th>
            <th>角色描述</th>
            <th>创建时间</th>
            <th>更新时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${allRoles}" var="role" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${role.name}</td>
                <td>${role.code}</td>
                <td>${role.description}</td>
                <td><fmt:formatDate value="${role.created}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${role.updated}" pattern="yyyy-MM-dd"/></td>
                <td>
                    <button type="button" class="btn btn-xs btn-default" onclick="toEditPrivilege(${role.roleId})">
                        <span class='glyphicon glyphicon-wrench'></span> 权限
                    </button>
                    <button type="button" class="btn btn-xs btn-info" onclick="toEditRole(${role.roleId})">
                        <span class="glyphicon glyphicon-pencil"></span> 编辑
                    </button>
                    <button type="button" class="btn btn-xs btn-danger" onclick="deleteRole(${role.roleId})">
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

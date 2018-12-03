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
    <title>设置权限</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery.ztree.all.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bs.css"/>
    <link rel="stylesheet" href="css/zTreeStyle/demo.css" type="text/css">
    <link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript">
        $(function () {
            var setting = {
                check: {
                    enable: true
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                async: {
                    enable: true,
                    url: "admin/privilege/rolePrivileges/${roleId}",
                    dataType : "json"
                }
            };

            $.fn.zTree.init($("#privilegeTree"), setting);

        });

        function setRolePrivileges() {
            var privilegeTree = $.fn.zTree.getZTreeObj("privilegeTree");
            var checkedNodes = privilegeTree.getCheckedNodes(true);
            var array = new Array();
            for (var i = 0; i < checkedNodes.length; i++) {
                array.push(checkedNodes[i].id);
            }
            var ids = array.join(",");
            $.ajax({
                type : "POST",
                url : "admin/role/updatePrivilege",
                data : {ids : ids,roleId : ${roleId}},
                success : function(result){
                    if(result.code == 200){
                        $(".red").html("保存成功");
                    }else{
                        $(".red").html("保存失败");
                    }
                },
                dataType : "json"
            });
        }
    </script>
</head>
<body>
<div class="container" style="border: 1px solid #CCCCCC;height: 1000px;">
    <div style="height:100px;border-bottom: 1px solid #CCCCCC;padding-top:30px;margin-bottom: 20px">
        <h2 class="h2">权限设置</h2>
    </div>
    <div class="content_wrap">
        <div class="zTreeDemoBackground left">
            <ul id="privilegeTree" class="ztree"></ul>
        </div>
    </div>
    <div class="row">
        <button type="button" onclick="setRolePrivileges()" class="btn btn-lg btn-default">
            <span class='glyphicon glyphicon-cog'></span> 保存设置
        </button>
        <span class="red"></span>
        <br>
        <a href="admin/role/list" target="centerFrame">返回角色列表</a>
    </div>
</div>
</body>
</html>

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
    <title>添加角色</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery.validate.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bs.css"/>
    <script type="text/javascript">

        $(function () {

            $("#storeForm").validate({
                //一失去焦点就校验
                onfocusout: function (element) {
                    $(element).valid();
                },
                errorPlacement: function (error, element) {	//错误信息位置设置方法
                    error.appendTo(element.parent().next());//这里的error是生成的错误对象，element是录入数据的对象,parent父元素，next()下一个
                },
                errorClass: "red",
                rules: {
                    name: {
                        required: true
                    },
                    code: {
                        required: true
                    },
                    description: {
                        required: true
                    }
                },
                messages: {
                    name: {
                        required: "请填写角色名"
                    },
                    code: {
                        required: "请填写角色代码"
                    },
                    description: {
                        required: "请填写角色说明"
                    }
                }
            });
        });

    </script>
</head>
<body>
<div class="container" style="border: 1px solid #CCCCCC;height: 1000px;">
    <div style="height:100px;border-bottom: 1px solid #CCCCCC;padding-top:30px;margin-bottom: 10px">
        <h2 class="h2">添加角色</h2>
    </div>
    <form class="form-horizontal" role="form" id="storeForm" method="post" action="admin/role/addition">

        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">角色名称：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="name" name="name" placeholder="角色名称">
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="code" class="col-sm-2 control-label">角色代码：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="code" name="code" placeholder="角色代码">
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="description" class="col-sm-2 control-label">角色描述：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="description" name="description" placeholder="角色描述">
            </div>
            <span></span>
        </div>


        <div class="form-group">
            <div class="col-sm-offset-2">
                <button type="submit" class="btn btn-lg btn-default" style="margin-left: 15px">
                    <span class='glyphicon glyphicon-plus'></span> 添加角色
                </button>
            </div>
        </div>
    </form>
</div>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色编辑</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script type="text/javascript" src="js/jquery.validate.js"></script>
    <link rel="stylesheet" href="css/bs.css"/>
    <script type="text/javascript">

        $(function () {
            jQuery.validator.addMethod("isMobile", function(value, element) {
                var length = value.length;
                var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
                return this.optional(element) || (length == 11 && mobile.test(value));
            }, "请正确填写手机号码");

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
    <div style="height:100px;border-bottom: 1px solid #CCCCCC;padding-top:30px;margin-bottom: 20px">
        <h2 class="h2">编辑店铺信息</h2>
    </div>
    <form class="form-horizontal" role="form" id="storeForm" method="post" action="admin/role/edit">
        <input type="hidden" name="roleId" value="${role.roleId}">
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">角色名称：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="name" name="name" value="${role.name}" placeholder="角色名称">
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="code" class="col-sm-2 control-label">角色代码：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="code" name="code" value="${role.code}" placeholder="角色代码">
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="description" class="col-sm-2 control-label">角色描述：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="description" name="description" value="${role.description}" placeholder="角色描述">
            </div>
            <span></span>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-3">
                <button type="submit" class="btn btn-lg btn-default">
                    保存修改
                </button>
                <span class="red">${saveMsg}</span>
            </div>
        </div>
        <a href="admin/role/list" target="centerFrame">返回角色列表</a>
    </form>
</div>
</body>
</html>

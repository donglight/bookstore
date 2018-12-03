<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script type="text/javascript" src="js/jquery.validate.js"></script>
    <link rel="stylesheet" href="css/bs.css"/>
    <script type="text/javascript">

        $(function () {

            $("#roleIds").focus();
            jQuery.validator.addMethod("isMobile", function (value, element) {
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
                    username: {
                        required: true
                    },
                    nickname: {
                        required: true
                    },
                    gender: {
                        required: true
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    phone: {
                        required: true,
                        isMobile: true
                    },
                    location: {
                        required: true
                    },
                    identity: {
                        required: true
                    },
                    detailAddress: {
                        required: true
                    }
                },
                messages: {
                    username: {
                        required: "请填写用户名"
                    },
                    nickname: {
                        required: "请填写昵称"
                    },
                    gender: {
                        required: "请选择您的性别"
                    },
                    email: {
                        required: "请填写邮箱地址",
                        email: "请正确填写邮箱"
                    },
                    phone: {
                        required: "请填写您的手机号码"
                    },
                    location: {
                        required: "请选择您所在的城市"
                    },
                    identity: {
                        required: "请选择您的身份"
                    },
                    detailAddress: {
                        required: "请填写您的详细地址"
                    }
                }
            });
        });

    </script>
</head>
<body>
<div class="container" style="border: 1px solid #CCCCCC;height: 1000px;">
    <div style="height:100px;border-bottom: 1px solid #CCCCCC;padding-top:30px;margin-bottom: 20px">
        <h2 class="h2">用户编辑</h2>
    </div>
    <form class="form-horizontal" role="form" id="storeForm" method="post" action="admin/user/update">
        <input type="hidden" name="userId" value="${user.userId}">
        <input type="hidden" name="username" value="${user.username}">
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">用户名：</label>
            <div class="col-sm-5">
                <span id="username">${user.username}</span>
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-2 control-label">更改密码：</label>
            <div class="col-sm-5">
                <input type="password" class="form-control" id="password" name="password" placeholder="密码(需要修改才填)">
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="nickname" class="col-sm-2 control-label">昵称：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname}"
                       placeholder="昵称">
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">性别:</label>
            <div class="col-sm-5">
                <label class="radio-inline">
                    <input type="radio" name="gender" id="man"
                           value="1" ${user.gender == "1"? "checked":""} > 男
                </label>
                <label class="radio-inline">
                    <input type="radio" name="gender" id="woman"
                           value="0" ${user.gender == "0"? "checked":""} > 女
                </label>
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-2 control-label">邮箱地址：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="email" name="email" value="${user.email}"
                       placeholder="邮箱地址">
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-2 control-label">手机号码：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="phone" name="phone" value="${user.phone}"
                       placeholder="手机号码">
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="location" class="col-sm-2 control-label">居住地:</label>
            <div class="col-sm-5">
                <select class="form-control" name="location" id="location" style="width: 150px;">
                    <option value="">请选择</option>
                    <option value="广州" ${user.location == "广州"? "selected":""} >广州</option>
                    <option value="北京" ${user.location == "北京"? "selected":""}>北京</option>
                    <option value="上海" ${user.location == "上海"? "selected":""}>上海</option>
                    <option value="深圳" ${user.location == "深圳"? "selected":""}>深圳</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label">身份:</label>
            <div class="col-sm-5">
                <input type="radio" name="identity" id="student"
                       value="学生" ${user.identity == "学生"? "checked":""}> 学生&nbsp&nbsp
                <input type="radio" name="identity" id="teacher"
                       value="教师" ${user.identity == "教师"? "checked":""}> 教师&nbsp&nbsp
                <input type="radio" name="identity" id="worker"
                       value="上班族" ${user.identity == "上班族"? "checked":""}> 上班族&nbsp&nbsp
                <input type="radio" name="identity" id="liberal"
                       value="自由职业" ${user.identity == "自由职业"? "checked":""}> 自由职业&nbsp&nbsp
                <input type="radio" name="identity" id="administrators"
                       value="管理员" ${user.identity == "管理员"? "checked":""}> 系统管理员&nbsp&nbsp
            </div>
            <span class="red"></span>
        </div>

        <div class="form-group">
            <label for="detailAddress" class="col-sm-2 control-label">详细住址:</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" name="detailAddress" id="detailAddress"
                       value="${user.detailAddress }" placeholder="详细住址:">
            </div>
            <span class="red"></span>
        </div>

        <shiro:hasPermission name="role-edit">
        <div class="form-group">
            <label for="location" class="col-sm-2 control-label">角色:</label>
            <div class="col-sm-3">
                <select class="form-control" name="roleIds" id="roleIds" multiple="multiple" style="width: 150px;">
                    <c:forEach items="${allRoles}" var="role">
                        <option value="${role.roleId}"
                                <c:forEach items="${userRoles}" var="userRole">
                                    ${userRole.roleId == role.roleId ? "selected":""}
                                </c:forEach>
                        >${role.name}</option>
                    </c:forEach>
                </select>
                <span>按住ctrl或shift键多选</span>
            </div>
            <span class="red"></span>
        </div>
        </shiro:hasPermission>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">保存基本信息</button>
                <span class="red">${saveMsg}</span>
            </div>
        </div>

    </form>
</div>
</body>
</html>

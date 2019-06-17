<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <base href="<%=basePath%>">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="css/register.css"/>
    <link rel="stylesheet" type="text/css" href="css/bs.css"/>
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/jquery.validate.js"></script>
    <script type="text/javascript" src="js/messages_zh.js"></script>

    <script type="text/javascript">
        /**
         * ajax校验用户名是否可以使用
         * 可以返回true
         * 否则返回false
         * @returns {boolean}
         */
        function checkUserExist(value, element) {
            var $username = $(element);
            var isExist = false;
            var trimVal = $.trim(value);
            if (trimVal.length >= 3 && trimVal.length <=15) {
                $.ajax({
                    type: "GET",
                    url: "user/checkUserExist",
                    data: {"username": value},
                    success: function (result) {
                        var $next = $username.parent().next();
                        $next.html("");
                        if (result.data) {
                            isExist = true;
                            $next.html("<span class='ok'>" + result.message + "</span>");
                        }
                        isExist = result.data;
                    },
                    complete: function () {
                    },
                    dataType: "json",
                    async: false
                });
            }
            return isExist;
        }

        $(function () {
            jQuery.validator.addMethod("checkUserExist", function (value, element, param) {
                if (param) {
                    return checkUserExist(value, element);
                } else
                    return false;
            }, "用户名已被注册");


            $("#regForm").validate({
                //一失去焦点就校验
                onfocusout: function (element) {
                    $(element).valid();
                },
                errorPlacement: function (error, element) {	//错误信息位置设置方法
                    var $next = element.parent().next();
                    $next.html("").focus();
                    error.appendTo($next);//这里的error是生成的错误对象，element是录入数据的对象,parent父元素，next()下一个
                },
                errorClass: "error",
                rules: {
                    username: {
                        required: true,
                        rangelength: [3, 15],
                        checkUserExist: true
                    },
                    password: {
                        required: true,
                        rangelength: [3, 15]
                    },
                    repassword: {
                        equalTo: "[name='password']",
                        required: true
                    },
                    email: {
                        required: true,
                        email: true
                    },
                    identity: {
                        required: true
                    },
                    agree: {
                        required: true
                    }
                },
                messages: {
                    username: {
                        required: "用户名不能为空",
                        rangelength: "用户名长度在{0}~{1}之间"
                    },
                    password: {
                        required: "密码不能为空",
                        rangelength: "密码长度在{0}~{1}之间"
                    },
                    repassword: {
                        equalTo: "两次输入密码不一致",
                        required: "确认密码"
                    },
                    email: {
                        required: "请输入邮箱地址",
                        email: "请输入一个正确的邮箱"
                    },
                    identity: {
                        required: "请选择"
                    },
                    agree: {
                        required: "请同意条款"
                    }
                }
            });
        })


    </script>

</head>
<body>
<jsp:include page="loginRegister_above.jsp"/>
<div style="height: 3px; background-color: #ff2832;"></div>
<div id="center" style="background-color:#ebeeef;">
    <form action="user/register" id="regForm" method="post">
        <div id="table">
            <div class="register">
                <span>新用户注册/企业用户注册</span>
            </div>
            <table id="register_table" style="margin-left:310px">
                <tr>
                    <td class="td">
                        用户名:
                    </td>
                    <td>
                        <input type="text" name="username" class="text" />
                    </td>
                    <td>
                        <span class="error">${registerError}</span>
                    </td>
                </tr>
                <tr>
                    <td class="td">
                        登录密码:
                    </td>
                    <td>
                        <input class="text" type="password" name="password" class="text"/>
                    </td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td class="td">
                        确认密码:
                    </td>
                    <td>
                        <input class="text" type="password" name="repassword" class="text"/>
                    </td>
                    <td>
                    </td>
                </tr>

                <tr>
                    <td class="td">
                        邮箱地址:
                    </td>
                    <td>
                        <input type="text" name="email" class="text" />
                    </td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td class="td">
                    </td>
                    <td>
                        <span style="font-size: 15px;">我是普通用户</span> <input type="radio" name="identity"
                                                                            value="ordinary"/>
                        <br/>
                        <span style="font-size: 15px;">我是企业用户/商家</span> <input type="radio" name="identity"
                                                                               value="business"/>
                    </td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="checkbox" name="agree" value="agree"/>
                        <span><a href="">我已同意服务条款</a></span>
                    </td>
                    <td>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <span class="a"><a href="page/login" target="_blank">立即登录</a></span>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" id="regbutton" value="注册"/>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
</body>
</html>

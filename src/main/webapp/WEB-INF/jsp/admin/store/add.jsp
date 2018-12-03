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
    <title>添加店铺</title>
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
                    storeManagerId: {
                        required: true
                    },
                    storeName: {
                        required: true
                    },
                    storePhoneNum: {
                        required: true,
                        isMobile:true
                    },
                    storeTelephone: {
                        number:true
                    },
                    storePosition: {
                        required: true
                    }
                },
                messages: {
                    storeManagerId: {
                        required: "请选择店主"
                    },
                    storeName: {
                        required: "请给店铺取个名"
                    },
                    storePhoneNum: {
                        required: "请输入手机号码"
                    },
                    storeTelephone: {
                        number:"请输入正确的号码"
                    },
                    storePosition: {
                        required: "请输入地址"
                    }
                }
            });
        });

    </script>
</head>
<body>
<div class="container" style="border: 1px solid #CCCCCC;height: 1000px;">
    <div style="height:100px;border-bottom: 1px solid #CCCCCC;padding-top:30px;margin-bottom: 10px">
        <h2 class="h2">添加店铺</h2>
    </div>
    <form class="form-horizontal" role="form" id="storeForm" method="post" action="admin/store/addition">

        <div class="form-group">
            <label for="storeManagerId" class="col-sm-2 control-label">店主：</label>
            <div class="col-sm-5">
                <select name="storeManagerId" id="storeManagerId" class="form-control" style="width: 100px">
                    <option value="">--请选择--</option>
                    <c:forEach items="${users}" var="user">
                        <option value="${user.userId}" >${user.username}</option>
                    </c:forEach>
                </select>
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="storeName" class="col-sm-2 control-label">商店名称：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="storeName" name="storeName" placeholder="商店名称">
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="storePhoneNum" class="col-sm-2 control-label">手机号码：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="storePhoneNum" name="storePhoneNum" placeholder="手机号码">
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="storeTelephone" class="col-sm-2 control-label">固定电话：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="storeTelephone" name="storeTelephone" placeholder="固定电话">
            </div>
            <span></span>
        </div>

        <div class="form-group">
            <label for="storePosition" class="col-sm-2 control-label">地址：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="storePosition" name="storePosition" placeholder="地址">
            </div>
            <span></span>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2">
                <button type="submit" class="btn btn-lg btn-default" style="margin-left: 15px">
                    <span class='glyphicon glyphicon-plus'></span> 添加商店
                </button>
            </div>
        </div>
    </form>
</div>
</body>
</html>

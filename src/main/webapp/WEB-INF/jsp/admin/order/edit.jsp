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

            jQuery.validator.addMethod("isMobile", function (value, element) {
                var length = value.length;
                var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(17[0-9]{9})|(15[0-9]{9})$/;
                return this.optional(element) || (length == 11 && mobile.test(value));
            }, "请正确填写手机号码");

            $("#orderForm").validate({
                //一失去焦点就校验
                onfocusout: function (element) {
                    $(element).valid();
                },
                errorPlacement: function (error, element) {	//错误信息位置设置方法
                    error.appendTo(element.parent().next());//这里的error是生成的错误对象，element是录入数据的对象,parent父元素，next()下一个
                },
                errorClass: "red",
                rules: {
                    shippingName: {
                        required: true
                    },
                    receiverAddress: {
                        required: true
                    },
                    receiverName: {
                        required: true
                    },
                    receiverMobile: {
                        required: true,
                        isMobile:true
                    }
                },
                messages: {
                    shippingName: {
                        required: "请选择快递"
                    },
                    receiverAddress: {
                        required: "请填写收获地址"
                    },
                    receiverName: {
                        required: "请填写收货人姓名"
                    },
                    receiverMobile: {
                        required: "请填写手机号码"
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
    <form class="form-horizontal" role="form" id="orderForm" method="post" action="admin/order/update">
        <input type="hidden" name="orderId" value="${orderCustom.order.orderId}">
        <div class="form-group">
            <label for="orderId" class="col-sm-2 control-label">订单号：</label>
            <div class="col-sm-5">
                <span id="orderId">${orderCustom.order.orderId}</span>
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="username" class="col-sm-2 control-label">买家用户名：</label>
            <div class="col-sm-5">
                <span id="username">${buyer.username}</span>
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="payment" class="col-sm-2 control-label">商品总价：</label>
            <div class="col-sm-5">
                <span id="payment" class="red">￥${orderCustom.order.payment}</span>
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label">支付类型</label>
            <div class="col-sm-5">
                <c:if test="${orderCustom.order.status == 0}">
                    <span>未付款</span>
                </c:if>

                <c:if test="${orderCustom.order.status != 0}">
                    <c:if test="${orderCustom.order.paymentType == 1}">
                        <span>微信</span>
                    </c:if>
                    <c:if test="${orderCustom.order.paymentType == 2}">
                        <span>支付宝</span>
                    </c:if>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label for="postFee" class="col-sm-2 control-label">邮费：</label>
            <div class="col-sm-5">
                <span id="postFee">${orderCustom.order.postFee}</span>
            </div>
            <span></span>
        </div>


        <div class="form-group">
            <label for="createTime" class="col-sm-2 control-label">下单时间：</label>
            <div class="col-sm-5">
                <span id="createTime"><fmt:formatDate value="${orderCustom.order.createTime}" pattern="yyyy-MM-dd"/></span>
            </div>
            <span></span>
        </div>

        <div class="form-group">
            <label for="shippingName" class="col-sm-2 control-label">快递：</label>
            <div class="col-sm-5">
                <select name="shippingName" id="shippingName">
                    <option value="圆通" ${orderCustom.order.shippingName eq "圆通"? "selected":""}>圆通</option>
                    <option value="中通" ${orderCustom.order.shippingName eq "中通"? "selected":""}>中通</option>
                    <option value="顺丰" ${orderCustom.order.shippingName eq "顺丰"? "selected":""}>顺丰</option>
                    <option value="申通" ${orderCustom.order.shippingName eq "申通"? "selected":""}>申通</option>
                </select>
            </div>
            <span></span>
        </div>


        <div class="form-group">
            <label for="receiverName" class="col-sm-2 control-label">收件人姓名：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" name="receiverName" id="receiverName" value="${orderCustom.orderShipping.receiverName}" placeholder="收件人姓名">
            </div>
            <span></span>
        </div>

        <div class="form-group">
            <label for="receiverMobile" class="col-sm-2 control-label">收件人手机号：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" name="receiverMobile" id="receiverMobile" value="${orderCustom.orderShipping.receiverMobile}" placeholder="收件人手机号">
            </div>
            <span></span>
        </div>

        <div class="form-group">
            <label for="receiverAddress" class="col-sm-2 control-label">收货地址：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" name="receiverAddress" id="receiverAddress" value="${orderCustom.orderShipping.receiverAddress}" placeholder="收货地址">
            </div>
            <span></span>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">保存订单信息</button>
                <span class="red">${saveMsg}</span>
            </div>
        </div>

    </form>
</div>
</body>
</html>

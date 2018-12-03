<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单结算</title>

    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bs.css"/>
    <link rel="stylesheet" href="css/order_info.css"/>

    <script type="text/javascript">
        $(function () {
            $("#payment a").click(function () {
                $("#payment a").removeClass();
                $(this).addClass("pay_active");
            });

            //提交提单POST请求
            $(function () {
                $("#checkout_btn").click(function () {
                    $("#orderForm").submit();
                });
            });
        });
    </script>
</head>
<body>
<jsp:include page="order_header.jsp"/>

<div class="container">


    <!--
        收货人信息
    -->
    <form id="orderForm" action="order/creation" class="form-horizontal" method="post">
        <div class="row">

            <h1 class="title order_title">收货人信息</h1>


            <div class="form-group">
                <label for="address" class="col-sm-1 control-label">地址</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="address" name="detailAddress"
                           value="${sessionScope.loginUser.detailAddress}">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-1 control-label">收货人</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="inputPassword3" name="username"
                           placeholder="请确认收货人" value="${sessionScope.loginUser.username}">
                </div>
            </div>
            <div class="form-group">
                <label for="confirmPassword" class="col-sm-1 control-label">联系方式</label>
                <div class="col-sm-5">
                    <input type="text" class="form-control" id="confirmPassword" name="phone"
                           placeholder="请输入联系方式" value="${sessionScope.loginUser.phone}">
                </div>
            </div>
        </div>

        <div class="row">
            <h1 class="title order_title">订单详情</h1>
        </div>

        <div class="row" id="order_table_div">
            <table id="order_table" border="0" cellpadding="0" cellspacing="0">
                <thead>
                <tr id="table_head">
                    <th width="20%" class="tcol1">商品信息</th>
                    <th width="10%">单价（元）</th>
                    <th width="10%">数量</th>
                    <th width="10%">总价（元）</th>
                </tr>
                </thead>

                <c:forEach items="${cart.cartItems}" var="cartItem">
                <c:if test="${cartItem.value.buyNum > 0 && cartItem.value.subTotal > 0}">

                <tbody>
                <tr class="order_item">
                    <td>
                        <a href="#"><img src="${cartItem.value.bookInfo.imageUrl}" width="20%"/></a>
                        <span>${cartItem.value.bookInfo.name}</span>
                    </td>
                    <td>
                        <span class="red">￥<fmt:formatNumber type="number" value="${cartItem.value.bookInfo.price}"
                                                             pattern="0.00"/></span>
                    </td>
                    <td>
                        <span>${cartItem.value.buyNum}</span>
                    </td>
                    <td>
                        <span class="red">￥<fmt:formatNumber type="number" value="${cartItem.value.subTotal}"
                                                             pattern="0.00"/></span>
                    </td>
                </tr>
                </c:if>
                </c:forEach>

                <tr class="tfoot">
                    <td class="tcol1">
                        <span>店铺合计	</span>
                    </td>
                    <td></td>
                    <td></td>
                    <td class="shop_total">￥<fmt:formatNumber type="number" value="${cart.total}" pattern="0.00"/></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div id="express">
            <p>
                <span>物流信息：</span>
                <span>
                    <select name="express">
                        <option value="圆通">圆通</option>
                        <option value="中通">中通</option>
                        <option value="顺丰">顺丰</option>
                        <option value="申通">申通</option>
                    </select>
                </span>
            </p>
        </div>
        <!--
            作者：offline
            时间：2018-10-28
            描述：支付方式
        -->
        <div class="row" id="payment">
            <h1 class="title order_title">支付方式</h1>
            <dl>
                <dt style="font-size: 16px;margin-top: 20px;">&nbsp;网上支付</dt>
                <%--<dd><a href="javascript:;" class="pay_active">支付宝</a></dd>
                <dd>&nbsp;&nbsp;&nbsp;<a href="javascript:;">微信</a></dd>--%>
                <select name="payMethod" style="width: 80px;height: 30px;font-size: 15px">
                    <option value="2">支付宝</option>
                    <option value="1">微信</option>
                </select>
            </dl>
        </div>

        <!--
            作者：offline
            时间：2018-10-28
            描述：去支付
        -->
        <div class="row" id="to_paid_div">
            <div id="to_paid_info">
                共<span class="red">2</span>件商品
                应付金额
                <span class="shop_total">￥<fmt:formatNumber type="number" value="${cart.total}" pattern="0.00"/></span>
                <a href="javascript:void(0);" class="total_btn" id="checkout_btn">结算订单</a>
            </div>
            <div id="return_cart">
                <a href="cart/items">返回购物车</a>
            </div>
        </div>
        <div class="clear"></div>
    </form>
</div>


</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单列表</title>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bs.css"/>
</head>
<script type="text/javascript">

    $(function (){
        $('[data-toggle="popover"]').each(function () {
            var element = $(this);
            var buyerId = element.attr('value');
            $.ajax({
                type:"GET",
                url:"admin/order/buyer/"+buyerId,
                success : function(result){
                    if(result.code == 200){
                        element.popover({
                            trigger: 'manual',
                            placement: 'right', //top, bottom, left or right
                            title: "买家:"+result.data.username,
                            html: 'true',
                            content: "来自:"+result.data.location
                        }).on("mouseenter", function () {
                            var _this = this;
                            $(this).popover("show");
                            $(this).siblings(".popover").on("mouseleave", function () {
                                $(_this).popover('hide');
                            });
                        }).on("mouseleave", function () {
                            var _this = this;
                            setTimeout(function () {
                                if (!$(".popover:hover").length) {
                                    $(_this).popover("hide")
                                }
                            }, 100);
                        });
                    }
                },
                dataType : "json"
            });

        });
    });

    function deleteOrder(orderId) {
        if (confirm("确认删除订单吗?")) {
            location.href = "<%=basePath%>admin/order/deletion/" + orderId;
        }
    }
    function lookShipping() {
        alert("暂无物流信息!");
    }

    function postOrder(orderId) {
        if (confirm("确认发货吗?")) {
            location.href = "<%=basePath%>admin/order/post/" + orderId;
        }
    }
    
    function toEditOrder(orderId) {
        location.href = "<%=basePath%>admin/order/toUpdate/" + orderId;
    }

</script>
<body>
<div class="container" style="border: 1px solid #CCCCCC;height: 1000px;">

    <div style="height:100px;border-bottom: 1px solid #CCCCCC;padding-top:30px;margin-bottom: 10px">
        <h2 class="h2">订单列表</h2>
    </div>

    <table class="table table-hover">
        <caption>订单列表</caption>
        <thead>
        <tr>
            <th>序号</th>
            <th>订单编号</th>
            <th>商品信息</th>
            <th>订单状态</th>
            <th>金额</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderCustoms}" var="orderCustom" varStatus="vs">
            <tr>
                <td>${vs.count}</td>
                <td>${orderCustom.order.orderId}</td>
                <td width="300px">
                    <c:forEach items="${orderCustom.orderDetails}" var="orderDetail">
                        <span>${orderDetail.mount}&nbsp;x</span>
                        <a href="book/info/${orderDetail.bookId}" title="${orderDetail.bookName}" target="_blank"><img src="${orderDetail.imageUrl}" width="20%"/></a>
                    </c:forEach>
                </td>
                <td>${orderCustom.order.statusString}</td>
                <td class="red">￥${orderCustom.order.payment}</td>
                <td>
                    <button type="button" class="btn btn-xs btn-info" onclick="toEditOrder('${orderCustom.order.orderId}')" >
                        查看订单
                    </button>
                    <c:if test="${orderCustom.order.status == 0}">
                        <button type="button" class="btn btn-xs btn-danger" onclick="deleteOrder('${orderCustom.order.orderId}')">
                            取消订单
                        </button>
                    </c:if>
                    <button type="button" id="buyerInfo" class="btn btn-xs btn-warning buyerInfo"
                            data-container="body" data-toggle="popover"

                            value="${orderCustom.order.userId}" <%--onclick="findBuyerInfo(${orderCustom.order.userId})"--%>>
                        买家信息
                    </button>
                    <c:if test="${orderCustom.order.status != 0}">
                        <c:if test="${orderCustom.order.status < 3}">
                            <button type="button" class="btn btn-xs btn-info" onclick="postOrder('${orderCustom.order.orderId}')" >
                                发货
                            </button>
                        </c:if>
                        <button type="button" class="btn btn-xs btn-info" onclick="lookShipping()" >
                            查看物流
                        </button>
                        <button type="button" class="btn btn-xs btn-danger" onclick="deleteOrder('${orderCustom.order.orderId}')">
                            删除订单
                        </button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

</body>
</html>

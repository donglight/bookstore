<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bs.css"/>
    <link rel="stylesheet" href="css/book_list.css"/>
    <title>book</title>
</head>
<body>

<jsp:include page="header.jsp"/>
<div class="container">
    <div class="search_tab">
        <ul>
            <li>全部商品</li>
        </ul>
    </div>
    <!--
        作者：offline
        时间：2018-10-28
        描述：crumbs面包屑div
    -->
    <div class="crumbs">
        <div>
            <a href="#">全部</a>
            <span>&gt;</span>
            <span class="search_word">${keywords}</span>
        </div>
        <span class="total_search_book_count">
					共<em class="red">${bookPageInfo.total}</em>本图书
				</span>
    </div>
    <div class="search_result">
        <div class="shoplist">
            <ul class="shoplist_ul">

                <c:forEach items="${bookPageInfo.list}" var="bookInfo">
                    <li>
                        <a href="book/info/${bookInfo.bookId}" target="_blank" title="${bookInfo.outline}">
                            <img src="${bookInfo.imageUrl}" alt="${bookInfo.outline}" width="200px" height="200px"/>
                        </a>
                        <p class="name">
                            <a href="book/info/${bookInfo.bookId}" title="${bookInfo.outline}" target="_blank">
                                    ${bookInfo.outline}
                            </a>
                        </p>
                        <p class="price">
                            <span class="search_now_price">￥ ${bookInfo.price}</span>
                            <span style="color: #C0C0C0;">定价：</span>
                            <span class="oprice">￥${bookInfo.marketPrice}</span>
                            <span class="search_discount">&nbsp;(${bookInfo.discount}折) </span>
                        </p>
                        <p class="search_book_author">
                            <span><a href="" title="程杰 著">${bookInfo.author}</a> 著</span>
                            <span> /${bookInfo.publishDate}</span>
                            <span>  /<a href="" title="${bookInfo.press}">${bookInfo.press}</a></span>
                        </p>
                        <p class="detail">
                                ${bookInfo.detail}
                        </p>
                        <div class="shop_button">
                            <p class="bottom_p">
                                <a class="search_btn_cart" href="cart/addition?bookId=${bookInfo.bookId}&buyNum=1">加入购物车</a>
                                <a class="search_btn_collect" href="javascript:void(0);">收藏</a>
                            </p>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <ul class="pagination pagination-lg">

            <%--
                上一页
            --%>
            <c:if test="${bookPageInfo.isFirstPage}">
                <li class="disabled"><a href="javascript:void(0);">前一页</a></li>
            </c:if>

            <c:if test="${!bookPageInfo.isFirstPage}">
                <li>
                    <a href="book/list?keywords=${keywords}&cateId=${cateId}&page=${bookPageInfo.prePage}">前一页</a>
                </li>
            </c:if>

            <%--<c:forEach
                    begin="${bookPageInfo.pageNum}"
                    end="${bookPageInfo.pageNum+5 < bookPageInfo.pages ? bookPageInfo.pageNum+5 : bookPageInfo.pages }"
                    var="current">
                <li
                        class="${(current == bookPageInfo.pageNum) ? 'active':''}">
                    <a href="book/list?keyword=${keywords}&cateId=${cateId}&page=${current}">
                        ${current}
                    </a>
                </li>
            </c:forEach>--%>
            <c:forEach
                    begin="${bookPageInfo.pageNum < 6 ? 1 :bookPageInfo.pageNum-5}"
                    end="${bookPageInfo.pages<6?bookPageInfo.pages:(bookPageInfo.pageNum < 6 ? 6 :bookPageInfo.pageNum) }"
                    var="current">
                <li
                        class="${(current == bookPageInfo.pageNum) ? 'active':''}">
                    <a href="book/list?keywords=${keywords}&cateId=${cateId}&page=${current}">
                            ${current}
                    </a>
                </li>
            </c:forEach>
            <%--
                下一页
            --%>
            <c:if test="${bookPageInfo.isLastPage}">
                <li class="disabled"><a href="javascript:void(0);">下一页</a></li>
            </c:if>

            <c:if test="${!bookPageInfo.isLastPage}">
                <li><a href="book/list?keywords=${keywords}&cateId=${cateId}&page=${bookPageInfo.nextPage}">下一页</a>
                </li>
            </c:if>

            <li class="disabled"><a href="javascript:void(0);">共${bookPageInfo.pages}页</a></li>
        </ul>
    </div>
</div>
</body>
</html>

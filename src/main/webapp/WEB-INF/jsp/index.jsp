<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${applicationScope.globalParameter.webName}</title>

    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <link rel="shortcut icon" href="img/java.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bs.css"/>

</head>

<body>
<jsp:include page="header.jsp"/>
<div class="container" style="margin-top:10px ;">
    <div class="row" style="height: 850px;">
        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12 sort" style="width: 17.5%;height: 100%;padding: 0;">
            <div id="sort_header">
                <span>图书分类</span>
            </div>
            <ul id="sort_ul">
                <li class="sort_li">
                    <a href="">特色书单</a>
                    <span class="sort_span">></span>
                </li>
                <c:forEach items="${bookCategories}" var="bookCat">
                    <li class="sort_li">
                        <a href="book/list?cateId=${bookCat.cateId}">${bookCat.name}</a>
                        <span class="sort_span">></span>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="col-lg-6 col-md-4 col-sm-6 col-xs-12" style="width: 65%;height: 100%;">
            <div id="myCarousel" class="carousel slide">
                <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0"
                        class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                    <li data-target="#myCarousel" data-slide-to="3"></li>
                    <li data-target="#myCarousel" data-slide-to="4"></li>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="img/lunbo1.jpg" alt="First slide">
                    </div>
                    <div class="item">
                        <img src="img/lunbo2.jpg" alt="Second slide">
                    </div>
                    <div class="item">
                        <img src="img/lunbo3.jpg" alt="Third slide">
                    </div>
                    <div class="item">
                        <img src="img/lunbo4.jpg" alt="Third slide">
                    </div>
                    <div class="item">
                        <img src="img/lunbo5.jpg" alt="Third slide">
                    </div>
                </div>
                <!-- 轮播（Carousel）导航 -->
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
            <div class="index_product_top">
                <span class="title">新书上架</span>
                <div class="div_hr"></div>
            </div>
            <div class="product_div">
                <ul class="product_ul">

                    <c:forEach items="${bookInfos}" begin="0" end="7" var="bookInfo">

                        <li class="product_li">
                            <a href="book/info/${bookInfo.bookId}" class="img" target="_blank">
                                <img src="${bookInfo.imageUrl}"/>
                            </a>
                            <p class="name">
                                <a href="book/info/${bookInfo.bookId}">${bookInfo.name}</a>
                            </p>
                            <p class="author">${bookInfo.author}</p>
                            <p class="price">
                                <span class="rob">￥${bookInfo.price}</span>
                                <span class="oprice">￥${bookInfo.marketPrice}</span>
                            </p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>

        <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12" style="width: 17.5%;height: 100%; padding: 0;">
            <div class="news">
                <p class="title">最新动态</p>
                <ul>
                    <li>要买好书，就来东东</li>
                    <li>今日特惠，5折抢购</li>
                    <li>满100减50</li>
                    <li>满300减150</li>
                    <li>满400减210</li>
                </ul>
            </div>
            <div class="hot_book">
                <p class="title">新书热卖</p>
                <ul>
                    <li>要买好书，就来东东</li>
                    <li>今日特惠，5折抢购</li>
                    <li>满100减50</li>
                    <li>满300减150</li>
                    <li>满400减210</li>
                </ul>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 30px;height: 500px;">
        <div class="col-lg-10 col-md-12 col-sm-12 col-xs-12" style="width: 82.5%;padding-left: 0;">
            <div class="index_product_top">
                <span class="title">好书推荐</span>
                <div class="div_hr"></div>
            </div>
            <div class="product_div">
                <ul class="product_ul">
                    <c:forEach items="${bookInfos}" begin="8" end="17" var="bookInfo">
                        <li class="product_li">
                            <a href="book/info/${bookInfo.bookId}" class="img" target="_blank">
                                <img src="${bookInfo.imageUrl}"/>
                            </a>
                            <p class="name">
                                <a href="book/info/${bookInfo.bookId}">${bookInfo.name}</a>
                            </p>
                            <p class="author">${bookInfo.author}</p>
                            <p class="price">
                                <span class="rob">￥${bookInfo.price}</span>
                                <span class="oprice">￥${bookInfo.marketPrice}</span>
                            </p>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="col-lg-2 col-md-12 col-sm-12 col-xs-12" style="width: 17.5%;padding: 0;padding-top:20px ;">
            <div class="hot_book">
                <p class="title">畅销图书</p>
                <ul>
                    <li>要买好书，就来东东</li>
                    <li>今日特惠，5折抢购</li>
                    <li>满100减50</li>
                    <li>满300减150</li>
                    <li>满400减210</li>
                </ul>
            </div>
        </div>
    </div>

</div>
</div>

<div style="height: 3px; background-color: #ff2832;"></div>

<jsp:include page="footer.jsp"/>
</body>

</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<body>
<div id="header">
    <div id="header_inner">
        <ul id="header_ul">
            <li class="header_li">
                <shiro:user>
                    <div class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle mybtn"
                                data-toggle="dropdown">
                            我的dd<span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="user/info">个人信息</a></li>
                            <shiro:hasPermission name="system">
                                <li><a href="admin">后台管理</a></li>
                            </shiro:hasPermission>
                            <li class="divider"></li>
                            <li><a href="user/logout">注销</a></li>

                        </ul>
                    </div>
                </shiro:user>
            </li>


            <li class="header_li">
                <a href="page/register">免费注册</a>
            </li>
            <li class="header_li">
                <c:if test="${sessionScope.loginUser == null}">
                    <a href="page/login">亲，请登录</a>
                </c:if>
                <c:if test="${sessionScope.loginUser != null } ">
                    <a href="user/info">${sessionScope.loginUser.username}</a>
                </c:if>
            </li>

            <li class="header_li">
                <c:if test="${sessionScope.loginUser != null }">
                    欢迎您，<a href="user/info">${sessionScope.loginUser.username}</a>
                </c:if>
            </li>

            <li class="header_li">
                欢迎来到dd书城，<a href="">首页</a>
            </li>

        </ul>
    </div>
</div>
</body>
</html>

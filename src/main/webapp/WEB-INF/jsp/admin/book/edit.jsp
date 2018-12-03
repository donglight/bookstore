<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; %>
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
            $("#bookForm").validate({
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
                    price: {
                        required: true,
                        number:true
                    },
                    marketPrice: {
                        required: true,
                        number:true
                    },
                    storeMount: {
                        required: true,
                        number: true
                    },
                    author: {
                        required: true
                    },
                    publishDate: {
                        required: true,
                        date:true
                    },
                    press: {
                        required: true
                    },
                    bookCategoryId: {
                        required: true
                    },
                    outline: {
                        required: true
                    },
                    bookDesc: {
                        required: true
                    }
                },
                messages: {
                    name: {
                        required: "请输入书名"
                    },
                    price: {
                        required: "请输入价格",
                        number: "必须是数字"
                    },
                    marketPrice: {
                        required: "请输入定价",
                        number: "必须是数字"
                    },
                    storeMount: {
                        required: "请输入定价",
                        number: "必须是数字"
                    },
                    author: {
                        required: "请输入作者"
                    },
                    publishDate: {
                        required: "请填写出版时间",
                        date:"时间格式不正确"
                    },
                    press: {
                        required: "请输入出版社"
                    },
                    bookCategoryId: {
                        required: "请选择类别"
                    },
                    outline: {
                        required: "请填写书籍概要"
                    },
                    bookDesc: {
                        required: "请添加书籍详情"
                    }
                }
            });
        });
        function changImg(e){
            for (var i = 0; i < e.target.files.length; i++) {
                var file = e.target.files.item(i);
                if (!(/^image\/.*$/i.test(file.type))) {
                    continue; //不是图片 就跳出这一次循环
                }
                //实例化FileReader API
                var freader = new FileReader();
                freader.readAsDataURL(file);
                freader.onload = function(e) {
                    $("#myImg").attr("src",e.target.result);
                }
            }
        }
    </script>
</head>
<body>
<div id="searchBook" style="height:50px;border-bottom: 1px solid #CCCCCC;margin-bottom: 10px"></div>
<div class="container" style="border: 1px solid #CCCCCC;">
    <form class="form-horizontal" role="form" id="bookForm" method="post" action="admin/book/update"
          enctype="multipart/form-data">
        <input type="hidden" name="storeId" value="${bookInfo.storeId}">
        <input type="hidden" name="bookId" value="${bookInfo.bookId}">
        <div class="form-group">
            <label for="name" class="col-sm-1 control-label">标题：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="name" name="name" value="${bookInfo.name}" placeholder="请输入书名或标题">
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="price" class="col-sm-1 control-label">价格：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="price" name="price" value="${bookInfo.price}" placeholder="请输入价格">
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="marketPrice" class="col-sm-1 control-label">定价：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="marketPrice" value="${bookInfo.marketPrice}" name="marketPrice" placeholder="请输入定价">
            </div>
            <span></span>
        </div>

        <div class="form-group">
            <label for="storeMount" class="col-sm-1 control-label">库存：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="storeMount" name="storeMount" value="${bookInfo.storeMount}" placeholder="库存">
            </div>
            <span></span>
        </div>

        <div class="form-group">
            <label for="author" class="col-sm-1 control-label" style="padding-left: 0">作者：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="author" value="${bookInfo.author}" name="author" placeholder="请输入作者">
            </div>
            <span></span>
        </div>

        <div class="form-group">
            <label for="press" class="col-sm-1 control-label" style="padding-left: 0">出版日期：</label>
            <div class="col-sm-5">
                <input type="date" class="form-control" id="publishDate" value="<fmt:formatDate value='${bookInfo.publishDate }' pattern='yyyy-MM-dd'/>" placeholder="例:2016-01-01" name="publishDate" >
            </div>
            <span></span>
        </div>

        <div class="form-group">
            <label for="press" class="col-sm-1 control-label" style="padding-left: 0">出版社：</label>
            <div class="col-sm-5">
                <input type="text" class="form-control" id="press" name="press" value="${bookInfo.press}" placeholder="请输入出版社">
            </div>
            <span></span>
        </div>

        <div class="form-group">
            <label for="bookCategoryId" class="col-sm-1 control-label" >类型：</label>
            <div class="col-sm-5">
                <select name="bookCategoryId" id="bookCategoryId" class="form-control" style="width: 100px;">
                    <c:forEach items="${applicationScope.bookCategories}" var="cate">
                        <option value="${cate.cateId}" ${cate.cateId == bookInfo.bookCategoryId?"selected":""}>${cate.name}</option>
                    </c:forEach>
                </select>
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="pictureFile" class="col-sm-1 control-label" >图片：</label>
            <div class="col-sm-5">
                <input type="file" id="pictureFile" name="pictureFile" onchange="changImg(event)">
                图片预览:<img alt="暂无图片" id="myImg" src="${bookInfo.imageUrl}" height="100px",width="100px">
            </div>
        </div>
        <div class="form-group">
            <label for="outline" class="col-sm-1 control-label">概述：</label>

            <div class="col-sm-5">
                 <textarea class="form-control" id="outline" name="outline" rows="4"
                           style="padding:0px">${bookInfo.outline}</textarea>
            </div>
            <span></span>
        </div>
        <div class="form-group">
            <label for="bookDesc" class="col-sm-1 control-label" style="padding: 0">书籍详情：</label>
        </div>
        <!-- 加载编辑器的容器 -->
        <script id="bookDesc" name="bookDesc" id="bookDesc" type="text/plain">
        </script>
        <div class="form-group">
            <div class="col-sm-5">
                <button type="submit" class="btn btn-lg btn-default" style="margin-top: 20px;">
                    保存修改
                </button>
            </div>
        </div>
    </form>
    <!-- 配置文件 -->
    <script type="text/javascript" src="ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="ueditor/ueditor.all.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">

        /*UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
        UE.Editor.prototype.getActionUrl = function(action) {
            if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadvideo') {
                return 'book/image/upload';
                    } else {
                        return this._bkGetActionUrl.call(this, action);
                    }
                }*/
        var ue = UE.getEditor('bookDesc');

        //对编辑器的操作最好在编辑器ready之后再做
        ue.ready(function () {
            //设置编辑器的内容
            ue.setContent('${bookDesc.bookDesc}');
            ue.setHeight("300");
            //获取html内容，返回: <p>hello</p>
            var html = ue.getContent();
            //获取纯文本内容，返回: hello
            var txt = ue.getContentTxt();
        });
    </script>
</div>
</body>
</html>

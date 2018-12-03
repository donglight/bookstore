<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <script type="text/javascript" src="js/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="js/jquery.validate.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bs.css"/>
    <link rel="stylesheet" href="css/zTreeStyle/demo.css" type="text/css">
    <link rel="stylesheet" href="css/zTreeStyle/zTreeStyle.css" type="text/css">
</head>
<script type="text/javascript">

    $(function () {

        $("#privilegeForm").validate({
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
                code: {
                    required: true
                },
                url: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: "请填写权限名"
                },
                code: {
                    required: "请填写权限代码"
                },
                url: {
                    required: "请填写资源路径"
                }
            }
        });

        var log, className = "dark";

        var rootNum = 0;
        // 添加根目录。界面上添加一个button，点击时执行。（因为，树上直接进行添加操作，添加的是子部门）
        /*function addRootDep(){
            rootNum++;
            $.ajax({
                type : "POST",
                async : false,
                url : "department!addDepNote.action",
                data :
                    {
                        name : "根部门" + rootNum,
                        pId:0
                    },
                success:function(result){
                    if(""!=result ){
                        var zTree = $.fn.zTree.getZTreeObj("privilegeTree");
                        var newNode=[{id:result,name:"根权限"+rootNum,pId:0}];
                        zTree.addNodes(null,newNode);
                    }else{
                        alert("无法添加根权限，请联系管理员！");
                    }
                }
            });
        }*/

        // 删除操作
        function beforeRemove(treeId, treeNode) {
            className = (className === "dark" ? "" : "dark");
            var zTree = $.fn.zTree.getZTreeObj("privilegeTree");
            zTree.selectNode(treeNode);
            if(treeNode.isParent){
                alert("请先删除子权限");
                return false;
            }
            var isDel = confirm("确认删除权限 -- " + treeNode.name + " 吗？");

            var isDeled = false;
            if (isDel) {
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "admin/privilege/deletion/" + treeNode.id,
                    /*data :
                        {
                            ids : treeNode.id
                        },*/
                    success: function (result) {
                        if (result.code == 200) {
                            isDeled = true;
                            $("#name").val("");
                            $("#code").val("");
                            $("#url").val("");
                        } else {
                            alert(result.message);
                            isDeled = false;
                        }
                    }
                });
                return isDeled;
            } else {
                return false;
            }
        }

        // 在进行重命名之前，进行一下确认
        function beforeEditName(treeId, treeNode) {
            className = (className === "dark" ? "" : "dark");
            var zTree = $.fn.zTree.getZTreeObj("privilegeTree");
            zTree.selectNode(treeNode);
            setTimeout(function () {
                if (confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？")) {
                    setTimeout(function () {
                        zTree.editName(treeNode);
                    }, 0);
                }
            }, 0);
            return false;
        }

        // 重命名操作
        function beforeRename(treeId, treeNode, newName, isCancel) {
            className = (className === "dark" ? "" : "dark");
            if (newName.length == 0) {
                setTimeout(function () {
                    var zTree = $.fn.zTree.getZTreeObj("privilegeTree");
                    zTree.cancelEditName();
                    alert("节点名称不能为空.");
                }, 0);
                return false;
            } else {
                var isRenamed = false;
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "admin/privilege/edit",
                    data:
                        {
                            privId: treeNode.id,
                            name: newName
                        },
                    success: function (result) {
                        if (result.code == 200) {
                            isRenamed = true;
                            $("#name").val(newName);
                        } else {
                            alert("重命名失败");
                            isRenamed = false;
                        }
                    },
                    dataType : "json"
                });
                return isRenamed;
            }
        }

        // 添加子权限操作
        var newCount = 0;

        function addHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='添加' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#addBtn_" + treeNode.tId);
            //var isAdded=false;
            if (btn) btn.bind("click", function () {
                newCount++;
                $.ajax({
                    type: "POST",
                    async: false,
                    url: "admin/privilege/addition",
                    data:
                        {
                            name: "新权限" + newCount,
                            parentId: treeNode.id
                        },
                    success: function (result) {
                        if (result.code == 200) {
                            var zTree = $.fn.zTree.getZTreeObj("privilegeTree");
                            zTree.addNodes(treeNode, {id: result.data, pId: treeNode.id, name: "新权限" + (newCount)});

                            //选中新添加的节点
                            var newNode = zTree.getNodeByParam("id",result.data,null);
                            zTree.selectNode(zTree.getNodeByParam("id",result.data,null));//选中指定节点
                            zTree.setting.callback.onClick(null, zTree.setting.treeId, newNode);//触发函数
                            return true;
                        } else {
                            alert("无法添加新权限，请联系管理员！");
                        }
                    }
                });
                return false;
            });
        }

        // 用于当鼠标移出节点时，隐藏用户自定义控件
        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_" + treeNode.tId).unbind().remove();
        }


        function showDetail(event, treeId, treeNode) {
            $.ajax({
                type: "GET",
                url: "admin/privilege/" + treeNode.id,
                success: function (result) {
                    if (result.code == 200) {
                        $("#privId").val(result.data.privId);
                        $("#parentId").val(result.data.parentId);
                        $("#name").val(result.data.name);
                        $("#code").val(result.data.code);
                        $("#url").val(result.data.url);
                    } else {
                        alert("无法查看权限详细信息，请联系系统管理员。");
                    }

                },
                dataType : "json"
            });
        }


        var setting = {
            data: {
                simpleData: {
                    enable: true,
                    rootPId: 0			// 根节点设置为0
                }/*,
                key : {
                    name : "name"	// 结点显示的name属性，对应到Json中的name
                }*/
            },
            view: {
                addHoverDom: addHoverDom,		// 用于当鼠标移动到节点上时，显示用户自定义控件。务必与 setting.view.removeHoverDom 同时使用
                removeHoverDom: removeHoverDom,	// 用于当鼠标移出节点时，隐藏用户自定义控件。务必与 addHoverDom 同时使用
                dblClickExpand: false,
                selectedMulti: false
            },
            edit: {
                enable: true,
                editNameSelectAll: true,// 节点编辑名称 input 初次显示时,设置 txt 内容是否为全选状态。
                removeTitle: "删除",	// 删除按钮的 Title 辅助信息
                renameTitle: "重命名"		// 编辑名称按钮的 Title 辅助信息。
            },
            async: {
                enable: true,
                url: "admin/privilege/treeNodes"
            },
            callback: {
                beforeEditName: beforeEditName,	// 用于捕获节点编辑按钮的 click 事件，并且根据返回值确定是否允许进入名称编辑状态
                beforeRemove: beforeRemove,	// 用于捕获节点被删除之前的事件回调函数，并且根据返回值确定是否允许删除操作
                beforeRename: beforeRename,		// 用于捕获节点编辑名称结束（Input 失去焦点 或 按下 Enter 键）之后，更新节点名称数据之前的事件回调函数，并且根据返回值确定是否允许更改名称的操作
                onClick: showDetail			// 点击节点时，显示节点详细信息
            }
        };

        $.fn.zTree.init($("#privilegeTree"), setting);
    });

    function privilegeFormAjaxSubmit() {
        var privilegeTree = $.fn.zTree.getZTreeObj("privilegeTree");
        var selectedNodes = privilegeTree.getSelectedNodes();
        if (selectedNodes.length == 1) {
            $.ajax({
                type: "POST",
                url: "admin/privilege/edit",
                data: $("#privilegeForm").serialize(),
                success: function (result) {
                    if (result.code == 200) {
                        var zTree = $.fn.zTree.getZTreeObj("privilegeTree");
                        var node = zTree.getNodeByParam("id", result.data.privId, null);
                        node.name = result.data.name;
                        zTree.updateNode(node);
                        $("#privId").val(result.data.privId);
                        $("#parentId").val(result.data.parentId);
                        $("#name").val(result.data.name);
                        $("#code").val(result.data.code);
                        $("#url").val(result.data.url);
                        alert("更新成功");
                    } else {
                        alert("无法修改权限信息，请联系系统管理员。");
                    }

                },
                dataType : "json"
            })
        } else {
            alert("请选中一个节点");
        }
    }


</script>
<style type="text/css">
    .ztree li span.button.add {
        margin-left: 2px;
        margin-right: -1px;
        background-position: -144px 0;
        vertical-align: top;
        *vertical-align: middle
    }

    .privilege_div {
        float: left;
        width: 600px;
        height: 400px;
        margin-left: 20px;
    }
</style>
</HEAD>

<BODY>
<div class="container" style="border: 1px solid #CCCCCC;height: 1000px;">
    <div style="height:100px;border-bottom: 1px solid #CCCCCC;padding-top:30px;margin-bottom: 10px">
        <h2 class="h2">权限管理</h2>
    </div>
    <div class="content_wrap privilege_div">
        <div class="zTreeDemoBackground left">
            <ul id="privilegeTree" class="ztree"></ul>
        </div>
    </div>
    <div class="privilege_div" style="padding-top: 100px">
        <form class="form-horizontal" role="form" id="privilegeForm" method="post" action="admin/privilege/edit">
            <input type="hidden" id="privId" name="privId">
            <input type="hidden" id="parentId" name="parentId">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">权限名称：</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="name" name="name" placeholder="权限名称">
                </div>
                <span></span>
            </div>
            <div class="form-group">
                <label for="code" class="col-sm-2 control-label">权限代码：</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="code" name="code" placeholder="权限代码">
                </div>
                <span></span>
            </div>
            <div class="form-group">
                <label for="url" class="col-sm-2 control-label">资源url：</label>
                <div class="col-sm-6">
                    <input type="text" class="form-control" id="url" name="url" placeholder="资源url">
                </div>
                <span></span>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-6">
                    <button type="button" onclick="privilegeFormAjaxSubmit()" class="btn btn-lg btn-default">
                        保存修改
                    </button>
                </div>
            </div>
        </form>
    </div>

</div>
</body>
</html>

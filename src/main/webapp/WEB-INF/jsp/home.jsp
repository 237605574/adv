<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<script type="text/javascript" src="/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.ajaxfileupload.js"></script>
<script type="text/javascript" src="/static/js/amazeui.min.js"></script>
<script type="text/javascript" src="/static/js/app.js"></script>
<script type="text/javascript" src="/static/js/blockUI.js"></script>
<script type="text/javascript">
    var fileName;

    function uploadFile() {
        fileName = document.getElementById('changeHeadPic').value;
        $.ajaxFileUpload({
            url: "<%=request.getContextPath()%>/userAction/uploadHeadPic",
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 'changeHeadPic', //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (data) {
                alert(data.msg);
            }

        });
    }

    function changeUserInfo() {
        $('#my-prompt').modal({
            relatedTarget: this,
            onConfirm: function () {
                uploadFile();
            },
            onCancel: function (e) {
            }
        });
    }


</script>
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>广告后台管理系统</title>
    <link rel="stylesheet" href="/static/css/amazeui.css"/>
    <link rel="stylesheet" href="/static/css/core.css"/>
    <link rel="stylesheet" href="/static/css/menu.css"/>
    <link rel="stylesheet" href="/static/css/index.css"/>
    <link rel="stylesheet" href="/static/css/admin.css"/>
    <link rel="stylesheet" href="/static/css/page/typography.css"/>
    <link rel="stylesheet" href="/static/css/page/form.css"/>
    <link rel="stylesheet" href="/static/css/component.css"/>
</head>
<body>


<!-- Begin page -->
<header class="am-topbar am-topbar-fixed-top">
    <div class="am-topbar-left am-hide-sm-only">
        <a href="<%=request.getContextPath()%>/adv/home" class="logo"><span>广告后台系统</span><i
                class="zmdi zmdi-layers"></i></a>
    </div>

    <div class="contain">
        <ul class="am-nav am-navbar-nav am-navbar-left">

            <li><h4 class="page-title">系统主页</h4></li>
        </ul>
    </div>
</header>
<!-- end page -->

<div class="am-modal am-modal-prompt" tabindex="-1" id="my-prompt">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">用户信息修改</div>
        <div class="am-modal-bd">
            <form enctype="multipart/form-data" accept-charset="UTF-8">
                姓名：
                <input type="text" class="am-modal-prompt-input" id="changeName">

                性别：
                <input type="text" class="am-modal-prompt-input" id="changeSex">

                手机号：
                <input type="text" class="am-modal-prompt-input" id="changeCell">

                年龄：
                <input type="text" class="am-modal-prompt-input" id="changeAge">

                头像：
                <div class="am-modal-prompt-input">

                    <input type="file" name="file"
                           id="changeHeadPic" size="28"/>

                </div>
            </form>
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-cancel>取消</span>
            <span class="am-modal-btn" data-am-modal-confirm>上传</span>
        </div>
    </div>
</div>

<div class="admin">
    <jsp:include page="sideBar.jsp"></jsp:include>
</div>


<a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
   data-am-offcanvas="{target: '#admin-offcanvas'}"><!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>

</body>

</html>

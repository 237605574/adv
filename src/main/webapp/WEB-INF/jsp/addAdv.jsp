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

            <li><h4 class="page-title">广告添加</h4></li>
        </ul>
    </div>
</header>
<!-- end page -->



<div class="admin">
    <jsp:include page="sideBar.jsp"></jsp:include>
    <%--表单--%>
    <div class="content-page">
        <!-- Start content -->
        <div class="content">
            <div class="am-g">
                <!-- Row start -->
                <div class="am-u-sm-12">
                    <div class="card-box">
                        <ul class="am-nav am-fr">
                            <li class="am-dropdown" data-am-dropdown="">
                                <a class="am-dropdown-toggle" data-am-dropdown-toggle="" href="javascript:;">
                                    <span class="am-icon-caret-down"></span>
                                </a>
                                <ul class="am-dropdown-content">
                                    <li>1231234</li>
                                    <li>1231234</li>
                                    <li>1231234</li>
                                    <li>1231234</li>
                                </ul>
                            </li>
                        </ul>

                        <h4 class="header-title m-t-0 m-b-30">输入广告信息</h4>

                        <div class="am-g">
                            <div class="am-u-md-6">
                                <form class="am-form am-text-sm">
                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right am-padding-left-0" for="adv_name">名字</label>
                                            <input class="am-u-md-10 form-control" id="adv_name" placeholder="输入广告名字">
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right am-padding-left-0  " for="start_time">开始有效时间</label>
                                            <input class="am-u-md-10 form-control" id="start_time" placeholder="输入开始有效时间">
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right  am-padding-left-0" for="user_tag">目标用户群</label>
                                            <%--<input class="am-u-md-10 form-control" id="user_tag" placeholder="输入开始有效时间">--%>
                                            <select id="user_tag">
                                                <option value="option1">选项一...</option>
                                                <option value="option2">选项二.....</option>
                                                <option value="option3">选项三........</option>
                                            </select>
                                            <span class="am-form-caret"></span>
                                            <button type="button" class="am-btn am-btn-primary">选值</button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="am-u-md-6">
                                <form class="am-form am-text-sm">
                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right" for="doc-ds-ipt-1">禁用</label>
                                            <input class="am-form-field am-u-md-10" type="text" id="doc-ds-ipt-1" placeholder="禁止输入" disabled="">
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right am-padding-left-0" for="doc-ipt-text-2">说明信息</label>
                                            <div class="am-u-md-10 am-padding-0">
                                                <input class="am-u-md-12 form-control am-padding-0" id="doc-ipt-text-2">
                                                <span class="help-block m-b-none">帮助文本，可能会超过一行，以块级元素显示</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right" for="doc-select-1">下拉选框</label>
                                            <%--<select id="doc-select-1">--%>
                                                <%--<option value="option1">选项一...</option>--%>
                                                <%--<option value="option2">选项二.....</option>--%>
                                                <%--<option value="option3">选项三........</option>--%>
                                            <%--</select>--%>
                                            <span class="am-form-caret"></span>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right" for="doc-select-2">多选框</label>
                                            <div class="am-u-md-10 am-padding-0">
                                                <select multiple="" class="" id="doc-select-2">
                                                    <option>1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Row end -->
            </div>


            <!-- row start -->
            <div class="am-g">
                <!-- col start -->
                <div class="am-u-md-6">
                    <div class="card-box">
                        <h4 class="header-title m-t-0 m-b-30">验证状态</h4>
                        <form action="" class="am-form">
                            <div class="am-form-group am-form-success am-form-icon am-form-feedback">
                                <div class="am-g">
                                    <label class="am-form-label am-u-md-3 am-md-text-right am-padding-right-0" for="doc-ipt-success">验证成功</label>
                                    <div class="am-u-md-6 am-u-end">
                                        <input type="text" id="doc-ipt-success" class="am-form-field am-radius">
                                        <span class="am-icon-check" id="rightPa"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="am-form-group am-form-warning">
                                <div class="am-g">
                                    <label class="am-form-label am-u-md-3 am-md-text-right am-padding-right-0" for="doc-ipt-warning">验证警告</label>
                                    <div class="am-u-md-6 am-u-end">
                                        <input type="text" id="doc-ipt-warning" class="am-form-field am-radius">
                                    </div>
                                </div>
                            </div>

                            <div class="am-form-group am-form-error">
                                <div class="am-g">
                                    <label class="am-form-label am-u-md-3 am-md-text-right am-padding-right-0" for="doc-ipt-error">验证失败</label>
                                    <div class="am-u-md-6 am-u-end">
                                        <input type="text" id="doc-ipt-error" class="am-form-field am-radius">
                                    </div>
                                </div>
                            </div>

                            <div class="am-form-group am-form-success am-form-icon am-form-feedback">
                                <div class="am-g">
                                    <label class="am-form-label am-u-md-3 am-md-text-right am-padding-right-0" for="doc-ipt-success2">验证成功</label>
                                    <div class="am-u-md-6 am-u-end">
                                        <input type="text" class="am-form-field am-radius" id="doc-ipt-success2" placeholder="验证成功">
                                        <span class="am-icon-check" id="rightPa"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="am-form-group am-form-warning am-form-icon am-form-feedback">
                                <div class="am-g">
                                    <label class="am-form-label am-u-md-3 am-md-text-right am-padding-right-0" for="doc-ipt-warm2">验证警告</label>
                                    <div class="am-u-md-6 am-u-end">
                                        <input type="text" class="am-form-field am-u-md-3 am-md-text-right am-padding-right-0 am-radius" id="doc-ipt-warm2" placeholder="验证警告">
                                        <span class="am-icon-warning" id="rightPa"></span>
                                    </div>
                                </div>
                            </div>

                            <div class="am-form-group am-form-error am-form-icon am-form-feedback">
                                <div class="am-g">
                                    <label class="am-form-label am-u-md-3 am-md-text-right am-padding-right-0" for="doc-ipt-error2">验证失败</label>
                                    <div class="am-u-md-6 am-u-end">
                                        <input type="text" class="am-form-field am-u-md-3 am-md-text-right am-padding-right-0 am-radius" id="doc-ipt-error2" placeholder="验证警告">
                                        <span class="am-icon-times" id="rightPa"></span>
                                    </div>
                                </div>
                            </div>



                        </form>
                    </div>
                </div>
                <!-- col end -->

                <!-- col start -->
                <div class="am-u-md-6">
                    <div class="card-box">
                        <h4 class="header-title m-t-0 m-b-30">表单域大小 </h4>
                        <form class="am-form">
                            <input class="am-form-field am-input-lg am-radius" type="text" placeholder="添加了 .am-input-lg">
                            <br>
                            <input class="am-form-field am-radius" type="text" placeholder="默认的 input">
                            <br>
                            <input class="am-form-field am-input-sm am-radius" type="text" placeholder="添加了 .am-input-sm">
                            <br>

                            <div class="am-form-group am-form-select">
                                <select class=" am-input-lg am-radius">
                                    <option value="">添加了 .am-input-lg</option>
                                </select>
                            </div>

                            <div class="am-form-group am-form-select">
                                <select class="am-radius">
                                    <option value="">select 默认大小</option>
                                </select>
                            </div>

                            <div class="am-form-group am-form-select">
                                <select class=" am-input-sm am-radius">
                                    <option value="">添加了 .am-input-sm</option>
                                </select>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- col end -->
            </div>
            <!-- row end -->


            <!-- row start -->
            <div class="am-g">
                <!-- col start -->
                <div class="am-u-md-6">
                    <div class="card-box">
                        <h4 class="header-title m-t-0 m-b-30">基本示例</h4>
                        <form class="am-form">
                            <div class="am-form-group">
                                <label for="doc-ipt-email-2">邮件</label>
                                <input type="email" class="am-radius" id="doc-ipt-email-2" placeholder="输入电子邮件">
                            </div>

                            <div class="am-form-group">
                                <label for="doc-ipt-pwd-2">密码</label>
                                <input type="password" class="am-radius" id="doc-ipt-pwd-2" placeholder="设置个密码吧">
                            </div>

                            <div class="am-checkbox">
                                <label>
                                    <input type="checkbox"> 记住我
                                </label>
                            </div>

                            <button type="submit" class="am-btn am-btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
                <!-- col end -->

                <!-- col start -->
                <div class="am-u-md-6">
                    <div class="card-box">
                        <h4 class="header-title m-t-0 m-b-30">水平排列</h4>
                        <form class="am-form am-form-horizontal">
                            <div class="am-form-group">
                                <label for="doc-ipt-3" class="am-u-sm-3 am-form-label am-text-right am-padding-right-0">电子邮件</label>
                                <div class="am-u-sm-9">
                                    <input type="email" id="doc-ipt-3" placeholder="输入你的电子邮件">
                                </div>
                            </div>

                            <div class="am-form-group">
                                <label for="doc-ipt-pwd-2" class="am-u-sm-3 am-form-label am-text-right am-padding-right-0">密码</label>
                                <div class="am-u-sm-9">
                                    <input type="password" id="doc-ipt-pwd-2" placeholder="设置一个密码吧">
                                </div>
                            </div>

                            <div class="am-form-group">
                                <label for="doc-ipt-pwd-3" class="am-u-sm-3 am-form-label am-text-right am-padding-right-0">确认密码</label>
                                <div class="am-u-sm-9">
                                    <input type="password" id="doc-ipt-pwd-3" placeholder="输入确认密码">
                                </div>
                            </div>

                            <div class="am-form-group">
                                <div class="am-u-sm-offset-3 am-u-sm-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox"> 记住我
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="am-form-group">
                                <div class="am-u-sm-10 am-u-sm-offset-3">
                                    <button type="submit" class="am-btn am-btn-default">提交登入</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- col end -->
            </div>
            <!-- row end -->

        </div>
    </div>
</div>


<a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
   data-am-offcanvas="{target: '#admin-offcanvas'}"><!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>

</body>

</html>

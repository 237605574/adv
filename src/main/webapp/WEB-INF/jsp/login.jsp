<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 上面这两行是java代码的引用 --%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
    <title>广告后台管理系统</title>
    <meta charset="utf-8">
    <link href="/css/login.css" rel='stylesheet' type='text/css'/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
</head>
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.tips.js"></script>
<script type="text/javascript" src="/js/login.js"></script>
<script type="text/javascript">
    function checkLoginInfo() {
        if ("" == $("#u").val() || "输入用户名" == $("#u").val()) {
            $("#u").tips({
                side: 2,
                msg: '用户名不得为空',
                bg: '#AE81FF',
                time: 3
            });
            $("#u").focus();
            return false;
        }
        if ($("#p").val() == "") {

            $("#p").tips({
                side: 2,
                msg: '密码不得为空',
                bg: '#AE81FF',
                time: 3
            });
            $("#p").focus();
            return false;
        }
        return true;
    }

    function webLogin() {
        if (checkLoginInfo()) {
            var loginname = $("#u").val();
            var password = $("#p").val();
            $.ajax({
                type: "POST",
                url: '<%=request.getContextPath()%>/userAction/login',
                data: {name: loginname, password: password},
                dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自己解析的，只需要 返回值.字段名称 就能使用了
                cache: false,
                success: function (data) {
                    // alert(data.code)
                    if (data.code == 0) {
                        window.location.href = data.data.nextUrl;
                    } else {
                        $("#u").tips({
                            side: 2,
                            msg: data.msg,
                            bg: '#AE81FF',
                            time: 3
                        });
                        $("#u").focus();
                    }
                }
            });
        }
    }


</script>
<body>
<!-----start-main---->
<div class="main">
    <div class="login-form">
        <h1>管理员登录</h1>
        <div class="head">
            <img src="/images/user.png" alt=""/>
        </div>
        <form>
            <input id="u" type="text" name="name" class="text" value="输入用户名"
                   onfocus="if (this.value == '输入用户名'){this.value = ''};"
                   onblur="if (this.value == '') {this.value = '输入用户名';}">
            <input id="p" type="password" name="password" placeholder="输入密码">
            <div class="submit">
                <input type="button" onclick="webLogin()" value="登录">
            </div>
            <p><a href="#">帮助</a></p>
        </form>
    </div>
    <!--//End-login-form-->
    <!-----start-copyright---->
    <div class="copy-right">
        <p>Copyright &copy; 2014.Company name All rights reserved.
    </div>
    <!-----//end-copyright---->
</div>
<!-----//end-main---->

<div style="display:none">
    <script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script>
</div>
</body>
</html>
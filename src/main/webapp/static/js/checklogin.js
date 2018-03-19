document.write("<script language='javascript' src='/static/js/jquery.tips.js'></script>");

function webLogin() {   //定义一个名为webLogin的js函数（在java中我们称呼函数为方法）
    // alert("lrz test");
    var loginname = $("#u").val();
    //var是申明一个变量的关键字，loginname为变量名，
    //$("#u")是找到一个标签ID为"u"的标签，.val() 是获取对应ID标签的值
    if ("" == loginname) {  //u标签的值为空
        //只有通过 $("#u") 的形式才能获取一个标签。
        $("#u").tips({  // .tips 是js提示标签的调用方法，具体的轮廓如上面的登陆页面的提示标签
            side: 2,
            msg: '用户名不得为空',  //提示的信息
            bg: '#AE81FF',  //背景色
            time: 3 //呈现的时间
        });
        $("#u").focus();    //让u标签获取输入焦点
        return false;   //返回false，打断js的执行
    }

    var loginpwd = $("#p").val();
    if (loginpwd == "") {
        $("#p").tips({
            side: 2,
            msg: '密码不得为空',
            bg: '#AE81FF',
            time: 3
        });
        $("#p").focus();
        return false;
    }

    $.ajax({    //使用jquery下面的ajax开启网络请求
        type: "POST",   //http请求方式为POST
        url: '<%=request.getContextPath()%>/userAction/login',  //请求接口的地址
        data: {loginId: loginname, pwd: loginpwd},  //存放的数据，服务器接口字段为loginId和pwd，分别对应用户登录名和密码
        dataType: 'json',   //当这里指定为json的时候，获取到了数据后会自动解析的，只需要 返回值.字段名称 就能使用了
        cache: false,   //不用缓存
        success: function (data) {  //请求成功，http状态码为200。返回的数据已经打包在data中了。
            if (data.code == 1) {   //获判断json数据中的code是否为1，登录的用户名和密码匹配，通过效验，登陆成功
                window.location.href = "<%=request.getContextPath()%>/mvc/home";    //跳转到主页
            } else {    //登录不成功
                alert(data.msg);    //弹出对话框，提示返回的错误信息
                $("#u").focus();
            }
        }
    });
}
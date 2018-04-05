<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<script type="text/javascript" src="/static/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/static/js/jquery.tips.js"></script>
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
                                    <li>这里是说明文档,目前仅支持图片格式的广告上传</li>
                                </ul>
                            </li>
                        </ul>

                        <h4 class="header-title m-t-0 m-b-30">输入广告信息</h4>

                        <div class="am-g">
                            <div class="am-u-md-6">
                                <form class="am-form am-text-sm">
                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right am-padding-left-0"
                                                   for="adv_name">名字</label>
                                            <div class="am-u-md-10 am-padding-0">
                                                <input class="am-input-lg am-u-md-10 form-control" id="adv_name"
                                                       placeholder="输入广告名字" style="width:100%">
                                                <span class="help-block m-b-none">广告名字要唯一</span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right am-padding-left-0  "
                                                   for="start_time">开始有效时间</label>
                                            <input class="inputClass am-u-md-10 form-control "
                                                   style=" width: 83.33333333%;" id="start_time" type="datetime-local"
                                                   placeholder="输入开始有效时间">
                                        </div>
                                    </div>

                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right  am-padding-left-0" for="user_tag">目标用户群</label>
                                            <%--<input class="am-u-md-10 form-control" id="user_tag" placeholder="输入开始有效时间">--%>
                                            <input class="am-input-lg am-form-field am-u-md-10" type="text"
                                                   id="user_tag"
                                                   placeholder="从下方选择用户群" disabled="">
                                            <div id="tag-main-container" style="display: none">
                                                <label class="am-u-md-2 am-md-text-right  am-padding-left-0"
                                                       for="tag-action-container">选择用户群</label>
                                                <div id="tag-action-container" class="am-form-field am-u-md-10"
                                                     style="overflow-y: scroll;overflow-x: scroll; height: 150px">
                                                </div>
                                            </div>
                                            <span class="am-form-caret"></span>
                                        </div>

                                        <button id="tag_btn" type="button" onclick="selectTag()"
                                                class="am-btn am-btn-default">选值
                                        </button>
                                    </div>
                                    <%-- <div class="am-form-group am-form-file" id="file-body">
                                         <button type="button" class="am-btn am-btn am-btn-secondary am-btn-sm">
                                             <i class="am-icon-cloud-upload"></i> 选择要上传的广告文件
                                         </button>
                                         <input name="file" id="adv-file" type="file" multiple accept="image/*">
                                     </div>
                                     <div id="file-list"></div>--%>
                                </form>
                            </div>

                            <div class="am-u-md-6">
                                <form class="am-form am-text-sm">
                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right am-padding-left-0"
                                                   for="adv-type-input">广告类型</label>
                                            <div class="am-u-md-10 am-padding-0">
                                                <input class="am-form-field am-u-md-10 " type="text" id="adv-type-input"
                                                       value="图片" disabled="">
                                                <span class="help-block m-b-none">目前仅支持图片类型</span>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right am-padding-left-0  "
                                                   for="end_time">有效时间截止</label>
                                            <input class="inputClass am-u-md-10 form-control "
                                                   style=" width: 83.33333333%;" id="end_time" type="datetime-local"
                                                   placeholder="输入截止的时间">
                                        </div>
                                    </div>
                                    <div class="am-form-group">
                                        <div class="am-g">
                                            <label class="am-u-md-2 am-md-text-right am-padding-left-0"
                                                   for="adv_name">跳转URL</label>
                                            <input class="am-input-lg am-u-md-10 form-control" id="adv-url-input"
                                                   placeholder="输入点击广告之后要跳转的URL">
                                        </div>
                                    </div>

                                    <%--    <div class="am-form-group">
                                            <div class="am-g">
                                                <label class="am-u-md-2 am-md-text-right"
                                                       for="adv-display-time">播放时间段</label>

                                                <div class="am-u-md-10 am-input-lg am-padding-0">
                                                    <select multiple="multiple" class="" id="adv-display-time">
                                                        <option value="0">0:00 —— 1:00</option>
                                                        <option value="1">1:00 —— 2:00</option>
                                                        <option value="2">2:00 —— 3:00</option>
                                                        <option value="3">3:00 —— 4:00</option>
                                                        <option value="4">4:00 —— 5:00</option>
                                                        <option value="5">5:00 —— 6:00</option>
                                                        <option value="6">6:00 —— 7:00</option>
                                                        <option value="7">7:00 —— 8:00</option>
                                                        <option value="8">8:00 —— 9:00</option>
                                                        <option value="9">9:00 —— 10:00</option>
                                                        <option value="10">10:00 —— 11:00</option>
                                                        <option value="11">11:00 —— 12:00</option>
                                                        <option value="12">12:00 —— 13:00</option>
                                                        <option value="13">13:00 —— 14:00</option>
                                                        <option value="14">14:00 —— 15:00</option>
                                                        <option value="15">15:00 —— 16:00</option>
                                                        <option value="16">16:00 —— 17:00</option>
                                                        <option value="17">17:00 —— 18:00</option>
                                                        <option value="18">18:00 —— 19:00</option>
                                                        <option value="19">19:00 —— 20:00</option>
                                                        <option value="20">20:00 —— 21:00</option>
                                                        <option value="21">21:00 —— 22:00</option>
                                                        <option value="22">22:00 —— 23:00</option>
                                                        <option value="23">23:00 —— 24:00</option>
                                                    </select>
                                                    <span class="help-block m-b-none">投放时间段以小时为单位，按住CTRL进行多选</span>
                                                </div>

                                            </div>
                                        </div>--%>
                                </form>
                                <button id="submit-btn" type="button" onclick="submitAdv()"
                                        class="am-btn am-btn am-btn-primary" style="float:right">确定
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Row end -->
            </div>
            <div class="am-g">
                <div class="am-u-sm-12">
                    <div class="card-box">
                        <h4 class="header-title m-t-0 m-b-30">查询结果显示</h4>
                        <div class="am-scrollable-horizontal">
                            <table class="am-table am-table-bordered am-table-striped am-text-nowrap am-table-hover">
                                <thead>
                                <tr>
                                    <th>广告名字</th>
                                    <th>网址</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>类型</th>
                                    <th>是否可用</th>
                                    <th>目标用户群</th>
                                    <th>投放时间段</th>
                                </tr>
                                </thead>
                                <tbody id="query-body">
                                </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>


<a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu"
   data-am-offcanvas="{target: '#admin-offcanvas'}"><!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>

<div class="am-modal am-modal-prompt" tabindex="-1" id="error-alert">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">发生错误</div>
        <div class="am-modal-bd" id="error-msg">
            Hello world！
        </div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>
<div class="am-modal am-modal-prompt" tabindex="-1" id="success-alert">
    <div class="am-modal-dialog">
        <div class="am-modal-hd">提交成功</div>
        <div class="am-modal-footer">
            <span class="am-modal-btn" data-am-modal-confirm>确定</span>
        </div>
    </div>
</div>
</body>
<script>
    function ajaxErrorAlert(XMLHttpRequest, textStatus, errorThrown) {
        var msg = "状态码:" + XMLHttpRequest.status + "\n"
            + "ready state:" + XMLHttpRequest.readyState + "\n"
            + "text status:" + textStatus + "\n"
            + "error thrown" + errorThrown;
        alertErrorMsg(msg);
    }

    $.ajax({
        type: "POST",
        url: '<%=request.getContextPath()%>/tag/getAllUserTags',
        dataType: 'json',
        cache: false,
        success: function (result) {
            if (result.code == 0) {
                var tagList = result.data;
                var listN = 7;
                var mainElement = $("#tag-action-container");
                var divElement = document.createElement("div");
                divElement.className = "am-checkbox";
                mainElement.append(divElement);
                var tagContainer = divElement;
                for (var i = 0; i < tagList.length; i++) {
                    var element = document.createElement("label");
                    element.className = "am-checkbox-inline am-input-lg";
                    var tagInput = document.createElement("input");
                    tagInput.type = "checkbox";
                    tagInput.name = "user_tag_check_box";
                    tagInput.value = tagList[i].id;
                    // tagInput.append(tagList[i].name);
                    element.innerText = tagList[i].name;
                    tagInput.setAttribute("data-am-ucheck", "");
                    element.appendChild(tagInput);

                    if ((i % listN) === 0 && i !== 0) {
                        var temp = document.createElement("div");
                        temp.className = "am-checkbox";
                        mainElement.append(temp);
                        tagContainer = temp;
                    }
                    tagContainer.append(element);
                }
            }
        },
        error: function (msg) {
            alertErrorMsg("error:" + msg);
        }
    });

    var isSelected = false;

    function selectTag() {
        isSelected = !isSelected;
        var tagBtn = document.getElementById("tag_btn");
        var mainContainer = document.getElementById("tag-main-container");
        if (isSelected) {
            mainContainer.style.display = "block";
            tagBtn.innerText = "确定";
        } else {
            var result = [];
            var tagCheckBox = document.getElementsByName("user_tag_check_box");
            for (var i = 0; i < tagCheckBox.length; i++) {
                if (tagCheckBox[i].checked)
                    result.push(tagCheckBox[i].parentElement.innerText);
            }
            document.getElementById("user_tag").value = result.join(",");
            mainContainer.style.display = "none";
            tagBtn.innerText = "选值"
        }
    }


    function submitAdv() {
        if (!checkAdv()) {
            return;
        }
        var advName = $("#adv_name").val();
        var startDate = $("#start_time").val();
        var endDate = $("#end_time").val();
        var advUrl = $("#adv-url-input").val();
        var userTags = new Array();
        $('input[name="user_tag_check_box"]:checked').each(function () {
            userTags.push(parseInt($(this).val()));
        });
        var displayDetail = $("#adv-display-time").val();
        var advType = 1;
        var advData = {
            name: advName,
            tags: userTags,
            type: advType,
            startDate: startDate,
            endDate: endDate,
            homepage: advUrl,
            displayDetail: JSON.stringify(displayDetail)
        };
        var jsonData = JSON.stringify(advData);
        var parseData = JSON.parse(jsonData);
        $.ajax({
            type: "POST",
            url: '<%=request.getContextPath()%>/advAction/addInfo',
            data: advData,
            dataType: 'json',
            cache: false,
            success: function (result) {
                if (result.code === 0) {
                    uploadAdvFile();
                } else {
                    alertErrorMsg(result.msg);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                ajaxErrorAlert(XMLHttpRequest, textStatus, errorThrown);
            }

        });
    }

    function uploadAdvFile() {
        $.ajaxFileUpload({
            url: "<%=request.getContextPath()%>/advAction/uploadAdvFile",
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: 'adv-file', //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            // contentType: "text/html; charset=utf-8",
            success: function (result) {
                if (result.code === 0) {
                    addAdv();
                } else {
                    alertErrorMsg(result.msg);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                ajaxErrorAlert(XMLHttpRequest, textStatus, errorThrown);
            }
        });
    }

    function addAdv() {
        $.ajax({
            type: "POST",
            url: '<%=request.getContextPath()%>/advAction/addAdv',
            dataType: 'json',
            cache: false,
            success: function (result) {
                if (result.code === 0) {
                    alertSuccessMsg();
                } else {
                    alertErrorMsg(result.msg);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                ajaxErrorAlert(XMLHttpRequest, textStatus, errorThrown);
            }
        })
    }

    function checkAdv() {
        var advName = $("#adv_name").val();
        var startDate = $("#start_time").val();
        var endDate = $("#end_time").val();
        var advUrl = $("#adv-url-input").val();
        var userTags = new Array();
        var minDate = new Date(1900, 1, 1);
        var maxDate = new Date(2200, 1, 1);
        var dStart = new Date(Date.parse(startDate));
        var dEnd = new Date(Date.parse(endDate));
        $('input[name="user_tag_check_box"]:checked').each(function () {
            userTags.push(parseInt($(this).val()));
        });


        if (dStart < minDate || dStart > maxDate) {
            $("#start_time").tips({
                side: 2,
                msg: '日期超出限制',
                bg: '#ff293f',
                time: 3
            });
            return false;
        }
        if (dEnd < minDate || dEnd > maxDate) {
            $("#end_time").tips({
                side: 2,
                msg: '日期超出限制',
                bg: '#ff293f',
                time: 3
            });
            return false;
        }
        // 判断是否为空
        if ((startDate == null || startDate === "") && (endDate == null || endDate === "")
            && (advUrl == null || advUrl == "") && (advName == null || advName === "")
            && (userTags.length == 0)) {
            alertErrorMsg("请输入查询内容")
        }
    }

    function alertErrorMsg(msg) {
        $("#error-msg").text(msg);
        $("#error-alert").modal({
            relatedTarget: this,
            onConfirm: function () {
                // alert("test");
            }
        });
    }

    function alertSuccessMsg() {
        $("#success-alert").modal({
            relatedTarget: this,
            onConfirm: function () {
                window.location.href = "<%=request.getContextPath()%>/adv/home";
            }
        });
    }

</script>
</html>

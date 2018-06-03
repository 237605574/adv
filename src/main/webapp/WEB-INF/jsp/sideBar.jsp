<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="admin-sidebar am-offcanvas  am-padding-0" id="admin-offcanvas">
    <div class="am-offcanvas-bar admin-offcanvas-bar">
        <!-- User -->
        <div class="user-box am-hide-sm-only">
            <div class="user-img">
                <img src="/images/avatar-1.jpg" alt="user-img" title="管理员"
                     class="img-circle img-thumbnail img-responsive" onclick="changeUserInfo()">

                <div class="user-status offline">
                    <i class="am-icon-dot-circle-o" aria-hidden="true"></i>
                </div>
            </div>
            <h5><a href="#">管理员</a></h5>
            <ul class="list-inline">
                <li>
                    <a href="#">
                        <i class="fa fa-cog" aria-hidden="true"></i>
                    </a>
                </li>

                <li>
                    <a href="#" class="text-custom">
                        <i class="fa fa-cog" aria-hidden="true"></i>
                    </a>
                </li>
            </ul>
        </div>
        <!-- End User -->

        <ul class="am-list admin-sidebar-list">
            <li><a href="<%=request.getContextPath()%>/adv/home"><span class="am-icon-home"></span> 首页</a></li>
            <li><a href="<%=request.getContextPath()%>/adv/addAdv"><span class="am-icon-table"></span> 添加</a></li>
            <li class="admin-parent">
                <a class="am-cf" data-am-collapse="{target: '#collapse-nav1'}"><span class="am-icon-table"></span>
                    广告管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav1">
                    <li><a href="<%=request.getContextPath()%>/adv/queryAdv" class="am-cf"> 查询/修改/删除</a></li>
                </ul>
            </li>
            <li><a href="<%=request.getContextPath()%>/userAction/logout"><span class="am-icon-home"></span> 退出登录</a></li
        </ul>
    </div>
</div>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="pagestart.jsp"%>
<%
	com.lubian.cpf.service.sys.SysNotificationService notiService = com.lubian.cpf.common.util.spring.SpringContextUtil.getBean(com.lubian.cpf.service.sys.SysNotificationService.class);
int notiCount = notiService.countUnProceedNoti();
%>
        <div id="header" class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <div id="logo" class="brand" >
                      <img src="${SITE.images_context}/images/logo.png">
                    </div><%--
                    
                    <div id="menu">
                      <ul class="nav top-menu">
                      	<li class="btnav-info selected"><a href="#">病历中心</a></li>
                      	<li class="btnav-info"><a href="${SITE.context}/doctor/collect/list">我的收藏</a></li>
                      	<li class="btnav-info"><a href="#">个人中心</a></li>
                      </ul>
                    </div>    

                    --%><!-- //顶部左侧链接与菜单 -->
                    <div class="b-top-nav">
                      <ul class="nav pull-right top-menu">
                          <li class="btnav-info">欢迎您, <c:if test="${not empty(USER.realName)}">${USER.realName }</c:if>
                         	<c:if test="${empty(USER.realName)}">${USER.userName }</c:if></li>
                          <li class="btnav-menu dropdown">
                          	  <a title="设置" data-toggle="dropdown" class="dropdown-toggle" href="#">
                                  <i class="icon-caret-down"></i>
                              </a>
                              <ul class="dropdown-menu">
                                  <li><a data-title="修改个人信息" href="${SITE.context}/admin/changeUserInfo" data-trigger="modal"><i class="icon-user"></i> 修改个人信息</a></li>
                                  <li><a data-title="修改密码" href="${SITE.context}/admin/changePass" data-trigger="modal"><i class="icon-key"></i> 修改密码</a></li>
                                  <li><a data-title="退出系统" href="${SITE.context}/admin/logout"><i class="icon-off"></i>退出系统</a></li>
                                  
                              </ul>
                          </li>
                      </ul>
                  </div><!--//b-top-nav:end-->

                </div><!--//container:end-->
            </div><!--//container-fluid:end-->
        </div><!--//header:end-->
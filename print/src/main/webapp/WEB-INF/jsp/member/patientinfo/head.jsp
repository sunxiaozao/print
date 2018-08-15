<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>
<%
	com.lubian.cpf.service.sys.SysNotificationService notiService = com.lubian.cpf.common.util.spring.SpringContextUtil.getBean(com.lubian.cpf.service.sys.SysNotificationService.class);
int notiCount = notiService.countUnProceedNoti();
%>
        <div id="header" class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">
                    <div id="logo" class="brand" >
                      <img src="${SITE.images_context}/images/logo.png">
                    </div>
                    
                    <div id="menu">
                      <ul class="nav top-menu">
                      	<li class="btnav-info "><a href="${SITE.context}/member/index">我的病历</a></li>
                      	<li class="btnav-info"><a href="${SITE.context}/member/relation/list">我的好友</a></li>
  
                      	<li class="btnav-info "><a href="${SITE.context}/member/share/list">我的分享</a></li>
                      	<li class="btnav-info selected"><a href="${SITE.context}/member/info">个人中心</a></li>
                      </ul>
                    </div>    

                    <!-- //顶部左侧链接与菜单 -->
                    <div class="b-top-nav">
                      <ul class="nav pull-right top-menu">
                          <li class="btnav-info">欢迎您, <c:if test="${not empty(PATIENT.fullname)}">${PATIENT.fullname }</c:if>
                         	<c:if test="${empty(PATIENT.fullname)}">${PATIENT.userName }</c:if></li>
                          <li class="btnav-menu dropdown">
                          	  <a title="设置" data-toggle="dropdown" class="dropdown-toggle" href="#">
                                  <i class="icon-caret-down"></i>
                              </a>
                              <ul class="dropdown-menu">
                                    <li><a data-title="修改主页风格" href="${SITE.context}/member/homestyle" data-trigger="modal"><i class="icon-cog" ></i> 修改主页风格</a></li>
                                    <li><a data-title="关联账号" href="${SITE.context}/member/relationPatient" data-trigger="modal"><i class="icon-exchange" ></i> 关联账号</a></li>
                                  <li><a data-title="修改密码" href="${SITE.context}/member/changePass" data-trigger="modal"><i class="icon-key"></i> 修改密码</a></li>
                                  <li><a data-title="退出系统" href="${SITE.context}/admin/logout"><i class="icon-off"></i> 退出系统</a></li>
                              </ul>
                          </li>
                      </ul>
                  </div><!--//b-top-nav:end-->

                </div><!--//container:end-->
            </div><!--//container-fluid:end-->
        </div><!--//header:end-->
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../../common/pagestart.jsp"%>
<%
	com.lubian.cpf.service.sys.SysNotificationService notiService = com.lubian.cpf.common.util.spring.SpringContextUtil
	.getBean(com.lubian.cpf.service.sys.SysNotificationService.class);
	int notiCount = notiService.countUnProceedNoti();
%>
<div id="header" class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container-fluid">
			<div id="logo" class="brand">
				<img src="${SITE.images_context}/images/logo.png">
			</div>

			<div id="menu">
				<ul class="nav top-menu">
					<li class="btnav-info selected"><a
						href="#">我的分享</a>
					</li>
					<li class="btnav-info "><a
						href="${SITE.context}/member/share/notebook/${caseHistory.id}/${caseHistory.patientId}"
						data-trigger="modal" data-title="留言"
						>留言</a>
					</li>
					<li class="btnav-info"><a
						href="${SITE.context}/member/share/info/${shareId}" data-trigger="modal"
						data-title="完善个人信息">注册账号</a>
					</li>
					<li class="btnav-info"><a
						href="${SITE.context}/member/share/sharing/${shareId}" data-trigger="modal"
						data-title="关联到现有账号">关联到现有账号</a>
					</li>
				</ul>
			</div>
		</div>
		<!--//container:end-->
	</div>
	<!--//container-fluid:end-->
</div>
<!--//header:end-->
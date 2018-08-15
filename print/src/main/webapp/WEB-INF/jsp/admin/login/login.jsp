<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html class="ie8"> <![endif]-->
<!--[if IE 9]> <html class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<head>
<title>印刷平台</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- // base css -->
<link href="${SITE.static_context}/stylelib/base.css" rel="stylesheet"
	type="text/css" />


<link href="${SITE.static_context}/stylelib/responsive.css"
	rel="stylesheet" type="text/css" />

<!-- // plugins css -->
<link
	href="${SITE.static_context}/stylelib/plugins/font-awesome/font-awesome.css"
	rel="stylesheet" type="text/css" />
<link href="${SITE.static_context}/stylelib/plugins/iealert/style.css"
	rel="stylesheet" type="text/css" />

<!-- // theme css -->
<!-- <link href="${SITE.static_context}/stylelib/theme_default/button.css" rel="stylesheet" type="text/css" /> -->
<link href="${SITE.static_context}/stylelib/theme_default/screen.css"
	rel="stylesheet" type="text/css" />
<link
	href="${SITE.static_context}/stylelib/theme_default/my_responsive.css"
	rel="stylesheet" type="text/css" />

<!-- // 全局 css -->
<link href="${SITE.static_context}/css/global.css" rel="stylesheet"
	type="text/css" />
<link href="${SITE.static_context}/css/login.css" rel="stylesheet"
	type="text/css" />
<link href="${SITE.static_context}/css/3rd/validationEngine.jquery.css"
	rel="stylesheet" type="text/css" />


<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
          <script src="${SITE.static_context}/stylelib/plugins/html5shiv.js"></script>
        <![endif]-->
</head>
<body id="login-page">
	<div id="notify-body" style="display: none;"></div>
	<div id="login-box">
		<div class="row-fluid">
			<div class="span12">
				<div class="b-login-body">
					<div class="blb-form">
						<div class="row-fluid">
							<div class="span6">
								<img alt="夏宇科技云胶片系统"
									src="${SITE.images_context}/images/loginbg.png">
							</div>
							<div class="span6">


								<div class="b-login-form-head">
									<img src="${SITE.images_context}/images/logo2.png">
								</div>

								<div class="alert fade in"
									style="display:none; margin: 0 10px 5px 10px;">
									<strong><span id="warningText">Holy guacamole!</span>
									</strong>
								</div>

								<form id="loginForm" action="" method="get">
									<div class="control-group">
										<div class="controls">
											<input class="span10" type="text" id="inputUsername"
												name="inputUsername" placeholder="请输入您的用户名(用户名/ICID/手机号/邮箱)" />
										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<input class="span10" type="password" id="inputPassword"
												name="inputPassword" placeholder="请输入您的密码" /> <label
												class="checkbox"><input type="checkbox"
												name="rememberMe">&nbsp;使我保持登录状态</label>
										</div>
									</div>
									<div class="control-group">
										<button type="submit" class="btn btn-primary span3"
											id="submitButton">登 录</button>
									</div>

								</form>

								<div class="b-login-form-foot">
									<a title="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;找回密码"
										href="${SITE.context}/admin/backpassword">忘记密码?</a> 没有账号?  <a data-trigger="modal"
										href="${SITE.context}/admin/register" data-cache="false"
										data-title="新用户注册 <small>请填信息，然后保存。</small>" >立即注册</a>

								</div>
							</div>
						</div>

					</div>
					<!--//blb-form:end-->
				</div>
				<!--//b-login-body:end-->
			</div>
			<!--//span12:end-->
		</div>
		<!--//row-fluid:end-->
	</div>
	<!--//login-box:end-->

	<jsp:include page="../../common/footer.jsp"></jsp:include>

	<!--// Loading JS -->
	<script src="${SITE.static_context}/stylelib/jquery-1.10.2.min.js"></script>
	<script src="${SITE.static_context}/stylelib/base.js"></script>
	<script
		src="${SITE.static_context}/stylelib/plugins/iealert/iealert.js"></script>
	<script
		src="${SITE.static_context}/stylelib/plugins/jquery.nicescroll.min.js"></script>
	<script src="${SITE.static_context}/stylelib/plugins/sco.modal.js"></script>
	<script src="${SITE.static_context}/stylelib/plugins/sco.confirm.js"></script>

	<!--在页面顶部显示提醒通知消息  -->
	<script src="${SITE.static_context}/js/3rd/jquery.topbar.js"></script>
	<script src="${SITE.static_context}/js/3rd/jquery.md5.js"></script>

	<!--common script for all pages-->
	<script src="${SITE.static_context}/stylelib/theme_default/global.js"></script>

	<!--script for this page only-->
	<script
		src="${SITE.static_context}/stylelib/plugins/form-validity/jquery.validity.js"></script>
	<script src="${SITE.static_context}/js/3rd/jquery.validationEngine.js"></script>
	<script
		src="${SITE.static_context}/js/3rd/jquery.validationEngine-zh_CN.js"></script>
	<script type="text/javascript">
		var SITE = {};
		SITE.context = "${SITE['context']}";
		SITE.images_context = "${SITE['images_context']}";
		SITE.static_context = "${SITE['static_context']}";
		var module = {
			param : {}
		};
	</script>

	<script type="text/javascript" src="${SITE.static_context}/js/main.js"></script>
	<script>
		$(function() {
			if (top.location != document.location.href) {
				top.location = document.location.href;
			}
			module.signin.init();
			<c:if test="${not empty error}">
			module.common.showPopupWindow('${error}');
		</c:if>
		});
	</script>
	<!--// JS End -->
</body>
</html>

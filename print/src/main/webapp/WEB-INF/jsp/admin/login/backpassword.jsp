<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html class="ie8"> <![endif]-->
<!--[if IE 9]> <html class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<head>
<title>夏宇科技云胶片系统</title>
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
							
							<div class="span12">
								<div class="b-login-form-head">
									<img src="${SITE.images_context}/images/logo2.png">
								</div>

								<div class="alert fade in"
									style="display:none; margin: 0 10px 5px 10px;">
									<strong><span id="warningText">Holy guacamole!</span>
									</strong>
								</div>

								<form id="findPassForm" action="" method="post" class="form-horizontal">
									<div class="row-fluid">
										<div class="span1"></div>
										<div class="span11">
											<h4>&nbsp;&nbsp;&nbsp;&nbsp;要重置您的密码，请输入与您的账户关联的电子邮箱地址。</h4>
										</div>
										
									</div>
									<br/>
									<div class="row-fluid">
										<div class="span4">
											<div class="control-group">
												<label class="control-label">关联邮箱： </label>
												<div class="controls">
													<input
														class="input w370 validate[custom[email]]"
														type="text" id="email" name="email"
														placeholder="请输入与您账号关联的邮箱地址" />
												</div>
											</div>
										</div>
										
										<div class="span8">
											<div class="control-group">
												<div class="controls">
													<button id="findPassButton" type="button"
														class="btn btn-primary">发送</button>
												</div>
											</div>
										</div>
									</div>

								</form>
								
								<br/><br/><br/><br/>
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
	<script type="text/javascript" src="${SITE.static_context}/js/back_pass.js"></script>
	
	<script>
		$(function() {
			if (top.location != document.location.href) {
				top.location = document.location.href;
			}
			module.backpass.findPassword();
		});
	</script>
	<!--// JS End -->
</body>
</html>

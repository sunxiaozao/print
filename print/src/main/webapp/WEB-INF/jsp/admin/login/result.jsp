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
<!-- <meta http-equiv="X-UA-Compatible" content="IE=IE9;IE=IE8"> -->
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
<link href="${SITE.static_context}/stylelib/theme_default/screen.css"
	rel="stylesheet" type="text/css" />
<link
	href="${SITE.static_context}/stylelib/theme_default/my_responsive.css"
	rel="stylesheet" type="text/css" />

<!-- // 全局 css -->
<link href="${SITE.static_context}/css/global.css" rel="stylesheet"
	type="text/css" />


<link
	href="${SITE.static_context}/stylelib/plugins/datepicker/datepicker.css"
	rel="stylesheet" type="text/css" />



</head>
<body>
	<!--//main-menu:end-->
	<div id="main-content">
		<div class="container-fluid">
			<div class="container-fluid form-box">
				<div class="form-box-title"></div>
				<div style="height: 503px">
					<div class="alert alert-block alert-success">
						<h4>注册成功!</h4>
						<p>
							将在<span id="result">5</span>秒后返回登录页面  <a href="javascript:return return_();">立即返回</a>
						</p>
					</div>
					<!--//alert:end-->
				</div>
			</div>
		</div>
	</div>
	<!--//main-content:end-->


	<!--// Loading JS -->
	<script src="${SITE.static_context}/stylelib/jquery-1.10.2.min.js"></script>
	<script src="${SITE.static_context}/stylelib/base.js"></script>
	<script
		src="${SITE.static_context}/stylelib/plugins/iealert/iealert.js"></script>
	<script
		src="${SITE.static_context}/stylelib/plugins/jquery.nicescroll.min.js"></script>

	<!--common script for all pages-->
	<script src="${SITE.static_context}/stylelib/theme_default/global.js"></script>

	<!-- // modal  -->
	<script src="${SITE.static_context}/stylelib/plugins/sco.modal.js"></script>
	<!-- confirm js -->
	<script src="${SITE.static_context}/stylelib/plugins/sco.confirm.js"></script>

	<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
          <script src="${SITE.static_context}/stylelib/plugins/jquery.placeholder.min.js"></script>
        <![endif]-->

	<!-- 时间控件，应该都是用的这个 -->










	<!--set context -->
	<script type="text/javascript">
		var SITE = {};
		SITE.context = "${SITE.static_context}";
		SITE.images_context = "${SITE.static_context}";
		SITE.static_context = "${SITE.static_context}";
		var module = {
			param : {}
		};
	</script>

	<script src="${SITE.static_context}/js/main.js"></script>
	<script src="${SITE.static_context}/js/admin.js"></script>
	<script>
	
		function return_(){
			self.parent.IframeModalCC(window.name, -1);
		}
	
	
		$(function() {
			var count = 4;
			setTimeout(function() {
				self.parent.IframeModalCC(window.name, -1);
			}, 5000);
			setInterval(function() {
				$("#result").html(count--);
			}, 1000);

		});
	</script>
	<!--// JS End -->
</body>
</html>
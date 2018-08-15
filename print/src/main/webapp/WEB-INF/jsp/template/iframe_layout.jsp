<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/pagestart.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html class="ie8"> <![endif]-->
<!--[if IE 9]> <html class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<head>
<title>夏宇科技微医通系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- // base css -->
<link href="${SITE.static_context}/stylelib/base.css" rel="stylesheet"
	type="text/css" />

<!-- // plugins css -->
<link
	href="${SITE.static_context}/stylelib/plugins/font-awesome/font-awesome.css"
	rel="stylesheet" type="text/css" />

<!-- // theme css -->
<link href="${SITE.static_context}/stylelib/theme_wblue/screen.css"
	rel="stylesheet" type="text/css" />
<!-- // 上传样式  -->
<link
	href="${SITE.static_context}/stylelib/plugins/bootstrap-fileupload/bootstrap-fileupload.css"
	rel="stylesheet" type="text/css" />
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
          <script src="${SITE.static_context}/${SITE.static_context}/${SITE.static_context}/stylelib/plugins/html5shiv.js"></script>
        <![endif]-->
</head>

<body class="m-iframe-body">
	<tiles:insertAttribute name="body" />



	<div class="mic-footer">
		<div class="m-emodal-footer">
			<button class="btn btn-mini btn-danger" id="reload" type="button">关闭</button>
		</div>
	</div>
	<!--// Loading JS -->
	<script src="${SITE.static_context}/stylelib/jquery-1.10.2.min.js"></script>
	<script src="${SITE.static_context}/stylelib/base.js"></script>
	<script
		src="${SITE.static_context}/stylelib/plugins/iealert/iealert.js"></script>
	<script
		src="${SITE.static_context}/stylelib/plugins/jquery.nicescroll.min.js"></script>
	<!--common script for all pages-->
	<script src="${SITE.static_context}/stylelib/theme_default/global.js"></script>
	<!-- 文件上传 -->
	<script
		src="${SITE.static_context}/stylelib/plugins/bootstrap-fileupload/bootstrap-fileupload.js"></script>
	<!--script for this page only-->
	<!--set context -->
	<script type="text/javascript">
		var SITE = {};
		SITE.context = "${SITE['context']}";
		SITE.images_context = "${SITE['images_context']}";
		SITE.static_context = "${SITE['static_context']}";
		var module = {
			param : {}
		};
	</script>

	<%--<!--全局 js -->--%>
	<script src="${SITE.static_context}/js/main.js"></script>
	<script src="${SITE.static_context}/js/admin.js"></script>

	<script>
		$(function() {
			module.menu.init();
			<c:if test="${not empty error}">
			module.common.showPopupWindow('${error}');
			</c:if>
		});
	</script>

	<tiles:insertAttribute name="script" />

	<script>
		//刷新父框架
		$("#reload").click(function() {
			self.parent.location.reload();
		});
	</script>
	<!--// JS End -->
</body>
</html>

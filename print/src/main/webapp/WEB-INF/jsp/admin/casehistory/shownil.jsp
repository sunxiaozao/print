<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>




<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>夏宇科技微医通系统</title>
	<link href="${SITE.static_context}/css/global.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		var SITE = {};
		SITE.context = "";
		SITE.images_context = "${SITE['images_context']}";
		SITE.static_context = "${SITE['static_context']}";
		var module = {
			param : {}
		};
	</script>
</head>
<body>
<iframe src="${url}" scrolling="no" frameborder="0" height="100%" id="mainFrame" width="100%" onload='IFrameReSize("mainFrame");'></iframe>
	<script src="${SITE.static_context}/stylelib/jquery-1.10.2.min.js"></script>
	<!-- 
	<script src="${SITE.static_context}/js/main.js"></script>
	<script src="${SITE.static_context}/js/casehistory.js"></script>
	 -->
	<script type="text/javascript">
		$(function() {
			//module.casehistory.showProgress();
			window.onresize=function(){  
				IFrameReSize("mainFrame");  
	       }  ;
		});
		
		function IFrameReSize(iframename) {
			var pTar = document.getElementById(iframename);

			if (pTar) { 
				pTar.height = document.documentElement.clientHeight-21;
			}

		}
	</script>

</body>
</html>
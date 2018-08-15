<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/pagestart.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html class="ie8"> <![endif]-->
<!--[if IE 9]> <html class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html> <!--<![endif]-->
	<head>
		<title>夏宇科技微医通系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- <meta http-equiv="X-UA-Compatible" content="IE=IE9;IE=IE8"> -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                
        <!-- // base css -->
        <link href="${SITE.static_context}/stylelib/base.css" rel="stylesheet" type="text/css" />
        <link href="${SITE.static_context}/stylelib/responsive.css" rel="stylesheet" type="text/css" />
                        
        <!-- // plugins css -->
        <link href="${SITE.static_context}/stylelib/plugins/font-awesome/font-awesome.css" rel="stylesheet" type="text/css" />
        <link href="${SITE.static_context}/stylelib/plugins/iealert/style.css" rel="stylesheet" type="text/css" />
        
        <!-- // theme css -->
        <link href="${SITE.static_context}/stylelib/theme_default/screen.css" rel="stylesheet" type="text/css" />
        <link href="${SITE.static_context}/stylelib/theme_default/my_responsive.css" rel="stylesheet" type="text/css" />
        
        <!-- // 全局 css -->
		<link href="${SITE.static_context}/css/global.css" rel="stylesheet" type="text/css" />
		
		<!-- // 上传样式  -->
		<link href="${SITE.static_context}/stylelib/plugins/bootstrap-fileupload/bootstrap-fileupload.css" rel="stylesheet" type="text/css" />
        <!-- // 时间控件样式 -->
        <link href="${SITE.static_context}/stylelib/plugins/datepicker/datepicker.css" rel="stylesheet" type="text/css" />
        
     	<!-- 全局css样式 -->
		<link href="${SITE.static_context}/css/3rd/validationEngine.jquery.css" rel="stylesheet" type="text/css" />
	    
	</head>
	<body>
		<div id="notify-body" style="display: none;"></div>
		
		<tiles:insertAttribute name="head" />
		<div class="space"></div>
            <div class="container-fluid">
				<tiles:insertAttribute name="body" />
            </div>
		<!--// Loading JS -->
		<script src="${SITE.static_context}/stylelib/jquery-1.10.2.min.js"></script>
        <script src="${SITE.static_context}/stylelib/base.js"></script>
        <script src="${SITE.static_context}/stylelib/plugins/iealert/iealert.js"></script>
        <script src="${SITE.static_context}/stylelib/plugins/jquery.nicescroll.min.js"></script>   
             
        <!--common script for all pages-->
        <script src="${SITE.static_context}/stylelib/theme_default/global.js"></script>
                		
		<%-- <!-- 增强型模态对话框 --> --%> 
		<!-- // modal  -->
        <script src="${SITE.static_context}/stylelib/plugins/sco.modal.js"></script>
        <%-- <!-- 确认框 -->  --%> 
        <!-- confirm js -->
        <script src="${SITE.static_context}/stylelib/plugins/sco.confirm.js"></script>
		
		<%-- <!-- 输入框中的提示文字在IE下无法显示，提供此控件 -->  --%>
		<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
          <script src="${SITE.static_context}/stylelib/plugins/jquery.placeholder.min.js"></script>
        <![endif]-->
                
        <%-- <!--钟面，未使用-->
        <script src="${SITE.static_context}/stylelib/plugins/clockface/clockface.js"></script> --%>
        
        <%-- <!-- 在文本域里增加或减少tab   tab input -->
        <script src="${SITE.static_context}/stylelib/plugins/tags-input/jquery.tagsinput.js"></script> --%>
        
        <!-- 时间控件，应该都是用的这个 -->
        <script src="${SITE.static_context}/stylelib/plugins/datepicker/bootstrap-datepicker.js"></script>
    
        <!-- 时间范围选择 控件  应该要两个一起用 -->
        <script src="${SITE.static_context}/stylelib/plugins/daterangepicker/date.js"></script> 
        <script src="${SITE.static_context}/stylelib/plugins/daterangepicker/daterangepicker.js"></script>
        
        <!--删除后，时间选择 和颜色选择 都没法弹出选择框-->
        <script src="${SITE.static_context}/stylelib/plugins/colorpicker/bootstrap-colorpicker.js"></script>
        <script src="${SITE.static_context}/stylelib/plugins/chosen/chosen.jquery.js"></script>    
        
        <!-- 时间 -->
        <script src="${SITE.static_context}/stylelib/plugins/timepicker/bootstrap-timepicker.min.js"></script>
        
        <!-- 格式化输入，如电话 -->
        <script src="${SITE.static_context}/stylelib/plugins/bootstrap-inputmask.min.js"></script>
  
        <!-- 文件上传 -->
        <script src="${SITE.static_context}/stylelib/plugins/bootstrap-fileupload/bootstrap-fileupload.js"></script>
	    
	    <!--在页面顶部显示提醒通知消息  -->
        <script src="${SITE.static_context}/js/3rd/jquery.topbar.js"></script>
        <script src="${SITE.static_context}/js/3rd/jquery.md5.js"></script>
                        
        <!--ajax file upload  -->
        <script src="${SITE.static_context}/js/3rd/ajaxfileupload-2.1.js"></script>
        
        <!--页面段验证插件  -->        
        <script src="${SITE.static_context}/js/3rd/jquery.validationEngine.js"></script>
        <script src="${SITE.static_context}/js/3rd/jquery.validationEngine-zh_CN.js"></script>
                        
        <!--数字和货币format  -->
        <script src="${SITE.static_context}/js/3rd/jshashtable-2.1.js"></script>
        <script src="${SITE.static_context}/js/3rd/jquery.numberformatter-1.2.3.min.js"></script>
        <script src="${SITE.static_context}/js/3rd/jquery.formatCurrency-1.4.0.min.js"></script>
        <script src="${SITE.static_context}/js/3rd/jquery.formatCurrency.zh-CN.js"></script>
        
        <!--set context -->
        <script type="text/javascript">
			var SITE={}; SITE.context="${SITE['context']}"; SITE.images_context="${SITE['images_context']}";  SITE.static_context="${SITE['static_context']}";
			var module={param:{}};
		</script>
		
		<%--<!--全局 js -->--%>
		<script src="${SITE.static_context}/js/main.js"></script>
		<script src="${SITE.static_context}/js/admin.js"></script>
		
		<script>
		$(function(){
			module.menu.init();
			<c:if test="${not empty error}">
				module.common.showPopupWindow('${error}');
			</c:if>
		});
		</script>
		
		<tiles:insertAttribute name="script" />
        <!--// JS End -->
	</body>
</html>
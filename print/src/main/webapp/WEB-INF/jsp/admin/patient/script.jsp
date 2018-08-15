<script src="${SITE.static_context}/js/user.js"></script>
<%@ page language="java" pageEncoding="UTF-8"%>
<script>
	$(function() {

		//查询页面
		module.clearForm.init();
		module.common.datepicker();
				
		//编辑页面
		$("#userEdit").validationEngine();
		
	});
</script>
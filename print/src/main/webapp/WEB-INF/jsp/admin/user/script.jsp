<script src="${SITE.static_context}/js/user.js"></script>
<%@ page language="java" pageEncoding="UTF-8"%>
<script>
	$(function() {

		
		//查询页面
		module.clearForm.init();
		module.common.datepicker();

		module.user.choice();

		$.validationEngineLanguage.allRules.ajaxUserName.url = SITE.context
				+ "/admin/checkusernameonly";//验证用户名是否重复
		$.validationEngineLanguage.allRules.ajaxEmail.url = SITE.context
				+ "/admin/checkuseronly/E";//验证Email是否重复
		$.validationEngineLanguage.allRules.ajaxMobile.url = SITE.context
				+ "/admin/checkuseronly/M";//验证手机号是否重复
		$.validationEngineLanguage.allRules.ajaxIc.url = SITE.context
				+ "/admin/checkuseronly/I";//验证IC/ID是否重复
		//新增页面
		$("#userAdd").validationEngine();
				
		//编辑页面
		$("#userEdit").validationEngine();
		
		if('${type}'=='S'||'${type}'=='D'){
			module.user.init_('${hospital}', '${department}');
		}
		
	});
</script>
<script src="${SITE.static_context}/js/doctor.js"></script>
<%@ page language="java" pageEncoding="UTF-8"%>
<script>
	$(function() {

		$.validationEngineLanguage.allRules.ajaxUserName.url = SITE.context
				+ "/admin/checkusernameonly";//验证用户名是否重复
		$.validationEngineLanguage.allRules.ajaxIc.url = SITE.context
				+ "/admin/checkuseronly/I";//验证IC/ID是否重复

		$.validationEngineLanguage.allRules.ajaxEmail.url = SITE.context
				+ "/admin/checkuseronly/E";//验证Email是否重复
		$.validationEngineLanguage.allRules.ajaxMobile.url = SITE.context
				+ "/admin/checkuseronly/M";//验证手机号是否重复
		//查询页面
		module.clearForm.init();
		module.common.datepicker();
		module.doctor.checkType();
		module.doctor.ic();
		module.doctor.email();
		module.doctor.mobile();
		//新增页面
		$("#doctorAdd").validationEngine();
		//编辑页面
		$("#doctorEdit").validationEngine();
	});
</script>
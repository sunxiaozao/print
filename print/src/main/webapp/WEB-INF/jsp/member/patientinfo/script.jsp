<%@ page language="java" pageEncoding="UTF-8"%>
<script src="${SITE.static_context}/js/patientinfo.js"></script>
<script>
	$.validationEngineLanguage.allRules.ajaxCheckcardid = {
		url : SITE.context + "/member/checkcardid",
		extraData : 'name=eric',
		alertTextOk : '医保卡号可以使用',
		alertText : '医保卡号已被使用',
		alertTextLoad : ''
	};
	$(function() {
		$.validationEngineLanguage.allRules.ajaxUserName.url = SITE.context
				+ "/admin/checkusernameonly";//验证用户名是否重复
		$.validationEngineLanguage.allRules.ajaxIc.url = SITE.context
				+ "/admin/checkuseronly/I";//验证IC/ID是否重复

		$.validationEngineLanguage.allRules.ajaxEmail.url = SITE.context
				+ "/admin/checkuseronly/E";//验证Email是否重复
		$.validationEngineLanguage.allRules.ajaxMobile.url = SITE.context
				+ "/admin/checkuseronly/M";//验证手机号是否重复
		
		$("#patientEdit").validationEngine();
	
		module.common.datepicker();

	});
</script>

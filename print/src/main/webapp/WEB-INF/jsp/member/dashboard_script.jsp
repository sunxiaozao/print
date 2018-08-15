<%@ page language="java" pageEncoding="UTF-8"%>
<script src="${SITE.static_context}/js/member.js"></script>
<script src="${SITE.static_context}/js/casehistory.js"></scrip>
<script>
	$(function() {
		//查询页面
		module.clearForm.init();
		//分享方式
		module.member.shareType();
		//分享
		module.member.saveShare();
	});
</script>

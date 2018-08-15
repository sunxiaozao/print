<script src="${SITE.static_context}/stylelib/plugins/bootstrap-fileupload/fileupload-a.js"></script>
<script src="${SITE.static_context}/js/casehistory.js"></script>
<script src="${SITE.static_context}/js/member.js"></script>
<%@ page language="java" pageEncoding="UTF-8"%>
<script>
$(function() {
	

	//查询页面
	module.clearForm.init();
	module.common.datepicker();	
	//新增页面
	
	$("#casehistoryAdd").validationEngine();
	
	//分享方式
	module.member.shareType();
	//分享
	module.member.saveShare();
	module.casehistory.checkAll();	
	module.casehistory.relation();	
	module.casehistory.temporary_relation();	
	
});

</script>
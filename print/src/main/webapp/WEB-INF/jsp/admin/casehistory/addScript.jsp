<script src="${SITE.static_context}/stylelib/plugins/bootstrap-fileupload/fileupload-a.js"></script>
<script src="${SITE.static_context}/js/casehistory.js"></script>
<%@ page language="java" pageEncoding="UTF-8"%>
<script>
$(function() {
	//查询页面
	module.clearForm.init();
	module.common.datepicker();	
	//新增页面
	
	$("#casehistoryAdd").validationEngine();
	var type='${type}';
	if(type=="add"){
		module.casehistory.init();
	}else{
		var arr=${array};
		module.casehistory.update(arr);
	}
	
});
</script>
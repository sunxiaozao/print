
<%@ page language="java" pageEncoding="UTF-8"%>
<script>
$(function() {
	//查询页面
	module.clearForm.init();
	module.common.datepicker();	
	//新增页面
	$("#hospitalAdd").validationEngine();
	//编辑页面
	$("#hospitalEdit").validationEngine();
	$("#hospitalEditSubmit").on('click',function() {
		if(!$("#hospitalEdit").validationEngine('validate'))return false;
		$.post(SITE.context + "/admin/hospital/saveEdit", $("#hospitalEdit").serializeArray(),
			function(resp) {
				if(resp.success==true) {
					module.common.showPopupWindow("保存成功");
				}
				else {
					module.common.showPopupWindow("保存失败");
				}
			}
		);
	})
});
</script>
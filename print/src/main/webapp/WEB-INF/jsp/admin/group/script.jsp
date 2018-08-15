<%@ page language="java" pageEncoding="UTF-8"%>
<script>
$(function() {
	//查询页面
	module.clearForm.init();
	//新增页面
	$("#groupAdd").validationEngine();
	//编辑页面
	$("#groupEdit").validationEngine();	
	$("#groupEditSubmit").on('click',function() {
		if(!$("#groupEdit").validationEngine('validate'))return false;
		$.post(SITE.context + "/admin/group/saveEdit", $("#groupEdit").serializeArray(),
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
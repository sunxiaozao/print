<%@ page language="java" pageEncoding="UTF-8"%>
<script>
$(function() {
	//查询页面
	module.clearForm.init();
	//新增页面
	$("#orgAdd").validationEngine();
	//编辑页面
	$("#orgEdit").validationEngine();	
	$("#orgEditSubmit").on('click',function() {
		if(!$("#orgEdit").validationEngine('validate'))return false;
		$.post(SITE.context + "/admin/org/saveEdit", $("#orgEdit").serializeArray(),
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
<%@ page language="java" pageEncoding="UTF-8"%>
<script>
$(function() {
	//查询页面
	module.clearForm.init();
	//新增页面
	$("#dictAdd").validationEngine();
	$('#shortCode').on('blur', function(){
		var shortCode = $(this).val().trim() ;
		if(shortCode == '')return;
		$.post(SITE.context + "/admin/dict/checkShortCode" ,{shortCode: shortCode} ,
			function(resp) {
				if(resp.success==true) {
					$('#notifyMsg').html("<font color='red'>数据代码重复，请重新输入</font>") ;
					//$('#shortCode').focus() ;
				}
				else {
					$('#notifyMsg').html("") ;
				}
			}
		) ;
	}) ;
	$("#dictAdd").submit(function(){
		if($('#notifyMsg').html()){
			return false;
		}
		return true;
	});
	//编辑页面
	$("#dictEdit").validationEngine();	
	$("#dictEditSubmit").on('click',function() {
		if(!$("#dictEdit").validationEngine('validate'))return false;
		$.post(SITE.context + "/admin/dict/saveEdit", $("#dictEdit").serializeArray(),
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
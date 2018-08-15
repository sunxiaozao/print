<%@ page language="java" pageEncoding="UTF-8"%>
<script>
$(function() {
	//查询页面
	module.clearForm.init();
	module.common.datepicker();
	$('a[name="publishV"]').on("click",function(){
		var cLink = $(this);
		$( "#dialog-confirm-publish" ).dialog(
		{
			resizable: false,
			height:140,
			modal: true,
			buttons: {
				"确认": function() {
					module.main.showProgress();
					$.post(SITE.context + "/admin/message/publish", {messageId:cLink.attr("mId")} ,
						function(resp) {
							if(resp.success==true) {
								doSearch();
							}
							module.main.hideProgress();
						},
					"json") ;
					$(this).dialog("close") ;
				},
				"取消": function() {
					$(this).dialog( "close" );
				}
			}
		});
	});
	//新增页面
	$("#messageAdd").validationEngine();
	//编辑页面
	$("#messageEdit").validationEngine();	
	$("#messageEditSubmit").on('click',function() {
		if(!$("#messageEdit").validationEngine('validate'))return false;
		$.post(SITE.context + "/admin/message/saveEdit", $("#messageEdit").serializeArray(),
			function(resp) {
				if(resp.success==true) {
					module.common.showPopupWindow("保存成功");
				}
				else {
					module.common.showPopupWindow("保存失败");
				}
			}
		);
	});
	function getUserType(){
		var userType=document.getElementById("#userType");
		
		if($("#userType").val()=="A"){
			document.getElementById("orgName").style.display="none";
			document.getElementById("groupName").style.display="none";
			document.getElementById("memberName").style.display="none";
		}else if($("#userType").val()=="B"){
			document.getElementById("orgName").style.display="";
			document.getElementById("groupName").style.display="none";
			document.getElementById("memberName").style.display="none";
		}else if($("#userType").val()=="C"){
			document.getElementById("groupName").style.display="";
			document.getElementById("orgName").style.display="none";
			document.getElementById("memberName").style.display="none";
		}else if($("#userType").val()=="D"){
			document.getElementById("memberName").style.display="";
			document.getElementById("orgName").style.display="none";
			document.getElementById("groupName").style.display="none";
		}
				
	}
});
</script>
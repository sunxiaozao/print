
module.userList = {
	init:function(){
		$('a[name="changePass"]').on("click",function(){
			var cLink = $(this);
			var userId = cLink.attr('userId');
			var userName = cLink.attr('userName');
			$("#myModalLabel").html('修改密码('+userName+')');
			$("#password").val('');
			$("#confirmPassword").val('');
			$('#changPassDiv').modal("show");
			$("#savePassButton").on('click', function(){
				var pass1 = $("#password").val();
				var pass2 = $("#confirmPassword").val();
				if(!$("#updatePassForm").validationEngine('validate'))return false;
				if(pass1 != pass2){
					module.common.showPopupWindow("确认密码不一致");
					$("#confirmPassword").focus();
					return false;
				}
				$.post(SITE.context+"/admin/user/changeUserPass", {userId: userId, password:$.md5(pass1), confirmPassword:$.md5(pass2)}, function(resp){
	            	if(resp.success===true){
	            		module.common.showPopupWindow("密码修改成功");
	            		$('#changPassDiv').modal("hide");
	            	}
	            	else{
						module.common.showPopupWindow("密码修改失败"+resp.desc);
					}
	            }, "json");
			});			
		});
	}
};

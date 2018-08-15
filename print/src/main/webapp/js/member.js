module.member = {
	simulateOfSelf : function() {
		module.common.showProgress();
		$.post(SITE.context + "/member/simulate/0", function(resp) {
			if (resp.success === true) {
				
					window.location.href = SITE.context + "/member/index";
				
			} else {
				module.common.hideProgress();
				$('#warningText').html(resp.error);
				$(".alert").show();
				return false;
			}
		}, "json");
	},
	simulate:function(userName,password){
		module.common.showProgress();
        $.post(SITE.context+"/member/simulate/1", 
        		{ userName: userName, password: password}, function(resp){
        	if(resp.success===true){
        		
        			window.location.href = SITE.context
					+ "/member/distribution";
        		
			}else{
				module.common.hideProgress();
				$('#warningText').html(resp.error);
				$(".alert").show();
				return false;
			}
        }, "json"); 
	},
	updPassSelectCli : function() {
		$(document).on("click", 'input[name="passInput"]', function() {
			module.member.updPassSelectVal();
		});
	},
	updPassSelectVal : function() {//修改被选中的需要通过的关注请求
		var selectValue="";
		var passInput = $('input[name="passInput"]');// 复选框

		for ( var inti = 0; inti < passInput.length; inti++) {
			if (passInput[inti].checked == true) {
				selectValue+=passInput[inti].value+",";
			}
		}
		$("#passSelectVals").val(selectValue);
		$("#passId").attr("href",SITE.context+"/member/relation/savePass/"+selectValue);
	},
	checkRelation : function() {// 修改账号的类型
		$(document).on("click", "#checkRelation", function() {
			var typeVal= $("#type").val();
			var userName= $("#userName").val();

			$.post(SITE.context + "/member/checkRelation",{"typeVal":typeVal,"userName":userName },
				function(resp) {
					if(resp.success==true) {
						module.common.showPopupWindow(resp.data.msg);
						$("#relatePatientId").attr("value",resp.data.relatePatientId);
					}else {
						module.common.showPopupWindow("非常抱歉，您暂时不能关注此用户！");
					}
				}
			);
			
			/*$.ajax({
				cache : true,
				type : "POST",
				async : true,
				data : {typeVal:typeVal,userName:userName },
				url : SITE.context + "/member/checkRelation",
				success : function(resp) {
					module.common.showPopupWindow(resp.data.msg);
				}
			});	*/
			
		});
	},
	changeTypeVal : function() {//修改账号的类型
		$(document).on("change","#type", function(){
			var typeVal=$("#type").val();
			$("#typeVal").attr("value",typeVal);
		});
	},
	saveAddRelation : function() {//添加关注
		$(document).on("click","#addRelationSubmit", function(){
			var relatePatientId=$("#relatePatientId").val();
			if (relatePatientId!=null && relatePatientId!="") {
				
				$("#addRelation").validationEngine();
				if(!$("#addRelation").validationEngine('validate'))return false;
				$.post(SITE.context + "/member/relation/saveAdd", $("#addRelation").serializeArray(),
					function(resp) {
						if(resp.success==true) {
							//module.common.showPopupWindow("保存成功");
							window.location.href=SITE.context+"/member/relation/list?add=Y";
						}else {
							module.common.showPopupWindow("保存失败");
						}
					}
				);
				
			}else{
				module.common.showPopupWindow("请先检查是否已关注过此用户！");
				return false;
			}
		});
	},
	shareType : function() {
		var email=$("#email").val();//邮箱
		var sms=$("#mobile").val();//短信
		if (email != "" && sms=="") {
			$("#emails").removeClass().addClass("input w370 validate[required,custom[email]]");
			$("#mobileDiv").html('<input type="text" id="mobile" name="mobile" value="" class="">');
		}
		if(sms != "" && email == ""){//邮箱为空，短信不为空，短信进行验证，并删除邮箱验证
			$("#mobile").removeClass().addClass("input w370 validate[required,custom[phone]]");
			$("#emailsDiv").html('<input type="text" id="email" name="email" value="" class=""><span class="help-inline">多个邮箱请用英文逗号(,)隔开</span>');
		}
		if(email == "" && sms == ""){//两者都为空时，两者都要进行验证
			$("#emails").removeClass().addClass("input w370 validate[required,custom[email]]");
			$("#mobile").removeClass().addClass("input w370 validate[required,custom[phone]]");
		}
		/*$(document).on("click",'input[name="typeName"]', function(){
			var typeId=$("#typeId");
			var emailId=$("#emailId");//复选框邮箱
			var smsId=$("#sms");//复选框短信
			//var email=$("#email").val();//用户输入邮箱
			
			if (emailId.is(':checked')==true) {//复选框id
				typeId.attr("value","A");
				emailId.attr("checked","checked");
				$("#emails").removeClass().addClass("input w370 validate[required,custom[email]]");
				//$("#mobile").removeClass();
				$("#mobileDiv").html('<input type="text" id="mobile" name="mobile" value="" class="">');
			}
			if(smsId.is(':checked')==true){
				typeId.attr("value","B");
				smsId.attr("checked","checked");
				$("#mobile").removeClass().addClass("input w370 validate[required,custom[phone]]");
				//$("#emails").removeClass();
				$("#emailsDiv").html('<input type="text" id="email" name="email" value="" class=""><span class="help-inline">多个邮箱请用英文逗号(,)隔开</span>');
			}
			if(smsId.is(':checked')==true && emailId.is(':checked')==true){
				typeId.attr("value","C");
				$("#emails").removeClass().addClass("input w370 validate[required,custom[email]]");
				$("#mobile").removeClass().addClass("input w370 validate[required,custom[phone]]");
			}
		});		*/	
	},
	saveShare : function() {
		$(document).on("click","#shareSubmit", function(){
			module.member.shareType();
			$("#share").validationEngine();
			if(!$("#share").validationEngine('validate'))return false;
			$.post(SITE.context + "/member/saveShare", $("#share").serializeArray(),
				function(resp) {
					if(resp.success==true) {
						//module.common.showPopupWindow("保存成功");
						window.location.href=SITE.context+"/member/share/list?share=Y&mobile="+$("#mobile").val()+"&email="+$("#email").val();
					}else {
						if(resp.data.msg!=''){
							module.common.showPopupWindow(resp.data.msg);
						}else{
							module.common.showPopupWindow("保存失败");
						}
					}
				}
			);
		});
	}
	

};

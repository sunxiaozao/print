module.doctor = {
	/**
	 * 判断复选框是否选中
	 * 
	 * @param name
	 * @returns {Boolean}
	 */
	isChecked : function(name) {
		var arr_v = new Array();
		$("input[name='" + name + "']:checked").each(function() {
			arr_v.push(this.value);
		});
		var ids = arr_v.join(',');
		return ids != '';
	},
	checkType : function() {
		$("#type").on("change",function() {
			var id;
			var class_;
			var key = $("#type").val();
			switch (key) {
							case "E":
								id = "email";
								class_ = "validate[required,custom[email],ajax[ajaxEmail]] text-input";
								break;

							case "M":
								id = "mobile";
								class_ = "validate[required,custom[phone],ajax[ajaxMobile]] text-input";
								break;
							case "I":
								id = "ic";
								class_ = "validate[required,ajax[ajaxIc]] text-input";
								break;
							}
							$("#ic_email_mobile").html(
									'<input type="text" id="' + id
											+ '" name="user" class="' + class_
											+ '">');
						});
	},
	ic : function() {
		$("#ic").on("change", function() {
			$("#ic").attr("class", "validate[ajax[ajaxIc]] text-input");
		});
	},
	email : function() {
		$("#email").on("change",
			function() {
				$("#email").attr("class","validate[custom[email],ajax[ajaxEmail]] text-input");
		});
	},
	mobile : function() {
		$("#mobile").on("change",function() {
			$("#mobile").attr("class","validate[custom[phone]],ajax[ajaxMobile]] text-input");
		});
	},
	saveCollect : function(caseId,caseType) {
		$.post(SITE.context + "/doctor/saveCollect",{caseId:caseId,caseType:caseType}
				,function(resp) {
					if(resp.success==true) {
						//module.common.showPopupWindow("保存成功");
						window.location.href=SITE.context+"/doctor/collect/list?add=Y";
					}else {
						module.common.showPopupWindow("收藏失败！");
					}
				}
		)
	},
	searchCollect:function(){
		$("#vague").on("click",function() {//检索
			$("#vague").attr("type","submit");
			$("#myForm").attr("action",SITE.context+"/doctor/collect/list?sign=ne");
		});
		$("#high").on("click",function() {//高精尖检索
			$("#high").attr("type","submit");
			$("#myForm").attr("action",SITE.context+"/doctor/collect/list?sign=eq");
		});
	}
	
}

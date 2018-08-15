module.folder = {
	changeHospital : function() {// 添加病例资料是选择医院修改科室
		$('#hospital').on('change', function() {
			var text = $('#hospital').val();
			
				module.folder.loadDepartment(text);
			
			
			
		});
	},
	loadDepartment : function(hospitalid, department) {// 加载科室
		var url = SITE.context + "/member/casehistory/";
		$("#departmentId").empty();
		$
				.post(
						url + "department/" + hospitalid,
						function(resp) {
							if (resp.success == true) {
								$
										.each(
												resp.data.list,
												function(i, item) {
													if (hospitalid !== null
															&& department !== undefined
															&& department == item.id) {
														var $optc = $("<option selected=\"selected\"></option>");
													} else {
														var $optc = $("<option></option>");
													}
													$optc.val(item.id);
													$optc
															.text(item.departmentName);
													$optc
															.appendTo("#departmentId");
												});
							} else {
								return false;
							}

						}, "json");

	},
	isNNull : function() {
		var arr_v = new Array();
		$("input[name='ids']:checked").each(function() {
			arr_v.push(this.value);
		});
		var ids = arr_v.join(',');

		if (ids != '') {

			return true;
		} else {
			module.common.showPopupWindow('请选择要授权的医生!');
			return false;
		}

	},
	show:function(){
		$(".navs").find("li:has(ul)").children("a")
		.click(function(){
			if($(this).next("ul").is(":hidden")){
				$('#iHidden').removeClass("icon-caret-right");
				$('#iHidden').addClass("icon-caret-down");
				$(this).next("ul").slideDown("slow");
				if($(this).parent("li").siblings("li").children("ul").is(":visible")){
					$(this).parent("li").siblings("li").find("ul").slideUp("1000");
					$(this).parent("li").siblings("li:has(ul)").children("a").css({background:"url(images/statu_close.gif) no-repeat left top;"})
					.end().find("li:has(ul)").children("a").css({background:"url(images/statu_close.gif) no-repeat left top;"});
					}
					$(this).css({background:"url(images/statu_open.gif) no-repeat left top;"});
					return true;
			}else{
				$('#iHidden').removeClass("icon-caret-down");
				$('#iHidden').addClass("icon-caret-right");
				$(this).next("ul").slideUp("normal");
				// 不用toggle()的原因是为了在收缩菜单的时候同时也将该菜单的下级菜单以后的所有元素都隐藏
				$(this).css({background:"url(images/statu_close.gif) no-repeat left top;"});
				$(this).next("ul").children("li").find("ul").fadeOut("normal");
				$(this).next("ul").find("li:has(ul)").children("a").css({background:"url(images/statu_close.gif) no-repeat left top;"});
				return false;
			}
		});
	},deleteFolder:function(){
		$("#deleteFolder").on('click',function() { 
				var arr_v = new Array();
				$("input[name='boxFolder']:checked").each(function() {
					arr_v.push(this.value);
				});
				var selectFolderValue = arr_v.join(',');
				if(selectFolderValue!=""){
					if(confirm("是否确认删除！")){
						$.post(SITE.context + "/member/folder/deleteFolderBox", {"selectFolderValue":selectFolderValue}, function(resp) {
								if (resp.success == true) {
									window.location.href=SITE.context
									+"/member/folderShow/list?deleteFolder=Y";
								} else {
									module.common.showPopupWindow(resp.data.msg);
								}
							});
					}
				}else{
					module.common.showPopupWindow('请选择要删除的病历夹!');
				}
				
		});
		
	}
	
	
};

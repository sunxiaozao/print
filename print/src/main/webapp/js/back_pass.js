module.backpass = {
	// 找回密码 密码输入页面
	saveBackPass : function() {
		var login_form = $('#passForm');
		$("#passForm").validate({
			rules : {
				inputPassword : {
					required : true,
					minlength : 6
				},
				againPassword : {
					required : true,
					minlength : 6
				}
			},
			messages : {
				inputPassword : {
					required : "请输入密码",
					minlength : "密码至少6个字符"
				},
				againPassword : {
					required : "请输入密码",
					minlength : "密码至少6个字符"
				}
			}
		});
		$('#passButton').on('click', function() {
			var status = login_form.valid();

			debugger;

			var inputPassword = $.md5($("#inputPassword").val());
			var againPassword = $.md5($("#againPassword").val());
			if (inputPassword != againPassword) {
				module.common.showPopupWindow('密码输入不一致，请重新输入');
				return false;
			}

			if (status) {
				$.post(SITE.context + "/admin/updPass", {
					inputPassword : inputPassword,
					againPassword : againPassword,
					email : $("#email").val()
				}, function(resp) {
					if (resp.success == true) {
						module.common.showPopupWindow('密码修改成功');
						window.location.href = SITE.context + "/admin/index";
					} else {
						module.common.hideProgress();
						$('#warningText').html(resp.error);
						$(".alert").show();
						return false;
					}
				}, "json");
			}
			return false;
		});
	},
	// 找回密码 邮箱发送页面
	findPassword : function() {
		var login_form = $('#findPassForm');
		$("#findPassForm").validate({
			rules : {
				email : {
					required : true,
					email : true
				}
			},
			messages : {
				email : {
					required : "请输入关联邮箱",
					email : "请检查邮箱格式"
				}
			}
		});

		$('#findPassButton').on('click', function() {
			var status = login_form.valid();
			var email = $("#email").val();

			if (status) {
				// module.common.showProgress();
				$.post(SITE.context + "/admin/checkFindPass", {
					email : email
				}, function(resp) {
					if (resp.success == true) {
						if (resp.data.msg == "0") {
							module.common.showPopupWindow("该邮箱不存在，请检查邮箱地址是否正确！");
						} else {
							// module.common.sendEmail($("#email").val());
							// window.location.href=SITE.context+"/admin/sendEmail?email="+email;
							$.post(SITE.context + "/admin/sendEmail", {
								"email" : email
							}, function(resp) {
								if (resp.success == true) {
									module.common.showPopupWindow("发送成功");
									// window.location.href = SITE.context+ "/admin/backpassword";
								} else {
									if (resp.data.msg != '') {
										module.common.showPopupWindow(resp.data.msg);
									} else {
										module.common.showPopupWindow("发送失败");
									}
								}
							});
						}
					} else {
						module.common.hideProgress();
						$('#warningText').html(resp.error);
						$(".alert").show();
						return false;
					}
				}, "json");
			}
			return false;
		});
	},// 模拟登陆
	remoteDoctorInit : function() {
		var login_form = $('#loginRemoteForm');
		$("#loginRemoteForm").validate({
			rules : {
				inputPassword : {
					required : true,
					minlength : 6
				}
			},
			messages : {
				inputPassword : {
					required : "请输入密码",
					minlength : "密码至少6个字符"
				}
			}
		});
		$('#remoteButton').on('click', function() {
			var status = login_form.valid();

			var pass = $("#pass").val();
			var caseHistoryId = $("#caseHistoryId").val();
			var shareId = $("#shareId").val();

			if (status) {
				// module.common.showProgress();
				$.post(SITE.context + "/member/share/checkRemote", {
					caseHistoryId : caseHistoryId,
					inputPassword : $.md5($('#inputPassword').val()),
					pass : pass,
					shareId : shareId
				}, function(resp) {
					if (resp.success == true) {

						window.location.href = SITE.context + "/member/share/show?caseHistoryId=" + caseHistoryId + "&shareId=" + shareId + "&pass=" + pass;

					} else {
						module.common.showPopupWindow(resp.error);
						return false;
					}
				}, "json");
			}
			return false;
		});
	}
};

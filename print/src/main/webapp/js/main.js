module.constant = {
	cookie : {
		lastPage : 'lastPage',
		selectedMenu : 'selectedMenu'
	}
};

module.common = {
	datepicker : function() {
		$('.form_date').datepicker({
			language : 'zh-CN',
			format : 'yyyy-mm-dd',
			weekStart : 1,
			autoclose : 1,
			minView : 3,
			todayHighlight : 1,
			startView : 3,
			forceParse : 0
		});
	},
	showPopupWindow : function(content) {
		$('#notify-body').showTopbarMsg({
			message : content,
			close : 3000
		});
	},
	showProgress : function() {
		var main = module.common;
		if (main._progressBar) {
			main.progress.m.show();
		} else {
			main.progress = {};
			main.progress.m = $('<div class="progressBarBg" style=" z-index: 2001;"></div>');
			main.progress.loading = $('<div style="position:absolute;z-index:2002;"><img src="'
					+ SITE.images_context
					+ '/images/loading2.gif" width="100px" height="100px"/></div>');
			main.progress.loading.appendTo(main.progress.m);

			main.progress.m.height($(document).height());
			main.progress.m.width($(document).width());

			main.progress.m.appendTo(document.body);
			main._progressBar = true;
		}

		var x = ($(window).width() - 100) / 2;
		var y = ($(window).height() - 100) / 2;
		main.progress.loading.css('top', y);
		main.progress.loading.css('left', x);
	},
	hideProgress : function() {

		module.common.progress.m.hide();
	},
	_cookie : function(name, value, options) {
		if (typeof value != 'undefined') { // name and value given, set cookie
			options = options || {};
			if (value === null) {
				value = '';
				options.expires = -1;
			}
			var expires = '';
			if (options.expires
					&& (typeof options.expires == 'number' || options.expires.toUTCString)) {
				var date;
				if (typeof options.expires == 'number') {
					date = new Date();
					date.setTime(date.getTime()
							+ (options.expires * 24 * 60 * 60 * 1000));
				} else {
					date = options.expires;
				}
				expires = '; expires=' + date.toUTCString(); // use expires
				// attribute,
				// max-age is
				// not supported
				// by IE
			}
			// CAUTION: Needed to parenthesize options.path and options.domain
			// in the following expressions, otherwise they evaluate to
			// undefined
			// in the packed version for some reason...
			var path = options.path ? '; path=' + (options.path) : '';
			var domain = options.domain ? '; domain=' + (options.domain) : '';
			var secure = options.secure ? '; secure' : '';
			document.cookie = [ name, '=', encodeURIComponent(value), expires,
					path, domain, secure ].join('');
		} else { // only name given, get cookie
			var cookieValue = null;
			if (document.cookie && document.cookie != '') {
				var cookies = document.cookie.split(';');
				for ( var i = 0; i < cookies.length; i++) {
					var cookie = jQuery.trim(cookies[i]);
					// Does this cookie string begin with the name we want?
					if (cookie.substring(0, name.length + 1) == (name + '=')) {
						cookieValue = decodeURIComponent(cookie
								.substring(name.length + 1));
						break;
					}
				}
			}
			return cookieValue;
		}
	},
	cookie : function(name, value, days) {
		if (typeof value != 'undefined') {
			var expires = 90;
			if (days != 'undefined') {
				expires = days;
			}
			return module.common._cookie(name, value, {
				path : '/',
				expires : expires
			});
		} else {
			var v = module.common._cookie(name);
			if (v) {
				v = v.replace(/"/g, '');
			}
			return v;
		}
	},// 文件上传
	ajaxFileUpload : function(uploadUrl, fileToUploadId, callback) {
		debugger;
		module.common.showProgress();
		$.ajaxFileUpload({
			url : uploadUrl,
			secureuri : false,
			fileElementId : fileToUploadId,
			dataType : 'json',
			success : function(resp, status) {
				debugger;
				module.common.hideProgress();
				if (callback) {
					callback(resp.data.pathList);
				}
			},
			error : function(data, status, e) {
				module.common.hideProgress();
				if (callback) {
					callback();
				}
			}
		});
		return false;
	},// 文件下载
	upload : function(fileToUploadId, callback) {
		module.common.ajaxFileUpload(SITE.context + '/upload', fileToUploadId,
				callback);
	},// 给表单填充数据
	formSetValues : function(formId, data) {
		if (!data || $.isEmptyObject(data))
			return;
		$("#" + formId).find(":input").each(function(index, el) {
			var value = null;
			if ('undefined' != typeof (data[el.name])) {
				value = data[el.name];
			}

			if ('undefined' != typeof (value)) {
				switch (el.type) {
				case 'password':
				case 'select-multiple':
				case 'select-one':
				case 'text':
				case 'hidden':
				case 'textarea':
					$(el).val(value);
					break;
				case 'checkbox':
					this.checked = 1 == value ? 'checked' : null;
					break;
				case 'radio':
					this.checked = this.value == value ? 'checked' : null;
					break;
				}
			}
			;
		});
	},
	// 清空form表单
	clearForm : function() {
		$(':input').not(':button, :submit, :reset, :hidden').val('')
				.removeAttr('checked').removeAttr('selected');
	}
};

module.clearForm = {
	init : function() {
		$('.clearForm').on('click', function() {
			module.common.clearForm();
		});
	}
};

module.menu = {
	init : function() {
		$('a[name=menuLink]').on('click', function() {
			var code = $(this).attr('code');
			module.common.cookie(module.constant.cookie.selectedMenu, code);
			return true;
		});
	}
};

module.head = {
	changePass : function() {
		$("#updatePassLink").on("click", function() {
			var c_form = $('#updatePassForm');
			if (!c_form.validationEngine("validate"))
				return false;
			var password = c_form.find("#password").val();
			var confirmPassword = c_form.find("#confirmPassword").val();
			if (password != confirmPassword) {
				module.common.showPopupWindow('密码输入不一致，请重新输入');
				return false;
			}
			$.post(SITE.context + "/admin/savePassChange", {
				oldPassword : $.md5($('#oldPassword').val()),
				password : $.md5(password)
			}, function(resp) {
				if (resp.success === true) {
					module.common.showPopupWindow('密码修改成功');
					var modal = $.scojs_modal({
						keyboard : true
					});
					modal.close();
				} else {
					module.common.showPopupWindow('密码修改失败:' + resp.error);
				}
			}, "json");
			return false;
		});
	},
	changeMemberPass : function() {
		$("#updatePassLink").on("click", function() {
			var c_form = $('#updatePassForm');
			if (!c_form.validationEngine("validate"))
				return false;
			var password = c_form.find("#password").val();
			var confirmPassword = c_form.find("#confirmPassword").val();
			if (password != confirmPassword) {
				module.common.showPopupWindow('密码输入不一致，请重新输入');
				return false;
			}
			$.post(SITE.context + "/member/savePassChange", {
				oldPassword : $.md5($('#oldPassword').val()),
				password : $.md5(password)
			}, function(resp) {
				if (resp.success === true) {
					module.common.showPopupWindow('密码修改成功');
					var modal = $.scojs_modal({
						keyboard : true
					});
					modal.close();
				} else {
					module.common.showPopupWindow('密码修改失败:' + resp.error);
				}
			}, "json");
			return false;
		});
	},
	changeDoctorPass : function() {
		$("#updatePassLink").on("click", function() {
			var c_form = $('#updatePassForm');
			if (!c_form.validationEngine("validate"))
				return false;
			var password = c_form.find("#password").val();
			var confirmPassword = c_form.find("#confirmPassword").val();
			if (password != confirmPassword) {
				module.common.showPopupWindow('密码输入不一致，请重新输入');
				return false;
			}
			$.post(SITE.context + "/doctor/savePassChange", {
				oldPassword : $.md5($('#oldPassword').val()),
				password : $.md5(password)
			}, function(resp) {
				if (resp.success === true) {
					module.common.showPopupWindow('密码修改成功');
					var modal = $.scojs_modal({
						keyboard : true
					});
					modal.close();
				} else {
					module.common.showPopupWindow('密码修改失败:' + resp.error);
				}
			}, "json");
			return false;
		});
	},

	changeUserInfo : function() {
		$("#updateUserInfoLink").on(
				"click",
				function() {
					var c_form = $('#updateUserInfoForm');
					if (!c_form.validationEngine("validate"))
						return false;
					$.post(SITE.context + "/admin/saveUserInfoChange", c_form
							.serializeArray(), function(resp) {
						if (resp.success === true) {
							module.common.showPopupWindow('个人信息修改成功');
							var modal = $.scojs_modal({
								keyboard : true
							});
							modal.close();
						} else {
							module.common.showPopupWindow('个人信息修改失败:'
									+ resp.error);
						}
					}, "json");
					return false;
				});
	},
	relation : function() {
		$("#relationLink")
				.on(
						"click",
						function() {
							debugger;

							if ($('#password').val() == null
									|| $('#password').val() == ''
									|| $('#cardNo').val() == null
									|| $('#cardNo').val() == '') {
								module.common.showPopupWindow('请填写登录卡号和登录密码!');
								return false;
							}

							$
									.post(
											SITE.context
													+ "/member/relationUser",
											{
												password : $.md5($('#password')
														.val()),
												cardNo : $('#cardNo').val()
											},
											function(resp) {
												if (resp.success === true) {
													debugger;
													module.common
															.showPopupWindow('关联成功!');

													var html = '<tr id="'
															+ resp.data.rId
															+ '"><td>'
															+ resp.data.patient.userName
															+ '</td><td>'
															+ resp.data.patient.fullname
															+ '</td><td>'
															+ resp.data.patient.icId
															+ '</td><td><a href="javascript:module.head.relationDel('
															+ resp.data.rId
															+ ')"class="btn btn-mini btn-inverse">取消关联</a></td></tr>';

													$("#tab tr:last").after(
															html);
												} else {
													module.common
															.showPopupWindow(resp.error);
												}
											}, "json");
							return false;
						});
	},
	relationDel : function(id) {
		$.post(SITE.context + "/member/relationDel/" + id, function(resp) {
			if (resp.success === true) {
				module.common.showPopupWindow('删除成功!');
				var obj = $("#" + id);
				$(obj).remove();
			} else {
				module.common.showPopupWindow('删除失败!');
			}
		}, "json");

	}
};

module.signin = {
		showLastPage : function() {
			var lastPage = module.common.cookie(module.constant.cookie.lastPage);
			module.common.cookie(module.constant.cookie.lastPage, '');
			if (lastPage != null && lastPage != '') {
				window.location.href = lastPage;
			} else {
				window.location.href = SITE.context;
			}
		},
		init : function() {
			var login_form = $('#loginForm');
			$("#loginForm").validate({
				rules : {
					inputUsername : {
						required : true,
						minlength : 4
					},
					inputPassword : {
						required : true,
						minlength : 4
					},
					captchaText : {
						required : true
					}
				},
				messages : {
					inputUsername : {
						required : "请输入用户名",
						minlength : "用户名至少6个字符"
					},
					inputPassword : {
						required : "请输入密码",
						minlength : "密码至少6个字符"
					},
					captchaText : {
						required : "请输入验证码"
					}
				}
			});
			$('#submitButton').on(
					'click',
					function() {
						var status = login_form.valid();
						if (status) {
							module.common.showProgress();
							$.post(SITE.context + "/admin/signIn", {
								userName : $('#inputUsername').val(),
								captchaText : $('#captchaText').val(),
								password : $.md5($('#inputPassword').val())
							}, function(resp) {
								debugger;
								if (resp.success === true) {
									if (resp.data.user !== null) {
										if (resp.data.user.userType == 'A') {
											window.location.href = SITE.context
													+ "/admin/index";
										} else if (resp.data.user.userType == 'M') {
											window.location.href = SITE.context
													+ "/member/distribution";
										} else if (resp.data.user.userType == 'D'
												|| resp.data.user.userType == 'S'
												|| resp.data.user.userType == 'R') {
											window.location.href = SITE.context
													+ "/doctor/distribution";
										}
									} else {
										window.location.href = SITE.context
												+ "/member/distribution";
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
		}

};

module.editorList = {
	editor : function() {
		var fonts = [ 'Serif', 'Sans', 'Arial', 'Arial Black', 'Courier',
				'Courier New', 'Comic Sans MS', 'Helvetica', 'Impact',
				'Lucida Grande', 'Lucida Sans', 'Tahoma', 'Times',
				'Times New Roman', 'Verdana' ], fontTarget = $('[title=Font]')
				.siblings('.dropdown-menu');
		$.each(fonts, function(idx, fontName) {
			fontTarget.append($('<li><a data-edit="fontName ' + fontName
					+ '" style="font-family:\'' + fontName + '\'">' + fontName
					+ '</a><>'));
		});
		$('a[title]').tooltip({
			container : 'body'
		});
		$('.dropdown-menu input').click(function() {
			return false;
		}).change(
				function() {
					$(this).parent('.dropdown-menu').siblings(
							'.dropdown-toggle').dropdown('toggle');
				}).keydown('esc', function() {
			this.value = '';
			$(this).change();
		});

		$('[data-role=magic-overlay]').each(
				function() {
					var overlay = $(this), target = $(overlay.data('target'));
					overlay.css('opacity', 0).css('position', 'absolute')
							.offset(target.offset()).width(target.outerWidth())
							.height(target.outerHeight());
				});
		$('#voiceBtn').hide();
	}
};

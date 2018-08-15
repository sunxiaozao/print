module.casehistory = {
	init : function() {
		var inputStatus = function(isDisable) {
			if (isDisable) {
				$('input').attr('disabled', 'disabled');
				$('select').attr('disabled', 'disabled');
			} else {
				$('input').removeAttr('disabled');
				$('select').removeAttr('disabled');
			}
		};

		var url = SITE.context + "/member/casehistory/";

		var loadHospital = function() {
			$("#hospitalId").empty();
			$.post(url + "hospitals", function(resp) {
				if (resp.success == true) {
					$.each(resp.data.list, function(i, item) {
						var $optc = $("<option></option>");
						$optc.val(item.id);
						$optc.text(item.name);
						$optc.appendTo("#hospitalId");
					});
				} else {
					return false;
				}
				loadDepartment($('#hospitalId').val());
			}, "json");
		};
		$(function() {
			loadHospital();
			inputStatus(true);
		});
		var loadDepartment = function(hospitalid) {
			$("#departmentId").empty();
			$.post(url + "department/" + hospitalid, function(resp) {
				if (resp.success == true) {
					$.each(resp.data.list, function(i, item) {
						var $optc = $("<option></option>");

						$optc.val(item.id);
						$optc.text(item.departmentName);
						$optc.appendTo("#departmentId");
					});
				} else {
					return false;
				}
				loadDoctor($('#departmentId').val());
			}, "json");
		};
		$('#hospitalId').on('change', function() {
			inputStatus(true);
			loadDepartment($('#hospitalId').val());
		});

		var loadDoctor = function(departmentId) {
			$("#doctorId").empty();
			$.post(url + "doctor/" + departmentId, function(resp) {
				if (resp.success == true) {
					$.each(resp.data.list, function(i, item) {
						var $optc = $("<option></option>");
						$optc.val(item.id);
						$optc.text(item.fullname);
						$optc.appendTo("#doctorId");
					});

				} else {
					return false;
				}

			}, "json");
			inputStatus(false);
		};
		$('#departmentId').on('change', function() {
			inputStatus(true);
			loadDoctor($('#departmentId').val());
		});
	},
	update : function(vals) {
		var inputStatus = function(isDisable) {
			if (isDisable) {
				$('input').attr('disabled', 'disabled');
				$('select').attr('disabled', 'disabled');
			} else {
				$('input').removeAttr('disabled');
				$('select').removeAttr('disabled');
			}
		};

		var url = SITE.context + "/member/casehistory/";

		var loadHospital = function() {
			$("#hospitalId").empty();
			$.post(url + "hospitals", function(resp) {
				if (resp.success == true) {
					$.each(resp.data.list, function(i, item) {
						if (vals.hospitalId == item.id) {
							var $optc = $("<option selected=\"selected\"></option>");
						} else {
							var $optc = $("<option></option>");
						}
						$optc.val(item.id);
						$optc.text(item.name);
						$optc.appendTo("#hospitalId");
					});
				} else {
					return false;
				}
				loadDepartment($('#hospitalId').val());
			}, "json");
		};
		$(function() {
			alert("0000");
			loadHospital();
			inputStatus(true);
		});
		var loadDepartment = function(hospitalid) {
			$("#departmentId").empty();
			$.post(url + "department/" + hospitalid, function(resp) {
				if (resp.success == true) {
					$.each(resp.data.list, function(i, item) {
						if (vals.departmentId == item.id) {
							var $optc = $("<option selected=\"selected\"></option>");
						} else {
							var $optc = $("<option></option>");
						}

						$optc.val(item.id);
						$optc.text(item.departmentName);
						$optc.appendTo("#departmentId");
					});
				} else {
					return false;
				}
				loadDoctor($('#departmentId').val());
			}, "json");
		};
		$('#hospitalId').on('change', function() {
			inputStatus(true);
			loadDepartment($('#hospitalId').val());
		});

		var loadDoctor = function(departmentId) {
			$("#doctorId").empty();
			$.post(url + "doctor/" + departmentId, function(resp) {
				if (resp.success == true) {
					$.each(resp.data.list, function(i, item) {
						if (vals.doctorId == item.id) {
							var $optc = $("<option selected=\"selected\"></option>");
						} else {
							var $optc = $("<option></option>");
						}
						$optc.val(item.id);
						$optc.text(item.fullname);
						$optc.appendTo("#doctorId");
					});

				} else {
					return false;
				}

			}, "json");
			inputStatus(false);
		};
		$('#departmentId').on('change', function() {
			inputStatus(true);
			loadDoctor($('#departmentId').val());
		});
	},
	checkAll : function() {
		$("#all").on("click", function() {
			$(".js-all-select").click();
		});
	},
	relation : function() {

		$("#relation").on("click", function() {
			var arr_v = new Array();
			$("input[name='id']:checked").each(function() {
				arr_v.push(this.value);
			});
			var ids = arr_v.join(',');

			if (ids != '') {

				var html = '<a data-trigger="modal"  href="' + SITE.context + "/member/casehistory/relation/" + ids + '/H" id="relations" style="visibility:hidden" data-title="关联病历资料"    data-cache="false" class="btn btn-mini btn-primary"></a>';
				$("#div_relation").html(html);
				$("#relations").click();
			} else {
				module.common.showPopupWindow('请选择关联的数据!');
			}
		});

	},
	temporary_relation_sub : function() {
		var tempIdEq = $("#tempIdEq").val();

		if (tempIdEq == '') {
			module.common.showPopupWindow('请选择输入临时病例Id!');
			return false;
		}
		return true;
	},
	temporary_relation : function() {
		$("#temporary_relation").on("click", function() {
			var arr_v = new Array();
			$("input[name='id']:checked").each(function() {
				arr_v.push(this.value);
			});
			var ids = arr_v.join(',');

			if (ids != '') {

				var html = '<a data-trigger="modal"  href="' + SITE.context + "/member/casehistory/relation/" + ids + '/T" id="relations" style="visibility:hidden" data-title="关联病历资料"    data-cache="false" class="btn btn-mini btn-primary"></a>';
				$("#div_relation").html(html);
				$("#relations").click();
			} else {
				module.common.showPopupWindow('请选择关联的数据!');
			}
		});
	},
	showProgress_ : function() {


		var main = module.casehistory;
		if (main._progressBar) {
			main.progress.m.show();
		} else {
			main.progress = {};
			main.progress.m = $('<div class="progressBarBg1" style=" z-index: 2001;"></div>');
			main.progress.loading = $('<div style="position:absolute;z-index:2002;"><img src="' + SITE.images_context + '/images/loading2.gif" width="100px" height="100px"/></div>');
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

		module.casehistory.progress.m.hide();
	},
	showProgress : function() {
		module.casehistory.showProgress_();
		setTimeout(module.casehistory.hideProgress, 5000);// 1秒=1000，这里是3秒
	},
	img_switch : function(obj) {
		$("#img_").attr("src", obj.src);
	},
	open_ : function(url) {

		window.location.href = SITE.context + url;
		module.common.showProgress();
		setTimeout(module.common.hideProgress, 5000);// 1秒=1000，这里是3秒
	},
	isNull : function() {
		var txt = $('#file').val();
		if (txt == '') {
			module.common.showPopupWindow('请选择病例文件!');
			return false;
		} else {
			var num = txt.length - 3;
			var t = txt.substring(num);
			if ("ZIP" != t && "zip" != t) {
				module.common.showPopupWindow('请选择zip文件!');
				return false;

			}
		}
	},
	notebook : function() {
		$("#sub").on("click", function() {
			var content = $("#content").val();
			if (content == "") {
				module.common.showPopupWindow('请填写留言内容!');
			} else {
				var c_from = $("#notebookAdd");
				$.post(SITE.context + "/member/share/savenotebook", c_from.serializeArray(), function(resp) {
					if (resp.success === true) {
						module.common.showPopupWindow('留言成功!');
						var modal = $.scojs_modal({
							keyboard : true
						});
						modal.close();
					} else {
						module.common.showPopupWindow('留言失败');
					}
				}, "json");
			}
		});
	},
	charing : function() {
		$("#sub").on("click", function() {

			var c_form = $('#charingAdd');
			if (!c_form.validationEngine("validate"))
				return false;
			$.post(SITE.context + "/member/share/saveSharing", c_form.serializeArray(), function(resp) {
				if (resp.success === true) {
					module.common.showPopupWindow('关联成功!');
					var modal = $.scojs_modal({
						keyboard : true
					});
					modal.close();
				} else {
					module.common.showPopupWindow(resp.error);
				}
			}, "json");

		});
	}

};
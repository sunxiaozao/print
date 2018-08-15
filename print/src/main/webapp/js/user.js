module.user = {
		choice : function() {

		$("#userType").on(
				"change",
				function() {
					var key = $("#userType").val();
					switch (key) {
					case 'S':
					case 'D':
						$("#admin").hide();
						$("#member").hide();
						$("#doctor").show();
						$("html").getNiceScroll().resize();
						module.user.init();
						$("#userAdd").attr("action",
								SITE.context + "/admin/user/saveDoctor");
						break;
					case 'M':
						$("#admin").hide();
						$("#doctor").hide();
						$("#member").show();
						$("#userAdd").attr("action",
								SITE.context + "/admin/user/saveMember");
						break;
					default:
						$("#doctor").hide();
						$("#member").hide();
						$("#admin").show();
						$("#userAdd").attr("action",
								SITE.context + "/admin/user/saveAdd");
						break;

					}
				});

	},
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

			}, "json");
			inputStatus(false);
		};
		$('#hospitalId').on('change', function() {
			inputStatus(true);
			loadDepartment($('#hospitalId').val());
		});

	},init_ : function(hospital,department) {
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
						if (hospital == item.id) {
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
			loadHospital();
			inputStatus(true);
		});
		var loadDepartment = function(hospitalid) {
			$("#departmentId").empty();
			$.post(url + "department/" + hospitalid, function(resp) {
				if (resp.success == true) {
					$.each(resp.data.list, function(i, item) {
						if (department == item.id) {
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

			}, "json");
			inputStatus(false);
		};
		$('#hospitalId').on('change', function() {
			inputStatus(true);
			loadDepartment($('#hospitalId').val());
		});

	}
	
}
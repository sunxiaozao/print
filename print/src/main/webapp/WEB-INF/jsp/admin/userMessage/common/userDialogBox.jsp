<%@ page language="java" pageEncoding="UTF-8"%>
<div id="user-dialog-form" title="关联用户" style="visibility: hidden">

	<form id="myForm" name="myForm" method="post" class="form-search">
		<div class="row-fluid">
			<div class="span12 searchBlock">

				<input id="flag" name="flag" type="hidden" value="Dialog1" />
				用户名:
				<input id="dialog_userName" name="dialog_userName" type="text"
					style="width: 180px" />

				<span class="text">Email: </span>
				<input id="dialog_email" name="dialog_email" type="text"
					style="width: 180px" />
				<button type="button" class="btn btn-primary"
					onclick="doSearch();"><i class="icon-search icon-white"></i> 搜 索</button>
				


			</div>

		</div>
		<div id="dynamicUserDiv"></div>
	</form>

</div>

<script>
var userTextId = "";
function doSearch() {

	var dynamicUserDiv = $("#dynamicUserDiv");
	var dialog_userName = $("#dialog_userName").val();
	var dialog_email = $("#dialog_email").val();
	var showUser = function() {
		$.post(SITE.context
				+ "/admin/getMemberList.do?flag=Dialog1&dialog_userName="
				+ dialog_userName + "&dialog_email=" + dialog_email, function(
				resp) {
			dynamicUserDiv.html($.trim(resp));
			return false;
		}, "html");
	};
	showUser();

}
function getUserId(userId) {
	$("#" + userTextId + "").val(userId);
	$("#" + userTextId + "").change();

	$("#user-dialog-form").dialog("close");
}

function findUser(userId, buttonId) {
	userTextId = userId;

	document.getElementById("user-dialog-form").style.visibility = "visible";//显示
	$(function() {
		// a workaround for a flaw in the demo system (http://dev.jqueryui.com/ticket/4375), ignore!
		$("#dialog:ui-dialog").dialog("destroy");
		var userName = $("#dialog_userName");
		var email = $("#dialog_email");
		allFields = $( []).add(userName).add(email), $("#user-dialog-form")
				.dialog( {
					autoOpen : false,
					height : 500,
					width : 650,
					modal : true,
					close : function() {
						allFields.val("");
					}

				});

		$("#" + buttonId + "").button().click(function() {
			$("#user-dialog-form").dialog("open");
		});
	});

	doSearch();
}
</script>
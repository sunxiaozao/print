<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp" %>

<form action="" id="updatePassForm" method="post" class="form-horizontal">
	<input type="hidden" value="${patientId }" id="patientId" />
	<div class="m-iframe-content">
		
		<!-- <div class="control-group">
			<label class="control-label">输入旧密码：</label>
			<div class="controls">
				<input type="password" id="oldPassword" name="oldPassword" class="input validate[required,minSize[5],maxSize[20]]">
			</div>
		</div> -->
		<div class="control-group">
			<label class="control-label">输入新密码：</label>
			<div class="controls">
				<input type="password" id="password" name="password" class="input validate[required,minSize[5],maxSize[20]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确认新密码：</label>
			<div class="controls">
				<input type="password" id="confirmPassword" name="confirmPassword" class="input validate[required,minSize[5],maxSize[20]]" >
			</div>
		</div>
	</div>
	<div class="m-emodal-footer">
        <button aria-hidden="true" data-dismiss="modal" class="btn">关 闭</button>
        <a href="javascript:;" id="updatePassLink" class="btn btn-primary">确 定</a>                           
    </div>
</form>
<script>
$(function(){
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
			var patientId= c_form.find("#patientId").val();
			$.post(SITE.context + "/admin/patient/savePassChange", {
				password : $.md5(password),
				patientId : patientId
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
});
</script>
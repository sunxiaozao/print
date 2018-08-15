<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/pagestart.jsp" %>

<form action="" id="updatePassForm" method="post" class="form-horizontal">
	<div class="m-iframe-content">
		<div class="control-group">
			<label class="control-label">输入旧密码：</label>
			<div class="controls">
				<input type="password" id="oldPassword" name="oldPassword" class="input validate[required,minSize[5],maxSize[20]]">
			</div>
		</div>
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
	module.head.changeDoctorPass();
});
</script>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/pagestart.jsp" %>

<form action="" id="updateUserInfoForm" method="post" class="form-horizontal">
	<div class="m-iframe-content">
			<div class="control-group">
				<label class="control-label">
					用户名：
				</label>
				<div class="controls">
					${user.userName }
				</div>
			</div>	
			<div class="control-group">
				<label class="control-label">
					姓名：
				</label>
				<div class="controls">
					<input type="text" id="realName" name="realName"
						value="${user.realName }"
						class="input w370 validate[required,maxSize[10]]">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					Email：
				</label>
				<div class="controls">
					<input type="text" id="email" name="email" value="${user.email}"
						class="input w370 validate[custom[email]]">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					手机：
				</label>
				<div class="controls">
					<input type="text" id="mobile" name="mobile" value="${user.mobile}"
						class="input w370 validate[required,custom[phone]]">
				</div>
			</div>
	</div>
	<div class="m-emodal-footer">
        <button aria-hidden="true" data-dismiss="modal" class="btn">关 闭</button>
        <a href="javascript:;" id="updateUserInfoLink" class="btn btn-primary">确 定</a>                           
    </div>
</form>
<script>
$(function(){
	module.head.changeUserInfo();
});
</script>
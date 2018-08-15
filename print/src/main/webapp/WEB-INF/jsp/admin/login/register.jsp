<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>



<form action="${SITE.context}/admin/saveUser" class="form-horizontal"
	id="register" method="post">
	<div class="m-iframe-content m-scroll ">
		<div class="control-group"></div>
		<div class="control-group">
			<label class="control-label"> 账号名称： </label>
			<div class="controls">
				<input value=""
					class="validate[required,ajax[ajaxUserName]] text-input"
					type="text" name="userName" id="username" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 密码: </label>
			<div class="controls">
				<input type="password" id="password" name="password"
					value="${user.icId }"
					class="text-input validate[required,minSize[6],maxSize[20]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 确认密码: </label>
			<div class="controls">
				<input
					class="text-input validate[condRequired[password],equals[password]]"
					type="password">
			</div>
		</div>
	</div>
	<div class="m-emodal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">返回</button>
		<button class="btn btn-primary">保存</button>
	</div>
</form>


<script type="text/javascript">
	$(function() {
		$.validationEngineLanguage.allRules.ajaxUserName.url = SITE.context
		+ "/admin/checkusernameonly";//验证用户名是否重复
		$("#register").validationEngine();
	});
</script>

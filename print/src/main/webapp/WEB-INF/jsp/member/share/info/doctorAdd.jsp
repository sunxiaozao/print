<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../../common/pagestart.jsp"%>

<form action="${SITE.context}/member/share/saveAdd"
	class="form-horizontal" id="doctorAdd" method="post">
	<div class="m-iframe-content m-scroll m-h300">

		<div class="control-group">
			<label class="control-label"> 医院： </label>
			<div class="controls">
				<input type="text" id="hospital" name="hospital">
				<input type="hidden" id="shateid" name="shareId" value="${shareId}">

			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> 科室： </label>
			<div class="controls">
				<input type="text" id="department" name="department">

			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 账号名称： </label>
			<div class="controls">
				<input value="" class="validate[required,ajax[ajaxUserName],minSize[6]] text-input" type="text"
					name="username" id="username" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 密码： </label>
			<div class="controls">
				<input  class="validate[required,minSize[6]] text-input" type="password"
					name="password" id="password" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 确认密码： </label>
			<div class="controls">
				<input value="" class="validate[required,equals[password]] text-input" type="password"
					name="pwd" id="pwd" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 手机: </label>
			<div class="controls">

				<c:if test="${!empty mobile}">
					<input type="text" id="mobile" name="mobile" value="${mobile }"
						disabled>
				</c:if>
				<c:if test="${empty mobile}">
					<input type="text" id="mobile" name="mobile" value="${mobile }"
						class="validate[custom[phone],ajax[ajaxMobile]] text-input">
				</c:if>
			</div>

		</div>

		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls" id="email">
				<input type="text" id="email" name="email"
					class="validate[custom[email],ajax[ajaxEmail]] text-input">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<input type="text" id="fullname" name="fullname"
					class="input w370 validate[required]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别:</label>
			<div class="controls">
				<select name="sex" class="sex">
					<c:forEach items="${sexMap}" var="sex">
						<option value="${sex.key}"
							<c:if test="${sex.key eq doctor.sex}">selected</c:if>>${sex.value}</option>
					</c:forEach>

				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职务:</label>
			<div class="controls">
				<input type="text" id="title" name="title"
					class="input w370 ">
			</div>
		</div>
	</div>
	<div class="m-emodal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		<button class="btn btn-primary">完成</button>
	</div>
</form>
<script>
	$(function() {
		$.validationEngineLanguage.allRules.ajaxUserName.url = SITE.context
		+ "/admin/checkusernameonly";//验证用户名是否重复
		$.validationEngineLanguage.allRules.ajaxEmail.url = SITE.context
				+ "/admin/checkuseronly/E";//验证Email是否重复
		$.validationEngineLanguage.allRules.ajaxMobile.url = SITE.context
				+ "/admin/checkuseronly/M";//验证手机号是否重复
		$("#doctorAdd").validationEngine();

	});
</script>

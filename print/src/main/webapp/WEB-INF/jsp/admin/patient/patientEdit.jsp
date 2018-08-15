<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="userEdit" method="post" class="form-horizontal">
		<div class="form-box-title">
			<h3>
				修改病人信息 <small>请填写病人信息，然后保存。</small>
			</h3>
		</div>

		<div class="control-group">
			<label class="control-label"> 用户名： <input type="hidden"
				name="userId" value="${user.userId}"
				class="input w370 validate[required,minSize[5],maxSize[20]]">
			</label>
			<div class="controls">
				<input type="text" id="userName" name="userName"
					value="${user.userName}"
					class="input w370 validate[required,minSize[5],maxSize[20]]"
					readonly="readonly">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 密码： </label>
			<div class="controls">
				<input type="password" id="pwd" name="password"
					class="input w370 validate[minSize[5],maxSize[20]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 确认密码： </label>
			<div class="controls">
				<input type="password"
					class="validate[condRequired[pwd],equals[pwd]]">
			</div>
		</div>


		<div class="control-group">
			<label class="control-label"> 姓名： </label>
			<div class="controls">
				<input type="text" id="fullname" name="fullname"
					value="${user.realName }"
					class="input w370 validate[required,maxSize[10]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> Email： </label>
			<div class="controls">
				<input type="text" id="email" name="email" value="${user.email}"
					class="input w370 validate[custom[email]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 手机号码： </label>
			<div class="controls">
				<input type="text" id="mobile" name="mobile" value="${user.mobile}"
					class="input w370 validate[required,custom[phone]]">
			</div>
		</div>
		
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-primary" >
					<i class="icon-ok icon-white"></i> 保存
				</button>
				&nbsp;&nbsp; <a class="btn" href="${SITE.context}/admin/user/list">
					返回 </a>
			</div>
		</div>
	</form>
</div>
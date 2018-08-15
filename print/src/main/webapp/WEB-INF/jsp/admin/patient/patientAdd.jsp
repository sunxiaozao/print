<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="userAdd" action="${SITE.context}/admin/user/saveAdd"
		method="post" class="form-horizontal">
		<div class="form-box-title">
			<h3>
				新建用户 <small>请填写用户信息，然后保存。</small>
			</h3>
		</div>
		<div class="control-group">
			<label class="control-label"> 用户名： </label>
			<div class="controls">
				<input type="text" id="username" name="userName"
					value="${user.userName}"
					class="input w370 validate[required,minSize[5],maxSize[20],ajax[ajaxUserName]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 密码： </label>
			<div class="controls">
				<input type="password" id="pwd" name="password"
					class="input w370 validate[required,minSize[5],maxSize[20]]">
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
			<label class="control-label"> 用户类型： </label>
			<div class="controls">
				<select name="userType" class="userType" id="userType">
					<c:forEach items="${userTypeMap}" var="typeMap">
						<option value="${typeMap.key}">${typeMap.value}
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> 姓名： </label>
			<div class="controls">
				<input type="text" id="realName" name="realName"
					value="${user.realName }"
					class="input w370 validate[required,maxSize[10]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> Email： </label>
			<div class="controls">
				<input type="text" id="email" name="email" value="${user.email}"
					class="input w370 validate[custom[email],ajax[ajaxEmail]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 手机： </label>
			<div class="controls">
				<input type="text" id="mobile" name="mobile" value="${user.mobile}"
					class="input w370 validate[required,custom[phone],ajax[ajaxMobile]]">
			</div>
		</div>
		<div class="control-group" id="admin">
			<label class="control-label"> 角色： </label>
			<div class="controls">
				<select name="roleId" class="select">
					<c:forEach items="${roleList}" var="role">
						<option value="${role.roleId}"
							<c:if test="${user.roleId == role.roleId}">selected</c:if>>
							${role.roleName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group" id="member" style="display: none">
			<label class="control-label"> icId： </label>
			<div class="controls">
				<input type="text" id="ic" name="icId" value="${user.mobile}"
					class="input w370 validate[ajax[ajaxIc]]">
			</div>
		</div>


		<div style="display: none" id="doctor">

			<div class="control-group">
				<label class="control-label"> 所属医院： </label>
				<div class="controls">
					<select id="hospitalId" name="hospitalId">
						<option value="">请选择所属医院</option>

					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"> 所属科室： </label>
				<div class="controls">
					<select id="departmentId" name="departmentId">
						<option value="">请选择所属科室</option>

					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">职务:</label>
				<div class="controls">
					<input type="text" id="title" name="title"
						class="input w370 validate[required]">
				</div>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-primary" id="submit">
					<i class="icon-ok icon-white"></i> 保存
				</button>
				&nbsp;&nbsp;
				<button type="button" class="btn"
					onclick="javascript:history.go(-1)">返回</button>
			</div>
		</div>
	</form>
</div>
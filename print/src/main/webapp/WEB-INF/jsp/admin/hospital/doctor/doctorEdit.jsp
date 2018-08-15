<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="doctorEdit" method="post" class="form-horizontal"
		action="${SITE.context}/admin/doctor/saveEdit">
		<div class="form-box-title">
			<h3>
				修改医生信息 <small>请填写医生信息，然后保存。</small>
			</h3>
		</div>
		<div class="control-group">
			<label class="control-label"> 所属医院： </label>
			<div class="controls">
				<label class="control-label">${hostpital.name} </label> <input
					type="hidden" id="hospitalId" name="hospitalId"
					value="${hostpital.id}">

			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> 所属科室： </label>
			<div class="controls">
				<label class="control-label">${department.departmentName} </label> <input
					type="hidden" id="departmentId" name="departmentId"
					value="${department.id}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">医生类别:</label>
			<div class="controls">

				<label class="radio inline"> <input type="radio"
					<c:if test="${user.userType eq 'D' }">	checked</c:if>
					name="userType" value="D" /> 普通医生</label> <label class="radio inline">
					<input type="radio" name="userType" value="S"
					<c:if test="${user.userType eq 'S' }">	checked</c:if> />超级医生</label>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> 账号名称： </label>
			<div class="controls">
				<label class="control-label">${doctor.username }</label> <input
					type="hidden" name="id" value="${doctor.id }"> <input
					type="hidden" id="userId" name="userId" value="${doctor.userId}">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> IC/ID/手机/邮箱： </label>
			<div class="controls">
				<select name="loginType" id="loginType">
					<option value="">选择登录类型</option>
					<c:forEach items="${loginType}" var="types">
						<option value="${types.key}"
							<c:if test="${types.key eq type}">selected</c:if>>${types.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> IC/ID </label>
			<div class="controls">
				<input type="text" id="ic" name="ic" value="${user.icId }"
					class=" text-input">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 邮箱 </label>
			<div class="controls">
				<input type="text" id="email" name="email" value="${user.email }"
					class=" text-input">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 手机 </label>
			<div class="controls">
				<input type="text" id="mobile" name="mobile" value="${user.mobile }"
					class="text-input">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">姓名:</label>
			<div class="controls">
				<input type="text" id="fullname" name="fullname"
					value="${doctor.fullname }" class="input w370 validate[required]">
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
				<input type="text" id="title" name="title" value="${doctor.title }"
					class="input w370 validate[required]">
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-primary" id="doctorEditSubmit">
					<i class="icon-ok icon-white"></i> 保存
				</button>
				&nbsp;&nbsp; <a class="btn"
					href="${SITE.context}/admin/doctor/list?departmentId=${department.id }">
					返回 </a>
			</div>
		</div>
	</form>
</div>
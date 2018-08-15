<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="doctorAdd" action="${SITE.context}/admin/doctor/saveAdd"
		method="post" class="form-horizontal">
		<div class="form-box-title">
			<h3>
				新建(普通医生) <small>请填写医生信息，然后保存。</small>
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
				<label class="radio inline"> <input type="radio" checked
					name="userType" value="D" /> 普通医生</label> <label
					class="radio inline"> <input type="radio"
					name="userType" value="S"  />超级医生</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 账号名称： </label>
			<div class="controls">

				<input value=""
					class="validate[required,ajax[ajaxUserName]] text-input"
					type="text" name="username" id="username" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> IC/ID/手机/邮箱： </label>
			<div class="controls">
				<select name="type" id="type">

					<c:forEach items="${loginType}" var="types">
						<option value="${types.key}">${types.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> </label>
			<div class="controls" id="ic_email_mobile">
				<input type="text" id="ic" name="user"
					class="validate[required,ajax[ajaxIc]] text-input">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> </label>
			<div class="controls">
				<label class="checkbox"><input type="checkbox" id="ischeck_"
					name="ischeck_" value="1"><font style="color:red;">
						&nbsp;当选中“作为登录账户”时，需确保是唯一的 </font> </label>
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
					class="input w370 validate[required]">
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
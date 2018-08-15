<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="departmentEdit" method="post" class="form-horizontal">
		<div class="form-box-title">
			<h3>
				修改科室信息 <small>请填写科室信息，然后保存。</small>
			</h3>
		</div>
		<div class="control-group">
			<label class="control-label"> 所属医院： </label>

			<div class="controls">
				<label class="control-label">${hospital.name} </label> <input
					type="hidden" id="hospitalId" name="hospitalId"
					value="${hospital.id}"> <input type="hidden" id="id"
					name="id" value="${department.id}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 科室名称名称： </label>
			<div class="controls">
				<input type="text" id="departmentName" name="departmentName"
					class="input w370 validate[required]"
					value="${department.departmentName }">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 科室类别： </label>
			<div class="controls">
				<select name="type" class="type">
					<c:forEach items="${dicts}" var="dict">
						<option value="${dict.shortCode}"
							<c:if test="${department.type == dict.shortCode}">selected</c:if>>
							${dict.longName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 科室描述： </label>
			<div class="controls">
				<textarea class="input w370 validate[required]" rows="3"
					name="description" id="description">${department.description}</textarea>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button type="button" class="btn btn-primary"
					id="departmentEditSubmit">
					<i class="icon-ok icon-white"></i> 保存
				</button>
				&nbsp;&nbsp; <a class="btn"
					href="${SITE.context}/admin/department/list?hospitalId=${department.hospitalId }">
					返回 </a>
			</div>
		</div>
	</form>
</div>
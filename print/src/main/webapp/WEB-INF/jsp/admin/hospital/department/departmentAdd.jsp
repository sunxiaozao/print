<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="departmentAdd" action="${SITE.context}/admin/department/saveAdd"
		method="post" class="form-horizontal">
		<div class="form-box-title">
			<h3>
				新建科室 <small>请填写科室信息，然后保存。</small>
			</h3>
		</div>

		<div class="control-group">
			<label class="control-label"> 所属医院： </label>
			
			<div class="controls">
				<label class="control-label">${hostpital.name} </label>
				 <input type="hidden" id="hospitalId"
					name="hospitalId" value="${hostpital.id}">

			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 科室名称名称： </label>
			<div class="controls">
				<input type="text" id="departmentName" name="departmentName"
					class="input w370 validate[required]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 科室类别： </label>
			<div class="controls">
				<select name="type" class="type">
					<c:forEach items="${dicts}" var="dict">
						<option value="${dict.shortCode}"
							<c:if test="${hospital.type == dict.shortCode}">selected</c:if>>
							${dict.longName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 科室描述： </label>
			<div class="controls">
				<textarea class="input w370 validate[required]" rows="3"
					name="description" id="description"></textarea>

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
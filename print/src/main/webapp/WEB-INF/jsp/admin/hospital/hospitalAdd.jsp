<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="hospitalAdd" action="${SITE.context}/admin/hospital/saveAdd" method="post" class="form-horizontal">
		<div class="form-box-title">
			<h3>
				新建医院 <small>请填写医院信息，然后保存。</small>
			</h3>
		</div>
		<div class="control-group">
			<label class="control-label"> 所属医院： </label>
			<div class="controls">
				<select name="parentId">
					<option value="">请选择</option>
					<c:forEach items="${map}" var="m">
						<option value="${m.key}">${m.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 医院编号： </label>
			<div class="controls">
				<input type="text" id="hospitalId" name="hospitalId" value="${hospital.hospitalId}" class="input w370 validate[required]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 医院名称： </label>
			<div class="controls">
				<input type="text" id="name" name="name" value="${hospital.name}" class="input w370 validate[required]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 医院类别： </label>
			<div class="controls">
				<select name="type" class="type">
					<c:forEach items="${dicts}" var="dict">
						<option value="${dict.shortCode}" <c:if test="${hospital.type == dict.shortCode}">selected</c:if>>${dict.longName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 医院描述： </label>
			<div class="controls">
				<textarea class="input w370 validate[required]" rows="3" name="description" id="description">${hospital.description}</textarea>

			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-primary" id="submit">
					<i class="icon-ok icon-white"></i> 保存
				</button>
				&nbsp;&nbsp;
				<button type="button" class="btn" onclick="javascript:history.go(-1)">返回</button>
			</div>
		</div>
	</form>
</div>
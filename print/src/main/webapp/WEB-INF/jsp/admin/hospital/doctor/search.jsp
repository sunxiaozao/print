<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../../common/inc.jsp"%>

<div class="row-fluid">
	<h2 id="page-title">
		<span>医生管理</span>&nbsp;&nbsp;&nbsp;<small>(${department.departmentName
			})</small>
	</h2>
</div>
<div class="row-fluid">
	<div class="span12 searchBlock">
		<div class="span9">
			<form id="myForm" name="myForm" method="post" class="form-search"
				action="${SITE.context}/admin/doctor/list">
				<input name="departmentId" type="hidden" class="input-medium"
					value="${department.id}" /> 医生姓名: <input placeholder="请输入医生姓名..."
					name="fullname" type="text" class="input-medium"
					value="${doctor.fullname}" /> 性别: <select name="sex" class="sex">
					<option value="">请选择...</option>

					<c:forEach items="${sexMap}" var="sex">
						<option value="${sex.key}"
							<c:if test="${sex.key eq doctor.sex}">selected</c:if>>${sex.value}</option>
					</c:forEach>

				</select>
				<button type="submit" class="btn btn-primary">
					<i class="icon-search icon-white"></i> 搜 索
				</button>
				<button class="btn clearForm" type="button">
					<i class="icon-eraser"></i> 清 除
				</button>
			</form>
		</div>
		<div class="span3">
			<div class="pull-right">
				<a
					href="${SITE.context}/admin/department/list?hospitalId=${department.hospitalId}"
					class="btn btn-success "><i class="icon-plus  icon-mail-reply"></i>返回</a>
				<a href="${SITE.context}/admin/doctor/add/${department.id}"
					class="btn btn-success "><i class="icon-plus icon-white"></i>
					添加医生</a>
			</div>
		</div>
	</div>
</div>
<div class="space"></div>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../../common/inc.jsp"%>

<div class="row-fluid">
	<h2 id="page-title"><span>科室管理</span>&nbsp;&nbsp;&nbsp;(${hostpital.name })</h2>
</div>
<div class="row-fluid">
<div class="span12 searchBlock">
	<div class="span9">
		<form id="myForm" name="myForm" method="post" class="form-search" action="${SITE.context}/admin/department/list">
			
			<input placeholder="请输入医院编号..." name="hospitalId" type="hidden" class="input-medium" value="${department.hospitalId}" />
			科室名称:
			<input placeholder="请输入科室名称..." name="departmentName" type="text" class="input-medium" value="${department.departmentName}" />
			科室类型:
			<select name="type" class="type" >
			<option value="">请选择科室类型...</option>
					<c:forEach items="${dicts}" var="dict">
					
						<option value="${dict.shortCode}"
							<c:if test="${department.type == dict.shortCode}">selected</c:if>>
							${dict.longName}</option>
					</c:forEach>
				</select>
				<button type="submit" class="btn btn-primary"><i class="icon-search icon-white"></i> 搜 索</button>
				<button class="btn clearForm" type="button"><i class="icon-eraser"></i> 清 除</button>
		</form>
	</div>
	<div class="span3"><div class="pull-right">
	<a href="${SITE.context}/admin/hospital/list" class="btn btn-success "><i class="icon-plus  icon-mail-reply"></i>返回</a>
		<a href="${SITE.context}/admin/department/add/${hostpital.id}" class="btn btn-success "><i class="icon-plus icon-white"></i> 添加科室</a>
	</div></div>
</div>
</div>
<div class="space"></div>
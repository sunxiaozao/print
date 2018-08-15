<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="row-fluid">
	<h2 id="page-title"><span>医院管理</span></h2>
</div>
<div class="row-fluid">
	<div class="span12 searchBlock">
		<div class="span10">
			<form id="myForm" name="myForm" method="post" class="form-search"
				action="${SITE.context}/admin/hospital/list">
				医院编号: <input placeholder="请输入医院编号..." name="hospitalId" type="text"
					class="input-medium" value="${hostpital.hospitalId}" /> 医院名称: <input
					placeholder="请输入医院名称..." name="name" type="text"
					class="input-medium" value="${hostpital.name}" /> 医院类型: <select
					name="type" class="type">
					<option value="">请选择医院类型...</option>
					<c:forEach items="${dicts}" var="dict">

						<option value="${dict.shortCode}"
							<c:if test="${hostpital.type == dict.shortCode}">selected</c:if>>
							${dict.longName}</option>
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
		<div class="span2">
			<div class="pull-right">
				<a href="${SITE.context}/admin/hospital/add"
					class="btn btn-success "><i class="icon-plus icon-white"></i>
					添加医院</a>
			</div>
		</div>
	</div>
</div>
<div class="space"></div>
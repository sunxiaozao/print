<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="row-fluid">
	<h2 id="page-title">
		<span>用户管理</span>
	</h2>
</div>
<div class="row-fluid">
	<div class="span12 searchBlock">
		<div class="span8">
			<form id="myForm" name="myForm" method="post" class="form-search" action="${SITE.context}/admin/user/list">
				用户名: <input placeholder="请输入用户名..." name="userName" type="text" class="input-small" value="${userSearch.userName }" /> 角色: <select placeholder="请选择角色..." name="roleId" class="input-small">
					<option value=""></option>
					<c:forEach items="${roleList}" var="role">
						<option value="${role.roleId}" <c:if test="${role.roleId == roleId}">selected</c:if>>${role.roleName}</option>
					</c:forEach>
				</select> 上次登陆: <input type="text" placeholder="请选择开始日期..." class="form_date input-small" name="lastLoginDtFrom" value="<fmt:formatDate value="${userSearch.lastLoginDtFrom}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />"> <input type="text" placeholder="请选择结束日期..." class="form_date input-small" name="lastLoginDtTo" value="<fmt:formatDate value="${userSearch.lastLoginDtTo}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />">
				<button type="submit" class="btn btn-primary"">
					<i class="icon-search icon-white"></i> 搜 索
				</button>
				<button class="btn clearForm" type="button">
					<i class="icon-eraser"></i> 清 除
				</button>
			</form>
		</div>
		<div class="span4">
			<div class="pull-right">
				<a href="${SITE.context}/${doctortemp}" class="btn btn-success "><i class="icon-download-alt icon-white"></i> 医生模板</a> 
				
			
				
				<a data-trigger="modal" href="${SITE.context}/admin/user/import" data-cache="false" data-title="导入医生信息" class="btn btn-success"><i class="icon-pencil"></i> 导入</a>
				
				 <a href="${SITE.context}/admin/user/add" class="btn btn-success "><i class="icon-plus icon-white"></i> 添加用户</a>
			</div>
		</div>
	</div>
</div>
<div class="space"></div>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>
<div class="row-fluid">
	<h2 id="page-title"><span>角色管理</span></h2>
</div>
<div class="row-fluid">
	<div class="span12 searchBlock">
		<div class="span8">
			<form name="myForm" method="post" class="form-search" action="${SITE.context}/admin/role/list">
				角色名称: 
				<input name="roleName" type="text" class="input" value="${roleName }" placeholder="请输入角色名称" /> 		
				<button type="submit" class="btn btn-primary""><i class="icon-search icon-white"></i> 搜 索</button>
				<button class="btn clearForm" type="button"><i class="icon-eraser"></i> 清 除</button>
			</form>
		</div>
		<div class="span4">
			<div class="pull-right">
			<a href="${SITE.context}/admin/role/add" class="btn btn-success">
				<i class="icon-plus icon-white"></i> 添加角色
			</a>
			</div>
		</div>
	</div>
</div>
<div class="space"></div>
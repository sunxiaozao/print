<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="row-fluid">
	<h2 id="page-title"><span>组织机构管理</span></h2>
</div>
<div class="row-fluid">	
	<div class="span12 searchBlock">
		<div class="span10">
			<form name="myForm" method="post" class="form-search" action="${SITE.context}/admin/org/list">
				组织机构名称:
				<input name="orgName" type="text" class="input" value="${orgName }" placeholder="请输入组织机构名称" /> 
				<button type="submit" class="btn btn-primary""><i class="icon-search icon-white"></i> 搜 索</button>
				<button class="btn clearForm" type="button"><i class="icon-eraser"></i> 清 除</button>
			</form>
			</div>
			<div class="span2"><div class="pull-right">
				<a href="${SITE.context}/admin/org/add" class="btn btn-success"><i
					class="icon-plus icon-white"></i> 添加组织机构</a>
			</div>
		</div>
	</div>	
</div>
<div class="space"></div>
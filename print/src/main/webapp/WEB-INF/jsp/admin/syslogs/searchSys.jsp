<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="row-fluid">
	<h2 id="page-title"><span>系统日志列表</span></h2>
</div>
<div class="row-fluid">
	<div class="span12 searchBlock">
	<form name="myForm" method="post" class="form-search" action="${SITE.context}/admin/syslog/list">
		用户名称: 
				<input placeholder="请输入用户名称.." name="userName" type="text" class="input-medium" value="${sysLogsSearch.userName}"/> 
		描述: 
				<input placeholder="请输入描述信息..." name="description" type="text" class="input-medium" value="${sysLogsSearch.description}"/> 
		操作日期:
				<input type="text" placeholder="请选择开始日期..." class="form_date input-small" name="cdateFrom" value="<fmt:formatDate value="${sysLogsSearch.cdateFrom}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />">
				<input type="text" placeholder="请选择结束日期..." class="form_date input-small" name="cdateTo" value="<fmt:formatDate value="${sysLogsSearch.cdateTo}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />">	
				<button type="submit" class="btn btn-primary""><i class="icon-search icon-white"></i> 搜 索</button>
				<button class="btn clearForm" type="button"><i class="icon-eraser"></i> 清 除</button>
	</form>
	</div>
</div>
<div class="space"></div>
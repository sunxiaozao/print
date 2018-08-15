<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="row-fluid">
	<h2 id="page-title"><span>系统参数管理</span></h2>
</div>
<div class="row-fluid">
	<div class="span12 searchBlock">
		<div class="span12">
		<form id="myForm" name="myForm" method="post" class="form-search" action="${SITE.context}/admin/para/list">
			父类名称: 
				 <select name="parentCode" class="select">
						<c:forEach items="${sysParaTypeMap}" var="sysParaType">
							<option value="${sysParaType.key}" <c:if test="${sysParaType.key  == sysPara.parentCode }">selected</c:if>>
								${sysParaType.value}
							</option>
						</c:forEach>
					</select> 
				<button type="submit" class="btn btn-primary""><i class="icon-search icon-white"></i> 搜 索</button>
				<button class="btn clearForm" type="button"><i class="icon-eraser"></i> 清 除</button>
			</form>			
		</div>
	</div>
</div>
<div class="space"></div>
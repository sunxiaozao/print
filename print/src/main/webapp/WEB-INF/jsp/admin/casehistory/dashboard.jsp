<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp" %>

<div id="indexNav" class="row-fluid">
	<div class="span12">
		<ul class="nav nav-tabs">
		  <li >
		    <a href="${SITE.context}/member/index">分类汇总</a>
		  </li>
		  <li class="active"><a href="${SITE.context}/member/casehistory/list">按病历资料方式</a></li>
		  <li><a href="${SITE.context}/member/folderShow/list">按病历夹方式</a></li>
		</ul>
	</div>
</div>
<div class="row-fluid">
	<div class="span12 searchBlock">
		<div class="span10">
			<form id="myForm" name="myForm" method="post" class="form-search"
				action="${SITE.context}/member/casehistory/list">
				
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
				<a href="${SITE.context}/member/casehistory/add/${patient.id}"
					class="btn btn-success "><i class="icon-plus icon-white"></i>
					添加病历资料</a>
			</div>
		</div>
	</div>
</div>
<div class="space"></div>
<table class="table table-striped">
	<thead>
		<tr>
			<th>
				病历资料名称
			</th>
			<th>
				病历资料类别
			</th>
			<th>
				选择就诊医院
			</th>
			<th>
				检查日期
			</th>
			
		</tr>
	</thead>
 <c:if test="${!empty pm.datas}">
	<tbody>
		<c:forEach items="${pm.datas}" var="data" varStatus="status">			
				<tr>
					<td style="text-align:left; padding-left:10px;">
						${data.caseName}
					</td>
					<td>
						${caseTypeMap[data.caseType]}
					</td>
					<td>
						${hospitalMap[data.hospitalId]}
					</td>
					<td>
						${deMap[data.departmentId]}
					</td>
				</tr>			
		</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="4" align="center" bgcolor="#EFF3F7">
				没有找到相应的记录
			</td>
		</tr>
	</c:if>
	<c:if test="${not empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="4" class="bottom">
				<div class="pagination pagination-right row-fluid">
					<div class="span12">
						<span> <!-- 需要修改url地址 dx--> 
						<pg:pager url="${SITE.context}/member/casehistory/list" items="${pm.total}" export="currentPageNumber=pageNumber" maxPageItems="${ps}"
								scope="request">
								<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
							</pg:pager> <!-- 用户选择每页显示行数下拉列表 --> 
							<jsp:include page="/WEB-INF/jsp/pages/pager-select.jsp" flush="true" /> </span>
					</div>
				</div>
			</td>
		</tr>
	</c:if>
</table>
<div class="space"></div>
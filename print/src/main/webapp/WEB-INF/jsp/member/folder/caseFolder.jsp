<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>

<div id="indexNav" class="row-fluid">
	<div class="span12">
		<ul class="nav nav-tabs">
			<li><a href="${SITE.context}/member/index">分类汇总</a></li>
			<li><a href="${SITE.context}/member/casehistory/list">按病历资料方式</a>
			</li>
			<li class="active"><a
				href="${SITE.context}/member/folderShow/list">按病历夹方式</a>
			</li>
		</ul>
	</div>
</div>


<div class="row-fluid">
	<div class="span12 searchBlock">
		<div class="span8">

			<font size="5"><strong>病历夹管理</strong> </font>
		</div>
		<div class="span4">
			<div class="pull-right">
				<a href="${SITE.context}/member/folderShow/list"
					class="btn "><i class="icon-plus icon-reply"></i>
					返回</a>&nbsp; <a href="${SITE.context}/member/folder/addshow"
					data-trigger="modal" data-cache="false"
					data-title="添加病例夹  <small>请把内容填写完整，然后提交保存。</small>"
					class="btn btn-success "><i class="icon-plus icon-white"></i>
					添加病例夹</a>
			</div>
		</div>
	</div>
</div>
<div class="space"></div>

<table class="table table-striped">
	<thead>
		<tr>
			<th>病历夹名称</th>
			<th>所属医院</th>
			<th>所属科室</th>
			<th>操作</th>
		</tr>
	</thead>

	<c:if test="${not empty pm.datas }">
		<tbody>
			<c:forEach items="${pm.datas}" var="caseFolder" varStatus="status">
				<tr>
					<td>${caseFolder.folderName }</td>
					<td>${hospitalMap[caseFolder.hospitalId] }</td>
					<td>${departmentsMap[caseFolder.departmentId] }</td>
					<td><a
						href="${SITE.context}/member/folder/edit/${caseFolder.id}"
						data-trigger="modal" data-cache="false"
						data-title="修改病例夹  <small>请把内容填写完整，然后提交保存。</small>"
						class="btn btn-mini btn-info"><i
							class="icon-pencil icon-white"></i> 修改</a> &nbsp; <a
						data-trigger="confirm"
						href="${SITE.context}/member/folder/delete/${caseFolder.id}"
						class="btn btn-mini btn-warning" data-content="确定要删除该病历夹吗？"> <i
							class="icon-remove icon-white"></i>删除</a> &nbsp; <a
						href="${SITE.context}/member/folder/impower/${caseFolder.id}/${caseFolder.patientId}"
						title="授权管理" class="btn btn-mini btn-primary"><i
							class="icon-user"></i> 授权管理</a> &nbsp; <a
						href="${SITE.context}/member/casehistory/list?folderId=${caseFolder.id}"
						title="病历资料管理" class="btn btn-mini btn-success"><i
							class="icon-cog"></i> 病历资料管理</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="4" align="center" bgcolor="#EFF3F7">没有找到相应的记录</td>
		</tr>
	</c:if>


	<c:if test="${not empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="4" class="bottom">
				<div class="pagination pagination-right row-fluid">
					<div class="span12">
						<span class="right"> <!-- 需要修改url地址 dx--> <pg:pager
								url="${SITE.context}/member/folder/list" items="${pm.total}"
								export="currentPageNumber=pageNumber" maxPageItems="${ps}"
								scope="request">
								<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
							</pg:pager> <!-- 用户选择每页显示行数下拉列表 --> <jsp:include
								page="/WEB-INF/jsp/pages/pager-select.jsp" flush="true" /></span>
					</div>
				</div></td>
		</tr>
	</c:if>

</table>



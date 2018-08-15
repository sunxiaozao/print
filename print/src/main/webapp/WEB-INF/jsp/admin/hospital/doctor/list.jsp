<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../../common/inc.jsp"%>

<table class="table table-striped">
	<thead>
		<tr>

			<th>账号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>职务</th>
			<th width=200px>操作</th>
		</tr>
	</thead>
	<c:if test="${!empty pm.datas}">
		<tbody>
			<c:forEach items="${pm.datas}" var="doctors" varStatus="status">
				<tr>
					<td>${doctors.username}</td>
					<td>${doctors.fullname}</td>
					<td>${sexMap[doctors.sex]}</td>
					<td>${doctors.title}</td>
					<td><a
						href="${SITE.context}/admin/doctor/edit/${doctors.id }"
						title="编辑" class="btn btn-mini btn-primary"><i
							class="icon-pencil icon-white"></i> 编辑</a> &nbsp; <a
						href="${SITE.context}/admin/doctor/delete/${doctors.id }/${doctors.departmentId}"
						title="删除" class="btn btn-mini btn-warning" data-trigger="confirm"
						data-content="确认删除(${doctors.fullname})吗？"><i
							class="icon-remove icon-white"></i> 删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="5" align="center" bgcolor="#EFF3F7">没有找到相应的记录</td>
		</tr>
	</c:if>
	<c:if test="${not empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="5" class="bottom">
				<div class="pagination pagination-right row-fluid">
					<div class="span12">
						<span> <!-- 需要修改url地址 dx--> <pg:pager
								url="${SITE.context}/admin/doctor/list" items="${pm.total}"
								export="currentPageNumber=pageNumber" maxPageItems="${ps}"
								scope="request">
								<pg:param name="departmentId" />
								<pg:param name="sex" />
								<pg:param name="fullname" />
								<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
							</pg:pager> <!-- 用户选择每页显示行数下拉列表 --> <jsp:include
								page="/WEB-INF/jsp/pages/pager-select.jsp" flush="true" /> </span>
					</div>
				</div></td>
		</tr>
	</c:if>
</table>
<div class="space"></div>
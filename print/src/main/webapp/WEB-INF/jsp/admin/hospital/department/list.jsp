<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../../common/inc.jsp"%>

<table class="table table-striped">
	<thead>
		<tr>

			<th>科室名称</th>
			<th>科室类型</th>
			<th>科室描述</th>
			<th width=200px>操作</th>
		</tr>
	</thead>
	<c:if test="${!empty pm.datas}">
		<tbody>
			<c:forEach items="${pm.datas}" var="departments" varStatus="status">
				<tr>
					<td>${departments.departmentName}</td>
					<td>${mp[departments.type]}</td>
					<td>${departments.description}</td>
					<td><a
						href="${SITE.context}/admin/department/edit/${departments.id }"
						title="编辑" class="btn btn-mini btn-primary"><i
							class="icon-pencil icon-white"></i> 编辑</a> &nbsp; <a
						href="${SITE.context}/admin/doctor/list?departmentId=${departments.id }"
						class="btn btn-mini btn-warning"><i
							class="icon-key icon-reorder"></i> 管理医生</a> &nbsp; <a
						href="${SITE.context}/admin/department/delete/${departments.id }/${departments.hospitalId}"
						title="删除" class="btn btn-mini btn-warning" data-trigger="confirm"
						data-content="确认删除(${departments.departmentName })吗？"><i
							class="icon-remove icon-white"></i> 删除</a>
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
						<span> <!-- 需要修改url地址 dx--> <pg:pager
								url="${SITE.context}/admin/department/list" items="${pm.total}"
								export="currentPageNumber=pageNumber" maxPageItems="${ps}"
								scope="request">
								<pg:param name="hospitalId" />
								<pg:param name="type" />
								<pg:param name="departmentName" />
								<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
							</pg:pager> <!-- 用户选择每页显示行数下拉列表 --> <jsp:include
								page="/WEB-INF/jsp/pages/pager-select.jsp" flush="true" /> </span>
					</div>
				</div>
			</td>
		</tr>
	</c:if>
</table>
<div class="space"></div>
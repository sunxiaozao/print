<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<table class="table table-striped">
	<thead>
		<tr>
			<th width="25%">
				角色名称
			</th>
			<th>
				角色描述
			</th>
			<th width="200">
				操作
			</th>
		</tr>
	</thead>
	<c:if test="${!empty pm.datas}">
	<tbody>
		<c:forEach items="${pm.datas}" var="role" varStatus="status">			
				<tr>
					<td>
						${role.roleName }
					</td>
					<td>
						${role.roleDesc }
					</td>
					<td>
						<a href="${SITE.context}/admin/role/edit/${role.roleId}" title="编辑" class="btn btn-mini btn-primary"><i class="icon-pencil icon-white"></i> 编辑</a> &nbsp;
						<a href="${SITE.context}/admin/role/delete/${role.roleId  }" title="删除" class="btn btn-mini btn-warning"
							data-trigger="confirm" data-content="确认删除角色(${role.roleName })吗？"><i class="icon-remove icon-white"></i> 删除</a> &nbsp;
						<a href="${SITE.context}/admin/role/view/${role.roleId  }" title="详细" class="btn btn-mini btn-info"><i class="icon-eye-open icon-white"></i> 详细</a>
					</td>
				</tr>			
		</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="3" align="center" bgcolor="#EFF3F7">
				没有找到相应的记录
			</td>
		</tr>
	</c:if>
	<c:if test="${not empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="3" class="bottom">
				<div class="pagination pagination-right row-fluid">
					<div class="span12">
						<span> <!-- 需要修改url地址 dx--> 
						    <pg:pager url="${SITE.context}/admin/role/list" items="${pm.total}" export="currentPageNumber=pageNumber"
								maxPageItems="${ps}" scope="request">
								<pg:param name="roleName" />
								<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
							</pg:pager> <!-- 用户选择每页显示行数下拉列表 --> 
							<jsp:include page="/WEB-INF/jsp/pages/pager-select.jsp" flush="true" />
						</span>
					</div>
				</div>
			</td>
		</tr>
	</c:if>
</table>
<div class="space"></div>
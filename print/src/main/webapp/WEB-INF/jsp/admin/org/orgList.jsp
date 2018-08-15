<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<table class="table table-striped">
	<thead>
		<tr>
			<th>
				组织机构名称
			</th>
			<th>
				状态
			</th>
			<th>
				创建人
			</th>
			<th>
				创建时间
			</th>
			<th>
				父节点
			</th>
			<th>
			   显示优先级
			</th>
			<th width=150px>
				操作
			</th>
		</tr>
	</thead>
	<c:if test="${!empty pm.datas}">
	<tbody>
		<c:forEach items="${pm.datas}" var="org" varStatus="status">			
				<tr>
					<td style="text-align:left; padding-left:10px; max-width: 150px;">
						${org.orgName}
					</td>
					<td>
						<c:if test="${org.status=='A'}">正常</c:if>
						<c:if test="${org.status=='B'}">登陆受限</c:if>
					</td>
					<td>
						${org.createBy}
					</td>
					<td style="max-width: 80px;">
						<fmt:formatDate value="${org.createDt}" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
						${orgMap[org.parentId] }
					</td>
					<td>
						${org.priority }
					</td>
					<td>
						<a href="${SITE.context}/admin/org/edit/${org.orgId}" title="编辑" class="btn btn-mini btn-primary"><i class="icon-pencil icon-white"></i> 编辑</a> &nbsp;
						<a href="${SITE.context}/admin/org/delete/${org.orgId}" title="删除" class="btn btn-mini btn-warning"
							data-trigger="confirm" data-content="确认删除机构(${org.orgName })吗？"><i class="icon-remove icon-white"></i> 删除</a> 
					</td>
				</tr>			
		</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${empty pm.datas}">
		<tr>
			<td colspan="7" align="center" bgcolor="#EFF3F7">
				没有找到相应的记录
			</td>
		</tr>
	</c:if>
	<c:if test="${not empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="7" class="bottom">
				<div class="pagination pagination-right row-fluid">
					<div class="span12">
						<span class="right"> <!-- 需要修改url地址 dx--> <pg:pager url="${SITE.context}/admin/org/list" items="${pm.total}" export="currentPageNumber=pageNumber"
								maxPageItems="${ps}" scope="request">
								<pg:param name="orgName" />
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


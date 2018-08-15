<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<table class="table table-striped">
	<thead>
		<tr>
			<th>
				用户
			</th>
			<th>
				功能
			</th>
			<th>
				描述
			</th>
			<th>
				IP地址
			</th>
			<th>
				操作时间
			</th>
			<th>
				操作
			</th>
		</tr>
	</thead>
	<c:if test="${!empty pm.datas}">
	<tbody>
		<c:forEach items="${pm.datas}" var="sysLog" varStatus="status">			
				<tr>
					<td>
						${sysLog.userName }
					</td>
					<td>
						${sysLog.functionName }
					</td>
					<td style="text-align: left; padding-left: 20px;">
						${sysLog.description }
					</td>
					<td>
						${sysLog.ip }
					</td>
					<td>
						<fmt:formatDate value="${sysLog.cdate }" type="date" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
						<!-- <a href="${SITE.context}/admin/syslog/delete/${group.groupId}${sysLog.logId}" title="删除" class="btn btn-mini btn-warning"
							data-trigger="confirm" data-content="确认删除该日志吗？"><i class="icon-remove icon-white"></i> 删除</a>-->
					</td>
				</tr>			
		</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="6" align="center" bgcolor="#EFF3F7">
				没有找到相应的记录
			</td>
		</tr>
	</c:if>
	<c:if test="${not empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="6" class="bottom">
				<div class="pagination pagination-right row-fluid">
					<div class="span12">
						<span class="right"> <!-- 需要修改url地址 dx--> 
						<pg:pager url="${SITE.context}/admin/syslog/list" items="${pm.total}" export="currentPageNumber=pageNumber"
								maxPageItems="${ps}" scope="request">
								<pg:param name="userName" />
								<pg:param name="description" />
								<pg:param name="cdateFrom" />
								<pg:param name="cdateTo" />
								<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
							</pg:pager> <!-- 用户选择每页显示行数下拉列表 --> 
							<jsp:include page="/WEB-INF/jsp/pages/pager-select.jsp" flush="true" /></span>
					</div>
				</div>
			</td>
		</tr>
	</c:if>
</table>
<div class="space"></div>
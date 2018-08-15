<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<table class="table table-striped">
	<thead>
		<tr>
			<th>
				标题
			</th>
			<th>
				内容
			</th>
			<th>
				用户类型
			</th>
			<th>
			    创建时间
			</th>
			<th>
				消息状态
			</th>
			<th>
				操作
			</th>
		</tr>
	</thead>
	<c:if test="${!empty pm.datas}">
	<tbody>
		<c:forEach items="${pm.datas}" var="userMessage" varStatus="status">			
				<tr>
					<td>
						${userMessage.title }
					</td>
					<td>
						${userMessage.content }
					</td>
					<td>
					    <c:if test="${userMessage.userType=='A'}">${userTypeMap[userMessage.userType]}</c:if>
						<c:if test="${userMessage.userType=='B'}">${userTypeMap[userMessage.userType]}(${orgMap[userMessage.orgId]})</c:if>
						<c:if test="${userMessage.userType=='C'}">${userTypeMap[userMessage.userType]}(${groupMap[userMessage.groupId]})</c:if>
						<c:if test="${userMessage.userType=='D'}">${userTypeMap[userMessage.userType]}()</c:if>
					</td>
					<td>
					   <fmt:formatDate value="${userMessage.createDt}" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long" />
					</td>
					<td>
						<c:if test="${userMessage.isGenerated eq 'N'}">
							<a href="javascript:void(0)" name="publishV" mId="${userMessage.messageId }">发布消息</a>
						</c:if>
						<c:if test="${userMessage.isGenerated eq 'Y'}">已发布(${userMessage.msgCount })</c:if>
					</td>
					<td>
						<c:if test="${userMessage.isGenerated eq 'N'}">
							<a href="${SITE.context}/admin/message/edit/${userMessage.messageId  }"  title="编辑" class="btn btn-mini btn-primary"><i class="icon-pencil icon-white"></i> 编辑</a> &nbsp;
					        <a href="${SITE.context}/admin/message/delete/${userMessage.messageId  }" title="删除" class="btn btn-mini btn-warning"
							data-trigger="confirm" data-content="确认删除该消息吗？"><i class="icon-remove icon-white"></i> 删除</a>
					    </c:if>
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
						<pg:pager url="${SITE.context}/admin/message/list" items="${pm.total}" export="currentPageNumber=pageNumber"
								maxPageItems="${ps}" scope="request">
								<pg:param name="title" />
								<pg:param name="userType" />
								<pg:param name="isGenerated" />
								<pg:param name="createDtFrom" />
								<pg:param name="createDtTo" />
								<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
							</pg:pager> <!-- 用户选择每页显示行数下拉列表 --> 
							<jsp:include page="/WEB-INF/jsp/pages/pager-select.jsp" flush="true" /></span>
					</div>
				</div>
			</td>
		</tr>
	</c:if>
</table>
<div id="dialog-confirm-publish" title="发布版本" style="display: none">
	<p>
		确定要发布消息吗？
	</p>
</div>
<div class="space"></div>
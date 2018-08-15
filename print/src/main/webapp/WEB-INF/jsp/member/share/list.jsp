<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<table class="table table-striped">
	<thead>
		<tr>
			<th>病历名称</th>
			<th>病历资料查看地址</th>
			<th>密码</th>
			<th>备注</th>
			<th>分享时间</th>
			<th>查看状态</th>
			<th width="150">操作</th>
		</tr>
	</thead>
	<c:if test="${!empty pm.datas}">
		<tbody>
			<c:forEach items="${pm.datas}" var="l" varStatus="status">
				<tr>
					<td>${l.cpfCaseHistory.caseName }</td>
					<td>< <a href="${SITE.context}/member/casehistory/show?hid=${l.cpfCaseHistory.id}" target="_blank"> ${l.url }</a></td>

					<td>${l.password }</td>
					<td>${l.content }</td>
					<td><fmt:formatDate value="${l.createTime  }" type="date" dateStyle="long" pattern="yyyy-MM-dd" />
					</td>
					<td>${viewStatuMap[l.status] }</td>
					<td>
						<%--<a href="${SITE.context}/member/deleteShare/${l.id}" 
							 		class="btn btn-mini">删除</a> &nbsp; --%> <a data-atype="modal" data-title="查看留言" data-height="400px" data-width="large" href="${SITE.context }/notebook/patient/list/${l.cpfCaseHistory.id}" class="btn btn-mini btn-primary">查看留言</a> <%--<a href="${SITE.context}/member/cancelShare/${l.id}" 
								data-content="确定取消该分享？" data-trigger="confirm"
								class="btn btn-mini btn-inverse">取消分享</a> &nbsp;
							--%></td>
				</tr>
			</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="8" align="center" bgcolor="#EFF3F7">没有找到相应的记录</td>
		</tr>
	</c:if>
	<c:if test="${not empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="8" class="bottom">
				<div class="pagination pagination-right row-fluid">
					<div class="span12">
						<span class="right"> <!-- 需要修改url地址 dx--> <pg:pager url="${SITE.context}/member/share/list" items="${pm.total}" export="currentPageNumber=pageNumber" maxPageItems="${ps}" scope="request">
								<pg:param name="mobile" />
								<pg:param name="email" />
								<pg:param name="status" />
								<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
							</pg:pager> <!-- 用户选择每页显示行数下拉列表 --> <jsp:include page="/WEB-INF/jsp/pages/pager-select.jsp" flush="true" /></span>
					</div>
				</div></td>
		</tr>
	</c:if>

</table>

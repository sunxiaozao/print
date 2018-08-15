<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<table class="table table-striped">
	<thead>
		<tr>
			<th>用户</th>
			<th>性别 </th>
			<th>出生日期 </th>
			<th>手机</th>
			<th>邮箱</th>
			<th>医保卡号 </th>
			<th>身份证号 </th>
			<th width=200px>操作</th>
		</tr>
	</thead>
	<c:if test="${!empty pm.datas}">
	<tbody>
		<c:forEach items="${pm.datas}" var="user" varStatus="status">			
				<tr>
					<td>
						${user.userName }
					</td>
					<td>
						${sexMap[user.sex]}
					</td>
					<td>
						<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />
					</td>
					<td>
						${user.mobile }
					</td>
					<td>
						${user.email }
					</td>
					<td>
						${user.cardId }
					</td>
					<td>
						${user.identityCard  }
					</td>
					<td>
						<a  data-trigger="modal" href="${SITE.context}/admin/patient/changePass/${user.id }" data-title="重置密码" class="btn btn-mini btn-primary"><i class="icon-pencil icon-white"></i> 重置密码</a> &nbsp;
					</td>
				</tr>			
		</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="8" align="center" bgcolor="#EFF3F7">
				没有找到相应的记录
			</td>
		</tr>
	</c:if>
	<c:if test="${not empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="8" class="bottom">
				<div class="pagination pagination-right row-fluid">
					<div class="span12">
						<span> <!-- 需要修改url地址 dx--> 
						<pg:pager url="${SITE.context}/admin/patient/list" items="${pm.total}" export="currentPageNumber=pageNumber" maxPageItems="${ps}"
								scope="request">
								<pg:param name="userName" />
								<pg:param name="email" />
								<pg:param name="identityCard" />
								<pg:param name="cardId" />
								<pg:param name="mobile" />
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
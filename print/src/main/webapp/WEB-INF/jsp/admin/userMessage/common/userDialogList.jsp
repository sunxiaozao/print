<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../../common/inc.jsp"%>
<table class="table table-hover table-bordered " >
 <thead >
	<tr>
		<th >
			ID
		</th>	
		<th>
			用户名
		</th>
		<th>
			真实姓名:
		</th>
		<th width="230px">
			Email
		</th>
	</tr>
	</thead>
	<c:if test="${!empty pm.datas}">
		<c:forEach items="${pm.datas}" var="user" varStatus="status">
		
			<tr ondblclick="getUserId(${user.userId},<c:choose><c:when test="${empty user.orgId}">-1</c:when><c:otherwise>${user.orgId}</c:otherwise></c:choose>)">
				<td>
					${user.userId}
				</td>			
				<td>
					${user.userName}
				</td>
				<td>
					${user.realName}
				</td>
				<td>
					${user.email }
				</td>
			
		</c:forEach>
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
	<td colspan="4" class="bottom">
		<div class="pagination pagination-right row-fluid">
	         <div class="span12">
				<span class="right"> <!-- 需要修改url地址 dx--> <pg:pager url="getMemberList.do" items="${pm.total}" export="currentPageNumber=pageNumber" maxPageItems="${ps}" scope="request">
						<pg:param name="method" />
						<pg:param name="dialog_userName" />
						<pg:param name="dialog_email" />
						<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
					</pg:pager></span>
		        </div>
		   </div>
		</td>
		</tr>
	</c:if>
</table>
<script type="text/javascript">
/* 
$("a[name='page_link']").live("click",function(){
			var dynamicUserDiv = $("#dynamicUserDiv");
			$.post($(this).attr("href"),function (resp) {
				dynamicUserDiv.html(resp);
			}, "html");
			return false;
		});
 */
</script>
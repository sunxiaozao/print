<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="../../tld/pager-taglib.tld" prefix="pg"%> 
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<pg:first>
	<a name="page_link" href="${pageUrl}" id="firstpageurl">首页</a>
</pg:first>
<pg:prev>
	<a name="page_link" href="${pageUrl }">上一页</a>
</pg:prev>
<pg:pages>
	<c:choose>
		<c:when test="${currentPageNumber eq pageNumber}">
			<a href="javascript:;" class="now">${pageNumber }</a>
		</c:when>
		<c:otherwise>
			<a name="page_link" href="${pageUrl }">${pageNumber }</a>
		</c:otherwise>
	</c:choose>
</pg:pages>
<pg:next>
	<a name="page_link" href="${pageUrl }">下一页</a>
</pg:next>
<pg:last>
	<a name="page_link" href="${pageUrl }">尾页</a>
</pg:last>
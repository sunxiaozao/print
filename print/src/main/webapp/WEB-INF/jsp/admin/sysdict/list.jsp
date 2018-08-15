<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<table class="table table-striped">
	<thead>
		<tr>
			<th>
				代码
			</th>
			<th>
				名称
			</th>
			<th>
				类别
			</th>
			<th>
				优先级
			</th>
			<th width="150">
				操作
			</th>
		</tr>
	</thead>
	<c:if test="${!empty pm.datas}">
	<tbody>
		<c:forEach items="${pm.datas}" var="sysDict" varStatus="status">			
				<tr>
					<td>
						${sysDict.shortCode }
					</td>
					<td>
						${sysDict.longName }
					</td>

					<td>
						<c:forEach items="${sysDictTypeMap}" var="sysDictType">
							<c:if test="${sysDictType.key  == sysDict.type }">
							 	${sysDictType.value}
							 </c:if>
						</c:forEach>
					</td>
					<td>
						${sysDict.priority }
					</td>
					<td>
						<a href="${SITE.context}/admin/dict/edit/${sysDict.dictId  }"  title="编辑" class="btn btn-mini btn-primary"><i class="icon-pencil icon-white"></i> 编辑</a> &nbsp;
						<a href="${SITE.context}/admin/dict/delete/${sysDict.dictId  }"   title="删除" class="btn btn-mini btn-warning"
							data-trigger="confirm" data-content="确认删除字典(${sysDict.longName })吗？"><i class="icon-remove icon-white"></i> 删除</a>
					</td>
				</tr>			
		</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="5" align="center" bgcolor="#EFF3F7">
				没有找到相应的记录
			</td>
		</tr>
	</c:if>
	<c:if test="${not empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="5" class="bottom">
				<div class="pagination pagination-right row-fluid">
					<div class="span12">
						<span class="right"> <!-- 需要修改url地址 dx--> 
						<pg:pager url="${SITE.context}/admin/dict/list" items="${pm.total}" export="currentPageNumber=pageNumber"
								maxPageItems="${ps}" scope="request">
								<pg:param name="type" />
								<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
							</pg:pager> <!-- 用户选择每页显示行数下拉列表 --> <jsp:include page="/WEB-INF/jsp/pages/pager-select.jsp" flush="true" /></span>
					</div>
				</div>
			</td>
		</tr>
	</c:if>
</table>
<div class="space"></div>
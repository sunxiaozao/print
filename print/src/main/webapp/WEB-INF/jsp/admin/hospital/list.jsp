<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<table class="table table-striped">
	<thead>
		<tr>
			
			<th>
				医院编号
			</th>
			<th>
				医院名称
			</th>
			<th>
				医院类型
			</th>
			<th>
				医院描述
			</th>
			<th width=300px>
				操作
			</th>
		</tr>
	</thead>
 <c:if test="${!empty pm.datas}">
	<tbody>
		<c:forEach items="${pm.datas}" var="hospitals" varStatus="status">			
				<tr>
					<td style="text-align:left; padding-left:10px;">
						${hospitals.hospitalId}
					</td>
					<td>
						${hospitals.name}
					</td>
					<td>
						${mp[hospitals.type]}
					</td>
					<td>
						${hospitals.description}
					</td>
					<td>
						<a href="${SITE.context}/admin/hospital/edit/${hospitals.id }" title="编辑" class="btn btn-mini btn-primary"><i class="icon-pencil icon-white"></i> 编辑</a> &nbsp;
						<a href="${SITE.context}/admin/department/list?hospitalId=${hospitals.id }"  class="btn btn-mini btn-warning"><i class="icon-key icon-reorder"></i> 管理科室</a> &nbsp;
						<!--  <a href="${SITE.context}/admin/department/list?hospitalId=${hospitals.id }"  class="btn btn-mini btn-warning"><i class="icon-key icon-reorder"></i> 管理医生</a> &nbsp;-->
						<a href="${SITE.context}/admin/hospital/delete/${hospitals.id }" title="删除" class="btn btn-mini btn-warning"
							data-trigger="confirm" data-content="确认删除(${hospitals.name })吗？"><i class="icon-remove icon-white"></i> 删除</a>
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
						<span> <!-- 需要修改url地址 dx--> 
						<pg:pager url="${SITE.context}/admin/hospital/list" items="${pm.total}" export="currentPageNumber=pageNumber" maxPageItems="${ps}"
								scope="request">
								<pg:param name="name" />
								<pg:param name="type" />
								<pg:param name="hospitalId" />
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
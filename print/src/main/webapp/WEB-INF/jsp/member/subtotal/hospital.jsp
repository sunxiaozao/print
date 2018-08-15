<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<form action="" method="post" class="form-horizontal">
	<div class="m-iframe-content">
		<table class="table table-hover table-bordered table-condensed">
			<thead>
				<tr>
					<th>医院编号</th>
					<th>医院名称</th>
					<th>医院类别</th>
				</tr>
			</thead>
			<c:if test="${!empty caseCount}">
				<tbody>
					<c:forEach items="${caseCount}" var="l" varStatus="status">
						<tr>
							<td>${l.sysHospital.hospitalId }</td>
							<td>${l.sysHospital.name  }</td>
							<td>${mp[l.sysHospital.type]  }</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:if>
			<c:if test="${empty caseCount}">
				<tr>
					<!-- 需要修改列数dx -->
					<td colspan="3" align="center" bgcolor="#EFF3F7">没有找到相应的记录</td>
				</tr>
			</c:if>
			
		</table>

	</div>

	<div class="m-emodal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
	</div>
</form>

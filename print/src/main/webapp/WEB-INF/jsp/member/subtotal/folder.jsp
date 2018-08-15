<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<form action="" method="post" class="form-horizontal">
	<div class="m-iframe-content">
		<table class="table table-hover table-bordered table-condensed">
			<thead>
				<tr>
					<th>病历夹名称</th>
					<th>描述</th>
				</tr>
			</thead>
			<c:if test="${!empty folderCount}">
				<tbody>
					<c:forEach items="${folderCount}" var="l" varStatus="status">
						<tr>
							<td>${l.folderName }</td>
							<td>${l.description  }</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:if>
			<c:if test="${empty folderCount}">
				<tr>
					<!-- 需要修改列数dx -->
					<td colspan="2" align="center" bgcolor="#EFF3F7">没有找到相应的记录</td>
				</tr>
			</c:if>
			
		</table>

	</div>

	<div class="m-emodal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
	</div>
</form>

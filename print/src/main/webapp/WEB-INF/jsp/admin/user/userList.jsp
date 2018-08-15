<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<table class="table table-striped">
	<thead>
		<tr>
			<th>
				用户
			</th>
			<th>
				类型
			</th>
			<th>
				手机
			</th>
			<th>
				角色
			</th>
			<th>
				状态
			</th>			
			<th>
				最后登陆
			</th>
			<th width=200px>
				操作
			</th>
		</tr>
	</thead>
	<c:if test="${!empty pm.datas}">
	<tbody>
		<c:forEach items="${pm.datas}" var="user" varStatus="status">			
				<tr>
					<td style="text-align:left; padding-left:10px;">
						${user.userName }
						<c:if test="${not empty user.realName }">
							(${user.realName })
						</c:if>
					</td>
					<td>
						${map[user.userType]}
					</td>
					<td>
						${user.mobile }
					</td>
					<td>
						${user.role.roleName }
					</td>
					<td>
					<c:if test="${user.enabled eq 1 }">
						已审核
					</c:if>
					<c:if test="${user.enabled eq 0 }">
						<a href="${SITE.context}/admin/user/audit/${user.userId }" title="审核" class="btn btn-mini btn-warning"
							data-trigger="confirm" data-content="确认审核通过用户(${agencyMap[user.agencyId] }:${user.userName })吗？"><i class="icon-ok icon-white"></i> 审核</a>
					</c:if>
					</td>
					<td>
						<fmt:formatDate value="${user.lastLoginDt }" type="date" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					<td>
						<a href="${SITE.context}/admin/user/edit/${user.userId }" title="编辑" class="btn btn-mini btn-primary"><i class="icon-pencil icon-white"></i> 编辑</a> &nbsp;
						<a href="${SITE.context}/admin/user/delete/${user.userId }" title="删除" class="btn btn-mini btn-warning"
							data-trigger="confirm" data-content="确认删除用户(${user.userName })吗？"><i class="icon-remove icon-white"></i> 删除</a>
					</td>
				</tr>			
		</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="7" align="center" bgcolor="#EFF3F7">
				没有找到相应的记录
			</td>
		</tr>
	</c:if>
	<c:if test="${not empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="7" class="bottom">
				<div class="pagination pagination-right row-fluid">
					<div class="span12">
						<span> <!-- 需要修改url地址 dx--> 
						<pg:pager url="${SITE.context}/admin/user/list" items="${pm.total}" export="currentPageNumber=pageNumber" maxPageItems="${ps}"
								scope="request">
								<pg:param name="userName" />
								<pg:param name="roleId" />
								<pg:param name="lastLoginDtFrom" />
								<pg:param name="lastLoginDtTo" />
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
<div id="changPassDiv" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:400px;">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">修改密码</h3>
  </div>
  <div class="modal-body">
	<form action="" id="updatePassForm" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">输入新密码：</label>
			<div class="controls">
				<input type="password" id="password" name="password" class="input-small validate[required,minSize[5],maxSize[20]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确认新密码：</label>
			<div class="controls">
				<input type="password" id="confirmPassword" name="confirmPassword" class="input-small validate[required,minSize[5],maxSize[20]]" >
			</div> 
		</div>
	</form>
  </div>
  <div class="modal-footer">
    <button class="btn btn-primary" id="savePassButton"><i class="icon-ok icon-white"></i> 保存</button>
  </div>
</div>
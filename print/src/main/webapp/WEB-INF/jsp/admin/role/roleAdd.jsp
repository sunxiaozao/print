<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="roleAdd" action="${SITE.context}/admin/role/saveAdd" method="post" class="form-horizontal">

		<div class="form-box-title">
			<h3>
				新增角色
				<small>请把需要赋予的权限打钩，然后提交保存。</small>
			</h3>
		</div>
		<div class="control-group">
			<label class="control-label">
				角色名称：
			</label>
			<div class="controls">
				<input type="hidden" name="roleId" value="${role.roleId}">
				<input type="text" id="roleName" name="roleName"
					value="${role.roleName }"
					class="input w370 validate[required,maxSize[20]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				角色描述：
			</label>
			<div class="controls">
				<textarea rows="5" cols="80" name="roleDesc" class="textarea ">${role.roleDesc }</textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				角色权限：
			</label>
			<div class="controls">
				<table class="table table-bordered table-condensed" style="border-width: 1px; width: 550px; ">
					<tr>
						<td class="baseInfoLabel" style="text-align: right; width: 23%">
								权限管理
							</td>
							<td style="text-align: left; padding-left:10px;">
							<c:forEach items="${priveledgeList}" var="function" varStatus="status">						
								<c:forEach items="${function.functionCrudList}"	var="functionCrudVO"  varStatus="vs">
								<label class="checkbox inline" style="margin-left:10px;">
									<input type="checkbox" name="functionCrudRelId"
									<c:if test="${relRoleFuncMap[functionCrudVO.relId]!=null}">checked</c:if> 
									value="${functionCrudVO.relId}">
									<c:choose><c:when test="${fn:length(function.functionCrudList)>1}">${functionCrudVO.description}</c:when>
									<c:otherwise>${function.functionName}</c:otherwise></c:choose>
								</label>
								</c:forEach>						
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td class="baseInfoLabel" style="text-align: right; width: 23%">
								系统管理
							</td>
							<td style="text-align: left; padding-left:10px;">
							<c:forEach items="${sysList}" var="function" varStatus="status">						
								<c:forEach items="${function.functionCrudList}"	var="functionCrudVO"  varStatus="vs">
								<label class="checkbox inline" style="margin-left:10px;">
									<input type="checkbox" name="functionCrudRelId"
									<c:if test="${relRoleFuncMap[functionCrudVO.relId]!=null}">checked</c:if> 
									value="${functionCrudVO.relId}">
									<c:choose><c:when test="${fn:length(function.functionCrudList)>1}">${functionCrudVO.description}</c:when>
									<c:otherwise>${function.functionName}</c:otherwise></c:choose>
								</label>
								</c:forEach>						
							</c:forEach>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="control-group">
			<div class="controls" style="margin-left: 300px;">
				<button type="submit" class="btn btn-primary" id="submit">
					<i class="icon-ok icon-white"></i> 保存
				</button>
				&nbsp;&nbsp;
				<button type="button" class="btn"
					onclick="javascript:history.go(-1)">
					返回
				</button>
			</div>
		</div>
	</form>
</div>
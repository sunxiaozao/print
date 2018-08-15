<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form action="" class="form-horizontal">
		<div class="form-box-title">
			<h3>
				角色详细
				<small>若需要修改，请在列表页点击修改按钮</small>
			</h3>
		</div>

		<input type="hidden" name="roleId" value="${role.roleId}">
		<div class="control-group">
			<label class="control-label">
				角色名称：
			</label>
			<div class="controls">
				${role.roleName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				角色描述：
			</label>
			<div class="controls">
				<textarea rows="5" cols="80" name="roleDesc" class="textarea" readonly="readonly" style="background-color: #FCFCFC">${role.roleDesc }</textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				角色权限：
			</label>
			<div class="controls">
				<table class="table table-bordered table-condensed" style="border-width: 1px; width: 550px;">
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
									value="${functionCrudVO.relId}" disabled>
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
									value="${functionCrudVO.relId}" disabled>
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
				<button type="button" class="btn" id="return" onclick="javascript:history.go(-1)">
					返回
				</button>
			</div>
		</div>
	</form>
</div>
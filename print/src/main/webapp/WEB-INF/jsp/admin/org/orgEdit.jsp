<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="orgEdit" action="${SITE.context}/admin/org/saveEdit" method="post" class="form-horizontal">
		<div class="form-box-title">
			<h3>
				修改组织机构
				<small>请填写组织机构信息，然后保存。</small>
			</h3>
		</div>
		<div class="control-group">
			<label class="control-label">
				组织机构名称：
				<input type="hidden" name="orgId" value="${org.orgId}">
			</label>
			<div class="controls">
				<input type="text" id="orgName" name="orgName" value="${org.orgName}" class="input w370 validate[required,maxSize[200]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				机构状态：
			</label>
			<div class="controls">
				<label class="radio inline">
					<input type="radio" name="status" value="A" <c:if test="${org.status eq 'A'}" >checked</c:if>>
					正常
				</label>
				<label class="radio inline">
					<input type="radio" name="status" value="B" <c:if test="${org.status eq 'B'}" >checked</c:if>>
					登陆受限
				</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				父节点：
			</label>
			<div class="controls">
				<select id="parentId" name="parentId" class="input w370 validate[maxSize[20]]">
					<option value="">
						请选择父节点
					</option>
					<c:forEach items="${parentIdlist}" var="list" varStatus="status">
						<option value="${list.orgId }" <c:if test="${org.parentId==list.orgId }">selected='selected'</c:if>>
							${list.orgName }
						</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				显示优先级：
			</label>
			<div class="controls">
				<input type="text" id="priority" name="priority" value="${org.priority }" class="input w90 validate[required,custom[number]]">
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button type="button" class="btn btn-primary" id="orgEditSubmit">
					<i class="icon-ok icon-white"></i> 保存
				</button>
				&nbsp;&nbsp;
				<a class="btn" href="${SITE.context}/admin/org/list">
					返回
				</a>
			</div>
		</div>
	</form>
</div>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="groupEdit" action="${SITE.context}/admin/group/saveEdit" method="post"
		class="form-horizontal">
		<div class="form-box-title">
			<h3>
				修改组
				<small>请填写组信息，然后保存。</small>
			</h3>
		</div>
		<div class="control-group">
			<label class="control-label">
				组名称：
				<input type="hidden" name="groupId" value="${group.groupId}">
			</label>
			<div class="controls">
				<input type="text" id="groupName" name="groupName"
					value="${group.groupName}"
					class="input w370 validate[required,maxSize[20]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				组状态：
			</label>
			<div class="controls">
				<label class="radio inline">
					<input type="radio" name="status" value="A" <c:if test="${group.status eq 'A'}" >checked</c:if>>正常
				</label>
				<label class="radio inline">
					<input type="radio" name="status" value="B" <c:if test="${group.status eq 'B'}" >checked</c:if>>登陆受限
				</label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				父节点：
			</label>
			<div class="controls">
					 <select id="parentId" name="parentId" class="input w370 validate[maxSize[20]]">
					 	<option value="">请选择父节点</option>
			             <c:forEach items="${parentIdlist}" var="list" varStatus="status">
			              <option value="${list.groupId }" <c:if test="${group.parentId==list.groupId }">selected='selected'</c:if>>${list.groupName }</option>
			             </c:forEach>
			         </select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				显示优先级：
			</label>
			<div class="controls">
				<input type="text" id="priority" name="priority"
					value="${group.priority }"
					class="input w90 validate[required,custom[number]]">
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button type="button" class="btn btn-primary" id="groupEditSubmit">
					<i class="icon-ok icon-white"></i> 保存
				</button>
				&nbsp;&nbsp;
				<a class="btn" href="${SITE.context}/admin/group/list">
					返回
				</a>
			</div>
		</div>
	</form>
</div>
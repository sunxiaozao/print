<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="orgAdd" action="${SITE.context}/admin/org/saveAdd" method="post"
		class="form-horizontal">

		<div class="form-box-title">
			<h3>
				新增组织机构
				<small>请填写组织机构信息，然后保存。</small>
			</h3>
		</div>
		<div class="control-group">
			<label class="control-label">
				组织机构名称：
				<input type="hidden" name="orgId" value="${org.orgId}">
			</label>
			<div class="controls">
				<input type="text" id="orgName" name="orgName"
					value="${org.orgName }"
					class="input w370 validate[required,maxSize[200]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				机构状态：
			</label>
			<div class="controls">
				<label class="radio inline">
					<input type="radio" name="status" value="A" checked>正常
				</label>
				<label class="radio inline">
					<input type="radio" name="status" value="B">登陆受限
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
			          <c:forEach items="${list}" var="org" varStatus="status">
			          <option value="${org.orgId }">${org.orgName }</option>
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
					value="0"
					class="input w370 validate[required,maxSize[20],custome[number]]">
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
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

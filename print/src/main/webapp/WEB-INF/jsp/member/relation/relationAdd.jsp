<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<form id="addRelation" action="" method="post" class="form-horizontal">
	
	<div class="form-box-title">
		<h3>
			添加我关注的
		</h3>
	</div>
	
	<input type="hidden" value="" id="typeVal" name="typeVal"/>
	<input type="hidden" value="" id="relatePatientId" name="relatePatientId"/>
	
	<div class="m-iframe-content">
		<div class="control-group">
			<label class="control-label">该用户账号类型： </label>
			<div class="controls">
				<select name="type" id="type">				
					<c:forEach items="${relationUserMap}" var="types">
						<option 
							<c:if test="${types.key  == typeVal }">selected</c:if>
						value="${types.key}">${types.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">该用户的账号：</label>
			<div class="controls">
				<input value="${userName }" class="input-large w370 validate[required,maxSize[20]]"
					type="text" name="userName" id="userName" />
				
				<a class="btn btn-info" id="checkRelation">检查是否已关注</a>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">申请理由：</label>
			<div class="controls">
				<input value="${applyReason }" class="input-large w370 validate[required,maxSize[50]]"
					type="text" name="applyReason" id="applyReason" />
			</div>
		</div>
	</div>
	
	<div class="control-group">
		<div class="controls">
			<button type="button" class="btn btn-primary" id="addRelationSubmit">
			<i class="icon-ok icon-white"></i> 提交
		</button>
		&nbsp;&nbsp;
		<a class="btn" href="${SITE.context}/member/relation/list">
			返回 </a>		
		</div>
	</div>
	
	
</form>

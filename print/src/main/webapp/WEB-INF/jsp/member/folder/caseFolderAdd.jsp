<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/pagestart.jsp"%>



<form action="${SITE.context}/member/folder/add" class="form-horizontal" id="addFolder" method="post">
	<div class="m-iframe-content m-scroll m-h300">
		<div class="control-group">
			<label class="control-label"> 病历夹名称： </label>
			<div class="controls">
				<input type="text" id="folderName" name="folderName"
					value="${caseFolder.folderName }"
					class="input validate[required,maxSize[20]]"> <input
					type="hidden" id="id" name="id" value="${caseFolder.id }">
				<input type="hidden" id="patientId" name="patientId"
					value="${caseFolder.patientId }">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 所属医院： </label>
			<div class="controls">
				<select class="input-large m-wrap" tabindex="1" id="hospital"
					name="hospital">
					<c:forEach items="${hospitalMp}" var="m" varStatus="status">
						<option value="${m.key }">${m.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 所属科室： </label>
			<div class="controls">
				<select class="input-large m-wrap" tabindex="1" id="departmentId"
					name="departmentId">
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 病历夹描述： </label>
			<div class="controls">
				<textarea class="input-large" rows="3" name="description">${caseFolder.description }</textarea>
			</div>
		</div>
	</div>
	<div class="m-emodal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">返回</button>
		<button class="btn btn-primary">保存</button>
	</div>
</form>


<script type="text/javascript">
	$(function() {
		$("#addFolder").validationEngine();
		var text=$('#hospital').val();
		module.folder.loadDepartment(text.substring(
				text.indexOf(',') + 1));
		module.folder.changeHospital();
	});
</script>









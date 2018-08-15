<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>

<form action="${SITE.context}/member/casehistory/relation" class="form-horizontal" method="post">
	<div class="m-iframe-content" onload="a()" id="divID">
		<div class="control-group">
			<label class="control-label"> 所属病历夹: </label>
			<div class="controls">
				<select name="folderId" id="folderId">
					<c:forEach items="${caseFolders}" var="caseFolder">
						<option value="${caseFolder.id}">${caseFolder.folderName}</option>
					</c:forEach>
				</select>
				<input type="hidden" value="${caseHistoryId}" name="caseHistoryId" id="caseHistoryId">
				<input type="hidden" value="${patientId}" name="patientId">
			</div>
		</div>

	</div>

	<div class="m-emodal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		<button class="btn btn-primary">关联</button>
	</div>
</form>


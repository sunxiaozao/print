<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../../common/pagestart.jsp"%>

<form action=""
	class="form-horizontal" id="notebookAdd" method="post">
	<div class="m-iframe-content">

		

		
		
		<div class="control-group">
			<label class="control-label"> 留言： </label>
			<div class="controls">
			<input type="hidden" name="patientId" value="${patientId}">
			<input type="hidden" name="caseHistoryId" value="${caseHistoryId}">
			<textarea class="validate[required] text-input" rows="3" name="content" id="content"></textarea>
				
			</div>
		</div>
		
	</div>
	<div class="m-emodal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		<a  id="sub" href="javascript:;"class="btn btn-primary" >完成</a>
	</div>
</form>
<script>
	$(function() {
		module.casehistory.notebook();

	});
</script>

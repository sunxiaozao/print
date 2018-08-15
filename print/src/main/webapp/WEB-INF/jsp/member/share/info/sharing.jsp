<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../../common/pagestart.jsp"%>

<form action="" class="form-horizontal" id="charingAdd" method="post">
	<div class="m-iframe-content">





		<div class="control-group">
			<label class="control-label"> 账号名称： </label>
			<div class="controls">
				<input value="" class="validate[required,minSize[6]] text-input" type="text" name="userName" id="userName" /> <input value="${shareid }" type="hidden" name="shareid" id="shareid" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 密码： </label>
			<div class="controls">
				<input class="validate[required,minSize[6]] text-input" type="password" name="password" id="password" />
			</div>
		</div>

	</div>
	<div class="m-emodal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		<a id="sub" href="javascript:;" class="btn btn-primary">完成</a>
	</div>
</form>
<script>
	$(function() {
		module.common.showPopupWindow(1)
		module.casehistory.charing();
	});
</script>

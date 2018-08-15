<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../../common/pagestart.jsp"%>


<form action="${SITE.static_context}/admin/user/userImport" class="form-horizontal" enctype="multipart/form-data" method="post" onsubmit="javascript:return onsub();">
	<div class="">
		<div class="control-group"></div>
		<div class="control-group">
			<label class="control-label"> 用户资料(Excel)</label>
			<div class="controls">
				<div data-provides="fileupload" class="fileupload fileupload-new">
					<span class="btn btn-mini btn-file"> <span class="fileupload-new">选择文件</span> <span class="fileupload-exists">重新选择</span> <input type="file" class="default" name="file" id="file"> </span> <span class="fileupload-preview"></span> <a style="float: none" data-dismiss="fileupload" class="close fileupload-exists" href="#">×</a>

				</div>
			</div>
		</div>
	</div>
	<div class="m-emodal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">返回</button>
		<button class="btn btn-primary">保存</button>
	</div>
</form>

<script>
	function onsub() {
		var fileVal = $("#file").val();
		if (fileVal == "") {
			module.common.showPopupWindow("请选择上传文件!");
			return false;
		}
		return true;
	}
</script>






























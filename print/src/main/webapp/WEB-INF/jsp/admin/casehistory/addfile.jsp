<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>
<!-- onsubmit="javascript:return module.casehistory.isNull();" -->
<form action="${SITE.context}/member/casehistory/readfile" method="post" class="form-horizontal" enctype="multipart/form-data"  onsubmit="javascript:return module.casehistory.isNull()">
	<div class="space"></div>
	<div class="space"></div>
	<div class="space"></div>
	<div class="control-group">
		<label class="control-label">附件:</label>
		<div class="controls">

			<div class="b-fileupload-row">
				<div data-provides="fileupload" class="fileupload fileupload-new">
					<div class="input-append">
						<div class="uneditable-input span2">
							<i class="icon-file fileupload-exists"></i> <span class="fileupload-preview"></span>
						</div>
						<span class="btn btn-file"> <span class="fileupload-new">选择文件</span> <span class="fileupload-exists">重新选择</span> <input type="file" name="file" id="file" class="default aaa"> </span> <a data-dismiss="fileupload" class="btn fileupload-exists" href="#">清除</a>
					</div>
				</div>
			</div>

		</div>
		
	</div>
	<div class="control-group">
		<label class="control-label"></label>
		<div class="controls">

			<div class="b-fileupload-row">
				<strong class=" alert-error">备注:请把要上传的文件打包成ZIP文件!!</strong>
			</div>

		</div>
		
	</div>
	  
                        
	
	<div class="m-emodal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		<button class="btn btn-primary">保存</button>
	</div>
</form>





















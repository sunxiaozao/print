<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="casehistoryAdd" action="${SITE.context}/member/casehistory/saveLocal"
		method="post" class="form-horizontal" enctype="multipart/form-data">
		<div class="form-box-title">
			<h3>
				新增病例资料 <small>请填病例资料，然后保存。</small>
			</h3>
		</div>


		<div class="control-group">
			<label class="control-label"> 所属病历夹: </label>
			<div class="controls">
				<select name="folderId" id="folderId">
					<c:forEach items="${caseFolders}" var="caseFolder">
						<option value="${caseFolder.id}">${caseFolder.folderName}</option>
					</c:forEach>
				</select>

			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> 病例资料名称: </label>
			<div class="controls">
				<input type="text" id="caseName" name="caseName" class="validate[required]">

			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> 病历资料类别: </label>
			<div class="controls">
				<select name="caseType" id="caseType" class="validate[required]">
					<c:forEach items="${caseTypeMap}" var="caseType">
						<option value="${caseType.key}">${caseType.value}</option>
					</c:forEach>
				</select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 选择就诊医院: </label>
			<div class="controls">
				<select name="hospitalId" id="hospitalId">
					
				</select>

			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 检查日期: </label>
			<div class="controls">

				<input value="" class="form_date input w370" type="text" 
					name="caseDate" id="caseDate" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">就诊类别: </label>
			<div class="controls">
				<input type="text" id="catgory" name="catgory" class="">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查项目: </label>
			<div class="controls">
				<input type="text" id="item" name="item" class="">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">执行技师: </label>
			<div class="controls">
				<input type="text" id="technician" name="technician" class="">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请科室: </label>
			<div class="controls">
				<select name="departmentId" id="departmentId">
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请医生: </label>
			<div class="controls">
				<select name="doctorId" id="doctorId">
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">临床诊断: </label>
			<div class="controls">
				<textarea rows="3" class="input-large" id="description"
					name="description"></textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">住院号: </label>
			<div class="controls">
				<input type="text" id="hospitalNo" name="hospitalNo" class="">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">床号: </label>
			<div class="controls">
				<input type="text" id="bedNo" name="bedNo" class="">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">病人性质: </label>
			<div class="controls">
				<input type="text" id="patientCatgory" name="patientCatgory"
					class="">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">附件:</label>
			<div class="controls">

				<div class="b-fileupload-row">
					<div data-provides="fileupload" class="fileupload fileupload-new">
						<div class="input-append">
							<div class="uneditable-input span2">
								<i class="icon-file fileupload-exists"></i> <span
									class="fileupload-preview"></span>
							</div>
							<span class="btn btn-file"> <span class="fileupload-new">选择文件</span>
								<span class="fileupload-exists">重新选择</span> <input type="file"
								name="file" class="default aaa">
							</span> <a data-dismiss="fileupload" class="btn fileupload-exists"
								href="#">清除</a>
						</div>
					</div>
				</div>
				<div>
					<a href="" class="js-btn-addup btn-link">增加</a>
				</div>

			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-primary" id="submit">
					<i class="icon-ok icon-white"></i> 保存
				</button>
				&nbsp;&nbsp;
				<a  class="btn"
					href="${SITE.context}/member/casehistory/list">返回</a>
			</div>
		</div>
	</form>
</div>


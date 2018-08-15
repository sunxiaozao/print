<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="casehistoryAdd" action="${SITE.context}/member/casehistory/saveCaseHistory" method="post" class="form-horizontal" enctype="multipart/form-data">
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
				<input type="text" id="caseName" name="caseName" class="validate[required]" value="${caseHistory.caseName }"> <input type="hidden" name="id" value="${caseHistory.id}">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label"> 病历资料类别: </label>
			<div class="controls">
				<select name="caseType" id="caseType" class="validate[required]">
					<c:forEach items="${caseTypeMap}" var="caseType">
						<option value="${caseType.key}" <c:if test="${caseHistory.caseType  eq caseType.key}">selected</c:if>>${caseType.value}</option>
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

				<input value="<fmt:formatDate value="${caseHistory.caseDate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />" class="form_date input w370" type="text" name="caseDate" id="caseDate" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">就诊类别: </label>
			<div class="controls">
				<input type="text" id="catgory" name="catgory" class="${caseHistory.catgory}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查项目: </label>
			<div class="controls">
				<input type="text" id="item" name="item" class="${caseHistory.item}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">执行技师: </label>
			<div class="controls">
				<input type="text" id="technician" name="technician" class="${caseHistory.technician}">
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
				<textarea rows="3" class="input-large" id="description" name="description">${caseHistory.description}</textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">住院号: </label>
			<div class="controls">
				<input type="text" id="hospitalNo" name="hospitalNo" class="${caseHistory.hospitalNo}">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">床号: </label>
			<div class="controls">
				<input type="text" id="bedNo" name="bedNo" class="${caseHistory.bedNo}">
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">病人性质: </label>
			<div class="controls">
				<input type="text" id="patientCatgory" name="patientCatgory" class="${caseHistory.patientCatgory}">
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-primary" id="submit">
					<i class="icon-ok icon-white"></i> 保存
				</button>
				&nbsp;&nbsp; <a class="btn" href="${SITE.context}/member/casehistory/list">返回</a>
			</div>
		</div>
	</form>
</div>


<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="folderEdit" method="post" class="form-horizontal">
		<div class="form-box-title">
			<h3>
				修改病历资料
				<small>请把内容填写完整，然后提交保存。</small>
			</h3>
		</div>

		<div class="control-group">
			<label class="control-label">
				病历夹名称：
			</label>
			<div class="controls">
				<input type="text" id="folderName" name="folderName"
					value="${caseFolder.folderName }"
					class="input validate[required,maxSize[20]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				所属医院：
			</label>
			<div class="controls">
				<select class="input-large m-wrap" tabindex="1" id="hospital" name="hospitalId" >
		  			<option value="" selected>--请选择病历夹所属医院--</option>
		  			<c:forEach items="${hospitalMap}" var="hospital" varStatus="status">
	                	 <option value="${hospital.key }" <c:if test="${caseFolder.hospitalId==hospital.key }">selected="selected"</c:if>>${hospital.value }</option>
	                 </c:forEach>
            	</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				所属科室：
			</label>
			<div class="controls">
				<jsp:include page="departmentChange.jsp"></jsp:include>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				病历夹状态：
			</label>
			<div class="controls">
				<select class="input-large m-wrap" tabindex="1" id="status" name="status" >
		  			<option value="" selected>--请选择病历夹状态--</option>
	               <c:forEach items="${statusMap}" var="statusMap" varStatus="status">
	                	 <option value="${statusMap.key }" <c:if test="${caseFolder.status==statusMap.key }">selected="selected"</c:if>>${statusMap.value }</option>
	                 </c:forEach>
           		 </select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				病历夹类型：
			</label>
			<div class="controls">
				<select class="input-large m-wrap" tabindex="1" id="type" name="type" >
		  			<option value="" selected>--请选择病历夹类型--</option>
		  			<option value="0">0</option>
	               	<option value="1">1</option>
           		 </select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				病历夹描述：
			</label>
			<div class="controls">
				<textarea class="input-xxlarge" rows="3" name="description">${caseFolder.description }</textarea>
			</div>
		</div>
		<div class="control-group">
			<div class="controls" style="margin-left: 300px;">
				<button type="button" class="btn btn-primary" id="FolderEditSubmit">
					<i class="icon-ok icon-white"></i> 保存
				</button>
				&nbsp;&nbsp;
				<a class="btn" href="${SITE.context}/member/folder/list">
					返回
				</a>
			</div>
		</div>
	</form>
</div>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/pagestart.jsp"%>
<link
	href="${SITE.static_context}/stylelib/plugins/bootstrap-tree/bootstrap-tree.css"
	rel="stylesheet" type="text/css" />


<form action="${SITE.context}/member/authorization/add" class="form-horizontal" method="get" onsubmit="javascript:return module.folder.isNNull();"
	id="addFolder">
	<div class="m-iframe-content m-scroll m-h300">
		<div class="control-group">
			<label class="control-label"> 病历夹名称： </label>
			<div class="controls">
				<input type="text" id="folderName" name="folderName"
					value="${caseFolder.folderName }" readonly> <input
					type="hidden" id="patientId" name="patientId" value="${patientId }">
				<input type="hidden" id="folderId" name="folderId"
					value="${caseFolder.id }">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 授权期限： </label>
			<div class="controls">
				<select class="input-large m-wrap" tabindex="1" id="experiod"
					name="experiod">
					<c:forEach items="${experiedMap}" var="m" varStatus="status">
						<option value="${m.key}">${m.value}月</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 授权对象： </label>
			<div class="controls">

				<ul id="tree_1" class="tree m-scroller">

					<c:forEach var="h" items="${hospitals}">
						<li><a id="nut1" data-value="Bootstrap_Tree"
							data-toggle="branch" class="tree-toggle closed" href="#"><i
								class="icon-home"></i> ${h.name }</a>
							<ul class="branch">
								<c:forEach var="de" items="${departments}">
									<c:if test="${h.id eq de.hospitalId }">
										<li><a id="nut1" data-value="Bootstrap_Tree"
											data-toggle="branch" class="tree-toggle closed" href="#"><i
												class="icon-home"></i> ${de.departmentName }</a>
											<ul class="branch">
												<c:forEach var="d" items="${doctors}">
													<c:if test="${de.id eq d.departmentId }">
														<li><a data-role="leaf" href="#">
														
														<input type="checkbox" value="${h.id},${de.id},${d.id}" name="ids">
														<i
																class="icon-user"></i> ${d.fullname }</a>
														</li>
													</c:if>
												</c:forEach>
											</ul></li>
									</c:if>
								</c:forEach>
							</ul></li>
					</c:forEach>
				</ul>
			</div>
		</div>

	</div>
	<div class="m-emodal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">返回</button>
		<button class="btn btn-primary">保存</button>
	</div>
</form>












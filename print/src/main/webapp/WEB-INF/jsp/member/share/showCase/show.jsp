<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../../common/pagestart.jsp"%>

		



	<div class="container-fluid">
		
		<div class="m-cbox m-cyan">
			<div class="mc-head">
				<h4>
					<span class="mch-smenu-btn"><i class="icon-reorder"></i>&nbsp;${caseHistory.caseName
						} </span>

				</h4>
			</div>
			<!--//mc-head:end-->
			<div class="mc-body">
				<form action="" class="form-horizontal" method="post" id="reply">

					<div class="row-fluid">
						<div class="span2">

							<p class="text-left">
								<label>就诊医院:${hospital.name }</label>
							</p>
							<p class="text-left">
								<label>就诊科室:${department.departmentName}</label>
							</p>
							<p class="text-left">
								<label>就诊医生:${doctor.fullname }</label>
							</p>


							<p class="text-left">
								<label>临时病历ID:${caseHistory.tempId }</label>
							</p>
							<p class="text-left">
								<label>所属病历夹:${folder.folderName }</label>
							</p>
							<p class="text-left">
								<label>病历资料类别:${caseTypeMap[caseHistory.caseType] }</label>
							</p>
							<p class="text-left">
								<label>病历资料名称:${caseHistory.caseName }</label>
							</p>
							<p class="text-left">
								<label>就诊类别:${caseHistory.catgory }</label>
							</p>
							<p class="text-left">
								<label>就诊时间:<fmt:formatDate
										value="${caseHistory.caseDate}" pattern="yyyy-MM-dd"
										type="date" dateStyle="long" /> </label>
							</p>
							<p class="text-left">
								<label>检查项目:${caseHistory.item }</label>
							</p>
							<p class="text-left">
								<label>执行技师:${caseHistory.technician }</label>
							</p>
							<p class="text-left">
								<label>临床诊断:${caseHistory.description }</label>
							</p>
							<p class="text-left">
								<label>住院号:${caseHistory.hospitalNo }</label>
							</p>
							<p class="text-left">
								<label>床号:${caseHistory.bedNo }</label>
							</p>
							<p class="text-left">
								<label>病人性质:${caseHistory.patientCatgory }</label>
							</p>
						</div>
						<div class="span8" align="center">
							<c:if test="${!empty path}">
								<img src="${SITE.images_context}/${path[0] }" width="380"
									id="img_" height="220" class="img-polaroid">
							</c:if>
						</div>
						<div class="span2">
							<c:if test="${!empty path}">
								<c:forEach var="u" items="${path }">
									<img src="${SITE.images_context}/${u}" width="280" height="160"
										onclick="module.casehistory.img_switch(this)" class="img-polaroid">
								</c:forEach>
							</c:if>
						</div>
					</div>
					
				</form>
			</div>
		</div>
		<!--//m-cbox:end-->

	</div>
	
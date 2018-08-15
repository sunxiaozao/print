<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>

<div class="row-fluid">
	<div class="span2">
		<div class="navs">
			<c:forEach items="${list }" var="folder" varStatus="statu">
				<ul>
					<li style="list-style:none;"><a href="#" class="floatL"> <i class="icon-caret-right"></i>&nbsp;&nbsp;&nbsp;</a><input type="checkbox" name="boxFolder" class="floatL" value="${folder.id }"><a href="${SITE.context}/member/folderShow/list?folderId=${folder.id }">&nbsp;${folder.folderName }</a>
						<ul class="child" <c:if test="${folder.id eq folderId}"> style="display: block" </c:if>>
							<c:forEach items="${CaseTypeMap }" var="CaseType" varStatus="statu">
								<li style="list-style:none;"><a href="${SITE.context}/member/folderShow/list?caseType=${CaseType.key}&folderId=${folder.id }"> <c:if test="${CaseType.key eq details && folder.id ne folderId}">&nbsp;&nbsp;${CaseType.value }</c:if> <c:if test="${CaseType.key ne details && folder.id eq folderId}">&nbsp;&nbsp;${CaseType.value }</c:if> <c:if test="${CaseType.key ne details && folder.id ne folderId}">&nbsp;&nbsp;${CaseType.value }</c:if> <c:if test="${CaseType.key eq details && folder.id eq folderId}">
											<font color="#FF0000">&nbsp;&nbsp;${CaseType.value }</font>
										</c:if> </a>
								</li>
							</c:forEach>
						</ul>
					</li>
				</ul>
			</c:forEach>
		</div>
	</div>
	<div class="span10">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>类别</th>
					<th>名称</th>
					<th>检查项目</th>
					<th>执行技师</th>
					<th>临床诊断</th>
					<th>就诊时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty pm.datas }">
					<c:forEach items="${pm.datas}" var="history" varStatus="status">
						<tr>
							<td>${CaseTypeMap[history.caseType] }</td>
							<td>${history.caseName }</td>
							<td>${history.item }</td>
							<td>${history.technician }</td>
							<td>${history.description }</td>
							<td><fmt:formatDate value="${history.caseDate }" type="date" dateStyle="long" pattern="yyyy-MM-dd" />
							</td>
							<td width="440"><a href="${SITE.context}/member/casehistory/show/update/${history.id}/F" class="btn btn-mini btn-info"><i class="icon-pencil icon-white"></i> 修改</a>&nbsp; <a href="${SITE.context}/member/casehistory/show?hid=${history.id}" class="btn btn-mini btn-primary" target="_blank"> <i class="icon-search"></i>查看</a> &nbsp; <a data-atype="modal" data-title="回复留言" data-height="400px" data-width="large" href="${SITE.context}/notebook/patient/list/${history.id }" class="btn btn-mini btn-success"> <i class="icon-pencil icon-white"></i>留言</a> &nbsp; <a data-trigger="modal" data-cache="false" href="${SITE.context}/member/share/${history.id }" data-title="我要分享病历资料" class="btn btn-mini btn-info"><i class="icon-cloud"></i>分享</a> &nbsp; <a title="病历资料《${history.caseName}》的分享情况" class="btn btn-mini btn-primary" href="${SITE.context}/member/share/detail/${history.id}"> <i class="icon-reorder"></i> 分享统计</a> &nbsp; <a href="${SITE.context}/member/casehistory/download/${history.id}"
								title="下载" class="btn btn-mini btn-inverse"> <i class="icon-download"></i> 下载</a> &nbsp; <a href="${SITE.context}/member/casehistory/delete/${history.id}" title="删除" class="btn btn-mini btn-warning" data-trigger="confirm" data-content="确认删除病例资料(${history.caseName})吗？"><i class="icon-remove icon-white"></i> 删除</a>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty pm.datas}">
					<tr>
						<!-- 需要修改列数dx -->
						<td colspan="7" align="center" bgcolor="#EFF3F7">没有找到相应的记录</td>
					</tr>
				</c:if>
				<tr>
					<!-- 需要修改列数dx -->
					<td colspan="7" class="bottom">
						<div class="pagination pagination-right row-fluid">
							<div class="span12">
								<span class="right"> <!-- 需要修改url地址 dx--> <pg:pager url="../../../cpf/member/folderShow/list" items="${pm.total}" export="currentPageNumber=pageNumber" maxPageItems="${ps}" scope="request">
										<pg:param name="caseType" />
										<pg:param name="folderId" />
										<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
									</pg:pager> <!-- 用户选择每页显示行数下拉列表 --> <jsp:include page="/WEB-INF/jsp/pages/pager-select.jsp" flush="true" /></span>
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</div>

<div class="space"></div>
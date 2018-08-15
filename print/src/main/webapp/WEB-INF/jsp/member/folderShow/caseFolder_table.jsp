<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>
<div id="selectDetail">
	<input type="hidden" id="caseType" name="caseType" value="${details }">
	<input type="hidden" id="folderId" name="folderId" value="${folderId }">
	<div>
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
							<td><fmt:formatDate value="${history.caseDate }" type="date"
									dateStyle="long" pattern="yyyy-MM-dd" /></td>
							<td width="400"><c:if test="${history.source eq 'L'}">
									<a data-atype="modal" data-title="查看" data-height="500px"
										data-width="xlarge" data-width="large"
										href="${SITE.context}/member/casehistory/show/${history.id}"
										data-cache="false" class="btn btn-mini btn-primary"> <i
										class="icon-search"></i> 查看</a>

								</c:if> <c:if test="${history.source ne 'L'}">
									<%--<a data-atype="modal" data-title="查看" data-height="auto"
										onclick="module.casehistory.showProgress();"
										data-width="xxlarge"
										href="${history.nilUrl}"
										data-cache="false" class="btn btn-mini btn-primary"> <i
										class="icon-search"></i>查看</a>

								--%>
								<a href="${SITE.context}/member/casehistory/show?hid=${history.id}" class="btn btn-mini btn-primary" target="_blank"> <i
								class="icon-search" ></i>查看</a>
								
								</c:if> &nbsp; <a data-atype="modal" data-title="回复留言"
								data-height="400px" data-width="large"
								href="${SITE.context}/notebook/patient/list/${history.id }"
								class="btn btn-mini btn-success"> <i
									class="icon-pencil icon-white"></i>留言</a> &nbsp; <a
								data-trigger="modal" data-cache="false"
								href="${SITE.context}/member/share/${history.id }"
								data-title="我要分享病历资料" class="btn btn-mini btn-info"><i
									class="icon-cloud"></i>分享</a> &nbsp; <a
								title="病历资料《${history.caseName}》的分享情况"
								class="btn btn-mini btn-primary"
								href="${SITE.context}/member/share/detail/${history.id}"> <i
									class="icon-reorder"></i> 分享统计</a> &nbsp; <a
								href="${SITE.context}/member/casehistory/download/${history.id}"
								title="下载" class="btn btn-mini btn-inverse"> <i
									class="icon-download"></i> 下载</a> &nbsp; <a
								href="${SITE.context}/member/casehistory/delete/${history.id}"
								title="删除" class="btn btn-mini btn-warning"
								data-trigger="confirm"
								data-content="确认删除病例资料(${history.caseName})吗？"><i
									class="icon-remove icon-white"></i> 删除</a></td>
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
								<span class="right"> <!-- 需要修改url地址 dx--> <pg:pager
										url="../../../cpf/member/folderShow/list" items="${pm.total}"
										export="currentPageNumber=pageNumber" maxPageItems="${ps}"
										scope="request">
										<pg:param name="caseType" />
										<pg:param name="folderId" />
										<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
									</pg:pager> <!-- 用户选择每页显示行数下拉列表 --> <jsp:include
										page="/WEB-INF/jsp/pages/pager-select.jsp" flush="true" /></span>
							</div>
						</div></td>
				</tr>
			</tbody>
		</table>
	</div>
</div>
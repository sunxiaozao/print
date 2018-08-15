<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<%--<div id="indexNav" class="row-fluid">
	<div class="span12">
		<ul class="nav nav-tabs">
			<li><a href="${SITE.context}/member/index">分类汇总</a></li>
			<li class="active"><a
				href="${SITE.context}/member/casehistory/list">按病历资料方式</a>
			</li>
			<li><a href="${SITE.context}/member/folderShow/list">按病历夹方式</a>
			</li>
		</ul>
	</div>
</div>--%>
<div class="row-fluid form-box-title">
	<div class="span10">
		<h4>病历资料<strong>《${caseName}》</strong>的分享情况</h4>
		<h5>共分享${countAllShare }次，已被查看${countShareByStatu }次
		<c:if test="${createTime ne null}">
		，第一次分享时间为<fmt:formatDate value="${createTime }" type="date"
			dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" />
		</c:if>	
		</h5>
	</div>
	<div class="span2">
		<div class="pull-right">
			<a href="javascript:history.go(-1);" 
				class="btn btn-danger">返回</a>
		</div>
	</div>
</div>

<div class="">

	<table class="table table-striped">
			<thead>
				<tr>
					<th>序号 </th>
					<th>类型 </th>
					<th>内容</th>
					<th>分享人</th>
					<th>状态</th>
				</tr>
			</thead>
			<c:if test="${!empty pm.datas}">
				<tbody>
					<c:forEach items="${pm.datas}" var="l" varStatus="status">
						<tr>
							<td>${status.index+1}</td>
							<td>${typeMap[l.type] }</td>
							<td>${l.content }</td>
							<td>${patientMap[l.patientId] }</td>
							<td>${viewStatuMap[l.status] }</td>
						</tr>
					</c:forEach>
				</tbody>
			</c:if>
			<c:if test="${empty pm.datas}">
				<tr>
					<!-- 需要修改列数dx -->
					<td colspan="5" align="center" bgcolor="#EFF3F7">没有找到相应的记录</td>
				</tr>
			</c:if>
	<c:if test="${not empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="5" class="bottom">
				<div class="pagination pagination-right row-fluid">
					<div class="span12">
						<span class="right"> <!-- 需要修改url地址 dx--> <pg:pager
							url="/cpf/member/share/detail/${caseHistoryId}" items="${pm.total}"
							export="currentPageNumber=pageNumber" maxPageItems="${ps}"
							scope="request">
								<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
							</pg:pager> <!-- 用户选择每页显示行数下拉列表 --> <jsp:include
								page="/WEB-INF/jsp/pages/pager-select.jsp" flush="true" /></span>
					</div>
				</div></td>
		</tr>
	</c:if>

		</table>
</div>
	
<div class="space"></div>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/pagestart.jsp"%>



<div id="indexNav" class="row-fluid">
	<div class="span12">
		<ul class="nav nav-tabs">
			<li><a href="${SITE.context}/member/index">分类汇总</a></li>
			<li><a href="${SITE.context}/member/casehistory/list">按病历资料方式</a>
			</li>
			<li class="active"><a
				href="${SITE.context}/member/folderShow/list">按病历夹方式</a>
			</li>
		</ul>
	</div>
</div>

<div class="row-fluid">
	<div class="span12 searchBlock">
		<div class="span8">

			<font size="5"><strong>授权管理</strong>(${caseFolder.folderName
				})</font>
		</div>
		<div class="span4">
			<div class="pull-right">
				<a href="${SITE.context}/member/folder/list" class="btn "><i
					class="icon-plus icon-reply"></i> 返回</a>&nbsp; <a
					href="${SITE.context}/member/authorization/addshow/${caseFolder.id
				}/${patientId}"
					data-trigger="modal" data-cache="false"
					data-title="添加授权 <small>请把内容填写完整，然后提交保存。</small>"
					class="btn  btn-success "><i class="icon-plus icon-white"></i>
					添加授权</a>
			</div>
		</div>
	</div>
</div>
<div class="space"></div>

<table class="table table-striped">
	<thead>
		<tr>
			<th>授权医生</th>
			<th>医院</th>
			<th>科室</th>
			<th>授权期限</th>
			<th>授权日期</th>
			<th>操作</th>
		</tr>
	</thead>

	<tbody>
		<c:if test="${not empty pm.datas }">
			<c:forEach items="${pm.datas}" var="auto" varStatus="status">
				<c:if test="${empty departmentId }">
					<tr>
						<td>${sysDoctorMap[auto.doctor] }</td>
						<td>${hospitalMap[auto.hospitalId] }</td>
						<td>${departmentAllMap[auto.departmentId] }</td>
						<td>${auto.experiod}个月</td>
						<td><fmt:formatDate value="${auto.createTime }" type="date"
								dateStyle="long" pattern="yyyy-MM-dd" />
						</td>
						<td><a data-trigger="confirm"
							href="${SITE.context}/member/authorization/cancel/${auto.id}/${caseFolder.id
				}/${patientId}"
							class="btn btn-mini btn-primary"
							data-content="确定取消对[${sysDoctorMap[auto.doctor] }]医生的授权?">取消授权</a>
						</td>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
			<!-- 需要修改列数dx -->
			<td colspan="6" class="bottom">
				<div class="pagination pagination-right row-fluid">
					<div class="span12">
						<span class="right"> <!-- 需要修改url地址 dx--> <pg:pager
								url="${SITE.context}/member/folder/impower/${caseFolder.id}"
								items="${pm.total}" export="currentPageNumber=pageNumber"
								maxPageItems="${ps}" scope="request">
								<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
							</pg:pager> <!-- 用户选择每页显示行数下拉列表 --> <jsp:include
								page="/WEB-INF/jsp/pages/pager-select.jsp" flush="true" /></span>
					</div>
				</div>
			</td>
		</tr>
		</c:if>
		<c:if test="${empty pm.datas}">
			<tr>
				<!-- 需要修改列数dx -->
				<td colspan="6" align="center" bgcolor="#EFF3F7">没有找到相应的记录</td>
			</tr>
		</c:if>
		
	</tbody>
</table>

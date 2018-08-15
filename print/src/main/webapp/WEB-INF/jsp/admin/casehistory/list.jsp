
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>

<div id="indexNav" class="row-fluid">
	<div class="span12">
		<ul class="nav nav-tabs">
			<li><a href="${SITE.context}/member/index">分类汇总</a></li>
			<li class="active"><a href="${SITE.context}/member/casehistory/list">按病历资料方式</a>
			</li>
			<li><a href="${SITE.context}/member/folderShow/list">按病历夹方式</a></li>
		</ul>
	</div>
</div>
<div class="row-fluid">
	<div class="span12 searchBlock">
		<div class="span8">
			<form id="myForm" name="myForm" method="post" class="form-search" action="${SITE.context}/member/casehistory/list">
				就诊时间 : <input type="text" placeholder="请选择就诊时候..." class="form_date input-medium" name="caseDate" value="<fmt:formatDate value="${caseHistory.caseDate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />"> 病例名称: <input name="caseName" value="${caseHistory.caseName }" placeholder="请输入病例名称..." class="input-medium" /> 病例类型:<select name="caseType" id="caseType" class="input-small">
					<option value="">请选择病例类型</option>
					<c:forEach items="${caseTypeMap}" var="typeMap">
						<option value="${typeMap.key}" <c:if test="${typeMap.key eq caseHistory.caseType}">selected</c:if>>${typeMap.value}</option>
					</c:forEach>
				</select>
				<button type="submit" class="btn btn-primary">
					<i class="icon-search icon-white"></i> 搜 索
				</button>
				<button class="btn clearForm" type="button">
					<i class="icon-eraser"></i> 清 除
				</button>
			</form>
		</div>
		<div class="span4">
			<div class="pull-right">
				<a href="${SITE.context}/member/casehistory/addLocal"
					class="btn btn-success "><i class="icon-plus  icon-white"></i>
					添加本地病历资料</a>
					&nbsp;
					<%-- <a href="${SITE.context}/member/casehistory/temporary"
					class="btn btn-success "><i class="icon-plus icon-white"></i>
					临时病历资料</a> --%>
				<a data-trigger="modal" data-cache="false" data-title="新增病例资料 <small>选择病例资料，然后保存。</small>" href="${SITE.context}/member/casehistory/addfile" class="btn btn-success "><i class="icon-plus icon-white"></i> 添加医院病例资料</a>
			</div>
		</div>
	</div>
</div>
<div class="space"></div>
<table class="table table-striped">
	<thead>
		<tr>
		<th><input type="checkbox" class="js-all-select"
				data-range-name=".table"></th>
			<th>病历资料名称</th>
			<th>病历资料类别</th>
			<th>就诊医院</th>
			<th>就诊科室</th>
			<th>操作</th>
		</tr>
	</thead>
	<c:if test="${!empty pm.datas}">
		<tbody>
			<c:forEach items="${pm.datas}" var="data" varStatus="status">
				<tr>
				<td>
				
					<c:if test="${data.folderId eq null}">
						<input type="checkbox" value="${data.id }" name="id">
					</c:if>
					</td>
					<td style="text-align:left; padding-left:10px;">${data.caseName}</td>
					<td>${caseTypeMap[data.caseType]}</td>
					<td>${hospitalMap[data.hospitalId]}</td>
					<td>${deMap[data.departmentId]}</td>


					<td width="440">
					<a 
								href="${SITE.context}/member/casehistory/show/update/${data.id}/N"
					class="btn btn-mini btn-info"><i
							class="icon-pencil icon-white"></i> 修改</a>&nbsp;
					
						
						
						<a href="${SITE.context}/member/casehistory/show?hid=${data.id}" class="btn btn-mini btn-primary" target="_blank"> <i class="icon-search"></i>查看</a>
								
								
								
								
						 &nbsp; <a data-atype="modal" data-title="回复留言" data-height="400px" data-width="large" href="${SITE.context}/notebook/patient/list/${data.id }" class="btn btn-mini btn-success"> <i class="icon-pencil icon-white"></i>留言</a> &nbsp; <a data-trigger="modal" data-cache="false" href="${SITE.context}/member/share/${data.id }" data-title="我要分享病历资料" class="btn btn-mini btn-info"><i class="icon-cloud"></i>分享</a> &nbsp; <a title="病历资料《${data.caseName}》的分享情况" class="btn btn-mini btn-primary" href="${SITE.context}/member/share/detail/${data.id}"> <i class="icon-reorder"></i> 分享统计</a> &nbsp; <a href="${SITE.context}/member/casehistory/download/${data.id}" title="下载" class="btn btn-mini btn-inverse"> <i class="icon-download"></i> 下载</a> &nbsp; <a href="${SITE.context}/member/casehistory/delete/${data.id}"
						title="删除" class="btn btn-mini btn-warning" data-trigger="confirm" data-content="确认删除病例资料(${data.caseName})吗？"><i class="icon-remove icon-white"></i> 删除</a>
					</td>

				</tr>
			</c:forEach>
			<tr>
				<td colspan="6"><a href="javascript:;" id="all"class="btn btn-success btn-mini"></i>全选</a>&nbsp;&nbsp;<a
					href="javascript:;" id="relation"class="btn btn-success btn-mini"><i class="icon-plus icon-white"></i>关联病历资料</a>
					
					<div id="div_relation"></div>
					
				</td>
			</tr>
		</tbody>
	</c:if>
	<c:if test="${empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="6" align="center" bgcolor="#EFF3F7">没有找到相应的记录</td>
		</tr>
	</c:if>
	<c:if test="${not empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="6" class="bottom">
				<div class="pagination pagination-right row-fluid">
					<div class="span12">
						<span> <!-- 需要修改url地址 dx--> <pg:pager url="${SITE.context}/member/casehistory/list" items="${pm.total}" export="currentPageNumber=pageNumber" maxPageItems="${ps}" scope="request">
								<pg:param name="caseDate" />
								<pg:param name="caseType" />
								<pg:param name="caseName" />
								<pg:param name="viewStatus" />

								<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
							</pg:pager> <!-- 用户选择每页显示行数下拉列表 --> <jsp:include page="/WEB-INF/jsp/pages/pager-select.jsp" flush="true" /> </span>
					</div>
				</div></td>
		</tr>
	</c:if>
</table>
<div class="space"></div>
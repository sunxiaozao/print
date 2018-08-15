-<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>

<div id="indexNav" class="row-fluid">
	<div class="span12">
		<ul class="nav nav-tabs">
			<li><a href="${SITE.context}/member/index">分类汇总</a>
			</li>
			<li class="active"><a
				href="${SITE.context}/member/casehistory/list">按病历资料方式</a></li>
			<li><a href="${SITE.context}/member/folderShow/list">按病历夹方式</a>
			</li>
		</ul>
	</div>
</div>
<div class="row-fluid">
	<div class="span12 searchBlock">
		<div class="span8">
			<form id="myForm" name="myForm" method="post" class="form-search"
				action="${SITE.context}/member/casehistory/hospitaldata/H">
				就诊时间 : <input type="text" placeholder="请选择就诊时候..."
					class="form_date input-medium" name="caseDate"
					value="<fmt:formatDate value="${caseHistory.caseDate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />">
				病例名称：<input name="caseName" value="${caseHistory.caseName}"
					class="input-medium" />病例类型:<select name="caseType" id="caseType"
					class="input-small">
					<option value="">请选择病例类型</option>
					<c:forEach items="${caseTypeMap}" var="typeMap">
						<option value="${typeMap.key}"
							<c:if test="${typeMap.key eq caseHistory.caseType}">selected</c:if>>${typeMap.value}</option>
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
				<a href="${SITE.context}/member/casehistory/list"
					class="btn btn-success "><i class="icon-mail-reply"></i>
					返回</a> <a
					href="${SITE.context}/member/casehistory/temporary"
					class="btn btn-success "><i class="icon-plus icon-white"></i>
					关联临时病历资料</a>
					 <a
					href="${SITE.context}/member/casehistory/add/${patient.id}"
					class="btn btn-success "><i class="icon-plus icon-white"></i>
					添加本地病历资料</a>
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
	<c:if test="${!empty caseHistories}">
		<tbody>
			<c:forEach items="${caseHistories}" var="data" varStatus="status">
				<tr>
				<td>
					<c:if test="${data.relateStatus eq 'N'}">
						<input type="checkbox" value="${data.id }" name="id">
					</c:if>
					</td>
					<td style="text-align:left; padding-left:10px;">
						${data.caseName}</td>
					<td>${caseTypeMap[data.caseType]}</td>
					<td>${hospitalMap[data.hospitalId]}</td>
					<td>${deMap[data.departmentId]}</td>
					<td width="400"><c:if test="${data.source eq 'L'}">
							<a data-atype="modal" data-title="查看" data-height="auto"
								data-width="xxlarge"
								href="${SITE.context}/member/casehistory/show/${data.id}"
								data-cache="false" class="btn btn-mini btn-primary"><i
								class="icon-pencil icon-white"></i> 查看</a>

						</c:if> <c:if test="${data.source ne 'L'}">
							<%--<a data-atype="modal" data-title="查看" data-height="auto" onclick="module.casehistory.showProgress();"
								data-width="xxlarge" href="${data.nilUrl}"
								data-cache="false" class="btn btn-mini btn-primary"><i
								class="icon-pencil icon-white"></i> 查看</a>
						--%>
						<a href="${SITE.context}/member/casehistory/show?hid=${data.id}" class="btn btn-mini btn-primary" target="_blank"> <i
								class="icon-search" ></i>查看</a>
						
						</c:if>
						    <a data-trigger="modal" href="${SITE.context}/member/casehistory/relation/${data.id }/H" data-title="关联病历资料" class="btn btn-mini btn-primary"><i
								class="icon-exchange"></i>关联</a>
						
						</td>
						
						
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6"><a href="javascript:;" id="all">全选</a>&nbsp;|&nbsp;<a
					href="javascript:;" id="relation">全部关联</a>
					
					<div id="div_relation"></div>
					
				</td>
			</tr>
		</tbody>
	</c:if>
	<c:if test="${empty caseHistories}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="6" align="center" bgcolor="#EFF3F7">没有找到相应的记录</td>
		</tr>
	</c:if>
</table>
<div class="space"></div>
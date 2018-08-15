<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>
<div id="indexNav" class="row-fluid">
	<div class="span12">
		<ul class="nav nav-tabs">
			<li><a href="${SITE.context}/member/index">分类汇总</a></li>
			<li><a href="${SITE.context}/member/casehistory/list">按病历资料方式</a>
			</li>
			<li class="active"><a href="${SITE.context}/member/folderShow/list">按病历夹方式</a>
			</li>
		</ul>
	</div>
</div>
<div class="row-fluid">
	<div class="span12 searchBlock">
		<a href="${SITE.context}/member/folder/list" title="病历夹管理" class="btn  btn-primary">病历夹管理</a> &nbsp; <a id="deleteFolder" class="btn  btn-danger" data-content="确定要删除该病历夹吗？">删除病例夹</a>
	</div>
</div>
<div class="space"></div>
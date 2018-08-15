<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/pagestart.jsp" %>

<div id="indexNav" class="row-fluid">
	<div class="span12">
		<ul class="nav nav-tabs">
		  <li class="active">
		    <a href="${SITE.context}/member/index">分类汇总</a>
		  </li>
		  <li><a href="${SITE.context}/member/casehistory/list">按病历资料方式</a></li>
		  <li><a href="${SITE.context}/member/folderShow/list">按病历夹方式</a></li>
		</ul>
	</div>
</div>

<div id="indexNav" class="row-fluid">
	<div class="span12">
		
		
		<a data-trigger="modal"  data-cache="false" data-title="新增病例资料 <small>选择病例资料，然后保存。</small>"
					href="${SITE.context}/member/casehistory/addfile"   class="btn btn-mini btn-warning">添加医院病历资料</a> &nbsp;
					
					<a href="${SITE.context}/member/casehistory/addLocal"
					class="btn btn-mini btn-warning">
					添加本地病历资料</a>
	</div>
</div>

<div id="memIndex">
	<div class="row-fluid">
	<div class="span12">
		<div class="span3 box">
			<div class="row-fluid icon">
				<i class="my-icon-1"></i> 
				<span>新增病例夹</span>
			</div>
			<div class="row-fluid num">
				<span><a data-trigger="modal" data-cache="false" data-title="新增病例夹"
							href="${SITE.context}/member/subtotal/folders/N" 
							class="">${folderCountNewest }</a></span>条
			</div>
			<div class="row-fluid">
				合计：<a data-trigger="modal" data-cache="false" data-title="病例夹汇总"
							href="${SITE.context}/member/subtotal/folders/Y" 
							class="">${folderCount }</a>个
			</div>
		</div>
		<div class="span3 box">
			<div class="row-fluid icon">
				<i class="my-icon-2"></i> 
				<span>最新胶片</span>
			</div>
			<div class="row-fluid num">
				<span><a href="${SITE.context}/member/casehistory/list?caseType=F&viewStatus=N" 
							class="">${pictureCountNewest }</a></span>条
			</div>
			<div class="row-fluid">
				合计：<a href="${SITE.context}/member/casehistory/list?caseType=F"
							class="">${pictureCount }</a>条
			</div>
		</div>
		<div class="span3 box">
			<div class="row-fluid icon">
				<i class="my-icon-3"></i> 
				<span>最新报告</span>
			</div>
			<div class="row-fluid num">
				<span><a href="${SITE.context}/member/casehistory/list?caseType=R&viewStatus=N"
							class="">${reportCountNewest }</a></span>条
			</div>
			<div class="row-fluid">
				合计：<a href="${SITE.context}/member/casehistory/list?caseType=R" 
							class="">${reportCount }</a>个
			</div>
		</div>
		<div class="span3 box">
			<div class="row-fluid icon">
				<i class="my-icon-4"></i> 
				<span>最新化验单</span>
			</div>
			<div class="row-fluid num">
				<span><a href="${SITE.context}/member/casehistory/list?caseType=T&viewStatus=N" 
							class="">${assayCountNewest }</a></span>条
			</div>
			<div class="row-fluid">
				合计：<a href="${SITE.context}/member/casehistory/list?caseType=T" 
							class="">${assayCount }</a>个
			</div>
		</div>
		
	</div>
</div>
	<div class="row-fluid">
	<div class="span12">
		
		<div class="span3 box">
			<div class="row-fluid icon">
				<i class="my-icon-5"></i> 
				<span>最新申请单</span>
			</div>
			<div class="row-fluid num">
				<span><a href="${SITE.context}/member/casehistory/list?caseType=D&viewStatus=N"  
							class="">${applyCountNewest }</a></span>条
			</div>
			<div class="row-fluid">
				合计：<a href="${SITE.context}/member/casehistory/list?caseType=D" 
							class="">${applyCount }</a>个
			</div>
		</div>
		
		<div class="span3 box">
			<div class="row-fluid icon">
				<i class="my-icon-7"></i> 
				<span>最新留言</span>
			</div>
			<div class="row-fluid num">
				<span><%--<a data-atype="modal" data-title="最新留言"
							href="${SITE.context}/doctor/subtotal/notebooks/N">
							${notebookCountNewest }</a>
						
						--%><a data-atype="modal" data-title="最新留言" data-height="400px"
						data-width="large" href="${SITE.context}/notebook/member/subtotal/N"
						class="">${notebookCountNewest }</a> 
				
				</span>条
			</div>
			<div class="row-fluid">
				合计：<%--<a href="${SITE.context}/doctor/subtotal/notebooks/Y" data-atype="modal"
						data-title="留言汇总">${notebookCount }</a>个
			--%>
					<a data-atype="modal" data-title="留言汇总" data-height="400px"
						data-width="large" href="${SITE.context}/notebook/member/subtotal/Y"
						class="">${notebookCount }</a> 条
			
			</div>
		</div>
		<div class="span3 box">
			<div class="row-fluid icon">
				<i class="my-icon-8"></i> 
				<span>最新分享</span>
			</div>
			<div class="row-fluid num">
				<span><a title="最新分享"
							href="${SITE.context}/member/share/list?status=N">
							${shareCountNewest }</a></span>条
			</div>
			<div class="row-fluid">
				合计：<a title="分享汇总"
							href="${SITE.context}/member/share/list">${shareCount }</a>个 
			</div>
		</div>

		<div class="span3 box">
			<div class="row-fluid icon">
				<i class="my-icon-6"></i> <span>就诊医院</span>
			</div>
			<div class="row-fluid num">
				<span><a data-trigger="modal" data-cache="false" data-title="就诊医院汇总"
							href="${SITE.context}/member/subtotal/hospitals">${caseCount }</a></span>个
			</div>
			<div class="row-fluid">
					 &nbsp;</div>
		</div>

	<%--
		<div class="span2 box">
			<div class="image">
				<img src="${SITE.images_context}/images/add.png">
			</div>
		</div>
		<div class="span2 box">
			<div class="image">
				<img src="${SITE.images_context}/images/add.png">
			</div>
		</div>
		<div class="span2 box">
			<div class="image">
				<img src="${SITE.images_context}/images/add.png">
			</div>
		</div>
		<div class="span2 box">
			<div class="image">
				<img src="${SITE.images_context}/images/add.png">
			</div>
		</div>
	--%></div>
</div>
</div>




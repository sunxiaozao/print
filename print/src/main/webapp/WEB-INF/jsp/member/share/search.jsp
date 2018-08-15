<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<%--<div class="row-fluid">
	<h2 id="page-title"><span>商户管理</span></h2>
</div>
--%>
<div class="row-fluid">
	<div class="span12 searchBlock">
		<div class="span10">
			<form id="myForm" name="myForm" method="post" class="form-search" 
					action="/cpf/member/share/list">
				邮箱地址：<input name="email" value="" placeholder="请输入邮箱..."  >
				手机号码：<input name="mobile" value="" placeholder="请输入手机号码...">
				<button type="submit" class="btn btn-primary">
					<i class="icon-search icon-white"></i> 搜 索
				</button>
				<button class="btn clearForm" type="button">
					<i class="icon-eraser"></i> 清 除
				</button>
			</form>		
		</div>
		<div class="span2">
			<div class="pull-right">
				<%--<a data-trigger="confirm" href="${SITE.context}/doctor/saveCollect/1/A"
					class="btn btn-success" data-content="您确定要收藏选中的病人病历吗？！"><i
					class="icon-plus icon-white"></i>收藏</a>
					
				<a href="${SITE.context}/admin/group/add" class="btn"><i
					class="icon-remove icon-white"></i>取消收藏</a>
			--%></div>
		</div>
	</div>
<div class="space"></div>

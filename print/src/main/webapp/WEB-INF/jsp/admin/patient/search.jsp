<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="row-fluid">
	<h2 id="page-title">
		<span>病人管理</span>
	</h2>
</div>
<div class="row-fluid">
	<div class="span12 searchBlock">
		<form id="myForm" name="myForm" method="post" class="form-search" action="${SITE.context}/admin/patient/list">
				姓名: <input placeholder="请输入用户姓名..." name="userName" type="text" class="input-small" value="${patientSearch.userName }" /> 
				手机: <input placeholder="请输入用户手机..." name="mobile" type="text" class="input-small" value="${patientSearch.mobile }" /> 
				邮箱: <input placeholder="请输入用户邮箱..." name="email" type="text" class="input-small" value="${patientSearch.email }" /> 
				医保卡号: <input placeholder="请输入医保卡号..." name="cardId" type="text" class="input-medium" value="${patientSearch.cardId }" /> 
				身份证号: <input placeholder="请输入身份证号..." name="identityCard" type="text" class="input-medium" value="${patientSearch.identityCard }" /> 		
				
				<button type="submit" class="btn btn-primary">
					<i class="icon-search icon-white"></i> 搜 索
				</button>
				<button class="btn clearForm" type="button">
					<i class="icon-eraser"></i> 清 除
				</button>
		</form>
	</div>
</div>
<div class="space"></div>
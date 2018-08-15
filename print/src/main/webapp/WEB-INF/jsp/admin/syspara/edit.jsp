<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="paraEdit" action="${SITE.context}/admin/para/saveEdit" method="post" class="form-horizontal">
		<div class="formBox" style="clear: both;">
			<div class="form-box-title">
				<h3>
					更新系统参数
					<small> 请填写系统参数信息，然后更新。 
					<input type="hidden" name="paraCode" value="${sysPara.paraCode }"> 
					<input type="hidden" name="parentCode" value="${sysPara.parentCode }"> </small>
				</h3>
			</div>

			<div class="control-group">
				<label class="control-label">
					父类名称:
				</label>
				<div class="controls">
					${sysParaTypeMap[sysPara.parentCode]}
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					代码：
				</label>
				<div class="controls">
						${sysPara.paraCode }
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					参数名：
				</label>
				<div class="controls">
					<input type="text" name="paraName" id="paraName" value="${sysPara.paraName }" class="input span5 validate[required,maxSize[50]]">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					参数值：
				</label>
				<div class="controls">
					<textarea rows="5" cols="80" name="paraValue" id="paraValue" class="span5 validate[required,maxSize[1000]">${sysPara.paraValue }</textarea>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">
					优先级：
				</label>
				<div class="controls">
					<input type="text" name="priority" value="${sysPara.priority }" class="input validate[required,custom[number]]" id="priority">
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					<button type="button" class="btn btn-primary" id="paraEditSubmit">
						<i class="icon-ok icon-white"></i> 更新
					</button>
					&nbsp;&nbsp;
				<a class="btn" href="${SITE.context}/admin/para/list">
					返回
				</a>
				</div>
			</div>
	</form>
</div>
</form>
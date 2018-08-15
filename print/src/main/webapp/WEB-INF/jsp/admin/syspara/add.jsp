<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="paraAdd"  action="${SITE.context}/admin/para/saveAdd" method="post"
		class="form-horizontal">
		<div class="form-box-title">
			<h3>
				添加系统参数
				<small> 请填写系统参数信息，然后保存。 </small>
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
				<input type="text" name="paraCode" id="paraCode"
					value="${sysPara.paraCode }"
					class="input validate[required,maxSize[50]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				参数名：
			</label>
			<div class="controls">
				<input type="text" name="paraName" id="paraName"
					value="${sysPara.paraName }"
					class="input validate[required,maxSize[50]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				参数值：
			</label>
			<div class="controls">
				<input type="text" name="paraValue" id="paraValue"
					value="${sysPara.paraValue }"
					class="input validate[required,maxSize[500]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				优先级：
			</label>
			<div class="controls">
				<input type="text" name="priority" value="0"
					class="input validate[required,custom[number]]" id="priority">
			</div>
		</div>

		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn btn-primary" id="submit">
					<i class="icon-ok icon-white"></i> 保存
				</button>
				&nbsp;&nbsp;
				<button type="button" class="btn"
					onclick="javascript:history.go(-1)">
					返回
				</button>
			</div>
		</div>
	</form>
</div>
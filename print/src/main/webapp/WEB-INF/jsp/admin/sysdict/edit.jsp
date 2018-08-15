<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
<form id="dictEdit" action="${SITE.context}/admin/dict/saveEdit" method="post" class="form-horizontal">
	<div class="form-box-title">
				<h3>
			修改数据
		<small>
				请填写数据信息，然后保存。
				<input type="hidden" name="dictId" value="${sysDict.dictId}">
			</small>
				</h3>
			</div>

			<div class="control-group">
				<label class="control-label">类别: </label>
				<div class="controls"><select name="type" class="select" disabled>
						<c:forEach items="${sysDictTypeMap}" var="sysDictType">
							<option value="${sysDictType.key}" <c:if test="${sysDictType.key  == sysDict.type }">selected</c:if>>
								${sysDictType.value}
							</option>
						</c:forEach>
					</select>   </div>
			</div>
			<div class="control-group">
				<label class="control-label">代码：</label>
				<div class="controls"><input type="text" name="shortCode" id="shortCode" disabled value="${sysDict.shortCode }" class="input validate[required,maxSize[50]]">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">名称：</label>
				<div class="controls"><input type="text" name="longName" id="longName" value="${sysDict.longName }" class="input w370 validate[required,maxSize[50]]"> 
			</div>
			</div>
			<div class="control-group">
				<label class="control-label">优先级：</label>
				<div class="controls"><input type="text" name="priority" value="${sysDict.priority}" class="input w90 validate[required,custom[number]]" id="priority"> 
			</div>
			</div>
			<div class="control-group">
			<div class="controls">
				<button type="button" class="btn btn-primary" id="dictEditSubmit">
					<i class="icon-ok icon-white"></i>更新
				</button>
				&nbsp;&nbsp;
				<a class="btn" href="${SITE.context}/admin/dict/list">
					返回
				</a>
			</div>
		</div>
	</form>
</div>

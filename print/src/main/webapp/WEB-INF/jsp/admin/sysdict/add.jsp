<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
<form id="dictAdd"  action="${SITE.context}/admin/dict/saveAdd" method="post" class="form-horizontal">
	<div class="form-box-title">
			<h3>
			添加数据
		<small>
				请填写数据信息，然后保存。
			</small>
			</h3>
		</div>
		<div class="control-group">
			<label class="control-label">类别: </label>
			<div class="controls"><select name="type" class="select">
						<c:forEach items="${sysDictTypeMap}" var="sysDictType">
							<option value="${sysDictType.key}" <c:if test="${sysDictType.key  == sysDict.type }">selected</c:if>>
								${sysDictType.value}
							</option>
						</c:forEach>
					</select>  </div>
		</div>
		<div class="control-group">
			<label class="control-label">
				代码：</label>
			<div class="controls"><input type="text" name="shortCode" id="shortCode" value="${sysDict.shortCode }" class="input w370 validate[required,maxSize[50]]">
				<span id="notifyMsg">* 代码必须唯一</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">名称：</label>
			<div class="controls"><input type="text" name="longName" id="longName" value="${sysDict.longName }" class="input w370 validate[required,maxSize[50]]"> 
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">优先级：</label>
			<div class="controls"><input type="text" name="priority" value="0" class="input w90 validate[required,custom[number]]" id="priority"> 
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
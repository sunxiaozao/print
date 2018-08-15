<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="container-fluid form-box">
	<form id="messageEdit" action="${SITE.context}/admin/message/saveEdit" method="post" class="form-horizontal">
		<div class="form-box-title">
			<h3>
				修改消息
				<small> 请填写消息，然后保存。 </small>
			</h3>
		</div>

		<div class="control-group">
			<label class="control-label">
				<input type="hidden" name="messageId" value="${userMessage.messageId }">
				标题：
			</label>
			<div class="controls">
				<input type="text" name="title" id="title"
					value="${userMessage.title }" class="input w370 validate[required]">
  		    </div>
		</div>
		<div class="control-group">
			<label class="control-label">
				内容：
			</label>
			<div class="controls">
				<textarea rows="5" cols="50" name="content" id="content"
					class="input w370 validate[required]">${userMessage.content}</textarea>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">
				用户类型：
			</label>
			<div class="controls">
			    <select id="userType" name="userType" onchange="getUserType()">
			      <c:forEach items="${userTypeMap}" var="map">
			      <option value="${map.key }" <c:if test="${userMessage.userType==map.key}">selected='selected'</c:if>>${map.value }</option>
			      </c:forEach>
			    </select>
			</div>
		</div>
		<div class="control-group" id="orgName" style="display:none">
			<label class="control-label">机构名称：</label>
			<div class="controls">
			    <select id="orgId" name="orgId">
			      <c:forEach items="${orgMap}" var="map">
			      <option value="${map.key }">${map.value }</option>
			      </c:forEach>
			    </select> 
			</div>
		</div>
		<div class="control-group" id="groupName" style="display:none">
			<label class="control-label">组名称：</label>
			<div class="controls">
			    <select id="groupId" name="groupId">
			      <c:forEach items="${groupMap}" var="map">
			      <option value="${map.key }">${map.value }</option>
			      </c:forEach>
			    </select> 
			</div>
		</div>
		<div class="control-group" id="memberName" style="display:none">
			<label class="control-label">会员Id：</label>
			<div class="controls">
			   <input type="text" name="toUserIdStr" id="toUserIdStr" 
			        value="${userMessage.toUserIdStr}" class="input w370 validate[required,maxSize[500]]">
			   
			   <button type="button" class="btn btn-small btn-primary"
			          id="find-user" onclick="findUser('userId','find-user','orgId')">
			          <i class="icon-search icon-white"></i>查找
			   </button>
			</div>
		</div>
	<div class="control-group">
			<div class="controls">
				<button type="button" class="btn btn-primary" id="messageEditSubmit">
					<i class="icon-ok icon-white"></i> 保存
				</button>
				&nbsp;&nbsp;
				<a class="btn" href="${SITE.context}/admin/message/list">
					返回
				</a>
			</div>
		</div>
</form>
</div>
<%@ include file="./common/userDialogBox.jsp"%>
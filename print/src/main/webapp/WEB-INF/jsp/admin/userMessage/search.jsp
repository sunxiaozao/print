<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="row-fluid">
	<h2 id="page-title"><span>消息管理</span></h2>
</div>
<div class="row-fluid">
	<div class="span12 searchBlock">
		<form id="myForm" name="myForm" method="post" class="form-search" action="${SITE.context}/admin/message/list">
	          标题: 
	          <input placeholder="请输入标题..." name="title" type="text" class="input-medium" value="${title}"/> 
			用户类型:
		      <select id="userType" name="userType" class="input-small">
		          <option value="">  </option>
			      <c:forEach items="${userTypeMap}" var="map">
			      <option value="${map.key }" <c:if test="${map.key eq userMessageSearch.userType}">selected</c:if>>${map.value }</option>
			      </c:forEach>
			  </select> &nbsp;&nbsp;&nbsp;&nbsp;
			是否发布: 
			  <select id="isGenerated" name="isGenerated" class="input-small">
			       <option value="">  </option>
			       <c:forEach items="${isGeneratedMap}" var="map">
			       <option value="${map.key}" <c:if test="${map.key eq userMessageSearch.isGenerated}">selected</c:if>>${map.value}</option>
			       </c:forEach>
			  </select>&nbsp;&nbsp;&nbsp;&nbsp;
		           创建时间:
				<input type="text" placeholder="请选择开始日期..." class="form_date input-small" name="createDtFrom" value="<fmt:formatDate value="${userMessageSearch.createDtFrom}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />">
				<input type="text" placeholder="请选择结束日期..." class="form_date input-small" name="createDtTo" value="<fmt:formatDate value="${userMessageSearch.createDtTo}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />">
				<button type="submit" class="btn btn-primary""><i class="icon-search icon-white"></i> 搜 索</button>
				<button class="btn clearForm" type="button"><i class="icon-eraser"></i> 清 除</button>
		     
        <div class="pull-right">
		      <a href="${SITE.context}/admin/message/add" class="btn btn-success "><i class="icon-plus icon-white"></i> 添加消息</a>
		</div>
		</form>
	</div>
</div>
<div class="space"></div>
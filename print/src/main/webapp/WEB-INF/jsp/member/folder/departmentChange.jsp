<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp" %>
<div id="departmentSelect">
<select class="input-large m-wrap" tabindex="1" id="departmentId" name="departmentId" >
	<option value="" selected>--请选择病历夹所属科室--</option>
	<c:forEach items="${departmentMap}" var="department" varStatus="status">
         <option value="${department.key }" <c:if test="${caseFolder.departmentId==department.key }">selected="selected"</c:if>>${department.value }</option>
    </c:forEach>
</select>
</div>
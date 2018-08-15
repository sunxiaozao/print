<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>
<div class="control-group" id="departmentAtImpower">
	<label class="control-label">
		请选择科室：
	</label>
	<div class="controls">
		<c:forEach items="${departmentMap}" var="department" varStatus="status">						
				<label class="checkbox inline" style="margin-left:10px;">
					<input type="checkbox" id="${department.key}" name="departmentId" value="${department.key}" <c:if test="${impowerDeparMap[department.key]!=null}">checked</c:if>>
					<input type="hidden" id="selectDepar" name="selectDepar" value="">
					${department.value}
				</label>						
		</c:forEach>
		<br/><br/>
		<jsp:include page="impower_doctor.jsp"></jsp:include>
	</div>
</div>

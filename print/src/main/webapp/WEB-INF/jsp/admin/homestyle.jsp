<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/pagestart.jsp" %>

<form action="${SITE.context}/member/homeFlag" id="updatePassForm" method="post" class="form-horizontal">
	<div class="m-iframe-content">
		<div class="control-group">
			<label class="control-label">主页显示方式：</label>
			<div class="controls">
				<select name="homeFlag" class="homeFlag" >
					<c:forEach items="${homeFlagMap}" var="map">
						<option value="${map.key}"
							<c:if test="${map.key eq key}">selected</c:if>>${map.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="m-emodal-footer">
        <button aria-hidden="true" data-dismiss="modal" class="btn">关 闭</button>
        <button type="submit" class="btn btn-primary">确 定</button>
                             
    </div>
</form>

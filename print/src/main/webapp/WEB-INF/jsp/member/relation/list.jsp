<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/inc.jsp"%>

<div class="row-fluid form-box-title">
	<div class="span10">
		<h4>我的关注</h4>
	</div>
	<div class="span2">
		<div class="pull-right">
			<a href="${SITE.context}/member/relation/add" 
				class="btn btn-info"><i class="icon-plus"></i>添加</a>
		</div>
	</div>
</div>

<table class="table table-hover table-bordered table-condensed">
	<thead>
		<tr>
			<%--<th>序号</th> --%>
			<th>病人名称</th>
			<th>账号</th>
			<th>密码</th>
			<th>状态</th>
			<th width="150">操作</th>
		</tr>
	</thead>
	<c:if test="${!empty pm.datas}">
		<tbody>
			<c:forEach items="${pm.datas}" var="p">
				<tr>
					<%--<td>${status.index }</td> --%>
					<td>${p.relatePatient.userName }</td>
					<td>${p.relatePatient.userName  }</td>
					<td>${p.relatePatient.password }</td>
					<td>${relationStatuMap[p.status] }</td>
					<td>
						<c:if test="${p.status eq 'A' || p.status eq 'C'}">
							<a href="${SITE.context}/member/cancelRelation/${p.id}"
								data-content="确定取消该关注？" data-trigger="confirm"
								class="btn btn-mini btn-inverse">取消</a>
						</c:if>
						
						<c:if test="${p.status eq 'B' }">
							<a href="${SITE.context}/member/saveRelation/${p.id}"
								data-content="确定关注该病人？" data-trigger="confirm"
								class="btn btn-mini btn-inverse">关注</a>
						</c:if>
						
						<c:if test="${p.status eq 'C'}">
							<a href="javascript:module.member.simulate('${p.relatePatient.userName }','${p.relatePatient.password  }');" class="btn btn-mini btn-info">关注</a>
						</c:if>
								
					&nbsp;</td>
				</tr>
			</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${empty pm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="6" align="center" bgcolor="#EFF3F7">没有找到相应的记录</td>
		</tr>
	</c:if>
</table>


<div class="row-fluid form-box-title">
	<div class="span10">
		<h4>关注我的</h4>
	</div>
	<div class="span2">
		<div class="pull-right">
			<!--被选中的需通过记录id -->
			<a id="passId" href="${SITE.context }/member/relation/savePass/0" 
					data-trigger="confirm" data-content="通过后，您的密码将分享给该用户，是否继续？"
					class="btn btn-primary"><i class="icon-ok"></i>通过</a>
		</div>
	</div>
</div>

<table class="table table-hover table-bordered table-condensed">
	<thead>
		<tr>
			<th><input type="checkbox"/></th>
			<th>病人名称</th>
			<th>申请日期</th>
			<th>申请理由</th>
			<th>审核日期</th>
			<th>状态</th>
		</tr>
	</thead>
	<c:if test="${!empty otherPm.datas}">
		<tbody>
			<c:forEach items="${otherPm.datas}" var="other" >
				<tr>
					<td><input type="checkbox" name="passInput" 
							value="${other.id }" id="${other.id }"/></td>
					<td>${other.relatePatient.userName }</td>
					<td><fmt:formatDate value="${other.applyTime }" type="date"
							dateStyle="long" pattern="yyyy-MM-dd" /></td>
					<td>${other.applyReason }</td>
					<td><fmt:formatDate value="${other.checkTime }" type="date"
							dateStyle="long" pattern="yyyy-MM-dd" /></td>
					<td>${relationStatuMap[other.status] }</td>
				</tr>
			</c:forEach>
		</tbody>
	</c:if>
	<c:if test="${empty otherPm.datas}">
		<tr>
			<!-- 需要修改列数dx -->
			<td colspan="6" align="center" bgcolor="#EFF3F7">没有找到相应的记录</td>
		</tr>
	</c:if>
</table>

<div class="space"></div>
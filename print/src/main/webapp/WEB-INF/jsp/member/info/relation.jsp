<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>




<div class="m-iframe-content m-scroll m-h300">
	<table class="table table-striped">
		<thead>
			<tr>
				<th>登录卡号</th>
				<th>病人名称</th>
				<th>IC/ID</th>
				<th width="150">操作</th>
			</tr>
		</thead>
		<c:if test="${!empty list}">
			<tbody id="tab">
				<c:forEach items="${list}" var="p">

					<tr id="${p.id}">
						<td>${p.cpfPatient.userName}</td>
						<td>${p.cpfPatient.fullname}</td>
						<td>${p.cpfPatient.icId }</td>
						<td><c:if test="${p.relationPatientId ne id }">
								<a href="javascript:module.head.relationDel(${p.id})"
									class="btn btn-mini btn-inverse">取消关联</a>
							</c:if>
						</td>
					</tr>

				</c:forEach>
			</tbody>
		</c:if>
	</table>

	<form action="" id="relation" method="post" class="form-horizontal">
		<div class="m-iframe-content">
			<div class="control-group">
				<label class="control-label">输入登录卡号：</label>
				<div class="controls">
					<input type="text" id="cardNo" name="cardNo" class="input ">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">登录密码：</label>
				<div class="controls">
					<input type="password" id="password" name="password" class="input ">
				</div>
			</div>
		</div>
	</form>
</div>

<div class="m-emodal-footer">
	<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	<a href="javascript:;" id="relationLink" class="btn btn-primary">关联</a>
</div>
<script>
	$(function() {
		module.head.relation();
	});
</script>
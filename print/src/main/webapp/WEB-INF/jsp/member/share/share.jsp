<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>

<form id="share" action="/cpf/member/saveShare" method="post"
	class="form-horizontal">

	<input type="hidden" value="${cpfShare.id }" name="id" id="id" />
	<input type="hidden" value="${caseHistoryId }" name="caseHistoryId" id="caseHistoryId" />

	<input type="hidden" value="${viewPass }" id="password" name="password"/>
	<input type="hidden" value="${viewUrl }" id="url" name="url"/>
	
	<div class="m-iframe-content">
	
	<%--<div class="row-fluid form-box-title">
		<h4>共分享${countAllShare }次，已被查看${countShareByStatu }次，
		<br/>
		第一次分享时间为<fmt:formatDate value="${createTime }" type="date"
			dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" /></h4>
	</div>
	
	
	<div class="control-group">
			<label class="control-label">分享方式：</label>
			<div class="controls">
				<div class="row-fluid">
					<div class="span3">
						<label class="checkbox">
							<div class="checker">
								<span>
									<input id="emailId" name="typeName" value="A" type="checkbox">
								</span>
							</div> &nbsp;邮箱</label>
					</div>
					<div class="span3">
						<label class="checkbox">
							<div class="checker">
								<span>
									<input id="sms" name="typeName" value="B" type="checkbox">
								</span>
							</div> &nbsp;短信</label>
					</div>
				</div>
			</div>
		</div>
		--%>
		<div class="control-group">
			<label class="control-label"> 邮箱地址： </label>
			<div class="controls" id="emailsDiv">
				<input type="text" id="email" name="email" 
					value=""
					class="input w370 validate[required,custom[manyEmail]]">
				<span class="help-inline">多个邮箱请用英文逗号(,)隔开</span>
				
				<%--<input type="text" 
					class="input w370 validate[required,custom[email]]">
			--%></div>
		</div>

		<div class="control-group">
			<label class="control-label"> 输入接收短信手机号码： </label>
			<div class="controls" id="mobileDiv">
				<input type="text" id="mobile" name="mobile" value=""
					class="input w370 validate[required,custom[phone]]">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"> 分享期限： </label>
			<div class="controls">
				<select class="input-large m-wrap" tabindex="1" id="experiod"
					name="type">
					<c:forEach items="${experiedMap}" var="m" varStatus="status">
						<option value="${m.key}">${m.value}月</option>
					</c:forEach>
					<option value="9999">永久有效</option>
				</select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">留言：</label>
			<div class="controls">
				<textarea id="content" name="content" class="input-large w370 validate[required,maxSize[200]]" rows="3"></textarea>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label"> 当前分享的病历资料： </label>
			<div class="controls">
				<label class="control-label-right-larger">
				 胶片地址：
				${viewUrl }
				<%--<a data-atype="modal" data-title="查看" data-height="500px"
								data-width="xlarge" data-width="large"
								href="/cpf/member/casehistory/show/${caseHistoryId}"
								data-cache="false" class="">${viewUrl }</a>--%>
								
				</label>
				<br/>
				<label class="control-label-right">查看密码：${viewPass }</label>
			</div>
		</div>
	</div>

	<div class="m-emodal-footer">
		<button type="button" class="btn btn-primary" id="shareSubmit">
			<i class="icon-ok icon-white"></i> 分享
		</button>
		&nbsp;&nbsp;
		<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
		<%--<a class="btn" href="${SITE.context}/admin/agency/detail/${user.agencyId }">
					返回 </a>
			--%>
	</div>
</form>

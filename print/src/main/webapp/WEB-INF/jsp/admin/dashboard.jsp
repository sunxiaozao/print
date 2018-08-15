<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/pagestart.jsp" %>

<div class="row-fluid">
	<h3 id="page-title">
		<span>首 页</span><small> 欢迎使用云胶片系统</small>
	</h3>
</div>
<legend></legend>


<c:if test="${USER.userType eq 'A'}">
<div class="row-fluid">
	<h3 id="page-title">
		<small><i class="icon-bullhorn"></i> 系统通知</small>
	</h3>
</div>

<div class="row-fluid">
	<div class="span12">
					<table class="table table-hover table-condensed">
                        <thead>
                           <tr>
                               <th>通知</th>
                               <th>状态</th>
                               <th>用户</th>
                               <th>操作</th>
                            </tr>
                         </thead>
                        <c:if test="${!empty pm.datas}">
                         <tbody>
                             <c:forEach items="${pm.datas}" var="noti" varStatus="status">
								<tr>									
									<td>${noti.functionName }: ${noti.description }</td>
									<td>${isProceedMap[noti.isProceed] }</td>
									<td>${noti.processUserName }</td>
									<td>
										<c:if test="${noti.isProceed eq 'N' }">
											<a href="${SITE.context}/admin/doIsRroceed/${noti.id}" class="btn btn-mini btn-info">不再提醒</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
                          </tbody>
                          </c:if>
                          <c:if test="${empty pm.datas}">
								<tr>
									<!-- 需要修改列数dx -->
									<td colspan="4" align="center" bgcolor="#EFF3F7">没有通知</td>
								</tr>
							</c:if>
							<c:if test="${not empty pm.datas}">
								<tr>
									<!-- 需要修改列数dx -->
									<td colspan="4" class="bottom">
										<div class="pagination pagination-right row-fluid">
											<div class="span12">
												<span class="right"> <!-- 需要修改url地址 dx--> <pg:pager
														url="${SITE.context}/admin/index" items="${pm.total}"
														export="currentPageNumber=pageNumber" maxPageItems="${ps}"
														scope="request">
														<jsp:include page="/WEB-INF/jsp/pages/pager.jsp" flush="true" />
													</pg:pager> <!-- 用户选择每页显示行数下拉列表 --></span>
											 </div>
										 </div>
									</td>
									</tr>
							</c:if>			
                	 </table>
	</div><!--span12:end-->
</div><!--//row-fluid:end-->
</c:if>
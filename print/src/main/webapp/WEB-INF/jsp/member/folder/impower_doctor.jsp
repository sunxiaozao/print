<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="../../common/inc.jsp"%>
<div id="doctorAtImpower">
		<table class="table table-bordered table-condensed" style="border-width: 1px; width: 350px; ">
			<c:if test="${not empty impowerDeparMap }">
				<c:forEach items="${impowerDeparMap }" var="impowerDepar" varStatus="statu">
					<c:if test="${departmentMap[impowerDepar.key]!=null}">
						<tr>
							<td class="baseInfoLabel" style="text-align:center; width: 23%">
								${departmentMap[impowerDepar.key] }
							</td>
							<td style="text-align: left; padding-left:10px;">
								<c:forEach items="${doctorMap}" var="doctor" varStatus="status">	
									<c:if test="${doctor.key eq impowerDepar.key }">
										<c:forEach items="${doctor.value}" var="docList" varStatus="stat">			
											<label class="checkbox inline" style="margin-left:10px;">
												<input type="checkbox" name="doctorId" value="${impowerDepar.key }-${docList.id}"
												<c:if test="${not empty impowerDocMap[docList.id]}">disabled="disabled"</c:if>>
													${docList.fullname}(${docList.title })
											</label>
										</c:forEach>	
									</c:if>
								</c:forEach>					
							</td>
						</tr>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${empty impowerDeparMap }">
				<tr><td colspan="2"><small>请先选择科室。</small></td></tr>
			</c:if>
		</table>
</div>
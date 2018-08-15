<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../common/pagestart.jsp"%>

<div id="main-menu" class="row-fluid memberLeft">
	<div class="b-sidebar-scroll">
		<div id="sidebar" class="nav-collapse collapse">
			<ul class="b-sidebar-menu">
				<li class="bsm-image"><c:if
						test="${not empty(PATIENT.smallImage)}">
						<img src="${SITE.images_context}/${PATIENT.smallImage}"
							class="img-circle">
					</c:if> <c:if test="${empty(PATIENT.smallImage)}">
						<img src="${SITE.images_context}/images/doctor.jpg"
							class="img-circle">
					</c:if> <br></li>
				<li
					class="bsm-item <c:if test='${selectedMenuCat eq 0 }'>active</c:if>">
					<a name="menuLink"> <span>个人信息</span> <span class="pull-right">
							<%--<img src="${SITE.images_context}/images/icon-editor.png" width="20" height="20"> 
                     	--%>
					</span> <c:if test="${sessionScope.simulateMark eq 1}">
							<span> <a data-title="退出关注"
								href="javascript:module.member.simulateOfSelf();">
									<i class="icon-off"></i>退出关注</a>
							</span>
						</c:if> </a></li>

				<li class="bsm-item"><span>${func.functionName}</span></li>
				<li class="info"><span>姓名: <c:if
							test="${not empty(PATIENT.fullname)}">${PATIENT.fullname }</c:if> <c:if
							test="${empty(PATIENT.fullname)}">${PATIENT.userName }</c:if> </span> <span>性别:
						<c:if test="${PATIENT.sex eq 'M' }">男</c:if> <c:if
							test="${PATIENT.sex eq 'W' }">女</c:if> </span> <span>出生日期: <fmt:formatDate
							value="${PATIENT.birthday}" pattern="yyyy-MM-dd" type="date"
							dateStyle="long" />
				</span> <span>身份证号: ${PATIENT.identityCard}</span> <span>手机号:
						${PATIENT.mobile}</span> <span>医保卡号: ${PATIENT.cardId}</span>
				<!--  <span>主治医生: 张医生</span> <span>定点医院:
						北京协和医院</span>  -->
					<span>&nbsp;</span></li>
			</ul>
			<!--//sidebar-menu-->
		</div>
		<!--//sidebar:end-->
	</div>
</div>
<!--//main-menu:end-->

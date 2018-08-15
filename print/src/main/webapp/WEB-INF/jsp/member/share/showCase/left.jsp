<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../../common/pagestart.jsp"%>

<div id="main-menu" class="row-fluid memberLeft">
	<div class="b-sidebar-scroll">
		<div id="sidebar" class="nav-collapse collapse">
			<ul class="b-sidebar-menu">
				<li class="bsm-image">
					<img src="${SITE.images_context}/images/doctor.jpg"
							class="img-circle"> <br> </li>
				<li class="bsm-item <c:if test='${selectedMenuCat eq 0 }'>active</c:if>">
					<a name="menuLink"> 
						<span>个人信息</span> 
						<span class="pull-right">
							<img src="${SITE.images_context}/images/icon-editor.png" width="20" height="20"> 
                     	</span> 
					</a>
				</li>
				
				<li class="bsm-item"><span>${func.functionName}</span>
				</li>
				<li class="info"><span>姓名:  </span> 
								<span>性别: </span> 	
								<span>出生日期: </span>
								<span>身份证号: </span>
								<span>手机号: </span> 
								<span>医保卡号: </span>
								<span>&nbsp;</span>
				</li>
			</ul>
			<!--//sidebar-menu-->
		</div>
		<!--//sidebar:end-->
	</div>
</div>
<!--//main-menu:end-->

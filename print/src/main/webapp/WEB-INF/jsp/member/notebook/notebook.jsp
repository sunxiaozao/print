<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html class="ie8"> <![endif]-->
<!--[if IE 9]> <html class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<head>
<title>test iframe modal content</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- // base css -->
<link href="${SITE.static_context}/stylelib/base.css" rel="stylesheet"
	type="text/css" />

<!-- // plugins css -->
<link
	href="${SITE.static_context}/stylelib/plugins/font-awesome/font-awesome.css"
	rel="stylesheet" type="text/css" />

<!-- // theme css -->
<link href="${SITE.static_context}/stylelib/theme_wblue/screen.css"
	rel="stylesheet" type="text/css" />
<link href="${SITE.static_context}/css/3rd/validationEngine.jquery.css"
	rel="stylesheet" type="text/css" />
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
          <script  src="${SITE.static_context}/stylelib/plugins/html5shiv.js"></script>
        <![endif]-->
</head>
<body class="m-iframe-body">
	<form
		action="" 
		class="form-horizontal" method="post" id="reply">


		<div class="m-cbox m-lgray">
			<div class="mc-head">
				<h4>
					<span class="mch-smenu-btn"><i class="icon-reorder"></i> </span>

				</h4>
			</div>
			<!--//mc-head:end-->
			<div class="mc-body">

				<c:forEach items="${notebooks}" var="notebook">
					<div class="alert alert-block alert-success">
						<div>
							<p class="text-left">
								${notebook.sysDoctor.fullname}&nbsp;&nbsp;
								<fmt:formatDate value="${notebook.createTime}"
									pattern="yyyy-MM-dd" type="date" dateStyle="long" />
								&nbsp;&nbsp;
								
								<c:if test="${empty! notebook.sysDoctor.id }">
								<a
									href="javascript:reply(${notebook.id},${notebook.sysDoctor.id },${caseHistoryId })">回复</a>
							</c:if></p>
						</div>
						<div class="row-fluid">
							<div class="span1"></div>
							<div class="span10">${notebook.content}</div>
						</div>


						<c:forEach items="${replies}" var="re">
							<c:if test="${notebook.id eq re.notebookId }">
								<hr />
								<div>
									<p class="text-right">
										${re.cpfPatient.fullname}&nbsp;&nbsp;
										<fmt:formatDate value="${re.replyTime}" pattern="yyyy-MM-dd"
											type="date" dateStyle="long" />
									</p>
								</div>
								<div class="row-fluid">
									<div class="span11 offset1">
										<p class="text-right">${re.content}</p>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</c:forEach>



				<c:if test="${empty  notebooks}">没有任何留言</c:if>



			</div>
			<!--//mc-body:end-->
		</div>
		<!--//m-cbox:end-->


		<div class="mic-footer">
			<div class="m-emodal-footer">
				<div class="row-fluid">

					<div class="span8">
						<div class="span1"></div>
						<div class="span11">
							<input type="text" placeholder="回复留言..........." class="span12 validate[required]"
								name="content" style="display: none" id="content" />
						</div>


					</div>
					<div class="span4">
						<button class="btn btn-primary" type="submit"
							style="display: none" id="sub">
							<i class="icon-ok"></i>回复
						</button>
						<button class="btn" id="cancel" style="display: none"
							type="button">取消</button>
						<button class="btn btn-danger" id="close">关闭</button>
					</div>

				</div>


			</div>
		</div>

	</form>

	<!--// Loading JS -->
	<script src="${SITE.static_context}/stylelib/jquery-1.10.2.min.js"></script>
	<script src="${SITE.static_context}/stylelib/base.js"></script>
	<script
		src="${SITE.static_context}/stylelib/plugins/iealert/iealert.js"></script>
	<script
		src="${SITE.static_context}/stylelib/plugins/jquery.nicescroll.min.js"></script>
	<!--common script for all pages-->
	<script src="${SITE.static_context}/stylelib/theme_default/global.js"></script>

<!--页面段验证插件  -->
	<script src="${SITE.static_context}/js/3rd/jquery.validationEngine.js"></script>
	<script
		src="${SITE.static_context}/js/3rd/jquery.validationEngine-zh_CN.js"></script>

	<!--script for this page only-->
	<script type="text/javascript">
		var SITE = {};
		SITE.context = "${SITE.static_context}";
		SITE.images_context = "${SITE.static_context}";
		SITE.static_context = "${SITE.static_context}";
		var module = {
			param : {}
		};
	</script>

	<script src="${SITE.static_context}/js/main.js"></script>
	<script src="${SITE.static_context}/js/admin.js"></script>
	<script>
		//刷新父框架
		$("#reload").click(function() {
			self.parent.location.reload();
		});

		//取消
		$("#cancel").click(function() {
			$("#content").hide();
			$("#cancel").hide();
			$("#sub").hide();
		});

		//关闭
		$("#close").click(function() {
			self.parent.IframeModalCC(window.name, -1);
		});

		function reply(notebookId, doctorId, caseHistoryId) {
			$("#content").show();
			$("#cancel").show();
			$("#sub").show();

			$("#reply").attr(
					"action",
					SITE.context + "/notebook/addreply/" + notebookId + "/"
							+ doctorId + "/" + caseHistoryId);

		}
		
		$(function() {
			$("#notebookAdd").validationEngine();
		});
	</script>
	<!--// JS End -->
</body>
</html>

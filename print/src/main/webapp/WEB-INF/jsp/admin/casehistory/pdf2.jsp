<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../common/pagestart.jsp"%>


<!DOCTYPE html>
<!--[if IE 8]> <html class="ie8"> <![endif]-->
<!--[if IE 9]> <html class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<head>
<title>夏宇科技微医通系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- // base css -->
<link href="${SITE.static_context}/stylelib/base.css" rel="stylesheet" type="text/css" />

<!-- // plugins css -->
<link href="${SITE.static_context}/stylelib/plugins/font-awesome/font-awesome.css" rel="stylesheet" type="text/css" />

<!-- // theme css -->
<link href="${SITE.static_context}/stylelib/theme_wblue/screen.css" rel="stylesheet" type="text/css" />

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
          <script src="stylelib/plugins/html5shiv.js"></script>
        <![endif]-->


<script src="${SITE.static_context}/src/shared/util.js"></script>
<script src="${SITE.static_context}/src/display/api.js"></script>
<script src="${SITE.static_context}/src/display/metadata.js"></script>
<script src="${SITE.static_context}/src/display/canvas.js"></script>
<script src="${SITE.static_context}/src/display/webgl.js"></script>
<script src="${SITE.static_context}/src/display/pattern_helper.js"></script>
<script src="${SITE.static_context}/src/display/font_loader.js"></script>
<script src="${SITE.static_context}/src/display/annotation_helper.js"></script>

<script>
	var SITE = {};
	SITE.context = "${SITE.static_context}";
	SITE.images_context = "${SITE.static_context}";
	SITE.static_context = "${SITE.static_context}";
	var module = {
		param : {}
	};
	PDFJS.workerSrc = SITE.context + '/src/worker_loader.js';

		var pdfWithFormsPath = SITE.images_context + '/${url}';


</script>
<script src="${SITE.static_context}/forms.js"></script>
</head>
<body class="m-iframe-body" >
	<form action="../index.html" class="form-horizontal">

		<div class="row-fluid">
			<div class="offset1 span10">
				<div class="m-cbox m-lgray ">
					<div class="mc-head">
						<h4>
							<span class="mch-smenu-btn"><i class="icon-reorder"></i> </span> 查看病例资料
						</h4>
					</div>
					<!--//mc-head:end-->
					<div class="mc-body">
						<div class="m-cbgrid">
							<ul class="m-cbul m-cb5">
								<li class="m-item">就诊医院:${hospital.name }</li>
								<li class="m-item">就诊科室:${department.departmentName}</li>
								<li class="m-item">就诊医生:${doctor.fullname }</li>
								<li class="m-item">所属病历夹:${folder.folderName }</li>
								<li class="m-item">病历资料类别:${caseTypeMap[caseHistory.caseType] }</li>
								<li class="m-item">病历资料名称:${caseHistory.caseName }</li>
								<li class="m-item">就诊类别:${caseHistory.catgory }</li>
								<li class="m-item">就诊时间:<fmt:formatDate value="${caseHistory.caseDate}" pattern="yyyy-MM-dd" type="date" dateStyle="long" />
								</li>
								<li class="m-item">检查项目:${caseHistory.item }</li>
								<li class="m-item">执行技师:${caseHistory.technician }</li>
								<li class="m-item">临床诊断:${caseHistory.description }</li>
								<li class="m-item">住院号:${caseHistory.hospitalNo }</li>
								<li class="m-item">床号:${caseHistory.bedNo }</li>
								<li class="m-item">病人性质:${caseHistory.patientCatgory }</li>
							</ul>
						</div>
<hr>
						<div class="row-fluid">
							<div align="center">
								<div id="viewer"></div>
							</div>

						</div>
					</div>
					<!--//mc-body:end-->
				</div>
				<!--//m-cbox:end-->
			</div>
		</div>

		<div class="mic-footer">
			<div class="m-emodal-footer">
				
				
			</div>
		</div>

	</form>
</body>
</html>

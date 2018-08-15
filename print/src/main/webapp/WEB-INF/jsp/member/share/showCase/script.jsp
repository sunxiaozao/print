<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="../../../common/pagestart.jsp"%>
<script src="${SITE.static_context}/js/casehistory.js"></script>



<script src="${SITE.static_context}/js/3rd/pdfobject.js"></script>
<c:if test="${type eq 'pdf'}">
<script>
function load(vals) {
	var myPDF = new PDFObject({
		url : SITE.context + "/" + vals,
		width : "100%",
		height : "480px",
	}).embed('pdf');

}
function show(vals) {
	var myPDF = new PDFObject({
		url : SITE.context + "/" + vals,
		width : "100%",
		height : "480px",
	}).embed('pdf');

};
$(function(){
	load('${path[0] }');
});

</script>
</c:if>
<script type="text/javascript">
$(function() {
	module.casehistory.showProgress();
	window.onresize=function(){  
		IFrameReSize("mainFrame");  
   }  ;
});

function IFrameReSize(iframename) {
	var pTar = document.getElementById(iframename);

	if (pTar) { 
		pTar.height = document.documentElement.clientHeight-45;
	}

}
</script>
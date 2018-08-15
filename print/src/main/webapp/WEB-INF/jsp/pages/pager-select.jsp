<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="../../tld/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/tld/c.tld" prefix="c"%>

<span>共
<font style="color: black; font-weight: bold;">${pm.total}</font> 行&nbsp; 每页显示</span>
<a href="javascript:;" onClick="selectPagesize(10)" <c:if test='${ps eq 10 }'>class="now"</c:if>>10</a>
<a href="javascript:;" onClick="selectPagesize(20)" <c:if test='${ps eq 20 }'>class="now"</c:if>>20</a>
<a href="javascript:;" onClick="selectPagesize(50)" <c:if test='${ps eq 50 }'>class="now"</c:if>>50</a>
<span>行</span>
<script type="text/javascript">
	//Pager-Taglib 跳转
	function selectPagesize(field) {
		document.location.href = document.getElementById("firstpageurl")+ "&ps=" + field;//得到用户从下拉列表选择的每页显示的行数，并刷新到转到首页
	}
</script>

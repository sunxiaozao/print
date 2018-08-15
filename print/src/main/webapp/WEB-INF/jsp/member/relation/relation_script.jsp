<%@ page language="java" pageEncoding="UTF-8"%>
<script src="${SITE.static_context}/js/member.js"></script>

<script>
	$(function() {
		//添加关注
		module.member.saveAddRelation();
		//修改账号的类型
		module.member.changeTypeVal();
		//检查是否已关注
		module.member.checkRelation();
		//通过关注请求
		module.member.updPassSelectCli();
	});
</script>

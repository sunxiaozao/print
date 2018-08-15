<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page isELIgnored="false"%>  
<%@ page import="com.lubian.cpf.vo.*" %>
<%@page import="com.lubian.cpf.common.util.*"%>
<%@page import="com.lubian.cpf.common.constant.*"%>
<%@page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg"%>
<%
Map SITE=new HashMap();
SITE.put("context",request.getContextPath());

String images_context=Config.getProperty(Constant.IMAGE_CONTEXT);
if(StringUtils.isBlank(images_context)){
	images_context=request.getContextPath();
}
SITE.put("images_context",images_context);

String static_context=Config.getProperty(Constant.STATIC_CONTEXT);
if(StringUtils.isBlank(static_context)){
	static_context=request.getContextPath();
}
SITE.put("static_context",static_context);

request.setAttribute("SITE",SITE);

SysUser user=SessionUtil.getUser(request);
request.setAttribute("USER",user);
%>
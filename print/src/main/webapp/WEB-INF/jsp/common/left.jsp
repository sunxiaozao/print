<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="pagestart.jsp"%>

        <div id="main-menu" class="row-fluid">
            <div class="b-sidebar-scroll">
                <div id="sidebar" class="nav-collapse collapse">
                    <ul class="b-sidebar-menu">
                        <li class="bsm-search">
                            <form action="">
                                <span><input type="text"></span>
                                <button type="submit"><i class="icon-search"></i></button>
                            </form>
                        </li>
                        <li class="bsm-item <c:if test='${selectedMenuCat eq 0 }'>active</c:if>">
                          <a name="menuLink" code="0_0" class="" href="${SITE.context}/admin/index">
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <span>首&nbsp;&nbsp;页</span>
                          </a>
                        </li>
                	<c:forEach items="${USER_SESSION_MENUS}" var="menu" varStatus="status">
                	<c:forEach items="${menu.value}" var="func">
                        <li class="bsm-item <c:if test='${selectedMenuItem eq func.functionId }'>active</c:if>">
                          <a href="${SITE.context}${func.url}" class="bsmi-btn" name="menuLink" code="${menu.key.categoryId}_${func.functionId}" >
                              <span>${func.functionName}</span>
                              <span class="arrow"></span>
                          </a>
                        </li>
                   </c:forEach>
			       </c:forEach>
                    </ul><!--//sidebar-menu-->
                </div><!--//sidebar:end-->
            </div>
        </div><!--//main-menu:end-->

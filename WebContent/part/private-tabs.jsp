<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="show-user-data.jsp" %>
<div class=" col-sm-12 container no-padding tabs ">
	<a id="menu-personal-setting" onclick="afterLogin('<%=request.getContextPath() %>/private/personal-setting.jsp')" href="#" class="   "><span class="tabs-tab hoverAble-head">个人信息</span> </a>
	<a id="menu-personal-social" onclick="afterLogin('<%=request.getContextPath() %>/private/personal.follows.jsp')" href="#" class="   "><span class="tabs-tab hoverAble-head">朋友圈</span> </a>
	  
</div> 
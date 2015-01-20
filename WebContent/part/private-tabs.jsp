<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="show-user-data.jsp" %>
<div class=" col-sm-12 container no-padding tabs ">
	<a id="menu-index-1" href="<%=request.getContextPath() %>/<%=showUserName %>?t=1" class="   "><span class="tabs-tab hoverAble-head ">收藏的网址</span> </a>
	<a id="menu-index-2" href="<%=request.getContextPath() %>/<%=showUserName %>?t=2" class="   "><span class="tabs-tab hoverAble-head">网址导航</span> </a>
	 
	<a id="menu-personal-setting" onclick="afterLogin('<%=request.getContextPath() %>/private/personal-setting.jsp')" href="#" class="   "><span class="tabs-tab hoverAble-head">个人信息</span> </a>
	<a id="menu-personal-social" onclick="afterLogin('<%=request.getContextPath() %>/private/personal.follows.jsp')" href="#" class="   "><span class="tabs-tab hoverAble-head">朋友圈</span> </a>
	  
</div> 
<%@page import="com.tgy.util.PageType"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<div class=" col-sm-7 container no-padding tabs ">
	<a id="menu-index-all" href="<%=request.getContextPath()%>/share" class="   no-padding " style=" "><span class="tabs-tab hoverAble-head">首页</span> </a>
	<a id="menu-index-<%=PageType.word %>" href="<%=request.getContextPath()%>/share/<%=PageType.word %>" class="no-padding" style=" "><span class="tabs-tab hoverAble-head">热文</span> </a>
	<a id="menu-index-<%=PageType.resource %>" href="<%=request.getContextPath()%>/share/<%=PageType.resource %>" class="no-padding" style=" "><span class="tabs-tab hoverAble-head">资源</span> </a>
	<a id="menu-pan"  href="<%=request.getContextPath()%>/pan" class="   no-padding  " style=" "><span class="tabs-tab hoverAble-head">百度盘搜索</span> </a>
	<a id="menu-index-3" href="<%=request.getContextPath()%>/公用导航?t=3" class="   no-padding " style=" "><span class="tabs-tab hoverAble-head">公用导航</span> </a>
	<a id="menu-index-hot-user" href="<%=request.getContextPath()%>/index-hot-user.jsp" class="   no-padding " style=" "><span class="tabs-tab hoverAble-head">用户收藏夹</span> </a>
	
</div>
<div class=" col-sm-5 container no-padding tabs ">
	<span style="float: left;">快捷链接:</span> 
	<a href="<%=request.getContextPath()%>/美剧" target="_blank" class="no-padding tabs-quick-link" style=" "><span class="tabs-tab hoverAble-head">>>美剧</span> </a>
	<a href="http://www.baidu.com/" target="_blank" class="no-padding tabs-quick-link" style=" "><span class="tabs-tab hoverAble-head">>>百度</span> </a>
	<a href="http://www.gusouk.com/" target="_blank" class="no-padding tabs-quick-link" style=" "><span class="tabs-tab hoverAble-head">>>谷歌免翻墙</span> </a>
</div> 
<!-- 

 -->
	
	
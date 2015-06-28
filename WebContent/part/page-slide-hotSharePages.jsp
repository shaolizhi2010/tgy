<%@page import="com.tgy.service.cache.AppCache"%>
<%@page import="org.apache.commons.lang3.EnumUtils"%>
<%@page import="com.tgy.util.PageType"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="com.tgy.service.PageService"%>
<%@page import="com.tgy.entity.Tag"%>
<%@page import="com.tgy.util.ConditionMap"%>
<%@page import="com.tgy.statistic.service.TagService"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.PageUtil"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.statistic.service.LinkService"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
String tagName = request.getAttribute("tagName")!=null?(String)request.getAttribute("tagName"):null;
 
String type =  request.getAttribute("type")!=null?(String)request.getAttribute("type"):null;

PageType typeEnum = null;
if(StringUtils.isBlank(type) || !EnumUtils.isValidEnum(PageType.class, type)){
	type = null;
	typeEnum = PageType.resource;//default;
}
%>
<div id="" class="container col-sm-12 clearfix"
	style="padding: 2px; padding-left: 20px;padding-bottom: 10px; margin-bottom: 20px;  border-bottom: 1px solid #eee;">
	
		<div class="">
			<span style="font-size: 13px;font-weight: bold;">今日热门分享</span>
		</div>
	
	<%
		 
		 List<Page> pages =  AppCache.hotSharePages(typeEnum, tagName);
		 for(Page p: pages){
			 
		
		 %>
		 	<div style="margin-top: 10px;   height: 20px;">
				<a target="_blank" href="<%=p.url%>" class="col-sm-12">  
					<span class="hotPages-title" style=" "> <%= U.shortString(p.title,22) %></span> <!-- #ff076e #1155cc; 0000cc-->
				</a>  
			</div>
		 
		 <% }
	%>

</div>


<script src="<%=request.getContextPath()%>/myjs/index-all-tags.js"  ></script>
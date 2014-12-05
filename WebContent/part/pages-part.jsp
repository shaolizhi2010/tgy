<%@page import="com.tgy.App"%>
<%@page import="com.tgy.util.URLUtils"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="bookmark-data.jsp" %> 

<!-- 显示网址页面开始 -->
<div class="col-sm-12" style="padding: 0px; padding-bottom: 10px;  ">

	<%
		for (Page p : pages) {
			
			String link =p.url;
			if(link!=null && !link.startsWith("http:")){
				link = "http://"+link;
			}
			String linkshow = p.url;
			//link
			if (linkshow != null) {
				if (linkshow.startsWith("http://")) {
					linkshow = linkshow.replaceAll("http://", "");
				}
				if (linkshow.startsWith("https://")) {
					linkshow = linkshow.replaceAll("https://", "");
				}
				int linkLength = linkshow.length();
				if (linkLength > 20) {
					linkshow = linkshow.substring(0, 14) + "..."
							+ linkshow.substring(linkLength - 6);
				}
			}
			
			String pageName = p.name;
			if(StringUtils.isBlank(pageName)){
				pageName =linkshow;
			}
			//name
			if (pageName != null && pageName.length() > 16) {
				pageName = pageName.substring(0, 16) + "...";
			}
			
	%>
	<div class="hoverAble col-sm-12" style=" padding-left: 3px;">
	<a target="_blank" class="col-sm-11 editable pageMark " href="<%=link%>" 
		dataid="<%=p.id%>" dataname="<%=p.name%>"   ng-click="openLink('<%=p.id %>','page')"
		title = "<%=p.name %>"
		style="padding-left: 10px;padding-top: 6px;padding-bottom: 6px;   "> 

<%
					if(StringUtils.isNoneBlank(p.iconPath)  ){
						String iconPath = p.iconPath;
						if(iconPath.startsWith(App.imgPath)){
							iconPath = request.getContextPath()+"/"+iconPath;
						}
						%>
						<img style="width: 18px;height:18px;float: left; " data-original="<%=iconPath%> " alt='*' >
						<%
					}
					else{
						%>
						<img style="width: 18px;height:18px;float: left; " data-original="<%=request.getContextPath()%>/images/defaultFav.png " alt='<%=p.name%>' >
						<%
					}
				%>

		 <span
		style="color: #2e8cd8; padding-left: 15px;font-weight: bold;;font-size: 14px;"> <%=pageName%></span> - <span style="color:green;font-size: 11px;"><%=linkshow%></span> <!-- #ff076e #1155cc; 0000cc-->
		
		
	</a>  
	<a href="#" class="  pull-right deletePage  " data-id="<%=p.id %>" title="删除"  
		style="padding-left: 10px;padding-top: 6px;padding-bottom: 6px;   " >
		<span  class="pull-right " style="color:red;font-size: 10px;text-align: right;"> X </span>  
	</a>
	
	</div><!-- 1155cc --> 


	<%
		}
	%>

</div>
<!-- 显示网址页面结束 -->
<%@page import="com.tgy.App"%>
<%@page import="com.tgy.util.URLUtils"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="bookmark-data.jsp"%>

<!-- 显示all网址页面开始 -->
<div class="col-sm-12" style="padding: 0px; padding-bottom: 10px;">

	<%
		for (Folder folder : rootFolders) {
			String folderName = folder.name;
			
			if (folderName != null && folderName.length() > 8) {
				folderName = folderName.substring(0, 8) + "..";
			}
			
	%>
	<div class="col-sm-12"
		style="padding: 3px; margin-bottom: 10px; border-bottom: 1px solid #eee;">
		<a 
		class="btn col-sm-2 folderMark editable " title="<%=folder.name%>"
			 
			dataid="<%=folder.id%>" 
			dataname="<%=folder.name%>"
			name="<%=folder.id%>"
			id="<%=folder.id%>"
			href="<%=request.getContextPath()%>/folder/<%=folder.id%>/<%=folderName%>"
			ng-click="$event.preventDefault();openFolder('<%=folder.id %>','<%=folder.name %>',$event  );"
			
			style="background-color: #eee; border: 1px solid #ddd; margin-top: 2px; margin-bottom: 2px;">
			<span style="color: #666;"> <%=folderName%>
		</span>
		</a>
		<div class="col-sm-10" style="background-color:#fbfbfb; "> 
			<%
				for (Page p : folder.pages) {
						String pageName = p.name;
						pageName = StringUtils.trim(pageName); 
						//name
						pageName = U.shortTitle(pageName, 6);
						
						String pageTitle = p.name;
						String pageDescription = "";
						if(StringUtils.isNotBlank(p.description)){
							pageDescription = " - "+p.description;
						}
						if(StringUtils.isBlank(p.name)){
							pageTitle = p.url;
						}

						if (StringUtils.isBlank(pageName)) {
							pageName = p.url;
							pageName = U.shortURL(pageName,6);
						}
						String link = p.url;

						if (link != null && !link.startsWith("http:") && !link.startsWith("https:")) {
							link = "http://" + link;
						}
						
						
						//System.out.println(faviconUrl);
			%>

			<a dataid="<%=p.id%>" dataname="<%=pageTitle%>" title="<%=pageTitle+ pageDescription%>"
				class="btn col-xs-12 col-sm-4 col-md-3 page-all-link editable pageMark click-trace" href="<%=link%>"
				ng-click="openLink('<%=p.id%>','page')" target="_blank"
				 >
				<%
					if(StringUtils.isNoneBlank(p.iconPath)  ){
						String iconPath = p.iconPath;
						if(iconPath.startsWith(App.imgPath)){
							iconPath = request.getContextPath()+"/"+iconPath;
						}
						 
						%>
						<img style="width: 18px;height:18px;  float: left; " data-original="<%=iconPath %> " alt='<%=pageTitle%>' >
						<%
					}
					else{
						%>
						<img style="width: 18px;height:18px;float: left; " data-original="<%=request.getContextPath()%>/images/defaultFav.png " alt='<%=pageTitle%>' >
						<%
					}
				%>
				<span style="color: #222">  <%=(pageName)%></span><!-- float: left;padding-left: 10px; -->
			</a>

			<%
				}//end for pages
			%>

			<a title="添加网址" class="btn col-sm-3   " href="#"
				ng-click="preAddPageFunction('','','<%=folder.id%>') "
				style="border: 1px solid #eee; margin-top: 5px; margin-bottom: 5px; display: block; color: #222;">
				<span style="color: #eee; font-weight: bold;"> +</span>
			</a>

		</div>

	</div>
	<%
		
	%>


	<%
		}//end for folders
	%>

<%@include file="create-folderAndPage-part.jsp"%>
<%@include file="create-folderAndPage-part.jsp"%>
 
</div>
<!-- 显示网址页面结束 -->
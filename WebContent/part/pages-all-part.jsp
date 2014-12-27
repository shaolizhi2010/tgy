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
<div class="col-sm-12 no-padding" style="  padding-bottom: 10px;">

	<%
		for (Folder folder : rootFolders) {
			String folderName = folder.name;
			
			if (folderName != null && folderName.length() > 3) {
				folderName = folderName.substring(0, 3) + "..";
			}
			
	%>
	<div class="col-sm-12 no-padding sub-page folder-pages" >
		<a 
		class="btn  col-sm-2 folderMark editable " title="<%=folder.name%>"
			 
			dataid="<%=folder.id%>" 
			dataname="<%=folder.name%>"
			name="<%=folder.id%>"
			id="<%=folder.id%>"
			href="<%=request.getContextPath()%>/folder/<%=folder.id%>/<%=folderName%>"
			onclick="$event.preventDefault();openFolder('<%=folder.id %>','<%=folder.name %>',$event  );"
			
			style="  margin-top: 2px; margin-bottom: 2px;">
			<span style="color: #666;font-weight: bold;font-size: 15px;"> <%=folderName%>
		</span>
		</a>
		<div class="col-sm-10 container" style="  "> 
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
				class="  col-xs-12 col-sm-4 col-sm-3  folder-pages-link editable pageMark click-trace hoverAble  " href="<%=link%>"
				style="padding-right: 0px;"
				onclick="openLink('<%=p.id%>','page')" target="_blank">
				<%
					if(StringUtils.isNoneBlank(p.iconPath)  ){
						String iconPath = p.iconPath;
						if(iconPath.startsWith(App.imgPath)){
							iconPath = request.getContextPath()+"/"+iconPath;
						}
						 
						%>
						<img   style="width: 18px;height:18px;  float: left;margin-right: 10px; " data-original="<%=iconPath %> " alt='<%=pageTitle%>' >
						<%
					}
					else{
						%>
						<img   style="width: 18px;height:18px;float: left; margin-right: 10px; " data-original="<%=request.getContextPath()%>/images/defaultFav.png " alt='<%=pageTitle%>' >
						<%
					}
				%>
				  <span class="col-sm-9 no-padding" style=""><%=pageName%></span>  
			</a>

			<%
				}//end for pages
			%>
			<!-- 
			<a title="添加网址" class="btn col-sm-3  add-link " href="#"
				onclick="preAddPageFunction('','','<%=folder.id%>') "
				style="">
				<span style=""> +</span>
			</a>
 -->
		</div>

	</div>
	<%
		
	%>


	<%
		}//end for folders
	%>

 
</div>
<!-- 显示网址页面结束 -->
<%@page import="com.tgy.util.PageUtil"%>
<%@page import="com.tgy.entity.BaseFolder"%>
<%@page import="com.tgy.util.FolderUtil"%>
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
<div id="pages-all-container" class="col-sm-12 no-padding" style="  padding-bottom: 10px;">
	<div id="editPanel" class="col-sm-12" > 
		<a class="pull-right editPanelElement " href="#" onclick="preEditAll()">
			<span class="hoverAble-red" id="editAllBtn" > &nbsp;[ 快速编辑 ]&nbsp; </span> </a>
		<a  class="pull-right editPanelElement" href="#" onclick="preSortAll()">
			<span class="hoverAble-red" id="sortAllBtn" > &nbsp;[ 改变顺序 ]&nbsp; </span>
		</a>
		<a  class="pull-right" href="#" onclick="preAddPageFunction()">
			<span class="hoverAble-red"> &nbsp;[ 添加网址 ]&nbsp; </span></a>
	</div>
	<div id="returnCommonPanel" class="col-sm-12 " style="display: none;" > 
		<a class="pull-right" href="#">
			<span id="editAllBtn" > &nbsp;[ 返回浏览模式 ]&nbsp; </span> </a>
 
	</div>
	<div class="col-sm-12 info"></div>
	<%
		FolderUtil.sortByOrderIndex( rootFolders);
		for (Folder folder : rootFolders) {
			String folderName = folder.name;
			
			if (folderName != null && folderName.length() > 3) {
				folderName = folderName.substring(0, 3) + "..";
			}
			
	%>
	<div class="col-sm-12 no-padding sub-page folder-pages pages-all-subFolder" >
		<a  class="btn  col-sm-1 folderMark editable " title="<%=folder.name%>"
			 
			dataid="<%=folder.id%>" 
			dataname="<%=folder.name%>"
			name="<%=folder.id%>"
			id="<%=folder.id%>"
			href="<%=request.getContextPath()%>/folder/<%=folder.id%>/<%=folderName%>"
			style="  margin-top: 2px; margin-bottom: 2px;">
			<span style="font-size: 13px;" class="hoverAble-red">
				<%=folderName%>
			</span>
		</a>
		<div class="col-sm-11 container  pages-all-subFolder-pages" style="  "> 
			<%
				PageUtil.sortByOrderIndex( folder.pages);
				for (Page p : folder.pages) {
						String pageName = PageUtil.shortName(p, 4);
						String link =PageUtil.urlWithHttp(p);
			%>

			<a dataid="<%=p.id%>" dataname="<%=pageName%>" title="<%=pageName%>"
				class="  col-xs-12 col-xs-3 col-sm-2  folder-pages-link editable pageMark click-trace  pages-all-subFolder-pages-page hoverAble-red"  href="<%=link%>"
				style="padding-right: 0px;"
				onclick="openLink('<%=p.id%>','page')" target="_blank">
				<%
					if(StringUtils.isNoneBlank(p.iconPath)  ){
						String iconPath = p.iconPath;
						if(iconPath.startsWith(App.imgPath)){
							iconPath = request.getContextPath()+"/"+iconPath;
						}
						 
						%>
						<img   style="width: 18px;height:18px;  float: left;margin-right: 10px; " data-original="<%=iconPath %> " alt='<%=pageName%>' >
						<%
					}
					else{
						%>
						<img   style="width: 18px;height:18px;float: left; margin-right: 10px; " data-original="<%=request.getContextPath()%>/images/defaultFav.png " alt='<%=pageName%>' >
						<%
					}
				%>
				  <span class=" no-padding" style=""><%=pageName%></span>  
			</a>

			<%
				}//end for pages
			%>
		 
			<a title="添加网址" class="hoverAble-red col-sm-3 add-link" href="javascript:void(0)"
				onclick="preAddPageFunction('','','') "
				>
				<span> + 收藏网址 </span> 
			</a>
		</div>

	</div>
	<%
		
	%>


	<%
		}//end for folders
	%>

 
</div>
<!-- 显示网址页面结束 -->
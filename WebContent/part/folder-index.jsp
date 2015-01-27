<%@page import="com.tgy.dao.FolderDao"%>
<%@page import="com.tgy.util.URLUtils"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>
<%@page language="java" pageEncoding="UTF-8"%>

<%@include file="show-user-data.jsp"  %>  
<%@include file="bookmark-data.jsp"%>
 <%
 
 %>
<!-- folder index 开始 -->
<div id="folder-index" class="col-sm-12 container no-padding sub-page-with-title">
	<div class="col-sm-12 sub-page-title  no-padding">
		<span class="col-sm-5">收藏夹</span>
		<div class="col-sm-6 pull-right" style=" padding-top: 3px; ">
			<% if(isSelf){ %>
				<a class="pull-right hoverAble-red"  href="#"
					style="  " 
					title="编辑" onclick="preEditAll()"> &nbsp;[ 编辑 ]&nbsp;</a>
					
				<a class="pull-right hoverAble-red"  href="#"
					title="添加一个收藏夹" onclick="preCreateFolderFunction()"> &nbsp;[ 新建收藏夹 ]&nbsp;</a>
			<% } %>
			
		</div>
	</div>
	<div class="sub-page-body no-padding col-sm-12 container">
		<%
	 	if(CollectionUtils.isEmpty(rootFolders)){
	 		%>
	 		<div class="col-sm-12 ">
	 			还未创建任何收藏夹，现在就 <a href="#" onclick="preCreateFolderFunction()">添加一些</a> 吧
	 		</div>
	 		<%
	 	}
	 	else{
	 		String style= "";
	 		if(StringUtils.isBlank(showFolderID)){
	 			style = "text-decoration: underline;font-weight:bold;color:red;";
	 		}
	 		%>
	 		<a style="<%=style %>;" class="col-xs-4 col-sm-2 folder-index-element" href="<%=request.getContextPath()%>/u/<%=showUserID %>" >全部</a>	
	 		<%
	 			
		 		//Collections.reverse(rootFolders);
		 	 	for (Folder folder : rootFolders) {
		 	 		 
		 	 		String folderName = folder.name;
		 			if (folderName != null && folderName.length() > 12) {
		 				folderName = folderName.substring(0, 12) + "..";
		 			}
		 			if(StringUtils.equals(showFolderID, folder.id.toString())){
		 				style = "text-decoration: underline;font-weight:bold;color:red;";
		 			}
		 			else{
		 				style = "";
		 			}
		 	 		%> 
		 	 		<a style="<%=style %>;" 
			 	 		class="col-xs-4 col-sm-2 folder-index-element editable folderMark" 
			 	 		href="<%=request.getContextPath()%>/folder/<%=folder.id%>/<%=folder.name %>"
			 	 		dataid="<%=folder.id%>" 
						dataname="<%=folder.name%>"
						name="<%=folder.id%>"
						id="<%=folder.id%>"
		 	 		 ><%=folderName %></a>
		 	 		<%
		 	 	}
	 		}
	 	%>
	</div>
</div>
<!-- folder index结束 -->
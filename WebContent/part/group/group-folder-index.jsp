<%@page import="com.tgy.util.AuthManager"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%@page import="com.tgy.entity.group.InterestGroupFolder"%>
<%@page import="com.tgy.dao.FolderDao"%>
<%@page import="com.tgy.util.URLUtils"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>

<%@include file="group-data.jsp"  %>
 <%

 %>
 
<!-- folder index 开始 -->
<div id="folder-index" class="col-sm-12 container no-padding sub-page-with-title">
	<div class="col-sm-12 sub-page-title  no-padding">
		<span class="col-sm-5">收藏夹 &nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·&nbsp;·</span>
		<div class="col-sm-6 pull-right" style=" padding-top: 3px; ">
			<% if(editAble){ //编辑 %>
				<a class="pull-right hoverAble-red"  href="#"
					style="  " 
					title="编辑" onclick="preEditAll()"> &nbsp;[ 编辑 ]&nbsp;</a>
			<% } %>
			<% if(createAble){ //新增文件夹 %>
			<a href="<%=request.getContextPath() %>/group/folder/create/pre?groupID=<%=groupID %>" class="pull-right hoverAble-red" 
				title="添加一个收藏夹"  > &nbsp;[ 新建收藏夹 ]&nbsp;</a>
			<% } %>
		</div>
	</div>
	<div class="col-sm-12 container no-padding sub-page-body">
		<%
	 	if(CollectionUtils.isEmpty(group.folders)){
	 		%>
	 		<div class="col-sm-12 ">
	 			还未创建任何收藏夹，现在就 <a onclick="afterLogin('<%=request.getContextPath() %>/group/folder/create/pre?groupID=<%=groupID %>')"  href="#"  >添加一个</a> 吧
	 		</div>
	 		<%
	 	}
	 	else{
	 		String style= "";
	 		if(StringUtils.isBlank(showFolderID)){ 
	 			style = "background-color:#eee;";
	 		}
	 		%>
	 		<a style="<%=style %>" class="folder-index-element" href="<%=request.getContextPath()%>/g/<%=group.id %>" >全部</a>	
	 		<%
	 			
		 		//Collections.reverse(rootFolders);
		 	 	for (InterestGroupFolder folder : group.folders) {
		 	 		 
		 	 		String folderName = folder.name;
		 			if (folderName != null && folderName.length() > 12) {
		 				folderName = folderName.substring(0, 12) + "..";
		 			}
		 			if(StringUtils.equals(showFolderID, folder.id.toString())){
		 				style = "background-color:#eee;";
		 			}
		 			else{
		 				style = "";
		 			}
		 	 		%> 
		 	 		<a style="<%=style %>" 
		 	 		class=" folder-index-element editable folderMark" 
		 	 		href="<%=request.getContextPath()%>/group/folder/<%=folder.id%>/<%=folder.name %>"
					data-href-view="<%=request.getContextPath()%>/group/folder/<%=folder.id%>/<%=folder.name %>"
					data-href-edit="<%=request.getContextPath()%>/group/group.folder.edit.jsp?folderID=<%=folder.id%>&groupID=<%=groupID%>"
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
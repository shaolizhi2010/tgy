<%@page import="com.tgy.dao.FolderDao"%>
<%@page import="com.tgy.util.URLUtils"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="show-user-data.jsp"  %>  
<%@include file="bookmark-data.jsp"%>
 

<!-- folder fav 开始 -->
<div class="col-sm-12" style=" margin:0px; padding-bottom: 3px;padding-top: 1px; ">
 
 <div class="col-sm-12   " style="padding: 1px;"> 
 	<span style="font-size: 12px;">常用分类：</span>
 	
 	<%
 	List<Folder> recentList = new ArrayList<Folder>();
 	if(StringUtils.isNoneBlank(showUserID)){
 		recentList = new FolderDao().getFoldersByUserID(showUserID, "-lastModifyDate");
 	}
 	
 	int index = 0; 
 	
 	for (Folder folder : rootFolders) {
 		if(index++>4)break;
 		String folderName = folder.name;
		if (folderName != null && folderName.length() > 12) {
			folderName = folderName.substring(0, 12) + "..";
		}
 		%>
 		<a href="#<%=folder.id %>" style="padding-right: 10px;font-size: 13px;"><%=folderName %></a>
 		<%
 	}
 	%>
	 	<span style="font-size: 10px;color: #999;padding-left: 10px;">最新：</span>  
 	<%
 	index = 0;
 	for (Folder folder : recentList) {
 		if(index++>2)break;
 		String folderName = folder.name;
		if (folderName != null && folderName.length() > 12) {
			folderName = folderName.substring(0, 12) + "..";
		}
 		%>
 		<a href="#<%=folder.id %>" style="padding-right: 10px;font-size: 13px;"><%=folderName %></a>
 		<%
 	}
 	
 	%>
 
 </div>
 
</div>
<!-- 分割线 -->

<!-- folder fav  结束 -->
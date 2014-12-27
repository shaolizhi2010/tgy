<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.service.FolderService"%>
<%@page import="com.tgy.statistic.entity.Tag"%>
<%@page import="com.tgy.statistic.service.TagService"%>
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

<div id="public-tag-pop" class="col-sm-12 no-padding sub-page-with-title" >
	<div class="sub-page-title">
		<span>热门收藏夹</span>
	</div>
	<div class="sub-page-body">
	<%
 	FolderService fs = new FolderService();
	UserService us = new UserService();
 	List<Folder> folders = fs.list(40);
 	 	for (Folder folder : folders) {
 	 		String name = folder.name;
 			if (name != null && name.length() > 12) {
 				name = name.substring(0, 12) + "..";
 			}
 			String userID = folder.userID.toString();
 			User user = us.getByID(userID);
 			String username = "";
 			if(user!=null){
 				username = user.name ;
 			}
 	 		%> 
 	 		<a class="col-sm-12 public-folder-element hoverAble" href="<%=request.getContextPath()%>/folder/<%=folder.id %>/<%=folder.name %>" >
 	 			<span style="padding-left: 10px;"><%= name + " - " +username%></span> 
 	 			<!-- 
 	 			<div class="col-sm-1 pull-right glyphicon glyphicon glyphicon-plus" style="font-size: 8px;" title="拷贝到我的收藏夹"></div>
 	 			 -->
 	 		</a>
 	 		
 	 		<%
 	 	}
 	%>
	</div>
</div>

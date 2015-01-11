<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@page import="java.util.Collections"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%
Folder showFolder=null;
String showFolderName = "";
String showFolderID = "";
Long pageCount = 0L;
if(request.getAttribute("pageCount")!=null){
	pageCount = (Long)request.getAttribute("pageCount");
}

List<Folder> rootFolders = new ArrayList<Folder>();
//List<Folder> folders =  new ArrayList();
List<Page> pages =  new ArrayList<Page>();
BookmarkData bookmarkData = U.param(request, "bookmarkData", BookmarkData.class);
if(bookmarkData!=null){
	rootFolders = bookmarkData.getRootFolders();
	if(!CollectionUtils.isEmpty(rootFolders)){ 
		Collections.sort(rootFolders); 
		Collections.reverse(rootFolders); 
	}
	showFolder = bookmarkData.folder;
	if(showFolder!=null){
		showFolderName = showFolder.name;
		showFolderID =  showFolder.id.toString();
		pages=  showFolder.pages; //cur folder
		if(!CollectionUtils.isEmpty(pages)){
			Collections.sort(pages); 
			Collections.reverse(pages); 
		}
	}
}
	//BookmarkData loginUserBookmarkData = U.param(request, "loginUserBookmarkData", BookmarkData.class);
%>

<%
if(CollectionUtils.isEmpty(rootFolders)){
	%>
	<input type="hidden" id="bookmarkEmptyFlag" value="true">
	<%
}
%>
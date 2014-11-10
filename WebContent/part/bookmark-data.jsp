<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
 
<%
String curFolderName = "";	
String fid = "";
Folder curFolder=null;
Folder rootFolder = null;
List<Folder> rootFolders = new ArrayList();
List<Folder> folders =  new ArrayList();
List<Page> pages =  new ArrayList();

	BookmarkData bookmarkData = U.param(request, "bookmarkData", BookmarkData.class);
	
	if(bookmarkData!=null){
		 curFolder = bookmarkData.curFolder;
		 rootFolder = bookmarkData.rootFolder;
		
		if(curFolder!=null) curFolderName = curFolder.name;
		
		if(curFolder!=null) fid =  curFolder.id.toString();

		rootFolders = bookmarkData.getRootFolders();
		  
		if(rootFolder!=null) folders=  rootFolder.folders; //not cur folder
		 
		if(curFolder!=null) pages=  curFolder.pages; //cur folder
	}
	
	
%>
 
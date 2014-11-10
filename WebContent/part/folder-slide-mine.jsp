<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="org.apache.el.parser.JJTELParserState"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<%@include file="bookmark-data.jsp" %>

<div class="container col-md-12 clearfix"
	style="padding: 2px;   margin-bottom: 20px;">

<p style="padding:  3px;">收藏夹：</p>

<%
	for(Folder f : rootFolders){
		String name = f.name;
		if(name!=null && name.length()>6){
			name = name.substring(0,6)+"..";
		}
		String selectedStyle = "";
		String starStyle = "glyphicon-star-empty";
		if(rootFolder!=null && rootFolder.id!=null){
			if(StringUtils.equals(rootFolder.id.toString(), f.id.toString())){
				selectedStyle = "font-weight:bold;font-size:16px;";
				starStyle = "glyphicon-star";
			}	
		}
		
		
		%>
		<div class="col-md-12" style="padding:  3px;padding-right: 0px;">
		
		  <a  href="<%=request.getContextPath() %>/folder/<%=f.id %>/<%=name %>"
           class="folderMark editable col-md-12" dataid="<%=f.id%>" style=" padding: 0px; "
           dataname="<%=f.name%>"><span class=" glyphicon <%=starStyle%>"></span> 
           <span style="<%=selectedStyle%>">  <%=name%>  </span> 
             </a>
              
             </div>
		
		<%
	}
%>
	<a class="col-md-12" ng-click="preCreateRootFolderFunction()"
					style="margin-top: 10px;"> >>新建 </a>
	<a class="col-md-12"
					ng-click="preUploadBookmarkFunction()" style="margin-top: 10px;">
					>>导入 </a> 

 </div>
<%@page import="org.apache.el.parser.JJTELParserState"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<div class="container col-md-12 clearfix"
	style="padding: 2px;   margin-bottom: 20px;">


<p style="padding:  3px;">我的收藏夹：</p>
<%
	List<Folder> userFolders = U.paramList(request, "userFolders");
	for(Folder f : userFolders){
		String name = f.name;
		if(name!=null && name.length()>6){
			name = name.substring(0,6)+"..";
		}
		%>
		<div class="col-md-12" style="padding:  3px;">
		
		  <a  href="<%=request.getContextPath() %>/folder/?fid=<%=f.id %>"
           class="folderMark editable" dataid="<%=f.id%>"><span class=" glyphicon glyphicon-star-empty"></span> 
           <%=name%>
             </a>
              
             </div>
		
		<%
	}
%>
	<a class="col-md-12" ng-click="preCreateRootFolderFunction()"
					style="margin-top: 10px;"> >>新建 </a>
	<a class="col-md-12"
					ng-click="preUploadBookmarkFunction()" style="margin-top: 10px;">
					>>浏览器导入 </a>

 </div>
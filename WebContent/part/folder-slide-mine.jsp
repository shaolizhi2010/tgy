<%@page import="com.tgy.entity.User"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="org.apache.el.parser.JJTELParserState"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
 
 <%@include file="common.jsp" %>
 <%@include file="user-data.jsp" %>
<%@include file="bookmark-data.jsp" %>

<div class="container col-sm-12 clearfix"
	style="padding: 2px;   margin-bottom: 20px;">

<p style="padding:  3px;">分类：</p>

		<div class="col-sm-12" style="padding:  3px;padding-right: 0px;">
		
		  <a  href="<%=contextPath%>/u/<%=showUserID%>"
           class="folderMark editable col-sm-12" dataid="" style=" padding: 0px; "
           dataname="全部">
            
           <span   style="color:#d9534f"> </span>    
           
           <span style=" font-size:14px;"> <!-- font-weight:bold; color:#d9534f; -->
          	全部
            </span> 
             </a>
              
             </div>

<%
	for(Folder f : rootFolders){
		String name = f.name;
		if(name!=null && name.length()>6){
			name = name.substring(0,6)+"..";
		}
		String selectedStyle = "";
		String starStyle = "glyphicon-star-empty";

		
		%>
		<div class="col-sm-12" style="padding:  3px;padding-right: 0px;">
		
		  <a
		    href= "<%=request.getContextPath()%>/folder/<%=f.id%>/<%=f.name %>"
		    onclick="openFolder('<%=f.id %>','<%=f.name %>',$event  );$event.preventDefault();" 
		  	title="<%=f.name%>"   
           class="folderMark editable statistic_folder col-sm-12" 
           dataid="<%=f.id%>" 
           style=" padding: 0px; "
           dataname="<%=f.name%>">
            
            <%
    		if(showFolder!=null && showFolder.id!=null){
    			if(StringUtils.equals(showFolder.id.toString(), f.id.toString())){
    				selectedStyle = "font-weight:bold;";
    				%>
    				<span   style=" ">》</span>    <!--   -->
    				<%
    			}	
    		}
            %>
          
           
           <span style="<%=selectedStyle%>;  ;font-size:14px;"> 
            <%=name%>   
            </span> 
             </a>
              
             </div>
		
		<%
	}
%>
	<a class="col-sm-12" onclick="preCreateFolderFunction()"
					style="margin-top: 10px;"> >>新建分类 </a>
	<a class="col-sm-12"
					onclick="preUploadBookmarkFunction()" style="margin-top: 10px;">
					>>上传</a> 

 </div>
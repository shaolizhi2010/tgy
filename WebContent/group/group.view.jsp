<%@page import="com.tgy.entity.group.InterestGroup"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@page import="com.tgy.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
 
<%@include file="../part/common.jsp" %>
<%@include file="../part/group/group-data.jsp"  %>  
<%

//Folder showFolder = null;
//String showFolderID = null;
//String showFolderName = null;
%>
<!DOCTYPE html>
<html  >
<head lang="en">
<jsp:include page="../part/group/group-head-meta.jsp" />
<jsp:include page="../part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="../part/head.jsp" />
	<jsp:include page="../part/public-tabs.jsp" />
	<div class="  col-sm-12" style="margin-top: 10px;"></div>
 
 	<%
 		if(  StringUtils.isNotBlank(group.publicMessage)){
 	%> 
		<div class="container col-sm-12 clearfix " style=" font-size: 16px;">
 		 	 <%=group.publicMessage %> 
 		</div>
 			
 	<%
 		}
 	%>
 	 
	<!-- 书签主页面开始 -->
	<div class="container col-sm-12 clearfix " style="padding-top: 0px;">  
		<!-------- 书签主页面 --------->
		<div id="pageMain" class="col-sm-9 no-padding" >
			<div class="col-sm-12 group-info no-padding">
				<span class="group-info-title" ><%=groupName %></span>
				<%if(isJoined){%>
				
				<%}else{
					%>
					<a onclick="joinGroupFunction()" href="#" class="group-info-in"><span>加入群组</span></a>
				
				<%}%>
				
			</div>
				 
			<jsp:include page="../part/group/group-folder-index.jsp" />
			<div class="  col-sm-12" style="margin-top: 20px;"></div>
			<jsp:include page="../part/group/group-page-part.jsp" />
			
			<div class="  col-sm-12" style="margin-top: 10px;"></div>
		 	
		</div>
		<!--------  书签列表页面 end  --->
		<!-- 显示推荐页面开始 -->
		<div class="col-sm-3" style="  ">
			<div class="  col-sm-12">
				<jsp:include page="../part/group/group-discuss.jsp" />
			</div>		
			<div class="  col-sm-12">
				<jsp:include page="../part/group/group-users.jsp" />
			</div>
		</div>
		<!-- 显示推荐页面结束 -->


	</div>
	<!-- 书签主页面结束-->
	
	<jsp:include page="../part/foot.jsp" />
	<jsp:include page="../part/importAtFoot.jsp" />
	
	<script src="<%=request.getContextPath()%>/myjs/pageMainApp.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/group/group.view.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/group/group-page-part.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/group/group.discuss.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/group/group.edit-all.js"></script>
	
</body>

</html>
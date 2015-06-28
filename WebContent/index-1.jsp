<%@page import="java.util.Random"%>
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
 
<%@include file="part/common.jsp" %>
<%@include file="part/bookmark-data.jsp" %> 
<%@include file="part/show-user-data.jsp" %>
<!DOCTYPE html>
<html  >
<head lang="en">
<jsp:include page="part/head-meta.jsp" />
<jsp:include page="part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="part/head.jsp" />
	<%
	if(isSelf){ //这个flag从server来，server判断用户正在看的是自己的收藏
	%>
		<jsp:include page="part/private-tabs.jsp" />	
	<%
	}else if(loginFlag==true && (StringUtils.isBlank(showUserID) || StringUtils.equals(loginUserID, showUserID)  ) ){
		%>
		<jsp:include page="part/private-tabs.jsp" />
		<%
	}else{
		%>
		<jsp:include page="part/public-tabs.jsp" />
		
		<%
	}
	%>
 
 	<%
 		if(showUser!=null && StringUtils.isNotBlank(showUser.publicMessage)){
 	%>
 	<!-- 
		<div class="container col-sm-12 clearfix " style=" font-size: 16px;padding-bottom:10px; border-bottom:1px solid #f5f5f5;">
 		 	<span class="publicMessage"><%=showUser.publicMessage %></span>
 		</div>
 	-->
 			
 	<%
 		}
 	%>
 	
 	
 	
	<!-- 书签主页面开始 -->
	<div class="container col-sm-12 clearfix " style="padding-top: 0px;">  
		<!-------- 书签主页面 --------->
		<div id="pageMain" class="col-sm-9 no-padding" >
		
		  	<div class="  col-sm-12" style="margin-top: 20px;"></div>
 
			<div class="  col-sm-12" style="margin-top: 10px;"></div>
			
			<div class="  col-sm-12"  ></div>
			<%
			if(showUser!=null && showFolder==null &&StringUtils.equals("导航", showUser.showType) ){
				%>
				<jsp:include page="part/folder-fav.jsp" />
				<div class="  col-sm-12" style="margin-top: 10px;"></div>
				<jsp:include page="part/pages-all-part.jsp" />
				<%
			}
			else{
				%>
				<jsp:include page="part/folder-index.jsp" />
				<div class="  col-sm-12" style="margin-top: 20px;"></div>
				<jsp:include page="part/page-part.jsp" />
				<%
			}
			%>
			<div class="  col-sm-12" style="margin-top: 10px;"></div>
			<%
			if( !(showUser!=null && showFolder==null &&StringUtils.equals("导航", showUser.showType)) ){
				%>
				<div class="  col-sm-12" style="margin-top: 40px;"></div>
				<%--<jsp:include page="part/page-pop.jsp" /> --%> 
				<%
			}
			%>
			
		</div>
		<!--------  书签列表页面 end  --->
		<!-- 显示推荐页面开始 -->
		<div class="col-sm-3" style="  ">
			<div class="  col-sm-12"></div>
			
			<%//如果正在显示某用户
				if(StringUtils.isNotBlank(showUserName)){
					%>
					<jsp:include page="part/user-info.jsp" />
					<div class="  col-sm-12" style="margin-top: 20px;"></div>
					<%
				}
			%>
			<%//如果已经登陆
				if(loginFlag){
					%>
					<jsp:include page="part/page-slide-myHotClicks.jsp" />
					<div class="  col-sm-12" style="margin-top: 20px;"></div>
					<%
				}
			%>
			<jsp:include page="part/folder-slide-follow.jsp" />
			
			<!-- ad -->
			<div class="col-sm-12"  style="margin-top: 20px;"  >
					<script charset="gbk" type="text/javascript" src="http://union.dangdang.com/adapi/bang/?from=P-315609"></script>
				
		 	</div>
			<!-- ad end -->
			

 
		</div>
		<!-- 显示推荐页面结束 -->


	</div>
	<!-- 书签主页面结束-->


	<!-- hidden var begin -->

	<input type="hidden" id="loginFlag" value="<%=loginFlag %>">
	<input type="hidden" id="userID" value="<%=showUserID %>">
	<input type="hidden" id="contextPath" value="<%=contextPath%>">
	<input type="hidden" id="fid" value="<%=showFolderID%>">
	<input type="hidden" id="curFolder" value="<%=showFolderName%>">
	<input type="hidden" id="edit_pid" value="">
	<input type="hidden" id="edit_name" value="">
	<input type="hidden" id="edit_url" value="">

	<!-- hidden var end -->
	<input type="hidden" id="pageID" value="index-1">

	<!-- 弹出框开始 -->
	<jsp:include page="window/window.jsp" />
	<!-- 弹出框结束 -->
	
	<jsp:include page="part/foot-private.jsp" />
	<jsp:include page="part/importAtFoot.jsp" />
	
	<script src="<%=request.getContextPath()%>/myjs/pageMainApp.js" defer="defer"></script>
	<script src="<%=request.getContextPath()%>/myjs/common.js"  ></script>
	<script src="<%=request.getContextPath()%>/myjs/user-info.js" defer="defer"></script>
	<script src="<%=request.getContextPath()%>/myjs/index-1.js" defer="defer"></script>
</body>

</html>
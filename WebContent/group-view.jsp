<%@page import="com.tgy.entity.InterestGroup"%>
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
<%@include file="part/user-data.jsp" %>
<%
InterestGroup group = new InterestGroup();
if( request.getAttribute("group") !=null){
	group  =U.param(request, "group", InterestGroup.class);
}
Folder showFolder = null;
String showFolderID = null;
String showFolderName = null;
%>
<!DOCTYPE html>
<html  >
<head lang="en">
<jsp:include page="part/head-meta.jsp" />
<jsp:include page="part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="part/head.jsp" />

	<div class="  col-sm-12" style="margin-top: 10px;"></div>
 
 	<%
 		if(showUser!=null && showUser.authCreate==9){//public 
 	%>
		<div class="container col-sm-12 clearfix " style=" font-size: 16px;">
		 		这是一个神奇的页面！ 大家都可以添加收藏的网址，人人为我 我为人人！
				<a  href="#" title="添加一个网址" onclick="preAddPageFunction()">>>添加网址戳这里</a>
				
		</div>

 	<%
 		}
 	%>
 	<%
 		if(showUser!=null && StringUtils.isNotBlank(showUser.publicMessage)){
 	%>
		<div class="container col-sm-12 clearfix " style=" font-size: 16px;">
 		 	<h4></h4> <%=showUser.publicMessage %>
 		</div>
 			
 	<%
 		}
 	%>
 	 
	<!-- 书签主页面开始 -->
	<div class="container col-sm-12 clearfix " style="padding-top: 0px;">  
		<!-------- 书签主页面 --------->
		<div id="pageMain" class="col-sm-9 no-padding" >
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
				<jsp:include page="part/page-pop.jsp" />
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

	<!-- 弹出框开始 -->
	<jsp:include page="window/window.jsp" />
	<!-- 弹出框结束 -->
	
	<jsp:include page="part/foot.jsp" />
	<jsp:include page="part/importAtFoot.jsp" />
	
	<script src="<%=request.getContextPath()%>/myjs/pageMainApp.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
</body>

</html>
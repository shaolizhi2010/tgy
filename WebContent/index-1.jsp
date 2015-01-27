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
				<span>阿里旅游</span>
				<%
				int randomCount = 5;
				Random rad =  new Random();
				int i = 0;
				while(i<1){//找5圈，没有就退出
					if(rad.nextInt(randomCount)==1){
						%>
						<a class="col-sm-12 no-padding " 
							href="http://s.click.taobao.com/t?e=m%3D2%26s%3DVYPmic1qpAgcQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAruVGqbGPB3AqqacliaqsdAXPsBJarhjrlQlUTpNxHAmFMDQ4pd8PyuQnSfqJhGOSX7uq6sLYIqu0Q7QOybCaQy9AmARIwX9K9E0MBlxnM%2FDnaYpFBIfC%2F385cnhcjSS%2B6aAacK%2BFPJn" target="_blank"
							style="padding: 5px; ">
							<img  class="ad-img img-responsive" alt="阿里旅游" title="阿里旅游"
								data-original="http://gtms04.alicdn.com/tps/i4/TB13.MaGVXXXXX6XFXXBFn_2pXX-190-195.jpg"/>
						</a>
						<%
						i++;break;
					}				
					if(rad.nextInt(randomCount)==1){
						%>
						<a class="col-sm-12  no-padding " 
							href="http://s.click.taobao.com/t?e=m%3D2%26s%3DVYPmic1qpAgcQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAruVGqbGPB3AqqacliaqsdAXPsBJarhjrlQlUTpNxHAmFMDQ4pd8PyuQnSfqJhGOSX7uq6sLYIqu0Q7QOybCaQy9AmARIwX9K9E0MBlxnM%2FDnaYpFBIfC%2F385cnhcjSS%2B6aAacK%2BFPJn" target="_blank"
							style=" padding: 5px; ">
							<img  class="ad-img img-responsive" alt="阿里旅游" title="阿里旅游"
								data-original="http://gtms02.alicdn.com/tps/i2/TB1C9fHGVXXXXXnXFXXBFn_2pXX-190-195.jpg"/>
						</a>
						<%
						i++;break;
					}
					if(rad.nextInt(randomCount)==1){
						%>
						<a class="col-sm-12 no-padding  " 
							href="http://s.click.taobao.com/t?e=m%3D2%26s%3DVYPmic1qpAgcQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAruVGqbGPB3AqqacliaqsdAXPsBJarhjrlQlUTpNxHAmFMDQ4pd8PyuQnSfqJhGOSX7uq6sLYIqu0Q7QOybCaQy9AmARIwX9K9E0MBlxnM%2FDnaYpFBIfC%2F385cnhcjSS%2B6aAacK%2BFPJn" target="_blank"
							style=" padding: 5px;"> 
							<img  class="ad-img img-responsive" alt="阿里旅游" title="阿里旅游"
								data-original="http://img.taobao.com/bao/uploaded/i2/1096920918/TB27L.AbXXXXXaUXpXXXXXXXXXX_!!1096920918.jpg_190x190.jpg"/>
						</a>
						<%
						i++;break;
					}
					if(rad.nextInt(randomCount)==1){
						%>
						<a class="col-sm-12  no-padding " 
							href="http://s.click.taobao.com/t?e=m%3D2%26s%3DVYPmic1qpAgcQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAruVGqbGPB3AqqacliaqsdAXPsBJarhjrlQlUTpNxHAmFMDQ4pd8PyuQnSfqJhGOSX7uq6sLYIqu0Q7QOybCaQy9AmARIwX9K9E0MBlxnM%2FDnaYpFBIfC%2F385cnhcjSS%2B6aAacK%2BFPJn" target="_blank"
							style=" padding: 5px;">
							<img  class="ad-img img-responsive" alt="阿里旅游" title="阿里旅游"
								data-original="http://img.taobao.com/bao/uploaded/i2/852175380/TB2PZWBXVXXXXXoXXXXXXXXXXXX_!!852175380.jpg_190x190.jpg"/>
						</a>
						<%
						i++;break;
					}
				}
				
				%>
				 
				
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
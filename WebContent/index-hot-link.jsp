<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.statistic.service.LinkService"%>
<%@page import="com.tgy.service.FolderService"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="part/common.jsp" %>
<%@include file="part/bookmark-data.jsp" %> 
<%@include file="part/user-data.jsp" %>
<!DOCTYPE html>
<html>
<head lang="en">
<jsp:include page="part/head-meta.jsp" />
<jsp:include page="part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="part/head.jsp" />
 
	<div class="  col-sm-12" style="margin-top: 20px;"></div>
 	
 	<!-- 菜单 -->
 	<div class=" col-sm-3 no-padding" >
		<div class=" col-sm-10 col-sm-offset-1 no-padding" >
			<jsp:include page="part/index.public.menu.jsp"/>
		</div>
	</div>
	
	<!-- 主体内容 -->
	<div class=" col-sm-9 no-padding">
	
	<%
 	LinkService ls = new LinkService();
 	List<Link> links = ls.list(20);
 	 	for (Link linkObj : links) {
 	 		if (linkObj.keeps >= 0 //TODO 收藏次数大于1 才显示，只收藏过一次的可能是一些比较个性化的网址，没必要显示
					) {
				String link =linkObj.url;
				if(link!=null && !link.startsWith("http:")){
					link = "http://"+link;
				}
				String linkshow = linkObj.url;
				//link
				if (linkshow != null) {
					if (linkshow.startsWith("http://")) {
						linkshow = linkshow.replaceAll("http://", "");
					}
					if (linkshow.startsWith("https://")) {
						linkshow = linkshow.replaceAll("https://", "");
					}
					int linkLength = linkshow.length();
					if (linkLength > 20) {
						linkshow = linkshow.substring(0, 12) + "..."
								+ linkshow.substring(linkLength - 8);
					}
				}
				
				String pageName = linkObj.title;
				if(StringUtils.isBlank(pageName)){
					pageName =linkshow;
				}
				//name
				if (pageName != null && pageName.length() > 24) {
					pageName = pageName.substring(0, 24) + "...";
				}
						
						
	%>
		<div class="col-sm-5   info-card">
			<div class="col-sm-4">
				<a href="<%=link %>" target="_blank">
				<%
					if(StringUtils.isBlank(linkObj.iconPath)){
						%>
						<img style="height: 40px;width: 40px;" alt="<%=pageName %>" data-original="<%=request.getContextPath()+"/images/defaultFav.png"%>">
						<%
					}
					else{
						%>
						<img style="height: 40px;width: 40px;" alt="<%=pageName %>" data-original="<%=request.getContextPath()+"/"+ linkObj.iconPath%>">
						<%
					}
				%> 
				</a>
			</div>
			<div class="col-sm-8 link-name">
				<a href="<%=link %>" target="_blank">
					<span><%=pageName %></span></a>
				<div class="share-info"><span></span> <span><%=linkObj.favScore %> 访问   <%=linkObj.keeps %> 收藏 </span> </div>
			</div>
		</div>
		
		<%
			}
 	 	}
		%>
		
	</div>
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
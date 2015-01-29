<%@page import="org.apache.commons.lang3.math.NumberUtils"%>
<%@page import="com.tgy.util.PageType"%>
<%@page import="com.tgy.entity.group.InterestGroupPage"%>
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

<%
	List<InterestGroupPage> pages = (List<InterestGroupPage>)U.paramList(request, "pages");
	String tagName = (String)request.getAttribute("tagName");

%>
<!DOCTYPE html>
<html  >
<head lang="en">
<jsp:include page="part/head-meta.jsp" />
<jsp:include page="part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="part/head.jsp" />
	<jsp:include page="part/public-tabs.jsp" />
 	
	<!-- 书签主页面开始 -->
	<div class="container col-sm-12 clearfix " style="padding-top: 0px;">  
		<!-------- 书签主页面 --------->
		<div id="pageMain" class="col-sm-8 no-padding" style=" padding-right: 30px; ">
		  	
		  
		  	<div class="col-sm-12" style="margin-top: 10px;"></div>
		  	<div class="col-sm-12 no-padding" style="margin-top: 10px;">
		  		<span style=" color:#747F8C;font-size: 20px;">热点分类</span>
		  	</div>
		  	
		  	<div class="col-sm-12   container " style="padding: 10px;">
					<a class="  index-tag" href="<%=request.getContextPath()%>/share/自媒体" 
						style=" ">
						 自媒体
					</a>
					<a class="  index-tag" href="<%=request.getContextPath()%>/share/百度网盘" 
						style=" ">
						 百度网盘
					</a>
 	 			  	<a class="  index-tag" href="<%=request.getContextPath()%>/share/电影" 
						style=" ">
						电影
					</a>
					<a class="  index-tag" href="<%=request.getContextPath()%>/share/美剧" 
						style=" ">
						 美剧
					</a>
					<a class="  index-tag" href="<%=request.getContextPath()%>/share/小说下载" 
						style=" ">
						 小说下载
					</a> 
					<a class="  index-tag" href="<%=request.getContextPath()%>/share/高清下载" 
						style=" ">
						高清下载
					</a> 
						 
		  	</div>
		  	
		  	<div class="col-sm-12" style="border: 1px solid #eee;"></div>
		  	 
		  	<div class="col-sm-12" style="margin-top: 10px;"></div>
		 	<span style=" color:#747F8C;font-size: 20px;">最新网址分享 </span>
		  	<!-- 
		  	 	<span style=" color:#333;font-size: 13px;font-weight: bold;">网友分享的文章</span>
		  	<span style=" color:#747F8C;font-size: 20px;">网友分享的文章</span>
		  	<div class="col-sm-12  " style="border: 1px solid #eee;"></div>
		  	 -->
		  	<div class="col-sm-12   " style="margin-top: 10px;"></div>
		  	
	 		<jsp:include page="part/page-part2.jsp"/>
		</div>
		<!--------  书签列表页面 end  --->
		
		<!-- 显示推荐页面开始 -->
		<div class="col-sm-4" style="border-left:1px solid #eee;padding-left: 10px; ">
			<div id="discuss-container" class="  col-sm-12">
				<jsp:include page="part/discuss/all-discuss.jsp">
			    	<jsp:param name="sourceBoardName" value= "首页" />
			    </jsp:include>
			</div>		
		</div>
		<!-- 显示推荐页面结束 -->

	</div>
	<!-- 书签主页面结束-->
	
	<!-- hidden var end -->
	<input type="hidden" id="pageID" value="index-homepage">
	
	<jsp:include page="part/foot.jsp" />
	<jsp:include page="part/importAtFoot.jsp" />
	
	<script src="<%=request.getContextPath()%>/myjs/common.js"  ></script>
	<script src="<%=request.getContextPath()%>/myjs/user-info.js" defer="defer"></script>
	<script src="<%=request.getContextPath()%>/myjs/index-1.js" defer="defer"></script>
</body>

</html>
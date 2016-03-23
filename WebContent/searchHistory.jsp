<%@page import="org.apache.commons.lang3.EnumUtils"%>
<%@page import="com.tgy.entity.Tag"%>
<%@page import="com.tgy.statistic.service.TagService"%>
<%@page import="org.apache.commons.lang3.math.NumberUtils"%>
<%@page import="com.tgy.util.PageType"%>
<%@page import="java.util.Random"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
 
<%@include file="part/common.jsp" %>
<%
//String url = request.getAttribute("javax.servlet.forward.request_uri").toString();
//System.out.println("url : "+ url ) ;
//String queryString  = request.getQueryString();

%>

<%
	List<Page> pages = (List<Page>)U.paramList(request, "pages");
	String tagName = request.getAttribute("tagName")!=null?(String)request.getAttribute("tagName"):"";
	
	String firstLetter =  request.getAttribute("firstLetter")!=null?(String)request.getAttribute("firstLetter"):"";
	String type =  request.getAttribute("type")!=null?(String)request.getAttribute("type"):"";
	String pageID = type;
	List<Tag> tags = new ArrayList<Tag>();
	if(request.getAttribute("tags")!=null){
		tags = (List<Tag>)request.getAttribute("tags");
	}
	
	String pageName = "";
	if(StringUtils.isNotBlank(tagName)){
		pageName = tagName;
	}
	else if(EnumUtils.isValidEnum(PageType.class, type)){
		pageName = PageType.valueOf(type).value();
	}
	
	if(StringUtils.isBlank(pageID)){
		pageID = "all";
	}
	
%>
<!DOCTYPE html>
<html  >
<head lang="en">
<jsp:include page="part/head-meta-indexAll.jsp" />
<jsp:include page="part/importAtHead.jsp" />
</head>
<body>
	
	<input id="tagName" type="hidden" value="<%=tagName%>">
	<input id="type" type="hidden" value="<%=type%>">
	
	<jsp:include page="part/head.jsp" />
	<jsp:include page="part/public-tabs.jsp" />
 	
	<!-- 书签主页面开始 -->
	<div class="container col-sm-12 clearfix " style="padding-top: 0px;padding-left: 0px;">  
	

	
		<!-------- 书签主页面 --------->
		<div id="pageMain" class="col-sm-8 no-padding  " >
		
			<div class="col-sm-12 no-padding  " >
				<jsp:include page="part/indexAll-tabs.jsp" />
			</div>
			
			<div class="col-sm-12    " style=" padding-right: 30px; ">
				<div id="indexAll-tags" class="col-sm-12 " style="margin-top: 10px; display: none;">
			
				  	<div class="col-sm-12   container " style="padding: 10px;">
			  		<%
			  			for(Tag t : tags){
			  				String selectedClass = "";
			  				if(t.name.equals(tagName)){ selectedClass = "color:#fff;background-color: #65c330;";}
			  				else{selectedClass = "";}
			  				%>
			  				<a class="index-tag" href="<%=request.getContextPath()%>/share/<%=type %>?tagName=<%=t.name %> " 
								style="<%=selectedClass %> ">
								 <%=t.name %>
							</a>
			  				
			  				<%
			  			}
			  		%>
						 
		  			</div>
		  	
		 		 </div>
		 		 
		 		 <%
		 		 if(StringUtils.equals(type, PageType.resource.name())){
		 			 %>
		 			 <div id="indexAll-firstLetters" class="col-sm-12 container no-padding " style="display: none;">
			 		 	<%
			 		 	for(int i=(int)'A';i<=(int)'Z';i++){
							%>
							<a href="<%=request.getContextPath()%>/share/<%=type %>?tagName=<%=tagName %>&letter=<%=(char)(i+32) %>" class="  index-tag"><%=(char)i %> </a>
							<%
			 		 		 
						}
						%>
			 		 </div>
		 			 <%
		 		 }
		 		 %>
		 		 
			  	<div class="col-sm-12" style="border: 1px solid #f9f9f9;"></div>
			
			  	<div class="col-sm-12" style="margin-top: 10px;"></div>
			 	<div class="col-sm-12 no-padding"  >
			 		<span style=" color:#747F8C;font-size: 20px;">网盘分享 - 最新百度网盘搜索</span>
			 		<a class="pull-right " href="#create-share-link" >
			 			<span class=" myBtn" style="font-size: 20px;">[发布分享]</span></a>
			  	</div>
			  	<!-- 
			  	 	<span style=" color:#333;font-size: 13px;font-weight: bold;">网友分享的文章</span>
			  	<span style=" color:#747F8C;font-size: 20px;">网友分享的文章</span>
			  	<div class="col-sm-12  " style="border: 1px solid #eee;"></div>
			  	 -->
		  		<div class="col-sm-12   " style="margin-top: 10px;"></div>
		  	
	 			<jsp:include page="part/searchHistory-part.jsp"/>
	 				 
	 			
	 			<div class="col-sm-12   " style="margin-top: 10px;"></div>
			</div>
			<div id="create-share-link" class="col-sm-12  ">
				<div  class="col-md-10    " style="padding-left: 0px;" >
					<input id="share_pageUrl"  class=" form-control enterInput"  
						style=" border-radius:5px;border:1px solid #ddd;height: 40px;" placeholder="输入要共享的网址"  type="text" value=""/>
				</div>
				<div class="col-sm-2  " style="padding-left: 0px;" >
					<input id="create-share-link-btn" class="btn btn-primary col-sm-12" style="height: 40px;border-radius:5px;"  
						 type="button" value="发布">
				</div>
			</div>
			
			
			
		<!--   ad -->
			<div class="col-sm-12   " style="margin-top: 30px;"></div>
			<jsp:include page="part/ad/ad-taobao-temai.jsp"/>
		<!--   ad -->
		
		</div>
		<!--------  书签列表页面 end  --->
		
		<!-- 显示推荐页面开始 -->
		<div class="col-sm-4" style="border-left:1px solid #eee;padding-left: 10px; ">
			
			<div class="col-sm-12   " style="margin-top: 20px;"></div>
			
			<jsp:include page="part/page-slide-tags.jsp"/>
			
			<jsp:include page="part/page-slide-firstLetter.jsp"/>
			
			<jsp:include page="part/page-slide-hotSharePages.jsp"/>
			
			<!-- ad-->
			
			<!-- ad-->
			
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
	<input type="hidden" id="pageID" value="index-<%=pageID%>">
	
	<jsp:include page="part/foot.jsp" />
	<jsp:include page="part/importAtFoot.jsp" />
	
	<script src="<%=request.getContextPath()%>/myjs/common.js"  ></script>
	<script src="<%=request.getContextPath()%>/myjs/user-info.js" defer="defer"></script>
	<script src="<%=request.getContextPath()%>/myjs/index-all.js" defer="defer"></script>
	
</body>

</html>
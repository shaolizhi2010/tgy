<%@page import="java.net.URLEncoder"%>
<%@page import="com.tgy.util.FuliDou"%>
<%@page import="com.tgy.entity.TopicPic"%>
<%@page import="com.tgy.entity.TopicSumary"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.entity.Topic"%>
<%@page import="com.tgy.service.TopicService"%>
<%@page import="org.apache.commons.lang3.EnumUtils"%>
<%@page import="com.tgy.entity.Tag"%>
<%@page import="com.tgy.statistic.service.TagService"%>
<%@page import="org.apache.commons.lang3.math.NumberUtils"%>
<%@page import="com.tgy.util.PageType"%>
<%@page import="java.util.Random"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>

<%@include file="part/common.jsp"%>
<%
	//String url = request.getAttribute("javax.servlet.forward.request_uri").toString();
	//System.out.println("url : "+ url ) ;
	//String queryString  = request.getQueryString();
%>

<%
	List<Page> pages = (List<Page>) U.paramList(request, "pages");
	String tagName = request.getAttribute("tagName") != null ? (String) request
			.getAttribute("tagName") : "";

	String firstLetter = request.getAttribute("firstLetter") != null ? (String) request
			.getAttribute("firstLetter") : "";
	String type = request.getAttribute("type") != null ? (String) request
			.getAttribute("type") : "";
	String pageID = type;
	List<Tag> tags = new ArrayList<Tag>();
	if (request.getAttribute("tags") != null) {
		tags = (List<Tag>) request.getAttribute("tags");
	}

	String pageName = "";
	if (StringUtils.isNotBlank(tagName)) {
		pageName = tagName;
	} else if (EnumUtils.isValidEnum(PageType.class, type)) {
		pageName = "福利";
	}

	if (StringUtils.isBlank(pageID)) {
		pageID = "all";
	}
%>
<!DOCTYPE html>
<html>
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
	<div class="container col-sm-12 clearfix "
		style="padding-top: 0px; padding-left: 0px;">



		<!-------- 书签主页面 --------->
		<div id="pageMain" class="col-sm-8 col-xs-12 no-padding  ">

			<div class="col-sm-12    " style="padding-right: 30px;">
				<div id="indexAll-tags" class="col-sm-12 "
					style="margin-top: 10px; display: none;">

					<div class="col-sm-12   container " style="padding: 10px;">
						<%
							for (Tag t : tags) {
								String selectedClass = "";
								if (t.name.equals(tagName)) {
									selectedClass = "color:#fff;background-color: #65c330;";
								} else {
									selectedClass = "";
								}
						%>
						<a class="index-tag"
							href="<%=request.getContextPath()%>/share/<%=type%>?tagName=<%=t.name%> "
							style="<%=selectedClass%> "> <%=t.name%>
						</a>

						<%
							}
						%>

					</div>

				</div>

				<%
					if (StringUtils.equals(type, PageType.resource.name())) {
				%>
				<div id="indexAll-firstLetters"
					class="col-sm-12 container no-padding " style="display: none;">
					<%
						for (int i = (int) 'A'; i <= (int) 'Z'; i++) {
					%>
					<a
						href="<%=request.getContextPath()%>/share/<%=type%>?tagName=<%=tagName%>&letter=<%=(char) (i + 32)%>"
						class="  index-tag"><%=(char) i%> </a>
					<%
						}
					%>
				</div>
				<%
					}
				%>

				<div class="col-sm-12" style="border: 1px solid #f9f9f9;"></div>

				<div class="col-sm-12" style="margin-top: 10px;"></div>
				<div class="col-sm-12 no-padding">
					<span style="color: #747F8C; font-size: 20px;">网盘分享 - <%=pageName%></span>
					<a class="pull-right " href="#create-share-link"
						title="奖励  <%=FuliDou.createSharePageScore%>个 福利豆"> <span
						class=" myBtn" style="font-size: 20px;">[发布分享]</span></a>
				</div>
				<!-- 
			  	 	<span style=" color:#333;font-size: 13px;font-weight: bold;">网友分享的文章</span>
			  	<span style=" color:#747F8C;font-size: 20px;">网友分享的文章</span>
			  	<div class="col-sm-12  " style="border: 1px solid #eee;"></div>
			  	 -->
				<div class="col-sm-12   " style="margin-top: 10px;"></div>

				<jsp:include page="part/page-part2.jsp" />

				<div class="col-sm-12   " style="margin-top: 10px;"></div>
			</div>
			
			<div class="col-sm-12   " style="margin-top: 50px;"></div>
			<div id="create-share-link" class="col-sm-12  "
				style="padding: 30px;">
				<h2>给小伙伴们发资源，分享网盘链接</h2>
				<div class="col-md-10     " style="padding-left: 0px;">
					<input id="share_pageUrl" class=" form-control enterInput"
						style="border-radius: 5px; border: 1px solid #ddd; height: 40px;"
						placeholder="资源链接" type="text" value="" />
				</div>
				<div class="col-md-10" style="padding-left: 0px; margin-top: 10px;">
					<input id="share_pageName" class=" form-control enterInput"
						style="border-radius: 5px; border: 1px solid #ddd; height: 40px;"
						placeholder="资源标题" type="text" value="" />
				</div>
				<div class="col-md-10" style="padding-left: 0px; margin-top: 10px;">
					<input class="form-control enterInput" id="share_pageComment"
						style="border-radius: 5px; border: 1px solid #ddd; height: 40px;"
						placeholder="资源简介" type="text" value="" />
				</div>
				<div class="col-sm-12   " style="margin-top: 10px;"></div>
				<div class="col-sm-10  " style="padding-left: 0px;">
					<input id="create-share-link-btn" class="btn btn-primary col-sm-2"
						style="height: 40px; border-radius: 5px;" type="button" value="发布"
						title="奖励  <%=FuliDou.createSharePageScore%>个 福利豆"> <span class="col-md-4"
						style="padding-left: 20px; line-height: 40px;"> (发布资源奖励 <%=FuliDou.createSharePageScore%>
						福利豆)
					</span>
					<div class="col-md-2 pull-right">
						<input class="form-control enterInput" id="share_tagName"
							style="border-radius: 5px; border: 1px solid #ddd; height: 40px;"
							placeholder="标签" type="text" value="" />
					</div>
					<div class="col-md-4 container pull-right">
						<span class="col-sm-6"> </span> <input class="form-control enterInput col-sm-2" id="share_fulidou"
							style="border-radius: 5px; border: 1px solid #ddd; height: 40px;"
							placeholder="需福利豆" type="text" value="1" title="需福利豆"/>
					</div>
					
				</div>
			</div>
			<div class="col-sm-12   " style="margin-top: 10px;"></div>
		 	
		 	<div class="col-sm-12   " style="margin-top: 20px;"></div>
		 	
			<!-- 说明 -->
			<div class="col-sm-12   " style="margin-top: 50px;">
				<div class="col-sm-12   ">
					<h3>福利豆是什么</h3>
					<p>网盘盒子(www.webhezi.com)为了奖励网友分享资源而设置的站内积分。</p>
					<br>

					<h3>福利豆有什么用</h3>
					<p>站内用户可以发布福利资源，福利资源需要福利豆才可打开，福利豆会转给发布福利者。</p>
					<br>

					<h3>福利豆怎么获取</h3>
					<p>
						1. 发布分享链接，可得<%=FuliDou.createSharePageScore%>个福利豆。 <!-- ，之后每被查看一次，可得1个福利豆 -->
					</p>
					<p>
						2. 完善专题信息，为专题添加简介或图片，可得<%=FuliDou.createTopicInfoScore%>个福利豆。
					</p>
 
					<p >不过不要发布色情、反动、垃圾信息，站长可不想被抓哦。滥发垃圾信息扣<span style="color: orange;font-weight: bold;font-size: 22px;"> 一 百 万 豆 </span>哦亲！</p>
					<p>登陆后，在登录名的旁边能看到福利豆。</p>
					<br>
				</div>
			</div>
 
			<%
			 if(request.getRequestURL().indexOf("localhost") <0){ //
			 %>
			<div class="col-sm-12" style="margin-top: 10px; padding-left: 20px;">
				 <jsp:include page="part/ad/ad-all.jsp">
					<jsp:param name="width" value="300" />
					<jsp:param name="height" value="250" />
				</jsp:include>
 
			</div>
			 <%} %>	
			 
			  
		 	
		</div>
		<!--------  书签列表页面 end  --->

		<!-- 显示推荐页面开始 -->
		<div class="col-sm-4 col-xs-12"
			style="border-left: 1px solid #eee; padding-left: 10px;">

			<div class="col-sm-12   " style="margin-top: 40px;"></div>

			<!-- ad -->
			<%
			 if(request.getRequestURL().indexOf("localhost") <0){
			 %>
			
			
			<jsp:include page="topic/topic-slide.jsp">
				<jsp:param name="tagName" value='<%=tagName%>' />
			</jsp:include>
			
		 	<div class="col-sm-10 col-sm-offset-1 " style="margin-top: 20px;  ">
				
				<script type="text/javascript">
				    var cpro_id="u2199079";
				    (window["cproStyleApi"] = window["cproStyleApi"] || {})[cpro_id]={at:"3",rsi0:"300",rsi1:"250",pat:"17",tn:"baiduCustNativeAD",rss1:"#FFFFFF",conBW:"1",adp:"1",ptt:"0",titFF:"",titFS:"14",rss2:"#000000",titSU:"0"}
				</script>
				<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>

			</div>
			 <%} %>

			<div class="col-sm-12   " style="margin-top: 50px;"></div>

			<div id="discuss-container" class="  col-sm-12">
				<jsp:include page="part/discuss/all-discuss.jsp">
					<jsp:param name="sourceBoardName" value='福利' />
				</jsp:include>
			</div>

			<div class="col-sm-12" style="height: 20px;"></div>
			
			<jsp:include page="part/page-slide-hotSharePages.jsp" />

			<div class="col-sm-12" style="height: 20px;"></div>
						
			<%
			 if(request.getRequestURL().indexOf("localhost") <0){ //
			 %>
			<div class="col-sm-12" style="margin-top: 10px; padding-left: 20px;">
				 
 				<script type="text/javascript">
				    /*侧边栏 主题链接 pc*/
				    var cpro_id = "u2188174";
				</script>
				<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>
 
			</div>
			 <%} %>	
			 
			 <div class="col-sm-12" style="height: 60px;"></div>
			
			<jsp:include page="part/page-slide-tags.jsp" />

			<div class="col-sm-12   " style="margin-top: 20px;"></div>

			<jsp:include page="part/page-slide-hotKeywords.jsp" />	
			
			<div class="col-sm-12   " style="margin-top: 20px;"></div>
			 
			<jsp:include page="part/page-slide-firstLetter.jsp" />			
		


		</div>
		<!-- 显示推荐页面结束 -->

	</div>
	<!-- 书签主页面结束-->

	<!-- hidden var end -->
	<input type="hidden" id="pageID" value='index-fuli'>
	
	<a id="tempHiddenLink"  style=" " href="" target="_blank">   </a> 
 
	<jsp:include page="part/foot.jsp" />

	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/index-all.js"
		defer="defer"></script>
	<script src="<%=request.getContextPath()%>/myjs/reply.js" defer="defer"></script>
	<script src="<%=request.getContextPath()%>/myjs/topic.js" defer="defer"></script>
	<script src="<%=request.getContextPath()%>/myjs/user-info.js"
		defer="defer"></script>
</body>

</html>
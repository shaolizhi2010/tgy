<%@page import="com.tgy.entity.TopicPic"%>
<%@page import="com.tgy.entity.TopicSumary"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.entity.Reply"%>
<%@page import="com.tgy.entity.Topic"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@page import="org.apache.commons.lang3.EnumUtils"%>
<%@page import="com.tgy.entity.Tag"%>
<%@page import="com.tgy.statistic.service.TagService"%>
<%@page import="org.apache.commons.lang3.math.NumberUtils"%>
<%@page import="com.tgy.util.PageType"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>

<%@include file="../part/common.jsp"%>

<%
	String title = String.valueOf(request.getAttribute("title"));

	Topic topic = new Topic();
	if (request.getAttribute("topic") != null) {
		topic = (Topic) request.getAttribute("topic");
	}
	List<Page> pages = new ArrayList<Page>();
	if (request.getAttribute("links") != null) {
		pages = (List<Page>) request.getAttribute("links");
	}
	List<Reply> replies = new ArrayList<Reply>();
	if (request.getAttribute("replies") != null) {
		replies = (List<Reply>) request.getAttribute("replies");
	}
%>

<!DOCTYPE html>
<html>
<head lang="en">
<jsp:include page="../part/head-meta-indexAll.jsp" />
<jsp:include page="../part/importAtHead.jsp" />
<style type="text/css">
#topic-tilte-container h1 {
	word-wrap: break-word;
	display: block;
	font-size: 25px;
	font-weight: bold;
	color: #494949;
	margin: 0;
	padding: 0 0 15px 0;
	line-height: 1.1;
}

#topic-summary-container {
	font: 12px Helvetica, Arial, sans-serif;
	line-height: 1.62;
}

.topic-links-link {
	display: block;
}

.topic-links-link span {
	font-family: 微软雅黑, 黑体;
	font-size: 15px;
	font-weight: normal;
	word-wrap: break-word;
	word-break: break-all;
}

.topic-links-link:hover {
	
}

.topic-links-link span:hover {
	color: #ce310d;
	text-decoration: underline;
}
</style>
</head>
<body>
	<jsp:include page="../part/head.jsp" />
	<jsp:include page="../part/public-tabs.jsp" />

	<!--  主页面开始 -->
	<div class="container col-sm-12 clearfix "
		style="padding-top: 0px; padding-left: 0px;">

		<!--------  左侧主页面 --------->
		<div id="pageMain" class="col-sm-8 col-xs-12 no-padding">

			
				<div id="topic-links-container" class="col-sm-12">
					<h4>资源链接:</h4>
					<div class="col-sm-12" id="topic-links-links">
						<%
							for (Page p : pages) {
						%>
						<a href="<%=p.url%>" class="col-sm-12 topic-links-link"><span><%=p.title%></span></a>
						<%
							}
						%>

					</div>
					<div class="col-sm-12">
						<a class="" style="float: right;" href="#">[ 添加链接]</a>
					</div>
				</div>

		</div>
		<!--------   左侧主页面 end  --->

		<!-- 右侧边栏页面开始 -->
		<div class="col-sm-4 col-xs-12"
			style="border-left: 1px solid #eee; padding-left: 10px;">

			<div class="col-sm-10" style="height: 50px;"></div>

			<div class="col-sm-11   no-padding">
				<div id="topic-tilte-container" class="col-sm-12">
					<h1><%=title%></h1>
				</div>


				<div id="topic-summary-container" class="col-sm-12">
					<h4>资源简介:</h4>
					<div id="topic-summary-text">
						<%
							if (CollectionUtils.isEmpty(topic.sumaries)) {
						%>
						还没人添加过，快来添加一个吧！
						<%
							} else {
								for (TopicSumary sumary : topic.sumaries) {
						%>
						<div>
							<%=sumary.sumary%>
						</div>
						<%
							}

							}
						%>
					</div>
					<div class="col-sm-12">
						<a class="" style="float: right;" href="#">[ 添加简介]</a>
					</div>
				</div>

				<div id="topic-pic-container" class="col-sm-12">
					<h4>相关图片:</h4>
					<div id="topic-pic-pics" class="col-sm-12">
						<%
							if (CollectionUtils.isEmpty(topic.pics)) {
						%>
						还没人添加过，快来添加一个吧！
						<%
							} else {
								for (TopicPic pic : topic.pics) {
						%>
						<div class="col-sm-6">
							<img class="img-responsive" alt="" src="<%=pic.picUrl%>">
						</div>
						<%
							}

							}
						%>


					</div>
					<div class="col-sm-12">
						<a class="" style="float: right;" href="#">[ 添加图片]</a>
					</div>
				</div>

			</div>



			<div id="discuss-container" class="  col-sm-12">
				<jsp:include page="../part/discuss/all-discuss.jsp">
					<jsp:param name="sourceBoardName" value="首页" />
				</jsp:include>
			</div>


		</div>
		<!-- 右侧边栏页面结束 -->

	</div>
	<!--  主页面结束-->

	<jsp:include page="../part/foot.jsp" />

	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
</body>

</html>
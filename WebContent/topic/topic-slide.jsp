<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.entity.TopicPic"%>
<%@page import="com.tgy.entity.TopicSumary"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.entity.Topic"%>
<%@page import="com.tgy.service.TopicService"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%
	String tagName = request.getParameter("tagName");
	if(StringUtils.isBlank(tagName)){
		return;
	}
%>

<!-- topic开始 -->
<input id="topicName" type="hidden" value="<%=tagName%>">
<div class="col-sm-12  " style="padding-left: 10px;">

	<div class="col-sm-10" style="height: 20px;"></div>

	<div class="col-sm-11   no-padding">
		<div id="topic-tilte-container" class="col-sm-12">
			<h1><%=tagName%></h1>
		</div>


		<div id="topic-summary-container" class="col-sm-12">
			<h3>资源信息:</h3>
			<div id="topic-summary-text" class="col-sm-12">
				<%
					TopicService ts = new TopicService();

					Topic topic = ts.byTitle(tagName);
					if (topic == null) {
						topic = new Topic();
					}
					if (CollectionUtils.isEmpty(topic.sumaries)) {
				%>
					<a class="topic-sumary-show-btn" style=" " href="javascript:void(0)">[ 添加简介]</a>
				<%
					} else {
						for (TopicSumary sumary : topic.sumaries) {
				%>
				<div class="  col-sm-12 no-padding" style="margin-top: 10px;">
					<div class="  col-sm-12 no-padding"><%=sumary.sumary%> </div> 
					<div class="pull-right  " style="color: #aaa;" >-- <%=sumary.createrUserName %> </div> 
				</div>
				<%
					}
		}
				%>
			</div>
			<div class="col-sm-12">
				<a class="topic-sumary-show-btn" style="float: right;" href="javascript:void(0)">[ 添加简介]</a>
			</div>
		</div>

		<div id="topic-pic-container" class="col-sm-12">
			<h3>相关图片:</h3>
			<div id="topic-pic-pics" class="col-sm-12" >
				<%
					if (CollectionUtils.isEmpty(topic.pics)) {
				%>
				<a class="topic-pic-show-btn" style=" " href="javascript:void(0)">[ 添加图片]</a>
				<%
					} else {
						for (TopicPic pic : topic.pics) {
				%>
				<div class="col-sm-11" style="margin-top: 10px;">
					<img class="img-responsive" alt="" src="<%=pic.picUrl%>">
					<div class="pull-right  " style="color: #aaa;" >-- <%=pic.createrUserName %> </div> 
				</div>
				<%
					}

					}
				%>

			</div>
			<div class="col-sm-12" >
				<a class="topic-pic-show-btn" style="float: right;" href="javascript:void(0)">[ 添加图片]</a>
			</div>
		</div>
	</div>

</div>
<!-- topic结束 -->
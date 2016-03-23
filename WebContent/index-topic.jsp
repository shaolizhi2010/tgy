<%@page import="com.tgy.service.cache.AppCache"%>
<%@page import="com.tgy.service.PageService"%>
<%@page import="com.tgy.util.ConditionMap"%>
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
	TopicService ts = new TopicService();
	List<Topic> topics = AppCache.levelOneTopics();
	
	int adIndex = 0;
%>

<!DOCTYPE html>
<html>
<head lang="en">
<jsp:include page="part/head-meta-indexAll.jsp" />
<jsp:include page="part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="part/head.jsp" />
	<jsp:include page="part/public-tabs.jsp" />

	<!-- 书签主页面开始 -->
	<div class="container col-sm-12 clearfix "
		style="padding-top: 0px; padding-left: 0px;">

		<!--------  主页面 --------->
		<div id="pageMain" class="col-sm-8 col-xs-12 no-padding"
			style="margin-top: 30px;">

			<%
				for (Topic topic : topics) {
							 
					List<Topic> subTopics = AppCache.subTopics(topic.id.toString()); 
			%>
			<div class="col-sm-11 col-sm-offset-1 no-padding topic-row container " data-topicID="<%=topic.id.toString()%>">

				<div class="row col-sm-12   topic-row-title " >
					<!-- 标题栏 -->
					<a class="statistic-topic"  data-id="<%=topic.id.toString() %>" href="<%=request.getContextPath() %>/share/resource?tagName=<%=topic.title %>" target="_blank"> 
						<span class=" " style="font-weight: bold;">专题 - <%=topic.title %></span>
					</a>
					
					<a class="pull-right pre-create-topic" style="margin-right:10px; "  href="#create-topic-div" title="奖励  <%=FuliDou.createTopicInfoScore%>个 福利豆">[添加 专题 ]</a>
				</div>
					 
					<div class="row col-sm-12 topic-elements no-padding  ">
						<%
							for (int i=0;i<24;i++ ) {
								String topicName = "[添加]";
								String picUrl = "";
								String topicUrl = "#";
								String topicID = "";
								if(subTopics.size()>i){
									topicName = subTopics.get(i).title;
									topicUrl = request.getContextPath() + "/share/resource?tagName="+topicName;
									topicID = subTopics.get(i).id.toString();
									%>
									<div class="col-sm-3 col-xs-6 topic-element" >
										<a class="topic-element-tilte statistic-topic"  data-id="<%=topicID %>" href="<%=topicUrl%>" target="_blank" ><span class="hoverAble-red"><%=topicName %></span></a>
									</div>									
									<%
								}else{
									%>
									<div class="col-sm-3 col-xs-6 topic-element pre-create-topic" >
										<a class="topic-element-tilte" href="#create-topic-div" title="奖励  <%=FuliDou.createTopicInfoScore%>个 福利豆" ><span class="hoverAble-red"><%=topicName %></span></a>
									</div>									
									<%
								}
						%>

						<%
							}// end for subTopics
						%>
					</div>
					
				<div class="row col-sm-12   topic-row-title " >
					<a class="pull-right " style="margin-right:10px; "  href="<%=request.getContextPath() %>/topic/topic-all.jsp?parentTopicID=<%=topic.id.toString()%>"  >[ >>查看全部 <%=topic.title %>]</a>
				</div>
				
				
			</div>
			<%
								
			%>
			
			<!-- ad -->
			<div class="col-sm-11 col-sm-offset-1 no-padding  "
				style="margin-top: 60px; margin-bottom: 60px; ">
				 <jsp:include page="part/ad/ad-autosize.jsp" />
			</div>
			
			<%
				}// end for topics
			%>
			<div id="create-topic-div" class="col-sm-12  "
				style="padding: 30px;">
				<h2>添加一个专题</h2>
				<input type="hidden" id = "parentTopicID" value="" >
				<div class="col-md-10" style="padding-left: 0px; margin-top: 10px;">
					<input id="topicName" class=" form-control enterInput"
						style="border-radius: 5px; border: 1px solid #ddd; height: 40px;"
						placeholder="专题名称" type="text" value="" />
				</div>
				<div class="col-md-10" style="padding-left: 0px; margin-top: 10px;">
					<input class="form-control enterInput" id="topic-sumary-textarea"
						style="border-radius: 5px; border: 1px solid #ddd; height: 40px;"
						placeholder="专题简介" type="text" value="" />
				</div>
				<div class="col-md-10" style="padding-left: 0px; margin-top: 10px;">
					<input class="form-control enterInput" id="topic-pic-textarea"
						style="border-radius: 5px; border: 1px solid #ddd; height: 40px;"
						placeholder="专题图片" type="text" value="" />
				</div>
				<div class="col-sm-12   " style="margin-top: 10px;"></div>
				<div class="col-sm-10  " style="padding-left: 0px;">
					<input id="topic-create-submit-btn" class="btn btn-primary col-sm-2"
						style="height: 40px; border-radius: 5px;" type="button" value="发布"
						title="奖励  <%=FuliDou.createTopicInfoScore%>个 福利豆"> <span class="col-md-4"
						style="padding-left: 20px; line-height: 40px;"> (添加专题 奖励 <%=FuliDou.createTopicInfoScore%>
						福利豆)
					</span>
				</div>
			</div>
		</div>
		<!--------  主页面 end  --->

		<!-- 右侧边栏开始 -->
		<div class="col-sm-4 col-xs-12"
			style="border-left: 1px solid #eee; padding-left: 10px;">
			
			<div class="col-sm-12   " style="margin-top: 40px;"></div>

			<!-- ad -->
			<%
			 if(request.getRequestURL().indexOf("localhost") <0){
			 %>
			
			<div class="col-sm-10 col-sm-offset-1 " style="margin-top: 20px;  ">
				
				
				<script type="text/javascript">
				    var cpro_id="u2199079";
				    (window["cproStyleApi"] = window["cproStyleApi"] || {})[cpro_id]={at:"3",rsi0:"300",rsi1:"250",pat:"17",tn:"baiduCustNativeAD",rss1:"#FFFFFF",conBW:"1",adp:"1",ptt:"0",titFF:"",titFS:"14",rss2:"#000000",titSU:"0"}
				</script>
				<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script>

			</div>
			
			 <%} %>
			
			<div class="col-sm-12   " style="margin-top: 40px;"></div>
			
			<div id="discuss-container" class="  col-sm-12">
				<jsp:include page="part/discuss/all-discuss.jsp">
					<jsp:param name="sourceBoardName" value="所有专题" />
				</jsp:include>
			</div>
		</div>
		<!-- 右侧边栏结束 -->

	</div>
	<!-- 书签主页面结束-->
<input type="hidden" id="pageID" value="topic">

	<jsp:include page="part/foot.jsp" />

	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/topic.js" defer="defer"></script>
	<script src="<%=request.getContextPath()%>/myjs/user-info.js"
		defer="defer"></script>
	<script src="<%=request.getContextPath()%>/myjs/statistic.js" defer="defer"></script>
</body>

</html>
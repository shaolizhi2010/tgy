<%@page import="com.tgy.service.DiscussService"%>
<%@page import="com.tgy.util.DiscussUtils"%>
<%@page import="com.tgy.util.IpUtils"%>
<%@page import="com.tgy.entity.Discuss"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.Random"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
 
 <%--
 消息组，包括一个主帖，和一堆跟帖，组合在一期。
  --%>
<%
DiscussService ds = new DiscussService();
String primaryDiscussID = request.getParameter("primaryDiscussID");
 
if(StringUtils.isBlank(primaryDiscussID)){
	return;
}
 
 %>
 <div class="hoverAble-discuss col-sm-12 discuss-element-group"  
	style="padding: 10px;border-bottom: 1px solid #eee;"
	data-discussID="<%=primaryDiscussID%>"
	id="<%=primaryDiscussID%>"
	>

	<!-- 每一条主评论 -->
	<jsp:include page="discuss-element.jsp">
		<jsp:param name="discussID" value='<%=primaryDiscussID%>'/>
	</jsp:include>
	<!-- 每一条主贴 end -->
	
	<!-- 子评论列表开始 -->
	<div class="col-sm-11 col-sm-offset-1 no-padding" style="" >
		<jsp:include page="discuss-subElement-group.jsp">
		 	<jsp:param name="primaryDiscussID" value='<%=primaryDiscussID %>'/>
		 </jsp:include>
	</div>

	<!-- 子评论列表end -->
	
</div>

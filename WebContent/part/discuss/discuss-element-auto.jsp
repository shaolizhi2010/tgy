<%@page import="com.tgy.service.DiscussService"%>
<%@page import="com.tgy.util.DiscussUtils"%>
<%@page import="com.tgy.util.IpUtils"%>
<%@page import="com.tgy.entity.Discuss"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.Random"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
 
 <%--
 自动回复的帖子
 如帖子内容是 【权利的游戏】
回复帖子内容为 【www.webhezi.com/pan/权利的游戏】
  --%>
 
 <%

 String keyword = request.getParameter("keyword");
  
 %>
 <!-- 每一条帖子，可以是主贴，或回帖 -->
<div class="col-sm-12 container hoverAble-discuss2 discuss-element" style="padding: 5px;"
	>
	<!-- 评论头像  -->
	<a href="#" target="_blank" class="col-sm-1 hidden-xs no-padding " style=" " >
		<jsp:include page="discuss-user-head-img.jsp">
	    	<jsp:param name="radNum" value="1"/>
	    	<jsp:param name="headUrl" value='/images/ava/ava41.png'/>
	    	<jsp:param name="size" value='40'/>
	    </jsp:include>
	</a>
	
	<!-- 评论 内容 -->
	<div class=" col-sm-11 col-xs-9 no-padding discuss-element-msg" 
		style="  "
		data-fromUsername="<%=U.shortString("百度盘搜索", 20)%>"
		> 
		<!-- 主评论用户名  -->
		<div class="col-sm-12 no-padding " style=" ">
			<a href="#" 
				class="discuss-username no-padding"
				style="display: block;padding-left: 10px;float: left;"
				target="_blank">
			<%= U.shortString("百度盘搜索", 20) %></a>
			
			
		</div>
		<!-- 文字 -->
		<div style="" class=" col-sm-12 no-padding">
			<p class="discuss-content" style='font-size: 13px'>
				<%=U.addLinkForMessage( "http://www.webhezi.com/pan/"+keyword)  %>  
			</p>					
		</div>
	</div>
</div>

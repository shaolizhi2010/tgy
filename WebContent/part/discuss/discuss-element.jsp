<%@page import="com.tgy.service.DiscussService"%>
<%@page import="com.tgy.util.DiscussUtils"%>
<%@page import="com.tgy.util.IpUtils"%>
<%@page import="com.tgy.entity.Discuss"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.Random"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
 
 <%--
 一条帖子，可以是一个主帖，也可以是一个回帖
  --%>
 
 <%
 //取 discuss对象
//try get by attr
 Object o = request.getAttribute("discuss");
 Discuss d = null;
 if(o!=null && o instanceof Discuss){
	 d = (Discuss)o;
 }
 //if null,try get by id, may call by js template
 if(d==null){
	 String discussID = request.getParameter("discussID");
	 if(StringUtils.isNotBlank(discussID)){
		 DiscussService ds = new DiscussService();
		 d = ds.byID(discussID);
	 }
 }
 
 if(d == null){ //no discuss
	 return;
 }
 
  
 String primaryDiscussID = d.isPrimary?d.id.toString():d.primaryDiscussID; 

 //初始化 discuss 和用户数据
String fromUserName = DiscussUtils.fromUserName(d) ;
String toUserName = DiscussUtils.toUserName(d) ;

String fromUserHeadUrl = "";
String fromUserUrl = "#";
String fromUserID = "";

if(d.fromUser!= null && d.fromUser.id!=null){
	fromUserHeadUrl = d.fromUser.headImgUrl;
	fromUserID = d.fromUser.id.toString();
	fromUserUrl = request.getContextPath()+"/u/"+fromUserID;
}

  
 %>
 <!-- 每一条帖子，可以是主贴，或回帖 -->
<div class="col-sm-12 container hoverAble-discuss2 discuss-element" style="padding: 5px;"
	id="<%=d.id.toString()%>">
	<!-- 评论头像  -->
	<a href="<%=fromUserUrl%>" target="_blank" class="col-sm-1 col-xs-3 no-padding" style=" " >
		<jsp:include page="discuss-user-head-img.jsp">
	    	<jsp:param name="radNum" value="<%=IpUtils.lastPartIp(d.fromIP)/2+1  %>"/>
	    	<jsp:param name="headUrl" value='<%=d.fromUser!=null?d.fromUser.headImgUrl:"" %>'/>
	    	<jsp:param name="size" value='<%=d.isPrimary?40:30 %>'/>
	    </jsp:include>
	</a>
	
	<!-- 评论 内容 -->
	<div class=" col-sm-11 col-xs-9 no-padding discuss-element-msg" 
		style="  "
		data-discussID="<%=d.id.toString()%>"
		data-fromUsername="<%=U.shortString(fromUserName, 20)%>"
		> 
		<!-- 主评论用户名  -->
		<div class="col-sm-12 no-padding " style=" ">
			<a href="<%=fromUserUrl%>" 
				class="discuss-username no-padding"
				style="display: block;padding-left: 10px;float: left;"
				target="_blank">
			<%= U.shortString(fromUserName, 20) %></a>
			
			<a class="discuss-reply-show-btn"  href="#<%=primaryDiscussID %>" style="font-size: 10px; float: right;">
				  [回复] 
			</a>
			<!-- 
			<span class="discuss-reply-show-date" style=""><%= U.dateTimeShort( d.createDate)  %></span>
			 -->
		</div>
		<!-- 文字 -->
		<div style="" class=" col-sm-12 no-padding">
			<p class="discuss-content" style='font-size: <%=d.isPrimary?"15px":"13px" %>;'>
				
				<%if(d.isReply && d.replyToDiscuss!=null && d.replyToDiscuss.isReply) {//回复非主贴，加@
				%>
				<span class="discuss-reply-at" title="<%=toUserName %>">
				@<%=toUserName %>:
				<% }%></span>
				
				<%=U.addLinkForMessage( d.message)  %>  
				<%
					if(StringUtils.isNotBlank(d.sourceBoardName)){
						%>
						 <span style="color: #ccc;"> (来自 <%=U.shortString(d.sourceBoardName, 6)  %>) </span>
						<%
					}
				%>
			</p>					
		</div>
	</div>
</div>

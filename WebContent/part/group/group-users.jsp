<%@page import="com.tgy.util.ConditionMap"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.entity.group.GroupUser"%>
<%@page import="com.tgy.service.group.GroupUserService"%>
<%@page import="com.tgy.service.group.InterestGroupService"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.GravatarUtil"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="group-data.jsp" %>  

<%
GroupUserService gus = new GroupUserService();
UserService us = new UserService();
List<GroupUser>  users = gus.list(new ConditionMap().add("groupID", groupID), null, 50); 
%>

<div  class="container col-sm-12 clearfix no-padding sub-page-with-title">
	<div class="sub-page-title">
		<span>组成员(<%=users.size() %>)</span>
	</div>
	<div class=" col-sm-12 sub-page-body">
		<%
		
		for(GroupUser gu : users){
			String userName = gu.user.name;
			String userID = "";
			if(gu.user.id!=null){
				userID = gu.user.id.toString();
			}
			if(StringUtils.isBlank(userName)){
				userID = gu.getUserID();
				User u = us.getByID(userID);
				userName = u.name;
			}
			%>
			
			<div>
				<a target="_blank" href="<%=request.getContextPath() %>/u/<%=userID %>" class="col-sm-10 no-padding"  > 
				 	<span class="glyphicon glyphicon-user" style="font-size: 15px;color: #e5e5e5;"></span>
				 	<span style="color: #1155cc;"> <%=userName%></span> <!-- #ff076e #1155cc; 0000cc-->
				</a> 
				<span class="col-sm-2" style="color: #1155cc;">  </span>
			</div>
			<%
		}
		%>
	</div>
 
</div>

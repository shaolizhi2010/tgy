<%@page import="com.tgy.entity.Discuss"%>
<%@page import="com.tgy.service.DiscussService"%>
<%@page import="java.util.Collections"%>
<%@page import="com.tgy.util.ConditionMap"%>
<%@page import="com.tgy.service.group.GroupDiscussService"%>
<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.entity.GroupDiscuss"%>
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

<%@include file="login-user-data.jsp"  %>  

<%
	UserService uService = new UserService(); 
	DiscussService discussService = new DiscussService();
	List<Discuss> discusses = discussService.list(new ConditionMap().add("targetIsSearchPage", "true") , "-createDate", 10);
	Collections.reverse( discusses); 
%>

	<div class=" container col-sm-12 sub-page-with-title"  >
		<div class="sub-page-title">
			<span>... </span>
		</div>
		<div class="col-sm-12 sub-page-body no-padding" style="padding:0px;padding-top:10px; background-color: #fff;">
		<%
			for(Discuss d : discusses){
				String userName = "游客";
			String headUrl = "";
			String userUrl = "";
			//String userID = "";
			if(StringUtils.isBlank(d.userID)){
				if(StringUtils.isNotBlank(d.soucrceIP)){
					userName = d.soucrceIP;
				}
			}
			else{
				User sourceUser = uService.getByID(d.userID);
				if(sourceUser!=null){
					userName = sourceUser.name;
					headUrl = sourceUser.headImgUrl;
					userUrl = request.getContextPath()+"/u/"+d.userID;
				}
			}
		%>
		<div class="col-sm-12 no-padding" style=" ">
			<a href="<%=userUrl%>" class="  no-padding" style="display: block;  " >
				<%	
				if(StringUtils.isBlank(headUrl)){
			%>
				<span class="glyphicon glyphicon-user" style="font-size: 15px;color: #ddd;float: left;"></span>
			<%
				} else{
				%>
				<img height="15" width="15" alt="" src="<%=headUrl %>">
				<%
				}
				%>
				 <span style="color: #666;font-size: 10px;"><%= U.shortString(userName, 6) %></span>   
			</a>
			<p class=" col-sm-12 no-padding" style=" background-color:#f2f2f2;  padding:   10px;padding-left:  15px;  font-size: 12px;font-weight: normal;word-wrap: break-word; word-break: break-all;">
					<%=U.addLinkForMessage( d.message)  %>  
			</p>
		</div>
		<%
			}
		%>
		 <div class="col-sm-12  no-padding " style="  ">
				<div  style="margin-top: 5px;" >
		         		<textarea   id="createDiscussMessage" data-func-name="createDiscussForSearchFunction" class="form-control enterInput hover-focus" rows="2" placeholder=""></textarea> 
	         	</div>
	         	<div  style="margin-top: 4px;" >
	              <button onclick="createDiscussForSearchFunction()" id="createFolder-ok" type="button"
	                      class="btn btn-default pull-right" style="font-size:10px; height: 30px; " >发送
	              </button>
	         	</div>
		</div> 	
 	</div>
</div>
 
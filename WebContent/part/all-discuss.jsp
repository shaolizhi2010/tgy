<%@page import="com.tgy.util.IpUtils"%>
<%@page import="java.util.Random"%>
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
String sourceName  = request.getParameter("sourceName");
if(StringUtils.isBlank(sourceName)){
	sourceName = "大家爱吐槽";
}

%>
<input type="hidden" id="sourceName" value="<%=sourceName%>">
<%
	UserService uService = new UserService(); 
	DiscussService discussService = new DiscussService();
	List<Discuss> discusses = discussService.list(new ConditionMap().add("targetIsAllSite", "true") , "-createDate", 10);
	Collections.reverse( discusses); 
%>

	<div class=" container col-sm-12 sub-page-with-title"  >
		<div class="sub-page-title">
			<span>吐槽 - <%=sourceName %></span>
		</div>
		<div class="col-sm-12 sub-page-body no-padding" style="padding:0px;padding-top:10px; background-color: #fff;">
		<%
			//Random rad = new Random();
			for(Discuss d : discusses){
				String userName = d.sourceName ;
				String headUrl = "";
				String userUrl = "#";
				String userID = "";
				int shortIp = 1;//123.123.123.11 ip最后一段
				String splitToken = ":";
				if(StringUtils.isBlank(d.userID)){
					shortIp = IpUtils.lastPartIp(d.soucrceIP);
					userName +=shortIp;
				}
				else{
					User sourceUser = uService.getByID(d.userID);
					if(sourceUser!=null){
						userName = sourceUser.name;
						headUrl = sourceUser.headImgUrl;
						userUrl = request.getContextPath()+"/u/"+d.userID;
						userID = sourceUser.id.toString();
					}
				}
		%> 
		<div class="col-sm-12 no-padding hoverAble-discuss" style="padding:10px;padding-bottom:0px; border-top: 1px solid #fafafa;border-bottom: 1px solid #fafafa;">
			
				<a href="<%=userUrl%>" class="col-sm-1  no-padding " style="min-width:50px ;padding-right:10px; display: block; float: left; " >
				<%	
				
				if(StringUtils.isBlank(headUrl)){
					
				%>
					<img height="40" width="40" alt="" src="<%=request.getContextPath() %>/images/ava/ava<%=shortIp/2+1%>.png" style="-moz-border-radius: 20px;-webkit-border-radius: 20px;border-radius: 20px;" >
				<%
				} else{ 
				%>
					<img height="40" width="40" alt="" src="<%=headUrl %>" style="-moz-border-radius: 20px;-webkit-border-radius: 20px;border-radius: 20px;" >
				<%
				}
				%>
				</a>
				
				<div class="col-sm-9 no-padding" style="float: left;"> 
					<a href="<%=userUrl%>" 
						class="discuss-username"
						style="display: block;padding-left: 10px; "
						target="_blank">
					<%= U.shortString(userName, 20) %></a>
					
					<p class="" style="color:#333;  padding-left:10px; padding-top:5px;  font-size: 13px;font-weight: normal;word-wrap: break-word; word-break: break-all;">
						<%=U.addLinkForMessage( d.message)  %>  
						<%
							if(StringUtils.isNotBlank(d.sourceName)){
								%>
								<span style="color: #ccc;"> (来自 <%=U.shortString(d.sourceName, 6)  %>) </span>
								<%
							}
						%>
						
						
					</p>
				</div>
		</div>
		<%
			}
		%>
		
		<!-- 翻页 
		<div class="col-sm-12">
		<nav>
		  <ul class="pagination">
		    <li>
		      <a href="#" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    <%
		    for(int i = 1;i<11;i++){
		    	String selectedStyle="";
 
		   		%>
		   		 <li><a style="<%=selectedStyle %>" href="#"><%=i %></a></li>
		   		<% 	
		    }
		    %>
		   
		    <li>
		      <a href="#" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span> 
		      </a>
		    </li>
		  </ul>
		</nav>
	</div>
	-->
		<div class="col-sm-12" >
			 <div class="col-sm-12" style=" padding: 10px;background-color: #f8f8f8; ">
				<div  style=" ">
		         		<textarea  style="border:1px solid #ccc;" id="createDiscussMessage" 
		         			data-func-name="createDiscussForAllFunction" 
		         			class="form-control enterInput hover-focus" rows="4" 
		         			placeholder="快到碗里来说两句"></textarea> 

	         	</div>
				<div  style="margin-top: 5px; " >
	         	    <a href="javascript:void(0)">
	         	    <span onclick="createDiscussForAllFunction()" id="createFolder-ok" type="button"
                      class="pull-right" style="width:45px;height:35px;line-height:35px;text-align:center;border-radius:5px; background-color: #4cbb1f;color: #fff;font-size: 13px;font-weight: bold;">
                      		发送
                      </span>
                     </a>
              	</div>
			</div> 	
 		</div>
 	</div>
</div>
 
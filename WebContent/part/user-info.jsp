<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.GravatarUtil"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="user-data.jsp" %>

<%
String showUserNameShort = showUserName;
if (showUserNameShort != null && showUserNameShort.length() > 4) {
	showUserNameShort = showUserNameShort.substring(0, 4)+"..";
}
%>

<div  class="container col-sm-12 clearfix no-padding sub-page-with-title">
	<div class="sub-page-title">
		<span>创建人</span>
	</div>
	<div class="sub-page-body">
		<div>
			<div class="col-sm-4" style="padding-top: 5px;">
				<%
					if(StringUtils.isBlank(showUserHeadImgUrl)){
						%>
						<span class="glyphicon glyphicon-user" style="font-size: 35px;color: #f5f5f5;"></span>
						<%
					}
					else{
						%>
						<img style="height: 35px;width: 35px;" alt="已登录" src="<%=showUserHeadImgUrl%>">
						<%
					}
				%> 
				
			</div>
			<div class="col-sm-8 hoverAble" style="padding: 10px;">
				<a class="col-sm-12" href="<%=request.getContextPath() %>/u/<%=showUserID %>" style="padding-left:15px;;color:#222 ;font-weight:bold;font-size: 18px;" title="<%=showUserName%>"><%=showUserNameShort%></a>
			</div>
		</div>
		<div class="col-sm-12 no-padding" style="margin-top: 10px;">
			<div class="col-sm-4 hoverAble" style="border: 1px solid #eee;">
				<span><%= showUser!=null?showUser.showTimes:"0" %></span><br>
				<span style="font-size: 10px;">访问</span>
			</div>
			<div class="col-sm-4 hoverAble" style="border: 1px solid #eee;">
				<span>1</span><br>
				<span style="font-size: 10px;">粉丝</span>
			</div>
			<div class="col-sm-4 hoverAble" style="border: 1px solid #eee;">
				<span>1</span><br>
				<span style="font-size: 10px;">关注</span>
			</div>
		</div>
	 
		<div class="col-sm-12" style="padding-top: 10px;padding-bottom: 10px;">
			<span style="font-size: 12px;">这家伙很懒，什么都没有留下</span>
		</div>
	</div>

	
	
 
</div>

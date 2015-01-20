<%@page import="java.util.Random"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.GravatarUtil"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="bookmark-data.jsp" %>
<%@include file="show-user-data.jsp" %>

<%
String showUserNameShort = showUserName;
if (showUserNameShort != null && showUserNameShort.length() > 4) {
	showUserNameShort = showUserNameShort.substring(0, 4)+"..";
}
long showTimes =0;
if(showUser!=null){
	showTimes = showUser.showTimes;
}
%>
<div  class="container col-sm-12 clearfix no-padding sub-page-with-title">
	<div class="sub-page-title">
		<span></span>
	</div>
	<div class="sub-page-body" style="background-color: #fff;">
		<div>
			<div class="col-sm-6" style="padding-top: 5px;">
				<%
					if(StringUtils.isBlank(showUserHeadImgUrl)){
						%>
						<img class="img-responsive"  style="min-width: 80px;min-height: 80px; border-radius:80px;" alt="已登录" src="<%=request.getContextPath()%>/images/ava/ava<%=new Random().nextInt(120)+1%>.png">
						<br><a href="<%=request.getContextPath()%>/private/personal-setting.jsp">[设置头像]</a>
						<%
					}
					else{
						%>
						<img class="img-responsive"  style="min-width: 80px;min-height: 80px;border-radius:80px; " alt="已登录" src="<%=showUserHeadImgUrl%>">
						<%
					}
				%> 
				
			</div>
			<div class="col-sm-6  no-padding" style="  ">
				<a class="col-sm-12 no-padding" href="<%=request.getContextPath() %>/u/<%=showUserID %>" style=" ;color:#222 ;font-weight:bold;font-size: 22px;" title="<%=showUserName%>"><%=showUserNameShort%></a>
				<a onclick="follow('<%=showUserID %>')" style="width:80px; height: 30px;line-height: 10px;background-color: #0084e8;color: #fff;font-size: 12px;font-weight: bold;margin-top: 10px;" type="button" class="btn " > <span class="glyphicon glyphicon-plus"></span> 关注</a>
				 
			</div>
		</div>
	 
		 <div class="col-sm-12 no-padding" style="margin-top: 10px;">
			<div class="col-sm-3 hoverAble" style="border-left: 1px solid #f5f5f5;border-right: 1px solid #f5f5f5;">
				<span style="font-size: 14px;font-weight: bold;color: #999;font-family: Tahoma;"><%=pageCount %></span><br>
				<span style="font-size: 10px;color: #333;font-family: Tahoma;">收藏</span>
			</div>
			<div class="col-sm-3 hoverAble" style="border-left: 1px solid #f5f5f5;border-right: 1px solid #f5f5f5;">
				<span style="font-size: 14px;font-weight: bold;color: #999;font-family: Tahoma;"><%=showUser.showTimes %></span><br>
				<span style="font-size: 10px;color: #333;font-family: Tahoma;">访问</span>
			</div>
			<div class="col-sm-3 hoverAble" style="border-left: 1px solid #f5f5f5;border-right: 1px solid #f5f5f5;">
				<span style="font-size: 14px;font-weight: bold;color: #999;font-family: Tahoma;"><%=showUser.fansCount %></span><br>
				<span style="font-size: 10px;color: #333;font-family: Tahoma;">粉丝</span>
			</div>
			<div class="col-sm-3 hoverAble" style="border-left: 1px solid #f5f5f5;border-right: 1px solid #f5f5f5;">
				<span style="font-size: 14px;font-weight: bold;color: #999;font-family: Tahoma;"><%=showUser.followsCount %></span><br>
				<span style="font-size: 10px;color: #333;font-family: Tahoma;">关注</span>
			</div>
		</div>
	 
		<div class="col-sm-12" style="padding-top: 10px;padding-bottom: 10px;">
			<span class="user-info-publicMessage"><%= showUser!=null&&StringUtils.isNotBlank(showUser.publicMessage)? showUser.publicMessage:"TA 很懒，什么都没有留下 <a href='"+request.getContextPath()+"/private/personal-setting.jsp'>[编辑]</a>" %></span>
		</div>
	</div>
</div>

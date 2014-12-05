<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.entity.User"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="user-data.jsp" %>

<%
String loginUserNameShow =   "";
if(StringUtils.isNotBlank(loginUserName)  ){

	if(loginUserName.length()>8){
		loginUserNameShow = loginUserName.substring(0,8)+"..";
	}
	else{
		loginUserNameShow = loginUserName;
	}
}
boolean isTempUser = false;
if(loginUser!=null){
	isTempUser = loginUser.isTemp;
}

%>

<!-- 菜单 开始 -->
<div id="menu" class="container col-sm-12 "
	style="padding: 3px; background-color: #f5f5f5;">
	<div id="logo" class="col-sm-4 ">
		<a style="text-decoration: none;"
			 href="/"><span style="color: #444;font-size: 26px;font-weight:bold;">网址盒子</span> </a> 
		<span
			style="font-size: 14px; color: #666;">好网址? 快到盒子里来！</span>  
	</div>

<div class="col-sm-4 "  style="text-align: center;">
	<!-- 
	<span style="font-size: 12px;text-align: center;background-color: orange ;
	color: #fff;padding: 5px;font-weight: 400;">网站内测中 欢迎试用！</span> 
	 -->
</div>

	<%
		if (loginFlag) {
			if(isTempUser){
				%>
				
					<div class="col-sm-3 col-sm-offset-1  "      
		style="font-size: 14px; color: #555; padding-top: 2px; ">
		<span style="font-size: 12px;color: #555; ">欢迎您:</span>  <a href="<%=request.getContextPath() %>/u/<%=loginUserID%>" 
			style="margin: 3px;font-size: 12px;color: #555;font-weight: bold;" 
			title="<%=loginUserName%>">  
			 <%=loginUserNameShow%> 
		</a>
		<span style="font-size:12px;color: #555;">,建议您</span> 
		<a href="#" ng-click="preEditTempUserFunction()" style="margin: 3px;font-size: 12px;color: #555;font-weight: bold;"> [免费注册为正式用户] </a>
		<a href="#" ng-click="logoutFunction()" style="margin: 3px;font-size: 12px;color: #555;font-weight: bold;"> [退出] </a>
	</div>
				
				<%
			}
			else{
				%>
				
					<div class="col-sm-3  col-sm-offset-1  "     
		style="font-size: 14px; color: #555; padding-top: 12px; ">
		<span style="font-size: 12px;color: #555;font-weight: bold;">欢迎您:</span>  <a href="<%=request.getContextPath() %>/u/<%=loginUserID%>" 
			style="margin: 3px;font-size: 12px;color: #555;font-weight: bold;" 
			title="<%=loginUserName%>">  
			 <%=loginUserNameShow%> 
		</a> 
		,
		<a href="#" ng-click="preEditUserFunction()" style="margin: 3px;font-size: 12px;color: #555;font-weight: bold;"> [编辑] </a>
		<a href="#" ng-click="preLoginFunction()" style="margin: 3px;font-size: 12px;color: #555;font-weight: bold;"> [切换用户] </a>
		<a href="#" ng-click="logoutFunction()" style="margin: 3px;font-size: 12px;color: #555;font-weight: bold;"> [退出] </a>
	</div>
				
				<%
			}
			
	%> 

	<%
		} else {
	%>
	<div class="col-sm-3  col-sm-offset-1"
		style="font-size: 12px; color: #555;  ">

		<span class="glyphicon glyphicon-envelope"
			style="padding-top: 12px;font-size: 12px; color: #555;"></span> 
		<span
			style="padding-top: 12px;font-size: 12px; color: #555;"> 欢迎 </span>
		 <a
			ng-click="preLoginFunction()" href="#" style="padding-top: 12px;font-weight: bold;">登录</a>
			
		<span style="padding-top: 12px;font-size: 12px; color: #555;">或</span> <a
			ng-click="preAddUserFunction()" href="#" style="font-weight: bold;">注册</a>
		<a style="padding-top: 8px;float:right;display: block; " href="https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101171952&redirect_uri=http://www.webhezi.com/qqcallback.jsp&state=test">
			<img alt="使用QQ帐号登录" src="<%=request.getContextPath() %>/images/qqlogin.png">
		</a>
	</div>
	<%
		}
	%>




	<!-- 
	<div class="col-sm-2">
		<div class="input-group">
			<input   id="search-input-baidu" placeholder="百度一下"
				ng-keyup="$event.keyCode == 13 ? searchBaidu() : null" type="text"
				class="form-control">  <span  ng-click="searchBaidu( )" class="input-group-addon glyphicon glyphicon-search" >  </span>
		</div>
	</div>
	<div class="col-sm-2">
		<div class="input-group">
			<input id="search-input-google" placeholder="Google一下"
				ng-keyup="$event.keyCode == 13 ? searchGoogle() : null" type="text"
				class="form-control"> <span ng-click="searchGoogle( )"
				class="input-group-addon glyphicon glyphicon-search"></span>
		</div>
	</div>
	 -->

	<!-- 
	<div id="searchSite-div" class="col-sm-offset-4 col-sm-3" style="padding:3px;   ">
		<div class="input-group">
			<input id="searchSite-input" placeholder="网站类别"
				ng-keyup="$event.keyCode == 13 ? searchSite() : null" type="text"
				class="form-control"> <span ng-click="searchSite()"
				class="input-group-addon ">找网站</span>
			 
		</div>
	</div>
 -->

	<!-- 
	<div class="col-sm-1" style="padding:3px;  ">
		<a href="#" class="btn btn-default  dropdown-toggle"
			data-toggle="dropdown">
			<span>菜单</span><span class="caret"></span>
		</a>
		<ul class="dropdown-menu" role="menu">
			<li><a ng-click="preLoginFunction()" href="#">登录</a></li>
			<li><a ng-click="preAddUserFunction()" href="#">注册</a></li>
			<li class="divider"></li>
		</ul>
	</div>
	 -->
	<!-- 
	<div class="col-sm-2">

		<button ng-click="preLoginFunction( )"
			class="btn btn-default col-sm-4">登录</button>
		<button ng-click="preAddUserFunction( )"
			class="btn btn-default col-sm-4">注册</button>
	</div>
	
	 -->
</div>

<!-- 菜单 结束 -->

<!-- 分割线  -->
<hr class="col-sm-12"
	style="border-top: 2px solid #eee; padding: 0px; margin: 0px;">
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.entity.User"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="show-user-data.jsp" %>
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
String showUserNameShort = showUserName;
if (showUserNameShort != null && showUserNameShort.length() > 8) {
	showUserNameShort = showUserNameShort.substring(0, 8)+"..";
}
%>
<!-- 菜单 开始 -->
<div id="head-menu" class="container col-sm-12 " style="padding-top: 50px;padding-bottom: 50px;">
	<div id="logo" class="col-sm-2 no-padding">
		<a href="/" title="网址收藏 网站分享"><span>网盘盒子</span> </a>
	</div> 
	<div id="god-div" class="col-sm-5 col-xs-0 no-padding hidden-xs "> 
		
		<div id="pan-search-input" class="col-sm-11 col-sm-offset-1 ">
			<div  class="col-sm-9   no-padding" >
				<input id="pan_search_value" class=" form-control hover-focus enterInput"   data-func-name="panSearch"
					value="" style="height: 40px;border-radius:3px;" placeholder="百度网盘搜索" />
			</div>
			<div class="col-sm-3">
				<input class="btn btn-primary col-sm-12" style="height: 40px;border-radius:3px;" onclick="panSearch()" 
					 type="button" value="搜索资源">
			</div>
		</div>


	</div>
 
	<% if (loginFlag) { %>
		<div id= "user-operation-div" class="col-sm-4 col-sm-offset-1   no-padding">
	   		<a id= "user-operation-btn" class="col-sm-8 user-operation-btn  " href="javascript:void(0)" 
				title="">
				<%
					if(StringUtils.isBlank(loginUserHeadImgUrl)){
						%>
						<span class="glyphicon glyphicon-user"></span>
						<%
					}
					else{
						%>
						<img style="height: 20px;width: 20px;" alt="已登录" src="<%=loginUserHeadImgUrl%>">
						<%
					}
				%> 
				
				 <%=loginUserNameShow%> 
				 
				 <span style="font-size: 12px;color: orange;"  >( <%=fuliScore %> 福利豆 )</span>
				  
			</a>
			<a class="col-sm-3 user-operation-btn" href="#" onclick="logoutFunction()" style="font-size: 12px;padding-top: 15px;">[退出]</a>
			<!-- 
			<div id= "user-operation-content" class="col-sm-12 no-padding "  style="">
		 		<a onclick="preLoginFunction()" class="hoverAble2" href="#" style="">切换账号</a>
		 		<a onclick="logoutFunction()" class="hoverAble2" href="#" style="">注销</a>
			</div>
			 -->
		</div>
	<%
		} else {
	%>
	<div class="col-sm-3 col-sm-offset-1  " style="font-size: 12px; color: #555;">
		
		<a style="float:right;display: block;margin-left: 5px; margin-top: 10px;" title="使用QQ登录" href="https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101171952&redirect_uri=http://www.webhezi.com/qqcallback.jsp&state=test">
			<img alt="使用QQ帐号登录" style="height: 35px;;width: 35px;" src="<%=request.getContextPath() %>/images/qq-login.jpg">
		</a>
		 <a class="btn btn-success col-sm-4  " onclick="preLoginFunction()" href="#" style=" font-weight: bold;color:#fff;font-size: 16px;border-radius: 20px;margin-left: 5px;margin-top: 10px;">登录</a>
		  <a class="btn btn-primary col-sm-4  " onclick="preAddUserFunction()" href="#" style="font-weight: bold;color:#fff;font-size: 16px;border-radius: 20px;margin-left: 5px;margin-top: 10px;">注册</a>
		<!-- 
		 <a class=" col-sm-2"   href="#"  >
			<img  class=" " alt="qq登录" style="width: 35px;height: 35px;  " src="<%=request.getContextPath() %>/images/qq-login.jpg">
		</a> -->
	</div>
	<%
		}
	%>
</div>
<!-- 菜单 结束 -->


<!-- 弹出框开始 -->
<jsp:include page="../window/common-window.jsp" />
<!-- 弹出框结束 -->

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
<div id="head-menu" class="container col-sm-12 ">
	<div id="logo" class="col-sm-2 no-padding">
		<a href="/" title="网址收藏 网站分享"><span>网址盒子</span> </a>
	</div> 
	<div id="god-div" class="col-sm-3 col-xs-0 no-padding"> 
		<input id="god-input" class="form-control hover-focus" style="height: 40px;border-radius:10px;"   placeholder="搜 资源、网址、网站名称" />
		<div id= "god-content-div" 
			class="col-sm-12"  
			style="display: none;position:absolute;z-index:1000;background-color:#fff;border: 1px solid #333; ">
		</div> 
		<a id="god_link" href="#" target="_blank" style="display: none;"></a>
	</div>
	<div id="head-sub-menu" class="col-sm-3 container no-padding"> 
		 <a id="head-sub-menu-personal"  class="col-sm-5 col-sm-offset-1  no-padding  " href="<%=request.getContextPath()%>/me"  ><span style="color: #387bb6;">网址收藏</span></a>
		 <a id="head-sub-menu-public" class="col-sm-6    no-padding  " href="<%=request.getContextPath()%>/share"><span style="color: #e85205;" >网址分享</span></a>
		 <!--  
		  <a class="col-sm-3    hoverAble2 " href="<%=request.getContextPath()%>/公用导航"><span  >导航</span></a>
		 -->
	</div>
 
	<% if (loginFlag) { %>
		<div id= "user-operation-div" class="col-sm-2 col-sm-offset-2 no-padding">
	   		<a id= "user-operation-btn" class="col-sm-12  hoverAble2" href="<%=request.getContextPath() %>/u/<%=loginUserID %>" 
				title="<%=loginUserName%>">
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
				 <span class="caret"></span>
			</a>
			<div id= "user-operation-content" class="col-sm-12 no-padding "  style="">
		 		<a onclick="preLoginFunction()" class="hoverAble2" href="#" style="">切换账号</a>
		 		<a onclick="logoutFunction()" class="hoverAble2" href="#" style="">注销</a>
			</div>
		</div>
	<%
		} else {
	%>
	<div class="col-sm-3 col-sm-offset-1" style="font-size: 12px; color: #555;">
		
		<a style="float:right;display: block;margin-left: 5px; " title="使用QQ登录" href="https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101171952&redirect_uri=http://www.webhezi.com/qqcallback.jsp&state=test">
			<img alt="使用QQ帐号登录" style="height: 35px;;width: 35px;" src="<%=request.getContextPath() %>/images/qq-login.jpg">
		</a>
		 <a class="btn btn-success col-sm-4  " onclick="preLoginFunction()" href="#" style=" font-weight: bold;color:#fff;font-size: 16px;border-radius: 20px;margin-left: 5px;">登录</a>
		  <a class="btn btn-primary col-sm-4  " onclick="preAddUserFunction()" href="#" style="font-weight: bold;color:#fff;font-size: 16px;border-radius: 20px;margin-left: 5px;">注册</a>
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

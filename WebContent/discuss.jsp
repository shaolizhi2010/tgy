<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.entity.Discuss"%>
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
<%@include file="part/common.jsp" %>
<%@include file="part/show-user-data.jsp" %>
<%
	UserService uService = new UserService();
	List<Discuss> discusses = U.paramList(request, "discusses") ;
	Link link = U.param(request, "target",Link.class);
	String linkID = "";
	String linkTitle = "";
	if(link!=null){
		linkID = link.id.toString();
		linkTitle = link.title;
	}
%>


<!DOCTYPE html>
<html >
<head lang="en">
<jsp:include page="part/head-meta.jsp" />
<jsp:include page="part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="part/head.jsp" />
	<div class="  col-sm-12" style="margin-top: 10px;"></div>
 
	<!-- 书签主页面开始 -->
	<div class="container col-sm-12 clearfix ">  
		<!-------- 书签主页面 --------->
		<div id="pageMain" class="col-sm-9 no-padding" >
			<div class="  col-sm-12" style="margin-top: 10px;"></div>
		 	<div class=" container col-sm-12 sub-page-with-title"  >
	 			<div class="sub-page-title">
					<span>评论 </span>
				</div>
				<div class="sub-page-body">
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
 				<div class="col-sm-12 no-padding" style="border-bottom: 1px solid #eee;padding-top: 10px;">
 					<a href="<%=userUrl%>" class="col-sm-2 " style="display: block;padding: 10px;" >
 						<%	
 						if(StringUtils.isBlank(showUserHeadImgUrl)){
						%>
							<span class="glyphicon glyphicon-user" style="font-size: 20px;color: #ddd;"></span>
						<%
 						} else{
 						%>
							<img height="15" width="15" alt="" src="<%=headUrl %>">
 						<%
 						}
 						%>
 						 <span style="color: #666;font-size: 10px;"><%=userName%></span>   
 					</a>
 					<blockquote class="col-sm-10 no-padding " style="padding-left: 15px;">
 						<p class=" " style=" padding: 20px; font-size: 14px;font-weight: normal;">
 								<%=d.message %>  
 						</p>
 						<a href="javascript:void(0);" class="col-sm-2 pull-right ajax-reply" style="font-size: 10px;padding-right: 1px;color: #666;text-align: right;"></a>
 					</blockquote>
 				</div>
 				<%
		 			}
	 			%>
				 <div class="col-sm-12   " style="margin-top: 10px;  margin-bottom: 10px;">
			 		<input type="hidden" id="createlinkComment_targetID" value="<%=linkID%>">
			 		<div  style="margin-top: 5px;" >
	            		<textarea   id="createlinkCommentMessage"  class="form-control" rows="5" placeholder=""></textarea>            </div>
	            	<div  style="margin-top: 10px;" >
		                <button onclick="createDiscussFunction()" id="createFolder-ok" type="button"
		                        class="btn btn-primary pull-right">发送
		                </button>
	            	</div>
			 	</div> 	
			 	</div>
		 	</div>

		 	
			
		</div>
		<!--------  书签列表页面 end  --->
		<!-- 显示推荐页面开始 -->
		<div class="col-sm-3" style="  ">
			<div class="  col-sm-12" style="margin-top: 10px;"></div>
			
			<%//如果正在显示某用户
				if(StringUtils.isNotBlank(showUserName)){
					%>
					<jsp:include page="part/user-info.jsp" />
					<div class="  col-sm-12" style="margin-top: 20px;"></div>
					<%
				}
			%>
			<%//如果已经登陆
				if(loginFlag){
					%>
					<jsp:include page="part/page-slide-myHotClicks.jsp" />
					<div class="  col-sm-12" style="margin-top: 20px;"></div>
					<%
				}
			%>
			<jsp:include page="part/folder-slide-follow.jsp" />
		</div>
		<!-- 显示推荐页面结束 -->


	</div>
	<!-- 书签主页面结束-->

	<!-- toolbar -->
	<jsp:include page="part/foot-toolbar.jsp" />

	<!-- hidden var begin -->

	<input type="hidden" id="loginFlag" value="<%=loginFlag %>">
	<input type="hidden" id="userID" value="<%=showUserID %>">
	<input type="hidden" id="contextPath" value="<%=contextPath%>">
 	<input type="hidden" id="edit_pid" value="">
	<input type="hidden" id="edit_name" value="">
	<input type="hidden" id="edit_url" value="">

	<!-- hidden var end -->

	<!-- 弹出框开始 -->
	<jsp:include page="window/window.jsp" />
	<!-- 弹出框结束 -->
	
	<jsp:include page="part/foot.jsp" />
	<jsp:include page="part/importAtFoot.jsp" />
	
	<script src="<%=request.getContextPath()%>/myjs/pageMainApp.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
</body>

</html>
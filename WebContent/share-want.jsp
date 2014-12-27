<%@page import="com.tgy.service.DiscussService"%>
<%@page import="com.tgy.entity.Discuss"%>
<%@page import="com.tgy.service.FolderService"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="part/common.jsp" %>
<%@include file="part/bookmark-data.jsp" %> 
<%@include file="part/user-data.jsp" %>
<%
	UserService uService = new UserService();
	DiscussService disService = new DiscussService();
	List<Discuss> discusses = disService.list(null, null, "share_want", 40, null);
%>

<!DOCTYPE html>
<html>
<head lang="en">
<jsp:include page="part/head-meta.jsp" />
<jsp:include page="part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="part/head.jsp" />
	<div class="  col-sm-12" style="margin-top: 20px;"></div>
 	
 	<!-- 菜单 -->
 	<div class=" col-sm-3 no-padding" >
		<div class=" col-sm-10 col-sm-offset-1 no-padding" >
			<jsp:include page="part/ziyuan.menu.jsp"/>
		</div>
	</div>
	<!-- 主体内容 -->
	<div class=" col-sm-9 no-padding">
		<div class=" container col-sm-12 sub-page-with-title"  >
	 			<div class="sub-page-title">
					<span>共享资源，寻找资源</span>
				</div>
				<div class="sub-page-body">
		 		<%
		 			for(Discuss d : discusses){
		 				String userName = "游客";
						String headUrl = "";
						String userUrl = "";
						//String userID = "";
						if(StringUtils.isBlank(d.sourceID)){
							if(StringUtils.isNotBlank(d.soucrceIP)){
								userName = d.soucrceIP;
							}
						}
						else{
							User sourceUser = uService.getByID(d.sourceID);
							if(sourceUser!=null){
								userName = sourceUser.name;
								headUrl = sourceUser.headImgUrl;
								userUrl = request.getContextPath()+"/u/"+d.sourceID;
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
 								<%=U.addLinkForMessage(d.message)  %>  
 						</p>
 						<a href="javascript:void(0);" class="col-sm-2 pull-right ajax-reply" style="font-size: 10px;padding-right: 1px;color: #666;text-align: right;"></a>
 					</blockquote>
 				</div>
 				<%
		 			}
	 			%>
				 <div class="col-sm-12" style="padding-top:50px; ;margin-top: 20px;  margin-bottom: 20px;background-color: #f3f3f3;">
				 	<input type="hidden" id="discussType" value="share_want">
			 		<span style="font-size:12px;color: #a2a2a2;">内容</span> 
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
	
	<input type="hidden" id="loginFlag" value="<%=loginFlag %>">
	<input type="hidden" id="userID" value="<%=showUserID %>">
	<input type="hidden" id="contextPath" value="<%=contextPath%>">
	<input type="hidden" id="fid" value="<%=showFolderID%>">
	<input type="hidden" id="curFolder" value="<%=showFolderName%>">
	<input type="hidden" id="edit_pid" value="">
	<input type="hidden" id="edit_name" value="">
	<input type="hidden" id="edit_url" value="">

	<!-- hidden var end -->

	<!-- 弹出框开始 -->
	<jsp:include page="window/window.jsp" />
	<!-- 弹出框结束 -->
	<jsp:include page="part/foot.jsp" />
	<jsp:include page="part/importAtFoot.jsp" />
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/pageMainApp.js"></script>
</body>

</html>
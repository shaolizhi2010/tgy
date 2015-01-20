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
<%@include file="../part/common.jsp" %>
<%@include file="../part/login-user-data.jsp" %>
<%
	UserService uService = new UserService();
 
%>
<!DOCTYPE html>
<html >
<head lang="en">
<jsp:include page="../part/head-meta.jsp" />
<jsp:include page="../part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="../part/head.jsp" />
	<jsp:include page="../part/private-tabs.jsp" />
	<div class="  col-sm-12" style="margin-top: 20px;"></div>
	<!-- 主页面开始 -->
	<div class="container col-sm-9 clearfix no-padding">  
		<!-------- 书签主页面 --------->
		<div id="pageMain" class="col-sm-10 col-sm-offset-1 container no-padding" >
		 	
		 	<!-- 个人签名 -->
		 	<div class=" container col-sm-12 sub-page-with-title">
	 			<div class="sub-page-title">
					<span style="font-size: 20px; ">编辑个性签名</span>
				</div>
				<div class="col-sm-12 container sub-page-body no-padding" style=" ">
					<div class="col-sm-10    " style="padding: 10px; ">
						 <div style=" " >
			                <textarea  id="publicMessage"  class="form-control  " rows="3"
			                	placeholder=""><%= loginUser!=null&&StringUtils.isNotBlank(loginUser.publicMessage)? loginUser.publicMessage:"这家伙很懒,什么都没有留下" %></textarea>
			            </div>
		            	 
				 	</div>
			 	</div>
		 	</div>
		 	<!-- 个人签名 -->
		 	<div class=" container col-sm-12 sub-page-with-title">
	 			<div class="sub-page-title">
					<span style="font-size: 20px; ">设置我的头像</span>
				</div>
				<div class="col-sm-12 container sub-page-body no-padding" style=" ">
					<div class="col-sm-10" style="padding: 10px; ">
						<div>
							<img alt="头像" src="<%= loginUser!=null&&StringUtils.isNotBlank(loginUser.headImgUrl)? loginUser.headImgUrl:"" %>"
								width="100" height="100">
						</div>
						 <div style=" " >
			                <input id="headImgUrl"  class="form-control" type="url" placeholder="请将头像图片的网址拷贝到这里" value="<%= loginUser!=null&&StringUtils.isNotBlank(loginUser.headImgUrl)? loginUser.headImgUrl:"" %>"/>
			            </div>
		            	 
				 	</div>
			 	</div>
		 	</div>		 	
		 	<!-- 设置邮箱  -->
		 	<div class=" container col-sm-12 sub-page-with-title" style="margin-top: 40px;">
	 			<div class="sub-page-title">
					<span style="font-size: 20px; ">设置邮箱</span>
				</div>
				<div class="col-sm-12 container sub-page-body no-padding" style=" ">
			 		<div class="col-sm-11   " style=" " >
			 			<label for="email">我的邮箱</label>
	            		<input id="email"  class="form-control" type="email" placeholder="" value="<%= loginUser!=null&&StringUtils.isNotBlank(loginUser.email)? loginUser.email:"" %>"/>
	            	</div>
			 	</div>
		 	</div>		
	
		 	<!-- 是否公开 -->
		 	<div class=" container col-sm-12 sub-page-with-title" style="margin-top: 40px;">
	 			<div class="sub-page-title">
					<span style="font-size: 20px;">设置权限</span>
				</div>
				<div class="col-sm-12 container sub-page-body no-padding" style=" ">
					<div class="col-sm-12    " style="padding: 10px; ">
						<div class=" col-sm-12"  >
							<input id="auth-query" value="" type="hidden">
							<span class=" col-sm-4"  style="font-size: 14px;color: #666;">谁能看到我的收藏夹：</span>
							<div class="btn-group ">
								<%
								if(loginUser!=null&&loginUser.authQuery==C.authOwner){
								%>
								<a type="button"  data-value="<%=C.authOwner %>" href="javascript:void(0);" class="btn btn-default btn-success auth-query-btn" style=" color:#fff;" >只有自己</a>
								<a type="button"  data-value="<%=C.authEveryOne %>" href="javascript:void(0);" class="btn btn-default auth-query-btn" style=" ">大家</a>
								<%
								}
								else{
								%>
								<a type="button"  data-value="<%=C.authOwner %>" href="javascript:void(0);" class="btn btn-default  auth-query-btn" >只有自己</a>
								<a type="button"  data-value="<%=C.authEveryOne %>" href="javascript:void(0);" class="btn btn-default btn-success auth-query-btn" style=" color:#fff;" >大家</a>			 
								<%
								}
								%>
								<input type="hidden" id="auth-create-value">
							</div>
						</div>
				 	</div>
			 	</div>
		 	</div>	
		 	 	
		 	<!-- 重置密码 -->
		 	<div class=" container col-sm-12 sub-page-with-title" style="margin-top: 40px;">
	 			<div class="sub-page-title">
					<span style="font-size: 20px; ">重置密码</span>
				</div>
				<div class="col-sm-12 container sub-page-body no-padding" style=" ">
					<div class="col-sm-12    " style="padding: 10px; ">
				 		<div class="col-sm-11   " style="margin-top: 10px;" >
				 			 <label for="editUser-password">密码</label>
               				<input class="form-control" id="editUser-password"   placeholder="不填 则不更改密码"  type="password"/>
                  		</div>
				 		<div class="col-sm-11   " style="margin-top: 10px;" >
				 			<label for="editUser-password-again">确认密码</label>
			                <input  placeholder="不填 则不更改密码"
			                       class="form-control" id="editUser-password-again" type="password"/>
			        	</div>
		            	 
				 	</div>
			 	</div>
		 	</div>
		 	
		 	<div class="col-sm-11" style="margin-top:40px;">
                <button onclick="setting()" id="" type="button"
                        class="btn btn-primary ">确定
                </button>
                <button onclick="javascript:history.go(-1)" type="button" class="btn btn-default">取消</button>
		    </div>
 
			
		</div>
		<!--------  主页面 end  --->
	</div>
 	<div class=" col-sm-3 no-padding" >
		<div class=" col-sm-10 col-sm-offset-1 no-padding" >
		</div>
	</div>
	<!-- 书签主页面结束-->
		<input type="hidden" id="pageID" value="personal-setting">
	
	<jsp:include page="../part/foot-private.jsp" />
	<jsp:include page="../part/importAtFoot.jsp" />
	
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/private/private.setting.js"></script>
</body>

</html>
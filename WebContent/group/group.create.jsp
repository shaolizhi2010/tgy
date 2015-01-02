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
<%@include file="../part/show-user-data.jsp" %>
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
	<jsp:include page="../part/public-tabs.jsp" />
	<div class="  col-sm-12" style="margin-top: 20px;"></div>
	<!-- 主页面开始 -->
	<div class="container col-sm-9 clearfix no-padding">  
		<!-------- 书签主页面 --------->
		<div id="pageMain" class="col-sm-10 col-sm-offset-1 container no-padding" >
		 	<div class=" container col-sm-12 sub-page-with-title">
	 			<div class="sub-page-title">
					<span>创建兴趣组</span>
				</div>
				<div class="col-sm-12 container sub-page-body no-padding" style=" ">
					<div class="col-sm-12    " style="padding: 20px; ">
				 		<div class="col-sm-8   " style="" >
		            		<input   id="group-create-name"  class="form-control"   placeholder="兴趣组名称"/>
		            	</div>
		            	<div class="col-sm-4   " style="">
			                <button onclick="createGroupFunction()" id="" type="button"
			                        class="btn btn-primary ">确定
			                </button>
		            	</div>
				 	</div>
			 	</div>
		 	</div>
		</div>
		<!--------  主页面 end  --->
	</div>
 	<div class=" col-sm-3 no-padding" >
		<div class=" col-sm-10 col-sm-offset-1 no-padding" >
			<jsp:include page="../part/index.public.menu.jsp"/>
		</div>
	</div>
	<!-- 书签主页面结束-->
	
	<jsp:include page="../part/foot.jsp" />
	<jsp:include page="../part/importAtFoot.jsp" />
	
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/group/group.create.js"></script>
</body>

</html>
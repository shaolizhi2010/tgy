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
					<span>编辑网址信息</span>
				</div>
				<div class="col-sm-12 container sub-page-body no-padding" style=" ">
					<div class="col-sm-12    " style="padding: 20px; ">
						<input type="hidden" id="pageID" value="<%=request.getParameter("pageID") %>">
						<input type="hidden" id="groupID" value="<%=request.getParameter("groupID") %>">
				 		<div class="col-sm-11   " style="margin-top: 10px;" >
				 			<label for="pageName">名称</label>
		            		<input id="pageName"  class="form-control"   placeholder=""/>
		            	</div>
				 		<div class="col-sm-11   " style="margin-top: 10px;" >
				 			<label for="pageUrl">网址</label>
		            		<input id="pageUrl"  class="form-control"   placeholder=""/>
		            	</div>
		            	<div class="col-sm-11" style="margin-top: 10px;">
			                <button onclick="editGroupPageFunction()" id="" type="button"
			                        class="btn btn-primary ">确定
			                </button>
			                <button onclick="javascript:history.go(-1)" type="button" class="btn btn-default">取消</button>
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
	<script src="<%=request.getContextPath()%>/myjs/group/group.edit-all.js"></script>
</body>

</html>
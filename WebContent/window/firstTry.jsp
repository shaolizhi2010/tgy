<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="../part/bookmark-data.jsp" %> 
 

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">
				<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">添加自己的网址收藏夹</h4>
		</div>
		<div class="modal-body">

			<div style="margin-top: 20px;">
				<h4>您还没有登录，您可以</h4>
			</div>
			 
			<div style="margin-top: 20px;">
				<button ng-click="preLoginFunction()" 
					type="button" class="btn btn-success">登录</button>
				<button ng-click="preAddUserFunction()" 
					type="button" class="btn btn-primary">注册</button>
				<a  
					href="<%=request.getContextPath()%>/user/create/temp"
					type="button" class="btn btn-warning"
					style="color: #fff;" >快速体验</a>
				
			</div>


			<!-- create page end-->

		</div>

	</div>
</div>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<div class="container col-sm-12 clearfix"
	style="padding: 2px;padding-left: 20px;    margin-bottom: 20px;">

	<div>
		<span style="font-size: 14px;">公用导航：</span>
	</div>

<div style="  padding-top:10px;padding-bottom: 10px; "> 
	<a href="<%=request.getContextPath()%>/公用导航"
		class="  col-sm-8  " style="padding-left:3px; ">  <span
			class="glyphicon glyphicon-star" style="color: #ffd76e;"></span> <span color="#1155cc">公用导航</span>   </a>
			
</div>

<div style=" padding-top:10px;padding-bottom: 10px; ">
				
	<a href="<%=request.getContextPath()%>/视频"
		class="  col-sm-8  " style="padding-left:3px; ">  <span
			class="glyphicon glyphicon-star" style="color: #ffd76e;"></span> <span color="#1155cc">视频</span>   </a>
	
</div>

 
<div style="  padding-top:10px;  ">
				
	<a href="<%=request.getContextPath()%>/网购"
		class="  col-sm-8  " style="padding-left:3px; ">  <span
			class="glyphicon glyphicon-star" style="color: #ffd76e;"></span> <span color="#1155cc">网购</span>   </a>
	
</div> 

<!-- 
		<a class="col-sm-12" target="_blank" href="<%=request.getContextPath()%>/paihang"
		 style="margin-top: 10px;">
		>>查看更多 </a>
		
		 <a class="col-sm-12" ng-click="preCreateFolderFunction()"
		style="margin-top: 10px;"> >>添加 </a>
 -->
</div>

<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<div class="container col-md-12 clearfix"
	style="padding: 2px;   margin-bottom: 20px;">
	
	<p style="padding:  3px;">盒子的收藏：</p>
	
	 <a href="<%=request.getContextPath()%>/folder/?fid=5448f8dd78908430816bcb4d"
		class="  col-md-12  " style="padding:  3px;"> <span class=" glyphicon glyphicon-star-empty"></span> 网址盒子 </a> 
		
		<a class="col-md-12"
		ng-click="preAddPageFunction()" style="margin-top: 10px;">
		>>查看更多 </a>
		
		 <a class="col-md-12" ng-click="preCreateFolderFunction()"
		style="margin-top: 10px;"> >>添加 </a>
</div>
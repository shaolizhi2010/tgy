<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.vo.BreadCrumb"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.vo.Ext"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html ng-app="pageMainApp" ng-controller="pageMainCtrl">
<head lang="en">
<jsp:include page="part/head-meta.jsp" />

<jsp:include page="part/importcss.jsp" />

</head>

<%
	String userID = U.getUserID(request);
	List<Page> pages = new ArrayList();
	if(request.getAttribute("pages")!=null){
		 pages = (List<Page>)request.getAttribute("pages");
	}
	String tagName = String.valueOf( request.getAttribute("tagName")) ;
%>

<body>
	<jsp:include page="part/head.jsp" />

	<!-- 书签主页面开始 -->
	<div class="container col-md-12 clearfix">
		<!-- 左侧书签列表 -->
		<div id="bookmarks-sidebar" class="container col-md-2">
	  		<p>相似类别</p>
	  		<a class="  col-md-12"  href="#">相关类别</a>
	  		<a class="  col-md-12" href="#">相关类别</a>
	  		<a class="  col-md-12" href="#">相关类别</a>
	  		<a class="  col-md-12" href="#">相关类别</a>
	  		<a class="  col-md-12" href="#">相关类别</a>
		

		</div>
		<!-- 左侧书签列表结束-->


		<!-------- 右侧 书签主页面 --------->
		<div id="pageMain" class="  col-md-10">
		
		<div class=" col-sm-12" style="padding: 0px;">
			<p><%=tagName %>网站</p>
		</div>
		
		<%
			for(Page p : pages){
				%>
				
				<a pre-href="<%=p.url%>" target="_blank" class="  c-a" href="<%=p.url%>"
	style="margin-top:10px; display: block; height: 20px;  "> <span
	class="glyphicon glyphicon-star" style="color: #ffd76e;"></span> <span
	style="color: #1155cc;"> <%=p.name%></span> - <%=p.url%> <!-- #ff076e #1155cc; 0000cc-->
</a>
<br>
				
				<%
			}
		%>

		</div>
		<!-------- 右侧 书签主页面end  --->

	</div>
	<!-- 书签主页面结束-->


	<!-- 弹出框开始 -->


	<!-- create user Modal -->
	<div class="modal fade" id="createUserModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

		<jsp:include page="window/createUserModel.jsp" />
	</div>


	<!-- login Modal -->
	<div class="modal fade" id="loginModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">

		<jsp:include page="window/loginModel.jsp" />
	</div>

	<!-- 弹出框结束 -->




	<script>
var app = angular.module("pageMainApp", []);
app.controller('pageMainCtrl',  function ($scope, $element, $http) {

							$scope.preAddUserFunction = function() {

								// $('#createPageDiv').show();
								$('#createUserModel').modal()
								//alert('ng add');
							}
							$scope.addUserFunction = function() {
								$http(
										{
											url : "<%=request.getContextPath()%>/user/create/",
											method : "POST",
											data : {
												"name" : $('#createUser-name')
														.val(),
												"password" : $(
														'#createUser-password')
														.val()
											}
										})
										.success(
												function(data, status, headers,
														config) {
													$('#createUserModel')
															.modal('hide');
													alert(data);
													location.reload(true);
													//$scope.data = data;
												}).error(
												function(data, status, headers,
														config) {
													alert('服务器正在飞速运转，请耐心等待'
															+ data);
													//  $scope.status = status;
												});

							};

							$scope.preLoginFunction = function() {

								// $('#createPageDiv').show();
								$('#loginModel').modal()
								//alert('ng add');
							}
							$scope.loginFunction = function() {
								$http({
									url : "<%=request.getContextPath()%>/user/login/",
											method : "POST",
											data : {
												"name" : $('#login-name').val(),
												"password" : $(
														'#login-password')
														.val()
											}
										})
										.success(
												function(data, status, headers,
														config) {

													if (data == '登录成功') {
														$('#loginModel').modal(
																'hide');
														//location.href = 'http://localhost/';
														location.reload(true);
													} else {
														alert(data);
													}

													// alert(data);

													//$scope.data = data;
												}).error(
												function(data, status, headers,
														config) {
													alert('服务器正在飞速运转，请耐心等待'
															+ data);
													//  $scope.status = status;
												});

							};
							$scope.logoutFunction = function() {
								$http({
									url : "http://localhost/user/logout/",
									method : "POST",
									data : {}
								})
										.success(
												function(data, status, headers,
														config) {
													// $('#loginModel').modal('hide');
													alert(data);
													location.href = 'http://localhost/';
													//$scope.data = data;
												}).error(
												function(data, status, headers,
														config) {
													alert('服务器正在飞速运转，请耐心等待'
															+ data);
													//  $scope.status = status;
												});

							};

							$scope.goTo = function() {
								location.href = 'http://localhost/'
										+ $('#goto-input').val();
							};

							$scope.searchBaidu = function() {
								//location.href = 'http://www.baidu.com/s?wd=' + $('#search-input').val();
								window.open('http://www.baidu.com/s?wd='
										+ $('#search-input-baidu').val());
							};
							$scope.searchGoogle = function() {
								//location.href = 'http://www.baidu.com/s?wd=' + $('#search-input').val();
								window
										.open('https://www.google.com.hk/search?q='
												+ $('#search-input-google')
														.val());
							};

							$(".c-a")
									.click(
											function(event) {
												if ($(this).attr("pre-href") !== null) {

													$(this)
															.attr(
																	"href",
																	$(this)
																			.attr(
																					"pre-href"));
													if ($(this).attr("href")
															.indexOf("http://") == -1
															&& $(this)
																	.attr(
																			"href")
																	.indexOf(
																			"https://") == -1) {
														$(this)
																.attr(
																		"href",
																		"http://"
																				+ $(
																						this)
																						.attr(
																								"href"));
													}

													$(this).trigger("click");
												}
												//    return false;
											})

							$scope.openLink = function(event) {
								$("a").click(function(event) {
									event.stopPropagation(); // do something
								})
							}
						});

		function CheckLength(txtObj) {
			var val = txtObj.val();
			var valLength = 0;
			for (var ii = 0; ii < val.length; ii++) {
				var word = val.substring(ii, 1);
				if (/[^\x00-\xff]/g.test(word)) {
					valLength += 2;
				} else {
					valLength++;
				}
			}
			return valLength;
		}
		/*
		 //alert($(this).attr("pre-href"))

		 $http({
		 url: "http://example.appspot.com/rest/app",
		 method: "POST",
		 data: {"foo": "bar"}
		 }).success(function (data, status, headers, config) {
		 $scope.data = data;
		 }).error(function (data, status, headers, config) {
		 $scope.status = status;
		 });*/
	</script>
</body>
<jsp:include page="part/importjs.jsp" />
</html>
<%@page import="java.util.Collections"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
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
	
	//request.getSession().setAttribute("tests", "testss");
	//System.out.println("jsp session "+ request.getSession(false).getId());
	
	Folder curFolder = U.param(request, "curFolder", Folder.class);
	String pid = "";
	if(curFolder == null){
		curFolder = new Folder();
	}
	else{
		pid = String.valueOf(curFolder.id) ;
	}
	
	    List<Folder> folders = U.paramList(request, "folders");
	    List<Page> pages = U.paramList(request, "pages");
	   // Ext ext = new Ext();  // (Ext)request.getAttribute("ext");
%>

<body>
	<jsp:include page="part/head.jsp" />

	<!-- 书签主页面开始 -->
	<div class="container col-md-12 clearfix">
		<!-- 左侧书签列表 -->
		<div id="bookmarks-sidebar" class="container col-md-2"
			style="padding: 2px;">


			<jsp:include page="part/folder-slide-mine.jsp" />
			<jsp:include page="part/folder-slide-pop.jsp" />
			<jsp:include page="part/folder-slide-follow.jsp" />
 

		</div>
		<!-- 左侧书签列表结束-->


		<!-------- 右侧 书签主页面 --------->
		<div id="pageMain" class="  col-md-10">


			<!--------面包屑----------->
			<div class=" col-sm-12" style="padding: 0px;">
				<div class="col-sm-8">
					<ol class="breadcrumb "
						style="margin: 0px; background-color: #fff;">
						<span>当前位置：</span>
						<%
							BreadCrumb bread = null;
							if(U.param(request, "bread", BreadCrumb.class) != null){
																		bread =  U.param(request, "bread",BreadCrumb.class);
																		while(bread!=null){
						%>
						<li><a href="<%=bread.link%>"><%=bread.name%></a></li>
						<%
							bread = bread.child;
																		}
																	}
						%>

					</ol>
				</div>
				<div class="col-sm-4">
					<a href="<%=request.getRequestURL()+"?"+request.getQueryString() %>&show=h"><span>横着显示</span></a>
					<a href="<%=(request.getRequestURL()+"?"+request.getQueryString()).replaceAll("&show=h", "") %>"><span>竖着显示</span></a>
				</div>
			</div>

			<!---------------------------------------- 分割线 ---------------------------------->
			<hr class="divider col-sm-10"
				style="border-top: 2px solid #eee; margin-top: 1px;">

			<!-- 显示网址页面开始 -->
			<div class="col-sm-9"
				style="padding-top: 0px; padding-bottom: 20px;">
				
				<%
					Folder rootFolder = new Folder();
					rootFolder.name="未分类";
					rootFolder.pages = curFolder.pages;
					if(!folders.contains(rootFolder)){
						Collections.reverse(folders);
						folders.add(rootFolder);
						Collections.reverse(folders);			
					}
					
					for (Folder folder : folders) {

						if(!CollectionUtils.isEmpty(folder.pages)){
						%>
						<div class="col-sm-12">
							<span class="btn col-sm-2" style="background-color: #ccc;  margin-top: 2px;margin-bottom: 2px;   "><%=folder.name%></span>
					 		<div class="col-sm-10">
						<%
						
					for (Page p : folder.pages) {
						String pageName = p.name;
						//name
						pageName = U.shortTitle(pageName);
						if(pageName!=null && pageName.length()>20){
							pageName = pageName.substring(0,20)+"..";
						}
				%>

				<a   target="_blank" class=" col-sm-3  " href="<%=p.url %>"
					style=" margin-top: 5px;margin-bottom: 5px;display: block;color: #222; "> <span
					class="glyphicon glyphicon-star" style="color: #ffd76e;"></span> <span > <%= pageName %></span>  
				</a>
				
				<%
					}//end for pages
					%>
					</div>	
					<hr class="divider col-sm-10"
						style="border-top: 2px solid #eee; margin-top: 1px;">		 
						</div>
					<%
				 }
				%>
						
									
				<%
					}//end for folders
				%>
			</div>
			<!-- 显示网址页面结束 -->
		<!-- 显示推荐页面开始 -->
			<div class="col-sm-3"
				style="padding-top: 20px; padding-bottom: 20px;">
			
			<jsp:include page="part/page-slide-pop.jsp" />
			</div>
			<!-- 显示推荐页面结束 -->

		</div>
		<!-------- 右侧 书签列表页面 end  --->

	</div>
	<!-- 书签主页面结束-->


	<!-- 弹出框开始 -->

	<!-- create root folder model -->
	<div class="modal fade" id="createRootFolderModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<jsp:include page="window/createRootFolder.jsp" />
	</div>

	<!-- upload root folder model -->
	<div class="modal fade" id="uploadBookmarkModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<jsp:include page="window/uploadBookmark.jsp" />
	</div>

	<!-- create folder model -->
	<div class="modal fade" id="createFolderModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<jsp:include page="window/createFolder.jsp" />

	</div>


	<!-- create page model -->
	<div class="modal fade" id="createPageModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<jsp:include page="window/createPage.jsp" />

	</div>


	<!-- create user Modal -->
	<div class="modal fade" id="createUserModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

		<jsp:include page="window/createUser.jsp" />
	</div>


	<!-- login Modal -->
	<div class="modal fade" id="loginModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">

		<jsp:include page="window/login.jsp" />
	</div>

	<!-- 弹出框结束 -->




	<script>
var app = angular.module("pageMainApp", []);
app.controller('pageMainCtrl',  function ($scope, $element, $http) {
    /*
     $scope.init = function () {
     alert( $.cookie('pages') ) ; ;// 读取 cookie
     };
     $scope.init();
     */


    $scope.preAddBookmarkFunction = function () {

        $('#createBookmarkModel').modal();

        // location.href = '#createBookmark_bookmarkName';

    }
    $scope.createBookmarkFunction = function () {
    	//alert( $('#createBookmark_bookmarkName').val());
        $http({
            url: "<%=request.getContextPath()%>/bookmark/create/",
            method: "POST",
            data:  {
            	name:$('#createBookmark_bookmarkName').val(),
            	userID:'<%=userID%>'
            	}// 'name=' +$('#createBookmark_bookmarkName').val() 
           // ,
            //headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function (data, status, headers, config) {
            // alert('添加成功' + JSON.stringify(data));
            $('#createBookmarkModel').modal('hide');
           // $scope.ngBookmarks = data;


            location.reload(true);
            //$scope.data = data;
        }).error(function (data, status, headers, config) {
            alert('服务器正在飞速运转，请耐心等待' + data);
            //  $scope.status = status;
        });

    };

    $scope.preUploadBookmarkFunction = function () {

        $('#uploadBookmarkModel').modal();

        // location.href = '#createBookmark_bookmarkName';

    }

    $scope.preCreateFolderFunction = function (parentFolderName, parentFolderId) {

        $('#createFolderModel').modal();

        //alert('ng add');
    }

    $scope.createFolderFunction = function () {
        $http({
            url: "<%=request.getContextPath()%>/folder/create/",
											method : "POST",
											data : {
												"name" : $(
														'#createFolder_folderName')
														.val(),
												userID:'<%=userID%>',
												pid:'<%=pid%>'
											}
										})
										.success(
												function(data, status, headers,
														config) {
													//  alert('添加成功');
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
						    $scope.preCreateRootFolderFunction = function (parentFolderName, parentFolderId) {

						        $('#createRootFolderModel').modal();

						        //alert('ng add');
						    };
							  $scope.createRootFolderFunction = function () {
							        $http({
							            url: "<%=request.getContextPath()%>/folder/create/",
																		method : "POST",
																		data : {
																			"name" : $(
																					'#createRootFolder_folderName')
																					.val(),
																			userID:'<%=userID%>' 
																		}
																	})
																	.success(
																			function(data, status, headers,
																					config) {
																				//  alert('添加成功');
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

							$scope.preAddPageFunction = function(bookmarkId,
									folderId) {

								// $('#createPageDiv').show();
								$('#createPageModel').modal();

								// $('#createPage_bookmarkId').val(bookmarkId);
								// $('#createPage_folderId').val(folderId);
								//$('#pageUrl').focus();

								//location.href = '#pageUrl';

								//alert('ng add');
							};
							$scope.createPageFunction = function() {
								$http({
									url : "<%=request.getContextPath()%>/page/create/",
									method : "POST",
									data : {
										//"bookmarkId": $('#createPage_bookmarkId').val(),
										// "folderId": $('#createPage_folderId').val(),
										"link" : $('#pageUrl').val(),
										"name" : $('#pageName').val(),
										userID:'<%=userID%>',
										pid:'<%=pid%>'
									}
								})
										.success(
												function(data, status, headers,
														config) {
													// alert('添加成功');
													location.reload(true);
													//$scope.data = data;
												}).error(
												function(data, status, headers,
														config) {
													alert('服务器正在飞速运转，请耐心等待'
															+ data);
													//  $scope.status = status;
												});
								//alert('ng add');
							}

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
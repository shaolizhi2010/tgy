<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.vo.Ext"%>
<%@page import="com.tgy.entity.Bookmark"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html ng-app="pageMainApp" ng-controller="pageMainCtrl">
<head lang="en">
<meta charset="UTF-8">
<title></title>

<link href="stylesheets/bootstrap.min.css" rel="stylesheet">
<link href="stylesheets/bootstrap-theme.min.css" rel="stylesheet">

<script src="javascripts/jquery-1.11.1.min.js"></script>
<script src="javascripts/jquery.cookie.js"></script>
<script src="javascripts/bootstrap.min.js"></script>
<script src="javascripts/angular.min.js"></script>

<!--[if lt IE 9]>
    <script src="/javascripts/html5shiv.js"></script>
    <script src="/javascripts/respond.js"></script>
    <![endif]-->

</head>

	<%
		String userID = null;

	    List<Bookmark> bookmarks = U.paramList(request, "bookmarks");
	    List<Folder> folders = U.paramList(request, "folders");
	    List<Page> pages = U.paramList(request, "bookmarks");
	    Ext ext = new Ext();  // (Ext)request.getAttribute("ext");
	               
	%>

<body>

	<!-- 菜单 开始 -->
	<div id="menu" class="container col-md-12 " style="padding-top: 10px;padding-bottom: 10px;">
		<div id="logo" class="col-md-3">LOGO</div>

		<div class="col-md-2">
			<div class="input-group">
				<input id="search-input-baidu" placeholder="百度一下"
					ng-keyup="$event.keyCode == 13 ? searchBaidu() : null" type="text"
					class="form-control"> <span ng-click="searchBaidu( )"
					class="input-group-addon glyphicon glyphicon-search"></span>
			</div>
		</div>
		<div class="col-md-2">
			<div class="input-group">
				<input id="search-input-google" placeholder="Google一下"
					ng-keyup="$event.keyCode == 13 ? searchGoogle() : null" type="text"
					class="form-control"> <span ng-click="searchGoogle( )"
					class="input-group-addon glyphicon glyphicon-search"></span>
			</div>
		</div>

		<div id="goto-div" class="col-md-2">
			<div class="input-group">
				<input id="goto-input" placeholder="用户名"
					ng-keyup="$event.keyCode == 13 ? goTo() : null" type="text"
					class="form-control"> <span ng-click="goTo( )"
					class="input-group-addon glyphicon glyphicon-chevron-right"></span>
			</div>
		</div>
	</div>

	<!-- 菜单 结束 -->

	<!-- 分割线 -->
	<hr class="divider col-sm-12"
		style="border-top: 4px solid #eee; margin-top: 1px;">
		
		
		
		
		
  
	<script>
var app = angular.module("pageMainApp", []);
app.controller("pageMainCtrl", function ($scope, $element, $http) {
    /*
     $scope.init = function () {
     alert( $.cookie('pages') ) ; ;// 读取 cookie
     };
     $scope.init();
     */


    $scope.preAddBookmarkFunction = function () {

        $('#createBookmarkModel').modal();

        // location.href = '#createBookmark_bookmarkName';

        //alert('ng add');
    }
    $scope.createBookmarkFunction = function () {
        $http({
            url: "http://localhost/bookmark/create",
            method: "POST",
            data: {"createBookmark_bookmarkName": $('#createBookmark_bookmarkName').val(),
                "createBookmark_username": $('#createBookmark_username').val(),
                "createBookmark_password": $('#createBookmark_password').val()

            }
        }).success(function (data, status, headers, config) {
            // alert('添加成功' + JSON.stringify(data));
            $('#createBookmarkModel').modal('hide');
            $scope.ngBookmarks = data;


            location.reload(true);
            //$scope.data = data;
        }).error(function (data, status, headers, config) {
            alert('服务器正在飞速运转，请耐心等待' + data);
            //  $scope.status = status;
        });

    };


    $scope.preCreateFolderFunction = function (parentFolderName, parentFolderId) {

        $('#createFolderModel').modal()

        // $('#createFolder_parentName').val(parentFolderName);
        //$('#createFolder_parentFolderId').val(parentFolderId);
        //location.href = '#createFolder_folderName';

        //alert('ng add');
    }

    $scope.createFolderFunction = function () {
        $http({
            url: "http://localhost/<%=ext.userId%>/bookmark/<%=ext.curBookmarkId%>/folder/<%=ext.curFolderId%>/create",
            method: "POST",
            data: {"folderName": $('#createFolder_folderName').val(),
                "parentFolderId": $('#createFolder_parentFolderId').val()}
        }).success(function (data, status, headers, config) {
            //  alert('添加成功');
            location.reload(true);
            //$scope.data = data;
        }).error(function (data, status, headers, config) {
            alert('服务器正在飞速运转，请耐心等待' + data);
            //  $scope.status = status;
        });

    };

    $scope.preAddPageFunction = function (bookmarkId, folderId) {

        // $('#createPageDiv').show();
        $('#createPageModel').modal()

       // $('#createPage_bookmarkId').val(bookmarkId);
       // $('#createPage_folderId').val(folderId);
        //$('#pageUrl').focus();

        //location.href = '#pageUrl';

        //alert('ng add');
    };
    $scope.createPageFunction = function () {
        $http({
            url: "http://localhost/<%=ext.userId%>/bookmark/<%=ext.curBookmarkId%>/folder//page/create",
											method : "POST",
											data : {
												//"bookmarkId": $('#createPage_bookmarkId').val(),
												// "folderId": $('#createPage_folderId').val(),
												"pageUrl" : $('#pageUrl').val(),
												"pageName" : $('#pageName')
														.val()
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
											url : "http://localhost/user/create",
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
									url : "http://localhost/user/login",
									method : "POST",
									data : {
										"name" : $('#login-name').val(),
										"password" : $('#login-password').val()
									}
								})
										.success(
												function(data, status, headers,
														config) {

													if (data == '登录成功') {
														$('#loginModel').modal(
																'hide');
														location.href = 'http://localhost/';
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
									url : "http://localhost/user/logout",
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
</html>
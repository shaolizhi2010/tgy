

var app = angular.module("pageMainApp", []);
app
		.controller(
				'pageMainCtrl',
				function($scope, $element, $http) {

					var userID = $('#userID').val();
					var contextPath = $('#contextPath').val();
					var fid = $('#fid').val();
					var editingFlag = false;

					/*
					 * $scope.init = function () { alert( $.cookie('pages') ) ;
					 * ;// 读取 cookie }; $scope.init();
					 */

					$scope.preUploadBookmarkFunction = function() {
						$('#uploadBookmarkModel').modal();
					}

					$scope.preCreateFolderFunction = function(parentFolderName,
							parentFolderId) {
						$('#createFolderModel').modal();
					}

					$scope.createFolderFunction = function() {
						$http(
								{
									url : contextPath
											+ "/folder/create/",
									method : "POST",
									data : {
										"name" : $('#createFolder_folderName')
												.val(),
										"userID" : userID,
										"pid" : fid
									}
								}).success(
								function(data, status, headers, config) {
									if(data.indexOf("操作成功")>=0 ){
										location.reload(true);
									}
									else{
										alert(data);
									}
								}).error(
								function(data, status, headers, config) {
									alert('服务器正在飞速运转，请耐心等待' + data);
									// $scope.status = status;
								});

					};
					$scope.editFolderFunction = function() {
						
						$http(
								{
									url : contextPath
											+ "/folder/edit/",
									method : "POST",
									data : {
										"id":$("#editFolder_dataid").val(),
										"name" : $('#editFolder_folderName')
												.val(),
										"pid" : fid
									}
								}).success(
								function(data, status, headers, config) {
									if(data.indexOf("操作成功")>=0 ){
										location.reload(true);
									}
									else{
										alert(data);
									}
								}).error(
								function(data, status, headers, config) {
									alert('服务器正在飞速运转，请耐心等待' + data);
									// $scope.status = status;
								});

					};
					$scope.deleteFolderFunction = function() {
						
						$http(
								{
									url : contextPath
											+ "/folder/delete/",
									method : "POST",
									data : {
										"id":$("#editFolder_dataid").val()
									}
								}).success(
								function(data, status, headers, config) {
									if(data.indexOf("操作成功")>=0 ){
										location.reload(true);
									}
									else{
										alert(data);
									}
								}).error(
								function(data, status, headers, config) {
									alert('服务器正在飞速运转，请耐心等待' + data);
									// $scope.status = status;
								});
					};
					
					$scope.preCreateRootFolderFunction = function(
							parentFolderName, parentFolderId) {

						$('#createRootFolderModel').modal();

						// alert('ng add');
					};
					$scope.createRootFolderFunction = function() {
						$http(
								{
									url : contextPath + "/folder/create/",
									method : "POST",
									data : {
										"name" : $(
												'#createRootFolder_folderName')
												.val(),
										userID : userID,
										isRoot : 'true'
									}
								}).success(
								function(data, status, headers, config) {
									
									if(data.indexOf("操作成功")>=0 ){
										location.reload(true);
									}
									else{
										alert(data);
									}
									
									// $scope.data = data;
								}).error(
								function(data, status, headers, config) {
									alert('服务器正在飞速运转，请耐心等待' + data);
									// $scope.status = status;
								});

					};

					$scope.preAddPageFunction = function(name, url) {
						
						if(typeof name != 'undefined' && name.length>0){
							$('#pageName').val(name);
						}
						
						if(typeof url != 'undefined' && url.length>0){
							$('#pageUrl').val(url);
						}
						
						$('#createPageModel').modal();
					};
					$scope.createPageFunction = function(name, url) {
						
						if(typeof name != 'undefined' && name.length>0){
							$('#pageName').val(name);
						}
						
						if(typeof url != 'undefined' && url.length>0){
							$('#pageUrl').val(url);
						}
						
						$http({
							url : contextPath + "/page/create/",
							method : "POST",
							data : {
								// "bookmarkId":
								// $('#createPage_bookmarkId').val(),
								// "folderId": $('#createPage_folderId').val(),
								"url" : $('#pageUrl').val(),
								"name" : $('#pageName').val(),
								userID : userID,
								pid : $('#createPage_pid').val()
							}
						}).success(function(data, status, headers, config) {
							if(data.indexOf("操作成功")>=0 ){
								location.reload(true);
							}
							else{
								alert(data);
							}
						}).error(function(data, status, headers, config) {
							alert('服务器正在飞速运转，请耐心等待' + data);
							// $scope.status = status;
						});
						// alert('ng add');
					};
					$scope.editPageFunction = function() {
						$http(
								{
									url : contextPath
											+ "/page/edit/",
									method : "POST",
									data : {
										"id":$("#editPage_dataid").val(),
										"name" : $('#editPage_pageName')
												.val(),
										"url" : $('#editPage_url').val(),
										"pid" : fid
									}
								}).success(
								function(data, status, headers, config) {
									if(data.indexOf("操作成功")>=0 ){
										location.reload(true);
									}
									else{
										alert(data);
									}
								}).error(
								function(data, status, headers, config) {
									alert('服务器正在飞速运转，请耐心等待' + data);
								});

					};
					$scope.deletePageFunction = function() {
						$http(
								{
									url : contextPath
											+ "/page/delete/",
									method : "POST",
									data : {
										"id":$("#editPage_dataid").val()
									}
								}).success(
								function(data, status, headers, config) {
									if(data.indexOf("操作成功")>=0 ){
										location.reload(true);
									}
									else{
										alert(data);
									}
								}).error(
								function(data, status, headers, config) {
									alert('服务器正在飞速运转，请耐心等待' + data);
								});

					};
					$scope.preAddUserFunction = function() {

						$('#createUserModel').modal();
					}
					$scope.addUserFunction = function() {
						$http({
							url : contextPath + "/user/create/",
							method : "POST",
							data : {
								"name" : $('#createUser-name').val(),
								"password" : $('#createUser-password').val()
							}
						}).success(function(data, status, headers, config) {
							$('#createUserModel').modal('hide');
							if(data.indexOf("操作成功")>=0 ){
								location.href = contextPath;
							}
							else{
								alert(data);
							}
							// $scope.data = data;
						}).error(function(data, status, headers, config) {
							alert('服务器正在飞速运转，请耐心等待' + data);
							// $scope.status = status;
						});

					};

					$scope.preLoginFunction = function() {
						$('#loginModel').modal();
					}
					$scope.loginFunction = function() {
						$http({
							url : contextPath + "/user/login/",
							method : "POST",
							data : {
								"name" : $('#login-name').val(),
								"password" : $('#login-password').val()
							}
						}).success(function(data, status, headers, config) {

							if (data == '登录成功') {
								location.href = contextPath;
								
								//$('#loginModel').modal('hide');
								
								// location.reload(true);
							} else {
								alert(data);
								//alert(data);
							}

							// alert(data);

							// $scope.data = data;
						}).error(function(data, status, headers, config) {
							alert('服务器正在飞速运转，请耐心等待' + data);
							// $scope.status = status;
						});

					};
					$scope.logoutFunction = function() {
						$http({
							url : contextPath + "/user/logout/",
							method : "POST",
							data : {}
						}).success(function(data, status, headers, config) {
							if(data.indexOf("操作成功")>=0 ){
								location.reload(true);
							}
							else{
								alert(data);
							}
						}).error(function(data, status, headers, config) {
							alert('服务器正在飞速运转，请耐心等待' + data);
							// $scope.status = status;
						});

					};

					$scope.searchSite = function() {
						location.href = contextPath + '/site/?tag='
								+ $('#searchSite-input').val();
					};

					$scope.searchBaidu = function() {
						// location.href = 'http://www.baidu.com/s?wd=' +
						// $('#search-input').val();
						window.open('http://www.baidu.com/s?wd='
								+ $('#search-input-baidu').val());
					};
					$scope.searchGoogle = function() {
						// location.href = 'http://www.baidu.com/s?wd=' +
						// $('#search-input').val();
						window.open('https://www.google.com.hk/search?q='
								+ $('#search-input-google').val());
					};
					$scope.preEditAll = function() {
						editingFlag = !editingFlag;
						if(editingFlag){
							$(".editable").append("<span class='glyphicon glyphicon-pencil' style='font-size:8px;'></span>");
							$(".editable").addClass('editing');
							$(".editing").click( function(event) {
								event.preventDefault();
								// alert($(this).attr('dataid'));
								if($(this).hasClass('folderMark')){
									$("#editFolder_dataid").val( $(this).attr('dataid'));
									$("#editFolder_folderName").val(  $(this).attr('dataname') );
									$('#editFolderModel').modal();
								}
								else if($(this).hasClass('pageMark')){
									$("#editPage_dataid").val( $(this).attr('dataid'));
									$("#editPage_pageName").val(  $(this).attr('dataname'));
									$("#editPage_url").val( $.trim($(this).attr('href')) );
									$('#editPageModel').modal();
								}
								
								
							});
						}
						else{
							$(".glyphicon-pencil").remove();
							$(".editable").removeClass('editing');
							$(".editable").unbind('click');//取消弹出编辑框，回复初始状态。
						}
						

						// alert('edit');
					};
					
// $scope.edit = function(event){
// alert(event);
// }

					$scope.openLink = function(id,type) {
							
						$http({
							url : contextPath + "/statistic/click/",
							method : "POST",
							data : {
								
								"id" : id,
								"type" : type
								
							}
						});
						
//						$("a").click(function(event) {
//							event.stopPropagation(); // do something
//						});
					};
				});

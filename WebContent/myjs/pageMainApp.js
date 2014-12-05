var app = angular.module("pageMainApp", []);
app.controller(
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
						$('.modal').modal('hide');
						$('#uploadBookmarkModel').modal();
					};
					
					

					$scope.preCreateFolderFunction = function(parentFolderName,
							parentFolderId) {
						
						if( window.clipboardData ){
							
							//alert( window.clipboardData.getData("text") );
							
							//if(/text\/plain/.test(window.clipboardData.types)){
								$('#createFolder_folderName')
								.val(window.clipboardData.getData("text"));
							//}
						}
						
						$('.modal').modal('hide');
						$('#createFolderModel').modal();
					}

					$scope.createFolderFunction = function() {
						$http(
								{
									url : contextPath
											+ "/folder/create",
									method : "POST",
									data : {
										"name" : $('#createFolder_folderName')
												.val(),
										"userID" : userID
										 
									}
								}).success(
								function(data, status, headers, config) {
									if(data.indexOf("操作成功")>=0 ){
										location = contextPath;
										//location.reload(true);
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
											+ "/folder/edit",
									method : "POST",
									data : {
										"id":$("#editFolder_dataid").val(),
										"name" : $('#editFolder_folderName')
												.val() 
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
											+ "/folder/delete",
									method : "POST",
									data : {
										"id":$("#editFolder_dataid").val()
									}
								}).success(
								function(data, status, headers, config) {
									if(data.indexOf("操作成功")>=0 ){
										//删除的是当前查看的folder，则跳回主页
										if(fid == $("#editFolder_dataid").val()){
											location = contextPath;
										}
										else{
											location.reload(true);
										}
										
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
					 

					$scope.preAddPageFunction = function(name, url,pid) {
						
						if(typeof name != 'undefined' && name.length>0){
							$('#pageName').val(name);
						}
						
						if(typeof url != 'undefined' && url.length>0){
							$('#pageUrl').val(url);
						}
						
						if(typeof pid != 'undefined' && pid.length>0){
							$('#createPage_pid').val(pid);
						}
						$('.modal').modal('hide');
						$('#createPageModel').modal();
					};
					$scope.createPageFunction = function(name, url) {
						
						if(typeof name != 'undefined' && name.length>0){
							$('#pageName').val(name);
						}
						
						if(typeof url != 'undefined' && url.length>0){
							$('#pageUrl').val(url);
						}
						//alert($('#link_prompt_index').val());
						if($('#link_prompt_index').val() != '-1'){
							return;
						}
						
						
						$http({
							url : contextPath + "/page/create",
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
								location.href = $('#contextPath').val()+'/u/'+$('#userID').val()  ;//TODO
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
											+ "/page/edit",
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
											+ "/page/delete",
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
					$scope.firstTryFunction = function() {
						$('.modal').modal('hide');
						$('#firstTryModel').modal();
					}
					$scope.preAddUserFunction = function() {
						$('.modal').modal('hide');	
						$('#createUserModel').modal();
					}
					$scope.addUserFunction = function() {
						
						if($('#createUser-password').val() != $('#createUser-password-again').val()){
							alert("两次输入的用户名不一致,请重新输入");
							return
						}
						
						$http({
							url : contextPath + "/user/create",
							method : "POST",
							data : {
								"name" : $('#createUser-name').val(),
								"password" : $('#createUser-password').val()
							}
						}).success(function(data, status, headers, config) {
							$('#createUserModel').modal();
							if(data.indexOf("成功")>=0 ){
								if($('#contextPath').val() == ''){
									location.href =  '/' ;
								}
								else{
									location.href =  $('#contextPath').val()  ;
								}
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
					$scope.preEditUserFunction = function() {
						$('.modal').modal('hide');
						$('#editUserModel').modal();
					};
					
					$scope.editUserFunction = function() {
						$http({
							url : contextPath + "/user/edit",
							method : "POST",
							data : {
								"id":$('#editUser-id').val(),
								"name" : $('#editUser-name').val(),
								"password" : $('#editUser-password').val()
							}
						}).success(function(data, status, headers, config) {
							$('#editUserModel').modal('hide');
							if(data.indexOf("操作成功")>=0 ){
								if($('#contextPath').val() == ''){
									location.href =  '/' ;
								}
								else{
									location.href =  $('#contextPath').val()  ;
								}
								
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
					$scope.preEditTempUserFunction = function() {
						$('.modal').modal('hide');
						$('#editTempUserModel').modal();
					};
					$scope.editTempUserFunction = function() {
						$http({
							url : contextPath + "/user/edit",
							method : "POST",
							data : {
								"id":$('#editTempUser-id').val(),
								"name" : $('#editTempUser-name').val(),
								"password" : $('#editTempUser-password').val()
							}
						}).success(function(data, status, headers, config) {
							$('#editTempsUserModel').modal('hide');
							if(data.indexOf("操作成功")>=0 ){
								if($('#contextPath').val() == ''){
									location.href =  '/' ;
								}
								else{
									location.href =  $('#contextPath').val()  ;
								}
								
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
						$('.modal').modal('hide');
						$('#loginModel').modal();
					}
					$scope.loginFunction = function() {
						$http({
							url : contextPath + "/user/login",
							method : "POST",
							data : {
								"name" : $('#login-name').val(),
								"password" : $('#login-password').val()
							}
						}).success(function(data, status, headers, config) {

							if (data.indexOf("成功")>=0 ) {
								if($('#contextPath').val() == ''){
									location.href =  '/' ;
								}
								else{
									location.href =  $('#contextPath').val()  ;
								}
								
								//location.reload(true);
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
							url : contextPath + "/user/logout",
							method : "POST",
							data : {}
						}).success(function(data, status, headers, config) {
							if(data.indexOf("操作成功")>=0 ){
								location.href =  $('#contextPath').val();
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
						location.href = contextPath + '/site?tag='
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
									$('.modal').modal('hide');
									$('#editFolderModel').modal();
								}
								else if($(this).hasClass('pageMark')){
									$("#editPage_dataid").val( $(this).attr('dataid'));
									$("#editPage_pageName").val(  $(this).attr('dataname'));
									$("#editPage_url").val( $.trim($(this).attr('href')) );
									$('.modal').modal('hide');
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
							url : contextPath + "/statistic/click",
							method : "POST",
							data : {
								
								"id" : id,
								"type" : type
								
							}
						});
					};
					
					$scope.openFolder = function(id,name,obj ) {
						//正在编辑，执行编辑操作，不执行本方法
						if( $(obj.target.parentElement).hasClass('editing') ||$(obj.target).hasClass('editing')){
							return;
						}
						else{
							//$event.preventDefault(); 
							$http({
								url : contextPath + "/statistic/click",
								method : "POST",
								data : {
									"id" : id,
									"type" : 'folder'
								}
							}).success(function(data, status, headers, config) {
							}).error(function(data, status, headers, config) {
							});; 
							location.href = contextPath+"/folder/"+id+"/"+name;
						}
						
					
					};
					
				});


//$(".link-prompt").click( linkPromptFunc);
$("#showUserHistoryBtn").click( function(){
	//alert($('#loginUserID').val());
	$("#showUserHistoryContent").empty();
	if($('#loginUserID').val()=='' || $('#loginUserID').val()=='not login'){//未登录，不现实
		$("#showUserHistoryContent").append('<li><a  href="'+$('#contextPath').val()+'/u/'+$('#userID').val()+'" style=" "><span style="color: #999; font-size: 13px;">用户:</span> <span style="color: #333; font-weight: bold; font-size: 13px;">'+$('#showUserName').val()+'</span></a></li>');
		return; 
	}
	
	
	$.ajax({
		  url: $('#contextPath').val()+"/visitHistory/requestUser",
		  data:{"userID":$('#loginUserID').val()  },
		  success:function(data) {
			  
			  var dataJson = JSON.parse(data);
			  
			  for(var i=0;i<dataJson.length;i++){
				 // alert(linksJson[i].name);
				 // alert(linksJson[i].url);
				  $("#showUserHistoryContent").append('<li><a  href="'+$('#contextPath').val()+'/u/'+dataJson[i].responseUserID+'" style=" "><span style="color: #999; font-size: 13px;">用户:</span> <span style="color: #333; font-weight: bold; font-size: 13px;">'+dataJson[i].responseUserName+'</span></a></li>');
			  } 
			  $("#link-prompt-div").show();
			  $(".link-prompt").click( linkPromptFunc);
			}
		  
		}) ;
} );

$(".hover-focus").hover( 
function() { 
	$(this).focus();
}
,
function() { 
}
);




//god come
function godMethod(event){
	setTimeout(function(){
		$("#god-content-div").show(); 
	},300);
	
	
	var url = $(this).attr('dataurl');
	if(url == '' || typeof url == 'undefined'){
		url = $('#god-input').val();
	}
	if(url!=''){
		if(url.indexOf('http://')!=0 && url.indexOf('https://')!=0){
			url = 'http://'+url;
		}
		$('#god_link').attr('href',url);
		$('#god_link')[0].click();
	}
	
	
	
	//$('#god_link').attr('href', $(this).attr('dataurl'));
	//$('#god_link')[0].click();
	
	//alert('god here');
	//$("#god-input").setSelection(1,2);
	//alert(  $(this).attr('dataurl') );
	
} 
$("#god-input").blur(function(){
	setTimeout(function(){
		$("#god-content-div").hide(); 
	},300);
	
}) ;
$("#god-input").on('keyup',godPrompt  );
$("#god-input").on('click',godPrompt  ); 
$("#god-content-div").on('click',function(){
	//alert($(this).attr('dataurl'));
	//alert('aaa');
	  }  ); 

var god_index;
function godPrompt(event){
	var keycode = event.which||event.keyCode;
	if(keycode==38||keycode==40||keycode==13){
		return;
	}
	god_index = 0;
	
	if($("#god-input").val().length > 0){
		// alert( $('#contextPath').val()+"/link/prompt" );
		$.ajax({
			  url: $('#contextPath').val()+"/god/prompt",
			  data:{"inputValue":$("#god-input").val()  },
			  success:function(data) {
				  
				 // alert(linksData);
				
				  $("#god-content-div").empty();
				 
				  var dataJson = JSON.parse(data);
				  //alert(linksJson.length);
				  //if(dataJson.length>0){
					 
					  var i=0;
					  for(;i<dataJson.length;i++){
						 // alert(linksJson[i].name);
						 // alert(linksJson[i].url);
						  $("#god-content-div").append('<div id="god_element_'+i+'" data-index="'+i+'" class="col-sm-12 god-element" style="padding: 5px; padding-left:10px; padding-top: 10px;  border-bottom: 1px solid #eee;" dataurl="'+dataJson[i].url+'"><span style="color: #2e8cd8;  font-weight: bold;;font-size: 14px;"> '+dataJson[i].name+'</span> - <span style="color:green;font-size: 11px;"> '+dataJson[i].urlShow+'</span></div>');
					  }
					  //i++;
					  $("#god-content-div").append('<div id="god_element_'+i +'" data-index="'+i+'" class="col-sm-12 god-element" style="padding: 5px; padding-left:10px; padding-top: 10px;  border-bottom: 1px solid #eee;" dataurl="www.baidu.com/s?wd='+ $('#god-input').val()  +'"><span style="color: #2e8cd8;  font-weight: bold;;font-size: 14px;"> 百度搜索 '+$('#god-input').val() +'</span> </div>');
					 
					  var RegUrl = new RegExp(); 
					  RegUrl.compile("^.+[.].+$");
					  
					  if( RegUrl.test($('#god-input').val())){
						  i++;
						  $("#god-content-div").append('<div id="god_element_'+i+'" data-index="'+i+'" class="col-sm-12 god-element" style="padding: 5px; padding-left:10px; padding-top: 10px;  border-bottom: 1px solid #eee;" dataurl="'+ $('#god-input').val()  +'"><span style="color: green;  font-weight: bold;;font-size: 14px;"> 直接访问 '+$('#god-input').val() +'</span> </div>');
					  }
				 
					  //
					  if(dataJson.length<=3){ 
						  i++;
						  $("#god-content-div").append('<div id="god_element_'+i +'" data-index="'+i+'" class="col-sm-12 god-element" style="padding: 5px; padding-left:10px; padding-top: 10px;  border-bottom: 1px solid #eee;" dataurl="www.bing.com/search?q='+ $('#god-input').val()  +'"><span style="color: #2e8cd8;  font-weight: bold;;font-size: 14px;"> 必应搜索 '+$('#god-input').val() +'</span> </div>');

						  i++;
						  $("#god-content-div").append('<div id="god_element_'+i +'" data-index="'+i+'" class="col-sm-12 god-element" style="padding: 5px; padding-left:10px; padding-top: 10px;  border-bottom: 1px solid #eee;" dataurl="www.google.com.hk/search?q='+ $('#god-input').val()  +'"><span style="color: #2e8cd8;  font-weight: bold;;font-size: 14px;"> Google搜索 '+$('#god-input').val() +'</span> </div>');
		
					  }
					  if(dataJson.length<=0){ 
						  i++;
						  $("#god-content-div").append('<div id="god_element_'+i +'" data-index="'+i+'" class="col-sm-12 god-element" style="padding: 5px; padding-left:10px; padding-top: 10px;  border-bottom: 1px solid #eee;" dataurl="www.yandex.com/yandsearch?text='+ $('#god-input').val()  +'"><span style="color: #2e8cd8;  font-weight: bold;;font-size: 14px;"> Yandex搜索 '+$('#god-input').val() +'</span> </div>');
		
						   i++;
						  $("#god-content-div").append('<div id="god_element_'+i +'" data-index="'+i+'" class="col-sm-12 god-element" style="padding: 5px; padding-left:10px; padding-top: 10px;  border-bottom: 1px solid #eee;" dataurl="www.so.com/s?q='+ $('#god-input').val()  +'"><span style="color: #2e8cd8;  font-weight: bold;;font-size: 14px;"> 360搜索 '+$('#god-input').val() +'</span> </div>');
					 }

					  
					  
					  
				//  }
				 // else{//没有从后台找到结果
					  
					
					  
					  //$("#god-content-div").hide();
				//  }
				  $("#god-content-div").show();
				   $('#god_element_0').css('background-color',' #eee');
				  //$("#god-content-div").show();

				  $(".god-element").click(godMethod );
//				  $(".god-element").on('keydown',function(){
//					  alert('key up');
//				  } );  
				  
				  $(".god-element").hover(function(){
					  $('.god-element').css('background-color',' #fff');
					  $(this).css('background-color',' #eee') ;
					  god_index =  $(this).attr('data-index');
				  });
				}
			  
			}) ;
		
	}
	else{//小于 
		$("#god-content-div").empty();
		 $("#god-content-div").hide();
	}
}


$("#god-div").on('keyup', function(event) {
	 
	var keycode = event.which||event.keyCode;
	switch(keycode){
		
		case 38:{//up
			if(god_index>=1){
				god_index--;
				$('.god-element').css('background-color',' #fff');
				$('#god_element_'+god_index).css('background-color',' #eee');
			}
			else{//调到最后一个元素
				god_index = $('.god-element').length-1;
				$('.god-element').css('background-color',' #fff');
				$('#god_element_'+god_index).css('background-color',' #eee');
			}
	
			return;
		};
		case 40:{//down
			 	var num = $('.god-element').length;
			 	
			 	if(god_index<num-1){
			 		god_index++;
					//alert( $('#link_prompt_'+$('#link_prompt_index').val()) );
					$('.god-element').css('background-color',' #fff');
					$('#god_element_'+god_index).css('background-color',' #eee');
					
					
					//var start = $("#god-input").val().length;
					
					//$("#god-input").val($('#god_element_'+god_index).attr('dataurl') ); //
					//$("#god-input").setSelection(start,$("#god-input").val().length);
					
					//alert($('#link_prompt_index').val());
			 
			 	}
			return;
		};
		case 13:{//enter
			//godMethod();
			//alert( $('#god_element_'+god_index).attr('dataurl'));
			var url = $('#god_element_'+god_index).attr('dataurl');
			if(url == '' || typeof url == 'undefined'){
				url = $('#god-input').val();
			}
			if(url!=''){
				if(url.indexOf('http://')!=0 && url.indexOf('https://')!=0){
					url = 'http://'+url;
				}
				$('#god_link').attr('href',url);
				$('#god_link')[0].click();
			}
			
			//$('#god_link').attr('href', $('#god_element_'+god_index).attr('dataurl'));
			//$('#god_link')[0].click();
			
			
			//return;
		};
	}
});



//god left

//create page, prompt by name,begin
function linkPromptFunc(event){
	var link = $(this).attr('dataurl');
	$("#pageUrl").text(link);
	
	$("#link-prompt-div").empty();
	$("#link-prompt-div").hide();
}
$("#pageName").on('keyup', function() {
	
	var keycode = event.which||event.keyCode;
	if(keycode==38||keycode==40||keycode==13){
		return;
	}
	$('#link_prompt_index').val( '-1' );
	
	if($("#pageName").val().length > 1){
		// alert( $('#contextPath').val()+"/link/prompt" );
		$.ajax({
			  url: $('#contextPath').val()+"/link/prompt",
			  data:{"linkname":$("#pageName").val()  },
			  success:function(linksData) {
				  
				 // alert(linksData);
				  
				  $("#link-prompt-div").empty();
				 // $("#pageUrl").text(''); //清空url
				  
				  var linksJson = JSON.parse(linksData);
				  //alert(linksJson.length);
				  
				  for(var i=0;i<linksJson.length;i++){
					 // alert(linksJson[i].name);
					 // alert(linksJson[i].url);
					  $("#link-prompt-div").append('<div id="link_prompt_'+i+'" class="col-sm-12 link-prompt" style="padding: 5px; padding-left:10px; padding-top: 10px;  border: 1px solid #eee;" dataurl="'+linksJson[i].url+'"><span style="color: #2e8cd8;  font-weight: bold;;font-size: 14px;"> '+linksJson[i].name+'</span> - <span style="color:green;font-size: 11px;"> '+linksJson[i].urlShow+'</span></div>');
				  }
				  $("#link-prompt-div").show();
				  $(".link-prompt").click( linkPromptFunc);
				}
			  
			}) ;
		
	}
	else{//小于1
		$("#link-prompt-div").empty();
	}
	
});


$("#createPage-name-div").on('keyup', function(event) {
	var keycode = event.which||event.keyCode;
	var index = parseInt($('#link_prompt_index').val());
	switch(keycode){
		
		case 38:{//up
			if(index>=1){
				$('#link_prompt_index').val( index-1 );
				$('.link-prompt').css('background-color',' #fff');
				$('#link_prompt_'+$('#link_prompt_index').val()).css('background-color',' #eee');
			}
	
			return;
		};
		case 40:{//down
			 	var num = $('.link-prompt').length;
			 	if(index<num-1){
			 		$('#link_prompt_index').val( index+1 );
					//alert( $('#link_prompt_'+$('#link_prompt_index').val()) );
					$('.link-prompt').css('background-color',' #fff');
					$('#link_prompt_'+$('#link_prompt_index').val()).css('background-color',' #eee');
					//alert($('#link_prompt_index').val());
			 
			 	}
				
				
	
			return;
		};
		case 13:{//enter
			//alert( $('#link_prompt_'+$('#link_prompt_index').val()).attr('dataurl') );
			$("#pageUrl").text( $('#link_prompt_'+$('#link_prompt_index').val()).attr('dataurl') );
			
			$("#link-prompt-div").empty();
			$("#link-prompt-div").hide();
			$('#link_prompt_index').val( -1 )//index回归初始值
		return;
		};
	}
});

$("#bookmarkFileBtn").click( function(event) {
	 $.ajaxFileUpload
     (
         {
             url: $('#contextPath').val() +'/bookmark/upload', //用于文件上传的服务器端请求地址
             secureuri: false, //是否需要安全协议，一般设置为false
             fileElementId: 'bookmarkFile', //文件上传域的ID
             dataType: 'JSON', //返回值类型 一般设置为json
             success: function (data, status)  //服务器成功响应处理函数
             {
             	if(data.indexOf("操作成功")>=0 ){
						location.reload(true);
					}
					else{
						alert(data);
					}
             },
             error: function (data, status, e)//服务器响应失败处理函数
             {
             	alert('服务器正在飞速运转，请耐心等待' + data);
             }
         }
     );
     return false;
});
$(".deletePage").click( function(event) {
	
	if(confirm("要删除这条网址将？")){
		$.ajax({
			  url: $('#contextPath').val() + "/page/delete",
			  data:{"id": $(this).attr('data-id')   },
			  success:function(data) {
				  if(data.indexOf("操作成功")>=0 ){
						location.reload(true);
					}
					else{
						alert(data);
					}
			  }
		}
		);
	}
			  
	 
});


/*
			$(".statistic_folder").click( function(event) {
				alert("path = "+ $('#contextPath').val() + "/statistic/click");
				event.preventDefault();
				
				$.post(
						$('#contextPath').val() + "/statistic/click",
					{
					"id" :  $(this).attr('dataid'),
					"type" : 'folder'
					} ,function(result){
						
					});
				
				//location.reload(true);
				
			});*/

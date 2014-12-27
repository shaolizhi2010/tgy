var userID = $('#userID').val();
var contextPath = $('#contextPath').val();
var fid = $('#fid').val();
var editingFlag = false;
var loginFlag = $('#loginFlag').val();

function preUploadBookmarkFunction() {
	$('.modal').modal('hide');
	$('#uploadBookmarkModel').modal();
};

function preOpenMyFolder(){
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	else{
		location = '/';
	}
}
					
function preCreateFolderFunction(parentFolderName,
		parentFolderId) {
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
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
var createFolderFunction = function() {
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	$.ajax(
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
			function(data ) {
				if(data.indexOf("操作成功")>=0 ){
					location = contextPath;
					//location.reload(true);
				}
				else{
					alert(data);
				}
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
				// $scope.status = status;
			});

};		
function preCreateDiscuss(id) {
	$('.modal').modal('hide');
	$('#createlinkComment_targetID').val(id);
	$('#createCommentForLink').modal();
};
var createDiscussFunction = function() {
	$.ajax(
			{
				url : contextPath
						+ "/discuss/create",
				method : "POST",
				data : {
					"targetID":$('#createlinkComment_targetID').val(),
					"message" : $('#createlinkCommentMessage')
							.val(),
					"type":$('#discussType').val()
				}
			}).success(
			function(data) {
				if(data.indexOf("操作成功")>=0 ){
					location.reload(true);
				}
				else{
					alert(data);
				}
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
				// $scope.status = status;
			});
}

var editFolderFunction = function() {
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	
	$.ajax(
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
			function(data) {
				if(data.indexOf("操作成功")>=0 ){
					location.reload(true);
				}
				else{
					alert(data);
				}
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
				// $scope.status = status;
			});

};		
		
var deleteFolderFunction = function() {
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	
	$.ajax(
			{
				url : contextPath
						+ "/folder/delete",
				method : "POST",
				data : {
					"id":$("#editFolder_dataid").val()
				}
			}).success(
			function(data) {
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
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
				// $scope.status = status;
			});
};

var preAddPageFunction = function(name, url,pid) {
 
	
	//if(!(loginFlag=='true')){
	//	preLoginFunction();
	//	return;
	//}
	
	if(typeof name != 'undefined' && name.length>0){
		$('#pageName').val(name);
	}
	
	if(typeof url != 'undefined' && url.length>0){//说明是copy网址，不是新加，因为url打开添加页就已经有了
		
		if(!(loginFlag=='true')){
			preLoginFunction();
			return;
		}
		
		$('#pageUrl').val(url);
	}
	
	if(typeof pid != 'undefined' && pid.length>0){
		$('#createPage_pid').val(pid);
	}
	$('.modal').modal('hide');
	$('#createPageModel').modal();
};


var createPageFunction = function(name, url) {
	//if(!(loginFlag=='true')){
	//	preLoginFunction();
	//	return;
	//}
	
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
	
	
	$.ajax({
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
	}).success(function(data) {
		if(data.indexOf("操作成功")>=0 ){
			location.href = $('#contextPath').val()+'/u/'+$('#userID').val()  ;//TODO
		}
		else{
			//alert(JSON.stringify(data));
			alert(data);
		}
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
		// $scope.status = status;
	});
	// alert('ng add');
};

var editPageFunction = function() {
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	$.ajax(
			{
				url : contextPath
						+ "/page/edit",
				method : "POST",
				data : {
					"id":$("#editPage_dataid").val(),
					"name" : $('#editPage_pageName')
							.val(),
					"url" : $('#editPage_url').val(),
					"pid" : $('#editPage_pid').val()
				}
			}).success(
			function(data) {
				if(data.indexOf("操作成功")>=0 ){
					location.reload(true);
				}
				else{
					alert(data);
				}
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
			});

};

var deletePageFunction = function() {
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	$.ajax(
			{
				url : contextPath
						+ "/page/delete",
				method : "POST",
				data : {
					"id":$("#editPage_dataid").val()
				}
			}).success(
			function(data) {
				if(data.indexOf("操作成功")>=0 ){
					location.reload(true);
				}
				else{
					alert(data);
				}
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
			});

};

var firstTryFunction = function() {
	$('.modal').modal('hide');
	$('#firstTryModel').modal();
}

var preAddUserFunction = function() {
	$('.modal').modal('hide');	
	$('#createUserModel').modal();
}

var addUserFunction = function() {
	
	if($('#createUser-password').val() != $('#createUser-password-again').val()){
		alert("两次输入的用户名不一致,请重新输入");
		return;
	}
	$.ajax({
		url : contextPath + "/user/create",
		method : "POST",
		data : {
			"name" : $('#createUser-name').val(),
			"password" : $('#createUser-password').val(),
			"authCreate":$('#auth-create-value').val()
		}
	}).success(function(data) {
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
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
		// $scope.status = status;
	});

};
var preEditUserFunction = function() {
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	$('.modal').modal('hide');
	$('#editUserModel').modal();
};
var preEditPublicMessageFunction = function() {
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	$('.modal').modal('hide');
	$('#editPublicMessageModel').modal();
};



var editUserFunction = function() {
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	$.ajax({
		url : contextPath + "/user/edit",
		method : "POST",
		data : {
			"id":$('#editUser-id').val(),
			"name" : $('#editUser-name').val(),
			"password" : $('#editUser-password').val(),
			"authCreate":$('#auth-create-value').val()
		}
	}).success(function(data) {
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
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
		// $scope.status = status;
	});

};
var showAdditionalDiv = function(){
	$('.additionalDiv').toggle();
}

var editPublicMessageFunction = function() {
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	$.ajax({
		url : contextPath + "/public/message",
		method : "POST",
		data : {
			"userID":$('#publicMessageUserID').val(),
			"message" : $('#publicMessageValue').val()
		}
	}).success(function(data) {
		$('#editPublicMessage').modal('hide');
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
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
		// $scope.status = status;
	});

};
var preEditTempUserFunction = function() {
	$('.modal').modal('hide');
	$('#editTempUserModel').modal();
};
var editTempUserFunction = function() {
	$.ajax({
		url : contextPath + "/user/edit",
		method : "POST",
		data : {
			"id":$('#editTempUser-id').val(),
			"name" : $('#editTempUser-name').val(),
			"password" : $('#editTempUser-password').val()
		}
	}).success(function(data) {
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
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
		// $scope.status = status;
	});

};
var preLoginFunction = function() {
	$('.modal').modal('hide');
	$('#loginModel').modal();
}
var loginFunction = function() {
	$.ajax({
		url : contextPath + "/user/login",
		method : "POST",
		data : {
			"name" : $('#login-name').val(),
			"password" : $('#login-password').val()
		}
	}).success(function(data) {

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
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
		// $scope.status = status;
	});

};
var logoutFunction = function() {
	$.ajax({
		url : contextPath + "/user/logout",
		method : "POST",
		data : {}
	}).success(function(data) {
		if(data.indexOf("操作成功")>=0 ){
			location.href =  $('#contextPath').val();
		}
		else{
			alert(data);
		}
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
		// $scope.status = status;
	});

};
var preEditAll = function() {
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
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

var openLink = function(id,type) {
	$.ajax({
		url : contextPath + "/statistic/click",
		method : "POST",
		data : {
			
			"id" : id,
			"type" : type
			
		}
	});
};
var openFolder = function(id,name,obj ) {
	//正在编辑，执行编辑操作，不执行本方法
	if( $(obj.target.parentElement).hasClass('editing') ||$(obj.target).hasClass('editing')){
		return;
	}
	else{
		//$event.preventDefault(); 
		$.ajax({
			url : contextPath + "/statistic/click",
			method : "POST",
			data : {
				"id" : id,
				"type" : 'folder'
			}
		}).success(function(data) {
		}).error(function(data) {
		});; 
		location.href = contextPath+"/folder/"+id+"/"+name;
	}
	

};


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
	
} 
$("#god-input").blur(function(){
	
	setTimeout(function(){
		$("#god-content-div").hide();
		$("#god-div").removeClass('col-md-6').addClass('col-md-3');
		$("#head-sub-menu").show();
	},300);
	
}) ;
$("#god-input").on('keyup', godPrompt);
$("#god-input").on('click', godPrompt); 
$("#god-content-div").on('click',function(){
	//alert($(this).attr('dataurl'));
	//alert('aaa');
	  }  ); 

var god_index;
/*
function godPromptLazy(event){
	 return function(){
		 godPrompt(event);
    }
}*/

function godPrompt(event){
	var keycode = event.which||event.keyCode;
	if(keycode==38||keycode==40||keycode==13){
		return;
	}
	
	$("#god-div").removeClass('col-md-3').addClass('col-md-6');
	$("#head-sub-menu").hide();
	
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
				  $("#link-prompt-div").hide();
				 // $("#pageUrl").text(''); //清空url
				  
				  var linksJson = JSON.parse(linksData);
				  //alert(linksJson.length);
				  
				  for(var i=0;i<linksJson.length;i++){
					 // alert(linksJson[i].name);
					 // alert(linksJson[i].url);
					  $("#link-prompt-div").append('<div id="link_prompt_'+i+'" class="col-sm-12 link-prompt" style="padding: 5px; padding-left:10px; padding-top: 10px;  border: 1px solid #eee;" dataurl="'+linksJson[i].url+'"><span style="color: #2e8cd8;  font-weight: bold;;font-size: 14px;"> '+linksJson[i].name+'</span> - <span style="color:green;font-size: 11px;"> '+linksJson[i].urlShow+'</span></div>');
				  }
				  if(linksJson.length>0){
					  $("#link-prompt-div").show();
				  }
				
				  $(".link-prompt").click( linkPromptFunc);
				}
			}) ;
		
	}
	else{//小于1
		$("#link-prompt-div").empty();
		$("#link-prompt-div").hide();
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
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	if(confirm("确定要删除这条网址？")){
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
$(".editPage").click( function(event) { 
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	//alert( $(this).siblings('.pageMark').first().attr('data-id') );
	$("#editPage_dataid").val( $(this).parent().siblings('.pageMark').first().attr('data-id'));
	$("#editPage_pageName").val( $(this).parent().siblings('.pageMark').first().attr('data-name'));
	$("#editPage_url").val( $.trim($(this).parent().siblings('.pageMark').first().attr('href')) );
	$('.modal').modal('hide');
	$('#editPageModel').modal();
	
		/*
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
		*/
});

$("#foot-prompt-close").click( function() {
	$("#foot-prompt").hide();
	//$("#footer").show();
	
});


$("#user-operation-div").hover(
	function() { 
		setTimeout(function(){
			$("#user-operation-content").show();
		},300);
	}
	,
	function() { 
		setTimeout(function(){
			$("#user-operation-content").hide();
		},300);
	}
);

$("#foot-prompt-ok").click(function(){
	$.ajax({
		url : contextPath + "/user/quickLogin",
		method : "POST",
		data : {
			"quick-name" : $('#foot-prompt-username-input').val(),
			"quick-password" : $('#foot-prompt-password-input').val()
		}
	}).success(function(data) {

		if (data.indexOf("操作成功")>=0 ) {
			//alert("操作成功");
			if($('#contextPath').val() == ''){
				location.href =  '/' ;
			}
			else{
				location.href =  $('#contextPath').val()  ;
			}
		} else {
			alert(data);
		}
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
	});
});


$(".auth-create-btn").click(function(){
	$(".auth-create-btn").css("color","#222");
	$(this).css("color","#fff");
	$(".auth-create-btn").removeClass("btn-success");
	$(this).addClass("btn-success");
	$("#auth-create-value").val( $(this).attr("data-value") );
});

$(".ajax-reply").click(function(){
	if($(this).hasClass('ajax-reply-opening')){
		$(this).removeClass('ajax-reply-opening'); 
		$(this).text('回复');
		$(this).parent().children('.ajax-reply-div').eq(0).remove();
	}
	else{
		$('.ajax-reply-div').remove();
		$(this).addClass('ajax-reply-opening'); 
		$(this).text('收起回复');
		$(this).parent().append('<div class="ajax-reply-div" style="padding:20px;"><textarea class="col-sm-12 ajax-reply-textarea"></textarea><button class=" pull-right" style="margin-top:5px; height:25px;font-size:12px;padding-top:2px;padding-bottom:2px;">确定</button> </div>');	
	}
	
});



function copyFolder(){
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	$.ajax({
		url : contextPath + "/folder/copy",
		method : "POST",
		data : {
			"folderID" : $('#fid').val()
		}
	}).success(function(data) {

		if (data.indexOf("成功")>=0 ) {
			if($('#contextPath').val() == ''){
				alert("操作成功");
				location.href =  '/' ;
			}
			else{
				location.href =  $('#contextPath').val()  ;
			}
		} else {
			alert(data);
		}
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
	});
}


//设为首页
function SetHome(obj, vrl) {
	vrl = "http://webhezi.com/";
	try {
		obj.style.behavior = 'url(#default#homepage)';
		obj.setHomePage(vrl);
	} catch (e) {
		if (window.netscape) {
			try {
				netscape.security.PrivilegeManager
						.enablePrivilege("UniversalXPConnect");
			} catch (e) {
				alert("需要您手动设置！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
			}
			var prefs = Components.classes['@mozilla.org/preferences-service;1']
					.getService(Components.interfaces.nsIPrefBranch);
			prefs.setCharPref('browser.startup.homepage', vrl);
		} else {
			//alert("请检查您是否安装了以下软件——360安全卫士、金山卫士、QQ电脑管家、金山毒霸。如已安装，请参考软件中的浏览器设置主页的方法进行设置。");
			alert("您的浏览器不支持，请按照下面步骤操作：1.打开浏览器设置。2.点击设置网页。3.输入：" + vrl + "点击确定。");
		}
	}
}

// 加入收藏
function AddFavorite() {
 
	var sURL = window.location.href;

	var sTitle = "网址盒子";
	try {
		window.external.addFavorite(sURL, sTitle);
	} catch (e) {
		try {
			window.sidebar.addPanel(sTitle, sURL, "");
		} catch (e) {
			alert("需要您手动设置，请使用Ctrl+D进行添加");
		}
	}
}

function linkUp(id){
	$.ajax({
		url : contextPath + "/link/up",
		method : "POST",
		data : {
			"id" : id
		}
	}).success(function(){
		alert('操作成功');
	});
};
function pageUp(id){
	$.ajax({
		url : contextPath + "/page/up",
		method : "POST",
		data : {
			"id" : id
		}
	}).success(function(){
		alert('操作成功');
	});
};

$(".page-edit-div").hover( 
		function() { 
			$(this).children(".deletePage").show(500);
		}
		,
		function() { 
			$(this).children(".deletePage").hide(500);
		}
);
/*
$(".page-comment-div").hover(
		function() { 
			$(this).children(".pageComment").show();
		}
		,
		function() {
			$(this).children(".pageComment").hide(500);
		}
);
*/
$( document ).ready(function() {
	
	//$("#footer").hide();
	//$("#folder-index").children().slice(6).hide();
});

/*
$("#folder-index").hover( 
function() { 
	setTimeout(function(){
		$("#folder-index").children().slice(6).show();
	},500);
}
,
function() { 
	$(this).children().slice(6).hide();
}
);

*/

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

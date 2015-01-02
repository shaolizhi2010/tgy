jQuery.fn.setSelection = function(selectionStart, selectionEnd) {
    if(this.lengh == 0) return this;
    input = this[0];
 
    if (input.createTextRange) {
        var range = input.createTextRange();
        range.collapse(true);
        range.moveEnd('character', selectionEnd);
        range.moveStart('character', selectionStart);
        range.select();
    } else if (input.setSelectionRange) {
        input.focus();
        input.setSelectionRange(selectionStart, selectionEnd);
    }
 
    return this;
}

$(window).scroll(function(){
	if($(document).scrollTop()>100){
		$('#go-top').css('display','block');	
	}
	else {
		$('#go-top').css('display','none');	
	}
});
$(document).ready(function() {
	$("img").lazyload();
});

//鼠标经过 自动焦点
$(".hover-focus").hover( 
	function() { 
		$(this).focus();
	}
	,
	function() { 
	}
);

//回车执行某函数
$(".enterInput").keydown(
		 function(e){ 
			    if(!e) e = window.event;//火狐中是 window.event 
			    if((e.keyCode || e.which) == 13){ 
			    	e.preventDefault(); 
			    	var funcName = $(this).attr("data-func-name") ;
			    	eval(funcName + "()");
			    	//$(".enterBtn").eq(0).click(); 
			    } 
			} 
);

//-------- for head menu, begin---------
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
		} else {
			alert(data);
			//alert(data);
		}
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
		// $scope.status = status;
	});
};
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
//-------- for head menu, end---------
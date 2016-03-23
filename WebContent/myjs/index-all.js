$("#indexAll-show-tag").on('click', function(){
	//$(".indexAll-tab").removeClass("indexAll-tab-selected");
	$("#indexAll-firstLetters").hide();
	$("#indexAll-tags").toggle();
} );

$("#indexAll-show-firstLetter").on('click', function(){
	//$(".indexAll-tab").removeClass("indexAll-tab-selected");
	$("#indexAll-tags").hide();
	$("#indexAll-firstLetters").toggle();
} );

$("#create-share-link-btn").on('click', function(){
	//alert(  $('#share_pageUrl').val());
	//alert(  $('#tagName').val());
	//alert(  $('#type').val());
	
	if( !isNaN( $("#share_fulidou").val() )  ){
		if( parseInt($('#share_fulidou').val())   > 10 ){
			alert('最多10福利豆哦亲');
			return;
		}
		if( $('#share_pageName').val()=='' || $('#share_pageName').val().length<4){
			alert('标题长度需大于4');
			return;
		}
		if( $('#share_pageUrl').val()=='' || $('#share_pageUrl').val().length<10){
			alert('资源链接不正确');
			return;
		}
		
	}
	
	myAjax("/page/create/share", {
		url:$("#share_pageUrl").val(),
		name:$("#share_pageName").val(),
		comment:$("#share_pageComment").val(),
		tagName:$("#share_tagName").val(),
		fulidou:$("#share_fulidou").val(),
		type:$("#type").val()});
});

$(document).on('click','.extendable-showDetail',function(){
	 $(this).parent().html( $(this).parent().attr('data-bk') );
});


$(document).ready(function() {
 
	//for each
	$(".extendable" ).each(function(){
		var maxsize =80;
		var summeryText = $(this).text();
		if(summeryText.length > maxsize){
			$(this).attr("data-bk",summeryText)  ;
			
			$(this ).html( summeryText.substr(0,maxsize) );
			
			$(this).append( ' <a href="javascritp:void(0)" class="extendable-showDetail">[ 查看更多 ]</a>');
		}
	 
	});
	
});


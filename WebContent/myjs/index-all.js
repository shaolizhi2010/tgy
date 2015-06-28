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
	myAjax("/page/create/share", {url:$("#share_pageUrl").val(),name:$("#share_pageName").val(),tagName:$("#tagName").val(),type:$("#type").val()});
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


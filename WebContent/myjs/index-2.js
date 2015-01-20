var editingFlag = false;
var sortingFlag = false;

if($('#bookmarkEmptyFlag').val()=='true'){
	$(".add-link-div").show();
	$(".add-link").show();
}



$( ".sortable" ).sortable({
	opacity: 0.6, //设置拖动时候的透明度   
    revert: true, //缓冲效果   
    cursor: 'move', //拖动的时候鼠标样式  
    update: function(){  
    	 
        $.ajax(
    			{
    				url : $('#contextPath').val()+ "/sort/FoldersAndPages",
    				method : "POST",
    				data :  freshData(), //folders,//
    				dataType:'json'
    			}) ;
    }
});

function freshData(){
    var folders = [];
    //alert( $('#pages-all-container').children(".pages-all-subFolder").size() );;
    $('#pages-all-container').children(".pages-all-subFolder").each(function() {   
    	//alert( ) ;
    	var folder = {};
    	folder.folderID = $(this).children(".folderMark").eq(0).attr('dataid');
    	folder.pages=[];
    	
    	$(this).children(".pages-all-subFolder-pages").eq(0).children(".pages-all-subFolder-pages-page").each(function() { 
    		folder.pages.push( $(this).attr('dataid'));
    	});
    	
    	folders.push(folder);
    	//folders.push(this.title);   
    });   
    return JSON.stringify(folders);
}

$(".editPanelElement").click( function(event) {
	$("#editPanel").hide();
	$("#returnCommonPanel").show();
});
$("#returnCommonPanel").click( function(event) {
	$("#returnCommonPanel").hide();
	$("#editPanel").show();
	
	if(editingFlag){
		editingFlag = !editingFlag;
		$(".glyphicon-pencil").remove();
		$(".editable").removeClass('editing');
		$(".add-link-div").hide();
		$(".add-link").hide();
		$(".editable").unbind('click');//取消弹出编辑框，回复初始状态。
	}
	else if(sortingFlag){
		sortingFlag = !sortingFlag;
		//$(".glyphicon-resize-vertical").remove();
		//$(".glyphicon-resize-horizontal").remove();
		$(".info").html('');
		$(".info").hide();
		$(".editable").unbind('click');//取消弹出编辑框，回复初始状态。
		
		$("#pages-all-container").removeClass('sortable');
		$(".pages-all-subFolder-pages").removeClass('sortable');
	}
	
});

var preEditAll = function() {
	if(!($("#loginFlag").val()=='true')){
		preLoginFunction();
		return;
	}
	editingFlag = !editingFlag;
	if(editingFlag){
		$(".editable").append("<span class='glyphicon glyphicon-pencil' style='font-size:8px;'></span>");
		$(".editable").addClass('editing');
		
		$(".add-link-div").show();
		$(".add-link").show();
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
 
 
//			$(".glyphicon-resize-vertical").remove();
//			$(".glyphicon-resize-horizontal").remove();
//			$(".editable").unbind('click');//取消弹出编辑框，回复初始状态。
//			
//			$("#pages-all-container").removeClass('sortable');
//			$(".pages-all-subFolder-pages").removeClass('sortable');
 

	}

	// alert('edit');
};
var preSortAll = function() {
	if(!($("#loginFlag").val()=='true')){
		preLoginFunction();
		return;
	}
	sortingFlag = !sortingFlag;
	if(sortingFlag){
		//$(".folderMark").append("<span class='glyphicon  glyphicon-resize-vertical' style='font-size:8px;'></span>");
		//$(".pageMark").append("<span class='glyphicon  glyphicon-resize-horizontal' style='font-size:8px;'></span>");
		$(".info").html("拖动图标和链接可以改变排列顺序");
		$(".info").show();
		
		$(".editable").click( function(event) {
			event.preventDefault();
		});
		
		$("#pages-all-container").addClass('sortable');
		$(".pages-all-subFolder-pages").addClass('sortable');
		$(".sortable" ).sortable({
			opacity: 0.6, //设置拖动时候的透明度   
		    revert: true, //缓冲效果   
		    cursor: 'move', //拖动的时候鼠标样式  
		    update: function(){  
		    	 
		        $.ajax(
		    			{
		    				url : $('#contextPath').val()+ "/sort/FoldersAndPages",
		    				method : "POST",
		    				data :  freshData(), //folders,//
		    				dataType:'json'
		    			}) ;
		    }
		});
	}
//	else{
//		$(".glyphicon-resize-vertical").remove();
//		$(".glyphicon-resize-horizontal").remove();
//		$(".editable").unbind('click');//取消弹出编辑框，回复初始状态。
//		
//		$("#pages-all-container").removeClass('sortable');
//		$(".pages-all-subFolder-pages").removeClass('sortable');
// 
//		//$(".sortable").unbind('click');//取消sort，回复初始状态。
//	}

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

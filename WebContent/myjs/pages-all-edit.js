var editingFlag = false;
var preEditAll = function() {
	if(!($('#loginFlag').val()=='true')){
		preLoginFunction();
		return;
	}
	editingFlag = !editingFlag;
	if(editingFlag){
		$(".editable").append("<span class='glyphicon glyphicon-pencil  ' style='font-size:8px;'></span>");
		$(".editable").addClass('editing');
		$(".editing").click( function(event) {
			event.preventDefault();
			// alert($(this).attr('dataid'));
			if($(this).hasClass('folderMark')){
				
				var old = $(this);
				var oldTxt = old.text();
				var newInput = $("<input type='text' value='" + oldTxt + "'/>"); 
				
				old.html(newInput); 
				//old.setSelection(0,100);
				//$("#editFolder_dataid").val( $(this).attr('dataid'));
				//$("#editFolder_folderName").val(  $(this).attr('dataname') );
				//$('.modal').modal('hide');
				//$('#editFolderModel').modal();
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

function showReplyTextarea(event){
	//alert($(this).parent('div').parent('div').attr('class'));
	//alert($(this).parent('div').parent('div').find(".pageElement-reply-content").attr('class')); //.attr('class')
	
	var pageID = $(this).parent('div').parent('div').attr("id") ;
	$(this).parent('div').parent('div').find(".pageElement-reply-content").hide();
	$(this).parent('div').parent('div').find(".pageElement-reply-textarea-div").show();
	$(this).parent('div').parent('div').find(".pageElement-reply-textarea-div").empty();
	
	$(this).parent('div').parent('div').find(".pageElement-reply-textarea-div").
		append( '<textarea class="form-control page-reply-textarea enterInput hover-focus" id="textarea'+pageID+'" data-func-name="replyPageFunction"  rows="5"></textarea>'
				+'<a class="myBtn pull-right page-reply-cancel-btn" style="" >[取消]</a>'
				+'<a class="myBtn pull-right page-reply-submit-btn" style="" >[回复]</a>'
				 );
	/**/
	$(this).parent('div').parent('div').find(".pageElement-reply-textarea-div").find("textarea").focus();
	$("#replyToPageID").val( pageID );
	//$("#replyToDiscussID").val( $(this).closest(".discuss-element-msg").attr("data-discussID") );
	//$(".discuss-reply-textarea").eq(0).text('@'+$(this).closest(".discuss-element-msg").attr("data-fromUsername")+':');
	
}

//点击回复按钮
$(document).on('click','.pageElement-reply-show-btn',showReplyTextarea);
//$(document).on('click','.pageElement-reply-content',showReplyTextarea);


var replyPageFunction = function(){
	//回复
	
	//点击回车按钮，无法获取$(this)，所以用这个办法获取
	var  replyToPageID = $("#replyToPageID").val();
	if($('#textarea'+replyToPageID).val().length > 100){
		alert('最多100字哦亲');
		return;
	}
	//先给用户回馈，再提交数据
	$('#'+replyToPageID).find(".pageElement-reply-content").append('<br/>我 回复: '+$('#textarea'+replyToPageID).val()+'<br/>');
	$('#'+replyToPageID).find(".pageElement-reply-content").show();
	$('#'+replyToPageID).find(".pageElement-reply-textarea-div").hide();
	
	$.ajax(
			{
				url : $('#contextPath').val()
						+ "/reply/create",
				method : "POST",
				data : {
					"message" : $('#textarea'+replyToPageID).val() ,
					"replyToPageID" : replyToPageID
				}
			}).success(
			function(data) {
				if(data.indexOf("操作未成功")>=0 ){
					alert(data);
				}
				else{
					//elementGroupContainer.find(".discuss-reply-div").remove();
					//subElementContainer.append(data);
				}
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
			});
};

$(document).on('click','.page-reply-submit-btn',replyPageFunction);
$(document).on('click','.page-reply-cancel-btn',function(){
	$(this).parent('div').parent('div').find(".pageElement-reply-content").show();
	$(this).parent('div').parent('div').find(".pageElement-reply-textarea-div").hide();
});


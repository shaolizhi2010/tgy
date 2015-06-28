function showTopicSumaryTextarea(event){
	if(!($('#loginFlag').val()=='true')){
		preLoginFunction();
		return;
	}
	$("#topic-summary-container").append( '<div id="topic-summary-update-div"><textarea class="form-control enterInput hover-focus" id="topic-sumary-textarea" data-func-name="updateTopicSummaryFunction"  rows="3"></textarea>'
				+'<a class="myBtn pull-right topic-summary-cancel-btn" style="" >[取消]</a>'
				+'<a class="myBtn pull-right topic-summary-submit-btn" style="" >[发送]</a></div>'
				 );

	$("#topic-summary-container").find("textarea").focus();
}
function showTopicPicTextarea(event){
	if(!($('#loginFlag').val()=='true')){
		preLoginFunction();
		return;
	}
	$("#topic-pic-container").append( '<div id="topic-pic-update-div"><textarea class="form-control enterInput hover-focus" id="topic-pic-textarea" data-func-name="updateTopicPicFunction"  rows="2"></textarea>'
				+'<a class="myBtn pull-right topic-pic-cancel-btn" style="" >[取消]</a>'
				+'<a class="myBtn pull-right topic-pic-submit-btn" style="" >[发送]</a></div>'
				 );

	$("#topic-pic-container").find("textarea").focus();
}
//点击按钮
$(document).on('click','.topic-sumary-show-btn',showTopicSumaryTextarea);
$(document).on('click','.topic-pic-show-btn',showTopicPicTextarea);


var updateTopicSummaryFunction = function(){
	if($('#topic-sumary-textarea').val().length < 20){
		alert('最少20字哦亲');
		return;
	}
	
	if($('#topic-sumary-textarea').val().length > 1000){
		alert('最多1000字哦亲');
		return;
	}
	
	$.ajax(
			{
				url : $('#contextPath').val()
						+ "/topic/update",
				method : "POST",
				data : {
					"title":	$('#topicName').val() ,
					"sumary" : $('#topic-sumary-textarea').val()  
				}
			}).success(
			function(data) {
				if(data.indexOf("操作未成功")>=0 ){
					alert(data);
				}
				else{
					alert('操作成功');
					location.reload(true);
				}
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
			});
};

var updateTopicPicFunction = function(){
	
	$.ajax(
			{
				url : $('#contextPath').val()
						+ "/topic/update",
				method : "POST",
				data : {
					"title":	$('#topicName').val() ,
					
					"picUrl" : $('#topic-pic-textarea').val()
				}
			}).success(
			function(data) {
				if(data.indexOf("操作未成功")>=0 ){
					alert(data);
				}
				else{
					alert('操作成功');
					location.reload(true);
				}
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
			});
};
$(document).on('click','.topic-summary-submit-btn',updateTopicSummaryFunction);
$(document).on('click','.topic-summary-cancel-btn',function(){
	$("#topic-summary-update-div").remove( );
});

$(document).on('click','.topic-pic-submit-btn',updateTopicPicFunction);
$(document).on('click','.topic-pic-cancel-btn',function(){
	$("#topic-pic-update-div").remove( );
});




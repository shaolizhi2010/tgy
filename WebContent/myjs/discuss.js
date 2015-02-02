//发主贴
var createDiscussForAllFunction = function() {
	$.ajax(
			{
				url : $('#contextPath').val()
						+ "/discuss/all/create",
				method : "POST",
				data : {
					"message" : $('#createDiscussMessage').val() ,
					//"replyToDiscussID" : $('#replyToDiscussID').val(),
					//"sourceBoardType" : $('#sourceBoardType').val(),
					"sourceBoardName" : $('#sourceBoardName').val()
				}
			}).success(
			function(data) {
				if(data.indexOf("操作未成功")>=0 ){
					alert(data);
				}
				else{
					$("#all-discuss-container").append(data);
					$("#all-discuss-container").find('img').lazyload();
					$("#createDiscussMessage").val('');
				}
				
				
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
				// $scope.status = status;
			});
};

 
//点击 翻页 1 2 3 尾页等
$(document).on('click','.discuss-pg-num',function(){
	var start = $(this).attr('data-pageNum');
	 
	$("#discuss-container").load($('#contextPath').val() + "/part/discuss/all-discuss.jsp?discussStart="+start,function(){ //discuss-element
		
//		$.getScript($('#contextPath').val()+'/myjs/discuss.js',function(){
//		});
		
		//写在页面的 onclick 属性里。
		$("#discuss-container").find('img').lazyload();
	});
});


//点击 翻页 1 2 3 尾页等
$(document).on('click','.subDiscussPagination-num',function(){
	var start = $(this).attr('data-pageNum');
	var discussElementGroupContainer = $(this).closest(".discuss-element-group");
	var primaryDiscussID = discussElementGroupContainer.attr('data-discussID');
	
	discussElementGroupContainer.find(".discuss-subElement-group").eq(0).load(
		$('#contextPath').val() +"/part/discuss/discuss-subElement-group.jsp?primaryDiscussID="+primaryDiscussID+"&count="+10+"&subDiscussStart="+start,
		function(){
			discussElementGroupContainer.find('img').lazyload();
			//discussElementGroupContainer.find(".discuss-showAll-container").eq(0).hide();
			//discussElementGroupContainer.find(".subDiscussPagination").eq(0).show();
		}
	);
});


//点击‘显示全部’按钮
$(document).on('click','.discuss-showAll-btn',function(){ 
	var discussElementGroupContainer = $(this).closest(".discuss-element-group");
	var primaryDiscussID = discussElementGroupContainer.attr('data-discussID');
	//alert(primaryDiscussID);
	//discussElementGroupContainer.find(".discuss-showAll-container").eq(0).hide();
	//discussElementGroupContainer.find(".subDiscussPagination").eq(0).show();
	
	discussElementGroupContainer.find(".discuss-element-sub-container").eq(0).load(
		$('#contextPath').val() +"/part/discuss/discuss-subElement-group.jsp?primaryDiscussID="+primaryDiscussID+"&count="+10,
		function(){
			discussElementGroupContainer.find('img').lazyload();
			//discussElementGroupContainer.find(".discuss-showAll-container").eq(0).hide();
			//discussElementGroupContainer.find(".subDiscussPagination").eq(0).show();
		}
	);
});

var replyDiscussFunction = function(){
	//回复
	
	//点击回车按钮，无法获取$(this)，所以用这个办法获取
	var  replyToDiscussID = $("#replyToDiscussID").val();
	var elementGroupContainer = $("#"+replyToDiscussID).closest(".discuss-element-group");
	
	//var elementGroupContainer = $(this).closest(".discuss-element-group");
	var subElementContainer = elementGroupContainer.find(".discuss-element-sub-list").eq(0);
	$.ajax(
			{
				url : $('#contextPath').val()
						+ "/discuss/all/create",
				method : "POST",
				data : {
					"message" : $('.discuss-reply-textarea').val() ,
					"replyToDiscussID" : replyToDiscussID,
					"sourceBoardName" : $('#sourceBoardName').val()
				}
			}).success(
			function(data) {
				if(data.indexOf("操作未成功")>=0 ){
					alert(data);
				}
				else{
					elementGroupContainer.find(".discuss-reply-div").remove();
					subElementContainer.append(data);
					subElementContainer.find('img').lazyload();
				}
				
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
				// $scope.status = status;
			});
};
$(document).on('click','.discuss-reply-submit-btn',replyDiscussFunction);

//点击回复按钮
$(document).on('click','.discuss-reply-show-btn',function(event){
	$(".discuss-reply-div").remove();
	$(this).closest(".discuss-element-msg").
		append('<div class="col-sm-12 discuss-reply-div">'
				+'<textarea class="form-control discuss-reply-textarea enterInput hover-focus" data-func-name="replyDiscussFunction"  rows="1"></textarea>'
				+'<a class="myBtn pull-right discuss-reply-submit-btn" style="" >发送</a>'
				+'</div>');
	$(".discuss-reply-textarea").eq(0).focus();
	$("#replyToDiscussID").val( $(this).closest(".discuss-element-msg").attr("data-discussID") );
	//$(".discuss-reply-textarea").eq(0).text('@'+$(this).closest(".discuss-element-msg").attr("data-fromUsername")+':');
	
});
/*
$(document).on('hover','.discuss-element-primary').on('hover',
function(){
	//alert($(this));
	$(this).find(".discuss-reply-show-btn:gt(0)").show();
}
,
function(){
	$(this).find(".discuss-reply-show-btn:gt(0)").hide();
}
);
*/

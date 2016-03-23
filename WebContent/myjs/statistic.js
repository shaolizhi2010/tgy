var statistic = function(id,type) {
	$.ajax({
		url : $('#contextPath').val() + "/statistic/click",
		method : "POST",
		data : {
			"id" : id,
			"type" : type
		}
	});
};

$(document).on('click','.statistic-tag',function(){
	
	var id = $(this).attr('data-id');
	var type = 'tag';
	
	statistic(id,type);
});
 
$(document).on('click','.statistic-page',function(){
	var id = $(this).attr('data-id');
	var type = 'page';
	statistic(id,type);
});

$(document).on('click','.statistic-topic',function(){
	var id = $(this).attr('data-id');
	var type = 'topic';
	statistic(id,type);
});
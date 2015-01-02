var openLink = function(id,type) {
	$.ajax({
		url : $('contextPath').val()+ "/statistic/click",
		method : "POST",
		data : {
			
			"id" : id,
			"type" : type
			
		}
	});
};
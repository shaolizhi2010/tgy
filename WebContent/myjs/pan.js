function panSearch(){
	location.href =  $('#contextPath').val()+'/pan/'+$('#pan_search_value').val();
}

var createDiscussForSearchFunction = function() {
	$.ajax(
			{
				url : $('#contextPath').val()
						+ "/discuss/search/create",
				method : "POST",
				data : {
					"message" : $('#createDiscussMessage')
							.val() 
				}
			}).success(
			function(data) {
				if(data.indexOf("操作成功")>=0 ){
					location.reload(true);
				}
				else{
					alert(data);
				}
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
				// $scope.status = status;
			});
}
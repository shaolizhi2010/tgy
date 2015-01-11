function createGroupFunction(){
	if(!($('#loginFlag').val()=='true')){
		preLoginFunction();
		return;
	}
	$.ajax(
			{
				url :  $('#contextPath').val()
						+ "/g/create",
				method : "POST",
				data : {
					"name" : $('#group-create-name')
							.val()
				}
		}).success(
			function(data ) {
				if(data.indexOf("操作成功")>=0 ){
					location = $('#contextPath').val()+"/group/group.index.jsp" ;
					//location.reload(true);
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
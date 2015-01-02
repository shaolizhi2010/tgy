var createGroupDiscussFunction = function() {
	$.ajax(
			{
				url : contextPath
						+ "/g/discuss/create",
				method : "POST",
				data : {
					"groupID":$('#groupID').val(),
					"msg" : $('#createGroupDiscussMessage')
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

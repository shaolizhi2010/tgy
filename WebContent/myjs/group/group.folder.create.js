function createFolderForGroupFunction(){
 
	$.ajax(
			{
				url : $('#contextPath').val()
						+ "/group/folder/create",
				method : "POST",
				data : {
					"name" : $('#group_view_folderName')
							.val(),
					"groupID" : $('#groupID')
					.val()
							
				}
		}).success(
			function(data ) {
				if(data.indexOf("操作成功")>=0 ){
					location = $('#contextPath').val()+"/g/"+$('#groupID').val();
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
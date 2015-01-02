 function createPageFunction() {
	//if(!(loginFlag=='true')){
	//	preLoginFunction();
	//	return;
	//}
	$.ajax({
		url : $('#contextPath').val() + "/group/page/create",
		method : "POST",
		data : {
			"url" : $('#pageUrl').val(),
			"name" : $('#pageName').val(),
			"folderID":$('#folderID').val(),
			"groupID":$('#groupID').val()
		}
	}).success(function(data) {
		if(data.indexOf("操作成功")>=0 ){
			location.href = $('#contextPath').val()+'/g/'+$('#groupID').val()  ;//TODO
		}
		else{
			alert(data);
		}
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
		// $scope.status = status;
	});
	// alert('ng add');
};

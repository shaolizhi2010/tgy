
var quickAddPageFunction = function( url) {
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	
	if(typeof url != 'undefined' && url.length>0){
		$('#quick_pageUrl').val(url);
	}
	
	$.ajax({
		url : contextPath + "/page/create",
		method : "POST",
		data : {
			// "bookmarkId":
			// $('#createPage_bookmarkId').val(),
			// "folderId": $('#createPage_folderId').val(),
			"url" : $('#quick_pageUrl').val(),
			pid : $('#showFolderID').val()
			
		}
	}).success(function(data) {
		if(data.indexOf("操作成功")>=0 ){
			location.href = $('#contextPath').val()+'/u/'+$('#userID').val()  ;//TODO
		}
		else{
			//alert(JSON.stringify(data));
			alert(data);
		}
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
		// $scope.status = status;
	});
	// alert('ng add');
};
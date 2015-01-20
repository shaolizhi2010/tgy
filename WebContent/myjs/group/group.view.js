
 function joinGroupFunction() {
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	
	$.ajax({
		url : contextPath + "/g/join",
		method : "POST",
		data : {
			"groupID" : $('#groupID').val()
		}
	}).success(function(data) {
		if(data.indexOf("操作成功")>=0 ){
			location.reload()   ;//TODO
		}
		else{
			//alert(JSON.stringify(data));
			alert(data);
		}
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
	});
};


function copyGroupFolder(){
	if(!(loginFlag=='true')){
		preLoginFunction();
		return;
	}
	$.ajax({
		url : contextPath + "/group/folder/copy",
		method : "POST",
		data : {
			"folderID" : $('#showFolderID').val()
		}
	}).success(function(data) {

		if (data.indexOf("成功")>=0 ) {
			if($('#contextPath').val() == ''){
				alert("操作成功");
				location.href =  '/' ;
			}
			else{
				location.href =  $('#contextPath').val()  ;
			}
		} else {
			alert(data);
		}
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
	});
}

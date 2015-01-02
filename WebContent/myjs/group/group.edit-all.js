//点击编辑按钮触发事件，给所有被编辑element加铅笔图标，并改变链接为编辑页面的链接
var preEditAll = function() {
	editingFlag = !editingFlag;
	if(editingFlag){
		$(".editable").append("<span class='glyphicon glyphicon-pencil' style='font-size:8px;'></span>");
		$(".editable").addClass('editing');
		$(".editing").click( function(event) {
			event.preventDefault();
			location =  $(this).attr('data-href-edit');
		});
	}
	else{
		$(".glyphicon-pencil").remove();
		$(".editable").removeClass('editing');
		$(".editable").unbind('click');//取消弹出编辑框，回复初始状态。
	}
};

//编辑收藏夹
var editGroupFolderFunction = function() {
	$.ajax(
			{
				url : $("#contextPath").val()
						+ "/group/folder/edit",
				method : "POST",
				data : {
					"folderID":$("#folderID").val(),
					"folderName" : $('#folderName').val() 
				}
			}).success(
			function(data) {
				if(data.indexOf("操作成功")>=0 ){
					location = $('#contextPath').val()+"/g/"+$('#groupID').val();
				}
				else{
					alert(data);
				}
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
				// $scope.status = status;
			});

};		
//删除收藏夹
var deleteGroupFolderFunction = function() {
	$.ajax(
			{
				url : contextPath
						+ "/group/folder/delete",
				method : "POST",
				data : {
					"id":$("#editFolder_dataid").val()
				}
			}).success(
			function(data) {
				if(data.indexOf("操作成功")>=0 ){
					//删除的是当前查看的folder，则跳回主页
					if(fid == $("#editFolder_dataid").val()){
						location = contextPath;
					}
					else{
						location.reload(true);
					}
					
				}
				else{
					alert(data);
				}
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
				// $scope.status = status;
			});
};



//编辑网址信息
var editGroupPageFunction = function() {
	$.ajax(
			{
				url : $("#contextPath").val()
						+ "/group/page/edit",
				method : "POST",
				data : {
					"pageID":$("#pageID").val(),
					"pageName" : $('#pageName').val(),
					"pageUrl" : $('#pageUrl').val() 
				}
			}).success(
			function(data) {
				if(data.indexOf("操作成功")>=0 ){
					location = $('#contextPath').val()+"/g/"+$('#groupID').val();
				}
				else{
					alert(data);
				}
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
				// $scope.status = status;
			});

};	

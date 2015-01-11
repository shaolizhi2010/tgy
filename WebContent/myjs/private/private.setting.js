var setting = function() {

	if($('#editUser-password').val() != $('#editUser-password-again').val()){
		alert("两次输入的用户名不一致,请重新输入");
		return;
	}
	
	$.ajax({
		url : $('#contextPath').val() + "/private/setting",
		method : "POST",
		data : {
			"publicMessage":$('#publicMessage').val(),
			"email" : $('#email').val(),
			"password" : $('#editUser-password').val(),
			"authQuery":$('#auth-query').val(),
			"headImgUrl":$('#headImgUrl').val()
		}
	}).success(function(data) {
		$('#editUserModel').modal('hide');
		if(data.indexOf("操作成功")>=0 ){
			alert("操作成功");
			location.reload(true);
		}
		else{
			alert(data);
		}
		// $scope.data = data;
	}).error(function(data) {
		alert('服务器正在飞速运转，请耐心等待' + data);
		// $scope.status = status;
	});

};

$(".auth-query-btn").click(function(){
	$(".auth-query-btn").css("color","#222");
	$(this).css("color","#fff");
	$(".auth-query-btn").removeClass("btn-success");
	$(this).addClass("btn-success");
	$("#auth-query").val( $(this).attr("data-value") );
});
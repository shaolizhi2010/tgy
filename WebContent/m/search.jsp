<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title></title>
<link href="css/jquery.mobile-1.4.5.min.css"
	rel="stylesheet">

</head>
<body>

	<div id="login-main" data-role="page">

		<div data-role="header" data-position="fixed" style="height:56px;font-size:20px; background-color: #e52f17;color: #fbe7e5;font-weight: normal;font-family:  Helvetica">
			<h1>搜索资源</h1>
		</div>

		<div data-role="content">

			<div style="text-align: center;min-height: 80px;">
			 
			</div>

			<input type="text" id="name" name="name" value="" placeholder="资源名"> 
			 <input id="defaultBtn"
				type="button" value="搜索">
		</div>

		<jsp:include page="footer.jsp"></jsp:include>

	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.mobile-1.4.5.min.js"></script>
<script src="js/common.js"></script>

	<script type="text/javascript">
		$(function() {
			$.mobile.defaultPageTransition = 'slide'
		});
		
		$(document).on('click',"#defaultBtn", function(e){
			//alert(baseUrl + "/carService/technician/login");
			
			$.ajax(
					{
						url : baseUrl + "/carService/technician/login",
						method : "POST",
						data : {"loginName":$('#loginName').val(),"password":$('#password').val()}
					}).success(
						function(data) {
							if(data.code==200){
							//	alert("code 200");
								window.localStorage.technicianName = $('#loginName').val(); //global var in common.js
								window.localStorage.technicianId =  data.data.technicianId; //global var in common.js
								window.localStorage.pass =  $('#password').val(); //global var in common.js
								//huanxinInit(); //init 环信
								//connect($('#loginName').val(), $('#password').val()); //login 环信
								location.href = baseUrl +'/jishi/inquiry-all.jsp';
							}
							else{
								alert( data.message);	
							}
							
					}).error(
						function(data) {
							alert('error');
							alert(JSON.stringify(data));
						 
					});
		 
		} );
		
	</script>
</body>
</html>
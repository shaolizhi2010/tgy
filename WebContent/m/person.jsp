<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%
String technicialID = "";
	if(request.getSession().getAttribute("userID")!=null){
		technicialID =  String.valueOf( request.getSession().getAttribute("userID")) ;
	}
	 
%>
	
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title></title>
<link href="<%=request.getContextPath()%>/jquery.mobile-1.4.5.min.css"
	rel="stylesheet">
	
<script src="js/common.js"></script>

</head>
<body>

	<div id="person-main" data-role="page">
		
<div data-role="header" data-position="fixed" style="height:56px;font-size:20px; background-color: #e52f17;color: #fbe7e5;font-weight: normal;font-family: Helvetica">
			<h1>个人信息</h1>
		</div>

		<div data-role="content">

			<div style="text-align: center;min-height: 80px;">
				<img style=" "
					src="<%=request.getContextPath()%>/images/logo/logo2.png">
			</div>
			
			<input type="text" id="nickname"   value="" placeholder="您的称呼"> 
			<input type="text" id="workingYears"   value="" placeholder="您的工龄">
			<input type="text" id="nativePlace"   value="" placeholder="籍贯">
			<input type="text" id="introduction"   value="" placeholder="简单自我介绍">
		 
				
			<input id="defaultBtn"
				type="button" value="更新">
				
			<input id="logoutBtn"
				type="button" value="登出">
				

		</div>

		<jsp:include page="footer.jsp"></jsp:include>

	</div>




	<script src="<%=request.getContextPath()%>/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/jquery.mobile-1.4.5.min.js"></script>


	<script type="text/javascript">
		$(function() {
			$.mobile.defaultPageTransition = 'slide';
		});
		
	
		$(document).ready(function(){
			
 
			$.ajax(
					{
						url : baseUrl + "/carService/technician/get",
						method : "POST",
						data : {
								"id": localStorage.technicianId
								}
					}).success(
						function(data) {
							if(data.code==200){
								$('#nickname').val(data.data.nickname);
								$('#introduction').val(data.data.introduction);
								$('#workingYears').val(data.data.workingYears);
								$('#nativePlace').val(data.data.nativeplace);
								
								
								//alert(data.data.loginname);
							}
							else{
								//alert( data.message   );
								location.href = baseUrl +'/jishi/login.jsp';
									
							}
							
							 
					}).error(
						function(data) {
							//alert(JSON.stringify(data));
						 
					});
			} );
	 /*
		
		$(document).bind('pageinit',  function(e){
			
			
		});*/
		
	//回车执行某函数
	$(document).on('click',"#defaultBtn", function(e){
		
		
		$.ajax(
				{
					url : baseUrl + "/carService/technician/update",
					method : "POST",
					data : {
							"nickname":$('#nickname').val(),
							"introduction":$('#introduction').val(),
							"workingYears":$('#workingYears').val(),
							"nativePlace":$('#nativePlace').val(),
							"id": localStorage.technicianId
							}
				}).success(
					function(data) {
						if(data.code==200){
							alert('更新成功');
						}
						else{
							alert( data.message   );	
							location.href = baseUrl +'/jishi/login.jsp';
						}
						
						 
				}).error(
					function(data) {
						alert(JSON.stringify(data));
					 
				});
	 
	} );
		
		$(document).on('click',"#logoutBtn", function(e){
			window.localStorage.technicianName = ''; //global var in common.js
			window.localStorage.technicianId =  ''; //global var in common.js
			window.localStorage.pass = ''; //global var in common.js
			location.href = baseUrl +'/jishi/login.jsp';
			
		} );
		
	</script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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

	<div id="inquiry-technician-main" data-role="page">
		
		<div data-role="header" data-position="fixed" style="height:56px;font-size:20px; background-color: #e52f17;color: #fbe7e5;font-weight: normal;font-family: Helvetica">
			<h1>问诊列表</h1>
		</div>
		
		<div data-role="content">
			
		 <ul id="inquiryList" data-role="listview">
		 	 
		 	 
		 </ul>

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
						url : baseUrl + "/carService/inquiry/listByTechnician",
						method : "POST",
						data : { 
							technicianID: localStorage.technicianId							
						}
					}).success(
						function(data) {
							if(data.code==200){
								
								$.each( data.data, function(index, content){
									$('#inquiryList').append('<li><a data-ajax="false" class="ui-btn ui-btn-icon-right ui-icon-carat-r" href="chat.jsp?inquiryId='+ content.repairInquiryId +'&customerId='+ content.customerId +'&tname=&uname='+content.uname+' "> '+ content.content +' </a> </li>');
									//alert( "item #" + index + " its value is: " + JSON.stringify(content)  );  
								}); 
								
								//'<li><a href="chat.jsp?inquiryId='+  +'">  汽车熄火了</a> </li>'
								
								//$('#nickname').val(data.data.nickname);
								//$('#introduction').val(data.data.introduction);
							//	$('#workingYears').val(data.data.workingYears);
								
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
		
		
	</script>
</body>
</html>
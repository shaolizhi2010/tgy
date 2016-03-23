<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
	<%
	String inquiryId = request.getParameter("inquiryId");
	String customerId = request.getParameter("customerId");
	String uname = request.getParameter("uname");
	
	%>
	
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title></title>
<link href="<%=request.getContextPath()%>/jquery.mobile-1.4.5.min.css"
	rel="stylesheet">


	<style>
		.weixin-msg-text-user{
			-moz-border-radius: 4px;
		   -webkit-border-radius: 4px;
		   border-radius: 4px;
		  background-color: #fff;
		  padding: 12px;
		  padding-left:15px;
		  padding-right:15px;
		  max-width: 80%;
		  margin-top: 20px;
		  float: left;
		  }
		  
		  .weixin-msg-text-me{
			-moz-border-radius: 4px;
		   -webkit-border-radius: 4px;
		   border-radius: 4px;
		  background-color: #a2e759;
		  color:#333;
		  padding: 12px;
		  padding-left:15px;
		  padding-right:15px;
		  max-width: 80%;
		  margin-top: 20px;
		  float: right;
		  }
		  
	 

</style>

</head>
<body>

	<div id="chat-main" data-role="page">

		<div data-role="header" data-position="fixed" style="height:56px;font-size:20px; background-color: #e52f17;color: #fbe7e5;font-weight: normal;font-family: Helvetica">
			<a data-rel="back" style="margin-top: 10px;">后退</a>
			<h1>修车问诊</h1>
		</div>
		
				
			 

		<div data-role="content">
			<div id="chatList">
 			</div>
	</div>
	
	 <div data-role="footer" data-position="fixed" >
		 	<fieldset class="ui-grid-a">
			    <div class="ui-block-a" style="width: 80%;">
			    	<input id="chatContent"  type="text" name="name" value="">
			    </div>
			    <div class="ui-block-b" style="width: 20%;">
			    	<div style="height: 4px; "></div>
			    	<input id="sendMsgBtn"  type="button" value="发送">
			    </div>
			</fieldset>
		 </div>
	
	</div>


	<script src="<%=request.getContextPath()%>/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/jquery.mobile-1.4.5.min.js"></script>
			<script   src="<%=request.getContextPath()%>/js/json2.js"></script>
	<script   src="<%=request.getContextPath()%>/js/strophe-custom-2.0.1.js"></script>
	<script  src="<%=request.getContextPath()%>/js/easemob.im-1.0.7.js"></script>
<script src="js/common.js"></script>

	<script type="text/javascript">
		$(function() {
			$.mobile.defaultPageTransition = 'slide';
		});
		
		$(document).on('click',"#sendMsgBtn", function(e){
			
			var chatContent = 	 $('#chatContent').val();
			
			sendTextMessage('<%=uname%>',chatContent);
			
			$('#chatList').append('<div   class="weixin-msg-text-me">'+ chatContent +'</div>');
			$('#chatList').append('<div style="width:100%;float:right;height:10px;"></div>');
			
			
			//ajax
			$.ajax(
					{  //http://localhost:8080/carService/inquiry/chat/create?technicianId=2&content=测试&customerID=1&imgSrc=&parentInquiryId=3&voicePath=
						url : baseUrl + "/carService/inquiry/chat/create",
						method : "POST",
						data : { 
							parentInquiryId:'<%=inquiryId%>' ,
							technicianId:localStorage.technicianId,
							content:chatContent,
							customerID:'<%=customerId%>',
							fromType:1
							}
					}).success(
						function(data) {
							if(data.code==200){
								 
							}
							else{
								alert( data.message );	
							}
							
							 
					}).error(
						function(data) {
							//alert(JSON.stringify(data));
						 
					});
			//ajax end
			
			$('#chatContent').val('');
			
		} ); //ready end
		
$(document).ready(function(){
			
			$.ajax(
					{
						url : baseUrl + "/carService/inquiry/chat/technician/history",
						method : "POST",
						data : { 
							inquiryId:'<%=inquiryId%>',
							technicalID:localStorage.technicianId	
							}
					}).success(
						function(data) {
							if(data.code==200){
								$.each( data.data, function(index, content){ 
									if( content.fromType ==1 ){
										$('#chatList').append('<div data-icon="arrow-r" class="weixin-msg-text-me pull-right">'+ content.content +'</div> <div style="width:100%;float:left;height:10px;"></div><br>');
									}else{
										$('#chatList').append('<div class="weixin-msg-text-user">'+ content.content +'</div><div style="width:100%;float:right;height:10px;"></div>');
									}
								}); 
							}
							else{
								//alert( data.message   );
								location.href = baseUrl +'/jishi/login.jsp';
							}
							
							 
					}).error(
						function(data) {
							//alert(JSON.stringify(data));
						 
					});
			
			
			
			huanxinInit(); //init 环信
			connect(localStorage.technicianName, localStorage.pass); //login 环信
			
			localStorage.currentchatTo = '<%=uname%>';
			
			
				
			} );
		
	</script>
	
</body>
</html>
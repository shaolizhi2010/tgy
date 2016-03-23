<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.entity.Topic"%>
<%@page import="com.tgy.util.ConditionMap"%>
<%@page import="com.tgy.service.TopicService"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.service.cache.AppCache"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
	<%
	
	List<Topic> topics = new ArrayList<Topic>();
	
	topics.addAll( AppCache.topics());  
	
	topics.addAll( AppCache.hot10Topics() );  
	
	%>
	
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title></title>
<link href="css/jquery.mobile-1.4.5.min.css"
	rel="stylesheet">

<script src="js/common.js"></script>
</head>
<body>

	<div id="inquiry-all-main" data-role="page">
		
		<div data-role="header" data-position="fixed" style="height:56px;font-size:20px; background-color: #e52f17;color: #fbe7e5;font-weight: normal;font-family: Helvetica">
			<h1>问诊列表</h1>
		</div>
		
		<div data-role="content">
		 <ul id="inquiryList" data-role="listview">
		 	<%
		 	for(Topic t:  topics){
		 		%>
		 		<li><a data-ajax="false" style="font-weight: normal;" class="ui-btn ui-btn-icon-right ui-icon-carat-r" href="#"><%= t.title  %></a>
		 		<%  
		 	}
		 	%>
		 </ul>
		</div>

		<jsp:include page="footer.jsp"></jsp:include>

	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.mobile-1.4.5.min.js"></script>


	<script type="text/javascript">
		$(function() {
			$.mobile.defaultPageTransition = 'slide';
		});
		
		var startIndex=1;
		$(document).scroll(function() {
			
			//alert(baseUrl);
			
			//alert( $(window).scrollTop() );
			   if($(window).scrollTop() + $(window).height() > $(document).height() - 100) {
				   
				   $.ajax(
							{
								url : '' + "<%=request.getContextPath()%>/topic/list",
								method : "POST",
								data : {'start':startIndex++ }
							}).success(
								function(data) {
										//alert(data);
										
										$.each( JSON.parse(data), function(index, content){ //index, content
											//alert(index);
											//alert(content);
											$('#inquiryList').append('<li><a data-ajax="false" class="ui-btn ui-btn-icon-right ui-icon-carat-r" href="chat.jsp?inquiryId='+ content.parentTopicID +'&customerId='+ content.parentTopicID +'&tname=&uname='+content.parentTopicID+' "> '+ content.title +' </a> </li>');
											//alert( "item #" + index + " its value is: " + JSON.stringify(content)  );  
										}); 
										
										
							}).error(
								function(data) {
									alert('error');
									alert(JSON.stringify(data));
								 
							});
			       
			   }
			});
		$(document).ready(function(){
			 
			} );
		
		/*
		$(document).bind('pageinit',  function(e){
		
		});*/
		
		
	</script>
</body>
</html>
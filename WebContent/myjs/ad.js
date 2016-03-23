$(document).ready(function() {
		$(".ad-960-60").load($('#contextPath').val() + "/part/ad/ad-baidu-960-60.jsp",function(){ //discuss-element
			 alert('ad loaded' + $('#contextPath').val() + "/part/ad/ad-baidu-960-60.jsp");
		});
});
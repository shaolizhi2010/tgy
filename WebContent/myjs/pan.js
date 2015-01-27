$( document ).ready(function() {
	 
	if($('#keyword').val()!=''){
		$('#search-pagination').show();
	}
	else{
		$('#search-pagination').hide();
	}
	
	$.ajax(
		{
			url : $('#contextPath').val()
					+ "/search/history/top",
			method : "POST",
			data : {
			}
		}
	).success(
		function(data) {
			var dataJson = JSON.parse(data);
			  var contextPath = $('#contextPath').val();
			  for(var i=0;i<dataJson.length;i++){
				 // alert(dataJson[i].keyword);
				  $("#searchHistroy").append('<a  href="'+contextPath+'/pan/'+dataJson[i].keyword+'" style=" "> <span class="hoverAble-red" style="padding:5px;">'+dataJson[i].keyword+'</span></a>');
			  }
		}
	)
		
	 
});

$('.pages-part-page-name').hover(
	function(){
		$(this).parent().parent().children('.pages-part-page-comment').eq(0).show();
	}
	,
	function(){
		$(this).parent().parent().children('.pages-part-page-comment').eq(0).hide();
	}
);


function panSearch(){
	location.href =  $('#contextPath').val()+'/pan/'+$('#pan_search_value').val();
}

var createDiscussForSearchFunction = function() {
	$.ajax(
			{
				url : $('#contextPath').val()
						+ "/discuss/search/create",
				method : "POST",
				data : {
					"message" : $('#createDiscussMessage')
							.val() 
				}
			}).success(
			function(data) {
				if(data.indexOf("操作成功")>=0 ){
					location.reload(true);
				}
				else{
					alert(data);
				}
			}).error(
			function(data) {
				alert('服务器正在飞速运转，请耐心等待' + data);
				// $scope.status = status;
			});
}
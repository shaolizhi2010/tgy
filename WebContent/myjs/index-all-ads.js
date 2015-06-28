$( document ).ready(function() {
//	 alert($("#tagName").val());
//	 alert($("#contextPath").val());
	var tagName = '网购发现';
	if($("#tagName").val()!=''  ){
		tagName = $("#tagName").val();
	}
	
	
	$('#ad_item_template').load(
			 $('#contextPath').val() + '/commodity/ad',{  "tagName": tagName , "count":1 },
			function(){
				// $('#ad_item_template').find('img').lazyload();
				//discussElementGroupContainer.find(".discuss-showAll-container").eq(0).hide();
				//discussElementGroupContainer.find(".subDiscussPagination").eq(0).show();
			}
		);
	
	/*
		$.ajax({
			 url:  $('#contextPath').val()
				+ '/commodity/ad',	
			 dataType:'json',
			 data:{  "tagName": tagName , "count":1 },
			 async: true,
			 success:function(data){
				 
//				 alert('in ads');
//				 alert(data);
				 
				 showProducts(data);

				 
			 }
		 }) ;
	 */
	
});

var currentItemIndex =0;

function showProducts(products) { 
	 
	$template = $("#ad_item_template").clone(true);
	
	for ( var p in products) {
		
		
		currentItemIndex++;
		
		
		$newItem = $template.clone(true);
		
		setProduct($newItem, products[p]);
		
		$newItem.find("input").val(JSON.stringify(products[p]));
		
		//show on page
		
		$("#pages-part-elements").append($newItem);
		//$("#indexAll-slide-ads").append($newItem);
		//if(currentItemIndex%4 == 0){
		//	$("#search_result").append('<div class="clear-fix-hidden"></div>');
		//}

	}//end for
	
}

function setProduct(item, p){
	
	item.find(".ajax-img").attr('src',
			p.imgSrc);
	item.find(".ajax-title").text(p.name);
	item.find(".ajax-href").attr('href',
			p.url);
	item.find(".ajax-href").attr('data-id',
			p.idStr);
 
	item.find(".ajax-summary").text( 
			p.summary.substr(0,200));
	
	  
}

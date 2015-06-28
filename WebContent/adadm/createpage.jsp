<%@page import="org.apache.commons.lang3.EnumUtils"%>
<%@page import="com.tgy.entity.Tag"%>
<%@page import="com.tgy.statistic.service.TagService"%>
<%@page import="org.apache.commons.lang3.math.NumberUtils"%>
<%@page import="com.tgy.util.PageType"%>
<%@page import="java.util.Random"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
 
<jsp:include page="../part/importAtHead.jsp" /> 
 
<body>
<%@include file="../part/common.jsp" %>


	<div id="create-share-link" class="col-sm-12  ">
		<div  class="col-md-10    " style="padding-left: 0px;" >
			<input id="share_pageUrl"  class="form-control enterInput"  
				style="border-radius:5px;border:1px solid #ddd;height: 40px;" placeholder="输入要共享的网址"  type="text" value=""/>
		</div>
		
		<div  class="col-md-10    " style="padding-left: 0px;" >
			<input id="share_pageName"  class="form-control enterInput"  
				style="border-radius:5px;border:1px solid #ddd;height: 40px;" placeholder="输入要共享的网页名称"  type="text" value=""/>
		</div>
		
		<div  class="col-md-10    " style="padding-left: 0px;" >
			<input id="share_pageComment"  class="form-control enterInput"  
				style="border-radius:5px;border:1px solid #ddd;height: 40px;" placeholder="输入要共享的网页的描述"  type="text" value=""/>
		</div>
		
		<div  class="col-md-10    " style="padding-left: 0px;" >
			<input id="share_tagName"  class="form-control enterInput"  
				style="border-radius:5px;border:1px solid #ddd;height: 40px;" placeholder="输入要共享的网页的标签"  type="text" value=""/>
		</div>
		
		<div  class="col-md-10    " style="padding-left: 0px;" >
			<input id="share_type"  class="form-control enterInput"  
				style="border-radius:5px;border:1px solid #ddd;height: 40px;" placeholder="输入要共享的网页的类别"  type="text" value=""/>
		</div>
		
		<div  class="col-md-10    " style="padding-left: 0px;" >
			<input id="share_imgUrl"  class="form-control enterInput"  
				style="border-radius:5px;border:1px solid #ddd;height: 40px;" placeholder="输入要共享的网页的图片网址"  type="text" value=""/>
		</div>
		<br>
		<div class="col-sm-4  " style="padding-left: 0px;" >
			<input id="create-share-link-btn" class="btn btn-primary col-sm-12" style="height: 40px;border-radius:5px;"  
				 type="button" value="发布">
		</div>
	</div>
	
	<script src="<%=request.getContextPath()%>/myjs/common.js"  ></script>

<script>

$("#create-share-link-btn").on('click', function(){
	//alert(  $('#share_pageUrl').val());
	//alert(  $('#tagName').val());
	//alert(  $('#type').val());
	myAjax("/page/create/share", {
		url:$("#share_pageUrl").val(),
		name:$("#share_pageName").val(),
		comment:$("#share_pageComment").val(),
		tagName:$("#share_tagName").val(),
		type:$("#share_type").val(),
		imgSrc:$("#share_imgUrl").val()	
	});
});

</script>

</body>

</html>
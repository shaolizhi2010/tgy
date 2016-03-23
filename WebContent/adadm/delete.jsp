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
				style="border-radius:5px;border:1px solid #ddd;height: 40px;" placeholder="输入 网址"  type="text" value=""/>
		</div>
	 
		<div class="col-sm-4  " style="padding-left: 0px;" >
			<input id="delete-share-link-btn" class="btn btn-primary col-sm-12" style="height: 40px;border-radius:5px;"  
				 type="button" value="删除" >
		</div>
	</div>
	
	<script src="<%=request.getContextPath()%>/myjs/common.js"  ></script>

<script>

$("#delete-share-link-btn").on('click', function(){
 
	myAjax("/page/delete", {
		url:$("#share_pageUrl").val() 
	});
});

</script>

</body>

</html>
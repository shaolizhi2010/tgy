<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<style>
#mask {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	z-index: 999;
	background-color: rgba(0, 0, 0, 0.5);
}

#mask img {
	position: absolute;
	top: 0;
	
}
</style>
<link href="<%= request.getContextPath()%>/stylesheets/common.css" rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/stylesheets/bootstrap.min.css"
	rel="stylesheet">
	<script
		src="<%=request.getContextPath()%>/javascripts/bootstrap.min.js"
		defer="defer"></script>

	<script src="http://www.webhezi.com/javascripts/jquery-1.11.1.min.js"></script>
</head>
<body>
	
	<div id="logo" class="col-sm-12 no-padding">
		<a href="/" title="网址收藏 网站分享"><span style="font-size:60px;" >网盘盒子</span> </a>
	</div> 
	
	<div class="col-sm-12" style="border-bottom: 1px solid #eee; margin-bottom: 60px;"></div>

	<h3 class="col-sm-12" style="color: #666;"><img style="height: 60px;width: 60px;" src="<%=request.getContextPath() %>/images/android-logo.jpg"/> Android 下载 : </h3>
	
	<a class="download-link col-sm-12" onclick="showMask()" style="margin-top: 20px;"
		href="http://www.webhezi.com/app/webhezi.apk " target="_blank">
			<input class="btn btn-primary  " style=" border-radius:3px;font-size: 26px;font-weight: bold; padding:50px; padding-top: 20px;padding-bottom: 20px;" 
					 type="button" value="Android 下载"/> 
 	</a>


	<div id="mask" class="col-sm-12 no-padding" onclick="hideMask()">
		 
		<img class="col-sm-12 no-padding img-responsive" src="http://www.webhezi.com/images/weixin_bg.png" />
	</div>

	<script>
		function showMask() {
			//alert('show mask');
			var ua = navigator.userAgent.toLowerCase();
			if (ua.match(/MicroMessenger/i) == "micromessenger") {
				$("#mask").css('display', 'block');
			}
		}

		function hideMask() {
			$("#mask").css('display', 'none');
		}
	</script>

<jsp:include page="../part/foot.jsp" />

</body>
</html>

<%@ page language="java" pageEncoding="UTF-8"%>
<div class="modal-dialog draggable">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">
				<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="modal-title" id="myModalLabel" style="font-weight: bold;">登录网址盒子<a onclick="preAddUserFunction()" href="#" style="margin-left: 20px;font-size: 14px;">注册网址盒子</a></h4>
			
		</div>
		<div class="modal-body col-sm-12 container" style="background-color: #fff;padding: 10px;">
			
			<div class="col-sm-6 container" style="padding: 20px;">
				<div class="col-sm-12"  style="background-color: #f4f4f4;min-height: 220px;border-radius:5px;">
					<div class="col-sm-12" style="margin-top: 40px;display: block;">
						<span style="color: #99d233;" class="glyphicon glyphicon-ok-sign"> </span>
						<span  style=" font-size: 13px;font-weight:bold; color: #3997f8;">登录网址盒子</span>
					</div>
					<div class="col-sm-12" style="margin-top: 10px;display: block;"> 
						<span style="color: #99d233;" class="glyphicon glyphicon-ok-sign"> </span>
						<span class=" " style=" font-size: 11px;color: #666; ">在线 收藏喜欢的网址</span>
					</div>	
					<div class="col-sm-12" style="margin-top: 10px;display: block;"> 
						<span style="color: #99d233;" class="glyphicon glyphicon-ok-sign"> </span>
						<span class=" " style=" font-size: 11px;color: #666; ">设置自己的上网导航</span>
					</div>		
					<div class="col-sm-12" style="margin-top: 10px;display: block;">
						<span style="color: #99d233;" class="glyphicon glyphicon-ok-sign"></span>
						<span class=" " style=" font-size: 11px;color: #666; ">和朋友们分享</span>
					</div>
				</div>
			</div>
			
			<!-- login user begin-->
			<div class="col-sm-6 container">
				<div style="margin-top: 20px;">
					 <input   class="form-control" style="height: 43px;"
						id="login-name" type="text" placeholder="用户名" />
				</div>
				<div style="margin-top: 20px;">
					<input    class="form-control" style="height: 40px;"
						id="login-password" type="password" placeholder="密码"/>
				</div>
	
				<div style="margin-top: 20px;">
					<a href="#" onclick="loginFunction()" >
						<span class="col-sm-12 " id="login-ok"  
						 style="height:45px;line-height:45px;text-align:center;border-radius:5px; background-color: #3a84e6;color: #fff;font-size: 15px;font-weight: bold;">登录</span>
					</a>
					<a style="padding-top: 8px;float:right;display: block; " href="https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101171952&redirect_uri=http://www.webhezi.com/qqcallback.jsp&state=test">
						<img alt="使用QQ帐号登录" src="<%=request.getContextPath() %>/images/qqlogin.png">
					</a>
				</div>
			</div>
			<!-- login page end-->

		</div>

	</div>
</div>
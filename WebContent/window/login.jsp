<%@ page language="java" pageEncoding="UTF-8"%>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">
				<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">用户登录<a onclick="preAddUserFunction()" href="#" style="margin-left: 20px;font-size: 14px;">用户注册</a></h4>
			
		</div>
		<div class="modal-body">

			<!-- create user begin-->

			<div style="margin-top: 20px;">
				<label for="login-name">用户名</label><input ng-keyup="$event.keyCode == 13 ? loginFunction() : null"  class="form-control"
					id="login-name" type="text" />
			</div>
			<div style="margin-top: 20px;">
				<label for="login-password">密码</label><input  ng-keyup="$event.keyCode == 13 ? loginFunction() : null" class="form-control"
					id="login-password" type="password" />
			</div>

			<div style="margin-top: 20px;">
				<button onclick="loginFunction()" id="login-ok" type="button"
					class="btn btn-primary">确定</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<a style="padding-top: 8px;float:right;display: block; " href="https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=101171952&redirect_uri=http://www.webhezi.com/qqcallback.jsp&state=test">
					<img alt="使用QQ帐号登录" src="<%=request.getContextPath() %>/images/qqlogin.png">
				</a>
			</div>

			<!-- create page end-->

		</div>

	</div>
</div>
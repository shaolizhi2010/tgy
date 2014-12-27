<%@ page language="java" pageEncoding="UTF-8"%>
 <div class="modal-dialog  ">
    <div class="modal-content  ">
        <div class="modal-header ">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">用户注册<a onclick="preLoginFunction()" href="#" style="margin-left: 20px;font-size: 14px;">用户登录</a></h4>
        </div>
        <div class="modal-body ">
            <!-- create user begin-->
            <div class=" " style="margin-top: 20px;" >
                <label for="createUser-name">用户名</label><input class="form-control"
                                                                           id="createUser-name"
                                                                           type="text"/>
            </div>
            <div class=" " style="margin-top: 20px;" >
                <label for="createUser-password">密码</label><input class="form-control" id="createUser-password"
                                                          type="password"/>
            </div>
            <div class=" " style="margin-top: 20px;"  >
                <label for="createUser-password-again">确认密码</label>
                <input ng-keyup="$event.keyCode == 13 ? addUserFunction() : null"
                        class="form-control" id="createUser-password-again" type="password"/>
            </div>
            <div class="  " style="margin-top: 20px;" >
                <button onclick="addUserFunction()" id="createUser-ok" type="button"
                        class="btn btn-primary ">确定
                </button>
                <!-- 
                	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                 -->
                 <a onclick="showAdditionalDiv()" class="pull-right" href="#">>>详细设置</a>
            </div> 
			<div id="createUser-additionalDiv" class="additionalDiv" style="display:none; margin-top: 20px;border:2px solid #eee;border-radius:5px;padding: 10px;" >
				<div class="">
					<span style="font-size: 14px;color: #666;">谁能添加新的收藏夹或网址：</span>
					<div class="btn-group ">
						<a type="button"   data-value="0" href="#" class="btn btn-default btn-success auth-create-btn" style=" color:#fff;" >只有自己</a>
						<a type="button"  data-value="9" href="#" class="btn btn-default auth-create-btn" style=" ">所有人</a>
						<input type="hidden" id="auth-create-value">
					</div>
				</div>
				
			</div>

            <!-- create page end-->

        </div>

    </div>
</div>
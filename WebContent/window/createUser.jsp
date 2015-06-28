<%@ page language="java" pageEncoding="UTF-8"%>
 <div class="modal-dialog  ">
    <div class="modal-content  ">
        <div class="modal-header "  >
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel" style="font-weight: bold;">注册网址盒子<a onclick="preLoginFunction()" href="#" style="margin-left: 20px;font-size: 14px;">登录网址盒子</a></h4>
        </div>
        <div class="modal-body col-sm-12 container" style="background-color: #fff;padding: 10px;">
        	
        	<div class="col-sm-6 container" style="padding: 20px;">
				<div class="col-sm-12"  style="background-color: #f4f4f4;min-height: 240px;border-radius:5px;">
					
					<div class="col-sm-12" style="margin-top: 40px;display: block;"> 
						<span style="color: #99d233;" class="glyphicon glyphicon-ok-sign"> </span>
						<span  style=" font-size: 13px;font-weight:bold; color: #3997f8;">注册网盘盒子</span>
					</div>
					<div class="col-sm-12" style="margin-top: 10px;display: block;"> 
						<span style="color: #99d233;" class="glyphicon glyphicon-ok-sign">好资源大家一起分享</span>
						<span class=" " style=" font-size: 11px;color: #666; "></span>
					</div>	
					<div class="col-sm-12" style="margin-top: 10px;display: block;"> 
						<span style="color: #99d233;" class="glyphicon glyphicon-ok-sign"> </span>
						<span class=" " style=" font-size: 11px;color: #666; ">发布自己收藏的网盘</span>
					</div>		
					<div class="col-sm-12" style="margin-top: 10px;display: block;">
						<span style="color: #99d233;" class="glyphicon glyphicon-ok-sign"></span>
						<span class=" " style=" font-size: 11px;color: #666; ">发帖回帖赚福利豆</span>
					</div>
				</div>
			</div>
            <!-- create user begin-->
            <div class="col-sm-6 container">
	            <div class=" " style="margin-top: 20px;" >
	                <input class="form-control" id="createUser-name" style="height: 43px;" type="text" placeholder="用户名"/>
	            </div>
	            <div class=" " style="margin-top: 20px;" >
	                <input class="form-control" id="createUser-password" style="height: 40px;" placeholder="密码"
	                                                          type="password"/>
	            </div>
	            <div class=" " style="margin-top: 20px;"  >
	                <input  style="height: 40px;" placeholder="确认密码"
	                		data-func-name="addUserFunction"
	                        class="form-control enterInput" id="createUser-password-again" type="password"/>
	            </div>
	            <div class="  " style="margin-top: 20px;" >
	            	<a href="#" onclick="addUserFunction()" >
						<span class="col-sm-12 " id="createUser-ok"  
						 style="height:45px;line-height:45px;text-align:center;border-radius:5px; background-color: #3a84e6;color: #fff;font-size: 15px;font-weight: bold;">注册</span>
					</a>
	            </div> 
			</div>
			<!-- create page end-->
        </div>

    </div>
</div>
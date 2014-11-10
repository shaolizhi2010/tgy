<%@ page language="java" pageEncoding="UTF-8"%>
 <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">用户注册</h4>
        </div>
        <div class="modal-body">

            <!-- create user begin-->

            <div style="margin-top: 20px;" >
                <label for="createUser-name">用户名</label><input class="form-control"
                                                                           id="createUser-name"
                                                                           type="text"/>
            </div>
            <div  style="margin-top: 20px;" >
                <label for="createUser-password">密码</label><input class="form-control" id="createUser-password"
                                                          type="password"/>
            </div>
            <div style="margin-top: 20px;"  >
                <label for="createUser-password-again">确认密码</label>
                <input ng-keyup="$event.keyCode == 13 ? addUserFunction() : null"
                       class="form-control" id="createUser-password-again" type="password"/>
            </div>
            <div  style="margin-top: 20px;" >
                <button ng-click="addUserFunction()" id="createUser-ok" type="button"
                        class="btn btn-primary">确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>


            <!-- create page end-->

        </div>

    </div>
</div>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="../part/user-data.jsp"%>
<%

 
%>

 <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">修改资料</h4>
        </div>
        <div class="modal-body">

            <!-- create user begin-->
	
			<input type="hidden" id="editUser-id" value="<%=loginUserID %>">
	
            <div style="margin-top: 20px;" >
                <label for="editUser-name">用户名</label>
                <input class="form-control"   id="editUser-name" type="text" value="<%=loginUserName %>"/>
            </div>
            <div  style="margin-top: 20px;" >
                <label for="editUser-password">密码</label>
                <input class="form-control" id="editUser-password"   placeholder="不填 则不更改密码"
                                                          type="password"/>
            </div>
            <div style="margin-top: 20px;"  >
                <label for="editUser-password-again">确认密码</label>
                <input ng-keyup="$event.keyCode == 13 ? editUserFunction() : null" placeholder="不填 则不更改密码"
                       class="form-control" id="editUser-password-again" type="password"/>
            </div>
            <div  style="margin-top: 20px;" >
                <button ng-click="editUserFunction()" id="editUser-ok" type="button"
                        class="btn btn-primary">确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>


            <!-- create page end-->

        </div>

    </div>
</div>
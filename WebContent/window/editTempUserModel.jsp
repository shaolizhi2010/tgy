<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="../part/user-data.jsp"%>
<%


%>

 <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">免费注册</h4>
        </div>
        <div class="modal-body">
			
            <!-- create user begin-->
			
			<input type="hidden" id="editTempUser-id" value="<%=loginUserID %>">
			
            <div style="margin-top: 20px;" >
                <label for="editTempUser-name">用户名</label>
                <input class="form-control"   id="editTempUser-name" type="text" value=""/>
            </div>
            <div  style="margin-top: 20px;" >
                <label for="editTempUser-password">密码</label>
                <input class="form-control" id="editTempUser-password"   placeholder=""
                                                          type="password"/>
            </div>
            <div style="margin-top: 20px;"  >
                <label for="editTempUser-password-again">确认密码</label>
                <input ng-keyup="$event.keyCode == 13 ? editTempUserFunction() : null" placeholder=""
                       class="form-control" id="editTempUser-password-again" type="password"/>
            </div>
            <div  style="margin-top: 20px;" >
                <button onclick="editTempUserFunction()" id="editTempUser-ok" type="button"
                        class="btn btn-primary">确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>


            <!-- create page end-->

        </div>

    </div>
</div>
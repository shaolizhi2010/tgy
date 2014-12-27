<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="../part/user-data.jsp"%>
<%

 
%>

 <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">修改公告信息</h4>
        </div>
        <div class="modal-body">

            <!-- create user begin-->
	
			<input type="hidden" id="publicMessageUserID" value="<%=showUserID %>">
			
			<span style="font-size: 10px;color: #666;">公告信息将显示在页面的上方 较明显的位置</span>
            <div style="margin-top: 20px;" >
                <textarea  id="publicMessageValue"  class="form-control  " rows="3"
                	placeholder=""><%= showUser!=null&&StringUtils.isNotBlank(showUser.publicMessage)? showUser.publicMessage:"" %></textarea>
            </div>
            
            <div  style="margin-top: 20px;" >
                <button onclick="editPublicMessageFunction()"   type="button"
                        class="btn btn-primary">确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>

            <!-- create page end-->

        </div>

    </div>
</div>
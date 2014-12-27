<%@ page language="java" pageEncoding="UTF-8"%>
<div class="modal-dialog modal-sm">
    <div class="modal-content">
        <div class="modal-body">
        
        	
        	
            <!-- create commont begin-->
            <input type="hidden" id="createlinkComment_targetID">
            <div  style="margin-top: 5px;" >
            	<textarea   id="createlinkCommentMessage"  class="form-control" rows="3" placeholder="随便说说"></textarea>            </div>
            <div  style="margin-top: 5px;" >
                <button onclick="createCommentForLinkFunction()" id="createFolder-ok" type="button"
                        class="btn btn-primary ">发表
                </button>
                <button type="button" class="btn btn-default pull-right" data-dismiss="modal">取消</button>
            </div>
            <!-- create folder end-->
        </div>
    </div>
</div>
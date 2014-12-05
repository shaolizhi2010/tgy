<%@ page language="java" pageEncoding="UTF-8"%>


<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">编辑分类</h4>
        </div>
        <div class="modal-body">

            <!-- edit folder begin-->
			<input type="hidden" id="editFolder_dataid" value="">
            <div  style="margin-top: 20px;" >
                <label for="editFolder_folderName">名称</label><input class="form-control"  ng-keyup="$event.keyCode == 13 ? editFolderFunction() : null" id="editFolder_folderName"
                                                          type="text"/>
            </div>

            <div  style="margin-top: 20px;" >
                <button ng-click="editFolderFunction()" id="editFolder-ok" type="button"
                        class="btn btn-primary">确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                 <button ng-click="deleteFolderFunction()"  type="button" class="btn btn-danger pull-right"  ><span style="font-size: bold;">删除</span></button>
            </div>


            <!-- edit folder end-->

        </div>

    </div>
</div>
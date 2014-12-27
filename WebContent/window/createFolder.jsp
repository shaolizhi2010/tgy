<%@ page language="java" pageEncoding="UTF-8"%>


<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">添加</h4>
        </div>
        <div class="modal-body">

            <!-- create folder begin-->

            <div  style="margin-top: 20px;" >
                <label for="createFolder_folderName">收藏夹名称</label><input class="form-control hover-focus"  ng-keyup="$event.keyCode == 13 ? createFolderFunction() : null" id="createFolder_folderName"
                                                          type="text"/>
            </div>

            <div  style="margin-top: 20px;" >
                <button onclick="createFolderFunction()" id="createFolder-ok" type="button"
                        class="btn btn-primary">添加
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>


            <!-- create folder end-->

        </div>

    </div>
</div>
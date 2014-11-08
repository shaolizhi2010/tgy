<%@ page language="java" pageEncoding="UTF-8"%>


<div class="modal-dialog"><!--  modal-lg -->
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">添加收藏夹</h4>
        </div>
        <div class="modal-body">

            <!-- create folder begin-->

            <input type="hidden" id="createRootFolder_parentFolderId"/>

 
            <div  style="margin-top: 20px;" >
                <label for="createRootFolder_folderName">名称</label><input class="form-control"  ng-keyup="$event.keyCode == 13 ? createRootFolderFunction() : null" id="createRootFolder_folderName"
                                                          type="text"/>
            </div>

            <div  style="margin-top: 20px;" >
                <button ng-click="createRootFolderFunction()" id="createRootFolder-ok" type="button"
                        class="btn btn-primary">添加
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>


            <!-- create folder end-->

        </div>

    </div>
</div>
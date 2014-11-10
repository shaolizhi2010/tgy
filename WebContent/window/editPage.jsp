<%@ page language="java" pageEncoding="UTF-8"%>


<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
            <h4 class="modal-title" id="myModalLabel">编辑</h4>
        </div>
        <div class="modal-body">

            <!-- edit page begin-->
			<input type="hidden" id="editPage_dataid" value="">
            <div  style="margin-top: 20px;" >
                <label for="editPage_pageName">名称</label><input class="form-control"  ng-keyup="$event.keyCode == 13 ? editPageFunction() : null" id="editPage_pageName"
                                                          type="text"/>
            </div>
            <div  style="margin-top: 20px;" >
                <label for="editPage_url">网址</label><input class="form-control"  ng-keyup="$event.keyCode == 13 ? editPageFunction() : null" id="editPage_url"
                                                          type="text"/>
            </div>
            <div  style="margin-top: 20px;" >
                <button ng-click="editPageFunction()" id="editPage-ok" type="button"
                        class="btn btn-primary">确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button ng-click="deletePageFunction()"  type="button" class="btn btn-danger pull-right"  ><span style="font-size: bold;">删除</span></button>
            </div>


            <!-- edit page end-->

        </div>

    </div>
</div>
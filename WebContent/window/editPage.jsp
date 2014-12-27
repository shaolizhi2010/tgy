<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="../part/bookmark-data.jsp" %> 
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
 			<div style="margin-top: 20px;">
				<label for="pageUrl">分类</label> 
				<a href="#"  onclick="preCreateFolderFunction()" ><span style="font-size: 12px;line-height: 14px;">创建新分类</span> </a>
			</div>
			<div class="">
			   <select class="  form-control"   id="editPage_pid">
			   <option value="<%=showFolderID%>"  ><%=showFolderName%></option>
			   <%
			   for(Folder f: rootFolders){
				   String selected = "";
				   if(StringUtils.equals(showFolderID, String.valueOf(f.id ))){
					   selected = "selected='selected'";
				   }
				   %>
				   <option value="<%=f.id%>" <%=selected %> ><%=f.name%></option>
				   <%
			   }
			   %>
			    </select>
			</div>
			
             <div  style="margin-top: 20px;" >
                <label for="editPage_url">网址</label><input class="form-control"  ng-keyup="$event.keyCode == 13 ? editPageFunction() : null" id="editPage_url"
                                                          type="text"/>
            </div>
 
            <div  style="margin-top: 20px;" >
                <label for="editPage_pageName">名称</label><input class="form-control"  ng-keyup="$event.keyCode == 13 ? editPageFunction() : null" id="editPage_pageName"
                                                          type="text"/>
            </div>

            <div  style="margin-top: 20px;" >
                <button onclick="editPageFunction()" id="editPage-ok" type="button"
                        class="btn btn-primary">确定
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button onclick="deletePageFunction()"  type="button" class="btn btn-danger pull-right"  ><span style="font-size: bold;">删除</span></button>
            </div>


            <!-- edit page end-->

        </div>

    </div>
</div>
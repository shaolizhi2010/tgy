<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="../part/bookmark-data.jsp" %> 
 

<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">
				<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">添加网址</h4>
		</div>
		<div class="modal-body">
			
			<!-- create page begin -->
			<input type="hidden" id="createPage_folderId" /> <input type="hidden"
				id="createPage_bookmarkId" />
			
			<div style="margin-top: 20px;">
				<label for="pageUrl">分类</label> 
				<a href="#"  ng-click="preCreateFolderFunction()" ><span style="font-size: 12px;line-height: 14px;">创建新分类</span> </a>
			</div>
			<div class="">
			   <select class="  form-control"   id="createPage_pid">
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
			
			<div style="margin-top: 20px;">
				<label for="pageUrl">网址</label>
					
					<textarea    id="pageUrl"  class="form-control hover-focus" rows="3"></textarea>
			</div>
			<div style="margin-top: 20px;" id="createPage-name-div">
				<label for="pageName">网站名称</label> <input placeholder="不填则自动获取"
					ng-keyup="$event.keyCode == 13 ? createPageFunction() : null"
					class="form-control  " id="pageName" type="text" />
			</div>
			<input type="hidden" id="link_prompt_index" value='-1'>
			<div id="link-prompt-div" class="col-sm-12" style="margin-bottom: 10px;padding-right: 5px;" >
			
			</div>
			<div style="margin-top: 20px;">
				<label for="pageName">备注</label> 
				<textarea   id="pageDescription"  class="form-control  " rows="3"></textarea>
			</div>
			
			<div style="margin-top: 20px;">
				<button ng-click="createPageFunction()" id="createPage-ok"
					type="button" class="btn btn-primary">添加网址</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</div>


			<!-- create page end-->

		</div>

	</div>
</div>
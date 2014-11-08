<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!-- 显示收藏夹开始 -->
<div class="col-sm-12" style="padding: 0px;padding-top: 15px;">

	<%
		Folder rootFolder = U.param(request, "rootFolder", Folder.class);
		String rid = U.paramString(request.getSession(), "rid");
	
		if (rootFolder != null && !CollectionUtils.isEmpty(rootFolder.pages)) {
	%>
	<a href="<%=request.getContextPath()%>/folder/?fid=<%=rid %>" class="  btn  "
		style="  text-align: left;  ">
		<span class="glyphicon glyphicon-book" style="color:red"></span>
		<div class="label " style="color: #2e8cd8;  font-size: 15px;padding:5px;  padding-left:10px;padding-right:15px; ">未分类</div> 
	</a>
  
	<%
		}
	%>

	<%
		List<Folder> folders = U.paramList(request, "folders");
		for (Folder folder : folders) {
			String name = folder.name;
			if (name != null && name.length() > 4) {
	//			name = name.substring(0, 4) + "..";
			}
	%>

	<a href="<%=request.getContextPath()%>/folder/?fid=<%=folder.id%>&name=<%=name %>"
		dataid="<%=folder.id%>" dataname="<%=folder.name%>" class=" btn  folderMark editable "
		 style="text-align: left;margin:0px;">
		<span class="glyphicon glyphicon-book" style="color:<%=folder.color%>"></span>
		<div class="label" style="color: #2e8cd8;  font-size: 16px; padding:5px;  padding-left:10px;padding-right:15px; " ><%=name%></div>
	</a>
 
	<%
		} 
	%>


</div>
<!-- 显示收藏夹结束 -->
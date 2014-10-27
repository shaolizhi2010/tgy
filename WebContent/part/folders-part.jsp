<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!-- 显示收藏夹开始 -->
<div class="col-sm-12" style="padding: 0px; ">
	<%
		List<Folder> folders = U.paramList(request, "folders");
		for (Folder folder : folders) {
			String name = folder.name;
			if (name != null && name.length() > 4) {
				name = name.substring(0, 4) + "..";
			}
	%>


	<a href="<%=request.getContextPath()%>/folder/?fid=<%=folder.id%>"
		dataid="<%=folder.id%>" class="col-md-3 btn  folderMark editable "
		style="border:1px solid #eee; text-align:left; color: #000000;">
		<span class="glyphicon glyphicon-folder-open"
		style="font-size:10px; color: orange;  margin-right: 5px; font-weight: bold;"></span> <%=name%>
	</a>

	<%
		} 
	%>


</div>
<!-- 显示收藏夹结束 -->
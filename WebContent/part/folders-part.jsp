<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>


<%
	List<Folder> folders = U.paramList(request, "folders");
	for (Folder folder : folders) {
		String name = folder.name;
		if(name!=null && name.length()>10){
			name = name.substring(0,10)+"..";
		}
%>


<a pre-href="" href="<%=request.getContextPath() %>/folder/?fid=<%=folder.id %>"
	class="  col-md-2  " style="padding-top:5px;padding-bottom:5px; color: #000000;"> <span
	class="glyphicon glyphicon-folder-open"
	style="color: orange; margin-right: 5px; font-weight: bold;"></span> <%=name%>
</a>

<%
	}
%>



<%@page import="com.tgy.statistic.entity.Tag"%>
<%@page import="com.tgy.statistic.service.TagService"%>
<%@page import="com.tgy.dao.FolderDao"%>
<%@page import="com.tgy.util.URLUtils"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>
<%@page language="java" pageEncoding="UTF-8"%>

<%@include file="show-user-data.jsp"  %>  
<%@include file="bookmark-data.jsp"%>
 
<!-- folder index 开始 -->
<div id="history-folder-part" class="col-sm-12 no-padding sub-page-with-title" >
	<div class="sub-page-title">
		<span>分类</span>
	</div>
	<div class="sub-page-body">
	<%
 	TagService ts = new TagService();
 	List<Tag> tags = ts.list(40);
 	 	for (Tag tag : tags) {
 	 		String tagName = tag.name;
 			if (tagName != null && tagName.length() > 12) {
 				tagName = tagName.substring(0, 12) + "..";
 			}
 	 		%> 
 	 		<a class="col-sm-12 history-folder-element hoverAble" href="<%=request.getContextPath()%>/folder/<%=tag.id%>/<%=tag.name %>" >
 	 			<span style=" "><%=tagName %></span> 
 	 			<span class="col-sm-1 pull-right glyphicon glyphicon glyphicon-plus" style="font-size: 8px;" title="拷贝到我的收藏夹"></span>
 	 			<br>
 	 			<span style="color:#222;font-size: 10px;font-weight: normal;"><%=U.dateTimeWithOutYear(tag.createDate) %></span>
 	 		</a>
 	 		
 	 		<%
 	 	}
 	%>
	</div>
</div>
<!-- folder index结束 -->
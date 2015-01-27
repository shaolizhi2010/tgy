<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Collections"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.statistic.service.LinkService"%>
<%@page import="com.tgy.service.PageService"%>
<%@page import="com.tgy.App"%>
<%@page import="com.tgy.util.URLUtils"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>

<%@include file="group-data.jsp"  %> 
<%
 
 
%>
<!-- 显示网址页面开始 -->
<div id="pages-part" class="col-sm-12 no-padding sub-page-with-title">
	<div class="col-sm-12 container sub-page-title  no-padding">
		<span class="col-sm-5"><%=showFolderName %></span>
		<div class="col-sm-3 pull-right" style="  padding-top: 3px; ">
		<%
		if(StringUtils.isNotBlank(showFolderID)){
			%>
			<%
			if(true){//TODO
				%>
				<span id="copy-folder-btn" class="col-sm-6 btn pull-right sub-page-title-btn  hoverAble2" style="padding:8px; font-size: 13px;border: 1px solid #ccc;border-radius:10px;height: 32px;" title="一键复制 ' ' 中的所有网址(<%=pages.size() %>个),到自己的收藏夹" onclick="copyGroupFolder()">一键复制</span>
				<%
			}
			%>
			<%
		}
		if(createAble){
			%>
			<a href="<%=request.getContextPath() %>/group/page/create/pre?groupID=<%=groupID%>&&folderID=<%=showFolderID %>"  class="col-sm-6 btn  pull-right sub-page-title-btn hoverAble2 warning" style="padding:8px; font-size: 13px;border: 1px solid #ccc;border-radius:10px;  height: 32px; " title="添加一个网址"  >添加网址</a>
			<%
		}
		%>
		</div>
	</div>
	<div class="sub-page-body col-sm-12 no-padding" style="background: #fff;">
	<%
	if(CollectionUtils.isEmpty(pages)){
 		%>
 		<div class="col-sm-12 ">
 			还未收藏任何网址，现在就 <a onclick="afterLogin('<%=request.getContextPath() %>/group/page/create/pre?groupID=<%=groupID %>&&folderID=<%=showFolderID %>')" href="#"  >添加一个 </a> 吧
 		</div>
 		<%
 	}
 	else{
		Collections.sort(pages);
		Collections.reverse(pages);
		for (InterestGroupPage p : pages) {
			%>
			<jsp:include page="element/articleElement.jsp">
				<jsp:param name="pageID" value='<%=p.id.toString() %>'/>
				<jsp:param name="groupID" value='<%=groupID %>'/>
				<jsp:param name="editAble" value='<%=editAble %>'/>
			</jsp:include>
			<%
		}//end for
	}//end else
	%>
	</div>
</div>
<!-- 显示网址页面结束 -->
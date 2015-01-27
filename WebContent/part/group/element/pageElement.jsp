<%@page import="org.apache.commons.lang3.BooleanUtils"%>
<%@page import="com.tgy.util.PageUtil"%>
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

<%
Page p ;
String pageID = request.getParameter("pageID");
if(StringUtils.isBlank(pageID)){
	return ;
}
 
PageService ps = new PageService();
p = ps.byID(pageID); 
 
if(p==null){
	return ;
}

boolean editAble = BooleanUtils.toBoolean(request.getParameter("editAble")) ;
String groupID = request.getParameter("groupID");

Link link = new LinkService().getByUrl(p.url);
%>

<%
			String linkStr =PageUtil.urlWithHttp(p);
			String linkshow = PageUtil.shortUrl(p, 28);
			String pageName = U.shortString(p.name,24);//  PageUtil.shortName(p, 24);
			String iconPath = PageUtil.iconPath(p);
		%>
		<div class="col-sm-12 pages-part-page hoverAble">
		<a target="_blank" class="col-sm-8  pageMark" href="<%=linkStr%>" 
			onclick="openLink('<%=p.id %>','page')" title = "<%=p.name %>"
			data-id="<%=p.id%>" data-name="<%=p.name%>">
		 
					<img height="18" width="18"  class="img18" data-original="<%=iconPath%> " alt='*' >
			 		<span class="pages-part-page-name" > <%=pageName%></span> - 
			 		<span class="pages-part-page-link"><%=linkshow%></span>
		</a>  
		
		<%
		if(editAble){//TODO
			%>
			<div class="page-edit-div">
				<a href="<%=request.getContextPath()%>/group/group.page.edit.jsp?pageID=<%=p.id%>&groupID=<%=groupID%>"   class="pull-right  " data-id="<%=p.id %>" title="编辑这个网址">
					<span  class="pull-right glyphicon glyphicon-cog "></span>  
				</a>
				<a href="#" class="pull-right deletePage" data-id="<%=p.id %>" title="删除这个网址">
					<span  class="pull-right glyphicon glyphicon-remove" ></span> 
				</a>
			</div>		
			<span class="pull-right" style="padding-left: 10px;padding-right: 10px;padding-top: 6px;"> | </span>
		<%} %>

		<div class="page-comment-div">
			<!-- 
			<a onclick="preAddPageFunction('<%=p.name%>','<%=p.url%>')" href="#" 
				class="  pull-right  addPage hoverAble3" > 
				<span class="glyphicon glyphicon glyphicon-plus pull-right" title="复制到我的收藏夹"  ></span>
			</a>
			  -->
			<a href="#" onclick="downGroupPage('<%=p.id%>')" class="pull-right pageUp hoverAble3" title=" >不推荐< (有<%=link.downs %>人不推荐)">
				 <span style="color:green;" class="glyphicon glyphicon-thumbs-down"></span> 
			</a>
			<a href="#" onclick="upGroupPage('<%=p.id%>')" class="pull-right pageUp hoverAble3" title=" >推荐< (有<%=link.ups %>人推荐)">
				 <span style="color:green;" class="glyphicon glyphicon-thumbs-up"></span> 
			</a>
			<%
			if(link!=null&&link.id!=null&&StringUtils.isNotBlank(link.id.toString())){
			%>
				<a style="font-size: 13px;" href="<%=request.getContextPath()%>/discuss/link/<%=link.id %>" class="pull-right pageComment hoverAble3" title="评论一下 (已有<%=link.commentsCount %>次评论)">
					<%=link.lastDiscuss!=null?StringUtils.substring(link.lastDiscuss, 0,5):""  %>..评论(<%=link.commentsCount %>)
				</a>
			<%
			}
			%>

		</div>
		<!-- 
		<div title="访问:<%=p.favScore %>, 受欢迎度:<%=link.favScore %> ">
			<span style="font-size: 12px;color: #999;" ><%=p.favScore %></span>
			<span>/</span> 
			<span style="font-size: 12px;color: #999;" ><%=link.favScore %></span>
		</div>
		 -->
		</div><!-- 1155cc --> 
	 
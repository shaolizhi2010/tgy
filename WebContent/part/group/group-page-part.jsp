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
				<span id="copy-folder-btn" class="col-sm-6 btn pull-right sub-page-title-btn  hoverAble2" style="padding:8px; font-size: 13px;border: 1px solid #ccc;border-radius:10px;height: 32px;" title="一键复制 ' ' 中的所有网址(<%=pages.size() %>个),到自己的收藏夹" onclick="copyFolder()">一键复制</span>
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
	<div class="sub-page-body col-sm-12 no-padding">
	<%
 
	if(CollectionUtils.isEmpty(pages)){
 		%>
 		<div class="col-sm-12 ">
 			还未收藏任何网址，现在就 <a href="<%=request.getContextPath() %>/group/page/create/pre?groupID=<%=groupID %>&&folderID=<%=showFolderID %>"  >添加一个 </a> 吧
 		</div>
 		<%
 	}
 	else{
 			Collections.sort(pages);
 			Collections.reverse(pages);
 			LinkService lService = new LinkService();
			for (InterestGroupPage p : pages) {
				String linkStr =p.url;
				Link link = lService.getByUrl(p.url);
				if(link==null){
					link = new Link();
				}
				
				if(linkStr!=null && !linkStr.startsWith("http:")){
					linkStr = "http://"+linkStr;
				}
				String linkshow = p.url;
				//link
				if (linkshow != null) {
					if (linkshow.startsWith("http://")) {
						linkshow = linkshow.replaceAll("http://", "");
					}
					if (linkshow.startsWith("https://")) {
						linkshow = linkshow.replaceAll("https://", "");
					}
					int linkLength = linkshow.length();
					if (linkLength > 28) {
						linkshow = linkshow.substring(0, 18) + "..."
								+ linkshow.substring(linkLength - 10);
					}
				}
				
				String pageName = p.name;
				if(StringUtils.isBlank(pageName)){
					pageName =linkshow;
				}
				//name
				if (pageName != null && pageName.length() > 24) {
					pageName = pageName.substring(0, 24) + "...";
				}
		%>
		<div class="col-sm-12 pages-part-page hoverAble">
		<a target="_blank" class="col-sm-8  pageMark" href="<%=linkStr%>" 
			onclick="openLink('<%=p.id %>','page')" title = "<%=p.name %>"
			data-id="<%=p.id%>" data-name="<%=p.name%>">
		<%					String iconPath = "";
						if(StringUtils.isNoneBlank(p.iconPath)  ){
							iconPath = p.iconPath;
							if(iconPath.startsWith(App.imgPath)){
								iconPath = request.getContextPath()+"/"+iconPath;
							}
						}
						else{
							iconPath = request.getContextPath()+"/images/defaultFav.png";
						}
					%>
					<img class="img18" data-original="<%=iconPath%> " alt='*' >
			 		<span class="pages-part-page-name" > <%=pageName%></span> - <span class="pages-part-page-link"><%=linkshow%></span>
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
			<a href="#" onclick="pageUp('<%=p.id%>')" class="pull-right pageUp hoverAble3" title=" >顶< (已有<%=link.ups %>人顶过)">
				 <span style="color:green;" class="glyphicon glyphicon-thumbs-up"></span> 
			</a>
			<%
			if(link!=null&&link.id!=null&&StringUtils.isNotBlank(link.id.toString())){
			%>
				<a  href="<%=request.getContextPath()%>/discuss/link/<%=link.id %>" class="pull-right pageComment hoverAble3" title="评论一下 (已有<%=link.commentsCount %>次评论)">
					评论(<%=link.commentsCount %>)
				</a>
			<%
			}
			%>

		</div>
		<div title="访问:<%=p.favScore %>, 受欢迎度:<%=link.favScore %> ">
			<span style="font-size: 12px;color: #999;" ><%=p.favScore %></span>
			<span>/</span> 
			<span style="font-size: 12px;color: #999;" ><%=link.favScore %></span>
		</div>
		
		</div><!-- 1155cc --> 
		<%
			}//end for
		}//end else
		%>
		
		
		
	</div>

	
 		 
</div>
<!-- 显示网址页面结束 -->
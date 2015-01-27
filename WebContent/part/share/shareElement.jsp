<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.util.DiscussUtils"%>
<%@page import="org.apache.commons.lang3.EnumUtils"%>
<%@page import="com.tgy.util.PageType"%>
<%@page import="com.tgy.entity.group.InterestGroupPage"%>
<%@page import="com.tgy.service.group.InterestGroupPageService"%>
<%@page import="com.tgy.service.article.ArticleService"%>
<%@page import="com.tgy.entity.Article"%>
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
Page a ;
String pageID = request.getParameter("pageID");
if(StringUtils.isBlank(pageID)){
	return ;
}
 
 PageService  as = new PageService();
a = as.byID(pageID); 
 
//无图不显示
if(a==null || StringUtils.isBlank(a.imgSrc) ||  !PageType.article.equals(a.type)){
	return ;
}

UserService us = new UserService();
User u = us.getByID(a.userID);
String userUrl = u!=null? request.getContextPath()+"/u/"+u.id.toString():"";
String userName = u!=null?u.name:"";
//boolean editAble = BooleanUtils.toBoolean(request.getParameter("editAble")) ;
//String groupID = request.getParameter("groupID");

%>

<%
String url =PageUtil.urlWithHttp(a); 
String authorUrl = PageUtil.urlWithHttp(a.authorUrl);
//String linkshow = PageUtil.shortUrl(a, 28);
String pageName = PageUtil.shortName(a, 24);
String iconPath = PageUtil.iconPath(a); 
%>

<div class="col-sm-12 article-container no-padding ">
	
	<div class="col-sm-12 no-padding">
		<a   href='<%=request.getContextPath()+"/"+a.type.toString()%>' class=" col-sm-4 article-tag ">分类：<%=a.type.value()  %></a>
	</div>
	
	<div class="col-sm-12 no-padding">
		<div class="col-sm-4 no-padding">
			<a class="col-sm-12 no-padding" target="_blank"   href="<%=url%>"  title = "<%=a.title %>" >
				<img class="img-responsive col-sm-11 no-padding article-img" data-original="<%=a.imgSrc%> " alt=' <%=pageName%>' >
			</a>
		</div>
		<div class="col-sm-8 no-padding">
			<a class="col-sm-12 article-title no-padding" href="<%=url%>" target="_blank" >
				<span class="article-title-a"> <%=pageName%> </span>
			</a>
			<div class="col-sm-12 article-summry-container no-padding" >
				<a class="col-sm-12 article-summry-a no-padding" href="<%=url%>" target="_blank" >
					<%=StringUtils.substring(a.summry, 0,100)+"..."  %>
				</a>
			</div>
			<div class="col-sm-12" style="height: 30px;" ></div>
			<div class="col-sm-12 container no-padding">
				<div class="col-sm-3 article-time no-padding" >
					<span class="article-time-span"><%=U.dateTimeShort(a.orignDate)  %></span> 
				</div>
				<div class="    article-author pull-right">
					<!-- 
					<a target="_blank" class=" " href="<%=authorUrl%>"  title = "<%=a.authorName %>" >
						<img  width="30"  class="img-responsive article-author-img" data-original="<%=a.authorHearImgSrc%> " alt=' <%=pageName%>' >
					</a>
					-->
					<a target="_blank" class="article-authorName-url" href="<%=userUrl%>"  title = "<%=a.authorName %>" >
						<span class="  article-authorName-span ">来源： <%=userName %> 的网址分享</span>
					</a>
				 
				</div>
			</div>
	
			<div class="col-sm-6 article-author-head  no-padding" ></div>
			<div class="col-sm-6 article-authorName no-padding" >
	
			</div>
		</div>
	</div>
	

 

</div>
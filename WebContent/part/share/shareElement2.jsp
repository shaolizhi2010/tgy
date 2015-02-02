<%@page import="java.util.Random"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.util.DiscussUtils"%>
<%@page import="org.apache.commons.lang3.EnumUtils"%>
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
//if(a==null || StringUtils.isBlank(a.imgSrc) ||  !PageType.article.equals(a.type)){
//	return ;
//}

UserService us = new UserService();
User u = us.getByID(a.userID);
String userUrl = u!=null? request.getContextPath()+"/u/"+u.id.toString():"";
String userName = u!=null?u.name:"";
String userHeadImgUrl = u!=null?u.headImgUrl:"";
if(StringUtils.isNoneBlank(userHeadImgUrl) && userHeadImgUrl.startsWith("/images")){
	userHeadImgUrl = request.getContextPath()+userHeadImgUrl;
}
if(StringUtils.isBlank(userHeadImgUrl)){
	userHeadImgUrl = request.getContextPath()+ "/images/ava/ava"+userName.length()+".png";
}
//boolean editAble = BooleanUtils.toBoolean(request.getParameter("editAble")) ;
//String groupID = request.getParameter("groupID");

%>

<%
String url =PageUtil.urlWithHttp(a); 
String authorUrl = PageUtil.urlWithHttp(a.authorUrl);
//String linkshow = PageUtil.shortUrl(a, 28);
String pageName = U.shortString(a.name,40);//PageUtil.shortName(a, 24);
String iconPath = PageUtil.iconPath(a); 
String tagName = (String)request.getAttribute("tagName");
%>

<div class="col-sm-12 container article-container no-padding ">
	<%
	if(StringUtils.isBlank(tagName) || tagName.equals("all")){
		%>
		<div class="col-sm-6 col-sm-offset-2   no-padding article-type  ">
			<a   href='<%=request.getContextPath()+"/share/"+a.tagName%>' class="article-tag ">分类：<%=a.tagName  %></a>
		</div>
		<%
	}
	%>

	<div class="col-sm-12 no-padding">
		<div class="col-sm-2 col-xs-0  no-padding">
		<!-- 
			<div class="col-sm-12 no-padding  " style="  align: center;" >
				<a class="  " href="<%=userUrl %>" style="  text-align: center;display: block;" target="_blank">
					<img  style="" class="  headImg-50" data-original="<%=userHeadImgUrl%>" alt='<%=userName+"的网址分享"%>' />
				</a>
			</div>
			<div class="col-sm-12" style="height: 5px;" ></div>
			<div class="col-sm-12 no-padding  " style="  text-align: center;" >
				<a class="    article-authorName-url" 
					style=""
					href="<%=userUrl %>" target="_blank">
						<span class="article-authorName-span"><%=userName %></span> 
				</a>
			</div>
			 -->
		</div>
		<div class="col-sm-10 col-xs-12 no-padding">

			<a class="col-sm-12 article-title no-padding" href="<%=url%>" target="_blank" >
				<span class="article-title-a"> <%=pageName%> </span>
			 
			</a>
			<div class="col-sm-12 article-summry-container no-padding" >
					<%=U.shortString(a.summry, 200) %>
			</div>
			<%
			if(StringUtils.isNotBlank(a.imgSrc)){
				%>
				<div class="col-sm-12 no-padding">
					<a class="col-sm-5 no-padding" target="_blank"   href="<%=url%>"  title = "<%=a.title %>" >
						<img class="img-responsive col-sm-12 no-padding article-img" 
						data-original="<%=a.imgSrc%> " alt=' <%=pageName%>' />
					</a>
				</div>
				<%
			}
			%>

			<div class="col-sm-12" style="height: 20px;" ></div>
			<div class="col-sm-12 container no-padding">
				<div class="col-sm-6 col-xs-12 article-time no-padding" >
					<span class="article-time-span"><%=!StringUtils.equals( U.dateTimeShort(a.orignDate), "null") ?U.dateTimeShort(a.orignDate):"" %></span> 
				</div>
				<div class="col-sm-6 col-xs-12 no-padding article-author pull-right ">
					<!-- 
					<a target="_blank" class=" " href="<%=authorUrl%>"  title = "<%=a.authorName %>" >
						<img  width="30"  class="img-responsive article-author-img" data-original="<%=a.authorHearImgSrc%> " alt=' <%=pageName%>' >
					</a>
					-->
					<a target="_blank" class="article-source-url" 
						href="<%=userUrl%>"  title = "<%=userName %>" >
						<span class="article-authorName-span ">来源： <%=userName %> 的网址分享</span>
					</a>
				 
				</div>
			</div>
	
			<div class="col-sm-6 article-author-head  no-padding" ></div>
			<div class="col-sm-6 article-authorName no-padding" >
	
			</div>
		</div>
	</div>
	

 

</div>
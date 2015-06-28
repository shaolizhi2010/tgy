<%@page import="net.sourceforge.htmlunit.corejs.javascript.IdScriptableObject"%>
<%@page import="com.tgy.service.cache.AppCache"%>
<%@page import="com.tgy.util.PageReplyUtils"%>
<%@page import="com.tgy.service.ReplyService"%>
<%@page import="com.tgy.entity.Reply"%>
<%@page import="com.tgy.service.PanSearchCacheService"%>
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
	Page a =(Page) request.getAttribute("page-temp" );

/*
String pageID = request.getParameter("pageID");
if(StringUtils.isBlank(pageID)){
	return ;
}
 
 PageService  as = new PageService();
a = as.byID(pageID); 
*/

//从pansearchache里取
/*
if(a == null){
	PanSearchCacheService pss = new PanSearchCacheService();
	a = pss.byID(pageID);
}
if(a==null){
	return;
}
*/

 
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
String pageName = a.name;
	//pageName =StringUtils.replace(pageName, "_免费高速下载|百度云", "");
  pageName = U.shortString(pageName,40);//PageUtil.shortName(a, 24);
if(StringUtils.isBlank(pageName)){
	pageName = U.shortString(a.url,40);
}
if(StringUtils.contains(pageName, "error 40")  ){
	return;
}
if(StringUtils.isBlank(pageName)){
	return;
}
//TODO
pageName = pageName.replaceAll("关注微信", "");
pageName = pageName.replaceAll("公众平台", "");
pageName = pageName.replaceAll("高清下载吧", "");
pageName = pageName.replaceAll("gqxzb.com", "");
pageName = pageName.replaceAll("【", "");
pageName = pageName.replaceAll("】", "");

String iconPath = PageUtil.iconPath(a); 
String tagName = request.getAttribute("tagName")!=null?(String)request.getAttribute("tagName"):"";
String type =  request.getAttribute("type")!=null?(String)request.getAttribute("type"):"";
%>

<div class="col-sm-12 container article-container no-padding " id="<%=a.id.toString()%>">
 
		<div class="col-sm-10 col-sm-offset-2   no-padding article-type  ">
			<a class="article-tag col-sm-5"  
				data-id="<%=a.id.toString() %>"
				href='<%=request.getContextPath()+"/share/"+type+"?tagName=" +a.tagName%>' >分类：<%=a.tagName  %></a>
				
			<a class="pageElement-reply-show-btn   col-sm-3 hidden-xs pull-right" data-pageID="<%=a.id.toString() %>" href="javascript:void(0)" style="font-size: 10px; float: right;text-align: right;">
				  [回复] 
			</a>
				
		</div>
	 

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
		<%//TODO %>
		<div class=" col-sm-7 col-xs-7 no-padding shareElement-main"  >

			<a class="col-sm-12 article-title no-padding statistic-page" 
				data-id="<%=a.id.toString() %>"
				href="<%=url%>" target="_blank" >
				<span class="article-title-a"> <%=pageName%> </span>
			 
			</a>
			<%
			if(StringUtils.isNotBlank(a.description)&& !StringUtils.equals(a.name, a.description)){
				%>
				<div class="col-sm-12 article-summry-container extendable no-padding" data-bk=""><%//U.shortString(a.name, 200)  %>  
					<%=  U.shortString(a.description, 200)  %>
				</div>
				<%
			}
			%>

			 	<!-- 
			<div class="col-sm-12   no-padding" data-bk="">
				<div class="shareElement-reply col-sm-3 ">张三丰 回复：看不了了</div> <div class="shareElement-reply col-sm-3">李小虎 回复 有没有第三季？</div>   
			</div>
			-->
			<%
			if(StringUtils.isNotBlank(a.imgSrc)){
				%>
				<div class="col-sm-12 no-padding">
					<a class="col-sm-3 no-padding statistic-page" 
						data-id="<%=a.id.toString() %>"
						target="_blank"   href="<%=url%>"  title = "<%=a.title %>" >
						<img class="img-responsive col-sm-12 no-padding article-img" style="max-height:200px;"
						data-original="<%=a.imgSrc%> " alt=' <%=pageName%>' /><!-- data-original -->
					</a>
				</div>
				<%
			}
			%>

			<div class="col-sm-12" style="height: 20px;" ></div>
			<div class="col-sm-12 container no-padding">
				<div class="col-sm-6 col-xs-12 article-time no-padding" >
					<span class="article-time-span"><%=!StringUtils.equals( U.dateTimeShort(a.orignDate), "null") ?U.dateTimeShort(a.orignDate):"" %>   / <%=userName %>  </span> 
				</div>
				<div class="col-sm-6 col-xs-12 no-padding article-author pull-right ">
					<!-- 
					<a target="_blank" class=" " href="<%=authorUrl%>"  title = "<%=a.authorName %>" >
						<img  width="30"  class="img-responsive article-author-img" data-original="<%=a.authorHearImgSrc%> " alt=' <%=pageName%>' >
					</a>
					
					<a target="_blank" class="article-source-url" 
						href="<%=userUrl%>"  title = "<%=userName %>" >
						<span class="article-authorName-span ">来源： <%=userName %> 的网址分享</span>
					</a>
				 -->
				</div>
			</div>
	
			<div class="col-sm-6 article-author-head  no-padding" ></div>
			<div class="col-sm-6 article-authorName no-padding" >
	
			</div>
		</div>
		
		<div class=" col-sm-3  hidden-xs  no-padding shareElement-comment"  >
			<div class="pageElement-reply-content extendable"  >
				<%
					List<Reply> replies = AppCache.repliesByToID(a.id.toString()); 
				
					if(CollectionUtils.isEmpty(replies)){ //无回复
						String pageNameTrimed = StringUtils.replace(pageName, "_免费高速下载|百度云","");
						if(StringUtils.length(pageNameTrimed)<10){
						%>
							小盒  回复: <a target="_blank" href="<%=request.getContextPath()%>/pan/<%=pageNameTrimed %>">百度盘搜:<%=pageNameTrimed %></a><br/>
						<%	
						}else{
						%>
							暂无回复<br/>	
						<%
						}
						 
					}
					else{ //有回复
						for(Reply reply : replies){
							String from = PageReplyUtils.fromUserName(reply);
							String msg = U.addLinkForMessage(reply.content);
							%> <%=from%> 回复: <%=msg%> <br/><%
						}
					}
				
				
				%>
			</div>
			 
			<div class="pageElement-reply-textarea-div col-sm-11"  style="display: none;" >
			</div>
		</div>
		<!--  -->
	</div>
	 
</div>
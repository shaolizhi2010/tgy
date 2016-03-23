<%@page import="java.net.URLEncoder"%>
<%@page import="com.tgy.util.FuliDou"%>
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

if(StringUtils.contains(pageName, "a播放器")){
return;	
}


pageName = pageName.replaceAll("关注微信", "");
pageName = pageName.replaceAll("公众平台", "");
pageName = pageName.replaceAll("高清下载吧", "");
pageName = pageName.replaceAll("gqxzb.com", "");
pageName = pageName.replaceAll("【", "");
pageName = pageName.replaceAll("】", "");


String url =PageUtil.urlWithHttp(a); 
String url2 = request.getContextPath()+"/resource-detail.jsp?url="+URLEncoder.encode(url, "utf-8")+"&name="+pageName;

//System.out.println("url2 : " + url2);

String authorUrl = PageUtil.urlWithHttp(a.authorUrl);
//String linkshow = PageUtil.shortUrl(a, 28);

String iconPath = PageUtil.iconPath(a); 
String tagName = request.getAttribute("tagName")!=null?(String)request.getAttribute("tagName"):"";
String type =  request.getAttribute("type")!=null?(String)request.getAttribute("type"):"";
int fuliScore = U.parseInt( request.getAttribute("fuliScore")) ;
%>

<div class="col-sm-12 container article-container no-padding " id="<%=a.id.toString()%>">
 
		<div class="col-sm-10 col-sm-offset-2   no-padding article-type  ">
			<a class="article-tag col-sm-5"  
				data-id="<%=a.id.toString() %>"
				href='<%=request.getContextPath()+"/share/"+type+"?tagName=" +a.tagName%>' >分类：<%=a.tagName  %></a>
				
			<a class="pageElement-reply-show-btn   col-sm-3 hidden-xs pull-right" data-pageID="<%=a.id.toString() %>" href="javascript:void(0)" style="font-size: 10px; float: right;text-align: right;" 
			title="奖励  <%=FuliDou.replyScore %>个 福利豆">
				  [回复] 
			</a>
				
		</div>
	 

	<div class="col-sm-12 no-padding">
		<div class="col-sm-2 col-xs-0  no-padding">
		 
		</div>
	 
		<div class=" col-sm-9 col-xs-9 no-padding shareElement-main"  >

			<a class="col-sm-12 article-title no-padding statistic-page " 
				data-id="<%=a.id.toString() %>"
				<% if(a.needFulidou > 0) { //资源需要福利豆
						if(a.needFulidou > fuliScore){//福利豆不够 走js ，alert 
							%>
							onclick="checkFulidou('<%=a.id.toString() %>','<%=url2%>',<%=a.needFulidou%>)" href="#">
							<%
						}else{//福利豆够 直接显示连接
							%>
							 onclick="consumeFulidou('<%=a.id.toString() %>')" href="<%=url2%>"  target="_blank" >
							<%
						}
					%> 
					
				<span class="article-title-a"> [<%=a.needFulidou %>豆] <%=pageName%> </span>
				<%} else{ //资源不用福利豆
					%>
					href="<%=url2%>"  target="_blank" >
				<span class="article-title-a"> <%=pageName%> </span>
					<%
				}
				%>
				 
			 
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
					<span class="article-time-span"><%=StringUtils.substringBeforeLast(StringUtils.substringAfter(a.orignDate, "-"), " ")%>   / <%=userName %>  </span> 
				</div>
				<div class="col-sm-6 col-xs-12 no-padding article-author pull-right ">
 
				</div>
			</div>
	
			<div class="col-sm-6 article-author-head  no-padding" ></div>
			<div class="col-sm-6 article-authorName no-padding" >
	
			</div>
		</div>
		
		 
		<!--  -->
	</div>
	 
</div>
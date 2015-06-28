<%@page import="com.tgy.App"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.statistic.service.LinkService"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%@include file="bookmark-data.jsp" %> 

<div  class="container col-sm-12 clearfix no-padding sub-page-with-title" >
	<%
	LinkService lService = new LinkService();
	List<Link> links = new ArrayList<Link>();
	String tagName = "";
	int linkCount = 8;
	
		if (showFolder != null) {//通过tagname取
			tagName = showFolder.name;
			links = lService.getByName(tagName);
		}
		if(CollectionUtils.isEmpty(links)){ //无限制条件，取分数最多的
			links = lService.list(linkCount);	
			tagName = "";
		}
		if (!CollectionUtils.isEmpty(links)) {//取到结果
			if(links.size()>linkCount)links=links.subList(0, linkCount);
	%>
	
	<div class="sub-page-title container col-sm-12 no-padding">
		<span>大家收藏的网站 : <%=tagName %></span>
	</div>
	
	<div class="col-sm-12 container sub-page-body  no-padding">
		<%
			for (Link linkObj : links) {
				if (linkObj.keeps >= 1 // 收藏次数大于1 才显示，只收藏过一次的可能是一些比较个性化的网址，没必要显示
						) {
					String link =linkObj.url;
					if(link!=null && !link.startsWith("http:")){
						link = "http://"+link;
					}
					String linkshow = linkObj.url;
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
					
					String pageName = linkObj.title;
					if(StringUtils.isBlank(pageName)){
						pageName =linkshow;
					}
					//name
					if (pageName != null && pageName.length() > 24) {
						pageName = pageName.substring(0, 24) + "...";
					}
							
							
		%>
	
		<div class="col-sm-12 container pages-part-page hoverAble">
			<a target="_blank" href="<%=linkObj.url%>" class="col-sm-10"> 
				<%		String iconPath = "";
						if(StringUtils.isNoneBlank(linkObj.iconPath)  ){
							iconPath = linkObj.iconPath;
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
			<a onclick="preAddPageFunction('<%=pageName%>','<%=linkObj.url%>')" href="#" 
				class="col-sm-1 pull-right  addPage" > 
				<span class="glyphicon glyphicon glyphicon-plus pull-right" title="复制到我的收藏夹"  ></span>
			</a>
			<div class="col-sm-1 pull-right"  style="padding-top: 3px;" title=" 受欢迎度:<%=linkObj.favScore %> ">
				<span style="font-size: 12px;color: #999;" > </span>
			</div>
		</div>
		<%
			}
					}//end for
		%>
		<!-- 
		<a class="col-sm-12" target="_blank" href="<%=request.getContextPath()%>/tag/<%=tagName%>"
			style="margin-top: 10px;font-size: 13px;"> >>查看更多 </a>
			 -->
		<%
			}
	
		%>
	
	</div>
</div>
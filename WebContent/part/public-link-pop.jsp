<%@page import="com.tgy.App"%>
<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.statistic.service.LinkService"%>
<%@page import="com.tgy.statistic.entity.Tag"%>
<%@page import="com.tgy.statistic.service.TagService"%>
<%@page import="com.tgy.dao.FolderDao"%>
<%@page import="com.tgy.util.URLUtils"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>
<%@page language="java" pageEncoding="UTF-8"%>

<!-- folder index 开始 -->
<div id="public-link-pop" class="col-sm-12 no-padding sub-page-with-title" >
	<div class="sub-page-title">
		<span>热门网址</span>
	</div>
	<div class="sub-page-body">
	<%
 	LinkService ls = new LinkService();
 	List<Link> links = ls.list(20);
 	 	for (Link linkObj : links) {
 	 		if (linkObj.keeps >= 0 //TODO 收藏次数大于1 才显示，只收藏过一次的可能是一些比较个性化的网址，没必要显示
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
					if (linkLength > 20) {
						linkshow = linkshow.substring(0, 12) + "..."
								+ linkshow.substring(linkLength - 8);
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

	<div class="col-sm-12 pages-part-page hoverAble">
		<a target="_blank" href="<%=linkObj.url%>" class="col-sm-11"> 
			
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
		<!-- 
		<a onclick="createPageFunction('<%=linkObj.title%>','<%=linkObj.url%>')" href="#" class="pull-right"
			style="padding-left: 1px; padding-right: 1px;"> <span
			class="glyphicon glyphicon glyphicon-plus" 
			style="padding-right: 1px;" title="拷贝到我的收藏夹"></span>
		</a>
		 -->
	</div>
	<%
		}
 	 	}
 	%>
	</div>
</div>
<!-- folder index结束 -->
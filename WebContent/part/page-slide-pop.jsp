<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.entity.Link"%>
<%@page import="com.tgy.service.LinkService"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<div class="container col-md-12 clearfix"
	style="padding: 2px; padding-left: 20px; margin-bottom: 20px; border-left: 1px solid #eee;">



	<%
		Folder curFolder = U.param(request, "curFolder", Folder.class);

		if (curFolder != null) {

			String tagName = curFolder.name;
			LinkService lService = new LinkService();
			List<Link> links = lService.searchByTagName(tagName);
			if (!CollectionUtils.isEmpty(links)) {
	%>
	<p style="padding: 3px;"><%=tagName %>热门网站：</p>
	<%
		for (Link link : links) {
					if (link.keeps > 1 //收藏次数大于1 才显示，只收藏过一次的可能是一些比较个性化的网址，没必要显示
					) {
	%>
	<div style="margin-top: 10px;   height: 20px;">
		<a target="_blank" href="<%=link.url%>" class="col-md-8"
			> <span
			class="glyphicon glyphicon-star" style="color: #ffd76e;"></span> <span
			style="color: #1155cc;"> <%=link.title%></span> <!-- #ff076e #1155cc; 0000cc-->
		</a> <a ng-click="preCreateFolderFunction( )" href="#" class="col-md-2"
			style="padding-left: 1px; padding-right: 1px;"> <span
			class="glyphicon glyphicon glyphicon-plus"
			style="padding-right: 1px;"></span>
		</a>
	</div>
	<%
		}
				}//end for
	%>
	<a class="col-md-12" ng-click="preCreateFolderFunction()"
		style="margin-top: 10px;"> >>查看更多 </a>
	<%
		}

		}
	%>



</div>
<%@page import="com.tgy.App"%>
<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.service.FolderService"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="part/common.jsp" %>
<%@include file="part/bookmark-data.jsp" %> 
<%@include file="part/show-user-data.jsp" %>
<%
List<Page>  results = U.paramList(request, "results");
String keyword = U.paramString(request, "keyword");
%>

<!DOCTYPE html>
<html>
<head lang="en">
<jsp:include page="part/head-meta.jsp" />
<jsp:include page="part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="part/head.jsp" />
	<jsp:include page="part/public-tabs.jsp" />
	<div class="  col-sm-12" style="margin-top: 20px;"></div>
 	
	<!-- 主体内容 -->
	<div class=" col-sm-9 container ">
		<div class="  col-sm-12 " style="margin-top: 10px;"></div>
		<div class="  col-sm-10 col-sm-offset-1  " style="margin-top: 10px;"><span style="font-size:22px;">网盘搜索</span><span style="font-size:11px;" >   (找资源、找电影、找剧集、找资料...)</span></div>
		<div id="pan-search-input" class="col-sm-10 col-sm-offset-1 ">
			<div  class="col-sm-8   no-padding" >
				<input id="pan_search_value" class=" form-control hover-focus enterInput"   data-func-name="panSearch"
					value="<%=keyword %>" style="height: 40px;border-radius:10px;" placeholder="网盘搜索" />
			</div>
			<div class="col-sm-3">
				<input class="btn btn-primary col-sm-12" style="height: 40px;border-radius:10px;" onclick="panSearch()" 
					 type="button" value="搜索资源">
			</div>
		</div>
		
		<div id="pan-search-results" class="col-sm-10 col-sm-offset-1 no-padding">
		
			<%
			if(results==null||results.size()==0){
				%>
				<span style="font-size: 9px;padding-left: 10px;">暂无数据</span>
				<%
			}
			for (Page pageObj : results) {
 
					String link =pageObj.url;
					if(link!=null && !link.startsWith("http:")){
						link = "http://"+link;
					}
					String linkshow = pageObj.url;
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
					
					String pageName = pageObj.name;
					if(StringUtils.isBlank(pageName)){
						pageName =linkshow;
					}
					//name
					if (pageName != null && pageName.length() > 24) {
						pageName = pageName.substring(0, 24) + "...";
					}
		%>
	
		<div class="col-sm-12 container pages-part-page hoverAble no-padding">
			<a target="_blank" href="<%=pageObj.url%>" class="col-sm-9 no-padding"> 
				<%		String iconPath = "";
						if(StringUtils.isNoneBlank(pageObj.iconPath)  ){
							iconPath = pageObj.iconPath;
							if(iconPath.startsWith(App.imgPath)){
								iconPath = request.getContextPath()+"/"+iconPath;
							}
						}
						else{
							iconPath = request.getContextPath()+"/images/baiduyun.ico";
						}
					%>
					<img class="img18" src="<%=iconPath%> " alt='*' >
				
				
				<span class="pages-part-page-name" > <%=pageName%></span> - <span class="pages-part-page-link"><%=linkshow%></span>
			</a> 
			<a onclick="preAddPageFunction('<%=pageName%>','<%=pageObj.url%>')" href="#" 
				class="col-sm-3 pull-right  addPage" > 
				<span class="  pull-right" title="复制到我的收藏夹"  >[复制到我的收藏夹]</span>
			</a>
 
		</div>
		<%
					}//end for
		%>
		 </div>
 	
	</div>
	 	<input type="hidden" id="pageID" value="pan">
	
	 	<!-- 菜单 -->
 	<div class=" col-sm-3 no-padding" >
		<div class=" col-sm-10 col-sm-offset-1 no-padding" >
			<jsp:include page="part/search-discuss.jsp"/>
		</div>
	</div>
 
 
	<jsp:include page="part/foot.jsp" />
	<jsp:include page="part/importAtFoot.jsp" />
	<script src="<%=request.getContextPath()%>/myjs/common.js"></script>
	<script src="<%=request.getContextPath()%>/myjs/pan.js"></script>
</body>

</html>
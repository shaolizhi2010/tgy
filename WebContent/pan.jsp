<%@page import="org.apache.commons.lang3.math.NumberUtils"%>
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

int start =0;
if(request.getAttribute("start")!=null){
	start = Integer.parseInt((String)request.getAttribute("start"));
}
int curPageNum = start/10+1;

%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>百度网盘搜索-百度网盘下载-网址盒子</title>
<meta name='description' content='百度网盘搜索-百度网盘-百度网盘资源-百度网盘电影-百度网盘下载-百度网盘在线-百度网盘电视剧-百度网盘美剧'/>
<meta name='keywords' content='百度网盘搜索 百度网盘 百度网盘资源 百度网盘电影 百度网盘下载 百度网盘在线 百度网盘电视剧  百度网盘美剧'/>
<meta http-equiv="Content-Language" content="zh-CN" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />

<jsp:include page="part/importAtHead.jsp" />
</head>
<body>
	<jsp:include page="part/head.jsp" />
	<jsp:include page="part/public-tabs.jsp" />
	<div class="  col-sm-12" style="margin-top: 20px;"></div>
 	<input type="hidden" id="keyword" value="<%=keyword%>"/>
	<!-- 主体内容 -->
	<div class=" col-sm-6 container no-padding " style="margin-right: 50px;">
		<div class="  col-sm-12 no-padding" style="margin-top: 10px;"></div>
		<div class="  col-sm-11 col-sm-offset-1 no-padding " style="margin-top: 10px;"><span style="font-size:22px;">百度网盘搜索</span><span style="font-size:11px;" >   (找资源、找电影、找剧集、找资料...)</span></div>
		<div id="pan-search-input" class="col-sm-11 col-sm-offset-1 ">
			<div  class="col-sm-9   no-padding" >
				<input id="pan_search_value" class=" form-control hover-focus enterInput"   data-func-name="panSearch"
					value="<%=keyword %>" style="height: 40px;border-radius:3px;" placeholder="百度网盘搜索" />
			</div>
			<div class="col-sm-3">
				<input class="btn btn-primary col-sm-12" style="height: 40px;border-radius:3px;" onclick="panSearch()" 
					 type="button" value="搜索资源">
			</div>
		</div>
		
		<div id="pan-search-results" class="col-sm-11 col-sm-offset-1 no-padding">
		
			<%
			if(results==null||results.size()==0){
				%>
				<span style="font-size: 9px;padding-left: 10px;">请输入要找的资源</span>
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
					if (pageName != null && pageName.length() > 30) {
						pageName = pageName.substring(0, 30) + "...";
					}
		%>
	
		<div class="col-sm-12 container pages-part-page no-padding " style="min-height: 60px;" >
			<div class="col-sm-1"  style="min-width: 50px;">
				<a target="_blank" href="<%=pageObj.url%>" class=" no-padding"> 
						<img class="img40" src="<%=request.getContextPath()%>/images/yun.gif" alt='*' >
				</a> 
			</div>
			<div class="col-sm-11 ">
				<div class=" ">
					<a target="_blank" href="<%=pageObj.url%>" class="no-padding " style="color: #1024EE;text-decoration: underline;"> 
						<span class="pages-part-page-name " style="font-size: 15px;color: #1024EE; " ><%=pageName%></span> 
					</a>
					<a onclick="preAddPageFunction('<%=pageName%>','<%=pageObj.url%>')" href="#" 
						class="  pull-right  addPage no-padding " > 
						<span class="pull-right" title="复制到我的收藏夹"  >[复制到我的收藏夹]</span>
					</a><br/>
					<span class="search-page-link " style=" "><%=linkshow%></span>
					 
				</div>	

			

				<p class="col-sm-11  pages-part-page-comment" style="color: #666;padding-left: 10px;">
					<%=pageObj.comment %>
				</p>
			</div>
			
			
 
		</div>
		<%
			}//end for
		%>
		
		 </div><!-- results end -->
		
		<!-- 翻页 -->
		<div id="search-pagination" class="col-sm-10 col-sm-offset-1 no-padding" style="display: none;">
		<nav>
		  <ul class="pagination">
		    <li>
		      <a href="<%=request.getContextPath()%>/pan<%= StringUtils.isNoneBlank(keyword)?"/"+keyword:"" %>?start=<%=curPageNum==1?0:curPageNum*10-20%>" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      </a>
		    </li>
		    <%
		    for(int i = 1;i<11;i++){
		    	String selectedStyle="";
		    	if(i==curPageNum){
		    		selectedStyle="background-color:#072;color:#fff;";
		    	}
		   		%>
		   		 <li><a style="<%=selectedStyle %>" href="<%=request.getContextPath()%>/pan<%= StringUtils.isNoneBlank(keyword)?"/"+keyword:"" %>?start=<%=i*10-10%>"><%=i %></a></li>
		   		<% 	
		    }
		    %>
		   
		    <li>
		      <a href="<%=request.getContextPath()%>/pan<%= StringUtils.isNoneBlank(keyword)?"/"+keyword:"" %>?start=<%=curPageNum==100?10:curPageNum*10%>" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span> 
		      </a>
		    </li>
		  </ul>
		</nav>
		</div>
		<!-- 翻页end -->
		 
		 <!-- 搜索历史 -->
		 <div id="searchHistroy" class="col-sm-10 col-sm-offset-1 no-padding" >
		 	<span style="font-size: 13px;font-weight: bold;">热门搜索 : </span>
		 </div>
 		<!-- 搜索历史  end-->
 		
 		 <!-- 群组推荐 -->
		 <div id="searchHistroy" class="col-sm-10 col-sm-offset-1 no-padding" >
		 	<span style="font-size: 13px;font-weight: bold;">资源群组 : </span>
		 	<a  href="<%=request.getContextPath()%>/g/美剧" target="_blank" style=" "> <span class="hoverAble-red" style="padding:5px;color: red;font-weight: bold;">美剧</span></a>
		 	<a  href="<%=request.getContextPath()%>/g/电影" target="_blank" style=" "> <span class="hoverAble-red" style="padding:5px;">电影</span></a>
		 </div> 
 		<!-- 群组推荐 end-->
 		
	</div>
	
	 	<input type="hidden" id="pageID" value="pan">
	
	 	<!-- 菜单 -->
 	<div class=" col-sm-5 no-padding" style="border-left:1px solid #eee;padding-left: 10px; padding-top: 20px;">
		<div id="discuss-container" class=" col-sm-10  no-padding" >
			<jsp:include page="part/discuss/all-discuss.jsp">
		    	<jsp:param name="sourceBoardName" value="百度网盘搜索"/>
		    </jsp:include>
		</div>
	</div>
 
 
	<jsp:include page="part/foot.jsp" />
	<jsp:include page="part/importAtFoot.jsp" />
	<script src="<%=request.getContextPath()%>/myjs/common.js"  ></script>
	<script src="<%=request.getContextPath()%>/myjs/pan.js" defer="defer"></script>
</body>

</html>
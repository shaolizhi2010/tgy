<%@page import="java.util.Random"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="com.tgy.entity.SearchHistory"%>
<%@page import="java.util.Map"%>
<%@page import="org.apache.commons.lang3.math.NumberUtils"%>
<%@page import="com.tgy.entity.group.InterestGroupPage"%>
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
<%@ page language="java" pageEncoding="UTF-8"%>

<%
//if(StringUtils.isBlank(folderName)){
//	folderName = "网址";
//}
int start = 0;
if(request.getAttribute("pageStart")!=null){
	start = (Integer)request.getAttribute("pageStart");
}
//long count =  (Long)request.getAttribute("count");
List< SearchHistory> histories = (List<SearchHistory>)request.getAttribute("histories");
%>
<!-- 显示网址页面开始 -->
<div id="  " class="col-sm-12 container no-padding ">
 	
 	<%
 	Random rando = new Random(); 
 	for(SearchHistory h : histories){
 		String keyword = h.keyword;
 		String seoStr = "";
 		int randInt = rando.nextInt(40);
 		if(randInt==1)seoStr="百度网盘";
 		if(randInt==2)seoStr="百度盘";
 		if(randInt==3)seoStr="百度云";
 		if(randInt==4)seoStr="百度云网盘";
 		if(randInt==5)seoStr="百度云盘";
 		
 		keyword = URLDecoder.decode(keyword);
 		if(U.isNotMessCode(keyword) && keyword.length()<20){
 			
 		%>
 		 <a target="_blank" class="col-sm-3" style="font-size:14px;  " href="/pan/<%=h.keyword%>"><%=keyword+" "+seoStr%></a>
 		<%
 		}
 	}
 	%>
	<!-- 翻页 -->
	<jsp:include page="pagination.jsp">
		<jsp:param name="start" value='<%=start+"" %>'/>
		<jsp:param name="count" value='<%=1800+"" %>'/> 
	</jsp:include>	
		

	
 		 
</div>

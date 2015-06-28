<%@page import="org.apache.commons.lang3.math.NumberUtils"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
//1 2 3
//第一页start是0

String url = ""; //页面地址
if(StringUtils.isNotBlank(request.getParameter("url"))){
	url = request.getParameter("url");
}
if(StringUtils.isBlank(url)){
	url = request.getAttribute("javax.servlet.forward.request_uri").toString();	
}
String queryString = request.getQueryString();
if(StringUtils.isNotBlank(queryString)){
	//System.out.println("queryString "+queryString);
	if(queryString.contains("pageStart=")){
		queryString = StringUtils.substringBefore(queryString, "pageStart=");
	}
	//System.out.println("queryString after : "+queryString);
	url = url+"?" + queryString;	
}
String contactStr = "";
if(url.endsWith("?")||url.endsWith("&")){
	//empty
}
else{
	contactStr = url.contains("?") ? "&":"?"; //url中已经有 '？'了，那么后续参数用&链接，否则用？
}
 

int count = 0; //总共有多少条
if(StringUtils.isNotBlank(request.getParameter("count"))){
	count = NumberUtils.toInt( request.getParameter("count") );
}

int pageStart=0;//当前显示的记录是从第多少条开始的
if(StringUtils.isNotBlank(request.getParameter("pageStart"))){
	pageStart = NumberUtils.toInt( request.getParameter("pageStart") );
}


int currentPageNum = pageStart/10+1;
int pageCount = (count-1)/10+1; //9条1页 10条1页 11条2页

//从多少条开始显示，如果记录条数太多，比如1万条，不能在页面显示一千个页数。只显示前n个和后n个
int startPageNum = currentPageNum-3<1 ? 1: currentPageNum-3;
int endPageNum = currentPageNum+3>pageCount ? pageCount: currentPageNum+3;

%>

<!-- 翻页 -->
<div class="col-sm-12 no-padding">
	<nav>
	  <ul class="pagination">
	    <li class="">
	      <a href="<%=url%><%=contactStr%>pageStart=0">
	        <span class="pagination-btn" >首页</span>
	      </a>
	    </li>
	    <li>
	      <a href="<%=url%><%=contactStr%>pageStart=<%=currentPageNum*10-20>0?currentPageNum*10-20:0%>">
	        <span class="pagination-btn" >上一页</span>
	      </a>
	    </li>
	    <%
	    for(int i = startPageNum;i<=endPageNum;i++){
	    	String selectedStyle="";
	    	if(i== currentPageNum){
	    		selectedStyle="background-color:#072;color:#fff;";
	    	}
	   		%>
	   		 <li><a style="<%=selectedStyle %>" href="<%=url%><%=contactStr%>pageStart=<%=i*10-10%>"><span class="pagination-btn"><%=i %></span> </a></li>
	   		<% 	
	    }
	    %>
	    <li>
	      <a href="<%=url%><%=contactStr%>pageStart=<%=currentPageNum*10%>">
	        <span class="pagination-btn">下一页</span>
	      </a>
	    </li>
	    <li>
	      <a href="<%=url%><%=contactStr%>pageStart=<%=(count/10)*10%>" 
	      	title="尾页" >
	        <span class="pagination-btn" >尾页</span> 
	      </a>
	    </li>
	    <li>
	      <a style="color:#333;" class="pagination-btn" >
	          	共<span style="color: red;" > <%=pageCount %></span>页 
	      </a>
	    </li>
	  </ul>
	</nav>
	
</div>	
<!-- 翻页end -->
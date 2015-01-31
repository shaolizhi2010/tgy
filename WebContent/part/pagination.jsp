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
	if(queryString.contains("?start=")){
		queryString = StringUtils.substringBefore(queryString, "?start=");
	}
	url = url+"?" + queryString;	
}


String contactStr = url.contains("?") ? "&":"?"; //url中已经有 '？'了，那么后续参数用&链接，否则用？

int count = 0; //总共有多少条
if(StringUtils.isNotBlank(request.getParameter("count"))){
	count = NumberUtils.toInt( request.getParameter("count") );
}

int start=0;//当前显示的记录是从第多少条开始的
if(StringUtils.isNotBlank(request.getParameter("start"))){
	start = NumberUtils.toInt( request.getParameter("start") );
}


int currentPageNum = start/10+1;
int pageCount = (count-1)/10+1; //9条1页 10条1页 11条2页

//从多少条开始显示，如果记录条数太多，比如1万条，不能在页面显示一千个页数。只显示前n个和后n个
int startPageNum = currentPageNum-3<1 ? 1: currentPageNum-3;
int endPageNum = currentPageNum+3>pageCount ? pageCount: currentPageNum+3;

%>

<!-- 翻页 -->
<div class="col-sm-12 no-padding">
	<nav>
	  <ul class="pagination">
	    <li>
	      <a href="<%=url%>?start=0">
	        <span >首页</span>
	      </a>
	    </li>
	    <li>
	      <a href="<%=url%><%=contactStr%>start=<%=currentPageNum*10-20>0?currentPageNum*10-20:0%>">
	        <span >上一页</span>
	      </a>
	    </li>
	    <%
	    for(int i = startPageNum;i<=endPageNum;i++){
	    	String selectedStyle="";
	    	if(i== currentPageNum){
	    		selectedStyle="background-color:#072;color:#fff;";
	    	}
	   		%>
	   		 <li><a style="<%=selectedStyle %>" href="<%=url%><%=contactStr%>start=<%=i*10-10%>"><%=i %></a></li>
	   		<% 	
	    }
	    %>
	    <li>
	      <a href="<%=url%><%=contactStr%>start=<%=currentPageNum*10%>">
	        <span >下一页</span>
	      </a>
	    </li>
	    <li>
	      <a href="<%=url%><%=contactStr%>start=<%=(count/10)*10%>" 
	      	title="尾页" >
	        <span>尾页</span> 
	      </a>
	    </li>
	    <li>
	      <a style="color:#333;">
	          	共<span style="color: red;"> <%=pageCount %></span>页 
	      </a>
	    </li>
	  </ul>
	</nav>
	
</div>	
<!-- 翻页end -->
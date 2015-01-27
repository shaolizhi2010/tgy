<%@page import="com.tgy.util.ConditionMap"%>
<%@page import="com.tgy.service.DiscussService"%>
<%@page import="org.apache.commons.lang3.math.NumberUtils"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
//1 2 3
//第一页start是0
DiscussService ds = new DiscussService();
String url = "#"; //页面地址

String primaryDiscussID = null;
if(StringUtils.isNotBlank(request.getParameter("primaryDiscussID"))){
	primaryDiscussID = request.getParameter("primaryDiscussID");
}

int count = (int)ds.count(new ConditionMap().add("isPrimary", false).add("primaryDiscussID", primaryDiscussID)); //总共有多少条

int start=0;//当前显示的记录是从第多少条开始的
if(StringUtils.isNotBlank(request.getParameter("start"))){
	start = NumberUtils.toInt( request.getParameter("start") );
}


int currentPageNum = start/10+1;
int pageCount = count/10+1;

//从多少条开始显示，如果记录条数太多，比如1万条，不能在页面显示一千个页数。只显示前n个和后n个
int startPageNum = currentPageNum-3<1 ? 1: currentPageNum-3;
int endPageNum = currentPageNum+3>pageCount ? pageCount: currentPageNum+3;

%>

<!-- 翻页 -->
<div class="col-sm-12 no-padding myPagination subDiscussPagination" style="">
      <a href="#<%=primaryDiscussID %>" 
      	onclick=""
      	class="subDiscussPagination-num" 
      	data-pageNum="0" >
       	首页 
      </a>
	    <%
	    for(int i = startPageNum;i<=endPageNum;i++){
	    	String selectedStyle="";
	    	if(i== currentPageNum){
	    		selectedStyle="color:#000;";
	    	}
	   		%>
	   		 <a class="subDiscussPagination-num" 
	   		 		style="<%=selectedStyle %>" 
	   		 		data-pageNum="<%=i*10-10 %>"
	   		 		href="#<%=primaryDiscussID %>"><%=i %></a> 
	   		<% 	
	    }
	    %>
      <a href="#<%=primaryDiscussID %>"
     	 class="subDiscussPagination-num" 
     	 data-pageNum="<%=(count/10)*10 %>"
      	title="尾页" >
       	尾页 
      </a>
</div>	
<!-- 翻页end -->
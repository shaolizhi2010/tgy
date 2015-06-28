<%@page import="com.tgy.service.cache.AppCache"%>
<%@page import="com.tgy.entity.SearchHistory"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="com.tgy.service.SearchHistoryService"%>
<%@page import="org.apache.commons.lang3.EnumUtils"%>
<%@page import="com.tgy.util.PageType"%>
<%@page import="com.tgy.entity.Tag"%>
<%@page import="com.tgy.util.ConditionMap"%>
<%@page import="com.tgy.statistic.service.TagService"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.PageUtil"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.statistic.service.LinkService"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

 
<div id="" class="container col-sm-12 clearfix"
	style="padding: 2px; padding-left: 20px;padding-bottom: 10px; margin-bottom: 20px;  border-bottom: 1px solid #eee;">
	
		<div class="">
			<span style="font-size: 13px;font-weight: bold;">今日热点搜索</span>
		</div>
	
	<%
		SearchHistoryService hs  = new SearchHistoryService();
 
		List<String> keywords = AppCache.hotKeywords();
		for(String k : keywords){
			%>
			<a class="index-tag" href="<%=request.getContextPath()%>/pan/<%=k %> " 
				style="">
				 <%=k %>
			</a>
			<%
		}
	%>

</div>

 
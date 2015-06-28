<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.PageType"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
String type =  request.getAttribute("type")!=null?(String)request.getAttribute("type"):"";
String tagName = request.getAttribute("tagName")!=null?(String)request.getAttribute("tagName"):"";
String firstLetter =  request.getAttribute("firstLetter")!=null?(String)request.getAttribute("firstLetter"):"";
String orderStr =  request.getAttribute("orderStr")!=null?(String)request.getAttribute("orderStr"):"";

String newSelectedClass = (!StringUtils.equalsIgnoreCase("fav", orderStr) && StringUtils.isBlank(tagName) 
		&&StringUtils.isBlank(firstLetter))?"indexAll-tab-selected":"";
String hotSelectedClass = (StringUtils.equalsIgnoreCase("fav", orderStr))?"indexAll-tab-selected":"";
String tagSelectedClass = (StringUtils.isNotBlank(tagName))?"indexAll-tab-selected":"";
String firstLetterSelectedClass = (StringUtils.isNotBlank(firstLetter) )?"indexAll-tab-selected":"";

%>
 
<div class=" col-sm-12 container no-padding indexAll-tabs ">
	<a id="indexAll-show-new" 
		href="<%=request.getContextPath()%>/share/<%=type %>" 
		class="col-sm-3 col-xs-3  no-padding indexAll-tab-a " style=" ">
		<span class="indexAll-tab <%=newSelectedClass%>  ">全部</span> </a>
	<a id="indexAll-show-hot" 
		href="<%=request.getContextPath()%>/share/<%=type %>?orderStr=fav&days=-1" 
		class="col-sm-3 col-xs-3  no-padding indexAll-tab-a " style=" ">
		<span class="indexAll-tab <%=hotSelectedClass%>  ">最热</span> </a>
	<a id="indexAll-show-tag"  href="#" class="col-sm-3 col-xs-3  no-padding indexAll-tab-a" style=" ">
		<span class="indexAll-tab <%=tagSelectedClass%> ">标签</span> </a>
	<%
		if(StringUtils.equals(type, PageType.resource.name())){
	 %>
	<a id="indexAll-show-firstLetter" href="#" class="col-sm-3 col-xs-3 no-padding indexAll-tab-a" style=" ">
		<span class="indexAll-tab <%=firstLetterSelectedClass%> ">首字母</span> </a>
	<%} %>
</div>
 
 
	
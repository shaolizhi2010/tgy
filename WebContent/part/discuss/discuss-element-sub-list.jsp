<%@page import="org.apache.commons.lang3.math.NumberUtils"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.ConditionMap"%>
<%@page import="com.tgy.entity.Discuss"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.service.DiscussService"%>
<%@ page language="java" pageEncoding="UTF-8"%>
 
 
<%
DiscussService ds = new DiscussService();

String primaryDiscussID = null;
if(StringUtils.isNotBlank(request.getParameter("primaryDiscussID"))){
	primaryDiscussID = request.getParameter("primaryDiscussID");
}

int start =0;
if(request.getParameter("start")!=null){
	start = Integer.parseInt(request.getParameter("start"));
}

int showCount = 3;//显示多少条
if(StringUtils.isNotBlank(request.getParameter("count"))){
	showCount = NumberUtils.toInt( request.getParameter("count")) ;
}
%>
 
<div class="col-sm-12 no-padding discuss-element-sub-list" style="" >
	<%
	List<Discuss> subDiscuss = ds.list(new ConditionMap().add("primaryDiscussID", primaryDiscussID), "createDate", start,showCount);
	//Collections.reverse( subDiscuss); 
	for(Discuss subD : subDiscuss){
		%>
		<jsp:include page="discuss-element.jsp">
			<jsp:param name="discussID" value='<%=subD.id.toString()%>'/>
		</jsp:include>
		<%
	}
	%>
</div>
	 

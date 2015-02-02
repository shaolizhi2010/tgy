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

long total = ds.count("primaryDiscussID", primaryDiscussID);

int showCount = 3;//显示多少条
if(StringUtils.isNotBlank(request.getParameter("count"))){
	showCount = NumberUtils.toInt( request.getParameter("count")) ;
}
%>
<div class="col-sm-12 container discuss-element-sub-container  " style="background-color: #f5f5f5;border-radius:5px;">
	<jsp:include page="discuss-element-sub-list.jsp">
		<jsp:param name="primaryDiscussID" value='<%=primaryDiscussID%>'/>
		<jsp:param name="start" value='<%=start%>'/>
		<jsp:param name="showCount" value='<%=showCount%>'/>
	</jsp:include>
	<%
		if(showCount ==3 && total>3){//显示3条，说明是默认显示，此时分页条还没有显示。如果总数大于三条，则显示 ‘显示全部’ 按钮
			%>
			<!-- 显示全部    工具条-->
			<div class="col-sm-12   discuss-showAll-container">
				<span>还有<%= total-3 %>条回复</span> <a href="#<%=primaryDiscussID%>" class="discuss-showAll-btn">点击查看</a>
			</div>
			<%
		}
		//显示大于3条(一般是10条)，说明这时候已经不是默认显示了，不再显示‘显示全部’按钮，如果总数大于10，显示分页
		else if(showCount>3 && total>10){
			%>
			<!-- 翻页 -->
			 <jsp:include page="subDiscussPagination.jsp">
			 	<jsp:param name="start" value='0'/>
			 </jsp:include>
			<%
		}
	%>
</div>

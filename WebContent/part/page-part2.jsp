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
int start =   (Integer)request.getAttribute("pageStart");
long count =  (Long)request.getAttribute("count");

%>
<!-- 显示网址页面开始 -->
<div id="pages-part" class="col-sm-12 no-padding ">
 
	<div class="  col-sm-12 no-padding" style="background: #fff;">
	<%
	List< Page> pages = (List<Page>)request.getAttribute("pages");
 	if(pages==null){
 		return;
 	}
	for (Page p : pages) {
	%>
		<jsp:include page="share/shareElement2.jsp">
			<jsp:param name="pageID" value='<%=p.id.toString() %>'/>
		</jsp:include>
	<%
	}//end for
 	
 	 
 	%>
	<!-- 翻页 -->
	<jsp:include page="pagination.jsp">
		<jsp:param name="start" value='<%=start+"" %>'/>
		<jsp:param name="count" value='<%=count+"" %>'/> 
	</jsp:include>	
		
	</div>

	
 		 
</div>
<!-- 显示网址页面结束 -->
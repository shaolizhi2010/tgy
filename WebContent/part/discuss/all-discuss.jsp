<%@page import="org.bson.types.ObjectId"%>
<%@page import="com.tgy.util.DiscussUtils"%>
<%@page import="com.tgy.util.IpUtils"%>
<%@page import="java.util.Random"%>
<%@page import="com.tgy.entity.Discuss"%>
<%@page import="com.tgy.service.DiscussService"%>
<%@page import="java.util.Collections"%>
<%@page import="com.tgy.util.ConditionMap"%>
<%@page import="com.tgy.service.group.GroupDiscussService"%>
<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.entity.GroupDiscuss"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@page import="com.tgy.entity.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.util.C"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="com.tgy.util.U"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>

<%@include file="../login-user-data.jsp"  %>  

<%
DiscussService ds = new DiscussService();  

String sourceBoardID = null;
if(request.getParameter("sourceBoardID")!=null){
	sourceBoardID = request.getParameter("sourceBoardID");
}

String sourceBoardName  = request.getParameter("sourceBoardName");
if(StringUtils.isBlank(sourceBoardName)){
	sourceBoardName = "大家爱吐槽";
}

long discussCount = ds.count();

int start =0;
if(request.getParameter("start")!=null){
	start = Integer.parseInt(request.getParameter("start"));
}

%>
<input type="hidden" id="sourceBoardName" value="<%=sourceBoardName%>">
<input type="hidden" id="replyToDiscussID" value="">
<%
	UserService uService = new UserService(); 
	
	List<Discuss> discusses = ds.list(new ConditionMap().add("targetIsAllSite", true).add("isPrimary", true) , "-lastModifyDate",start, 10);
	//List<ObjectId> discussIds = ds.findIds(ds.createQuery(new ConditionMap().add("targetIsAllSite", true).add("isPrimary", true) , "-lastModifyDate",start, 10 )) ;
	Collections.reverse( discusses); 
%>

	<div class=" container col-sm-12 sub-page-with-title"  >
		<div class="">
			<span style="font-size: 13px;font-weight: bold;">盒子论坛 - <%=sourceBoardName %></span>
			<a href="#createDiscussMessage" style="float: right;font-size: 13px;font-weight: bold;">[ 我要发帖 ]</a>
		</div>
		<div class="col-sm-12 sub-page-body  no-padding" 
			
			style="padding:0px;padding-top:10px;  ">
		
			<div id="all-discuss-container">
				<%
				int groupIndex=0;
				for(Discuss d : discusses){
				%>
				<!-- 一组消息开始，包括主消息和子消息 -->
				<div class="" style='<%=groupIndex++%2==0?"background-color:#fafafa":""%>'>
					 <jsp:include page="discuss-element-group.jsp">
						<jsp:param name="primaryDiscussID" value='<%=d.id.toString()%>'/>
					</jsp:include>
				</div>
				<!-- 一组消息，包括主消息和子消息 end -->
				<%
					}
				%>	
			</div>
		
		<div class="col-sm-12" style="border: 1px solid #eee;margin-top: 10px;margin-bottom: 10px;"></div>
		<!-- 翻页 -->
		 <jsp:include page="discussPagination.jsp">
		 	<jsp:param name="start" value='<%=start %>'/>
		 </jsp:include>
		
		<div class="col-sm-12" style="margin-top: 40px;" >
			 <div class="col-sm-12" style=" padding: 10px;background-color: #f8f8f8; ">
				<div  style=" ">
		         		<textarea  style="border:1px solid #ccc;" id="createDiscussMessage" 
		         			data-func-name="createDiscussForAllFunction" 
		         			class="form-control enterInput hover-focus" rows="4" 
		         			placeholder="快到碗里来说两句"></textarea> 
	         	</div>
				<div  style="margin-top: 5px; " >
	         	    <a href="javascript:void(0)">
	         	    <span onclick="createDiscussForAllFunction()" id="createFolder-ok" type="button"
                      class="pull-right" style="width:45px;height:35px;line-height:35px;text-align:center;border-radius:5px; background-color: #4cbb1f;color: #fff;font-size: 13px;font-weight: bold;">
                      		发送
                      </span>
                     </a>
              	</div>
			</div> 	
 		</div>
 	</div>
</div>

 
<script  src="<%=request.getContextPath()%>/myjs/discuss.js" defer="defer"></script>
 
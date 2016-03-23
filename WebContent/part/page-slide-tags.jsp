<%@page import="com.tgy.service.cache.AppCache"%>
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

<%
String tagName = request.getAttribute("tagName")!=null?(String)request.getAttribute("tagName"):null;
//if(StringUtils.isNotBlank(tagName)){//已选择查看某个标签，不再显示标间列表
//	return;
//}
String type =  request.getAttribute("type")!=null?(String)request.getAttribute("type"):null;
PageType typeEnum = null;
if(StringUtils.isBlank(type) || !EnumUtils.isValidEnum(PageType.class, type)){
	type = null;
	typeEnum = PageType.resource;//default;
}
else{
	typeEnum = PageType.valueOf(type);
}
%>
<div id="" class="container col-sm-12 clearfix"
	style="padding: 2px; padding-left: 20px;padding-bottom: 10px; margin-bottom: 20px;   ">
	
		<div class="">
			<span style="font-size: 13px;font-weight: bold;">热点专题</span>
		</div>
	
	<%
		 
		List<Tag> tags = AppCache.tags(typeEnum);
		for(Tag t : tags){
			%>
			<a class="index-tag" href="<%=request.getContextPath()%>/share/<%=type %>?tagName=<%=t.name %> " 
				style="">
				 <%=t.name %>
			</a>
			<%
		}
	%>
				<div class="col-md-12   topic-row-title " >
					<a class="pull-right " style="margin-right:10px; "  href="<%=request.getContextPath()%>/topic"  >[ >>查看全部专题]</a>
				</div>
</div>


<script src="<%=request.getContextPath()%>/myjs/index-all-tags.js"  ></script>
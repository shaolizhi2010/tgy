<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="com.tgy.statistic.service.TagService"%>
<%@page import="com.tgy.dao.UserDao"%>
<%@page import="com.tgy.util.URLUtils"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>
<%@page language="java" pageEncoding="UTF-8"%>

<div id="public-tag-pop" class="col-sm-12 no-padding sub-page-with-title" >
	<div class="sub-page-title">
		<span>热门用户</span>
	</div>
	<div class="sub-page-body">
	<%
 	UserService fs = new UserService();
 	List<User> users = fs.list("-showTimes",20);
 	 	for (User user : users) {
 	 		String name = user.name;
 			if (name != null && name.length() > 12) {
 				name = name.substring(0, 12) + "..";
 			}
 	 		%> 
 	 		<a class="col-sm-12 public-folder-element hoverAble" href="<%=request.getContextPath()%>/u/<%=user.id %>" >
 	 			<span style="padding-left: 10px;"><%=name %></span> 
 	 			<!-- 
 	 			<div class="col-sm-1 pull-right glyphicon glyphicon glyphicon-plus" style="font-size: 8px;" title="拷贝到我的收藏夹"></div>
 	 			 -->
 	 		</a>
 	 		<%
 	 	}
 	%>
	</div>
</div>

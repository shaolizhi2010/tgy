<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
</head>

<body>

    <div class="container">
        <div class="row clearfix c-menu-sub">
            <div class="col-md-12 column "  >

                 <div class="col-md-2 column">
                 	<a href="#">返利成功</a>
                 </div>
                 <div class="col-md-2 column">
                 	<a href="#">菜单一</a>
                 </div>
                 <div class="col-md-2 column">
                 	<a href="#">菜单一</a>
                 </div>
                 <div class="col-md-2 column">
                 	<a href="#">菜单一</a>
                 </div>
                 <div class="col-md-2 column">
                 	<a href="#">菜单一</a>
                 </div>

            </div>
            
        </div>
    </div>
    
<!-- 本页菜单开始 -->
    <div class="container">
        <div class="row clearfix">
            <div class="col-md-12 column">
                <!--  content here  -->

					<table class="table">
						<thead>
							<tr class="success">
								<th>会员</th>
								<th>注册时间</th>
								<th>openid</th>
								<th>上次登录时间</th>
								<th>istemp</th>
								<th>登录次数</th>
								<th>访问次数</th>
							</tr>
						</thead>
						<tbody>
	
							<%
								UserService userService = new UserService();
								
								
								List<User> users = userService.list("-createDate",150);
								
								 for(User user: users){
									 
							%>
	
							<tr class="">
								<td><a href="<%=request.getContextPath()%>/u/<%=user.id%>"><%= user.name %></a>								</td>
								<td> 
									<%=user.createDate%>
								 </td>
								<td> 
									<%=user.openID%>
								 </td>
								 <td> 
									<%=user.lastLoginDate%>
								 </td>
								<td> 
									<%=user.isTemp%>
								 </td>
								 								<td> 
									<%=user.loginTimes%>
								 </td>
								 								<td> 
									<%=user.showTimes%>
								 </td>
							</tr>
							<%
								}
							%>
	
	
						</tbody>
					</table>

            </div>
 
        </div>
    </div>
</body>
</html>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="auth.jsp" %>
<%@include file="head.jsp" %>
<!DOCTYPE html>
<html>
<head>
 
    <div class="container">
        <div class="row clearfix c-menu">
            <div class="col-md-12 column "  >

                 <div class="col-md-2 column">
                 	<a href="<%=request.getContextPath() %>/benefit.success.jsp">返利成功</a>
                 </div>
                 <div class="col-md-2 column">
                 	<a href="<%=request.getContextPath() %>/benefit.wait.jsp">返利申请历史</a>
                 </div>
                 <div class="col-md-2 column">
                 	<a href="<%=request.getContextPath() %>/takeMoney.wait.jsp">等待提现</a>
                 </div>
                 <div class="col-md-2 column">
                 	<a href="<%=request.getContextPath() %>/user.list.jsp">用户列表</a>
                 </div>
                 <div class="col-md-2 column">
                 	<a href="<%=request.getContextPath() %>/accountDetail.list.jsp">用户明细账</a>
                 </div>
                 <div class="col-md-2 column">
                 	<a href="<%=request.getContextPath() %>/advertisement.create.jsp">添加广告</a>
                 </div>
                 <div class="col-md-2 column">
                 	<a href="<%=request.getContextPath() %>/advertisement.list.jsp">所有广告</a>
                 </div>
                 <div class="col-md-2 column">
                 	<a href="<%=request.getContextPath() %>/order.xml.jsp">订单xml</a>
                 </div>
                 <div class="col-md-2 column">
                 	<a href="<%=request.getContextPath() %>/benefit.xml.jsp">返利xml</a>
                 </div>
                 <div class="col-md-2 column">
                 	<a href="<%=request.getContextPath() %>/order.db.jsp">跟踪订单</a>
                 </div>
                 <div class="col-md-2 column">
                 	<a href="<%=request.getContextPath() %>/benefit.db.jsp">跟踪返利</a>
                 </div>
            </div>
            
        </div>
    </div>
 

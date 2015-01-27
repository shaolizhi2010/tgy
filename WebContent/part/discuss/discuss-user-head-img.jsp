<%@page import="java.util.Random"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" pageEncoding="UTF-8"%>
 
 <%
 //随机头像
 int radNum = 1;
 if(request.getParameter("radNum")!=null){
	 try{
		 radNum = Integer.parseInt(request.getParameter("radNum"));
	 }catch(Exception e){
	 }
 }
 else{
	 radNum = new Random().nextInt(127);
 }
 
 //head url
 String headUrl  = request.getParameter("headUrl");
 String defaultHeadUrl = request.getContextPath()+"/images/ava/ava127.png";//default
 if(StringUtils.isBlank(headUrl) || StringUtils.equals(headUrl, "null")){	//no head img,use random one
	 headUrl = request.getContextPath()+"/images/ava/ava"+radNum+".png" ;
 }
 
 
 
 //图片大小
 int size = 40;
 if(request.getParameter("size")!=null){
	 try{
		 size = Integer.parseInt(request.getParameter("size"));
	 }catch(Exception e){
	 }
 }
 
 %>
 
<img class="headImg-<%=size %> img-responsive"   alt="用户头像" src="<%=defaultHeadUrl %>" data-original="<%=headUrl %>" >
 

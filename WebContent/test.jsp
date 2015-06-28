<%@page import="com.tgy.service.WebInfo"%>
<%@page import="com.tgy.util.WebInfoUtil"%>
<%@page import="java.nio.charset.Charset"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
WebInfo info = new WebInfoUtil().info("http://www.baidu.com", false);//http://pan.baidu.com/s/1dDmzV7j

out.println("title : "+ info.title);

%>

<%@page import="com.tgy.service.ziyuan.BaiduPanDigService"%>
<%@page import="com.tgy.service.article.HaoSouWemediaService"%>
<%@page import="java.nio.charset.Charset"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
//new HaoSouWemediaService().digAndSave();
new BaiduPanDigService().digAndSave();

%>

<%@page import="com.tgy.tools.CovertDataTool"%>
<%@page import="com.tgy.entity.group.InterestGroupPage"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="com.tgy.entity.group.InterestGroupFolder"%>
<%@page import="com.tgy.service.group.InterestGroupPageService"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.service.FolderService"%>
<%@page import="com.tgy.entity.User"%>
<%@page import="com.tgy.entity.group.InterestGroup"%>
<%@page import="com.tgy.service.group.InterestGroupFolderService"%>
<%@page import="com.tgy.service.group.InterestGroupService"%>
<%@page import="com.tgy.service.UserService"%>
<%@page import="java.nio.charset.Charset"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
String userID = request.getParameter("u");// "5462c7dd7890e2d092e823ac";
String groupID = request.getParameter("g");
CovertDataTool.userToGroup(userID, groupID);
%>

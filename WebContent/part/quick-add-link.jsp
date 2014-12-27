<%@page import="com.tgy.dao.FolderDao"%>
<%@page import="com.tgy.util.URLUtils"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>

 
 	 
	<div   class="col-md-12   no-padding"> 
		<div class="  col-md-2 no-padding" style="padding-right: 5px; ">
		<input  class="  col-md-12 " style="height: 35px; border-radius:5px;border: 2px solid #ddd;" placeholder="网站分类" />
		</div>
		<div class="  col-md-8 no-padding" style="padding-right: 5px; ">
		<input class="  col-md-12 "    style="height: 35px; border-radius:5px;border:2px solid #ddd;" placeholder="要收藏的网址网址" />
		</div>
		<div class="col-md-2 no-padding" style="padding-right: 5px; ">
		<a class="btn " style="border: 1px solid #ccc;display: block;border-radius:5px;"><span   style="font-size: 10px;font-weight:550 ;color: #666;" >添加网址</span> </a>
		</div>
		
	</div>
	
	


<%@page import="com.tgy.service.JDRedirectService"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.commons.lang3.math.NumberUtils"%>
<%@page import="com.tgy.entity.group.InterestGroupPage"%>
<%@page import="com.tgy.statistic.entity.Link"%>
<%@page import="com.tgy.statistic.service.LinkService"%>
<%@page import="com.tgy.service.PageService"%>
<%@page import="com.tgy.App"%>
<%@page import="com.tgy.util.URLUtils"%>
<%@page import="com.tgy.web.vo.BookmarkData"%>
<%@page import="com.tgy.entity.Folder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.util.U"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.entity.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<%
JDRedirectService jd =   JDRedirectService.getInstance();
String tagName = request.getAttribute("tagName")!=null?(String)request.getAttribute("tagName"):"";
List<String> adWords = new ArrayList();
adWords.add(tagName);
adWords.add("移动硬盘");
adWords.add("充电宝");
adWords.add("新手机");
adWords.add("无线路由");
adWords.add("流量卡");
adWords.add("蓝牙耳机");
adWords.add("超极本");
adWords.add("iphone6");
adWords.add("移动电源");
adWords.add("随身wifi");
adWords.add(tagName);
%>
<!-- 显示广告页面开始 -->
<div id="ad1" class="col-sm-12 no-padding " style="background-color: #fafafa;min-height: 30px;line-height: 30px;
	text-align: center;">
	
	<%
		for(String w : adWords){
			if(StringUtils.isBlank(w))continue;
			%>
			<div class="col-sm-3   no-padding">
				<a target="_blank" href="<%=jd.redirect(w) %>" style="font-size: 15px;">
					<!-- 
					<img style="width: 18px; height: 18px; float: left; margin-right: 10px; display: block;" 
					alt="京东网上..." 
					src="/images/icon/9346c5fd146f3c58f293968771247cc0 ">  -->
					京东-<%=w %></a>
				
			</div>
			
			<%
		}
	%>


 
	
 		 
</div>
<!-- 显示广告页面结束 -->

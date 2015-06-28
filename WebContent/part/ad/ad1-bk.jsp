<%@page import="java.util.Random"%>
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
List<String> colors = new ArrayList();
colors.add("#ffdcd5");
colors.add("#e2efcc");
colors.add("#f9e7d8");
colors.add("#a6e0eb");
colors.add("#dfe2ef");



JDRedirectService jd = JDRedirectService.getInstance();
String tagName = request.getAttribute("tagName")!=null?(String)request.getAttribute("tagName"):"";
List<String> adWords = new ArrayList();
adWords.add(tagName);
adWords.add("移动硬盘");
adWords.add("充电宝");
adWords.add("新手机");
adWords.add("路由器");
adWords.add("移动电源");
adWords.add("蓝牙耳机");
adWords.add("笔记本电脑");
adWords.add("上网卡");
adWords.add("iphone");
adWords.add("随身wifi");
adWords.add(tagName);
%>
<!-- 显示广告页面开始 -->
<div id="ad1" class="col-sm-12" style="background-color: #fefefe;min-height: 30px;line-height: 30px;
	text-align: center;padding: 5px;">
	
	<%
		for(String w : adWords){
			if(StringUtils.isBlank(w))continue;
			%>
			<div class="col-sm-3     "   >
				<a target="_blank" href="<%=jd.redirect(w) %>" style="color:#666;-moz-border-radius: 10px;-webkit-border-radius: 10px;border-radius: 10px;padding:5px;padding-left:10px;padding-right:10px; font-size: 15px;background-color : <%=colors.get(new Random().nextInt(colors.size())) %>;"><%=w %></a>
			</div>
			
			<%
		}
	%>


 
	
 		 
</div>
<!-- 显示广告页面结束 -->

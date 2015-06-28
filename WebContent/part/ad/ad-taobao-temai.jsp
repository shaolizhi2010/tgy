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
String tagName = request.getAttribute("tagName")!=null?(String)request.getAttribute("tagName"):"";
if(StringUtils.isBlank(tagName)){
	tagName = "九块九";
}

List<Page> ads = new ArrayList<Page>();

Page p = new Page();

p = new Page();
p.url = "http://ai.taobao.com/search/index.htm?source_id=midPage&pid=mm_41911274_8730892_31742416&unid=&key="+ URLEncoder.encode(tagName,"utf-8") +"&clk1=7e5130bca4fff84f5ac08b12b0531c33#app_pvid=200_10.103.30.19_8034_1429409186860";
p.name = "爱淘宝-"+tagName;
ads.add(p );

p = new Page();
p.url = "http://temai.taobao.com/event7263.htm?pid=mm_41911274_8730892_31742416";
p.name = "淘宝特卖-精品疯抢";
ads.add(p );

p = new Page();
p.url = "http://temai.taobao.com/event7244.htm?pid=mm_41911274_8730892_31742416";
p.name = "一折清仓";
ads.add(p );

p = new Page();
p.url = "http://temai.taobao.com/event6892.htm?pid=mm_41911274_8730892_31742416";
p.name = "9块9包邮";
ads.add(p );
 
p = new Page();
p.url = "http://temai.taobao.com/event6603.htm?pid=mm_41911274_8730892_31742416";
p.name = "热卖新品疯抢ing";
ads.add(p );

p = new Page();
p.url = "http://s.click.taobao.com/t?e=m%3D2%26s%3DingYQykXj30cQipKwQzePCperVdZeJviK7Vc7tFgwiFRAdhuF14FMfaxP3RhTKKKJ1gyddu7kN9OO5silldrz%2B7yyPJcya%2BXVk8S82OBMJ1JWT4SlRN1ZKUuZxIcp9pfUIgVEmFmgnbDX0%2BHH2IEVa7A5ve%2FEYDnFveQ9Ld2jopwTqWNBsAwm%2BIKl4JSR4lzomfkDJRs%2BhU%3D";
p.name = "天猫精选品牌活动";
ads.add(p );

p = new Page();
p.url = "http://temai.taobao.com/event6188.htm?pid=mm_41911274_8730892_31742416";
p.name = "男人专场";
ads.add(p );

p = new Page();
p.url = "http://ai.taobao.com/search/index.htm?source_id=midPage&pid=mm_41911274_8730892_31742416&unid=&key="+ URLEncoder.encode("充电宝","utf-8") +"&clk1=7e5130bca4fff84f5ac08b12b0531c33#app_pvid=200_10.103.30.19_8034_1429409186860";
p.name = "爱淘宝-充电宝" ;
ads.add(p );

p = new Page();
p.url = "http://ai.taobao.com/search/index.htm?source_id=midPage&pid=mm_41911274_8730892_31742416&unid=&key="+ URLEncoder.encode("无线路由","utf-8") +"&clk1=7e5130bca4fff84f5ac08b12b0531c33#app_pvid=200_10.103.30.19_8034_1429409186860";
p.name = "爱淘宝-无线路由" ;
ads.add(p );

p = new Page();
p.url = "http://ai.taobao.com/search/index.htm?source_id=midPage&pid=mm_41911274_8730892_31742416&unid=&key="+ URLEncoder.encode("流量卡","utf-8") +"&clk1=7e5130bca4fff84f5ac08b12b0531c33#app_pvid=200_10.103.30.19_8034_1429409186860";
p.name = "爱淘宝-流量卡" ;
ads.add(p );

p = new Page();
p.url = "http://ai.taobao.com/search/index.htm?source_id=midPage&pid=mm_41911274_8730892_31742416&unid=&key="+ URLEncoder.encode("蓝牙耳机","utf-8") +"&clk1=7e5130bca4fff84f5ac08b12b0531c33#app_pvid=200_10.103.30.19_8034_1429409186860";
p.name = "爱淘宝-蓝牙耳机" ;
ads.add(p );




p = new Page();
p.url = "http://ai.taobao.com/search/index.htm?source_id=midPage&pid=mm_41911274_8730892_31742416&unid=&key="+ URLEncoder.encode(tagName,"utf-8") +"&clk1=7e5130bca4fff84f5ac08b12b0531c33#app_pvid=200_10.103.30.19_8034_1429409186860";
p.name = "爱淘宝-"+tagName;
ads.add(p );



%>
<!-- 显示广告页面开始 -->
<div id="ad1" class="col-sm-12 no-padding " style="background-color: #fafafa;min-height: 30px;line-height: 30px;
	text-align: center;">
	
	<%
		for(Page ad : ads){
			if(StringUtils.isBlank(ad.name))continue;
			%>
			<div class="col-sm-3   no-padding">
				<a target="_blank" href="<%=ad.url %>" style="font-size: 15px;">
					<!-- 
					<img style="width: 18px; height: 18px; float: left; margin-right: 10px; display: block;" 
					alt="京东网上..." 
					src="/images/icon/9346c5fd146f3c58f293968771247cc0 ">  -->
					 <%=ad.name %></a>
				
			</div>
			
			<%
		}
	%>

 
 
	
 		 
</div>
<!-- 显示广告页面结束 -->

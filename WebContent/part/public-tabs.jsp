<%@page import="com.tgy.util.PageType"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<div class=" col-sm-7 container no-padding tabs ">
	<a id="menu-index-<%=PageType.resource %>" href="<%=request.getContextPath()%>/share/<%=PageType.resource %>" class="col-sm-2 col-xs-3  no-padding" style=" "><span class="tabs-tab   hoverAble-head">全部资源</span> </a>
	<a id="menu-index-fuli"   href="<%=request.getContextPath()%>/share/resource?needfulidou=1" class="col-sm-2  col-xs-3  no-padding" style=" "><span class="tabs-tab hoverAble-head">福利资源</span> </a>
	<a id="menu-pan"  href="<%=request.getContextPath()%>/pan" class="col-sm-2 col-xs-3    no-padding  " style=" "><span class="tabs-tab hoverAble-head">搜资源</span> </a>
	<!-- 
	<a id="menu-index-hot-user" href="<%=request.getContextPath()%>/index-hot-user.jsp" class="  col-sm-2 hidden-xs  no-padding " style=" "><span class="tabs-tab hoverAble-head">收藏夹</span> </a>
	 -->
</div>
<div class=" col-sm-5 container no-padding tabs hidden-xs ">
	<div  class="bdsharebuttonbox col-sm-2 col-xs-3  no-padding"><a href="#" class="bds_more" data-cmd="more">分享到：</a></div>
	<span style="float: right;font-size: 14px;color: #666;margin-right: 30px;">    资源qq群( 禁H ) : 136145733  /  330400215  </span> 
  
</div> 
<!-- 
<a id="menu-index-all" href="<%=request.getContextPath()%>/share" class=" col-sm-2 col-xs-3  no-padding " style=" "><span class="tabs-tab hoverAble-head">全部</span> </a>
 -->
	
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"各种动漫、电影、美剧资源，福利不多说！","bdMini":"2","bdMiniList":

["mshare","tsina","weixin","tieba","douban","sqq","qzone","copy"],"bdPic":"http://www.webhezi.com/images/sharecat.jpg","bdStyle":"0","bdSize":"16"},"share":

{"bdSize":16}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement

('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
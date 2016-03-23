 
<%@page import="java.net.URLDecoder"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.tgy.entity.PanSearchCache"%>
<%@page import="com.tgy.service.PanSearchCacheService"%>
<%@page import="com.tgy.util.U"%>
<%
String name = U.filterCharacter(request.getParameter("name"));
String comment = U.filterCharacter(request.getParameter("comment"));
String imgSrc = U.filterCharacter(request.getParameter("imgSrc"));
String url = U.filterCharacter(request.getParameter("url"));
String key = U.filterCharacter(request.getParameter("key"));

if(StringUtils.isNotBlank(name)){
	name = URLDecoder.decode(name.replaceAll("%", "%25"), "utf-8");
}
if(StringUtils.isNotBlank(comment)){
	comment = URLDecoder.decode(comment.replaceAll("%", "%25"), "utf-8");
}
if(StringUtils.isNotBlank(imgSrc)){
	imgSrc = URLDecoder.decode(imgSrc.replaceAll("%", "%25"), "utf-8");
}
if(StringUtils.isNotBlank(url)){
	url = URLDecoder.decode(url.replaceAll("%", "%25"), "utf-8");
}
if(StringUtils.isNotBlank(key)){
	key = URLDecoder.decode(key.replaceAll("%", "%25"), "utf-8");
}
PanSearchCacheService s = new PanSearchCacheService();

PanSearchCache cache = new PanSearchCache();

cache.title = name;
cache.name = name;

cache.comment = comment;
cache.description = comment;
cache.summary=comment;
cache.imgSrc = imgSrc;

cache.url = url;

cache.key = key;

s.save(cache);
%>
 
<%@page import="java.net.URLEncoder"%>
<%@page import="org.apache.commons.collections.CollectionUtils"%>
<%@page import="com.tgy.entity.Page"%>
<%@page import="java.util.List"%>
<%@page import="com.tgy.util.ConditionMap"%>
<%@page import="com.tgy.service.SearchHistoryService"%>
<%@page import="com.tgy.entity.SearchHistory"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tgy.entity.PanSearchCache"%>
<%@page import="com.tgy.service.PanSearchCacheService"%>
<%@page import="com.tgy.util.U"%>

<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<%
List<String> needDigs = new ArrayList<String>();

SearchHistoryService ss = new SearchHistoryService();

try {
	ConditionMap conditions = new ConditionMap();
	conditions.add("lastModifyDate >", U.dateTime(-30)); //n天以内的
	conditions.add("times >", 3); //搜索2次以上
	
	List<SearchHistory> histories =  ss.list(conditions, "-lastModifyDate", 50);
	
	PanSearchCacheService cacheSvs = new PanSearchCacheService();
	for(SearchHistory h : histories ){
		List<Page> caches = cacheSvs.ListByKey(h.keyword+"0"); //key = keyword+"0"
		if(CollectionUtils.isEmpty(caches)){ //没有缓存
			needDigs.add(h.keyword);
			if(needDigs.size()>5){ //每次取出5个需要搜索的关键字
				break;
			}
		}
		else{
			//System.out.print(h.keyword + " exist ! ");
		}
		
	}
	
	String json = U.toJson(needDigs);
	
	json = URLEncoder.encode(json,"utf-8");
	
	response.setCharacterEncoding("utf-8");
	response.getOutputStream().write(json.getBytes("UTF-8"));
	response.getOutputStream().flush();
	
	//out.print(json);
	
	
	//U.message(response , json);
	return;
} catch (Exception e) {
	//U.resFailed(res, e.getMessage());
}
%>
 
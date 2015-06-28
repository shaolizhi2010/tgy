package com.tgy.service.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tgy.entity.Discuss;
import com.tgy.entity.Page;
import com.tgy.entity.Reply;
import com.tgy.entity.SearchHistory;
import com.tgy.entity.Tag;
import com.tgy.service.DiscussService;
import com.tgy.service.ReplyService;
import com.tgy.service.PageService;
import com.tgy.service.SearchHistoryService;
import com.tgy.statistic.service.TagService;
import com.tgy.util.ConditionMap;
import com.tgy.util.PageType;
import com.tgy.util.U;

public class AppCache {

	public static Map<String, CacheObject> cacheMap = new HashMap<String, CacheObject>();
	public static Map<String, CacheObject> repliesMap = new LinkedHashMap<String, CacheObject>();
	
	
	public static List<Reply> repliesByToID(String toID){
		CacheObject cache = repliesMap.get(toID);
		if( cache == null
				|| System.currentTimeMillis() - cache.timestamp > 30 * 1000){
			List<Reply> replies = new ReplyService().byToID(toID,3);
			Collections.reverse(replies);
			
			cache = new CacheObject(); // new one
			cache.timestamp = System.currentTimeMillis();
			cache.value = replies;
			
			if(repliesMap.size()>500){//存的太多了 清一清
				repliesMap.clear();
				System.out.println("AppCache :: repliesByPageID 存的太多了 清一清");
			} 
			repliesMap.put(toID, cache);
		}
		 
		return (List<Reply>)  cache.value;
	}
	
	public static List<Page> defaultPages() {

		String key = "defaultPages" ;
		CacheObject cache = cacheMap.get(key);
		if (cache == null
				|| System.currentTimeMillis() - cache.timestamp > 5 * 60 * 1000) {// 缓存时间检查


			PageService ps = new PageService();
			ConditionMap conditions = new ConditionMap();

			conditions.add("type", PageType.resource).add("createDate >", U.dateTime(-365)) // n天以内的，链接太久了容易失效
					.add("isShare", true);

			List<Page> pages = ps.list(conditions, "-lastModifyDate,-createDate", 0, 10);
			
			cache = new CacheObject(); // new one
			cache.timestamp = System.currentTimeMillis();
			cache.value = pages;
			set(key, cache);
		}

		return (List<Page>)  cache.value;
	}
	
	public static void defaultPagesClear(){
		String key = "defaultPages" ;
		CacheObject cache = cacheMap.get(key);
		PageService ps = new PageService();
		ConditionMap conditions = new ConditionMap();

		conditions.add("type", PageType.resource).add("createDate >", U.dateTime(-365)) // n天以内的，链接太久了容易失效
				.add("isShare", true);

		List<Page> pages = ps.list(conditions, "-lastModifyDate,-createDate", 0, 10);
		
		cache = new CacheObject(); // new one
		cache.timestamp = System.currentTimeMillis();
		cache.value = pages;
		set(key, cache);
	}
	
	//需改进 如果页数很多 那么缓存会变的很大 应对缓存的页数做限制
	public static List<Discuss> discusses(int start) {

		String key = "discusses_"+start ;
		CacheObject cache = cacheMap.get(key);
		if (cache == null
				|| System.currentTimeMillis() - cache.timestamp > 5 * 60 * 1000) {// 缓存时间检查

			DiscussService ds = new DiscussService();  
			List<Discuss> discusses = ds.list(new ConditionMap().add("targetIsAllSite", true).add("isPrimary", true) , "-lastModifyDate",start, 10);
			Collections.reverse( discusses); 
			
			cache = new CacheObject(); // new one
			cache.timestamp = System.currentTimeMillis();
			cache.value = discusses;
			set(key, cache);
		}

		return (List<Discuss>)  cache.value;
	}
	
	
	public static void discussesClear() {
		String key = "discusses_0" ;
		CacheObject cache = cacheMap.get(key);

		DiscussService ds = new DiscussService();  
		List<Discuss> discusses = ds.list(new ConditionMap().add("targetIsAllSite", true).add("isPrimary", true) , "-lastModifyDate",0, 10);
		Collections.reverse( discusses); 
		
		cache = new CacheObject(); // new one
		cache.timestamp = System.currentTimeMillis();
		cache.value = discusses;
		set(key, cache);
	}
	
	public static List<Tag> tags(PageType TypeEnum) {

		String key = "tags_" + TypeEnum.toString();
		CacheObject cache = cacheMap.get(key);
		if (cache == null
				|| System.currentTimeMillis() - cache.timestamp >   3600 * 1000) {// 缓存时间检查

			TagService ts = new TagService();
			List<Tag> tags = ts.list(new ConditionMap().add("type", TypeEnum),
					"-favScore", 0, 20);

			cache = new CacheObject(); // new one
			cache.timestamp = System.currentTimeMillis();
			cache.value = tags;
			set(key, cache);
		}

		return (List<Tag>) cache.value;
	}
	
	//热搜关键字
	public static List<String> hotKeywords( ) {

		String key = "hotKeywords" ;
		CacheObject cache = cacheMap.get(key);
		if (cache == null
				|| System.currentTimeMillis() - cache.timestamp >   5*60 * 1000) {// 缓存时间检查

			List<String> keywords = new ArrayList<String>();
			SearchHistoryService hs  = new SearchHistoryService();
		
			List<SearchHistory> histories =  hs.list(new ConditionMap().add("createDate >", U.dateTime(-1)), "-times", 20);
		
			for(SearchHistory h : histories){
				if(!U.isNotMessCode(h.keyword) || h.keyword.length()<3){//乱码 或者太短
					continue;
				}
				keywords.add(h.keyword);
			}

			cache = new CacheObject(); // new one
			cache.timestamp = System.currentTimeMillis();
			cache.value = keywords;
			set(key, cache);
		}

		return (List<String>) cache.value;
	}
	
	//最热200关键字
		public static List<String> top200Keywords( ) {

			String key = "top200Keywords" ;
			CacheObject cache = cacheMap.get(key);
			if (cache == null
					|| System.currentTimeMillis() - cache.timestamp >  60*60 * 1000) {// 缓存时间检查

				List<String> keywords = new ArrayList<String>();
				SearchHistoryService hs  = new SearchHistoryService();
			
				List<SearchHistory> histories =  hs.list(null, "-times", 200);
			
				for(SearchHistory h : histories){
					if(!U.isNotMessCode(h.keyword) || h.keyword.length()<3){//乱码 或者太短
						continue;
					}
					keywords.add(h.keyword);
				}

				cache = new CacheObject(); // new one
				cache.timestamp = System.currentTimeMillis();
				cache.value = keywords;
				set(key, cache);
			}

			return (List<String>) cache.value;
		}
	
	//最新关键字
		public static List<String> newKeywords( ) {

			String key = "newKeywords" ;
			CacheObject cache = cacheMap.get(key);
			if (cache == null
					|| System.currentTimeMillis() - cache.timestamp >   5*60 * 1000) {// 缓存时间检查

				List<String> keywords = new ArrayList<String>();
				SearchHistoryService hs  = new SearchHistoryService();
			
				List<SearchHistory> histories =  hs.list(null, "-createDate", 40);
			
				for(SearchHistory h : histories){
					if(!U.isNotMessCode(h.keyword) || h.keyword.length()<3){//乱码 或者太短
						continue;
					}
					keywords.add(h.keyword);
				}

				cache = new CacheObject(); // new one
				cache.timestamp = System.currentTimeMillis();
				cache.value = keywords;
				set(key, cache);
			}

			return (List<String>) cache.value;
		}
	
	public static List<Page> hotSharePages(PageType typeEnum,String tagName) {

		String key = "hotSharePages_"+typeEnum+"_"+tagName ;
		CacheObject cache = cacheMap.get(key);
		if (cache == null
				|| System.currentTimeMillis() - cache.timestamp > 5 * 60 * 1000) {// 缓存时间检查


			 PageService ps = new PageService();
			 List<Page> pages = ps.list(new ConditionMap().add("type", typeEnum)
					 				.add("tagName", tagName)
					 				.add("createDate >", U.dateTime(-1)) //n天以内的，链接太久了容易失效
					 				.add("isShare", true), "-favScore", 0, 10);			
			cache = new CacheObject(); // new one
			cache.timestamp = System.currentTimeMillis();
			cache.value = pages;
			set(key, cache);
		}

		return (List<Page>)  cache.value;
	}
	
	
	public static void set(String key, CacheObject value) {
		cacheMap.put(key, value);
	}

	public static CacheObject get(String key) {
		return cacheMap.get(key);
	}

	public static void main(String[] args) {

	}

}

class CacheObject {
	public long timestamp;// 缓存时间
	public Object value; // 缓存内容
}

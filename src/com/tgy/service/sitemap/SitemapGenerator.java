package com.tgy.service.sitemap;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.tgy.App;
import com.tgy.entity.Page;
import com.tgy.entity.PanSearchCache;
import com.tgy.entity.SearchHistory;
import com.tgy.entity.Tag;
import com.tgy.service.PageService;
import com.tgy.service.PanSearchCacheService;
import com.tgy.service.SearchHistoryService;
import com.tgy.statistic.service.TagService;
import com.tgy.util.ConditionMap;
import com.tgy.util.PageType;
import com.tgy.util.U;

public class SitemapGenerator {
	
	public void gen(){
		
		System.out.println("SitemapGenerator begin");
		
		//初始化 sitemap 文件
		String path = App.basePath;
		if(StringUtils.isBlank(path)){
			path = "c:/data/";
		}
		path+="Sitemap.txt";
		File sitemapFile = new File(path);
		
		try {
			if (sitemapFile.exists()  ) {//  存在 删了造新的
				sitemapFile.deleteOnExit();
			}
			sitemapFile.createNewFile();
		} catch (Exception e) {
			System.out.println("创建Sitemap.txt 失败");
			return ;
		}
		
		
		//http://www.webhezi.com/pan
		try {
			FileUtils.write(sitemapFile, "http://www.webhezi.com/pan" + "\r\n","UTF-8", true);
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
		}
		
		//遍历所有page
		PageService ps = new PageService();
		
		TagService ts = new TagService();
		//遍历 pageType
		for(PageType pt : PageType.values()){
			if( pt==pt.commodity || pt == pt.word){
				continue;//
			}
			
			long count = ps.count(ps.createQuery(new ConditionMap().add("isShare", true).add("type", pt), "-favScore", 0,0));
			for(int i=0;i<=count;i+=10){
				String url = "http://www.webhezi.com/share/"+pt.name()+"?pageStart="+i;
				try {
					FileUtils.write(sitemapFile, url + "\r\n","UTF-8", true);
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
			
			List<Tag> tags =  ts.list(new ConditionMap().add("type", pt), null, 0, 0);
			for(Tag t : tags){
				long count1 = ps.count(ps.createQuery(new ConditionMap().add("isShare", true).add("type", pt).add("tagName", t.name), "-favScore", 0,0));
				for(int i=0;i<=count1;i+=10){
					String url = "http://www.webhezi.com/share/"+pt.name()+"?pageStart="+i+"&tagName="+t.name;
					try {
						FileUtils.write(sitemapFile, url + "\r\n","UTF-8", true);
					} catch (IOException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			
			List<SearchHistory> histories = new SearchHistoryService().list(null,null, 5000);
//			PanSearchCacheService cacheSvs = new PanSearchCacheService();
//			ConditionMap condition = new ConditionMap();
			for(SearchHistory h : histories){
				if(U.isNotMessCode(h.keyword)){
//					condition.clear();
//					List<PanSearchCache> caches = cacheSvs.list(PanSearchCache.class, condition.add("key", h.keyword+"0"), null, 0);
//					if(CollectionUtils.isNotEmpty(caches)){//有缓存
						
						try {
							String url = "http://www.webhezi.com/pan/"+ URLEncoder.encode(h.keyword,"utf-8" );
							FileUtils.write(sitemapFile, url + "\r\n","UTF-8", true);
						} catch (IOException e) {
							System.out.println(e.getMessage());
						}
//					}

				}
			}
		}
		System.out.println("SitemapGenerator end");
	}
	
	public static void main(String[] args) {
		
		new SitemapGenerator().gen();
		System.out.println("end");
	}

}

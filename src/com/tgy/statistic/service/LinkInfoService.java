package com.tgy.statistic.service;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.dao.FolderDao;
import com.tgy.dao.LinkDao;
import com.tgy.dao.PageDao;
import com.tgy.dao.TagDao;
import com.tgy.dao.UserDao;
import com.tgy.entity.Page;
import com.tgy.service.WebInfo;
import com.tgy.statistic.entity.Link;
import com.tgy.util.WebInfoUtil;

public class LinkInfoService {
	
	WebInfoUtil util = new WebInfoUtil();

	public void copy(Link link) {
		
	}
	
	
	
	
	/**
	 * 扫描page表所有的网页，生成对应的信息。
	 */
	public void scan() {

		PageDao pDao = new PageDao();
		FolderDao fDao = new FolderDao();
		LinkDao lDao = new LinkDao();
		TagDao tDao = new TagDao();
		UserDao uDao = new UserDao();
		
		
		// 找到从来没处理过的
		Query<Link> query = App.getInstance().getDatastore()
				.createQuery(Link.class);
		Iterator<Link> it = lDao.find(query).iterator();
		
		while (it.hasNext()) {
			Link link = it.next();
			//没有 title 或 description 就抓取
			if( (StringUtils.isBlank(link.title) || StringUtils.isBlank(link.description)) && link.digTimes<3){
				System.out.println("LinkInfoService 正在抓取 ： " + link.url);
				boolean iconFlag = false;
				if(StringUtils.isBlank(link.iconPath)){
					iconFlag = true;
				}
				
				WebInfo info = util.info(link.url,iconFlag);
				if(info == null){
					continue;
				}
				
				if(StringUtils.isBlank(link.title) && StringUtils.isNoneBlank(info.title)){
					link.title = info.title;
				}
				if(StringUtils.isBlank(link.description) && StringUtils.isNoneBlank(info.description)){
					link.description = info.description;
				}
				link.digTimes++;
				lDao.save(link);//保存link
				
				//设置所有的page
				List<Page> pages = pDao.getByUrl(link.url);
				for(Page p : pages){
					if(StringUtils.isBlank(p.name) && StringUtils.isNoneBlank(info.title)){
						p.name = info.title;
					}
					if(StringUtils.isBlank(p.description) && StringUtils.isNoneBlank(info.description)){
						p.description = info.description;
					}
					if(StringUtils.isBlank(p.iconPath) && StringUtils.isNoneBlank(info.iconPath)){
						p.iconPath = info.iconPath;
					}
					pDao.save(p); //设置并保存page
				}
			}
			 
		}

	}
}

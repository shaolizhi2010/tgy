package com.tgy.asyn;

import org.apache.commons.lang3.StringUtils;

import com.tgy.App;
import com.tgy.dao.LinkDao;
import com.tgy.dao.PageDao;
import com.tgy.entity.Page;
import com.tgy.service.WebInfo;
import com.tgy.statistic.entity.Link;
import com.tgy.util.U;
import com.tgy.util.WebInfoUtil;

public class GetPageInfoThread  implements Runnable{

	String url ;
	String pageID ;
	
	public GetPageInfoThread(String url,String pageID  ){
		this.url = url;
		this.pageID = pageID;
	}
	
	@Override
	public void run() {
//		
//		long start = System.currentTimeMillis();
//		
//		LinkDao linkDao = new LinkDao();
//		
//		Link link = linkDao.getByUrl(url);
//		if(link==null  ){ //not link, then create new one
//			link = new Link();
//			link.url = url;
//			link.createDate = U.dateTime();
//		}
//		if(StringUtils.isBlank( link.title ) || StringUtils.isBlank( link.description ) || StringUtils.isBlank( link.iconPath ) ){
//			boolean getIconFlag = false;
//			if(StringUtils.isBlank( link.iconPath ) || !link.iconPath.startsWith(App.imgPath)){
//				getIconFlag  = true;
//			}
//			WebInfo info = new WebInfoUtil().info(url,getIconFlag);
//			if(info==null){
//				//link.title = url;
//			}
//			else{
//				if(StringUtils.isBlank(link.title)){
//					link.title =  StringUtils.trim(info.title);
//				}
//				if(StringUtils.isBlank(link.description)){
//					link.description =  StringUtils.trim(info.description);
//				}
//				//TODO icon操作放在后边，加快前台返回速度
//				if(StringUtils.isBlank(link.iconPath) || !link.iconPath.startsWith(App.imgPath)){
//					link.iconPath =  StringUtils.trim(info.iconPath);
//				}
//				linkDao.save(link);
//			}
//		}
//		
//		PageDao pDao = new PageDao();
//		Page page = pDao.byID(pageID);
//		if(StringUtils.isBlank(page.name)){
//			page.name = link.title;
//		}
//		if(StringUtils.isBlank(page.description)){
//			page.description = link.description;
//		}
//		if(StringUtils.isBlank(page.iconPath)){
//			page.iconPath = link.iconPath;
//		}
//		pDao.save(page);
//		
//		//get icon
////		if( StringUtils.isBlank( link.iconPath )   ){ //没有 icon
////			try{
////				
////			
////			}catch (Exception e) {
////				link.iconAvailable  = "false";
////			}
////			linkDao.save(link);
////		}
////		
////		
////		page.iconAvailable = link.iconAvailable;
////		pDao.save(page);
//		
//		System.out.println(System.currentTimeMillis() - start);
		
	}

}

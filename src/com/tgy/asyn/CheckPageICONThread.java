package com.tgy.asyn;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.WebUtils;

import com.tgy.dao.LinkDao;
import com.tgy.dao.PageDao;
import com.tgy.entity.Page;
import com.tgy.statistic.entity.Link;
import com.tgy.util.URLUtils;
import com.tgy.util.WebInfoUtil;

public class CheckPageICONThread  implements Runnable{

	String url ;
	String pageID ;
	
	public CheckPageICONThread(String url,String pageID  ){
		this.url = url;
		this.pageID = pageID;
	}
	
	@Override
	public void run() {
		
//		long start = System.currentTimeMillis();
//		
//		LinkDao linkDao = new LinkDao();
//		
//		Link link = linkDao.getByUrl(url);
//		
//		WebInfoUtil util = new WebInfoUtil();
//		
//		if(link!=null &&  StringUtils.isBlank( link.iconPath )  ){
//			try{
//				 if( util.checkICON(link.iconPath) ){
//					 
//				 }
//			
//			}catch (Exception e) {
//				//link.iconAvailable  = "false";
//			}
//		}
//		
//		if(link!=null && StringUtils.isNotBlank( link.iconAvailable )){
//			PageDao pDao = new PageDao();
//			Page page = pDao.getByID(pageID);
//			page.iconAvailable = link.iconAvailable;
//			pDao.save(page);
//		}
		
		//System.out.println(System.currentTimeMillis() - start);
		
	}

}

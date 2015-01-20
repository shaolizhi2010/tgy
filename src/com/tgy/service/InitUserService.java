package com.tgy.service;

import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.exception.BaseException;

import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.exception.BaseException;
 
public class InitUserService {
	 
	public void initUser(String userID){
		//init folder and init pages
		FolderService fs = new FolderService();
		PageService ps = new PageService();
		
		try {
			//常用
			Folder f1 = new Folder();
			f1.name = "常用";
			f1.userID = userID;
			fs.save(f1);
			Page p1 = new Page();
			p1.url = "www.baidu.com/";
			p1.userID = userID;
			p1.folderID = f1.id.toString();
			p1.pid = f1.id.toString();
			p1.iconPath = "/images/icon/cae06cd4b5b7be327ccb00a6dd6f588c";
			ps.save(p1);
			

			Page p2 = new Page();
			p2.url = "qzone.qq.com/";
			p2.userID = userID;
			p2.folderID = f1.id.toString();
			p2.pid = f1.id.toString();
			ps.save(p2);
			
			Page p3 = new Page();
			p3.url = "www.youku.com/";
			p3.userID = userID;
			p3.folderID = f1.id.toString();
			p3.pid = f1.id.toString();
			ps.save(p3);
			
			Page p4 = new Page();
			p4.url = "weibo.com/";
			p4.name = "新浪微博";
			p4.userID = userID;
			p4.folderID = f1.id.toString();
			p4.pid = f1.id.toString();
			ps.save(p4);
			
			f1.add(p1);
			f1.add(p2);
			f1.add(p3);
			f1.add(p4);
			fs.save(f1);
			
			//网购
			Folder f2 = new Folder();
			f2.name = "网购";
			f2.userID = userID;
			fs.save(f2);
			
			Page p5 = new Page();
			p5.url = "www.tmall.com/";
			p5.iconPath = "http://g.tbcdn.cn/mui/global/1.2.35/file/favicon.ico";
			p5.userID = userID;
			p5.folderID = f2.id.toString();
			p5.pid = f2.id.toString();
			ps.save(p5);
			

			Page p6 = new Page();
			p6.url = "www.jd.com/";
			p6.userID = userID;
			p6.folderID = f2.id.toString();
			p6.pid = f2.id.toString();
			ps.save(p6);
			
			Page p7 = new Page();
			p7.url = "www.taobao.com/";
			p7.userID = userID;
			p7.folderID = f2.id.toString();
			p7.pid = f2.id.toString();
			ps.save(p7);
			
			Page p8 = new Page();
			p8.url = "www.z.cn/";
			p8.userID = userID;
			p8.folderID = f2.id.toString();
			p8.pid = f2.id.toString();
			ps.save(p8);
			
			f2.add(p5);
			f2.add(p6);
			f2.add(p7);
			f2.add(p8);
			fs.save(f2);
			
			//新闻
			Folder f3 = new Folder();
			f3.name = "新闻";
			f3.userID = userID;
			fs.save(f3);
			
			Page p9 = new Page();
			p9.url = "www.sina.com.cn/";
			p9.userID = userID;
			p9.folderID = f3.id.toString();
			p9.pid = f3.id.toString();
			ps.save(p9);
			

			Page p10 = new Page();
			p10.url = "www.163.com/";
			p10.userID = userID;
			p10.folderID = f3.id.toString();
			p10.pid = f3.id.toString();
			ps.save(p10);
			
			Page p11 = new Page();
			p11.url = "news.baidu.com/";
			p11.userID = userID;
			p11.folderID = f3.id.toString();
			p11.pid = f3.id.toString();
			ps.save(p11);
			
//			Page p12 = new Page();
//			p12.url = "www.z.cn/";
//			p12.userID = userID;
//			p12.folderID = f3.id.toString();
//			ps.save(p12);
			
			f3.add(p9);
			f3.add(p10);
			f3.add(p11);
//			f3.add(p12);
			fs.save(f3);
			
			
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
}

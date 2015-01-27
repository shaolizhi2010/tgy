package com.tgy.service.ziyuan;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.entity.User;
import com.tgy.service.FolderService;
import com.tgy.service.PageService;
import com.tgy.service.UserService;
import com.tgy.service.WebInfo;
import com.tgy.util.ConditionMap;
import com.tgy.util.Connecter;
import com.tgy.util.PageType;
import com.tgy.util.U;
import com.tgy.util.WebInfoUtil;
import com.tgy.util.X;

public class BaiduPanDigService {
	
	public void digAndSave(){
		
		UserService us = new UserService();
		User u = us.getByName("百度云盘收藏");
		if(u==null||u.id==null)return;
		
		FolderService fs = new FolderService();
		Folder f  = fs.ByUserIDAndName("百度云", u.id.toString());
		if(f==null||f.id==null)return;
		
		List<Page> pages = dig();
		PageService ps = new PageService();
		int savedCount = 0;
		for(Page a : pages){
			try {
				a.folderID = f.id.toString();
				a.pid = f.id.toString();
				a.userID = u.id.toString();
				
				//不存在 防重复
				if(ps.list(new ConditionMap().add("url", a.url).add("folderID", a.folderID), null,0, 0).size()<=0){
					ps.save(a);
					//f.add(a);
					//fs.save(f);
					savedCount++;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				//empty
			}
		}
		System.out.println("BaiduPanDigService : " +savedCount + " saved.");
	}

	public List<Page> dig(){
		
		List<Page> returnList = new ArrayList<>();
		
		BaiduPanDigService s = new BaiduPanDigService();
		List<Page> tieziList = s.allTiezi();
		int digCount = 0 ;
		for(Page t : tieziList){
			String url = t.url;
			if(!StringUtils.startsWith(url, "http://tieba.baidu.com")){
				url = "http://tieba.baidu.com"+url;
			}
			List<Page> ziyuanList = s.allZiyuanLink(url);
			for(Page z : ziyuanList){
				System.out.println(z.url);
				WebInfo info = new WebInfoUtil().info(z.url,false);
				if(StringUtils.isNotBlank(info.title) //
						&& !StringUtils.contains(info.title, "请输入提取密码") 
						&& !StringUtils.contains(info.title, "链接不存在")){ //加密资源忽略
					Page p = new Page();
					String title = info.title;
					title = title.replaceAll("网盘-分享无限制", ""); //去掉重复字符串
					p.title = title;
					p.name = title;
					p.summry = title;
					p.type = PageType.baidupan;
					p.isShare = true;
					p.orignDate = U.dateTime();
					p.url = z.url;
					returnList.add(p);
				}
				System.out.println(info.title);
				digCount++;
			 
			}
			if(digCount> 30){//一次最多取30个
				break;
			}
		}
		return returnList;
	}
	
	//抓取帖子
	public List<Page> allTiezi(){
		List<Page> tieziList = new ArrayList<>();
		String pageSource  = Connecter.getPageSource("http://tieba.baidu.com/f?kw=%E7%99%BE%E5%BA%A6%E7%BD%91%E7%9B%98&ie=utf-8", "utf-8");
		
		Document doc;
		int count=0;
		try {
			doc = new org.jdom2.input.SAXBuilder().build(new StringReader(pageSource));
			List<Element> elementList = X.getSubElementList(doc, "//a");
			
			for(Element e : elementList){
				Attribute attr = e.getAttribute("href");
				if(attr!=null){
					String href = attr.getValue();
					if(StringUtils.startsWith(href, "/p/")){
						//System.out.println(e.getValue());
					//	System.out.println(href);
						Page p = new Page();
						p.url = href;
						tieziList.add(p);
//						if(count++ > 10){//一次最多抓10个，减小系统开销，防止被屏蔽，
//							break;
//						}
					}
				}
			}
		} catch ( Exception e1) {
			e1.printStackTrace();
		} 
		return tieziList;
	}
	
	public List<Page> allZiyuanLink(String url){
		List<Page> returnList = new ArrayList<>();
		String pageSource  = Connecter.getPageSource(url, "utf-8");
		
		Document doc;
		try {
			doc = new org.jdom2.input.SAXBuilder().build(new StringReader(pageSource));
			List<Element> elementList = X.getSubElementList(doc, "//a");
			
			int count=0;
			for(Element e : elementList){
				//判断是否是百度盘链接，
				//根据链接文字而不是href，因为用户会直接copy链接，href是百度的中间跳转链接
				if(e.getText().trim().startsWith("http://pan.baidu.com/s")){
					Page p = new Page();
					p.url = e.getText();//
					returnList.add(p);
//					if(count++ > 10){//一帖最多抓10个，减小系统开销，防止被屏蔽，
//						break;
//					}
				}
			}
		} catch ( Exception e1) {
			e1.printStackTrace();
		} 
		return returnList;
	}
	
	
//	public WebInfo getHtmlInfo(String url){
//		WebInfo info = new WebInfoUtil().info(url,false);
//	}
	
	public static void main(String[] args) {
		new BaiduPanDigService().digAndSave();
//		U.printList(list);
		System.out.println("end");
	}

}

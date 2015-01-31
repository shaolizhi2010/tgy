package com.tgy.service.ziyuan;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.entity.PageTagMap;
import com.tgy.entity.Tag;
import com.tgy.entity.TiebaDigHistory;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.service.FolderService;
import com.tgy.service.PageService;
import com.tgy.service.PageTagMapService;
import com.tgy.service.TiebaDigHistoryService;
import com.tgy.service.UserService;
import com.tgy.service.WebInfo;
import com.tgy.statistic.service.TagService;
import com.tgy.util.ConditionMap;
import com.tgy.util.Connecter;
import com.tgy.util.PageType;
import com.tgy.util.U;
import com.tgy.util.WebInfoUtil;
import com.tgy.util.X;

public class BaiduPanDigService {
	
	/**
	 * 抓取tieba 内容
	 * 有时候贴吧名字不适合作为tag名字，就在空格后写tagname
	 * 那么如果参数是 "abc吧   abc" ，中间空格前边是贴吧名，后边是tag名，
	 * @param tiebaNames
	 */
	public void digAndSave(String... tiebaNames){
		for(String tiebaStr : tiebaNames){
			String tiebaName = "";
			String tagName = "";
			if(tiebaStr.trim().contains("-")){
				String[] arr = tiebaStr.split("-");
				tiebaName = arr[0].trim();
				tagName = arr[1].trim();
				if(StringUtils.isBlank(tagName)){
					tagName = tiebaName;
				}
			}
			else{
				tiebaName = tiebaStr;
				tagName = tiebaStr;
			}
			digAndSave(tiebaName,tagName);
			try {
				Thread.sleep(2*60*1000);//休息n分钟
			} catch (InterruptedException e) {
				//empty
			}
		}
	}
	
	public void digAndSave(String tiebaName, String tagName){ 
		
		UserService us = new UserService();
		
		User u = us.getByName(tagName);
		if(u==null||u.id==null){
			u=new User();
			u.name = tagName;
			u.password = "r";
			u.isRobot = true;
			u.headImgUrl = "/images/ava/ava"+ (new Random().nextInt(120)+1) +".png";
			u.publicMessage = tagName;
			us.save(u);
		}
		
		FolderService fs = new FolderService();
		Folder f  = fs.ByUserIDAndName(tagName, u.id.toString());
		if(f==null||f.id==null){
			f = new Folder();
			f.name = tagName;
			f.isRobot = true;
			f.userID = u.id.toString();
			try {
				fs.save(f);
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				System.out.println("BaiduPanDigService: 文件夹 " + tagName +" 无法创建");
				return;
			}
		}
		
		TagService ts = new TagService();
		Tag t = ts.getByName( tagName , PageType.resource);
		if(t==null || t.id==null){ //create tag if not exist
			t = new Tag();
			t.name = tagName;
			t.type = PageType.resource;
			ts.save(t);
		}
		
		PageTagMapService ptms = new PageTagMapService();
		
		List<Page> pages = dig(tiebaName,tagName);
		PageService ps = new PageService();
		int savedCount = 0;
		for(Page a : pages){
			try {
				a.folderID = f.id.toString();
				a.pid = f.id.toString();
				a.userID = u.id.toString();
				a.isShare = true;
				
				//不存在 防重复
				if(ps.list(new ConditionMap().add("url", a.url).add("folderID", a.folderID), null,0, 0).size()<=0){
					ps.save(a);
					
					//save page tag map
					PageTagMap ptm = new PageTagMap();
					ptm.pageID = a.id;
					ptm.tagID = t.id;
					ptms.save(ptm);
					
					//f.add(a);
					//fs.save(f);
					savedCount++;
				}
				else{
					System.out.println("BaiduPanDigService : 资源已存在 " + a.url  );
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				//empty
			}
		}
		System.out.println("BaiduPanDigService : " +savedCount + " saved.");
	}

	public List<Page> dig(String tiebaName,String tagName){
		
		List<Page> returnList = new ArrayList<>();
		
		BaiduPanDigService s = new BaiduPanDigService();
		List<Page> tieziList = s.getTieziLinks(tiebaName);
		int digCount = 0 ;
		for(Page t : tieziList){
			String url = t.url;
			String tieziId = url.replace("/p/", "");

			List<Page> ziyuanList = s.getZiyuanLinks(tieziId);
			for(Page z : ziyuanList){
				System.out.println(z.url);
				
				try {
					WebInfo info = new WebInfoUtil().info(z.url,false);
					if(StringUtils.isNotBlank(info.title) // 
							&& !StringUtils.contains(info.title, "请输入提取密码")
							&& !StringUtils.contains(info.title, "百度云升级")
							&& !StringUtils.contains(info.title, "页面无法找到")
							&& !StringUtils.contains(info.title, "error 404")
							&& !StringUtils.contains(info.title, "页面不存在")
							&& !StringUtils.contains(info.title, "链接不存在")){ //加密资源忽略
						Page p = new Page();
						String title = info.title;
						title = title.replaceAll("网盘-分享无限制", ""); //去掉重复字符串
						p.title = title;
						p.name = title;
						p.summry = title;
						p.type = PageType.resource;
						p.orignDate = U.dateTime();
						p.url = z.url;
						p.isShare = true;
						p.tagName = tagName;
						
						returnList.add(p);
					}
					System.out.println(info.title);
					digCount++;
				} catch (Exception e) {
					System.out.println("dig "+z.url + " error : "+e.getMessage());
					continue;
				}
				
			}
			if(digCount> 30){//一次最多取30个
				break;
			}
		}
		return returnList;
	}
	
	//抓取帖子
	public List<Page> getTieziLinks(String tiebaName){
		List<Page> tieziList = new ArrayList<>();
		String pageSource  = Connecter.getPageSource("http://tieba.baidu.com/f?kw="+tiebaName+"&ie=utf-8", "utf-8");
		
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
						if(count++ > 100){//一次最多抓100个，减小系统开销，防止被屏蔽，
							break;
						}
					}
				}
			}
		} catch ( Exception e1) {
			e1.printStackTrace();
		} 
		return tieziList;
	}
	
	public List<Page> getZiyuanLinks(String tieziID ){
		
		//根据这个帖子出现的次数，确定要抓第几页，
		//比如一个帖子，第一次被爬虫发现，那么抓第一页 
		//如果已经是第6次被爬虫发现，那么就不要总抓第一页了，抓第二或第三页。
		//maxrepeatDigTimes 就是每页最多重复抓几次然后调到下一页。这个数字越大，爬虫翻页越慢。
		int maxrepeatDigTimes = 10;
		
		TiebaDigHistoryService hs = new TiebaDigHistoryService();
		TiebaDigHistory h = hs.get(new ConditionMap().add("tieziID", tieziID));
		
		int pageNum = 1;
		if(h== null){
			h = new TiebaDigHistory();
			h.tieziID = tieziID;
		}
		else{
			pageNum = h.foundTimes/maxrepeatDigTimes+1;
		}
		h.pageNum = pageNum;
		h.foundTimes++;
		h.lastDigTime = U.dateTime();
		hs.save(h);
		
		String url  = "http://tieba.baidu.com/p/"+tieziID+"?pn="+pageNum;
		
		String pageSource  = Connecter.getPageSource(url, "utf-8");
 
		
		List<Page> returnList = new ArrayList<>();
		Document doc;
		try {
			doc = new org.jdom2.input.SAXBuilder().build(new StringReader(pageSource));
			List<Element> elementList = X.getSubElementList(doc, "//a");
			
			int count=0;
			for(Element e : elementList){
				//判断是否是百度盘链接，
				//根据链接文字而不是href，因为用户会直接copy链接，href是百度的中间跳转链接
				if(e.getText().trim().startsWith("http://pan.baidu.com/")
						&& !e.getText().trim().startsWith("http://pan.baidu.com/mbox") //无标题，滤掉!
						&& !e.getText().trim().startsWith("http://pan.baidu.com/play") //无标题，滤掉!
						&& !e.getText().trim().startsWith("http://pan.baidu.com/inbox") //无标题，滤掉!
						&& !e.getText().trim().startsWith("http://pan.baidu.com/disk/home#") //无意义
						&& !e.getText().trim().equals("http://pan.baidu.com/disk/home") //无意义
						){
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
		new BaiduPanDigService().digAndSave("资源-百度网盘",  "美剧资源站-美剧", "百度网盘");
//		U.printList(list);
		System.out.println("end");
	}

}

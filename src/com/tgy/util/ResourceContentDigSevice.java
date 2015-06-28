package com.tgy.util;

import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.CookieSpecFactory;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.params.HttpParams;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.SimpleXmlSerializer;
import org.htmlcleaner.TagNode;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import com.tgy.App;
import com.tgy.dao.SearchHistoryDao;
import com.tgy.entity.Page;
import com.tgy.entity.SearchHistory;
import com.tgy.statistic.entity.Link;

/**
 * 抓取资源的介绍 图片等信息，丰富网站内容
 * 按资源名抓取
 */
public class ResourceContentDigSevice {

	public String getResourceName(String rawName ){
		
		rawName = StringUtils.replace(rawName, "99搜盘网", "");
		
		rawName = StringUtils.replace(rawName, "_免费高速下载|百度云", "");
		rawName = StringUtils.replace(rawName, "免费高速下载", "");
		rawName = StringUtils.replace(rawName, "中英字幕", "");
		rawName = StringUtils.replace(rawName, "日语中字", "");
		rawName = StringUtils.replace(rawName, "高清下载", "");
		rawName = StringUtils.replace(rawName, "中英双字", "");
		rawName = StringUtils.replace(rawName, "电影吧", "");
		rawName = StringUtils.replace(rawName, "百度云", "");
		rawName = StringUtils.replace(rawName, "剧场版", "");
		rawName = StringUtils.replace(rawName, "网盘", "");
		rawName = StringUtils.replace(rawName, "百度盘", "");
		rawName = StringUtils.replace(rawName, "无修版", "");
		rawName = StringUtils.replace(rawName, "枪版", "");
		rawName = StringUtils.replace(rawName, "全集", "");
		rawName = StringUtils.replace(rawName, "字幕", "");
		rawName = StringUtils.replace(rawName, "中英", "");
		rawName = StringUtils.replace(rawName, "清晰", "");
		rawName = StringUtils.replace(rawName, "种子", "");
		rawName = StringUtils.replace(rawName, "bt", "");
		rawName = StringUtils.replace(rawName, "下载", "");
		rawName = StringUtils.replace(rawName, "和谐", "");
		rawName = StringUtils.replace(rawName, "蓝光", "");
		
		rawName = StringUtils.replace(rawName, "torrent", "");
		
		
		rawName = StringUtils.replace(rawName, "_", "");
		rawName = StringUtils.replace(rawName, "|", "");
		rawName = StringUtils.replace(rawName, "【", "");
		rawName = StringUtils.replace(rawName, "】", "");
		rawName = StringUtils.replace(rawName, "[", "");
		rawName = StringUtils.replace(rawName, "]", "");
		rawName = StringUtils.replace(rawName, "(", "");
		rawName = StringUtils.replace(rawName, ")", "");
		rawName = StringUtils.replace(rawName, "《", "");
		rawName = StringUtils.replace(rawName, "》", "");
		rawName = StringUtils.replace(rawName, ".", "");
		
		
		rawName = StringUtils.replace(rawName, "wmv", "");
		rawName = StringUtils.replace(rawName, "mkv", "");
		rawName = StringUtils.replace(rawName, "rmvb", "");
		rawName = StringUtils.replace(rawName, "mp4", "");
		rawName = StringUtils.replace(rawName, "avi", "");
		rawName = StringUtils.replace(rawName, "txt", "");
		rawName = StringUtils.replace(rawName, "rar", "");
		rawName = StringUtils.replace(rawName, "zip", "");
		
		
		rawName = StringUtils.replace(rawName, "720p", "");
		rawName = StringUtils.replace(rawName, "1080p", "");
		
		return rawName;
		
	}
	
	//判断字符含有汉字，日文等字符
	public boolean hasSpecialChar(String s){
		if(s.getBytes().length == s.length()){
			return false;
		}
		return true;
	}
	
	public String getContent(String keyword ) {
		String content = "";
		
		try {
			keyword = getResourceName(keyword);
		
			if(!hasSpecialChar(keyword) && keyword.length()<5){//不含汉字（只有英文或数字类字符）,长度又很小，不抓内容，抓了一般也不准
				return content;
			}
			
			
			System.out.println("keyword : "+ keyword);
			keyword = URLEncoder.encode(keyword,"utf-8");
			String url = "http://cn.bing.com/search?q="+keyword;
			
			//String s  = SimpleConnecter.connect(url, "utf-8");
			//String s  = IOUtils.toString(new URL(url));
// 
//			
			HttpClient httpclient = App.getInstance().getHttpClient();
			HttpGet httpget = new HttpGet(url);
			httpget.setHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)"); 
			httpget.setHeader("Accept-Language", "zh-CN");
			httpget.setHeader("Accept-Charset", "UTF-8,GBK;q=0.7");
			httpget.setHeader("Referer", "www.bing.com");  
			HttpResponse response = httpclient.execute(httpget);
//
			HttpEntity entity = response.getEntity();
			InputStream entityContent = entity.getContent();
			
			HtmlCleaner hc = new HtmlCleaner();
			TagNode node = hc.clean(entityContent);
			
			String pageSource = getPageSourceFromNode(node);
			
			Document doc = new org.jdom2.input.SAXBuilder().build(new StringReader(pageSource));
			List<Element> elementList = X.getSubElementList(doc, "//a");
			
			for(Element e : elementList){
				Attribute attr = e.getAttribute("href");
				if(attr!=null && attr.getValue() !=null 
						&& (attr.getValue().startsWith("http://baike.baidu.com")||attr.getValue().startsWith("http://www.baike.com")||attr.getValue().startsWith("http://baike.sogou.com"))){
					  content = e.getParentElement().getParentElement().getValue();
					content = StringUtils.replace(content, "百度百科", "");
					content = StringUtils.replace(content, "baike.baidu.com", "webhezi.com");
					content = StringUtils.replace(content, "搜狗百科", "");
					content = StringUtils.replace(content, "baike.sogou.com", "webhezi.com");
					content = StringUtils.replace(content, "互动百科", "");
					content = StringUtils.replace(content, "www.baike.com/wiki/", "webhezi.com");
					content = StringUtils.replace(content, "维基百科", "");
					
					content = StringUtils.replace(content, "百科", "");
					content = content  +"...";
					
					break;
				}

			}
			System.out.println("end ： content ： "  + content);
			return content;
		} catch (Exception e) {
			System.out.println("ResouceContentDigService Exception : "+e.getMessage());
			return content;
		}
	}

 	public  String getPageSourceFromNode(TagNode node) {
		// long start = System.currentTimeMillis();
		HtmlCleaner hc = new HtmlCleaner();
		CleanerProperties props = hc.getProperties();
		props.setNamespacesAware(false);
		props.setOmitCdataOutsideScriptAndStyle(true);
		props.setOmitComments(true);
		props.setOmitXmlDeclaration(true);
		props.setOmitDoctypeDeclaration(true);
		// PrettyXmlSerializer SimpleXmlSerializer
		SimpleXmlSerializer serializer = new SimpleXmlSerializer(props);
		String pageSource = "";
		try {
			pageSource = serializer.getAsString(node, "UTF-8");
		} catch (Exception e) {
			// no thing
		}
		// pageSource = U.clean(pageSource);
		// L.trace("Connecter getPageSourceFromNode ", " finished, time is " +
		// (System.currentTimeMillis() -start));
		return pageSource;
	}
	public static void main(String[] args) {
		System.out.println("start");
		String c = new ResourceContentDigSevice().getContent("废柴舅舅_免费高速下载|百度云");
		System.out.println(c);
		System.out.println("end");
	}
	

}

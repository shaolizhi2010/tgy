package com.tgy.util;

import java.io.InputStream;
import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
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

public class BingSearchSevice extends BaseSearchService {

	public List<Page> search(String keyword,int start ) {
		try {
			
			//搜索历史 记录到后台
			SearchHistoryDao dao = new SearchHistoryDao();
			
			SearchHistory sh = dao.byKeyword(keyword);
			if(sh==null){
				sh = new SearchHistory();
				sh.keyword = keyword;
				sh.createDate = U.dateTime();
				sh.times=1; 
				//sh.userID = user
			}
			else{
				sh.times++;
			}
			dao.save(sh);
			
			List<Page>  results = new ArrayList<>();
			keyword = URLEncoder.encode(keyword,"utf-8");
			String url = "http://cn.bing.com/search?q="+keyword+"++site%3Apan.baidu.com&first="+start;
			
//			String s  = SimpleConnecter.connect(url, "utf-8");
			
			HttpClient httpclient = App.getInstance().getHttpClient();
			HttpGet httpget = new HttpGet(url);
			httpget.setHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpget.setHeader("Accept-Language", "zh-CN");
			httpget.setHeader("Accept-Charset", "UTF-8,GBK;q=0.7");
			httpget.setHeader("Referer", "cn.bing.com");  
			HttpResponse response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();
			InputStream entityContent = entity.getContent();
			
			HtmlCleaner hc = new HtmlCleaner();
			TagNode node = hc.clean(entityContent, "UTF-8");
			
			String pageSource = getPageSourceFromNode(node);
			
			Document doc = new org.jdom2.input.SAXBuilder().build(new StringReader(pageSource));
			List<Element> elementList = X.getSubElementList(doc, "//a");
			
			for(Element e : elementList){
				Attribute attr = e.getAttribute("href");
				if(attr!=null){
					String href = attr.getValue();
					if(StringUtils.startsWith(href, "http://pan.baidu.com")){
						//System.out.println(e.getValue());
					//	System.out.println(href);
						Page p = new Page();
						p.name = e.getValue();
						p.url = href;
						results.add(p);
					}
				}
			}
			return results;
		} catch (Exception e) {
			System.out.println("WebInfoUtil Exception : "+e.getMessage());
			return new ArrayList<>();
		}
	}

 
	
	public List<Page> search2(String keyword ) {
		try {
			
			//搜索历史 记录到后台
			SearchHistoryDao dao = new SearchHistoryDao();
			
			List<Page>  results = new ArrayList<>();
			keyword = URLEncoder.encode(keyword+"网站");
			
			int index = 0;
			while(index<=10 && results.size()<5){//没凑够5条结果，并且搜索记录少于20条，继续搜素
				String url = "http://cn.bing.com/search?q="+keyword +"&first="+index;
				
				String s  = SimpleConnecter.connect(url, "utf-8");
				
				HtmlCleaner hc = new HtmlCleaner();

				TagNode node = hc.clean(s);
				String pageSource = getPageSourceFromNode(node);
				
				Document doc = new org.jdom2.input.SAXBuilder().build(new StringReader(pageSource));
				List<Element> elementList = X.getSubElementList(doc, "//a");
				
				for(Element e : elementList){
					Attribute attr = e.getAttribute("href");
					if(attr!=null){
						String href = attr.getValue();
						//String hrefTemp = StringUtils.replace(href, "://", "");
						//hrefTemp = StringUtils.replaceOnce(hrefTemp, "/", "");
						//StringUtils.countMatches("", sub)
						
						//System.out.println(href +"---");
						if( StringUtils.startsWith(href, "http")&& StringUtils.countMatches(href, "/")<=3&&StringUtils.endsWith(href, "/")&& !StringUtils.contains(href, "bing.com")){//url中不包含/， 是根域名
							//System.out.println(e.getValue());
							//System.out.println(href);
							Page p = new Page();
							p.name = e.getValue();
							p.url = href;
							results.add(p);
						}
					}
				}
				index+=12;
			}
			
		
			
			
			
			return results;
		} catch (Exception e) {
			System.out.println("WebInfoUtil Exception : "+e.getMessage());
			return new ArrayList<>();
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


 

}

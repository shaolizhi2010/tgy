package com.tgy.util;

import java.io.InputStream;
import java.io.StringReader;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRouteParams;
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

public class GoogleSearchSevice extends BaseSearchService {

	public List<Page> search(String keyword,int start ) {
		try {
			
			//搜索历史 记录到后台
//			SearchHistoryDao dao = new SearchHistoryDao();
//			
//			SearchHistory sh = dao.byKeyword(keyword);
//			if(sh==null){
//				sh = new SearchHistory();
//				sh.keyword = keyword;
//				sh.createDate = U.dateTime();
//				sh.times=1; 
//				//sh.userID = user
//			}
//			else{
//				sh.times++;
//			}
//			dao.save(sh);
			
			//http://209.85.228.19/
			
			List<Page>  results = new ArrayList<>();
			keyword = URLEncoder.encode(keyword,"utf-8");
			String url = "http://209.85.228.19/search?q="+keyword+"+site%3Apan.baidu.com"+"&start="+start;
			
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
			httpget.setHeader("Referer", "www.google.com.hk");  
			
//			HttpHost proxy = new HttpHost("209.85.228.22",80, null);  // /
//			httpclient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);  
			
			HttpResponse response = httpclient.execute(httpget);
//
			HttpEntity entity = response.getEntity();
			InputStream entityContent = entity.getContent();
			
			HtmlCleaner hc = new HtmlCleaner();
			TagNode node = hc.clean(entityContent);
			
			String pageSource = getPageSourceFromNode(node);
			//System.out.println(pageSource);
			
			
			Document doc = new org.jdom2.input.SAXBuilder().build(new StringReader(pageSource));
			List<Element> elementList = X.getSubElementList(doc, "//a");
			
			for(Element e : elementList){
//				String text = e.getText();
				Attribute attr = e.getAttribute("href"); //data-href
				if(attr!=null){
					String href = attr.getValue();
					//System.out.println(href);
					
					href = StringUtils.substringBetween(href, "?q=","&");
					//System.out.println(href);
					
					if(href!=null)href= URLDecoder.decode(href,"utf-8");
					//System.out.println("decoded : "+href);
					//System.out.println("---------------");
					
					if(StringUtils.startsWith(href, "http://pan.baidu.com")){
						
						//System.out.println(e.getValue());
						//System.out.println(href);
						Page p = new Page();
						p.name = e.getValue();
						p.url = href;
						results.add(p);
					}
				}
			}
			return results;
		} catch (Exception e) {
			System.out.println("GoogleSearch Exception : "+e.getMessage());
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
	public static void main(String[] args) {
		List<Page> pages = new GoogleSearchSevice().search("权利的游戏",10);
		for(Page p : pages){
			System.out.println(p.name);
			System.out.println(p.url);
		}
		System.out.println(pages.size());
	}
	

}

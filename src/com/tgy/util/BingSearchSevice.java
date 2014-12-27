package com.tgy.util;

import java.io.StringReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.SimpleXmlSerializer;
import org.htmlcleaner.TagNode;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import com.tgy.dao.SearchHistoryDao;
import com.tgy.entity.Page;
import com.tgy.entity.SearchHistory;
import com.tgy.statistic.entity.Link;

public class BingSearchSevice {

	public List<Page> search(String keyword ) {
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
			keyword = URLEncoder.encode(keyword);
			String url = "http://cn.bing.com/search?q="+keyword+"++site%3Apan.baidu.com ";
			
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

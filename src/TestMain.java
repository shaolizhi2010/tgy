import java.io.StringReader;
import java.net.URLEncoder;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.SimpleXmlSerializer;
import org.htmlcleaner.TagNode;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import com.tgy.util.PageType;
import com.tgy.util.SimpleConnecter;
import com.tgy.util.X;

public class TestMain {

	public static void main(String[] args) {
		try {
			System.out.println("start");
			
			System.out.println(PageType.article);
			System.out.println(PageType.article.value());
			
//			System.out.println( NumberUtils.toInt(""));
			
//			List<String> list = (List<String>)null; 
//			System.out.println(list);
//			for(String s : list){
//				System.out.println(s);
//			}
//			for(int i=0;i<100;i++){
//				System.out.println(new Random().nextInt(2));
//			}
			
			//System.out.println(s);
			
//			String url = "http://www.wanggousousuo.com/";
//			String url = "http://news.taobao.com/";
//			String url = "http://news.ifeng.com/";
//			String url = "http://www.baidu.com/";
//			String url = "http://www.jd.com/";
//			
//			long start = System.currentTimeMillis();
//			
//			List<Link> list =  new LinkDao().getByName("人人");
//			
//			for(Link link : list){
//				System.out.println(link.title);
//				System.out.println(link.url);
//			}
//			
			
//			String faviconUrl = URLUtils.getBaseUrl(url)+"/favicon.ico";
//			
//			
			//System.out.println(new WebInfoUtil().checkICON(url));
			
		//	System.out.println(new URLUtils().getBaseUrl(url));
			
//			Html html = Connecter.getHtml(url);
//			System.out.println( html.title() );
//			System.out.println(System.currentTimeMillis() - start);
//			
//			
//			//System.out.println( html.description() );
//			
//			
		
//			WebInfo info = new WebInfoUtil().info(url,true);
//			System.out.println(info.charset);
//			System.out.println(info.title);
//			System.out.println(info.description);
//			System.out.println(info.iconPath);
//			System.out.println(System.currentTimeMillis() - start);
//			
//			System.out.println(  
//					new WebInfoUtil().getTitle(url));
//			System.out.println(webInfo.title);
//			System.out.println(webInfo.charset);
//			System.out.println(webInfo.description);
//			
//			System.out.println(System.currentTimeMillis() - start);
		//	new TagService().scan(); 
		//	new LinkService().scan();
//			new LinkInfoService().scan();
		//	System.out.println("over");
			
//			List<Page> list = new PageDao().find().asList();
//			for(Page page : list){
//				
//				new Thread(new GetPageInfoThread(page.url, page.id.toString())).start();
//				new Thread().sleep(1000);
//			}
			
//			int i =0;
//			while(i++<100){
//				System.out.println(new Random().nextInt(5));
//			}
//			
			
			//System.out.println(Charset.defaultCharset());
			
//			List<Link> list =  new LinkService().searchByTagName("网购"); //new TagService().searchByName("喜剧");// 搞笑电影
//			;
//			System.out.println(list.size());
//			//U.printList(list);
//			for(Link l : list){
//				System.out.println(l.url);
//				System.out.println(l.users.size());
//			}
//			//System.out.println(new Gson().toJson(list));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static String getPageSourceFromNode(TagNode node) {
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

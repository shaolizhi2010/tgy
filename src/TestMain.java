import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.tgy.entity.Tag;
import com.tgy.service.JDRedirectService;
import com.tgy.service.PageService;
import com.tgy.service.WebInfo;
import com.tgy.statistic.service.TagService;
import com.tgy.util.ADUtils;
import com.tgy.util.ConditionMap;
import com.tgy.util.PageType;
import com.tgy.util.ResourceContentDigSevice;
import com.tgy.util.SimpleConnecter;
import com.tgy.util.U;
import com.tgy.util.URLUtils;
import com.tgy.util.WebInfoUtil;
import com.tgy.util.X;

public class TestMain {
	
	
	// public static String toUnicode(String s){
	// if(StringUtils.isBlank(s)){
	// return s;
	// }
	// StringBuffer us = new StringBuffer();
	// char[] ss = s.toCharArray();
	// for(int i=0;i<ss.length;i++){
	// String sss = "\\u" + Integer.toHexString(ss[i] | 0x10000).substring(1);
	// us.append(sss);
	// }
	// return us.toString();
	// }

	public static void main(String[] args) {
		try {
			System.out.println("start");
			/*
			String[] ss = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p",
					"q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6",
					"7","8","9" };
			
			for(String s2:ss){
				System.out.println( s2+".download");
			}
			
			for(String s1 :ss){
				for(String s2:ss){
					System.out.println(s1+s2+".download");
				}
			}
			*/
			/*
			String[] ss1 = {  "b","c","d", "f","g","h", "j","k","l","m","n", "p",
					"q","r","s","t", "v","w","x","y","z"};
			
			String[] ss2 = {"a" ,"e", "i", "o", "u"};
			
			List<String> list = new ArrayList<>();
			
			for(String s1 :ss1){
				for(String s2:ss2){
					//System.out.println(s1+s2 );
					list.add(s1+s2);
				}
			}
			
			System.out.println(list.size());
			
			for(String s1 : list ){
				for(String s2 : list ){
					System.out.println(s1+s2);
				}
			}
			*/
			
			
			//for(int i=10;i<100;i++){
				for(int k=10;k<100;k++){
					System.out.println(k+""+k+".download");
				}
			//}
			
			//System.out.println(new Random().nextInt(2));
			
		//	System.out.println(URLUtils.isValid2("http://pan.baidu.com/s/1o6wx2C6 密码: 26xx"));
	 
//			WebInfoUtil webInfoUtil = new WebInfoUtil();
//			WebInfo info = webInfoUtil.info( "http://pan.baidu.com/wap/share/home?uk=2047156413&third=0" ,false);

			
			
			
			
		//	System.out.println( (double) 600/900 );
			
//			String s = "【荐】魔法少女小圆.rmvb（2011年4月）_免费高速下载|百度云";
//			
//			s = s.replaceAll("^【*】$", "");
//			System.out.println(s);
			
//			String refer = "http://localhost/tgy/share/resource?tagName=美剧";
//			String keywordParam = "tagName=";
//			
//			String keyword = StringUtils.substringBetween(refer, "?"+keywordParam,"&");
//			if(StringUtils.isBlank(keyword))keyword = StringUtils.substringAfterLast(refer, "?"+keywordParam);
//			if(StringUtils.isBlank(keyword))keyword = StringUtils.substringBetween(refer, "&"+keywordParam,"&");
//			if(StringUtils.isBlank(keyword))keyword = StringUtils.substringAfterLast(refer, "&"+keywordParam);
//		System.out.println(keyword);
			
			//System.out.println(  new ResourceContentDigSevice().hasSpecialChar("123") );
			
//			String returnStr =  SimpleConnecter.connect("http://www.webhezi1.com");
//			
//			System.out.println(returnStr);
//			System.out.println(returnStr.length());
		
			//Runtime.getRuntime().exec("F:\\devall\\gitdb\\tgy\\src\\restartTomcat.bat");
			
			
			
//			String r =  JDRedirectService.getInstance().redirect("斯巴达克斯");
//			System.out.println(r);
			
//			for(PageType pt : PageType.values()){
//				System.out.println(pt.name());
//			}
			
			
			//System.out.println(SimpleConnecter.connect("http://www.smzdm.com/URL/AA/FX/ECDB671ED0DF8860")); 
			
//			char c = 'A';
//			System.out.println( (int)'A' - (int)'a');
//			System.out.println(  (int)'Z');
			

			
			//  String str = "[webhezi.com]你好世界 hello world";
//			   System.out.println(  PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_MARK) );
//			   System.out.println( PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_NUMBER));
//			    System.out.println( PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITHOUT_TONE));
			 //   System.out.println( PinyinHelper.getShortPinyin(str));
			
// try {
//				barName = URLEncoder.encode(barName,"utf-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
            
//            System.out.println(barName);
			
			//System.out.println(PageType.valueOf("abcde"));
			
//			System.out.println("abc".contains("-"));
//			
//			String[] arr = " abc - abcd".split("-");
//			System.out.println(arr[0]);
//			System.out.println(arr[1]);
			
			//TagService ts = new TagService();//new ConditionMap().add("type", PageType.resource)
			//List<Tag> tags =  ts.list(null, null, 0	, 0);
			//System.out.println(ts.count());
			
//			for(Tag t : tags){
//				System.out.println(t.name);
//				System.out.println(t.type.value());
//			}
			
//			PageService ps = new PageService();
//			ps.list(new ConditionMap().add("type", page), orderStr, start, limit)
			
			//System.out.println(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			
//			System.out.println(PageType.article);
//			System.out.println(PageType.article.value());
			
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
			
			System.out.println("end");
			
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

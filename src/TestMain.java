import com.tgy.service.WebInfo;
import com.tgy.util.WebInfoUtil;

public class TestMain {

	public static void main(String[] args) {
		try {
			System.out.println("start");
			String url = "alivv.cn";
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
		
			WebInfo info = new WebInfoUtil().info(url,true);
			System.out.println(info.charset);
			System.out.println(info.title);
			System.out.println(info.description);
			System.out.println(info.iconPath);
//			System.out.println(System.currentTimeMillis() - start);
//			
//			System.out.println(  
//					new WebInfoUtil().getTitle(url));
//			System.out.println(webInfo.title);
//			System.out.println(webInfo.charset);
//			System.out.println(webInfo.description);
//			
//			System.out.println(System.currentTimeMillis() - start);
//			new TagService().scan(); 
//			new LinkService().scan();
//			new LinkInfoService().scan();
			System.out.println("over");
			
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

}

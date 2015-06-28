//import java.net.URLEncoder;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.tgy.entity.Page;
//import com.tgy.entity.PanSearchCache;
//import com.tgy.entity.SearchHistory;
//import com.tgy.util.Connecter;
//import com.tgy.util.GoogleSearchSevice;
//
//public class PanSearchLocalMain {
//
//	public static void main(String[] args) {
//
//	
//		work();
//	}
//	
//	
//	public static void work( ){
//		// String keyword = "我与女友";
//
//		try {
//			// MongoCredential credential =
//			// MongoCredential.createMongoCRCredential("root", "admin",
//			// "cake4you".toCharArray());
//			// ServerAddress address;
//			// address = new ServerAddress("115.29.44.92");
//			// MongoClient client = new
//			// MongoClient(address,Arrays.asList(credential));
//			// Datastore ds= new Morphia().createDatastore(client, "tgy" );
//			
//			
//			String keywordJson = Connecter.connect(" http://www.webhezi.com/needDigKeywords.jsp", "utf-8"); //www.webhezi.com
//			 
//			List<String> keywords =  new Gson().fromJson(keywordJson,new TypeToken<List<String>>(){}.getType());
//			
//			for(String sh : keywords ){
//				
//				Thread.sleep(30000);
//				
//				dig(sh );
//			}
//			
//			//
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public static void dig(String keyword){
//		
//		try {
//			String key = keyword+0;
//			
//			GoogleSearchSevice gs = new GoogleSearchSevice();
//			List<Page> list = gs.search(keyword, 0);
//
//			for (Page p : list) { //www.webhezi.com
//				
//				String name = "";
//				String comment = "";
//				String url = "";
//				String imgSrc = "";
//				
//				if(StringUtils.isNotBlank(p.name)){
//					name = URLEncoder.encode(p.name,"utf-8");
//				}
//				if(StringUtils.isNotBlank(p.comment)){
//					comment = URLEncoder.encode(p.comment,"utf-8");
//				}
//				if(StringUtils.isNotBlank(p.url)){
//					url = URLEncoder.encode(p.url,"utf-8");
//				}
//				if(StringUtils.isNotBlank(p.imgSrc)){
//					imgSrc = URLEncoder.encode(p.imgSrc,"utf-8");
//				}
//				//String imgSrc = null;
//				
//				
//				String connectUrl = "http://www.webhezi.com/createCache.jsp?name=" //www.webhezi.com
//						+ name + "&comment=" + comment + "&imgSrc="
//						+ imgSrc + "&url=" +url+"&key="+key;
//				
//				Thread.sleep(3000);
//				
//				Connecter.connect(connectUrl, "utf-8");
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//
//}

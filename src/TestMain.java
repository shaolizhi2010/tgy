import java.util.List;
import java.util.Random;

import com.tgy.service.PageService;
import com.tgy.statistic.entity.Link;
import com.tgy.statistic.service.LinkService;
import com.tgy.statistic.service.TagService;

public class TestMain {

	public static void main(String[] args) {
		try { 
			
			new TagService().scan(); 
			new LinkService().scan();
//			int i =0;
//			while(i++<100){
//				System.out.println(new Random().nextInt(5));
//			}
//			
			System.out.println("over");
//			
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

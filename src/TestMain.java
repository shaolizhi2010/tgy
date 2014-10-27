import java.util.List;

import com.tgy.entity.Link;
import com.tgy.service.LinkService;

public class TestMain {

	public static void main(String[] args) {
		try { 
			
			List<Link> list =  new LinkService().searchByTagName("网购"); //new TagService().searchByName("喜剧");// 搞笑电影
			;
			System.out.println(list.size());
			//U.printList(list);
			for(Link l : list){
				System.out.println(l.url);
				System.out.println(l.users.size());
			}
			//System.out.println(new Gson().toJson(list));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

package com.tgy.service.commodity;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.entity.Tag;
import com.tgy.entity.User;
import com.tgy.exception.BaseException;
import com.tgy.service.FolderService;
import com.tgy.service.PageService;
import com.tgy.service.UserService;
import com.tgy.statistic.service.TagService;
import com.tgy.util.ConditionMap;
import com.tgy.util.PageType;
import com.tgy.util.PriceUtil;
import com.tgy.util.SimpleConnecter;
import com.tgy.util.U;
import com.tgy.util.X;

public class CommonCommodityService {

	public List<Page> search(String keyword, String shopName) {

		List<Page> resultPages = new ArrayList<>();

		PageService ps = new PageService();
		UserService us = new UserService();

		User u = us.getByName("网购发现");
		if (u == null || u.id == null) {
			u = new User();
			u.name = "网购发现";
			u.password = "r";
			u.isRobot = true;
			u.headImgUrl = "/images/ava/ava" + (new Random().nextInt(120) + 1)
					+ ".png";
			u.publicMessage = "网购发现";
			us.save(u);
		}

		FolderService fs = new FolderService();
		Folder f = fs.ByUserIDAndName("网购发现", u.id.toString());
		if (f == null || f.id == null) {
			f = new Folder();
			f.name = "网购发现";
			f.isRobot = true;
			f.userID = u.id.toString();
			try {
				fs.save(f);
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				System.out.println("BaiduPanDigService: 文件夹 " + "网购发现"
						+ " 无法创建");
				// return;
			}
		}

		TagService ts = new TagService();
		Tag t = ts.getByName(keyword, PageType.commodity);
		if (t == null || t.id == null) { // create tag if not exist
			t = new Tag();
			t.name = keyword;
			t.type = PageType.commodity;
			ts.save(t);
		}

		String url = null;

		try {

			url = buildSearchUrl(keyword, shopName);

			String json = SimpleConnecter.connect(url);
			
			if (StringUtils.isBlank(json)) {
				return resultPages;
			}
			List<Map> jsonProducts =  U.jsonToListMap(json);
			for (Map m : jsonProducts) {
				Page p = new Page();

				// //L.trace(this, X.toString(e));

				String name =  (String) m.get("name");

				p.name = name;
				p.title = name;

				String content = (String)m.get("content");
				content = U.Html2Text(content);
				p.summary = content;
				p.description = content;

				String productHref = (String)m.get("href");
				productHref = StringUtils.normalizeSpace(productHref);
				p.url = productHref;

				String imgUrl = (String)m.get("src");
				imgUrl = StringUtils.normalizeSpace(imgUrl);
				p.imgSrc = (imgUrl);

				// String price =
				// X.getValue(e,".//*[local-name() = 'FormattedPrice']");
				String price = (String)m.get("price");
				price = StringUtils.normalizeSpace(price);

				p.price = (price);

				p.isShare = true;
				p.tagName = keyword;
				p.type = PageType.commodity;

				p.folderID = f.id.toString();
				p.pid = f.id.toString();
				p.userID = u.id.toString();

				if (ps.list(new ConditionMap().add("url", p.url), null, 0, 0)
						.size() <= 0) {
					ps.save(p);
				}

				resultPages.add(p);

				// String source =
				// StaticInfo.getShopbyName(shopName).getShopNameCn();
				// p.setSource(source);

			} // end for

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultPages;

	}

	public String buildSearchUrl(String keyword, String shopName) {

		String requestUrl = "";
		
		if(StringUtils.equalsIgnoreCase(shopName, "jingdong")){
			requestUrl = "http://webhezi.com/wanggousousuo/searchBean?shopName=jingdong";//TODO
		}

		return requestUrl;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// System.out.println("begin");

		try {

			String keyword = "电影";

			long start = System.currentTimeMillis();
			// new
			List<Page> plist = new CommonCommodityService().search(keyword,
					"jingdong");

			System.out.println("Finish read commodity list, time is "
					+ (System.currentTimeMillis() - start));
			System.out.println("list size is " + plist.size());
			U.printList(plist);

			System.out.println("end");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

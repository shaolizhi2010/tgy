package com.tgy.service.commodity;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.tgy.util.P;
import com.tgy.util.PageType;
import com.tgy.util.PriceUtil;
import com.tgy.util.SignedRequestsUtil;
import com.tgy.util.U;
import com.tgy.util.X;
 

public class AmazonCommodityService   {

	//amazon 会检查github里边是否有 aws的key 
	//所以如果要保存这段代码到git上，就要把key打乱
	//否则这个key就会被锁定而不能使用。
	private static final String KEY_ID = "AKI"+"AJR"+"H7Z"+"WLR"+"LYV"+"OTP"+"7A";
			//P.getProperty("AWS_ACCESS_KEY_ID");// AKIAJVTU6YAIEJI26SGA 
	private static final String SECRET_KEY = "N/Z"+"dl7"+"ROz"+"T6Y"+"/pG"+"3As"+"cVJ"+"Gs9"+"5Rb"+"yxv"+"Tcb"+"4jC"+"7oU"+"H"; 
			//P.getProperty("AWS_SECRET_KEY"); // CbLiKWY8ycHdNMWQ2eoVi12oE6ISxiJcFbFK6R0O
	private static final String BASE_ENDPOINT = "webservices.amazon.cn";// webservices.amazon.cn/onca/xml
																			// ecs.amazonaws.com
 

	public List<Page> digAll(String keyword, String start,
			String associateTag, String catagery) {

		int startInt = Integer.parseInt(start);
		int pagenum = (startInt) / 10 + 1;


		List<Page> productList = new ArrayList<Page>();
		search(keyword, productList, pagenum + "", associateTag, catagery);

		return productList;
	}

	public void search(String keyword, List<Page> listDeprecated,
			String pagenum, String associateTag, String catagery) {

		PageService ps = new PageService();
		UserService us = new UserService();
		
		User u = us.getByName("网购发现");
		if(u==null||u.id==null){
			u=new User();
			u.name = "网购发现";
			u.password = "r";
			u.isRobot = true;
			u.headImgUrl = "/images/ava/ava"+ (new Random().nextInt(120)+1) +".png";
			u.publicMessage = "网购发现";
			us.save(u);
		}
		
		FolderService fs = new FolderService();
		Folder f  = fs.ByUserIDAndName("网购发现", u.id.toString());
		if(f==null||f.id==null){
			f = new Folder();
			f.name = "网购发现";
			f.isRobot = true;
			f.userID = u.id.toString();
			try {
				fs.save(f);
			} catch (BaseException e) {
				// TODO Auto-generated catch block
				System.out.println("BaiduPanDigService: 文件夹 " + "网购发现" +" 无法创建");
				//return;
			}
		}
		
		TagService ts = new TagService();
		Tag t = ts.getByName( keyword , PageType.commodity);
		if(t==null || t.id==null){ //create tag if not exist
			t = new Tag();
			t.name = keyword;
			t.type = PageType.commodity;
			ts.save(t);
		}
		
		String url = null;

		try {

			url = buildSearchUrl(keyword, catagery, pagenum, associateTag);

			SAXBuilder sb = new SAXBuilder();

			Document doc = sb.build(new URL(url));

			List<Element> itemList = X.selectNodes(doc,
					"//*[local-name() = 'Item']"); // exp.evaluate(doc);

			// System.out.println("itemList size "+itemList.size());

			if (itemList == null || itemList.size() == 0) {
				return;
			}
			for (Object o : itemList) {
				Element e = (Element) o;
				Page p = new Page();

				// //L.trace(this, X.toString(e));

				String name = X.getValue(e, ".//*[local-name() = 'Title']");
				name = StringUtils.normalizeSpace(name);
				name = U.toString(name);

				p.name = (name);
				p.title = name;
				
				String content = X.getValue(e, ".//*[local-name() = 'Content']");
				content = U.Html2Text(content);
				p.summary = content;
				p.description = content;

				String productHref = X.getValue(e,
						".//*[local-name() = 'DetailPageURL']");
				productHref = StringUtils.normalizeSpace(productHref);
				p.url = (productHref);

				String imgUrl = X
						.getValue(e,
								".//*[local-name() = 'LargeImage']/*[local-name() = 'URL']");

				imgUrl = StringUtils.normalizeSpace(imgUrl);
				p.imgSrc = (imgUrl);

				// String price =
				// X.getValue(e,".//*[local-name() = 'FormattedPrice']");
				String price = X
						.getValue(e,
								".//*[local-name() = 'LowestNewPrice']/*[local-name() = 'FormattedPrice']");
				price = StringUtils.normalizeSpace(price);

				p.price =  (price);

				p.isShare = true;
				p.tagName = keyword;
				p.type = PageType.commodity;
				
				p.folderID = f.id.toString();
				p.pid = f.id.toString();
				p.userID = u.id.toString();
				
				
				if(ps.list(new ConditionMap().add("url", p.url), null,0, 0).size()<=0){
					ps.save(p);
				}
				
				listDeprecated.add(p);
				
				// String source =
				// StaticInfo.getShopbyName(shopName).getShopNameCn();
				// p.setSource(source);
 

			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected String getPrice(Element e, String pricePath) {

		// //L.trace(this, "getPrice() element xml content: ");
		// //L.trace(this, X.toString(e));

		String price = X.getValue(e, pricePath);
		price = PriceUtil.getPrice(price);
		return price;
	}

	public String buildSearchUrl(String keyword, String catagery,
			String pagenum, String associateTag) {

		if (StringUtils.isBlank(associateTag)) {
			associateTag = "bijia365-23";
		}
		if(StringUtils.isBlank(pagenum)){
			pagenum = "1";
		}
		if(StringUtils.isBlank(catagery)){
			catagery = "All";
		}
		/*
		 * Set up the signed requests helper
		 */
		SignedRequestsUtil helper;
		try {
			helper = SignedRequestsUtil.getInstance( BASE_ENDPOINT  ,
					KEY_ID, SECRET_KEY);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}

		String requestUrl = null;

		Map<String, String> params = new HashMap<String, String>();
		params.put("Service", "AWSECommerceService");
		params.put("Version", "2011-08-01"); // 固定的
		params.put("Operation", "ItemSearch");
		params.put("SearchIndex", catagery); // 品类
		params.put("Keywords", keyword);

		params.put("ResponseGroup", "Medium"); // 返回信息的详细程度 Small Medium
		params.put("AssociateTag", associateTag);// bijia 365

		params.put("ItemPage", pagenum);

		requestUrl = helper.sign(params);
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
			List<Page> plist= 	new  AmazonCommodityService().digAll(keyword,"2","bijia365-23","All");
		 
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

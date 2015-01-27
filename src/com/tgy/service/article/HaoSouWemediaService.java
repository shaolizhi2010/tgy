package com.tgy.service.article;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.entity.User;
import com.tgy.service.FolderService;
import com.tgy.service.PageService;
import com.tgy.service.UserService;
import com.tgy.util.ConditionMap;
import com.tgy.util.Connecter;
import com.tgy.util.PageType;

public class HaoSouWemediaService { 
	
	public void digAndSave(){
		
		UserService us = new UserService();
		User u = us.getByName("自媒体");
		if(u==null||u.id==null)return;
		
		FolderService fs = new FolderService();
		Folder f  = fs.ByUserIDAndName("自媒体", u.id.toString());
		if(f==null||f.id==null)return;
		
		List<Page> articles = dig();
		PageService ps = new PageService();
		int savedCount = 0;
		for(Page a : articles){
			try {
				a.folderID = f.id.toString();
				a.userID = u.id.toString();
				
				//不存在 防重复
				if(ps.list(new ConditionMap().add("url", a.url).add("folderID", a.folderID), null,0, 0).size()<=0){
					ps.save(a);
					f.add(a);
					fs.save(f);
					savedCount++;
				}
				
			} catch (Exception e) {
				//empty
			}
		}
		System.out.println("HaoSouWemediaService : " +savedCount + " saved.");
	}
	
	public List<Page> dig(){
		List<Page> articles = new ArrayList<>();
		String s  = Connecter.getPageSource("http://wemedia.haosou.com/", "utf-8");
		s = StringUtils.substringBetween(s, "firstData =",";");
		if(StringUtils.isBlank(s)){
			s = StringUtils.substringBetween(s, "firstData=",";");
		}
		if(StringUtils.isBlank(s)){
			System.out.println("抓取haoso文章失败！");
			return articles;
		}
		//ArticleService as = new ArticleService();
		List<Map<String,String>> list = new Gson().fromJson(s, new TypeToken< List<Map<String,String>>>(){}.getType());
		for(Map<String,String> m : list){
			try {
				Page   a = new Page();
				a.title = m.get("title");
				a.name = m.get("title");
				a.summry = m.get("description");
				a.description = m.get("description");
				a.url = m.get("pc_url");
				a.imgSrc = m.get("big_img");
				if(StringUtils.isBlank(a.imgSrc)){
					a.imgSrc = m.get("img");	
				}
				a.orignDate = m.get("record_time");
				a.authorHearImgSrc = m.get("weibo_img");
				a.authorName = m.get("weibo_name");
				a.authorUrl = "weibo.com/"+m.get("weibo_uid");
				a.isShare = true;
				a.type= PageType.article; 
				articles.add(a);
			}
			catch (Exception e) {
				//empty
				continue;
			}
		}
		return articles;
	}
	
	public static void main(String[] args) {
		
	//	String s = " [{\"uid\":\"1212\",\"weibo_uid\":\"1609735332\",\"img\":\"http:\/\/p1.qhimg.com\/t0104e5c31770d37b93.jpg?size=400x300\",\"title\":\"\u5357\u90fd\u793e\u8bba\uff1a\u201c\u628a\u5f8b\u5e08\u8d76\u51fa\u6cd5\u5ead\u201d\uff0c\u6cd5\u6cbb\u5c06\u767e\u601d\u4e0d\u5f97\u89e3\",\"description\":\"[\u793e\u8bba]\u201c\u628a\u5f8b\u5e08\u8d76\u51fa\u6cd5\u5ead\u201d\uff0c\u6cd5\u6cbb\u5c06\u767e\u601d\u4e0d\u5f97\u89e3\u6765\u6e90\uff1a\u5357\u65b9\u90fd\u5e02\u62a52015\u5e7401\u670825\u65e5\u661f\u671f\u65e5\u3000\u3000\u65e5\u524d\uff0c\u5168\u56fd\u9ad8\u7ea7\u6cd5\u9662\u9662\u957f\u4f1a\u8bae\u5728\u5317\u4eac\u4e3e\u884c\uff0c\u6700\u9ad8\u4eba\u6c11\u6cd5\u9662\u9662\u957f\u5468\u5f3a\u8868\u793a\uff0c\u8981\u5207\u5b9e\u89e3\u51b3\u201c\u5ead\u5ba1\u865a\u5316\u201d\u3001\u8d70\u8fc7\u573a\u548c\u6446\u5f62\u5f0f\u7684\u95ee\u9898...\",\"pc_url\":\"http:\/\/weibo.com\/p\/1001603802827746095165?coo=1&c=spr_qdhz_bd_360dh_weibo_zmt\",\"record_time\":\"2015-01-25 09:10:52\",\"weibo_name\":\"\u674e\u8499\u4e0d\u8499\u4f60\",\"idx\":\"1\",\"re_idx\":\"0\",\"small_img\":\"http:\/\/p4.qhimg.com\/dmfd\/80_60_\/t0104e5c31770d37b93.jpg\",\"big_img\":\"http:\/\/p4.qhimg.com\/dmfd\/230_138_\/t0104e5c31770d37b93.jpg\",\"media_url\":\"http:\/\/wemedia.haosou.com\/media\/1212\/\",\"weibo_img\":\"http:\/\/p1.qhimg.com\/dmfd\/70_70_\/t01f3211dc2d7fb6a9a.jpg\"},{\"uid\":\"1146\",\"weibo_uid\":\"1667434994\",\"img\":\"\",\"title\":"\u300a\u8003\u7814\u8bcd\u6c47\u4f1a\u8fd9\u4e9b\u5c31\u7edd\u5bf9\u591f\u4e86\u300b\u2014\u2014\u7b2c\u4e00\u96c6","description":"\u300a\u8003\u7814\u8bcd\u6c47\u4f1a\u8fd9\u4e9b\u5c31\u7edd\u5bf9\u591f\u4e86\u300b\u2014\u2014\u7b2c\u4e00\u96c6absurd[\u0259b\u02c8s\u0259:d]a.\u4e0d\u5408\u7406\u7684\uff0c\u8352\u8c2c\u7684  sound [saund] n.\u58f0\u97f3a.\u5065\u5168\u7684\uff1b\u5408\u7406\u7684  sonar [\u02c8s\u0259\u028an\u0251:(r)]n.\u58f0\u5450\u88c5\u7f6e\uff0c...","pc_url":"http:\/\/weibo.com\/p\/1001603802827079193775?coo=1&c=spr_qdhz_bd_360dh_weibo_zmt","record_time":"2015-01-25 09:08:14","weibo_name":"\u5218\u4e00\u7537","idx":"2","re_idx":"0","small_img":"","big_img":"","media_url":"http:\/\/wemedia.haosou.com\/media\/1146\/","weibo_img":"http:\/\/p7.qhimg.com\/dmfd\/70_70_\/t018ba4f9124fcde796.jpg"},{"uid":"1658","weibo_uid":"1573471910","img":"http:\/\/p1.qhimg.com\/t018ebdb4c51740cc5d.jpg?size=690x517","title":"\u7532\u4ea2\u60a3\u8005\u62cd\u6253\u62c9\u7b4b\u6cbb\u61088\u79cd\u75c5","description":"\u8fd9\u4e2a\u6848\u4f8b\u8116\u5b50\u3001\u4e0b\u5df4\u3001\u5934\u90e8\u90fd\u51fa\u75e7\uff0c\u6240\u4ee5\u5f88\u6709\u4ee3\u8868\u6027\uff0c\u5c24\u5176\u5bf9\u7532\u72b6\u817a\u7c7b\u75c5\u3002\u770b\u4e0a\u53bb\u662f\u8116\u5b50\u4e0a\u957f\u4e86\u4e2a\u5305\uff0c\u5176\u5b9e\u662f\u4e94\u810f\u516d\u8151\u90fd\u6709\u75c5\uff01\u770b\u5979\u62cd\u6253\u62c9\u7b4b\u524d\u7684\u75c7\u72b6\uff1a\u624b\u811a\u51b0\u51b7\u3001\u5931\u7720\u3001\u4fbf\u79d8\u3001\u50f5\u786c......\u7b80\u8a00\u4e4b\u5c31\u662f\u590d\u5408\u75c5\uff01\u6240\u4ee5\u8981\u5fd8\u8bb0...","pc_url":"http:\/\/weibo.com\/p\/2304185dc946a60102vald?coo=1&c=spr_qdhz_bd_360dh_weibo_zmt","record_time":"2015-01-25 09:03:20","weibo_name":"\u8427\u5b8f\u6148","idx":"3","re_idx":"0","small_img":"http:\/\/p4.qhimg.com\/dmfd\/80_60_\/t018ebdb4c51740cc5d.jpg","big_img":"http:\/\/p4.qhimg.com\/dmfd\/230_138_\/t018ebdb4c51740cc5d.jpg","media_url":"http:\/\/wemedia.haosou.com\/media\/1658\/","weibo_img":"http:\/\/p3.qhimg.com\/dmfd\/70_70_\/t0122c96fb604ae7552.jpg"},{"uid":"255","weibo_uid":"1633716794","img":"http:\/\/p1.qhimg.com\/t01f5dbccf98d21b5a9.jpg?size=440x331","title":"\u4e0e\u7535\u5f71\u6709\u5173\u7684\u4e13\u4e1a\u82f1\u8bed\u8bcd\u6c47","description":"Movie Genres \u7535\u5f71\u79cd\u7c7b  thrillers \u60ca\u609a\u7247  science fiction \u79d1\u5e7b\u7247  comedies \u559c\u5267\u7247  romantic comedies\/romcoms \u6d6a\u6f2b\u559c\u5267\u7247  westerns \u897f\u90e8\u7247  costume dramas \u53e4\u88c5\u5267  horror \u6050\u6016\u7247  animated film \u52a8\u753b\u7247  act...","pc_url":"http:\/\/weibo.com\/p\/1001603802812621420243?coo=1&c=spr_qdhz_bd_360dh_weibo_zmt","record_time":"2015-01-25 08:10:47","weibo_name":"\u82f1\u8bed\u8001\u5e08\u9a86\u519b","idx":"4","re_idx":"0","small_img":"http:\/\/p10.qhimg.com\/dmfd\/80_60_\/t01f5dbccf98d21b5a9.jpg","big_img":"http:\/\/p10.qhimg.com\/dmfd\/230_138_\/t01f5dbccf98d21b5a9.jpg","media_url":"http:\/\/wemedia.haosou.com\/luojun.html","weibo_img":"http:\/\/p9.qhimg.com\/dmfd\/70_70_\/t016175188d90ca1788.jpg"},{"uid":"745","weibo_uid":"1120472023","img":"http:\/\/p1.qhimg.com\/t013067eb2a4d863d77.jpg?size=600x399","title":"\u300a\u4f55\u4ee5\u7b19\u7bab\u9ed8\u300b\u6211\u82e5\u4e0d\u52c7\u6562\uff0c\u8c01\u66ff\u6211\u575a\u5f3a","description":"\u6b63\u5728\u70ed\u64ad\u7684\u300a\u4f55\u4ee5\u7b19\u7bab\u9ed8\u300b\u7531\u949f\u6c49\u826f\u3001\u5510\u5ae3\u8054\u8882\u4e3b\u6f14\uff0c\u989c\u503c\u9887\u9ad8\u7684\u4e00\u51b7\u4e00\u6696\uff0c\u6545\u4e8b\u4e5f\u8db3\u591f\u66f2\u6298\u7684\u53c8\u8650\u53c8\u7597\uff0c\u7ec6\u770b\u4e0b\u6765\uff0c\u9887\u6709\u4e9b\u8da3\u5473\uff0c\u800c\u8fd9\u8da3\u5473\u80cc\u540e\uff0c\u5374\u662f\u4e00\u79cd\u575a\u6301\uff0c\u5bf9\u751f\u6d3b\uff0c\u5bf9\u7231\u60c5\uff0c\u5bf9\u81ea\u5df1\u5185\u5fc3\u7684\u6267\u7740\uff0c\u662f\u672c\u5267\u6700\u52b1\u5fd7\u7684\u6240\u5728\u3002","pc_url":"http:\/\/weibo.com\/p\/23041842c90bd70102vecl?coo=1&c=spr_qdhz_bd_360dh_weibo_zmt","record_time":"2015-01-25 08:03:24","weibo_name":"\u6218\u53f0\u70fd","idx":"5","re_idx":"0","small_img":"http:\/\/p8.qhimg.com\/dmfd\/80_60_\/t013067eb2a4d863d77.jpg","big_img":"http:\/\/p8.qhimg.com\/dmfd\/230_138_\/t013067eb2a4d863d77.jpg","media_url":"http:\/\/wemedia.haosou.com\/media\/745\/","weibo_img":"http:\/\/p2.qhimg.com\/dmfd\/70_70_\/t01a58dd42dde481bdb.jpg"},{"uid":"683","weibo_uid":"1246151574","img":"http:\/\/p1.qhimg.com\/t0135e964e072b19d43.jpg?size=440x293","title":"\u5927\u5b66\u751f\u5206\u671f\u4e3a\u4f55\u4f1a\u6210\u4e3a\u201c\u65b0\u5ba0\u201d\uff1f","description":"\u4e00\u591c\u4e4b\u95f4\uff0c\u674e\u5609\u8bda\u5c31\u4e0d\u662f\u9996\u5bcc\u4e86\u3002\u4e00\u591c\u4e4b\u95f4\uff0c\u5976\u8336\u59b9\u5c31\u4e0d\u662f\u4e1c\u5ac2\u4e86\u3002\u4e00\u591c\u4e4b\u95f4\uff0c\u5510\u671d\u5c31\u6ca1\u6709\u5927\u80f8\u4e86\u3002\u6211\u662f\u8d8a\u6765\u8d8a\u4e0d\u61c2\uff0c\u660e\u5929\u4f1a\u5439\u4ec0\u4e48\u98ce\uff1f\u4e5f\u662f\u4e00\u591c\u4e4b\u95f4\uff0c\u5206\u671f\u4e50\u3001\u55b5\u8d37\u3001\u8da3\u5206\u671f\u7b49\u5927\u5b66\u751f\u5206\u671f\u5e73\u53f0\u63a5\u8fde\u5192\u8d77\uff0c\u8fd9\u79cd\u65b0\u5174\u7684\u4e1a\u52a1\u6a21\u5f0f\u5728...","pc_url":"http:\/\/weibo.com\/p\/1001603802809823798217?coo=1&c=spr_qdhz_bd_360dh_weibo_zmt","record_time":"2015-01-25 07:59:39","weibo_name":"\u5eb7\u65af\u5766\u4e01","idx":"6","re_idx":"0","small_img":"http:\/\/p4.qhimg.com\/dmfd\/80_60_\/t0135e964e072b19d43.jpg","big_img":"http:\/\/p4.qhimg.com\/dmfd\/230_138_\/t0135e964e072b19d43.jpg","media_url":"http:\/\/wemedia.haosou.com\/media\/683\/","weibo_img":"http:\/\/p2.qhimg.com\/dmfd\/70_70_\/t011bd0c56a87c898bb.jpg"},{"uid":"1607","weibo_uid":"1223877100","img":"http:\/\/p1.qhimg.com\/t01af44357d5731f0b4.jpg?size=690x388","title":"2015\u5e74\u5317\u4eac\u7684\u7b2c\u4e8c\u6b21\u96ea\u2014\u2014\u975e\u7b2c\u4e8c\u573a\u96ea\uff1f","description":"2015\u5e741\u670824\u65e5\u665a\uff0c\u5ffd\u7136\u5c31\u98d8\u98d8\u6447\u6447\u6765\u4e86\u4e00\u70b9\u96ea\uff0c\u8bfb\u4e66\u5199\u5b57\u95f4\u5076\u7136\u671b\u51fa\uff0c\u60ca\u8bb6\u4e0d\u5df2  \u4e0b\u96ea\u662f\u4e00\u79cd\u81ea\u7136\u73b0\u8c61\uff0c\u4f46\u4efb\u4f55\u81ea\u7136\u7684\u72ec\u7acb\u5b58\u5728\u90fd\u4f1a\u6fc0\u8d77\u4eba\u7684\u60c5\u611f\u547c\u5e94---\u6240\u8c13\u5ba1\u7f8e\uff0c\u8fd9\u591c\u8272\u4e2d\u7684\u96ea\u8272\u8986\u76d6\u591a\u4e48\u52a8\u4eba   \u770b\u7740\u884c\u9a76\u8f66...","pc_url":"http:\/\/weibo.com\/p\/23041848f2e1ec0102vd1m?coo=1&c=spr_qdhz_bd_360dh_weibo_zmt","record_time":"2015-01-25 07:57:06","weibo_name":"\u5468\u661f","idx":"7","re_idx":"0","small_img":"http:\/\/p5.qhimg.com\/dmfd\/80_60_\/t01af44357d5731f0b4.jpg","big_img":"http:\/\/p5.qhimg.com\/dmfd\/230_138_\/t01af44357d5731f0b4.jpg","media_url":"http:\/\/wemedia.haosou.com\/media\/1607\/","weibo_img":"http:\/\/p4.qhimg.com\/dmfd\/70_70_\/t01dcf0dffa38d817dd.jpg"},{"uid":"1756","weibo_uid":"1223299212","img":"http:\/\/p1.qhimg.com\/t01376fcd49c69d2abb.jpg?size=240x240","title":"\u725b\u5200\uff1a\u56fd\u9645\u8d44\u672c\u53d1\u8d77\u603b\u653b\u7684\u65f6\u95f4\u5230\u4e86","description":"\u56fd\u9645\u8d44\u672c\u53d1\u8d77\u603b\u653b\u7684\u65f6\u95f4\u5230\u4e86            \u201c\u5168\u7403\u8d27\u5e01\u6218\u4e89\u5f00\u6253\uff0c\u5229\u597d\u4e2d\u56fd\u80a1\u5e02\u201d\u3002\u8fd9\u4e48\u611a\u8822\u7684\u8bdd\u90fd\u80fd\u8bf4\u7684\u51fa\u6765\uff0c\u53ef\u89c1\u4e2d\u56fd\u4eba\u81ea\u79c1\u81ea\u5229\u548c\u8150\u8d25\u8d2a\u5a6a\u7684\u672c\u6027\u662f\u6539\u4e0d\u4e86\u7684\u3002\u672c\u6b21\u5168\u7403\u770b\u4f3c\u6df7\u6218\uff0c\u5b9e\u9645\u4e0a\u9488\u5bf9\u4e2d\u56fd\u653f\u5e9c\uff0c\u76ee\u7684...","pc_url":"http:\/\/weibo.com\/p\/23041848ea108c0102viub?coo=1&c=spr_qdhz_bd_360dh_weibo_zmt","record_time":"2015-01-25 07:55:12","weibo_name":"\u725b\u5200","idx":"8","re_idx":"0","small_img":"http:\/\/p2.qhimg.com\/dmfd\/80_60_\/t01376fcd49c69d2abb.jpg","big_img":"http:\/\/p2.qhimg.com\/dmfd\/230_138_\/t01376fcd49c69d2abb.jpg","media_url":"http:\/\/wemedia.haosou.com\/media\/1756\/","weibo_img":"http:\/\/p6.qhimg.com\/dmfd\/70_70_\/t018b2c9d97d6de496f.jpg"},{"uid":"1636","weibo_uid":"1253686121","img":"http:\/\/p1.qhimg.com\/t019ce1950c2fb2c7a2.jpg?size=500x370","title":"\u4e2d\u56fd\u58f0\u97f3\uff1a\u4efb\u6b63\u975e\u5728\u8fbe\u6c83\u65af\u8bba\u575b\u53d1\u8a00\u5b9e\u5f55","description":"\u4efb\u6b63\u975e\u7b2c\u4e00\u6b21\u516c\u5f00\u63a5\u53d7\u76f4\u64ad\u5bf9\u8bdd\uff0c\u62ab\u9732\u4e86\u8bb8\u591a\u5173\u952e\u7ec6\u8282\uff1a1\u3001\u610f\u5916\u5f53\u5175\uff0c\u56e0\u4e3a\u4e09\u7ebf\u5efa\u8bbe\u9700\u8981\u5de5\u7a0b\u5175\uff0c\u9700\u8981\u5927\u5b66\u751f\uff1b2\u3001\u9000\u5f79\u540e\u53c8\u88ab\u56fd\u8425\u4f01\u4e1a\u9664\u540d\uff0c\u65e0\u8def\u53ef\u8d70\uff0c\u53ea\u597d\u81ea\u5df1\u521b\u4e1a\uff1b3\u3001\u56e0\u4e3a\u5e7c\u7a1a\uff0c\u9009\u62e9\u4e86\u6807\u51c6\u5168\u7403\u5316\u6700\u5f7b\u5e95\u7684\u901a\u8baf\u4e1a\uff0c\u4e00...","pc_url":"http:\/\/weibo.com\/p\/2304184ab9bb690102vaen?coo=1&c=spr_qdhz_bd_360dh_weibo_zmt","record_time":"2015-01-25 07:50:05","weibo_name":"\u738b\u80b2\u7428","idx":"9","re_idx":"0","small_img":"http:\/\/p3.qhimg.com\/dmfd\/80_60_\/t019ce1950c2fb2c7a2.jpg","big_img":"http:\/\/p3.qhimg.com\/dmfd\/230_138_\/t019ce1950c2fb2c7a2.jpg","media_url":"http:\/\/wemedia.haosou.com\/media\/1636\/","weibo_img":"http:\/\/p5.qhimg.com\/dmfd\/70_70_\/t01c4e6961b2a862cce.jpg"},{"uid":"957","weibo_uid":"2117355594","img":"http:\/\/p1.qhimg.com\/t015fb5a8c6afc372ac.webp?size=553x609","title":"\u5b8c\u6574\u72482015\u4eba\u4f53\u5065\u7f8e\u6b23\u8d4f \u9707\u64bc\u6027\u611f\u6781\u9650","description":"\u5979\u662fIFBB\u804c\u4e1a\u9009\u624b\uff0c\u5316\u5986\u5e08\uff0c\u4e5f\u662f\u56fd\u9645\u804c\u4e1a\u6559\u7ec3\uff01\u5979\u662fFitMiss\u7684\u4ee3\u8a00\u4eba\uff0c\u5065\u8eab\u6a21\u7279\uff0c\u4e24\u6b21\u56fd\u9645\u6bd4\u57fa\u5c3c\u5927\u8d5b\u51a0\u519b\uff01\u4ed6\u662fWBFF\u804c\u4e1a\u5065\u8eab\u6a21\u7279\uff0cMYPROTEIN\u5927\u4f7f\uff01\u5979\u662fIFBB\u804c\u4e1a\u5065\u8eab\u6a21\u7279\uff0cBB\u7f51\u7b7e\u7ea6\u5065\u8eab...","pc_url":"http:\/\/weibo.com\/p\/1001603802805956685023?coo=1&c=spr_qdhz_bd_360dh_weibo_zmt","record_time":"2015-01-25 07:44:17","weibo_name":"\u5f6d\u6653\u8f89","idx":"10","re_idx":"0","small_img":"http:\/\/p1.qhimg.com\/dmfd\/80_60_\/t015fb5a8c6afc372ac.webp","big_img":"http:\/\/p1.qhimg.com\/dmfd\/230_138_\/t015fb5a8c6afc372ac.webp","media_url":"http:\/\/wemedia.haosou.com\/media\/957\/","weibo_img":"http:\/\/p1.qhimg.com\/dmfd\/70_70_\/t012efe1362af1ea69a.jpg"}]";
		new HaoSouWemediaService().digAndSave(); 
		System.out.println("--- end ---");
		
		//String s  = Connecter.getPageSource("http://tjapi.news.haosou.com/getmutispt?callback=jQuery18301352672129869461_1422179935728&f=jsonp&url=http%3A%2F%2Fweibo.com%2Fp%2F1001603802827746095165%3Fcoo%3D1%26c%3Dspr_qdhz_bd_360dh_weibo_zmt%2Chttp%3A%2F%2Fweibo.com%2Fp%2F1001603802827079193775%3Fcoo%3D1%26c%3Dspr_qdhz_bd_360dh_weibo_zmt%2Chttp%3A%2F%2Fweibo.com%2Fp%2F2304185dc946a60102vald%3Fcoo%3D1%26c%3Dspr_qdhz_bd_360dh_weibo_zmt%2Chttp%3A%2F%2Fweibo.com%2Fp%2F1001603802812621420243%3Fcoo%3D1%26c%3Dspr_qdhz_bd_360dh_weibo_zmt%2Chttp%3A%2F%2Fweibo.com%2Fp%2F23041842c90bd70102vecl%3Fcoo%3D1%26c%3Dspr_qdhz_bd_360dh_weibo_zmt%2Chttp%3A%2F%2Fweibo.com%2Fp%2F1001603802809823798217%3Fcoo%3D1%26c%3Dspr_qdhz_bd_360dh_weibo_zmt%2Chttp%3A%2F%2Fweibo.com%2Fp%2F23041848f2e1ec0102vd1m%3Fcoo%3D1%26c%3Dspr_qdhz_bd_360dh_weibo_zmt%2Chttp%3A%2F%2Fweibo.com%2Fp%2F23041848ea108c0102viub%3Fcoo%3D1%26c%3Dspr_qdhz_bd_360dh_weibo_zmt%2Chttp%3A%2F%2Fweibo.com%2Fp%2F2304184ab9bb690102vaen%3Fcoo%3D1%26c%3Dspr_qdhz_bd_360dh_weibo_zmt%2Chttp%3A%2F%2Fweibo.com%2Fp%2F1001603802805956685023%3Fcoo%3D1%26c%3Dspr_qdhz_bd_360dh_weibo_zmt&_=1422182798051", "utf-8");
		//String ss = StringUtils.substringBetween(s, "(",")");
		//Map m =  new Gson().fromJson(ss, new TypeToken<Map>(){}.getType());
//		String[] ss = s.split("http:");
//		for(String sss : ss){
//			String ssss = StringUtils.substringBetween(sss, "weibo.com","?");
//			String sssss = StringUtils.substringAfterLast(ssss, "/");
//			System.out.println(sssss);
//		}
//		
//		Document doc;
		try {
			//doc = new org.jdom2.input.SAXBuilder().build(new StringReader(s));
			
//			List<Element> elementList = X.getSubElementList(doc, "//*[contains(@class, 'recommended-articles')]");
//			for(Element e : elementList){
//				
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(s);
		
	}

}

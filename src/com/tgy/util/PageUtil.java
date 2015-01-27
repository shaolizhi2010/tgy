package com.tgy.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tgy.App;
import com.tgy.entity.BasePage;
import com.tgy.entity.Page;

public class PageUtil {
	
	public static final int defaultPageNameMaxLength = 20;
	public static final String appendix = "...";
	
	public static String iconPath(BasePage p){
		String iconPath = "";
		if(StringUtils.isNoneBlank(p.iconPath)  ){
			iconPath = p.iconPath;
			if(iconPath.startsWith(App.imgPath)){
				iconPath = "/"+iconPath;
			}
		}
		else{
			iconPath =  "/images/defaultFav.png";
		}
		return iconPath;
	}
	
	
	/**
	 * 显示在页面的url，
	 * 省略http：//, url太长的也需要省略
	 * @param p
	 * @param length
	 * @return
	 */
	public static String shortUrl(BasePage p,int length){
		
		if(p==null || StringUtils.isBlank(p.url) ){
			return "";
		}
		
		String linkshow = p.url;
		linkshow = linkshow.trim();
		
		//省略http：// 
		 
		if (linkshow.startsWith("http://")) {
			linkshow = linkshow.replaceAll("http://", "");
		}
		if (linkshow.startsWith("https://")) {
			linkshow = linkshow.replaceAll("https://", "");
		}
		
		//url太长，中间省略，保留前后部分，中间省略掉
		int linkLength = linkshow.length();
		if (linkLength > length) {
			linkshow = linkshow.substring(0, (length*2/3)) + appendix
					+ linkshow.substring(linkLength - length/3);
		}
		return linkshow;
	 
	}
	
	/**
	 * 页面 href 用
	 * 如果链接不是以http开头，则补上http：//否则页面打开会出错 显示成webhezi.com/www.baidu.com 形式
	 * @param p
	 * @return
	 */
	public static String urlWithHttp(BasePage p){
		if(p==null || StringUtils.isBlank(p.url) ){
			return "";
		}
		
		String linkStr = p.url;
		linkStr = linkStr.trim();
		
		//补全 http:
		if( !linkStr.startsWith("http")){
			linkStr = "http://"+linkStr;
		}
		
		return linkStr;
	}
	public static String urlWithHttp(String linkStr){
		if( StringUtils.isBlank(linkStr) ){
			return "";
		}
		
		 
		linkStr = linkStr.trim();
		
		//补全 http:
		if( !linkStr.startsWith("http")){
			linkStr = "http://"+linkStr;
		}
		
		return linkStr;
	}
	
	/**
	 * page name 太长需省略显示
	 * @param p
	 * @return
	 */
	public static String shortName(BasePage p,int length ) {
		if(p==null ){
			return "";
		}
		String name = p.name;
		name = StringUtils.trim(name);
		
		//网页有name
		if(StringUtils.isNotBlank(name)){
			if(name.length() > length){ //太长
				//有特殊字符，把特殊字符前后截断，特殊字符最前边的，一般是网站名
				String[] arr = name.split("[- ，,.():：（）【】]");
				if (arr.length > 1) {
					name = arr[0] + appendix;
				}
			}
			if(name.length() > length){	//还是太长
				name = StringUtils.substring(name,0, length)  + appendix;
			}
			return name;
		}
		else{ //网页没有name
			//没有name 尝试description 
			 
			name =  p.description ;
			if(StringUtils.length(name) > length){	// 太长
				name = StringUtils.substring(name,0, length) + appendix;
			}
			 
			
			// 是空 尝试返回url
			if( StringUtils.isBlank(p.name)  ){ //page name为空，则显示page url
				name = shortUrl(p,length); //最长显示40个字符
			}
			return name;
		}
	}
	
	/**
	 * 
	 * @param p
	 * @param showUrlIfnull
	 * @return
	 */
	public static String name(BasePage p ){
		if(p==null){
			return "";
		}
		
		return shortName(p, defaultPageNameMaxLength);
	}
	
	public static void sortByOrderIndex(List<Page> list){
		Collections.sort(list, new Comparator<Page>( ) {

			@Override
			public int compare(Page o1, Page o2) {
				if(o1.sortOrder > o2.sortOrder){
					return 1;
				}
				else if(o1.sortOrder < o2.sortOrder){
					return -1;
				}
				else {
					return 0;
				}				
			}
			
		});
	}
	 
}

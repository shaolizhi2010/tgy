package com.tgy.util;

import java.io.File;
import java.io.InputStream;
import java.net.URI;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import com.tgy.App;
import com.tgy.service.WebInfo;

public class WebInfoUtil {

	public String checkICON(String url) {
		//String faviconUrl = URLUtils.getBaseUrl(url) + "/favicon.ico";

		try {
			byte[] data = IOUtils.toByteArray(new URI(url));
			if (data.length <= 0 || new String(data).contains("404")) {
				return "";
			} else {
				
				String md5String = org.apache.commons.codec.digest.DigestUtils.md5Hex(data);
				File iconFile = new File(App.basePath+App.imgPath+md5String);
				if(!iconFile.exists()){
					FileUtils.writeByteArrayToFile(iconFile, data);
				}
				return App.imgPath+md5String;
			}

		} catch (Exception e) {
			//System.out.println(e.getMessage());
			return "";
		} 

	}

	public WebInfo info(String url) {
		return info(url,true);
	}
	public WebInfo info(String url, boolean getIconFlag) {

		WebInfo info = new WebInfo();

		try {
			long starttime = System.currentTimeMillis();
			HttpClient httpclient = App.getInstance().getHttpClient();
			HttpGet httpget = null;
			
			String baseUrl = URLUtils.getBaseUrl(url);
			String formatedUrl = URLUtils.getFormatedUrl(url);
			httpget = new HttpGet(formatedUrl);
			httpget.setHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			 httpget.setHeader("Accept-Language", "zh-CN");
			httpget.setHeader("Accept-Charset", "UTF-8");
			// httpget.setHeader("Referer", baseUrl); // 模拟浏览器//TODO

			// L.trace(null,"Connecter start download page, time is " +
			// starttime );
			HttpResponse response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			// System.out.println( "链接时间 "+(
			// System.currentTimeMillis()-starttime));

			// int size = is.available();
			byte[] buffer = new byte[10240];
			is.read(buffer, 0, 10240);
			is.close();
			String contents = new String(buffer);

			// System.out.println(contents);

			String charset = getCharset(contents);
			info.charset = charset;

			if (StringUtils.isBlank(charset)) {
				charset = "utf-8";
			}
			String contentNew = new String(buffer, charset);
			contentNew = StringUtils.lowerCase(contentNew);
			info.title = StringUtils.trim(getTitle(contentNew));
			info.description = StringUtils.trim(getDescription(contentNew));
			
			// icon操作放在后边，加快前台返回速度
			if(getIconFlag){//TODO for test 
				info.iconPath = StringUtils.trim(getIconPath(contentNew,url));
			}
			

			//System.out.println( "WebInfoUtil 总共时间 "+(
			// System.currentTimeMillis()-starttime));
			//System.out.println("info.iconPath "+ info.iconPath);
			return info;
		} catch (Exception e) {
			System.out.println("WebInfoUtil Exception : "+e.getMessage());
			//e.printStackTrace();
			return null;
		}
	}

	public String getIconPath(String str1,String url){
		
		String iconPath = null;
		String baseUrl = URLUtils.getBaseUrl(url);
		iconPath = baseUrl+"/favicon.ico";
		
		String savedIconPath = "";
		savedIconPath = checkICON(iconPath);
		if( StringUtils.isNotBlank(  savedIconPath ) ){
			return savedIconPath;
		}
		
		if(str1.contains("favicon.ico")){
			String str = StringUtils.substringBefore(str1, "favicon.ico");
			if(StringUtils.isNotBlank(str)){
				iconPath = StringUtils.substringAfterLast(str, "href=\"");
				
				if(iconPath.startsWith("//")){
					iconPath =  "http:"+iconPath+"favicon.ico";
					savedIconPath = checkICON(iconPath);
					if( StringUtils.isNotBlank(  savedIconPath ) ){
						return savedIconPath;
					}
				}
				if(iconPath.startsWith("/")){
					iconPath =  baseUrl+iconPath+"favicon.ico";
					savedIconPath = checkICON(iconPath);
					if( StringUtils.isNotBlank(  savedIconPath ) ){
						return savedIconPath;
					}
				}
				if(iconPath.startsWith("http")){
					iconPath =   iconPath+"favicon.ico";
					savedIconPath = checkICON(iconPath);
					if( StringUtils.isNotBlank(  savedIconPath ) ){
						return savedIconPath;
					}
				}
				else{
					iconPath = baseUrl+ "/"+ iconPath+"favicon.ico";
					savedIconPath = checkICON(iconPath);
					if( StringUtils.isNotBlank(  savedIconPath ) ){
						return savedIconPath;
					}
				}
			}
		}
		else if(str1.contains("icon\"")){
			String str = StringUtils.substringBetween(str1, "icon\"",">");
			if(str.contains("href")){
				iconPath = getHref(str);
			}
			else{
				str = StringUtils.substringBetween(str1, "<","icon\"" );
				if(str.contains("href")){
					iconPath = getHref(str);
				}
			}
			if(iconPath.startsWith("//")){
				iconPath = "http:"+iconPath;
			}
			savedIconPath = checkICON(iconPath);
			if( StringUtils.isNotBlank(  savedIconPath ) ){
				return savedIconPath;
			}
		}
//		else{
//			return baseUrl+"/favicon.ico";
//		}
		
		return "";
		
//		String str = StringUtils.substringAfter(str1, "\"shortcut icon\"");
//		if (StringUtils.isBlank(str)) { // 单引号
//			str = StringUtils.substringAfter(str1, "\'shortcut icon\'");
//			iconPath = StringUtils.substringBetween(str, "href=\'", "\'");
//			return iconPath;
//		} else { // 双引号
//			iconPath = StringUtils.substringBetween(str, "href=\"", "\"");
//			return iconPath;
//		}
		
	}
	
	public String getHref(String str){
		String href = "";
		if(str.contains("href=\"")){//双引号
			 href =  StringUtils.substringBetween(str, "href=\"","\"");
		}
		else if(str.contains("href=\'")){//单引号
			 href =  StringUtils.substringBetween(str, "href=\'","\'");
		}
		return href;
	}
	
	public String getTitle(String str) {
		String title = null;
		title = StringUtils.substringBetween(str, "<title>", "</title>");

		return title;
	}

	public String getDescription(String str1) {
		String description = null;

		String str = StringUtils.substringAfter(str1, "\"description\"");
		if (StringUtils.isBlank(str)) { // 单引号
			str = StringUtils.substringAfter(str1, "\'description\'");
			description = StringUtils.substringBetween(str, "content=\'", "\'");
			return description;
		} else { // 双引号
			description = StringUtils.substringBetween(str, "content=\"", "\"");
			return description;
		}

	}

	public String getCharset(String line) {

		String charsetStr = "";
		if(line.contains("script")){
			line = StringUtils.substringBefore(line, "script");//一般charset设置都在script之前，排除掉script中charset设置的干扰	
		}
		
		

		if (StringUtils.substringBetween(line, "harset=\"", "\"") != null) {// 找到
			// charset，c可能大些
			// 略去
			charsetStr = StringUtils.substringBetween(line, "harset=\"", "\"");
			if (U.validateCharset(charsetStr))
				return charsetStr;

		}
		if (StringUtils.substringBetween(line, "harset=", "\"") != null) {// 找到
			// charset，c可能大些
			// 略去
			charsetStr = StringUtils.substringBetween(line, "harset=", "\"");
			if (U.validateCharset(charsetStr))
				return charsetStr;
		}
		if (StringUtils.lowerCase(line).contains("gb2312")) {
			charsetStr = "gb2312";
			return charsetStr;
		}
		if (StringUtils.lowerCase(line).contains("gbk")) {
			charsetStr = "gbk";
			return charsetStr;
		}
		if (StringUtils.lowerCase(line).contains("utf-8")) {
			charsetStr = "utf-8";
			return charsetStr;
		}
		if (StringUtils.lowerCase(line).contains("iso-8859-1")) {
			charsetStr = "iso-8859-1";
			return charsetStr;
		}

		return null;

	}

}

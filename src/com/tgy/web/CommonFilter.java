package com.tgy.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.tgy.util.U;

@WebFilter("/*")
public class CommonFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;

		//301 redirect
		String requestPage = req.getRequestURI();   
		String queryString = (req.getQueryString() == null ? "" : "?"+req.getQueryString());   

		//attempt to merge non-www urls   
		if(req.getRequestURL().indexOf("http://webhezi.com") >=0){
			res.setStatus(301);   
			res.setHeader( "Location", "http://www.webhezi.com"+requestPage+queryString);   
			res.setHeader( "Connection", "close" );   
		}
		if(req.getRequestURL().indexOf("tangguoyun.com") >=0){
			res.setStatus(301);   
			res.setHeader( "Location", "http://www.webhezi.com"+requestPage+queryString);   
			res.setHeader( "Connection", "close" );   
		}
		
		//搜索引擎过来的访问，直接转到相关搜索词的网盘搜索
		String refer = req.getHeader("Referer");
		if(StringUtils.isNoneBlank(refer) 
				&& !(requestPage+queryString).contains("tagName=")  //tag页不跳转
				&& !(requestPage+queryString).contains("/pan/")){	//网盘搜索页不跳转
			if(keywordChain("baidu.com","wd=",refer,req,res))return;
			if(keywordChain("google.com","q=",refer,req,res))return;
			if(keywordChain("sogou.com","query=",refer,req,res))return;
			if(keywordChain("haosou.com","q=",refer,req,res))return;
			if(keywordChain("bing.com","q=",refer,req,res))return;
			// if(keywordChain("localhost","tagName=",refer,req,res))return;
			
		}

		
		if(req.getRequestURL().indexOf("che.webhezi.com") >=0){
			U.forward(req, res, "/che/che.html");
			return;
		}
		
		
//		if(
//				StringUtils.contains(req.getRequestURL(), "hailunhuafei.")
//				&& (!StringUtils.contains(req.getRequestURL(), ".css"))
//				&& (!StringUtils.contains(req.getRequestURL(), ".js"))
//				&& (!StringUtils.contains(req.getRequestURL(), ".jsp"))
//				&& (!StringUtils.contains(req.getRequestURL(), "img"))		
//						 
//				//StringUtils.endsWithIgnoreCase(req.getRequestURL().toString().trim(), "hailunhuafei.webhezi.com")
//			//	||StringUtils.endsWithIgnoreCase(req.getRequestURL().toString().trim(), "hailunhuafei.com")
//				//||StringUtils.endsWithIgnoreCase(req.getRequestURL().toString().trim(), "www.hailunhuafei.com")
//				//&& (!StringUtils.endsWith(req.getRequestURL(), "css"))
//				//&& (!StringUtils.endsWith(req.getRequestURL(), "js"))
//				//&& (!StringUtils.endsWith(req.getRequestURL(), "html"))
//				){//访问根目录，js css不转跳
//			U.forward(req, res, "/hailunhuafei/index.jsp"); 
//			
//			// TiebaRobot tb-bk.txt
//			return;
//		}
		
		//redirect begin
		/*
		if(req.getRequestURL().indexOf("webhezi.com/美剧")>=0
				||req.getRequestURL().indexOf("webhezi.com/u/5486a54cc79d6117fdb02e17")>=0
				||req.getRequestURL().indexOf("webhezi.com/%E7%BE%8E%E5%89%A7")>=0){
			U.forward(req, res, "/g/美剧");
			//res.setStatus(301);   
			//res.setHeader( "Location", "http://www.webhezi.com/g/美剧");   
			//res.setHeader( "Connection", "close" ); 
			return;
		}
		if(req.getRequestURL().indexOf("webhezi.com/电影")>=0
				||req.getRequestURL().indexOf("webhezi.com/u/548da190c79d007151dd722f")>=0
				||req.getRequestURL().indexOf("www.webhezi.com/%E7%94%B5%E5%BD%B1")>=0){
			U.forward(req, res, "/g/电影");
			//res.setStatus(301);   
			//res.setHeader( "Location", "http://www.webhezi.com/g/电影");   
			//res.setHeader( "Connection", "close" ); 
			return;
		}
		*/
		//redirect end
		//StringEscapeUtils.escapee
		//System.out.println("EncodingFilter : default charset is "+Charset.defaultCharset());
		
		//System.out.println("do filter");

		// only userful when it is a post request
		// if it is a get request, the charset is set by
		// container(tomcat,default iso-8859-1)
		req.setCharacterEncoding("UTF-8");
		/*
		Map<String, String[]> map = req.getParameterMap();
		for(String key : map.keySet()){
			String values[] = map.get(key);
			
			for( int i=0 ;i<values.length;i++){
				values[i] = StringUtils.replace(values[i], "(","");
				values[i] = StringUtils.replace(values[i], ")","");
				values[i] = StringUtils.replace(values[i], "<","");
				values[i] = StringUtils.replace(values[i], ">","");
				values[i] = StringUtils.replace(values[i], "'","");
				values[i] = StringUtils.replace(values[i], ";","");
				//values[i] = StringUtils.replace(values[i], "%","");
				//values[i] = StringUtils.replace(values[i], "&","");
			}
		}
		*/
		
		Map<String, String[]> map = req.getParameterMap();
		for(String key : map.keySet()){
			String values[] = map.get(key);
			for( int i=0 ;i<values.length;i++){
				values[i] = StringEscapeUtils.escapeEcmaScript(values[i]);
				values[i] = StringEscapeUtils.escapeJava( values[i]);
				values[i] = StringEscapeUtils.escapeJson(values[i]);
				values[i] = StringEscapeUtils.escapeHtml3(values[i]);
				values[i] = StringEscapeUtils.escapeHtml4(values[i]);
				//StringEscapeUtils.escapesql
			}
		}
		
		//depends on container, sometime not work 
		res.setCharacterEncoding("UTF-8");
		
		//very useful
		//res.setContentType("text/plain; charset=utf-8");
		
		chain.doFilter(req, res);

	}
	
	private boolean keywordChain(String websiteStr, String keywordParam, String refer, HttpServletRequest req, HttpServletResponse res){
	
		if(StringUtils.isBlank(refer))return false;
		if(!StringUtils.contains(refer, websiteStr))return false;
			
		try {
			refer  = URLDecoder.decode(refer.replaceAll("%", "%25"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("CommonFiltr : keywordChain : Decode refer失败" + refer);
			return false;
		}
		if(StringUtils.contains(refer, "?"+keywordParam)){
			keywordParam = "?"+keywordParam;
		}
		else if(StringUtils.contains(refer, "&"+keywordParam)){
			keywordParam = "&"+keywordParam;
		}else{
			return searchEngineChain(refer, req, res);
		}
		int index = refer.indexOf(keywordParam);
		refer = refer.substring(index);
		
		String keyword ="";
		if(StringUtils.contains(refer, "&")){//keyword后边还有别的参数，有&
			keyword = StringUtils.substringBetween(refer, keywordParam,"&");
		}
		else{ //keyword 是最好的参数
			keyword = StringUtils.substringAfterLast(refer, keywordParam);
		}
		 
		if(StringUtils.isBlank(keyword)){
			return searchEngineChain(refer, req, res);
		}
		

		//直搜网站的 不转网盘搜索页面
		if( StringUtils.contains(keyword, "网址盒子"))return false;
		if( StringUtils.contains(keyword, "网盘盒子"))return false;
		if( StringUtils.contains(keyword, "webhezi"))return false;
		
		//滤掉无用keyword
		keyword = StringUtils.replace(keyword, "百度云网盘", "");
		keyword = StringUtils.replace(keyword, "百度网盘", "");
		keyword = StringUtils.replace(keyword, "百度云盘", "");
		keyword = StringUtils.replace(keyword, "百度云", "");
		keyword = StringUtils.replace(keyword, "百度盘", "");
		keyword = StringUtils.replace(keyword, "百度云网盘资源", "");
		keyword = StringUtils.replace(keyword, "百度网盘资源", "");
		keyword = StringUtils.replace(keyword, "百度资源", "");
		keyword = StringUtils.replace(keyword, "百度云资源", "");
		keyword = StringUtils.replace(keyword, "百度盘资源", "");
		keyword = StringUtils.replace(keyword, "百度云盘资源", "");
		
		keyword = StringUtils.replace(keyword, "网盘链接", "");
		keyword = StringUtils.trim(keyword);
		if(StringUtils.isBlank(keyword)){
			return false;
		}
//		try {
//			keyword = URLEncoder.encode(keyword,"UTF-8") ;
//		} catch (UnsupportedEncodingException e) {
//		}
		System.out.println("From "+websiteStr+", keyword = " + keyword);
		U.forward(req, res, "/pan/"+keyword );
		return true;
	}

	private boolean searchEngineChain(String refer,HttpServletRequest req, HttpServletResponse res){
		
		if(StringUtils.isBlank(refer))return false;
		
		if(StringUtils.contains(refer, "baidu.com") ||
				StringUtils.contains(refer, "google.com")||
				StringUtils.contains(refer, "sougou.com")||
				StringUtils.contains(refer, "haosou.com")||
				//StringUtils.contains(refer, "localhost")||
				StringUtils.contains(refer, "bing.com")){
			try {
				//res.sendRedirect("http://www.webhezi.com/pan");
				U.forward(req, res, "/pan");
			} catch ( Exception e) {
				return false;
			}
		
			return true;
		} 
		return false;
	}
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}

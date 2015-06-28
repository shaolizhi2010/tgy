package com.tgy.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.tgy.util.SimpleConnecter;


public class JDRedirectService {
	
	static JDRedirectService s = null;
	String searchHtml = "";
	
	private JDRedirectService(){
		long time = System.currentTimeMillis();
		String jsonp = SimpleConnecter.connect("http://ads.union.jd.com/pre?callback=callback&pid=275603151&cuid=&euid=&cb=jd"+time+"&t="+ (time+new Random().nextInt(20000)) );

		String url = "http://ads.union.jd.com/search" + StringUtils.substringBetween(jsonp, "http://ads.union.jd.com/search","\"");
		  searchHtml = SimpleConnecter.connect(url);
		  
	}
	
	public static JDRedirectService getInstance(){
		if(s == null){
			s = new JDRedirectService();
		}
		return s;
	}
	
	public String redirect(String keyword){
		String redirectUrl = "http://union.click.jd.com/jdc" + StringUtils.substringBetween(searchHtml, "http://union.click.jd.com/jdc","\"");
		try {
			keyword = URLEncoder.encode(keyword,"UTF-8");
		} catch (UnsupportedEncodingException e) {
		}

		redirectUrl = StringUtils.replace(redirectUrl, "#targetUrl", "http%3A%2F%2Fsearch.jd.com%2FSearch%3Fkeyword%3D"+keyword+"%26enc%3Dutf-8");
		
		return redirectUrl;
 
	}
	 
	
	
}

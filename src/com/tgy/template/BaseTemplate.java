package com.tgy.template;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.tgy.App;

public abstract class BaseTemplate {
	
	public String template;
	
	public BaseTemplate(String page){
		init(page);
	}
	
	public void init(String page){
		String path = App.basePath+"template/"+page+".html";
		try {
			template =  FileUtils.readFileToString(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String template( Map<String,String> values){
		
		for(Map.Entry<String, String> e : values.entrySet()){
			replace(e.getKey(),e.getValue()); 
		}
		return template;
		
	}
	
	public void replace(String key,String value){
		template = StringUtils.replace(template, "{{"+key+"}}", value);
	}
	
//	public void replace(String str){
//		template = StringUtils.replace(template, "{{"+str+"}}", str);
//	}
}

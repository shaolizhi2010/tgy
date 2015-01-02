//package com.tgy.template.pages;
//
//import java.util.Map;
//
//import org.apache.commons.lang3.StringUtils;
//
//import com.tgy.template.BaseTemplate;
//
//public class PagesTemplate extends BaseTemplate {
//
//	public PagesTemplate(String page) {
//		super(page);
//	}
//
//	@Override
//	public String template(Map<String, Object> values) {
//		String folderName = (String)values.get("folderName");
//		if(StringUtils.isBlank(folderName)){
//			folderName = "网址";
//		}
//		replace(folderName);
//		
//		String showFolderID = (String)values.get("showFolderID");
//		replace(showFolderID);
//		
//		return null;
//	}
//
//}

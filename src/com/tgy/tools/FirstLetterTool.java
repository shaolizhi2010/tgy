package com.tgy.tools;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.tgy.dao.PageDao;
import com.tgy.entity.Page;
import com.tgy.service.PageService;
import com.tgy.util.ConditionMap;
import com.tgy.util.PageType;

public class FirstLetterTool {

	public static void main(String[] args) {
		
	}
	
	public void convert(){
		System.out.println("start");
		try {
			PageService ps = new PageService();
			PageDao pd = new PageDao();
			
			List<Page> pages = ps.list(new ConditionMap().add("type", PageType.resource), null, 0, 0);
			for(Page p : pages){
				String firstString = p.title;
				firstString =StringUtils.trim(firstString);
				firstString =StringUtils.replace(firstString, "[", "");
				firstString =StringUtils.replace(firstString, "【", "");
				  firstString = StringUtils.substring(firstString, 0,1);
				String firstLetter = PinyinHelper.getShortPinyin(firstString);
				p.firstLetter = firstLetter;
				pd.save(p);
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		System.out.println("end");
	}
	
}

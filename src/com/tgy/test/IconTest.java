package com.tgy.test;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.tgy.asyn.GetPageInfoThread;
import com.tgy.dao.PageDao;
import com.tgy.entity.Page;
import com.tgy.service.WebInfo;
import com.tgy.util.WebInfoUtil;

public class IconTest {

	public static void main(String[] args) {
		
		List<Page> list = new PageDao().find().asList();
		for(Page page : list){
			
			try {
				WebInfo info = new WebInfoUtil().info(page.url,true);
				if(StringUtils.isBlank(info.iconPath)){
					System.out.println("找不到favicon，url ： " + page.url);
				}
				else{
					System.out.println("ok : "+page.url );
					System.out.println("ok icon url : "+info.iconPath );
				}
			} catch (Exception e) {
				continue;
			}
			
			//new WebInfoUtil().checkICON( info.iconPath );
			
		}

	}

}

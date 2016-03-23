package com.tgy.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ADUtils {

	public static final String pic = "pic"; 
	public static final String word = "word";
	public static final String link = "link";
	public static final String tag = "tag";
	public static final String all = "all";
	
	public static final String window = "window"; //橱窗
	
	public static final String defaultADID = "";
	
	
	public static ArrayList<AD> ads = new ArrayList<AD>(){
		{
			add( new AD(960,60,all,"u2188178","主题链接 页面页脚")  ); 
			add( new AD(640,60,all,"u2202318","640*60 创建于 2015-07-11 图片")  );
			add( new AD(468,60,all,"u2188200","主题链接 页面页脚  468*60")  );
			add( new AD(300,60,all,"u2200776","300*60")  );
			
			add( new AD(300,250,all,"u2188174","侧边栏 主题链接 pc")  );
			add( new AD(300,250,all,"u2199079","橱窗")  );
			
		}
	};
	
	
	
	public static String get(int width,int height ){
		
		List<AD> results = new ArrayList<AD>();
		
		for(AD ad : ads){
			if(ad.height == height && ad.width == width){
				results.add(ad);
			}
		}
		
		if(results.size()==0){
			return defaultADID;
		}
		else if(results.size()==1){
			return results.get(0).adID;
		}
		else if(results.size() > 1){
			return results.get( new Random().nextInt(results.size()	) ).adID;
		}
		
		return defaultADID; 
	}
	
}

class AD {
	
	public AD(int width,int height,String type,String adID,String comment){
		
		this.width = width;
		this.height = height;
		this.type = type;
		this.adID = adID;
		this.comment = comment;
	}
	
	public int height;
	public int width;
	public String type;
	public String adID = "";
	public String comment = "";
}

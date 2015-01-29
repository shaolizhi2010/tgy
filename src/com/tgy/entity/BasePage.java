package com.tgy.entity;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;

import com.tgy.statistic.entity.Link;

@Entity
public class BasePage extends StateFulBaseEntity  implements Serializable,Comparable {
	public String name;
	public String description;
	public String comment;
	
	public String pid;//folderID
	public String url;
	
	public int sortOrder;//排序编号，1排在第一位。。。
	
	public Link refLink;
	
	public String iconPath;
	
	
	//public String iconAvailable;//icon 图片是否可用  'true' 'false' or null
	
	 
	//public int scanTimes; //呗后台分析程序扫描的次数，0标识没扫描过
	
	@Override
	public int compareTo(Object o2) {
		
		BasePage p2 = (BasePage)o2;
		
		
		if(this.favScore > p2.favScore){
			return 1;
		}
		
		else if(this.favScore == p2.favScore) {
			return 0;
		}
		else{
			return -1;
		}
		
	}
}

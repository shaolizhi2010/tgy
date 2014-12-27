package com.tgy.entity;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Entity;

import com.tgy.statistic.entity.Link;

@Entity
public class Page extends StateFulBaseEntity  implements Serializable,Comparable {
 
	
	public String name;
	public String description;
	public String comment;
	
	public String pid;
	public String url;
	
	public Link refLink;
	
	public String iconPath;
	//public String iconAvailable;//icon 图片是否可用  'true' 'false' or null
	
	 
	public int scanTimes; //呗后台分析程序扫描的次数，0标识没扫描过
	
	@Override
	public int compareTo(Object o2) {
		
		Page p2 = (Page)o2;
		
		
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

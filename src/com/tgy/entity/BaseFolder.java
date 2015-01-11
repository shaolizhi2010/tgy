package com.tgy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;

@Entity
public class BaseFolder extends StateFulBaseEntity  implements Serializable,Comparable {
 
	public String name;
	public int sortOrder;//排序编号，1排在第一位。。。
	
	@Override
	public boolean equals(Object obj) {

		if (obj != null && obj instanceof BaseFolder) {
			BaseFolder f = (BaseFolder) obj;
			if (StringUtils.equals(String.valueOf(this.id),
					String.valueOf(f.id))
					&& StringUtils.equals(this.name, f.name)
					 ) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int compareTo(Object o2) {
		
		BaseFolder f2 = (BaseFolder)o2;
		
		
		if(this.favScore > f2.favScore){
			return 1;
		}
		
		else if(this.favScore == f2.favScore) {
			return 0;
		}
		else{
			return -1;
		}
		
	}

}

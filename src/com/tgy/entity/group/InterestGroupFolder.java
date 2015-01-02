package com.tgy.entity.group;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import com.tgy.entity.BaseFolder;

@Entity
public class InterestGroupFolder extends BaseFolder  implements Serializable,Comparable {
  
	public String groupID;
//	@Reference(ignoreMissing = true, lazy = true)
//	public InterestGroup group;
	
	@Reference(ignoreMissing = true, lazy = true)
	public List<InterestGroupPage> pages; // 文件夹包含的页面

	public void add(InterestGroupPage page) {
		if (pages == null) {
			pages = new ArrayList<>();
		}
		pages.add(page);
	}
	
	public void remove(InterestGroupPage page){
		if (pages != null) {
			for(int i=0;i<pages.size();i++){
				if(pages.get(i).id!=null &&
						page.id!= null &&
						StringUtils.equals(pages.get(i).id.toString(), page.id.toString())   ){
					pages.remove(i);
				}
			}
		}
	}
}

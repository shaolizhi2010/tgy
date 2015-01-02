package com.tgy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity
public class Folder extends BaseFolder  implements Serializable,Comparable {
	
	@Reference(ignoreMissing = true, lazy = true)
	public List<Page> pages; // 文件夹包含的页面

	public void add(Page page) {
		if (pages == null) {
			pages = new ArrayList<>();
		}
		pages.add(page);
	}
	
	public void remove(Page page){
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

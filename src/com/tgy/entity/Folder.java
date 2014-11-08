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
public class Folder implements Serializable {
	@Id
	public ObjectId id;
	public String name ;
	public String userID;
	
	public String pid; // 父文件夹的id //TODO ref Folder pFolder

	public boolean isDefault;
	public boolean isRoot; // 是否是根收藏夹，即页面左侧边栏里显示的收藏夹

	public String createDate;

	@Reference(ignoreMissing = true)
	public List<Folder> folders;// 所有子文件夹

	@Reference(ignoreMissing = true)
	public List<Page> pages; // 文件夹包含的页面

	public String color;
	
	// stastics
	public int scanTimes; // 呗后台分析程序扫描的次数，0标识没扫描过

	public void add(Folder folder) {
		if (folders == null) {
			folders = new ArrayList<>();
		}
		folders.add(folder);
	}

	public void add(Page page) {
		if (pages == null) {
			pages = new ArrayList<>();
		}
		pages.add(page);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj != null && obj instanceof Folder) {
			Folder f = (Folder) obj;
			if (StringUtils.equals(String.valueOf(this.id),
					String.valueOf(f.id))
					&& StringUtils.equals(this.name, f.name)
					&& StringUtils.equals(this.pid, f.pid)) {
				return true;
			}
		}
		return false;
	}

}

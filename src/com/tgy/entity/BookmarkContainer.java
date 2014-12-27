//package com.tgy.entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.lang3.StringUtils;
//import org.mongodb.morphia.annotations.Reference;
//
///**
// * 能够保存书签的实体，比如用户，用户组，收藏夹，等
// * @author qq
// *
// */
//public class BookmarkContainer extends StateFulBaseEntity {
//	
//	@Reference(ignoreMissing = true, lazy = true)
//	public List<Folder> folders;// 所有子文件夹
//
//	@Reference(ignoreMissing = true, lazy = true)
//	public List<Page> pages; // 文件夹包含的页面
//	
//	public void add(Folder folder) {
//		if (folders == null) {
//			folders = new ArrayList<>();
//		}
//		folders.add(folder);
//	}
//
//	public void remove(Folder folder){
//		if (folders != null) {
//			folders.remove(folder);
//		}
//	}
//	
//	public void add(Page page) {
//		if (pages == null) {
//			pages = new ArrayList<>();
//		}
//		pages.add(page);
//	}
//	
//	public void remove(Page page){
//		if (pages != null) {
//			for(int i=0;i<pages.size();i++){
//				if(pages.get(i).id!=null &&
//						page.id!= null &&
//						StringUtils.equals(pages.get(i).id.toString(), page.id.toString())   ){
//					pages.remove(i);
//				}
//			}
//		}
//	}
// 
//}

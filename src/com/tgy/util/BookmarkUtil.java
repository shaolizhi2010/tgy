package com.tgy.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tgy.entity.Bookmark;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.service.BookmarkService;

public class BookmarkUtil {
	
	static BookmarkService bookmarkService = new BookmarkService();
	
	public static void setCurrentBookmarkForRequest( Bookmark curBookmark,
			HttpServletRequest req) {

		if (curBookmark != null) {
			req.setAttribute("curBookmark", curBookmark);
			req.setAttribute("folders", curBookmark.folders);
			req.setAttribute("pages", curBookmark.pages);
		}
	}
	


	/**
	 * 取出来默认书签
	 * 
	 * @param bookmarks
	 * @return
	 */
	public static Bookmark getDefaultBookmark(List<Bookmark> bookmarks) {

		if (bookmarks == null || bookmarks.size() == 0) {
			return null;
		}

		// 只有一个书签，直接返回
		if (bookmarks.size() == 1) {
			return bookmarks.get(0);
		}

		for (Bookmark bookmark : bookmarks) {

			if (bookmark != null && bookmark.isDefault) {
				return bookmark;
			}
		}

		// 没有默认书签，取第一个作为默认书签
		return bookmarks.get(0);

	}

	public static Bookmark getCurrentBookmark(List<Bookmark> bookmarks,
			String bid) {

		if (bookmarks == null || bookmarks.size() == 0) {
			return null;
		}

		for (Bookmark bookmark : bookmarks) {

			if (bookmark != null && bookmark.id.toString().equals(bid)) {
				return bookmark;
			}
		}

		// 没有默认书签，取第一个作为默认书签
		return null;

	}

	public static List<Bookmark> defaultBookmarks() {
		Bookmark bookmark1 = new Bookmark();
		bookmark1.name = "糖果云的书签";
		bookmark1.folders = new ArrayList<Folder>();
		bookmark1.folders.add(buildFolderSearch());// Search
		bookmark1.folders.add(buildFolderWanggou());// Search

		bookmark1.pages = buildDefaultPages();

		bookmark1.isDefault = true;

		Bookmark bookmark2 = new Bookmark();
		bookmark2.name = "qq书签";
		bookmark2.folders = buildDefaultFolders();
		bookmark2.pages = buildDefaultPages();

		List<Bookmark> bookmarks = new ArrayList<Bookmark>();
		bookmarks.add(bookmark1);
		bookmarks.add(bookmark2);
		return bookmarks;
	}

	public static List<Folder> buildDefaultFolders() {
		Folder folder1 = new Folder();
		folder1.name = "folder1";
		folder1.pages = buildDefaultPages();

		Folder folder2 = new Folder();
		folder2.pages = buildDefaultPages();
		folder2.name = "folder2";

		List<Folder> folders = new ArrayList<>();
		folders.add(folder1);
		folders.add(folder2);

		return folders;
	}

	public static List<Page> buildDefaultPages() {

		List<Page> pages = new ArrayList<>();
		pages.add(buildPage("百度", "www.baidu.com"));
		pages.add(buildPage("51CTO", "51cto.com/"));
		pages.add(buildPage("Google", "google.com"));
		pages.add(buildPage("派代网", "www.paidai.com/"));
		pages.add(buildPage("阿里云备案", "http://aliyun.gein.cn/"));

		return pages;
	}

	// 搜索 文件夹
	public static Folder buildFolderSearch() {

		Folder folder = new Folder();

		folder.name = "搜索";
		folder.pages = new ArrayList<Page>();

		folder.pages.add(buildPage("百度", "baidu.com/"));
		folder.pages.add(buildPage("Google", "google.com.hk/"));
		folder.pages.add(buildPage("360搜索", "so.com/"));
		folder.pages.add(buildPage("搜搜", "baidu.com/"));
		folder.pages.add(buildPage("必应", "bing.com/"));
		folder.pages.add(buildPage("Yandex", "yandex.com/"));

		return folder;

	}

	// 网购 文件夹
	public static Folder buildFolderWanggou() {

		Folder folder = new Folder();

		folder.name = "网购";
		folder.pages = new ArrayList<Page>();

		folder.pages.add(buildPage("淘宝", "taobao.com/"));
		folder.pages.add(buildPage("天猫", "tmall.com/"));
		folder.pages.add(buildPage("京东", "jd.com/"));
		folder.pages.add(buildPage("亚马逊", "z.cn/"));
		folder.pages.add(buildPage("当当", "dangdang.com/"));

		return folder;

	}

	public static Page buildPage(String name, String link) {
		Page page = new Page();
		page.name = name;
		page.link = link;
		return page;
	}
}

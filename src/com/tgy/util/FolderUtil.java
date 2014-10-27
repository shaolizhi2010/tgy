package com.tgy.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;

import com.tgy.entity.Folder;
import com.tgy.entity.Page;

public class FolderUtil {

	public static void setFolderForSession(Folder curFolder,
			HttpServletRequest req) {

		if (curFolder != null) {
			req.getSession().setAttribute("curFolder", curFolder);
			req.getSession().setAttribute("folders", curFolder.folders);
			req.getSession().setAttribute("pages", curFolder.pages);
		}
	}

	/**
	 * 取出来默认Folder
	 * 
	 * @param folders
	 * @return
	 */
	public static Folder getDefaultFolder(List<Folder> folders) {

		if (CollectionUtils.isEmpty(folders)) {
			return null;
		}

		// 只有一个收藏夹，直接返回
		if (folders.size() == 1) {
			return folders.get(0);
		}

		for (Folder folder : folders) {

			if (folder != null && folder.isDefault) {
				return folder;
			}
		}

		// 没有默认收藏夹，取第一个作为默认收藏夹
		return folders.get(0);

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
		page.url = link;
		return page;
	}
}

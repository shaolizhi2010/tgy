package com.tgy.statistic.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.query.Query;

import com.tgy.App;
import com.tgy.dao.FolderDao;
import com.tgy.dao.LinkDao;
import com.tgy.dao.PageDao;
import com.tgy.dao.TagDao;
import com.tgy.entity.Folder;
import com.tgy.entity.Page;
import com.tgy.statistic.entity.Link;
import com.tgy.statistic.entity.Tag;

public class LinkService {

	public void copy(Link link) {

	}

	public List<Link> searchByTagName(String tagName) {

		Tag tag = new TagDao().getByName(tagName);
		if (tag == null || tag.links == null) {
			return new ArrayList<>();
		}

		// LinkDao lDao = new LinkDao();

		// sort TODO may cause performance issue
		Collections.sort(tag.links);
		Collections.reverse(tag.links);
		if (tag.links.size() > 20) {
			tag.links.subList(0, 20);
		}
		return tag.links;

	}

	/**
	 * 扫描page表所有的网页，生成对应的信息。
	 */
	public void scan() {

		PageDao pDao = new PageDao();
		FolderDao fDao = new FolderDao();
		LinkDao lDao = new LinkDao();
		TagDao tDao = new TagDao();
		
		// TODO 分组 -> mapreduce
		//只统计处理新增数据，TODO 更改或删除也应该纳入处理
		
		// 找到从来没处理过的
		Query<Page> query = App.getInstance().getDatastore()
				.createQuery(Page.class).filter("scanTimes", 0);
		Iterator<Page> it = pDao.find(query).iterator();
		
		while (it.hasNext()) {
			Page page = it.next();
			page.scanTimes++;
			pDao.save(page);
			
			// save link
			boolean newLinkFlag = false;
			Link link = lDao.getByUrl(page.url);
			if (link == null) { // first create
				newLinkFlag = true;
				link = new Link();
				link.url = StringUtils.trim(page.url);
				link.title = page.name;// TODO title should be html title.
				link.createDate = page.createDate;
				link.clicks = 0;
				link.keeps = 1;
				link.favScore = Link.keepScore;
			} else {
				link.keeps++;
				link.favScore += Link.keepScore;
			}

			// pre get tag

			// get folder name
			Folder folder = fDao.getByID(page.pid);
			Tag tag = null;
			if (folder != null) {
				// get tab by folder name
				tag = tDao.getByName(folder.name);

				if (tag != null) {
					if (link.tags == null || !link.tags.contains(tag)) {
						// add tag into link
						link.add(tag);
					}
				}
			}
			// save link
			lDao.save(link);
			// add link into tag and save tag
			if (tag != null && newLinkFlag) {// 只有是新的link才add到Tag里，避免重复
				tag.add(link);
				tDao.save(tag);
			}
		}

	}
}

package com.tgy.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mongodb.DBObject;
import com.tgy.dao.LinkDao;
import com.tgy.dao.TagDao;
import com.tgy.entity.Link;
import com.tgy.entity.Tag;

public class LinkService {
	
	public void copy(Link link){
		
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
		if(tag.links.size()>20){
			tag.links.subList(0, 20);
		}
		return tag.links;

	}
	/*
	 * public List<Link> searchByName(String name){
	 * 
	 * LinkDao tDao = new LinkDao(); LinkDao lDao = new LinkDao();
	 * 
	 * Link link = tDao.getByName(name); if(link==null){ return new
	 * ArrayList<>(); } return link.links;
	 * 
	 * }
	 */
}

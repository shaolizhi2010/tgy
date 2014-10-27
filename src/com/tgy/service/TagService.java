package com.tgy.service;

import com.tgy.dao.TagDao;
import com.tgy.entity.Tag;

public class TagService {

	public Tag searchByName(String name) {

		TagDao tDao = new TagDao();

		Tag tag = tDao.getByName(name);
		return tag;

	}
	/*
	 * public List<Link> searchByName(String name){
	 * 
	 * TagDao tDao = new TagDao(); LinkDao lDao = new LinkDao();
	 * 
	 * Tag tag = tDao.getByName(name); if(tag==null){ return new ArrayList<>();
	 * } return tag.links;
	 * 
	 * }
	 */
}

package com.tgy.dao;

import org.bson.types.ObjectId;

import com.tgy.App;
import com.tgy.entity.Article;

public class ArticleDao extends BaseBasicDAO<Article, ObjectId> {

	public ArticleDao() {
		super(Article.class, App.getInstance().getDatastore());
	}

}

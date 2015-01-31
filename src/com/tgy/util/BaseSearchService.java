package com.tgy.util;

import java.util.List;

import com.tgy.entity.Page;

public abstract class BaseSearchService {
	public abstract List<Page> search(String keyword,int start) ;
}

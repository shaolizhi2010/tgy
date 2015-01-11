package com.tgy.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.tgy.entity.BasePage;
import com.tgy.entity.Page;

public class PageUtil {

	public static void sortByOrderIndex(List<Page> list){
		Collections.sort(list, new Comparator<Page>( ) {

			@Override
			public int compare(Page o1, Page o2) {
				if(o1.sortOrder > o2.sortOrder){
					return 1;
				}
				else if(o1.sortOrder < o2.sortOrder){
					return -1;
				}
				else {
					return 0;
				}				
			}
			
		});
	}
	 
}

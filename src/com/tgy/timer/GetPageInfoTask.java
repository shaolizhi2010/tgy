package com.tgy.timer;

import java.util.List;

import com.tgy.asyn.GetPageInfoThread;
import com.tgy.dao.PageDao;
import com.tgy.entity.Page;

public class GetPageInfoTask extends java.util.TimerTask{

	@Override
	public void run() {
		List<Page> list = new PageDao().find().asList();
		for(Page page : list){
			
			new Thread(new GetPageInfoThread(page.url, page.id.toString())).start();
			try {
				new Thread().sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		
	}

}

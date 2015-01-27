package com.tgy.timer;

import com.tgy.service.article.HaoSouWemediaService;

public class DigArticleTask extends java.util.TimerTask {

	@Override
	public void run() {
		new HaoSouWemediaService().digAndSave();
	}

}

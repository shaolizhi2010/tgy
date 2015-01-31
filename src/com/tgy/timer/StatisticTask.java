package com.tgy.timer;

import com.tgy.statistic.service.LinkInfoService;
import com.tgy.statistic.service.LinkService;
import com.tgy.statistic.service.RefreshIconPathService;
import com.tgy.statistic.service.TagService;

public class StatisticTask extends java.util.TimerTask {

	@Override
	public void run() {
		//new TagService().scan();
		//new LinkService().scan();
		//new LinkInfoService().scan();
		//new RefreshIconPathService().scan();
	}

}

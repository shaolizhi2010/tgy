package com.tgy.timer;

import com.tgy.service.ziyuan.BaiduPanDigService;

public class DigBaiduYunTask extends java.util.TimerTask {

	@Override
	public void run() {
		new BaiduPanDigService().digAndSave();
	}

}

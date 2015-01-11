package com.tgy.timer;

import com.tgy.tools.CreateRobotUserTool;

public class CreateRobotUserTask extends java.util.TimerTask {

	@Override
	public void run() {
		 new CreateRobotUserTool().createRobotUser(5);
	}

}

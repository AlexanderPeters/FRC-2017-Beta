package controllers;

import lib.Loop;
import main.Constants;
import main.Robot;

public class StateCheckController implements Loop, Constants {
	
	@Override
	public void onStart() {
		//no-op
	}

	@Override
	public void onLoop() {
		System.out.println("here at statecheck");
		Robot.sensors.check();
	}

	@Override
	public void onStop() {
		//no-op
	}
}

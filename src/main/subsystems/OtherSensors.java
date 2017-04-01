package main.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;
import main.commands.driveAlerts.AlertDriver;

public class OtherSensors extends Subsystem implements Constants, HardwareAdapter {
	private boolean gearSwitchLastState;
	private boolean gearSwitchCurrentState;
	public OtherSensors() {
		gearSwitchLastState = gearSwitch.get();
		check();
		
	}
	private void check() {
		gearSwitchCurrentState = gearSwitch.get();
		
		if(gearSwitchCurrentState != gearSwitchLastState) new AlertDriver();
			
		gearSwitchLastState = gearSwitchCurrentState;
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}

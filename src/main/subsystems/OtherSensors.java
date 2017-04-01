package main.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import main.Constants;
import main.HardwareAdapter;
import main.commands.driveAlerts.AlertDriver;

public class OtherSensors extends Subsystem implements Constants, HardwareAdapter {
	private boolean gearSwitchLastState;
	private boolean gearSwitchCurrentState;
	private InternalButton alertDriverButton = new InternalButton();
	
	public OtherSensors() {
		gearSwitchLastState = gearSwitch.get();
		check();
		
	}
	private void check() {
		gearSwitchCurrentState = gearSwitch.get();
		
		alertDriverButton.setPressed(gearSwitchCurrentState != gearSwitchLastState);
		alertDriverButton.whenPressed(new AlertDriver());
			
		gearSwitchLastState = gearSwitchCurrentState;
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}

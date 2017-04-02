package main.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import main.Constants;
import main.HardwareAdapter;
import main.commands.driveAlerts.AlertDriver;
import main.commands.intake.IntakeForTime;

public class OtherSensors extends Subsystem implements Constants, HardwareAdapter {
	private boolean gearSwitchLastState;
	private boolean gearSwitchCurrentState;
	private InternalButton alertDriverButton = new InternalButton();
	private InternalButton runIntakeButton = new InternalButton();
	
	public OtherSensors() {
		gearSwitchLastState = gearSwitch.get();
		check();
		
	}
	
	private void check() {
		gearSwitchCheck();
		intakeSwitchCheck();
	}
	
	private void gearSwitchCheck() {
		gearSwitchCurrentState = gearSwitch.get();
		alertDriverButton.setPressed(gearSwitchCurrentState != gearSwitchLastState);
		alertDriverButton.whenPressed(new AlertDriver());
		gearSwitchLastState = gearSwitchCurrentState;
	}
	
	private void intakeSwitchCheck() {
		runIntakeButton.setPressed(intakeSwitch.get());
		runIntakeButton.whenPressed(new IntakeForTime(intakeOnTime));
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}

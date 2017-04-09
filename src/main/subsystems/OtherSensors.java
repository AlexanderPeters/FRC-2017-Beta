package main.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import main.Constants;
import main.HardwareAdapter;
import main.commands.driveAlerts.AlertDriver;
import main.commands.intake.IntakeForTime;
import main.commands.shootDoor.CloseShootDoor;
import main.commands.shootDoor.OpenShootDoor;

public class OtherSensors extends Subsystem implements Constants, HardwareAdapter {
	private boolean gearSwitchLastState;
	private boolean gearSwitchCurrentState;
	private InternalButton alertDriverButton = new InternalButton();
	private InternalButton runIntakeButton = new InternalButton();
	private InternalButton openShootDoorButton = new InternalButton();
	
	public OtherSensors() {
		gearSwitchLastState = gearSwitch.get();
	}
	
	public void check() {
		gearSwitchCheck();
		intakeSwitchCheck();
		shootProxSwitchCheck();
	}
	
	private void gearSwitchCheck() {
		gearSwitchCurrentState = gearSwitch.get();
		//System.out.println(gearSwitchCurrentState);
		alertDriverButton.setPressed(gearSwitchCurrentState != gearSwitchLastState);
		alertDriverButton.whenPressed(new AlertDriver());
		gearSwitchLastState = gearSwitchCurrentState;
	}
	
	private void intakeSwitchCheck() {
		runIntakeButton.setPressed(intakeSwitch.get());
		runIntakeButton.whenPressed(new IntakeForTime(intakeOnTime));
	}
	
	private void shootProxSwitchCheck() {
		openShootDoorButton.setPressed(shootProxSwitch.get());
		openShootDoorButton.whenPressed(new OpenShootDoor());
		openShootDoorButton.whenReleased(new CloseShootDoor());
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}

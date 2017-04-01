package main.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import main.Constants;
import main.HardwareAdapter;

public class DriverAlerts extends Subsystem implements Constants, HardwareAdapter {
	public DriverAlerts() {
		
	}
	
	public void setAlertLightState(boolean state) {
		alertRelay.set(state);
	}
	
	public boolean getAlertLightState() {
		return alertRelay.get();
	}
	
	public void pulseAlertLight(int pulseLength) {
		alertRelay.pulse(pulseLength);
	}
	
	public boolean isPulsing() {
		return alertRelay.isPulsing();
	}

	@Override
	protected void initDefaultCommand() {
		
	}

}

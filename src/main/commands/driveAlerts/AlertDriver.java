package main.commands.driveAlerts;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AlertDriver extends CommandGroup {
	public AlertDriver() {//Utilize pulseAlertLight method to simplify this.
		addSequential(new AlertLightOn());
		addSequential(new WaitCommand(0.15));
		addSequential(new AlertLightOff());
		addSequential(new AlertLightOn());
		addSequential(new WaitCommand(0.15));
		addSequential(new AlertLightOff());
		addSequential(new AlertLightOn());
		addSequential(new WaitCommand(0.15));
		addSequential(new AlertLightOff());
		addSequential(new AlertLightOn());
		addSequential(new WaitCommand(0.15));
		addSequential(new AlertLightOff());
		addSequential(new AlertLightOn());
		addSequential(new WaitCommand(0.15));
		addSequential(new AlertLightOff());
		addSequential(new AlertLightOn());
		addSequential(new WaitCommand(0.15));
		addSequential(new AlertLightOff());
		addSequential(new AlertLightOn());
		addSequential(new WaitCommand(0.15));
		addSequential(new AlertLightOff());
	}

}

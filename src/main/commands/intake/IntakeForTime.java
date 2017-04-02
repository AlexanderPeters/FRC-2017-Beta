package main.commands.intake;

import edu.wpi.first.wpilibj.command.TimedCommand;
import main.Constants;
import main.Robot;

public class IntakeForTime extends TimedCommand implements Constants {
	
	public IntakeForTime(double time) {
    	super(time);
     	requires(Robot.in);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }           

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.in.spin(stirrerMotorForward);
    }
    // Make this return true when this Command no longer needs to run execute()

    // Called once after isFinished returns true
    protected void end() {
    	Robot.in.spin(0.0);//Turn stirrer off after time is up
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
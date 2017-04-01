package main.commands.hood;

import edu.wpi.first.wpilibj.command.Command;
import main.Robot;

/**
 *
 */
public class AngleHood extends Command {
	int angle;
    public AngleHood(int angle) {
    	requires(Robot.hd);
    	this.angle = angle;
    	angle = (angle>120 ? 120:angle);
    	angle = (angle<0 ? 0:angle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.hd.angle = (Robot.hd.angle+angle);
    	System.out.println("Hood Angle" + Robot.hd.angle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

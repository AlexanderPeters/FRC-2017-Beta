package main.commands.drivetrain;

import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.Robot;

public class TimedDriveDistance extends TimedCommand {

	private double distance;
	private double tolerance;
	private double KP, KI, KD, maxV;
	private boolean tuning;
	
	//@param distance: the desired distance to go travel (+ or - (forward, backward; respectively)), tolerance: the absolute difference allowable 
	public TimedDriveDistance(double distance, double tolerance, double maxV, double KP, double KI, double KD, boolean tuning, double time) {
    	super(time);
		requires(Robot.dt);
    	this.distance = distance;
    	this.tolerance = tolerance;
    	this.maxV = maxV;
    	this.KP = KP;
    	this.KI = KI;
    	this.KD = KD;
    	this.tuning = tuning;
	}
	@SuppressWarnings("deprecation")
	public TimedDriveDistance(double distance, double tolerance, double maxV, boolean tuning, double time) {//feet, feet
    	super(time);
    	requires(Robot.dt);
    	this.distance = -distance;
    	this.tolerance = tolerance;
    	this.maxV = maxV;
    	this.tuning = tuning;
    	this.KP = SmartDashboard.getDouble("Distance KP", 0.0);
    	this.KI = SmartDashboard.getDouble("Distance KI", 0.0);
    	this.KD = SmartDashboard.getDouble("Distance KD", 0.0);

    }
    @SuppressWarnings("deprecation")
   	public TimedDriveDistance(double distance, double tolerance, boolean tuning, double time) {//feet, feet
    	super(time);
       	requires(Robot.dt);
       	this.distance = -distance;
       	this.tolerance = tolerance;
    	this.tuning = tuning;
       	this.KP = SmartDashboard.getDouble("Distance KP", 0.0);
       	this.KI = SmartDashboard.getDouble("Distance KI", 0.0);
       	this.KD = SmartDashboard.getDouble("Distance KD", 0.0);
       	this.maxV = SmartDashboard.getDouble("Distance MaxVoltage", 0.0);

    }
    @SuppressWarnings("deprecation")
	public TimedDriveDistance(double distance, boolean tuning, double time) {//feet
    	super(time);
    	requires(Robot.dt);
    	this.distance = -distance;
    	this.tuning = tuning;
    	this.KP = SmartDashboard.getDouble("Distance KP", 0.0);
    	this.KI = SmartDashboard.getDouble("Distance KI", 0.0);
    	this.KD = SmartDashboard.getDouble("Distance KD", 0.0);
    	this.tolerance = SmartDashboard.getDouble("Distance Tolerance", 0.0);
    	this.maxV = SmartDashboard.getDouble("Distance MaxVoltage", 0.0);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.dt.driveDistanceSetPID(KP, KI, KD, maxV, tuning);
    	Robot.dt.resetSensors();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.dt.driveDistance(distance, tolerance, tuning);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		return Math.abs(distance - Robot.dt.getDistanceTraveledRight()) <= tolerance; 
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}

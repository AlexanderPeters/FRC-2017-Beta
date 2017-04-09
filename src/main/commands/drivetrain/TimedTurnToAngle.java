package main.commands.drivetrain;

import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import main.Constants;
import main.Robot;

public class TimedTurnToAngle extends TimedCommand implements Constants {
	private boolean bigAngle;
	private double heading;
	private double tolerance;
	private int switchAngle;
	private double KP, KI, KD, maxV;
	private boolean tuning = false;
	
	//TODO-Fix @param headers
	//@param heading: the desired angle to go to (+ or - (right turn, left turn; respectively)), tolerance: the absolute difference allowable 
	
	@SuppressWarnings("deprecation")
	public TimedTurnToAngle(double heading, double tolerance, double maxV, double KP, double KI, double KD, boolean tuning, double time) {
    	super(time);
    	requires(Robot.dt);
    	this.heading = heading;
    	this.tolerance = tolerance;
    	this.maxV = maxV;
    	this.KP = KP;
    	this.KI = KI;
    	this.KD = KD;
    	this.tuning = tuning;
    	this.switchAngle = SmartDashboard.getInt("Turn In Place Controller Switch Angle", 45);
    	bigAngle = (Math.abs(heading) > switchAngle ? true:false);
	}
	@SuppressWarnings("deprecation")
	public TimedTurnToAngle(double heading, double tolerance, double maxV, boolean tuning, double time) {
    	super(time);
    	requires(Robot.dt);
    	this.heading = heading;
    	this.tolerance = tolerance;
    	this.maxV = maxV;
    	this.tuning = tuning;
    	this.switchAngle = SmartDashboard.getInt("Turn In Place Controller Switch Angle", 45);
    	bigAngle = (Math.abs(heading) > switchAngle ? true:false);
    	
    	this.KP = SmartDashboard.getDouble((bigAngle?"Turning KP Big Angle":"Turning KP Small Angle"), 0.0);
    	this.KI = SmartDashboard.getDouble((bigAngle?"Turning KI Big Angle":"Turning KI Small Angle"), 0.0);
    	this.KD = SmartDashboard.getDouble((bigAngle?"Turning KD Big Angle":"Turning KD Small Angle"), 0.0);
    	
    }
    @SuppressWarnings("deprecation")
	public TimedTurnToAngle(double heading, double tolerance, boolean tuning, double time) {
    	super(time);
    	requires(Robot.dt);
    	this.heading = heading;
    	this.tolerance = tolerance;
    	this.tuning = tuning;
    	this.switchAngle = SmartDashboard.getInt("Turn In Place Controller Switch Angle", 45);
    	bigAngle = (Math.abs(heading) > switchAngle ? true:false);
    	
    	this.KP = SmartDashboard.getDouble((bigAngle?"Turning KP Big Angle":"Turning KP Small Angle"), 0.0);
    	this.KI = SmartDashboard.getDouble((bigAngle?"Turning KI Big Angle":"Turning KI Small Angle"), 0.0);
    	this.KD = SmartDashboard.getDouble((bigAngle?"Turning KD Big Angle":"Turning KD Small Angle"), 0.0);
    	this.maxV = SmartDashboard.getDouble((bigAngle?"Turning MaxVoltage Big Angle":"Turning MaxVoltage Small Angle"), 0.0);
    }
    
    @SuppressWarnings("deprecation")
	public TimedTurnToAngle(double heading, boolean tuning, double time) {
    	super(time);
    	requires(Robot.dt);
    	this.heading = heading;
    	this.tuning = tuning;
    	this.switchAngle = SmartDashboard.getInt("Turn In Place Controller Switch Angle", 45);
    	bigAngle = (Math.abs(heading) > switchAngle ? true:false);
    	
    	this.KP = SmartDashboard.getDouble((bigAngle?"Turning KP Big Angle":"Turning KP Small Angle"), 0.0);
    	this.KI = SmartDashboard.getDouble((bigAngle?"Turning KI Big Angle":"Turning KI Small Angle"), 0.0);
    	this.KD = SmartDashboard.getDouble((bigAngle?"Turning KD Big Angle":"Turning KD Small Angle"), 0.0);
    	this.maxV = SmartDashboard.getDouble((bigAngle?"Turning MaxVoltage Big Angle":"Turning MaxVoltage Small Angle"), 0.0);
    	this.tolerance = SmartDashboard.getDouble("Turning Tolerance", 0.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(bigAngle)
    		Robot.dt.turnToBigAngleSetPID(KP, KI, KD, maxV, tuning);
    	else
    		Robot.dt.turnToSmallAngleSetPID(KP, KI, KD, maxV, tuning);
    	Robot.dt.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//this.heading = Robot.comms.getBearing();// (int) Robot.comms.getBearing();
    	//System.out.println("hehe" + heading);

    	if(bigAngle)
    		Robot.dt.turnToBigAngle(heading, tolerance, tuning);
    	else
    		Robot.dt.turnToSmallAngle(heading, tolerance, tuning);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Math.abs(heading - Robot.dt.getGyro().getYaw()) <= tolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

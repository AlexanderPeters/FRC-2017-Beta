package main.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import main.Constants;
import main.Robot;

public class IntakeForward extends Command implements Constants{

	public IntakeForward() {
        requires(Robot.in);
    }
	
	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		if(Robot.robotState != Robot.RobotState.Climbing)
			Robot.in.spin(intakeMotorForward);
		else
			Robot.in.spin(0);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
	
}
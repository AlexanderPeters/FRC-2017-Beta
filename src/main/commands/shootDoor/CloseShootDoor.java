package main.commands.shootDoor;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;

/**
 *
 */
public class CloseShootDoor extends CommandGroup implements Constants{
    
    public  CloseShootDoor() {
    	addSequential(new ShiftShootDoor(RET));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new ShiftShootDoor(OFF));
    }
}

package main.commands.shootDoor;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;

/**
 *
 */
public class OpenShootDoor extends CommandGroup implements Constants{
    
    public  OpenShootDoor() {
    	addSequential(new ShiftShootDoor(EXT));
    	addSequential(new WaitCommand(0.5));
    	addSequential(new ShiftShootDoor(OFF));
    }
}

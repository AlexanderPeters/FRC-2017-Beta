package main.commands.drivetrain;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import main.Constants;
import main.Robot;

public class TuneDriveTrain extends CommandGroup implements Constants {
	private double timeToTestMinVoltage = 2.0;
	private double timeToTestKP = 3.0;
	private double smallKP, bigKP, distanceKP;
	private double minVoltageTurn;

	public TuneDriveTrain() {
		System.out.println("AutoTune Drivetrain command starting.");

		System.out.println("Tuning process start.");
		System.out.println("Beginning test for minimum turning voltage.");
		for (double minV = 0; minV <= 12; minV += 0.1) {
			addParallel(new TimedDrive(0.0, minV / 12, timeToTestMinVoltage));
			addSequential(new WaitCommand(timeToTestMinVoltage + 0.1));

			// Gets the rate of change from the last run command
			if (Robot.dt.getAngularRateOfChange() >= 1) {// {//Degrees/s
				minVoltageTurn = minV - 1;// Set the minimum voltage to something lower than that required to move but close to it
				minV = 13;
			} // Set minV to a value that will break out of the for loop

		}

		System.out.println("Minimum Voltage Found: " + minVoltageTurn);	

		// Tune each kp to a stable oscilation here
		System.out.println("Beginning test to find KP's with stable oscillations.");

		System.out.println("Now starting identification of a stable KP for small turns.");
		for (double g = 0; g <= 5; g += 0.1) {
			addParallel(new TimedTurnToAngle(40, kToleranceDegreesDefault, 12.0, g, 0.0, 0.0, true, timeToTestKP));
			addSequential(new WaitCommand(timeToTestKP + 0.1));

			if (Robot.dt.getSmallAngleIsStable()) {// Is it stable
				smallKP = g;// Set the smallKP to the appropriate value
				g = 6;// Set the value of the for loop equal to a value that will break out of it

			}
		}

		System.out.println("Found stable small angle oscillation at KP = " + smallKP);

		System.out.println("Now starting identification of a stable KP for big turns.");
		for (double h = 0; h <= 5; h += 0.1) {
			addParallel(new TimedTurnToAngle(90, kToleranceDegreesDefault, 12.0, h, 0.0, 0.0, true, timeToTestKP));
			addSequential(new WaitCommand(timeToTestKP + 0.1));

			if (Robot.dt.getSmallAngleIsStable()) {// Is it stable
				bigKP = h;// Set the bigKP to the appropriate value
				h = 6;// Set the value of the for loop equal to a value that will break out of it
			}

		}
		System.out.println("Found stable big angle oscillation at KP = " + bigKP);

		System.out.println("Now starting identification of a stable KP for distance.");
		for (double i = 0; i <= 5; i += 0.1) {
			addParallel(
					new TimedDriveDistance(3, kToleranceDisplacementDefault, 12.0, i, 0.0, 0.0, true, timeToTestKP));
			addSequential(new WaitCommand(timeToTestKP + 0.1));

			if (Robot.dt.getSmallAngleIsStable()) {// Is it stable
				distanceKP = i;// Set the distanceKP to the appropriate value
				i = 6;// Set the value of the for loop equal to a value that will break out of it
			}

		}
		System.out.println("Found stable distance oscillation at KP = " + distanceKP);

		System.out.println("Now starting the autotune procedure.");
		for (int j = 0; j < 3; j++) {
			Robot.dt.resetSensors();

			System.out.println("Now tuning for small angles.");
			if (j == 0) {
				Robot.dt.turnToSmallAngleSetPIDMinVoltage(minVoltageTurn);
				addSequential(new TurnToAngle(40, kToleranceDegreesDefault, 12.0, smallKP, 0.0, 0.0, true));
				System.out.println(
						"FINALLY FOUND BEST PARAMETERS FOR SMALL ANGLE: kp=" + Robot.dt.getTunedSmallAngleKP() + "; ki="
								+ Robot.dt.getTunedSmallAngleKI() + "; kd=" + Robot.dt.getTunedSmallAngleKD());
			}

			System.out.println("Now tuning for big angles.");
			if (j == 1) {
				Robot.dt.turnToSmallAngleSetPIDMinVoltage(minVoltageTurn);
				addSequential(new TurnToAngle(90, kToleranceDegreesDefault, 12.0, bigKP, 0.0, 0.0, true));

				System.out.println("FINALLY FOUND BEST PARAMETERS FOR BIG ANGLE: kp=" + Robot.dt.getTunedBigAngleKP()
						+ "; ki=" + Robot.dt.getTunedBigAngleKI() + "; kd=" + Robot.dt.getTunedBigAngleKD());

			}

			System.out.println("Now tuning for distance.");
			if (j == 2) {
				addSequential(new DriveDistance(3, kToleranceDisplacementDefault, 12.0, distanceKP, 0.0, 0.0, true));

				System.out.println("FINALLY FOUND BEST PARAMETERS FOR DISTANCE: kp=" + Robot.dt.getTunedDistanceKP()
						+ "; ki=" + Robot.dt.getTunedDistanceKI() + "; kd=" + Robot.dt.getTunedDistanceKD());
			}
		}
		System.out.println("Tuning process complete.");
		System.out.println("AutoTune Drivetrain command complete.");

	}
}

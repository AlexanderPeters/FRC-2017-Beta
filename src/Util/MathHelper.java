package Util;

public class MathHelper {
	private double lastTimeErrorWas0, lastOneHalfPeriodDuration, periodsTheOscillationHasBeenStable = 0;
	// Each half of a period can have a 1/4 second difference with any other
	// half of a period
	private double oneHalfPeriodDeviationTolerance = 0.25;
	private int timesIsStableOscillationHasBeenCalled = 1;
	private double lastTime, lastValue;

	public MathHelper(double lastValue, double lastTime) {
		this.lastValue = lastValue;
		this.lastTime = lastTime;
	}
	public MathHelper() {
		periodsTheOscillationHasBeenStable = 0;
		oneHalfPeriodDeviationTolerance = 0.25;
		timesIsStableOscillationHasBeenCalled = 1;
	}

	public static double limit(double val, double limit) {
		if (Math.abs(val) >= limit)
			return limit * Math.signum(val);
		else
			return val;
	}

	public static boolean withinError(double target, double actual, double tolerance) {
		return Math.abs(target - actual) <= tolerance;
	}

	// Helper method for determining if the current oscillation is stable
	public boolean isStable(double target, double actual, double tolerance) {
		boolean withinError = MathHelper.withinError(target, actual, tolerance);
		if (withinError
				&& (timesIsStableOscillationHasBeenCalled % 4 == 1 || timesIsStableOscillationHasBeenCalled % 4 == 3))
			lastTimeErrorWas0 = System.currentTimeMillis();
		if (withinError && timesIsStableOscillationHasBeenCalled % 4 == 2)
			lastOneHalfPeriodDuration = System.currentTimeMillis() - lastTimeErrorWas0;
		if (withinError && timesIsStableOscillationHasBeenCalled % 4 == 4)
			if (Math.abs((System.currentTimeMillis() - lastTimeErrorWas0)
					- lastOneHalfPeriodDuration) <= oneHalfPeriodDeviationTolerance)
				periodsTheOscillationHasBeenStable++;

		timesIsStableOscillationHasBeenCalled++;
		return periodsTheOscillationHasBeenStable > 3;
	}

	public void resetIsStableVars() {
		periodsTheOscillationHasBeenStable = 0;
		oneHalfPeriodDeviationTolerance = 0.25;
		timesIsStableOscillationHasBeenCalled = 1;
	}
	
	//Helper method for determining the rate of change
	public double rateOfChange(double value) {
		double timeDelta = System.currentTimeMillis() - lastTime;
		double valueDelta = value - lastValue;
			
		return (valueDelta/timeDelta) * 1000;//Change/ms * 1000ms to get Change/s
		
	}
	public void setRateOfChangeVars(double lastValue, double lastTime) {
		this.lastValue = lastValue;
		this.lastTime = lastTime;
	}
}

package org.usfirst.frc.team246.robot.overclockedLibraries;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class CANTalon246 extends CANTalon implements SpeedController246 {

	boolean overridden = false;
	int pdpPort;
    PowerDistributionPanel pdp;
	
	public CANTalon246(int deviceNumber, int pdpPort, PowerDistributionPanel pdp) {
		super(deviceNumber);
		pdpPort = this.pdpPort;
		pdp = this.pdp;
	}
	
	public CANTalon246(int deviceNumber, int controlPeriodMs, int pdpPort, PowerDistributionPanel pdp) {
		super(deviceNumber, controlPeriodMs);
		pdpPort = this.pdpPort;
		pdp = this.pdp;
	}

	public void set(double speed) {
		if(!overridden) super.set(speed);
	}
	
	@Override
	public void overridingSet(double speed) {
		overridden = true;
        super.set(speed);
	}

	@Override
	public void returnControl() {
		overridden = false;
	}

	@Override
	public double getCurrent() {
		return pdp.getCurrent(pdpPort);
	}

}

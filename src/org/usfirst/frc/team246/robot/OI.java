package org.usfirst.frc.team246.robot;

import org.usfirst.frc.team246.robot.commands.CrabWithTwist;
import org.usfirst.frc.team246.robot.commands.GoFast;
import org.usfirst.frc.team246.robot.commands.RobotCentricCrabWithTwist;
import org.usfirst.frc.team246.robot.overclockedLibraries.LogitechF310;
import org.usfirst.frc.team246.robot.overclockedLibraries.Toggle;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public LogitechF310 driver;
    
    public boolean lastToting;
    
    public OI()
    {
    	driver = new LogitechF310(0);
    	
    	//driver.getLB().whileHeld(new CrabWithAbsoluteTwist());
    	driver.getLT().whileHeld(new GoFast());
    	new Toggle() {
			
			@Override
			public boolean get() {
				return driver.getX2().get();
			}
			
			@Override
			public boolean getToggler() {
				return Robot.drivetrain.getCurrentCommand().getName().equals("RobotCentricCrabWithTwist");
			}
		}.toggle(new CrabWithTwist(), new RobotCentricCrabWithTwist());
    }
}


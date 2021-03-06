package org.usfirst.frc.team246.robot;

import org.usfirst.frc.team246.robot.commands.CloseHopper;
import org.usfirst.frc.team246.robot.commands.CrabWithTwist;
import org.usfirst.frc.team246.robot.commands.GoFast;
import org.usfirst.frc.team246.robot.commands.Intake;
import org.usfirst.frc.team246.robot.commands.OpenHopper;
import org.usfirst.frc.team246.robot.commands.RobotCentricCrabWithTwist;
import org.usfirst.frc.team246.robot.commands.ShootAtTarget;
import org.usfirst.frc.team246.robot.commands.SpeedUpShooter;
import org.usfirst.frc.team246.robot.commands.StopShooter;
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
    	driver.getRT().whileHeld(new ShootAtTarget()); // hold LB to line up and shoot
    	driver.getRB().whileHeld(new Intake()); // hold RB to intake intake
    	
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
		
		//toggles hopper pneumatics forwards/backwards
		new Toggle() {
            
            @Override
            public boolean get() {
                return driver.getY2().get();
            }
            
            @Override
            public boolean getToggler() {
                return Robot.hopper.getCurrentCommand().getName().equals("OpenHopper");
            }
        }.toggle(new CloseHopper(), new OpenHopper());
        
		//toggles shooter motors between stop and speedup
		new Toggle() {
            
            @Override
            public boolean get() {
                return driver.getB().get();
            }
            
            @Override
            public boolean getToggler() {
                return Robot.shooter.getCurrentCommand().getName().equals("SpeedUpShooter");
            }
        }.toggle(new StopShooter(), new SpeedUpShooter(RobotMap.SHOOTER_MOTOR_FORWARD));        
    }
}


package org.usfirst.frc.team246.robot;

import org.usfirst.frc.team246.robot.commands.AutoDrive;
import org.usfirst.frc.team246.robot.commands.CloseHopper;
import org.usfirst.frc.team246.robot.commands.CrabWithTwist;
import org.usfirst.frc.team246.robot.commands.GoFast;
import org.usfirst.frc.team246.robot.commands.Intake;
import org.usfirst.frc.team246.robot.commands.Shoot;
import org.usfirst.frc.team246.robot.commands.SpeedUpShooter;
import org.usfirst.frc.team246.robot.commands.StopShooter;
import org.usfirst.frc.team246.robot.commands.OpenHopper;
import org.usfirst.frc.team246.robot.commands.RobotCentricCrabWithTwist;
import org.usfirst.frc.team246.robot.commands.VisionToVector2D;
import org.usfirst.frc.team246.robot.overclockedLibraries.AlertMessage;
import org.usfirst.frc.team246.robot.overclockedLibraries.LogitechF310;
import org.usfirst.frc.team246.robot.overclockedLibraries.Toggle;
import org.usfirst.frc.team246.robot.overclockedLibraries.UdpAlertService;
import org.usfirst.frc.team246.robot.overclockedLibraries.Vector2D;

import edu.wpi.first.wpilibj.networktables2.type.NumberArray;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    public LogitechF310 driver;
    
    public boolean lastToting;
    
    final NumberArray positionArray;
    
    public OI()
    {
    	driver = new LogitechF310(0);
    	
    	positionArray = new NumberArray();
    	
    	//driver.getLB().whileHeld(new CrabWithAbsoluteTwist());
    	driver.getLT().whileHeld(new GoFast());
<<<<<<< HEAD
    	while(driver.getLB().get()) {
    		try {
    			Robot.visionTable.retrieveValue("positions", positionArray);
    			Vector2D driveVector = new VisionToVector2D(positionArray.get(0), positionArray.get(1)).vectorToTarget;
    			double driveHeading = driveVector.getAngle();
    			new AutoDrive(driveVector, driveHeading);
    		} catch (TableKeyNotDefinedException exception) {
    			UdpAlertService.sendAlert(new AlertMessage("Table Key Not Defined!"));
    		}
    	}
=======
    	driver.getRT().whileHeld(new Shoot()); // hold LB to line up and shoot
    	driver.getRB().whileHeld(new Intake()); // hold RB to intake intake
    	
>>>>>>> master
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
        }.toggle(new StopShooter(), new SpeedUpShooter());        
    }
}


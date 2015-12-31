package org.usfirst.frc.team246.robot.commands;

import org.usfirst.frc.team246.robot.Robot;
import org.usfirst.frc.team246.robot.RobotMap;
import org.usfirst.frc.team246.robot.overclockedLibraries.AlertMessage;
import org.usfirst.frc.team246.robot.overclockedLibraries.DataInterpolator;
import org.usfirst.frc.team246.robot.overclockedLibraries.UdpAlertService;
import org.usfirst.frc.team246.robot.overclockedLibraries.Vector2D;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables2.type.NumberArray;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 *@author Jacob Nazarenko
 */

public class ShootAtTarget extends Command {
	
	public Vector2D targetLocation;
	public double targetXPos, targetYPos, driveHeading;
	private NumberArray positionArray;
	private DataInterpolator interpolator;
	
	AutoDrive drive;
	Shoot shoot;
	
	int state = 0;

	public ShootAtTarget() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	positionArray = new NumberArray();
    	interpolator = new DataInterpolator(RobotMap.SHOOTING_SPEED_DATA);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	// checks vision data
    	if (state == 0) {
    		try {
    			Robot.visionTable.retrieveValue("positions", positionArray); //"positions" should be pre-mapped in RoboRealm
    			this.targetXPos = positionArray.get(0);
    			this.targetYPos = positionArray.get(1); 
        		this.targetLocation = new Vector2D(true, targetXPos, targetYPos);
        		targetLocation.setAngle(targetLocation.getAngle() + RobotMap.navX.getYaw());
        		this.driveHeading = targetLocation.getAngle();
        		state = 1;
        	} catch (TableKeyNotDefinedException exception) {
        		UdpAlertService.sendAlert(new AlertMessage("Table Key Not Defined!"));
        	}
    	}
    	
    	// turns towards the target (with an empty vector for target location)
    	else if (state == 1) {
    		drive = new AutoDrive((new Vector2D(false, 0, driveHeading)), driveHeading, true);
    		drive.start();
    		state = 2;
    	}
    	
    	//checks if robot has finished turning, and then checks if the robot is within shooting range
    	else if (state == 2) {	
    		if (!drive.isRunning()) {
    			if (targetLocation.getMagnitude() > RobotMap.DISTANCE_FROM_TARGET) {
    				state = 3;
    			} else {
    				state = 5;
    			}
    		}
    	}
    	
    	// drives to maximum shoot distance if outside shooting range
    	else if (state == 3) {
    		// magnitude offset by maximum shooting range
    		targetLocation.setMagnitude(targetLocation.getMagnitude() - RobotMap.DISTANCE_FROM_TARGET); 
    		drive = new AutoDrive(targetLocation, driveHeading, true);
    		drive.start();
    		state = 4;
    	}
    	
    	// checks if robot has finished driving to maximum shooting range
    	else if (state == 4) {
    		if (!drive.isRunning()) {
    			state = 5;
    		}
    	}
    	
    	// shoots the ball based on distance, regardless now of where the robot is located within the shooting range
    	else if (state == 5) {
    		if (shoot == null) {
    			// obtains shooting speed based on data entered
        		shoot = new Shoot(interpolator.interpolateValue(targetLocation.getMagnitude()));
        		shoot.start(); 
        		// shoots (without launching shoot command more than once) until button is released
    		}
    	}
    	
    	else {
    		end(); // added for safety
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shoot.cancel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

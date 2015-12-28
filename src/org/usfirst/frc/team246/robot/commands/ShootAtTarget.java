package org.usfirst.frc.team246.robot.commands;

import org.usfirst.frc.team246.robot.Robot;
import org.usfirst.frc.team246.robot.RobotMap;
import org.usfirst.frc.team246.robot.overclockedLibraries.AlertMessage;
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

	public ShootAtTarget() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	positionArray = new NumberArray();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
			Robot.visionTable.retrieveValue("positions", positionArray); //"positions" should be pre-mapped in RoboRealm
			this.targetXPos = positionArray.get(0);
			this.targetYPos = positionArray.get(1); 
    		this.targetLocation = new Vector2D(true, targetXPos, targetYPos);
    		targetLocation.setMagnitude(targetLocation.getMagnitude() - RobotMap.DISTANCE_FROM_TARGET); //offset by desired shooting distance
    		this.driveHeading = targetLocation.getAngle();
    	} catch (TableKeyNotDefinedException exception) {
    		UdpAlertService.sendAlert(new AlertMessage("Table Key Not Defined!"));
    	}
    	if(new AutoDrive(targetLocation, driveHeading, true).isFinished()) {
    		new Shoot(RobotMap.SHOOTER_MOTOR_FORWARD); // TODO implement when and how to stop shooting, possibly with a button??
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

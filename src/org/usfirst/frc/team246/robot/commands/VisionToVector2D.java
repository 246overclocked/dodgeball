package org.usfirst.frc.team246.robot.commands;

import org.usfirst.frc.team246.robot.RobotMap;
import org.usfirst.frc.team246.robot.overclockedLibraries.Vector2D;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Jacob Nazarenko
 */

public class VisionToVector2D extends Command {
	
	public Vector2D vectorToTarget;
	public double targetXPos, targetYPos;

    // this vector will represent the location to which the robot will need to travel
	public VisionToVector2D(double xPos, double yPos) {
    	this.targetXPos = xPos;
    	this.targetYPos = yPos;
    	this.vectorToTarget = new Vector2D(true, xPos, yPos-RobotMap.DISTANCE_FROM_TARGET); // offset by shooting distance
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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

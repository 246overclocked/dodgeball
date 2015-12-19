package org.usfirst.frc.team246.robot.commands;

import org.usfirst.frc.team246.robot.Robot;
import org.usfirst.frc.team246.robot.RobotMap;
import org.usfirst.frc.team246.robot.overclockedLibraries.Vector2D;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class AutoDrive extends Command {

    private Vector2D targetLocation;
    private boolean zero;
    private double targetHeading;
	
	public AutoDrive(Vector2D targetLocation, double targetHeading, boolean zeroOdometry) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super();
    	this.targetLocation = targetLocation;
    	zero = zeroOdometry;
    	this.targetHeading = targetHeading;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (zero) Robot.drivetrain.odometry.resetLinearDiplacement();
		Robot.drivetrain.setAccelerationRamping(true);
		Robot.drivetrain.enableCrab(true);
		Robot.drivetrain.enableTwist(true);
		Robot.drivetrain.crabPID.setSetpoint(targetLocation);
		Robot.drivetrain.twistPID.setSetpoint(targetHeading);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Robot.drivetrain.crabPID.onTarget() && Robot.drivetrain.twistPID.onTarget());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setAccelerationRamping(false);
		Robot.drivetrain.enableCrab(false);
		Robot.drivetrain.enableTwist(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}

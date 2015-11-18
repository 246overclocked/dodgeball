package org.usfirst.frc.team246.robot.commands;

import org.usfirst.frc.team246.robot.Robot;
import org.usfirst.frc.team246.robot.overclockedLibraries.AlertMessage;
import org.usfirst.frc.team246.robot.overclockedLibraries.UdpAlertService;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 *@author Jacob Nazarenko
 */

public class LineUpShoot extends Command {

    public LineUpShoot() {
        requires(Robot.shooter);
        requires(Robot.drivetrain); // drivetrain will be needed to align robot
        //TODO: also requires the robot's odometry and vision systems
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	UdpAlertService.sendAlert(new AlertMessage("Shooting..."));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO: ADD ODOMETRY AND VISION TEST STATEMENTS HERE
    	Robot.shooter.shoot();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

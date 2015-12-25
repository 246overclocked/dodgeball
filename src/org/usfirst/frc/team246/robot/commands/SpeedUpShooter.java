package org.usfirst.frc.team246.robot.commands;

import org.usfirst.frc.team246.robot.Robot;
import org.usfirst.frc.team246.robot.overclockedLibraries.AlertMessage;
import org.usfirst.frc.team246.robot.overclockedLibraries.UdpAlertService;

import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Jacob Nazarenko
 */
public class SpeedUpShooter extends Command {

	public double speed;
	
    public SpeedUpShooter(double speed) {
    	this.speed = speed;
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	UdpAlertService.sendAlert(new AlertMessage("Shooter Motors On"));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.speedUp(speed);
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

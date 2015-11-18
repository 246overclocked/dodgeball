package org.usfirst.frc.team246.robot.commands;

import org.usfirst.frc.team246.robot.Robot;
import org.usfirst.frc.team246.robot.overclockedLibraries.AlertMessage;
import org.usfirst.frc.team246.robot.overclockedLibraries.UdpAlertService;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 *@author Jacob Nazarenko
 */

public class CloseHopper extends Command {

    public CloseHopper() {
        requires(Robot.hopper);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	UdpAlertService.sendAlert(new AlertMessage("Closing Hopper"));
        Robot.hopper.close();
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

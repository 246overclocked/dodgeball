package org.usfirst.frc.team246.robot.commands;

import org.usfirst.frc.team246.robot.Robot;
import org.usfirst.frc.team246.robot.RobotMap;
import org.usfirst.frc.team246.robot.overclockedLibraries.AlertMessage;
import org.usfirst.frc.team246.robot.overclockedLibraries.UdpAlertService;
import org.usfirst.frc.team246.robot.overclockedLibraries.Vector2D;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables2.type.NumberArray;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 *
 */
public class ExampleCommand extends Command {
	
	//Normally this sensor would be in RobotMap, but because it doesn't actually exist I put it here
	DigitalInput fakeBallDetector = new DigitalInput(0);
	
	//Variable to store commands which need to be referenced more than once
	AutoDrive drive;
	Shoot shoot;
	
	//This counter is used for what is called a state machine
	int state = 0;

    public ExampleCommand() {
        //Do NOT put a requires() call in here.
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Open the hopper
    	(new OpenHopper()).start(); //Because I never need to cancel, restart, or check the status of this command,
    								//I have no need to put it inside a variable.
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//State machine
    	if(state == 0) {
    		//Intake
    		intake.start();
    		
    		//When a ball is detected by the fake sensor, proceed to state 1
    		if(fakeBallDetector.get()) {
    			state = 1;
    		}
    	}
    	else if(state == 1) {
    		//Turn off the shooter
    		(new StopShooter()).start();
    		
    		//Get the distance to the target. Assume it is due north, and the robot is facing towards it
    		NumberArray pos = new NumberArray();
    		double distFromTarget = 0;
    		try {
    			Robot.visionTable.retrieveValue("positions", pos); //"positions" should be pre-mapped in RoboRealm
    			distFromTarget = pos.get(1);
        	} catch (TableKeyNotDefinedException exception) {
        		UdpAlertService.sendAlert(new AlertMessage("Table Key Not Defined!"));
        	}
    		
    		//Drive to the correct distance from the target
    		drive = new AutoDrive(new Vector2D(false, distFromTarget - RobotMap.DISTANCE_FROM_TARGET, 0), 0, true); 
    			//The true at the end ensures that our starting position is 0. The only time we don't put true is when we are
    			//doing a sequence of commands and want to keep referencing the same starting point for multiple movements
    		
    		//When the driving command is finished (the robot has reached its destination), proceed to state 2
    		if(!drive.isRunning()) {
    			state = 2;
    		}
    	}
    	else if(state == 2) {
    		//Shoot the ball
    		shoot = new Shoot(7); //7 is just a random number for the speed of the shooter
    		shoot.start();
    		
    		//When the ball is no longer detected by the fake sensor, proceed to state 4
    		if(!fakeBallDetector.get()) {
    			state = 3;
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state == 3;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//End the shoot command
    	shoot.cancel();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

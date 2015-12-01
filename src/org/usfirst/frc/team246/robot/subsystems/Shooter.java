package org.usfirst.frc.team246.robot.subsystems;

import org.usfirst.frc.team246.robot.RobotMap;
import org.usfirst.frc.team246.robot.commands.StopShooter;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 *@author Jacob Nazarenko
 */

public class Shooter extends Subsystem {
    
	public CANTalon rightMotor = RobotMap.rightShooterMotor;
	public CANTalon leftMotor = RobotMap.leftShooterMotor;
	public CANTalon feederMotor = RobotMap.feederMotor;
	
    public void speedUp(double speed) {
    	leftMotor.set(speed);
    	rightMotor.set(speed);
    	feederMotor.set(RobotMap.FEEDER_MOTOR_STOP);
    }
	
	public void shoot(double speed) {
		speedUp(speed);
		if ((rightMotor.get() >= speed) && (leftMotor.get() >= speed)) {
			feederMotor.set(RobotMap.FEEDER_MOTOR_FORWARD);
		} else {
			feederMotor.set(RobotMap.FEEDER_MOTOR_STOP);
		}
    }
    
    public void intake() {
    	leftMotor.set(RobotMap.SHOOTER_MOTOR_REVERSE);
    	rightMotor.set(RobotMap.SHOOTER_MOTOR_REVERSE);
    	feederMotor.set(RobotMap.FEEDER_MOTOR_REVERSE);
    }
    
    public void stop() {
    	leftMotor.set(RobotMap.SHOOTER_MOTOR_STOP);
    	rightMotor.set(RobotMap.SHOOTER_MOTOR_STOP);
    	feederMotor.set(RobotMap.FEEDER_MOTOR_STOP);
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new StopShooter());
    }  
}


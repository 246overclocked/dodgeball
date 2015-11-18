package org.usfirst.frc.team246.robot.subsystems;

import org.usfirst.frc.team246.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 *@author Jacob Nazarenko
 */

public class PIDShooter extends Subsystem {
    
	public CANTalon rightMotor = RobotMap.rightShooterMotor;
	public CANTalon leftMotor = RobotMap.leftShooterMotor;
	
    public void shoot() {
    	leftMotor.set(RobotMap.SHOOTER_MOTOR_REVERSE);
    	rightMotor.set(RobotMap.SHOOTER_MOTOR_FORWARD);
    }
    
    public void intake() {
    	leftMotor.set(RobotMap.SHOOTER_MOTOR_FORWARD);
    	rightMotor.set(RobotMap.SHOOTER_MOTOR_REVERSE);
    }
    
    public void stop() {
    	leftMotor.set(RobotMap.SHOOTER_MOTOR_STOP);
    	rightMotor.set(RobotMap.SHOOTER_MOTOR_STOP);
    }

    public void initDefaultCommand() {
    }
    
}


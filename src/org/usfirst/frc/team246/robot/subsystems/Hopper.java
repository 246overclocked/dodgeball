package org.usfirst.frc.team246.robot.subsystems;

import org.usfirst.frc.team246.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
*
*@author Jacob
*/

public class Hopper extends Subsystem {
    
    public DoubleSolenoid piston1 = RobotMap.hopperPiston1;
    public DoubleSolenoid piston2 = RobotMap.hopperPiston2;

    public void initDefaultCommand() {
    }
    
    public void open() {
        piston1.set(Value.kForward);
        piston2.set(Value.kForward);
    }
    
    public void close() {
        piston1.set(Value.kReverse);
        piston2.set(Value.kReverse);
    }
}


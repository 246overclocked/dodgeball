package org.usfirst.frc.team246.robot;

import org.usfirst.frc.team246.nav6.IMUAdvanced;
import org.usfirst.frc.team246.robot.overclockedLibraries.CANTalonPotentiometer;
import org.usfirst.frc.team246.robot.overclockedLibraries.Diagnostics;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;



/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
    
//Drivetrain
	
	//Motors
	
	public static CANTalon frontWheelMotor;
	public static CANTalon backWheelMotor;
	public static CANTalon leftWheelMotor;
	public static CANTalon rightWheelMotor;
	
	public static CANTalonPotentiometer frontModuleMotor;
	public static CANTalonPotentiometer backModuleMotor;
	public static CANTalonPotentiometer leftModuleMotor;
	public static CANTalonPotentiometer rightModuleMotor;
	
	//Sensor
	
	
	public static IMUAdvanced navX;
	
	//constants
	
	public static final double WHEEL_ENCODER_DISTANCE_PER_TICK = .5*Math.PI/256;
		
	public static double WHEEL_kP = 0.15;
	public static double WHEEL_kI = 0;
	public static double WHEEL_kD = 0;
	public static double WHEEL_kF = 0.075;
	
	public static double MODULE_kP = .05;
	public static double MODULE_kI = 0;
	public static double MODULE_kD = .08;
	public static double MODULE_kF = 0;
	
	public static double ABSOLUTE_TWIST_kP = .2;
    public static double ABSOLUTE_TWIST_kI = 0;
    public static double ABSOLUTE_TWIST_kD = 0;
	
    public static double ABSOLUTE_CRAB_kP = 1;
    public static double ABSOLUTE_CRAB_kI = 0;
    public static double ABSOLUTE_CRAB_kD = 0;
    
    public static double ABSOLUTE_CRAB_FAST_kP = 1;
    
    public static final double K_MODULE_ANGLE_DELTA = 1;
    public static final double K_MODULE_ANGLE_TWIST = 0;
    public static final double K_MODULE_ANGLE_REVERSE = 0;
    
    public static final double MAX_MODULE_ANGLE = 360; //the maximum angle which can be commanded to a module
    public static final double UNSAFE_MODULE_ANGLE = MAX_MODULE_ANGLE + 180; //the angle at which a module motor should be emergency stopped
    
    public static final double LEFT_RIGHT_WIDTH = 25; //distance between "left" and "right" modules
    public static final double FRONT_BACK_LENGTH = 25; //distance between "front" and "back" modules
    public static final double WHEEL_TOP_ABSOLUTE_SPEED = 12; //the highest speed that our wheels can move
    
    public static final double FAST_MAX_CRAB_SPEED = 7;
    public static final double FAST_MAX_SPIN_SPEED =7;
    
    public static final double SLOW_MAX_CRAB_SPEED = 2.31;
    public static final double SLOW_MAX_SPIN_SPEED = 5;
    
    public static final double crabZeroZone = .1;
    
    public static final double NORTH = 0;
    public static final double SOUTH = 180;
    public static final double WEST = 90;
    public static final double EAST = 270;
    
    public static final double ACCELERATION_CONSTANT = 20;
    
//Hopper
    
    public static DoubleSolenoid hopperPiston1;
    public static DoubleSolenoid hopperPiston2;
	
	static void init()
	{
		
	//Drivetrain
		
		//Motors
		
//		TODO: set pin numbers
		frontWheelMotor = new CANTalon(0, 12);
		frontWheelMotor.changeControlMode(ControlMode.Speed);
		frontWheelMotor.set(0);
		LiveWindow.addActuator("Drivetrain", "frontWheelMotor", (LiveWindowSendable) frontWheelMotor);
		Diagnostics.addSRXEncoder(frontWheelMotor, "frontWheelEncoder");
		
		backWheelMotor = new CANTalon(0, 12);
		backWheelMotor.changeControlMode(ControlMode.Speed);
		backWheelMotor.set(0);
		LiveWindow.addActuator("Drivetrain", "backWheelMotor", (LiveWindowSendable) backWheelMotor);
		Diagnostics.addSRXEncoder(backWheelMotor, "backWheelEncoder");
		
		leftWheelMotor = new CANTalon(2, 13);
		leftWheelMotor.changeControlMode(ControlMode.Speed);
		leftWheelMotor.set(0);
		LiveWindow.addActuator("Drivetrain", "leftWheelMotor", (LiveWindowSendable) leftWheelMotor);
		Diagnostics.addSRXEncoder(leftWheelMotor, "leftWheelEncoder");
		
		rightWheelMotor = new CANTalon(4, 14);
		rightWheelMotor.changeControlMode(ControlMode.Speed);
		rightWheelMotor.set(0);
		LiveWindow.addActuator("Drivetrain", "rightWheelMotor", (LiveWindowSendable) rightWheelMotor);
		Diagnostics.addSRXEncoder(rightWheelMotor, "rightWheelEncoder");
		
//		TODO: set pin numbers
// 		TODO: initial angle PID setpoints
		frontModuleMotor = new CANTalonPotentiometer(1, 15,1,1); //TODO: Get offset and range values
        frontModuleMotor.changeControlMode(ControlMode.Position);
        LiveWindow.addActuator("Drivetrain", "frontModuleMotor", (LiveWindowSendable) frontModuleMotor);
		Diagnostics.addSRXPotentiometer(frontModuleMotor, "frontWheelPotentiometer");
        
        backModuleMotor = new CANTalonPotentiometer(1, 15,1,1);
		backModuleMotor.changeControlMode(ControlMode.Position);
        LiveWindow.addActuator("Drivetrain", "backModuleMotor", (LiveWindowSendable) backModuleMotor);
        Diagnostics.addSRXPotentiometer(backModuleMotor, "backWheelPotentiometer");
        
        leftModuleMotor = new CANTalonPotentiometer(3, 1,1,1);
		leftModuleMotor.changeControlMode(ControlMode.Position);
        LiveWindow.addActuator("Drivetrain", "leftModuleMotor", (LiveWindowSendable) leftModuleMotor);
        Diagnostics.addSRXPotentiometer(leftModuleMotor, "leftWheelPotentiometer");
        
        rightModuleMotor = new CANTalonPotentiometer(5, 0,1,1);
		rightModuleMotor.changeControlMode(ControlMode.Position);
        LiveWindow.addActuator("Drivetrain", "rightModuleMotor", (LiveWindowSendable) rightModuleMotor);
        Diagnostics.addSRXPotentiometer(rightModuleMotor, "rightWheelPotentiometer");
        
		//Sensors
		
	    
	  //We were having occasional errors with the creation of the nav6 object, so we make 5 attempts before allowing the error to go through and being forced to redeploy.
        int count = 0;
        int maxTries = 5;
        while(true) {
            try {
                navX = new IMUAdvanced(new SerialPort(57600,SerialPort.Port.kMXP), (byte)50);
                if(navX != null) break;
            } catch (Exception e) {
                if (++count == maxTries)
                {
                    e.printStackTrace();
                    break;
                }
                Timer.delay(.01);
            }
        }
        LiveWindow.addSensor("Drivetrain", "Gyro", navX);
        
        //constants        
    	WHEEL_kP = 0.15;
    	WHEEL_kI = 0;
    	WHEEL_kD = 0;
    	WHEEL_kF = 0.075;
    	
    	MODULE_kP = .055;
    	MODULE_kI = 0;
    	MODULE_kD = .050;
    	MODULE_kF = 0;
    	
    	//Hopper
    	hopperPiston1 = new DoubleSolenoid(1, 0); //will need to use ports 0 (reverse) and 1 (forward) on RoboRio
    	hopperPiston2 = new DoubleSolenoid(3, 2); //will need to use ports 2 (reverse) and 3 (forward) on RoboRio
	}
}

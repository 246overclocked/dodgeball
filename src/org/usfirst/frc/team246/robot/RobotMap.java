package org.usfirst.frc.team246.robot;

import org.usfirst.frc.team246.nav6.IMUAdvanced;
import org.usfirst.frc.team246.robot.overclockedLibraries.AnalogPot;
import org.usfirst.frc.team246.robot.overclockedLibraries.Diagnostics;
import org.usfirst.frc.team246.robot.overclockedLibraries.SpeedController246;
import org.usfirst.frc.team246.robot.overclockedLibraries.Victor246;
import org.usfirst.frc.team246.robot.overclockedLibraries.VictorSP246;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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
	
	public static PowerDistributionPanel pdp;
    
//Drivetrain
	
	//Motors
	
	public static SpeedController246 frontWheelMotor;
	public static SpeedController246 backWheelMotor;
	public static SpeedController246 leftWheelMotor;
	public static SpeedController246 rightWheelMotor;
	
	public static SpeedController246 frontModuleMotor;
	public static SpeedController246 backModuleMotor;
	public static SpeedController246 leftModuleMotor;
	public static SpeedController246 rightModuleMotor;
	
	//Sensors
	
	public static Encoder frontWheelEncoder;
	public static Encoder backWheelEncoder;
	public static Encoder leftWheelEncoder;
	public static Encoder rightWheelEncoder;
	
	public static AnalogPot frontModulePot;
	public static AnalogPot backModulePot;
	public static AnalogPot leftModulePot;
	public static AnalogPot rightModulePot;
	
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
	
	static void init()
	{
		pdp = new PowerDistributionPanel();
		
	//Drivetrain
		
		//Motors
		
//		TODO: set pin numbers
//		frontWheelMotor = new VictorSP246(0, 12, pdp);
//		LiveWindow.addActuator("Drivetrain", "frontWheelMotor", (LiveWindowSendable) frontWheelMotor);
		backWheelMotor = new VictorSP246(0, 12, pdp);
		LiveWindow.addActuator("Drivetrain", "backWheelMotor", (LiveWindowSendable) backWheelMotor);
		leftWheelMotor = new VictorSP246(2, 13, pdp);
		LiveWindow.addActuator("Drivetrain", "leftWheelMotor", (LiveWindowSendable) leftWheelMotor);
		rightWheelMotor = new VictorSP246(4, 14, pdp);
		LiveWindow.addActuator("Drivetrain", "rightWheelMotor", (LiveWindowSendable) rightWheelMotor);
		
//		TODO: set pin numbers
//		frontModuleMotor = new Victor246(1, 15, pdp);
//		LiveWindow.addActuator("Drivetrain", "frontModuleMotor", (LiveWindowSendable) frontModuleMotor);
		backModuleMotor = new Victor246(1, 15, pdp);
		LiveWindow.addActuator("Drivetrain", "backModuleMotor", (LiveWindowSendable) backModuleMotor);
		leftModuleMotor = new Victor246(3, 1, pdp);
		LiveWindow.addActuator("Drivetrain", "leftModuleMotor", (LiveWindowSendable) leftModuleMotor);
		rightModuleMotor = new Victor246(5, 0, pdp);
		LiveWindow.addActuator("Drivetrain", "rightModuleMotor", (LiveWindowSendable) rightModuleMotor);
		
		//Sensors
		
//		TODO: define inputs
//		frontWheelEncoder = new Encoder(0,1);
//	    frontWheelEncoder.setDistancePerPulse(WHEEL_ENCODER_DISTANCE_PER_TICK);
//	    frontWheelEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate); // have encoder measure rate, not distance
//	    LiveWindow.addSensor("Drivetrain", "frontWheelEncoder", frontWheelEncoder);
	    Diagnostics.addEncoder(frontWheelEncoder, "Front Wheel Speed", frontWheelMotor);
		backWheelEncoder = new Encoder(0,1);
	    backWheelEncoder.setDistancePerPulse(WHEEL_ENCODER_DISTANCE_PER_TICK);
	    backWheelEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate); // have encoder measure rate, not distance
	    LiveWindow.addSensor("Drivetrain", "backWheelEncoder", backWheelEncoder);
	    Diagnostics.addEncoder(backWheelEncoder, "Back Wheel Speed", backWheelMotor);
		leftWheelEncoder = new Encoder(2,3);
	    leftWheelEncoder.setDistancePerPulse(WHEEL_ENCODER_DISTANCE_PER_TICK);
	    leftWheelEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate); // have encoder measure rate, not distance
	    LiveWindow.addSensor("Drivetrain", "leftWheelEncoder", leftWheelEncoder);
	    Diagnostics.addEncoder(leftWheelEncoder, "Left Wheel Speed", leftWheelMotor);
		rightWheelEncoder = new Encoder(4,5);
	    rightWheelEncoder.setDistancePerPulse(WHEEL_ENCODER_DISTANCE_PER_TICK);
	    rightWheelEncoder.setPIDSourceParameter(PIDSource.PIDSourceParameter.kRate); // have encoder measure rate, not distance
	    LiveWindow.addSensor("Drivetrain", "rightWheelEncoder", rightWheelEncoder);
	    Diagnostics.addEncoder(rightWheelEncoder, "Right Wheel Speed", rightWheelMotor);
		
//	    TODO: define inputs
//	    frontModulePot = new AnalogPot(0, 1800, -900, true);
//	    LiveWindow.addSensor("Drivetrain", "frontModulePot", frontModulePot);
//	    Diagnostics.addAnalogPot(frontModulePot, "Front Swerve Steering", frontModuleMotor);
	    backModulePot = new AnalogPot(0, 1800, -900, true);
	    LiveWindow.addSensor("Drivetrain", "backModulePot", backModulePot);
	    Diagnostics.addAnalogPot(backModulePot, "Back Swerve Steering", backModuleMotor);
	    leftModulePot = new AnalogPot(1, 1800, -900, true);
	    LiveWindow.addSensor("Drivetrain", "leftModulePot", leftModulePot);
	    Diagnostics.addAnalogPot(leftModulePot, "Left Swerve Steering", leftModuleMotor);
	    rightModulePot = new AnalogPot(2, 1800, -900, true);
	    LiveWindow.addSensor("Drivetrain", "rightModulePot", rightModulePot);
	    Diagnostics.addAnalogPot(rightModulePot, "Right Swerve Steering", rightModuleMotor);
	    
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
	}
}

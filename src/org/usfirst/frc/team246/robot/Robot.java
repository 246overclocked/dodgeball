
package org.usfirst.frc.team246.robot;

import org.usfirst.frc.team246.robot.overclockedLibraries.AlertMessage;
import org.usfirst.frc.team246.robot.overclockedLibraries.Diagnostics;
import org.usfirst.frc.team246.robot.overclockedLibraries.SwerveModule;
import org.usfirst.frc.team246.robot.overclockedLibraries.UdpAlertService;
import org.usfirst.frc.team246.robot.overclockedLibraries.Victor246;
import org.usfirst.frc.team246.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	
	public static boolean test1 = false;
	public static boolean test2 = false;
	public static boolean test3 = false;
	public static boolean gyroDisabled = false;
	public static boolean gasMode = false;
	
	public static Drivetrain drivetrain;
	
	public static boolean teleopZeroedNavX = false;
	
	public enum RobotMode
	{
		DISABLED, AUTONOMOUS, TELEOP, TEST;
	}
	
	public static RobotMode robotMode = RobotMode.DISABLED;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	
    	Diagnostics.initialize();
        RobotMap.init();
        
        drivetrain = new Drivetrain();
        
        oi = new OI();
        
        if(test1)
        {
            SmartDashboard.putNumber("speedSetpoint", 0);
            SmartDashboard.putNumber("angleSetpoint", 0);
            SmartDashboard.putBoolean("speedOn", true);
            SmartDashboard.putBoolean("angleOn", true);
            SmartDashboard.putBoolean("unwind", false);
            SmartDashboard.putNumber("speed", 0);
            SmartDashboard.putNumber("angle", 0);
            SmartDashboard.putNumber("K_DELTA", RobotMap.K_MODULE_ANGLE_DELTA);
            SmartDashboard.putNumber("K_TWIST", RobotMap.K_MODULE_ANGLE_TWIST);
            SmartDashboard.putNumber("K_REVERSE", RobotMap.K_MODULE_ANGLE_REVERSE);
        }
        if(test2)
        {
            SmartDashboard.putNumber("speedP", RobotMap.WHEEL_kP);
            SmartDashboard.putNumber("speedI", RobotMap.WHEEL_kI);
            SmartDashboard.putNumber("speedD", RobotMap.WHEEL_kD);
            SmartDashboard.putNumber("speedF", RobotMap.WHEEL_kF);
            SmartDashboard.putNumber("frontModuleSpeed", 0);
            SmartDashboard.putNumber("backModuleSpeed", 0);
            SmartDashboard.putNumber("leftModuleSpeed", 0);
            SmartDashboard.putNumber("rightModuleSpeed", 0);
            SmartDashboard.putNumber("frontModuleSpeedSetpoint", 0);
            SmartDashboard.putNumber("backModuleSpeedSetpoint", 0);
            SmartDashboard.putNumber("leftModuleSpeedSetpoint", 0);
            SmartDashboard.putNumber("rightModuleSpeedSetpoint", 0);
            SmartDashboard.putNumber("spinRate", 0);
        }
        if(test3)
        {
        	SmartDashboard.putNumber("absoluteTwistP", 0);
        	SmartDashboard.putNumber("absoluteTwistI", 0);
        	SmartDashboard.putNumber("absoluteTwistD", 0);
        }
        
        SmartDashboard.putBoolean("motorKilled", false);
        SmartDashboard.putBoolean("field-centric", true);
        SmartDashboard.putNumber("ARM_SHOULDER_MANUAL_SPEED", 5);
        SmartDashboard.putNumber("ARM_ELBOW_MANUAL_SPEED", 5);
        SmartDashboard.putNumber("ARM_WRIST_MANUAL_SPEED", 5);
        SmartDashboard.putNumber("startHeading", 0);
        
        SmartDashboard.putData(Scheduler.getInstance());
    }
    
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit() {
    	robotMode = RobotMode.DISABLED;
    	drivetrain.PIDOn(false);
    }
    
    boolean wasConnected = false;
	public void disabledPeriodic() {
		
		allPeriodic();
		
		if(DriverStation.getInstance().isDSAttached() && !wasConnected)
		{
			System.out.print("Connected to driver station");
			UdpAlertService.initialize("MSilver-PC.local", 5801);
			wasConnected = true;
		}

		Scheduler.getInstance().run();
	}
	
    public void autonomousInit() {
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	
    }

    public void teleopInit() {
    	robotMode = RobotMode.TELEOP;
    	Robot.drivetrain.setMaxSpeed(RobotMap.SLOW_MAX_CRAB_SPEED, RobotMap.SLOW_MAX_SPIN_SPEED);
    	drivetrain.PIDOn(true);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	allPeriodic();
    	
        if(test2)
        {
        	SmartDashboard.putNumber("frontModuleSpeed", drivetrain.frontModule.getWheelSpeed());
        	SmartDashboard.putNumber("backModuleSpeed", drivetrain.backModule.getWheelSpeed());
            SmartDashboard.putNumber("leftModuleSpeed", drivetrain.leftModule.getWheelSpeed());
            SmartDashboard.putNumber("rightModuleSpeed", drivetrain.rightModule.getWheelSpeed());
            SmartDashboard.putNumber("frontModuleSpeedSetpoint", drivetrain.frontModule.getSpeedSetpoint());
            SmartDashboard.putNumber("backModuleSpeedSetpoint", drivetrain.backModule.getSpeedSetpoint());
            SmartDashboard.putNumber("leftModuleSpeedSetpoint", drivetrain.leftModule.getSpeedSetpoint());
            SmartDashboard.putNumber("rightModuleSpeedSetpoint", drivetrain.rightModule.getSpeedSetpoint());
        }
        if(test3)
        {
        	
        }
        if(test1)
        {
            SwerveModule mod = drivetrain.backModule;
            
            if(SmartDashboard.getBoolean("unwind", false))
            {
               mod.unwind();
            }
            else
            {
                if(SmartDashboard.getBoolean("speedOn", false))
                {
                    mod.setWheelSpeed(SmartDashboard.getNumber("speedSetpoint", 0));
                }
                else
                {
                    mod.speedPIDOn(false);
                }

                if(SmartDashboard.getBoolean("angleOn", false))
                {
                    mod.setAngle(SmartDashboard.getNumber("angleSetpoint", 0));
                }
                else
                {
                    mod.anglePIDOn(false);
                }
            }
            
            SmartDashboard.putNumber("speed", mod.getWheelSpeed());
            SmartDashboard.putNumber("angle", mod.getModuleAngle());
        }
        else
        {
            //the drivetrain will automatically unwind each of the 4 modules when the robot is not moving
            if(drivetrain.isMoving())
            {
                drivetrain.stopUnwinding();
            }
            else
            {
            	if(!drivetrain.swerves[0].unwinding) UdpAlertService.sendAlert(new AlertMessage("Unwinding"));
                drivetrain.unwind();
            }
            
            if(!teleopZeroedNavX && DriverStation.getInstance().getMatchTime() > 130)
            {
            	if(oi.driver.getUp().get()) RobotMap.navX.zeroYaw(0);
            	if(oi.driver.getLeft().get()) RobotMap.navX.zeroYaw(-90);
            	if(oi.driver.getDown().get()) RobotMap.navX.zeroYaw(180);
            	if(oi.driver.getRight().get()) RobotMap.navX.zeroYaw(90);
            }
            
            Scheduler.getInstance().run();
        }
    }
    
    boolean liftWasDown = false;
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	robotMode = RobotMode.TEST;
    	allPeriodic();
    	
        LiveWindow.run();
    }
    
    public void allPeriodic()
    {
    	
        //This is a safety to prevent any of the modules from rotating too far and overtwisting the wires. 
        //If any module angle surpasses RobotMap.UNSAFE_MODULE_ANGLE, the motor controlling it will be automatically shut off
    	for(int i=0; i<drivetrain.swerves.length; i++)
    	{
    		if(Math.abs(drivetrain.swerves[i].getModuleAngle()) > RobotMap.UNSAFE_MODULE_ANGLE)
            {
                if(!SmartDashboard.getBoolean("motorKilled")) UdpAlertService.sendAlert(new AlertMessage("Steering Motor Killed").playSound("malfunction.wav"));
                ((Victor246)drivetrain.swerves[i].moduleMotor).overridingSet(0);
                SmartDashboard.putBoolean("motorKilled", true);
            }
    	}

        //allows the operator to manually return control of all modules to their respective PIDcontrollers
        if(!SmartDashboard.getBoolean("motorKilled", true))
        {
            for(int i=0; i<drivetrain.swerves.length; i++)
            {
            	((Victor246)drivetrain.swerves[i].moduleMotor).returnControl();
            }
        }
        
        SmartDashboard.putNumber("Heading", RobotMap.navX.getYaw());
        
        if(DriverStation.getInstance().isBrownedOut()) UdpAlertService.sendAlert(new AlertMessage("Brownout").playSound("low_power.wav"));
        
        //gyroDisabled = !SmartDashboard.getBoolean("field-centric", true);

    }
   
}

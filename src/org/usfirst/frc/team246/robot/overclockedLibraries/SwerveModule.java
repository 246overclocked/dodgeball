package org.usfirst.frc.team246.robot.overclockedLibraries;

import org.usfirst.frc.team246.robot.Robot;
import org.usfirst.frc.team246.robot.RobotMap;

import edu.wpi.first.wpilibj.CANJaguar.ControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Paul
 */
public class SwerveModule 
{
    double x; //the horizontal distance between this wheel and the center of the robot
    double y; //the vertical distance between this wheel and the center of the robot
    
    public String name;

    public CANTalon246 wheelMotor; //the motor controlling wheel speed

    public CANTalon246 moduleMotor; //the motor controlling module angle
    
    //public PIDController speedPID; //the PID controller for wheel speed
    //public PIDController anglePID; //the PID controller for module angle
    
    public boolean invertSpeed = false; // true when the wheel is pointing backwards
    
    public boolean unwinding = false; //if true, then the wheels will return to pointing forwards with the wires completely untwisted
    
    public double maxSpeed;
    
    public boolean accelerationControl = false;
    
    public SwerveModule(CANTalon246 wheelMotor, CANTalon246 moduleMotor, double maxSpeed, double x, double y, String name)
    {
        // set globals
        
        this.x = x;
        this.y = y;
        
        this.name = name;
        
        this.wheelMotor = wheelMotor;
        this.moduleMotor = moduleMotor;
        
        this.maxSpeed = maxSpeed;
        
        //initialize PID controllers
        wheelMotor.changeControlMode(edu.wpi.first.wpilibj.CANTalon.ControlMode.Speed);
        wheelMotor.set(0);
        moduleMotor.changeControlMode(edu.wpi.first.wpilibj.CANTalon.ControlMode.Position);
        moduleMotor.set(0);
        //speedPID = new PIDController(RobotMap.WHEEL_kP, RobotMap.WHEEL_kI, RobotMap.WHEEL_kD, RobotMap.WHEEL_kF, wheelEncoder, wheelMotor);
        //anglePID = new PIDController(RobotMap.MODULE_kP, RobotMap.MODULE_kI, RobotMap.MODULE_kD, RobotMap.MODULE_kF, modulePot, moduleMotor, .02);
        wheelMotor.setPID(RobotMap.WHEEL_kP, RobotMap.WHEEL_kI, RobotMap.WHEEL_kD, RobotMap.WHEEL_kF, 0, 0, 0);  //THESE CONSTANTS NEED TO BE DISCUSSED AND SET
        moduleMotor.setPID(RobotMap.WHEEL_kP, RobotMap.WHEEL_kI, RobotMap.WHEEL_kD, RobotMap.WHEEL_kF, 0, 0, 0);
        //speedPID.setOutputRange(-1, 1);
        //anglePID.setOutputRange(-1, 1);
        
        //anglePID.setAbsoluteTolerance(5);
        //TODO How to implement the above 3 lines of code with SRXs??
        //LiveWindow.addSensor("SwerveModule", name + "speedPID", wheelMotor);
        //LiveWindow.addSensor("SwerveModule", name + "anglePID", moduleMotor);
    }
    
//    coordinates
    public double getX(){
        return x;
    }
    
    public double getY(){
        return y;
    }
    
    // PID Methods
    
    //whenever possible, call setAngle before setSpeed
    
    public void setAngle(double angle){

        if(!unwinding)
        {
            //The following is uses a weighted rating system to decide which direction we rotate the module

            //constants for the weighted average
            final double K_DELTA = RobotMap.K_MODULE_ANGLE_DELTA;
            final double K_TWIST = RobotMap.K_MODULE_ANGLE_TWIST;
            final double K_REVERSE = RobotMap.K_MODULE_ANGLE_REVERSE;

            //ensure that anglePID is enabled before running
            //anglePID.enable();
            
            //converts the inputed angle into its reference angle
            angle = angle % 360;

            double setPointForward = angle; // angle setpoint if we want the wheel to move forward
            double setPointBackward = angle + 180; // angle setpoint if we want the wheel to move backwards

            //The following code ensures that our 2 potential setpoints are the ones closest to our current angle
            while(Math.abs(setPointForward - moduleMotor.getPotAngle()) > 180)
            {
                if(setPointForward - moduleMotor.getPotAngle() < 0 && setPointForward < RobotMap.MAX_MODULE_ANGLE - 360) setPointForward += 360; //if we need to add 360 to get closer to moduleEncoder, do so
                else if (setPointForward - moduleMotor.getPotAngle() > 0 && setPointForward > -RobotMap.MAX_MODULE_ANGLE + 360) setPointForward -= 360; //else subtract 360
                else break;
            }

            while(Math.abs(setPointBackward - moduleMotor.getPotAngle()) > 180)
            {
                if(setPointBackward - moduleMotor.getPotAngle() < 0 && setPointBackward < RobotMap.MAX_MODULE_ANGLE - 360) setPointBackward += 360; //if we need to add 360 to get closer to moduleEncoder, do so
                else if (setPointBackward - moduleMotor.getPotAngle() > 0 && setPointBackward > -RobotMap.MAX_MODULE_ANGLE + 360) setPointBackward -= 360; //else subtract 360
                else break;
            }

            //rating for how desirable each setpoint is. Higher numbers are better
            double forwardsRating = 0;
            double backwardsRating = 0;

            //Rating for the distance between where the module is currently pointing and each of the setpoints
            forwardsRating -= K_DELTA*Math.abs(setPointForward - moduleMotor.getPotAngle());
            backwardsRating -= K_DELTA*Math.abs(setPointBackward - moduleMotor.getPotAngle());

            //Rating boost if this setpoint is closer to the 0 (where the wire is completely untwisted) that the current module angle
            if(setPointForward > 0){
                forwardsRating += (moduleMotor.getPotAngle() - setPointForward)*K_TWIST; // positive => we are unwinding (moving closer to zero)
            } else {
                forwardsRating += (setPointForward - moduleMotor.getPotAngle())*K_TWIST; // negative => we are winding up (moving farther from zero)
            }

            if(setPointBackward > 0){
                backwardsRating += (moduleMotor.getPotAngle() - setPointBackward)*K_TWIST; // positive => we are unwinding (moving closer to zero)
            } else {
                backwardsRating += (setPointBackward - moduleMotor.getPotAngle())*K_TWIST; // negative => we are winding up (moving farther from zero)
            }

            //Rating for if the how much the velocity will need to change in order the make the wheel go further. Forwards rating gets a positive boost if wheel is already moving forwards, if the wheel is currently moving backwards it gets a deduction.
            forwardsRating += K_REVERSE * wheelMotor.getEncVelocity();

            //Decision making time
            if(forwardsRating > backwardsRating)
            {
                moduleMotor.set(setPointForward);
                invertSpeed = false;
            }
            else
            {
                moduleMotor.set(setPointBackward);
                invertSpeed = true;
            }
        }
    }
    
    public void setWheelSpeed(double speed){
        if(invertSpeed) speed = -speed;
        if(!Robot.gasMode)
        {
            if(Robot.test2)
            {
                wheelMotor.setPID(SmartDashboard.getNumber("speedP", RobotMap.WHEEL_kP), SmartDashboard.getNumber("speedI", RobotMap.WHEEL_kI), SmartDashboard.getNumber("speedD", RobotMap.WHEEL_kD));
                wheelMotor.setF(SmartDashboard.getNumber("speedF", RobotMap.WHEEL_kF));
            }
            wheelMotor.enableControl();
            if(accelerationControl) wheelMotor.set(wheelMotor.getSetpoint() + (speed*maxSpeed - wheelMotor.getSetpoint())/RobotMap.ACCELERATION_CONSTANT);  //TODO: Can we adjust this using new Talon stuff?
            else wheelMotor.set(maxSpeed*speed);
        }
        else
        {
        	wheelMotor.disableControl();;
            wheelMotor.set(speed);
        }
    }
    
    //Makes the wheels point forwards with the wires being completely untwisted. 
    //Once this method is called, setAngle(double angle) will be disabled until stopUnwinding is called()
    public void unwind()
    {
        unwinding = true;
        if(!moduleMotor.isControlEnabled())moduleMotor.enableControl();;
        moduleMotor.set(0);
    }
    
    //Stops the wheels from trying to point forwards and restores control to setAngle(double angle)
    public void stopUnwinding()
    {
        unwinding = false;
    }
    
    public void setMaxSpeed(double max)
    {
    	maxSpeed = max;
    }
    
    public void anglePIDOn(boolean on){
        if (on) moduleMotor.enableControl();
        else moduleMotor.enableControl();
    }
    
    public void speedPIDOn(boolean on){
        if (on) wheelMotor.enableControl();
        else wheelMotor.disableControl();
    }
    
    public double getAngleSetpoint() {
        return moduleMotor.getPotSetpoint();
    }
    
    public double getSpeedSetpoint() {
        return wheelMotor.getSetpoint();//This may need to be adjusted depending on format
    }
    
    public double getAngleOutput() {
        return moduleMotor.get();
    }
    
    public double getSpeedOutput() {
        return wheelMotor.get();
    }

    // Wheel Encoder Methods
    
    public double getWheelSpeed() {
        return wheelMotor.getSpeed();
    }
    
    public double getWheelDistance() {
        return wheelMotor.getEncPosition();//Not sure if this will work
    }
    
    public void resetWheelEncoder(){
        wheelMotor.setPosition(0);
    }
    
    // Module Encoder Methods
    
    public double getModuleAngle() {
        return moduleMotor.getPotAngle();
    }
}

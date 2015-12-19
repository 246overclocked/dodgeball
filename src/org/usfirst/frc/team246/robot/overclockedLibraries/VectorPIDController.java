package org.usfirst.frc.team246.robot.overclockedLibraries;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

public class VectorPIDController {
	PIDController PID;
	VectorPIDSource vectorSource;
	VectorPIDOutput vectorOutput;
	Vector2D setpoint;
	Vector2D velocityVector;
	
	//constructors
	public VectorPIDController(double Kp, double Ki, double Kd, double Kf,
            VectorPIDSource source, VectorPIDOutput output,
            double period) {
		vectorSource = source;
		vectorOutput = output;
		PIDSource regularSource = new PIDSource() {
			
			@Override
			public double pidGet() {
				return 0;
			}
		};
		PIDOutput regularOutput = new PIDOutput() {
			
			@Override
			public void pidWrite(double output) {
				Vector2D d = Vector2D.subtractVectors(vectorSource.pidGet(), setpoint);
				velocityVector = new Vector2D(false, output, d.getAngle());
				vectorOutput.pidWrite(velocityVector);
				
			}
		};
		PID = new PIDController(Kp, Ki, Kd, Kf, regularSource, regularOutput, period);
	}
	
	public VectorPIDController(double Kp, double Ki, double Kd,
            VectorPIDSource source, VectorPIDOutput output,
            double period) {
		this(Kp, Ki, Kd, 0, source, output, period);
	}
	
	public VectorPIDController(double Kp, double Ki, double Kd,
            VectorPIDSource source, VectorPIDOutput output) {
		this(Kp, Ki, Kd, 0, source, output, 20);
	}
	
	public VectorPIDController(double Kp, double Ki, double Kd, double Kf,
            VectorPIDSource source, VectorPIDOutput output) {
		this(Kp, Ki, Kd, Kf, source, output, 20);
	}

	
	public void setSetpoint(Vector2D setpoint) {
		this.setpoint = setpoint;
		Vector2D d = Vector2D.subtractVectors(vectorSource.pidGet(), setpoint);
		PID.setSetpoint(d.getMagnitude());
	}
	
	public Vector2D getSetpoint() {
		return setpoint;
	}
	
	public Vector2D get() {
		return velocityVector;
	}
	
	public void setPercentTolerance(double percentTolerance) {
		PID.setPercentTolerance(percentTolerance);
	}
	
	public void setAbsoluteTolerance(double absvalue) {
		PID.setAbsoluteTolerance(absvalue);
	}
	
	public boolean onTarget() {
		return PID.onTarget();
	}
	
	public void enable() {
		PID.enable();
	}
	
	public void disable() {
		PID.disable();
	}
	
	public boolean isEnabled() {
		return PID.isEnable();
	}
	
	
}

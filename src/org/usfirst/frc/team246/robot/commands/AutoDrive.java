package org.usfirst.frc.team246.robot.commands;

import org.usfirst.frc.team246.robot.overclockedLibraries.Vector2D;

/**
 *@author Jacob Nazarenko
 */

//TODO: This command should call the 'Shoot' command somewhere at the end of its execution
public class AutoDrive extends FieldCentricDrivingCommand {
	
	public Vector2D crab;
	public double angle;

    public AutoDrive(Vector2D crabVector, double heading) {
    	this.crab = crabVector;
    	this.angle = heading;
    }

	@Override
	protected Vector2D getCrabVector() {
		return null;
	}

	@Override
	protected double getSpinRate() {
		return 0;
	}

	@Override
	protected Vector2D getCOR() {
		return null;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		end();
	}
}

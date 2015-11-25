package org.usfirst.frc.team246.robot.commands;

import org.usfirst.frc.team246.robot.overclockedLibraries.Vector2D;

/**
 *@author Jacob Nazarenko
 */
public class AutoDrive extends FieldCentricDrivingCommand {

    public AutoDrive(Vector2D crabVector, double heading) {
    }

	@Override
	protected Vector2D getCrabVector() {
		return null;
	}

	@Override
	protected double getSpinRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected Vector2D getCOR() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

    
}

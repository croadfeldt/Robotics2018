package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.Robot;
import org.usfirst.frc.team6758.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auton extends Command {
	
	static boolean flag;
	double encLeft, encRight, distance;
	
    public Auton() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	flag = false;
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	encLeft = Robot.driveTrain.encLeft.getRaw();
    	encRight = Robot.driveTrain.encRight.getRaw();
    	distance = (encLeft + encRight) / 2;
    	
    	System.out.println(distance);
    	
    	if(distance < 39500) {
    	Robot.driveTrain.driveForward(RobotMap.defaultAutonSpeed);
    	}
    	else {
    		flag = true;
    		Robot.driveTrain.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return flag;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.resetDistance();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

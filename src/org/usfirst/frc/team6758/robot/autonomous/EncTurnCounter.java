package org.usfirst.frc.team6758.robot.autonomous;

import org.usfirst.frc.team6758.robot.Robot;
import org.usfirst.frc.team6758.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EncTurnCounter extends Command {

	private double /*time = -1 ,*/ pulses = -1;
	private boolean flag, rightFlag, leftFlag;
	private int encLeft, encRight;
	
    public EncTurnCounter(double measurement) {
        // Use requires() here to declare subsystem dependencies
        /*if(degree)*/ pulses = measurement * RobotMap.PPD;
        /*else time = measurement;*/
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	/*if(time != -1) setTimeout(time);
    	else*/ flag = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*if(time != -1)Robot.driveTrain.driveCounter(RobotMap.defaultAutonSpeed);
    	else if(pulses != -1) {*/
    		encLeft = -Robot.driveTrain.encLeft.getRaw();
    		encRight = Robot.driveTrain.encRight.getRaw();
    		
    		if(Math.abs(encLeft) < pulses) Robot.driveTrain.left.set(-RobotMap.defaultAutonSpeed);
    		else {
    			Robot.driveTrain.left.set(0);
    			rightFlag = true;
    		}
    		
    		if(Math.abs(encRight) < pulses) Robot.driveTrain.right.set(RobotMap.defaultAutonSpeed);
    		else {
    			Robot.driveTrain.right.set(0);
    			leftFlag = true;
    		}
    		
    		if(leftFlag && rightFlag) {
    			flag = true;
    			Robot.driveTrain.stop();
    		}
    		
    	/*}
    	else Robot.driveTrain.stop();*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	/*if(time != -1) return isTimedOut();
    	else*/ return flag;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

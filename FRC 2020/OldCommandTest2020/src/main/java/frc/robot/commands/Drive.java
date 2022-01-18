/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * An example command.  You can replace me with your own command.
 */
public class Drive extends Command {
  public Drive() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.DriveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double leftTrigger = Robot.m_oi.getDriver1RawAxis(RobotMap.LEFT_TRIGGER);
    double rightTrigger = Robot.m_oi.getDriver1RawAxis(RobotMap.RIGHT_TRIGGER);

    
    double leftStickY = Robot.m_oi.getDriver1RawAxis(RobotMap.LEFT_STICK_Y);
    double rightStickX = Robot.m_oi.getDriver1RawAxis(RobotMap.RIGHT_STICK_X);

    Robot.DriveTrain.setLeftMotors(leftTrigger*leftTrigger);
    Robot.DriveTrain.setRightMotors(leftTrigger*leftTrigger);


    Robot.DriveTrain.setRightMotors(rightStickX*rightStickX);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.DriveTrain.setRightMotors(0);
    Robot.DriveTrain.setLeftMotors(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.end();
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Intake extends Command {
  public Intake() {
    // Use requires() here to declare subsystem dependencies
     requires(Robot.IntakeSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  
    boolean INTAKE_TOGGLE_C1 = driver1Controller.getRawButton(RobotMap.LEFT_BUMPER);
  boolean INTAKE_TOGGLE_C2 = driver1Controller.getRawButton(RobotMap.LEFT_BUMPER);

    
    
    IntakeToggle();

        if(TOGGLE_INTAKE)
        {
          intake.set(FULL_SEND);
        }
        else{
            intake.set(NO_SEND);
        }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}

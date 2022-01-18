 /*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.manualDrive;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
 
  // instantiate new motor controller objects
  public Spark leftMaster = new Spark(RobotMap.leftMaster);
  public Spark leftSlave = new Spark(RobotMap.leftslave);
  public Spark rightMaster = new Spark(RobotMap.rightMaster);
  public Spark rightSlave = new Spark(RobotMap.rightslave);
  
  SpeedControllerGroup leftControllerGroup = new SpeedControllerGroup(leftMaster, leftSlave);
  SpeedControllerGroup rightControllerGroup = new SpeedControllerGroup(rightMaster, rightSlave);

  // instantiate a new DifferentialDrive object and assign motor controllers to differential drive
  public DifferentialDrive drive = new DifferentialDrive(leftControllerGroup, rightControllerGroup);

  // create constructor function
 
	
  // add manualDrive() method
  public void manualDrive(){
  
    drive.arcadeDrive(move, turn);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

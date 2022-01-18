/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {

  CANSparkMax leftFront = new CANSparkMax(Robotmap.leftFront, MotorType.kBrushless);
  CANSparkMax leftRear = new CANSparkMax(RobotMap.leftRear, MotorType.kBrushless);
  CANSparkMax rightRear = new CANSparkMax(RobotMap.rightRear, MotorType.kBrushless);
  CANSparkMax rightFront = new CANSparkMax(RobotMap.rightFront, MotorType.kBrushless);

  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftFront, leftRear);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightFront, rightRear);

  DifferentialDrive drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);
  
  
  public DriveTrain() {

  }
  public void manualDrive(double Move, double Turn){

    Drive.arcadeDrive(Move,Turn);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

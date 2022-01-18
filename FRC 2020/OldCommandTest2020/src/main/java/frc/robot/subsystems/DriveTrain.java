/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Drive;

/**
 * An example subsystem. You can replace with me with your own subsystem.
 */
public class DriveTrain extends Subsystem {
  
  private CANSparkMax leftFront = new CANSparkMax(RobotMap.leftFront, MotorType.kBrushless);
  private CANSparkMax leftRear = new CANSparkMax(RobotMap.leftRear, MotorType.kBrushless);
  private CANSparkMax rightFront = new CANSparkMax(RobotMap.rightFront, MotorType.kBrushless);
  private CANSparkMax rightRear = new CANSparkMax(RobotMap.rightRear, MotorType.kBrushless);

  @Override
  protected void initDefaultCommand() {

    // Set the default command for a subsystem here.
    setDefaultCommand(new Drive());
  }


  public void setLeftMotors(double speed){
    leftFront.set(-1);
    leftRear.set(-1);
  }

  public void setRightMotors(double turn){
    rightFront.set(1);
    rightRear.set(1);
  }
  
}

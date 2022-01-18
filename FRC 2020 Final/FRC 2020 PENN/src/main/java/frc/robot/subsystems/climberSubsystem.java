/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class climberSubsystem extends SubsystemBase {
  private final VictorSPX cascade = new VictorSPX(Constants.CASCADE_SPX);
  private final CANSparkMax winch = new CANSparkMax(Constants.WINCH_CSM, MotorType.kBrushless);
  /**
   * Creates a new ExampleSubsystem.
   */
  public climberSubsystem() {
    winch.setIdleMode(IdleMode.kBrake);
  }

  public void liftCascade(double power){
    cascade.set(ControlMode.PercentOutput,power*.5);
  }

  public void lowerCascade(double power){
    cascade.set(ControlMode.PercentOutput,-power*.75);
  }

  public void cascadeStop(){
    cascade.set(ControlMode.PercentOutput, 0);
  }
  public void winchUp(double power){
    winch.set(power);
  }

  public void winchDown(double power){
    winch.set(-power);
  }
// This is a comment 

  public void winchStop(){
    winch.set(Constants.STOP);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

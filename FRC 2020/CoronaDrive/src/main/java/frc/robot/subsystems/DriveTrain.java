/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  private final VictorSPX leftFrontDrive = new VictorSPX(Constants.leftFrontDrive);
  private final VictorSPX leftRearDrive = new VictorSPX(Constants.leftRearDrive);
  private final VictorSPX rightFrontDrive = new VictorSPX(Constants.rightFrontDrive);
  private final VictorSPX rightRearDrive = new VictorSPX(Constants.rightRearDrive);
  private final VictorSPX leftFrontRotate = new VictorSPX(Constants.leftFrontRotate);
  private final VictorSPX leftRearRotate = new VictorSPX(Constants.leftRearRotate);
  private final VictorSPX rightFrontRotate = new VictorSPX(Constants.rightFrontRotate);
  private final VictorSPX rightRearRotate = new VictorSPX(Constants.rightRearRotate);
  
   Encoder  leftFrontEncoder; 
   Encoder leftRearEncoder;
   Encoder rightFrontEncoder; 
   Encoder rightRearEncoder;



  public static Joystick joystick = new Joystick(0);
  public static XboxController controller = new XboxController(0);


  public DriveTrain() {

    leftRearDrive.follow(leftFrontDrive);
    rightRearDrive.follow(leftFrontDrive);
    rightFrontDrive.follow(leftFrontDrive);

    leftRearRotate.follow(leftFrontRotate);
    rightFrontRotate.follow(leftFrontRotate);
    rightRearRotate.follow(leftFrontRotate);

    

    leftFrontEncoder = new Encoder(1, 0);
    leftRearEncoder = new Encoder(4, 5);
    rightFrontEncoder = new Encoder(2, 3);
    rightRearEncoder = new Encoder(6, 7);
    
  }

  public void m_drive(double move,   double rotate) {


    move = applyDeadband(move, .05);
    rotate = applyDeadband(rotate, .05);
   
    
     leftFrontDrive.set(ControlMode.PercentOutput, move);
     leftFrontRotate.set(ControlMode.PercentOutput, rotate);
  }

  public void swerveDrive(double foward, double left, double backwards, double right ){

  

  }

  private double applyDeadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  public void rotate(double power){
    leftFrontRotate.set(ControlMode.PercentOutput, power);
  }

  public void drive(double power){
    leftFrontDrive.set(ControlMode.PercentOutput, power);
  }

  public void resetEncoders(){
    leftFrontEncoder.reset();
    leftRearEncoder.reset();
    rightFrontEncoder.reset();
    rightRearEncoder.reset();
  }

  public double getRotationEncoderDistance(){
      return (leftFrontEncoder.getDistance());
  }

  public void stopRotation(){
    leftFrontDrive.set(ControlMode.PercentOutput, 0);

  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
   SmartDashboard.putNumber("left Front Encoder", leftFrontEncoder.get());
   SmartDashboard.putNumber("left Rear Encoder", leftRearEncoder.get());
   SmartDashboard.putNumber("right Front Encoder", rightFrontEncoder.get());
   SmartDashboard.putNumber("Right Rear Encoder", rightRearEncoder.get());

   double dist = leftFrontEncoder.getDistance();
    SmartDashboard.putNumber("Encoder", dist);
  }
}

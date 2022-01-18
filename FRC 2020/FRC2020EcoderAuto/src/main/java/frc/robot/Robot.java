/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANEncoder;

import frc.robot.Drive;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  CANSparkMax leftFront = new CANSparkMax(10, MotorType.kBrushless);
  CANSparkMax leftRear = new CANSparkMax(11, MotorType.kBrushless);
  CANSparkMax rightRear = new CANSparkMax(21, MotorType.kBrushless);
  CANSparkMax rightFront = new CANSparkMax(20, MotorType.kBrushless);

  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftFront, leftRear);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightFront, rightRear);

//  DifferentialDrive Drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

  XboxController controller = new XboxController(0);
  

  //Shooter
  VictorSPX shooterTop = new VictorSPX(22);
  VictorSPX shooterBottom = new VictorSPX(51);
  VictorSPX shootAngle = new VictorSPX(2);
  CANSparkMax shoot = new CANSparkMax(41, MotorType.kBrushless);

  //Intake
  VictorSPX intake = new VictorSPX(4);

 



  private final double kDriveTicks2Feet = 1/42*1/8*6*Math.PI/12;

  Timer timer = new Timer();
  
  


  @Override
  public void robotInit() {

   //int Encoders
     
     
    // Set Encoders to 0
     

  }

  @Override
  public void autonomousInit() {

    timer.reset();
    

      
  }

  @Override
  public void autonomousPeriodic() {
    
    timer.start();

    

   
    if(timer.get()<3 )
    {
        shooterBottom.set(ControlMode.PercentOutput, 1);
        shooterTop.set(ControlMode.PercentOutput, 1);
    }
    else if(timer.get()< 8)
    {
      shooterTop.set(ControlMode.PercentOutput, 0);
      shooterBottom.set(ControlMode.PercentOutput, 0);
    }
    else
    {
      shooterTop.set(ControlMode.PercentOutput, 0);
      shooterBottom.set(ControlMode.PercentOutput, 0);
    }
    
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {

    
    
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void robotPeriodic() {
   
  }
  
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  CANSparkMax leftFront = new CANSparkMax(RobotMap.leftFront, MotorType.kBrushless);
  CANSparkMax leftRear = new CANSparkMax(RobotMap.leftRear, MotorType.kBrushless);
  CANSparkMax rightRear = new CANSparkMax(RobotMap.rightRear, MotorType.kBrushless);
  CANSparkMax rightFront = new CANSparkMax(RobotMap.rightFront, MotorType.kBrushless);

  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftFront, leftRear);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightFront, rightRear);

  DifferentialDrive Drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

  CANEncoder encoderLeft = new CANEncoder(leftFront);
  CANEncoder encoderRight = new CANEncoder(rightFront);

  public static final double wheelDiameter = 6;
	public static final double pulsePerRevolution = 360;

  public ADXRS450_Gyro m_gyro = new ADXRS450_Gyro(kGyroPort);
  private static final double kAngleSetpoint = 0.0;
  private static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;
  private static final double kP = 0.005; // propotional turning constant

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

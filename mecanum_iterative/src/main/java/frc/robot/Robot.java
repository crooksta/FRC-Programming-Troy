/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.MecanumDrive;

/**
 * This is a demo program showing how to use Mecanum control with the RobotDrive
 * class.
 */
public class Robot extends TimedRobot {
 
  CANSparkMax leftFront = new CANSparkMax(3, MotorType.kBrushless);
  CANSparkMax leftRear = new CANSparkMax(4, MotorType.kBrushless);
  CANSparkMax rightRear = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax rightFront = new CANSparkMax(5, MotorType.kBrushless);


  private MecanumDrive m_robotDrive;
  XboxController xbox0 = new XboxController(0);

  @Override
  public void robotInit() {
    

    // Invert the left side motors.
    // You may need to change or remove this to match your robot.
   rightFront.setInverted(true);
   //rightRear.setInverted(true);
   //leftFront.setInverted(true);
   leftRear.setInverted(true);

    m_robotDrive = new MecanumDrive(leftFront, leftRear, rightFront, rightRear);

    
  }

  @Override
  public void teleopPeriodic() {
    // Use the joystick X axis for lateral movement, Y axis for forward
    // movement, and Z axis for rotation.
    m_robotDrive.driveCartesian(-.3*xbox0.getY(Hand.kLeft),.3*xbox0.getX(Hand.kLeft), -.5*xbox0.getX(Hand.kRight));
   // m_robotDrive.drivePolar(-.5*xbox0.getY(Hand.kLeft),.5*xbox0.getX(Hand.kLeft), -.5*xbox0.getX(Hand.kRight));
  }
}

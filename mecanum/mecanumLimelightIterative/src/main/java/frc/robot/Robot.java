/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  double HALF_SPEED = .5;
  CANSparkMax frontLeftMotor = new CANSparkMax(RobotMap.leftFront, MotorType.kBrushless);
  CANSparkMax rearLeftMotor = new CANSparkMax(RobotMap.leftRear, MotorType.kBrushless);
  CANSparkMax frontRightMotor = new CANSparkMax(RobotMap.rightFront, MotorType.kBrushless);
  CANSparkMax rearRightMotor = new CANSparkMax(RobotMap.rightRear, MotorType.kBrushless);

  MecanumDrive m_drive = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

  

  XboxController driver0 = new XboxController(RobotMap.XBOX_CONTROLLER_PORT_0);
  Joystick joystick0 = new Joystick(0);

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

     frontLeftMotor.setInverted(true);
     rearLeftMotor.setInverted(true);
     //frontRightMotor.setInverted(true);
     //rearRightMotor.setInverted(true);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    //xbox controller drive
   // m_drive.driveCartesian(
    //HALF_SPEED*driver0.getRawAxis(RobotMap.RIGHT_Y_AXIS), //forward
   // HALF_SPEED*driver0.getRawAxis(RobotMap.RIGHT_X_AXIS), //rotation
   // HALF_SPEED*driver0.getRawAxis(RobotMap.LEFT_X_AXIS));  //strafe


    //joystick drive
    //m_drive.driveCartesian(HALF_SPEED*joystick0.getX(), HALF_SPEED*joystick0.getY(), HALF_SPEED*joystick0.getZ());


    double r = Math.hypot(driver0.getRawAxis(RobotMap.LEFT_Y_AXIS), driver0.getRawAxis(RobotMap.LEFT_X_AXIS));
            double robotAngle = Math.atan2(driver0.getRawAxis(RobotMap.LEFT_Y_AXIS), driver0.getRawAxis(RobotMap.LEFT_X_AXIS)) - Math.PI / 4;
            double leftX = -driver0.getRawAxis(RobotMap.RIGHT_Y_AXIS);
            final double v1 = r * Math.sin(robotAngle) + leftX;
            final double v2 = r * Math.cos(robotAngle) - leftX;
            final double v3 = r * Math.cos(robotAngle) + leftX;
            final double v4 = r * Math.sin(robotAngle) - leftX;

            frontLeftMotor.set(v1 * 1);
            rearLeftMotor.set(v2 * 1);
            frontRightMotor.set(v3 * 1);
            rearRightMotor.set(v4 * 1);

            if (Math.abs(joystijoystick0) < 0.10) {				
              joystick = 0;
            }
           // if (Math.abs(atan2) < 0.10) {
            //  atan2 = 0;
           // }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}

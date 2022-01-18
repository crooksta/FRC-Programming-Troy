/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
 
  // Naming Motor controllors
  CANSparkMax leftFront = new CANSparkMax(RobotMap.leftFront, MotorType.kBrushless);
  CANSparkMax leftRear = new CANSparkMax(RobotMap.leftRear, MotorType.kBrushless);
  CANSparkMax rightFront = new CANSparkMax(RobotMap.rightFront, MotorType.kBrushless);
  CANSparkMax rightRear = new CANSparkMax(RobotMap.rightRear, MotorType.kBrushless);

  // Grouping motors 
  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftFront, leftRear);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightFront, rightRear);

  // Making motors into a drive system
  DifferentialDrive drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

  // USB Imputs 
  XboxController controller = new XboxController(RobotMap.controllor);
  //Joystick joystick = new Joystick(RobotMap.joystick);

  Timer timer = new Timer();

  //Limelight

  // distance in inches the robot wants to stay from an object
  private static final double kHoldDistance = 120.0;

  // maximum distance in inches we expect the robot to see
  private static final double kMaxDistance = 200.0;

  // factor to convert sensor values to a distance in inches
  private static final double kValueToInches = 0.125;

  // proportional speed constant
  private static final double kP = 7.0;

  // integral speed constant
  private static final double kI = 0.018;

  // derivative speed constant
  private static final double kD = 1.5;
  private static final double kAngleSetpoint = 0.0;
  private static final double kPTurn = 0.005; //proportional turning constant

  // sensors
  private static final int kGyroPort = 0;
  private static final int kUltrasonicPort = 0;

  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;
  private final AnalogInput m_ultrasonic = new AnalogInput(kUltrasonicPort);

  
  public final AnalogGyro m_gyro = new AnalogGyro(kGyroPort); 

  

  private final PIDController m_pidController
      = new PIDController(kP, kI, kD, m_ultrasonic, new MyPidOutput());


  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
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
    timer.reset();
    timer.start();
  }


  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
     // Drive for 2 seconds
     if (timer.get() < 2.0) {
      drive.arcadeDrive(0.5, 0.0); // drive forwards half speed
  } else {
      drive.stopMotor(); // stop robot
  }
   
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    drive.tankDrive(controller.getRawAxis(1), controller.getRawAxis(5));
    //drive.arcadeDrive(controller.getRawAxis(1), controller.getRawAxis(5));

     // Set expected range to 0-24 inches; e.g. at 24 inches from object go
        // full forward, at 0 inches from object go full backward.
        m_pidController.setInputRange(0, kMaxDistance * kValueToInches);
        // Set setpoint of the pid controller
        m_pidController.setSetpoint(kHoldDistance * kValueToInches);
        m_pidController.enable(); // begin PID control
        double turningValue = (kAngleSetpoint - m_gyro.getAngle()) * kPTurn;
        // Invert the direction of the turn if we are going backwards
        turningValue = Math.copySign(turningValue, controller.getY());
        drive.tankDrive(controller.getY(Hand.kLeft), turningValue);
        // m_Drive.ArcadeDrive(m_Controller.getY(), turningValue);
        Update_Limelight_Tracking();

        double left = controller.getY(Hand.kLeft);
        double right = -controller.getY(Hand.kRight);
        double drive = controller.getY(Hand.kLeft); //this will only be used for arcadeDrive
        double steer = -controller.getX(Hand.kLeft);//this will only be used for arcadeDrive
        boolean auto = controller.getAButton();

        left *= 0.70;
        right *= 0.70;
        drive *= 0.70; //this will only be used for arcadeDrive
        steer *= 0.70; //this will only be used for arcadeDrive

        /*if (auto)
        {
          if (m_LimelightHasValidTarget)
          {
                drive.tankDrive(m_LimelightDriveCommand,m_LimelightSteerCommand);
                //drive.arcadeDrive(m_LimelightDriveCommand,m_LimelightSteerCommand); //this will only be used for arcadeDrive
          }
          else
          {
                drive.tankDrive(0.0,0.0);
                //drive.arcadeDrive(0.0,0.0); //this will only be used for arcadeDrive

          }
        }
        else
        {
          drive.tankDrive(left,right);
          //drive.arcadeDrive(drive,steer); //this will only be used for arcadeDrive

        }*/
  }
  public void Update_Limelight_Tracking()
  {
        // These numbers must be tuned for your Robot!  Be careful!
        final double STEER_K = 0.03;                    // how hard to turn toward the target
        final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
        final double DESIRED_TARGET_AREA = 13.0;        // Area of the target when the robot reaches the wall
        final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast

        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

        if (tv < 1.0)
        {
          m_LimelightHasValidTarget = false;
          m_LimelightDriveCommand = 0.0;
          m_LimelightSteerCommand = 0.0;
          return;
        }

        m_LimelightHasValidTarget = true;

        // Start with proportional steering
        double steer_cmd = tx * STEER_K;
        m_LimelightSteerCommand = steer_cmd;

        // try to drive forward until the target area reaches our desired area
        double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

        // don't let the robot drive too fast into the goal
        if (drive_cmd > MAX_DRIVE)
        {
          drive_cmd = MAX_DRIVE;
        }
        m_LimelightDriveCommand = drive_cmd;
  }
  private class MyPidOutput implements PIDOutput {
    @Override
    public void pidWrite(double output) {
      drive.arcadeDrive(output, 0);
    }
  }


  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    teleopPeriodic(); 
  }
}

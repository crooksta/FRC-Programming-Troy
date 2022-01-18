/*
 * This program uses CAN Spark Max and Neo motors connected in speed controller groups
 * to drive with joysticks, but once a button on the XBOX controller,A in this case, is held,
 * the robot will turn to find the target for the limelight. Once it finds the target, the robot 
 * will hold it's position/angle.  You can drive straight from that.  THis code also uses the
 * gyro to drive straight forward. Note, I have modified the code for TankDrive instead of arcade
 * drive, both for the limeLight and the driving Straight. I am not sure if this will work. Another option
 * for us is to look at arcade drive, which uses a single joystick for moving forward, backward, and rotation.
 * This is somethign we need to experiment with for our drivers and get them to figure out which is 
 * preferable.  I will put the ArcadeDrive commands back in place, but I will comment them out.
 */

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  // distance in inches the robot wants to stay from an object
  private static final double kHoldDistance = 12.0;

  // maximum distance in inches we expect the robot to see
  private static final double kMaxDistance = 24.0;

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

   // gyro calibration constant, may need to be adjusted;
  // gyro value of 360 is set to correspond to one full revolution
  private static final double kVoltsPerDegreePerSecond = 0.0128;

  private static final int leftFrontMotorPort = 3;
  private static final int leftRearMotorPort = 4;

  private static final int rightFrontMotorPort = 2;
  private static final int rightRearMotorPort = 5;



  private static final int kGyroPort = 0;
  private static final int kUltrasonicPort = 0;

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  private CANSparkMax m_Left0 = new CANSparkMax(leftFrontMotorPort, MotorType.kBrushless);
  private CANSparkMax m_Left1 = new CANSparkMax(leftRearMotorPort, MotorType.kBrushless);
  private CANSparkMax m_Right0 = new CANSparkMax(rightFrontMotorPort, MotorType.kBrushless);
  private CANSparkMax m_Right1 = new CANSparkMax(rightRearMotorPort, MotorType.kBrushless);


  private SpeedControllerGroup m_LeftMotors = new SpeedControllerGroup(m_Left0,m_Left1);
  private SpeedControllerGroup m_RightMotors = new SpeedControllerGroup(m_Right0,m_Right1);
  private DifferentialDrive m_Drive = new DifferentialDrive(m_LeftMotors,m_RightMotors);

  private XboxController m_Controller = new XboxController(0);

  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;
  private final AnalogInput m_ultrasonic = new AnalogInput(kUltrasonicPort);


  private final PIDController m_pidController
      = new PIDController(kP, kI, kD, m_ultrasonic, new MyPidOutput());

  public final AnalogGyro m_gyro = new AnalogGyro(kGyroPort);

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
        m_gyro.setSensitivity(kVoltsPerDegreePerSecond);

        m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
        m_chooser.addOption("My Auto", kCustomAuto);
        SmartDashboard.putData("Auto choices", m_chooser);
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
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    teleopPeriodic();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
        // Set expected range to 0-24 inches; e.g. at 24 inches from object go
        // full forward, at 0 inches from object go full backward.
        m_pidController.setInputRange(0, kMaxDistance * kValueToInches);
        // Set setpoint of the pid controller
        m_pidController.setSetpoint(kHoldDistance * kValueToInches);
        m_pidController.enable(); // begin PID control
        double turningValue = (kAngleSetpoint - m_gyro.getAngle()) * kPTurn;
        // Invert the direction of the turn if we are going backwards
        turningValue = Math.copySign(turningValue, m_Controller.getY());
        m_Drive.tankDrive(m_Controller.getY(Hand.kLeft), turningValue);
        // m_Drive.ArcadeDrive(m_Controller.getY(), turningValue);
        Update_Limelight_Tracking();

        double left = m_Controller.getY(Hand.kLeft);
        double right = -m_Controller.getY(Hand.kRight);
        double drive = m_Controller.getY(Hand.kLeft); //this will only be used for arcadeDrive
        double steer = -m_Controller.getX(Hand.kLeft);//this will only be used for arcadeDrive
        boolean auto = m_Controller.getAButton();

        left *= 0.70;
        right *= 0.70;
        drive *= 0.70; //this will only be used for arcadeDrive
        steer *= 0.70; //this will only be used for arcadeDrive

        if (auto)
        {
          if (m_LimelightHasValidTarget)
          {
                m_Drive.tankDrive(m_LimelightDriveCommand,m_LimelightSteerCommand);
                //m_Drive.arcadeDrive(m_LimelightDriveCommand,m_LimelightSteerCommand); //this will only be used for arcadeDrive
          }
          else
          {
                m_Drive.tankDrive(0.0,0.0);
                //m_Drive.arcadeDrive(0.0,0.0); //this will only be used for arcadeDrive

          }
        }
        else
        {
          m_Drive.tankDrive(left,right);
          //m_Drive.arcadeDrive(drive,steer); //this will only be used for arcadeDrive

        }
  }

  @Override
  public void testPeriodic() {
  }

  /**
   * This function implements a simple method of generating driving and steering commands
   * based on the tracking data from a limelight camera.
   */
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
      m_Drive.arcadeDrive(output, 0);
    }
  }
}
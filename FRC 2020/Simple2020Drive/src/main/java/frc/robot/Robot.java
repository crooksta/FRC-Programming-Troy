/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {


  CANSparkMax leftFront = new CANSparkMax(11, MotorType.kBrushless);
  CANSparkMax leftRear = new CANSparkMax(21, MotorType.kBrushless);
  CANSparkMax rightFront = new CANSparkMax(31, MotorType.kBrushless);
  CANSparkMax rightRear = new CANSparkMax(41, MotorType.kBrushless);


  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftFront, leftRear);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightFront, rightRear);

  
  DifferentialDrive arcadeDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

  XboxController controller = new XboxController(0);
  XboxController controller2 = new XboxController(1);

  
  Timer timer = new Timer(); 

  // Manipulators 
 
      //Shooter
  VictorSPX shooterTop = new VictorSPX(12); // Lead
  VictorSPX shooterBottom = new VictorSPX(13); // Follower
  VictorSPX lifter = new VictorSPX(22);

      //Intake
  //VictorSPX intake = new VictorSPX(2);
  //CANSparkMax trigger = new CANSparkMax(31, MotorType.kBrushless);
 // VictorSPX intake = new VictorSPX(23);
  CANSparkMax intake = new CANSparkMax(23, MotorType.kBrushless);
      //lift
  //VictorSPX liftUp = new VictorSPX(4);
  CANSparkMax Winch = new CANSparkMax(51, MotorType.kBrushless);


  //LimeLight
    
  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;

  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
   
   // shooterBottom.follow(shooterTop);
    shooterBottom.setInverted(true);

    //ShuffleBoard
    SmartDashboard.putNumber("Joystick X value", controller.getX());
    SmartDashboard.putNumber("RPM", leftFront.getAppliedOutput());
    //leftMotorGroup.setInverted(true);
    //rightMotorGroup.setInverted(true);
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
    if (timer.get() < 3.0) {
      arcadeDrive.arcadeDrive( -0.5, 0.0); // drive forwards half speed
    }
    else if(timer.get()<4.0)
    {
      shooterTop.set(ControlMode.PercentOutput, .5);
      shooterBottom.set(ControlMode.PercentOutput, 1);
    }
    else if (timer.get()> 15.0)
  {
    shooterBottom.set(ControlMode.PercentOutput, 0);
    shooterTop.set(ControlMode.PercentOutput, 0);
  }
    
  
  
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {

    
  // Shooter
    if(controller.getBumper(Hand.kLeft))
    {
    shooterTop.set(ControlMode.PercentOutput, -1);
    shooterBottom.set(ControlMode.PercentOutput, -1);
    } 

    else
    {
      shooterTop.set(ControlMode.PercentOutput, 0);
      shooterBottom.set(ControlMode.PercentOutput, 0);
    }

    if(controller.getBButton())
  {
      lifter.set(ControlMode.PercentOutput, .5);
  }
    else if(controller.getXButton())
    {
      lifter.set(ControlMode.PercentOutput,- .5);
    }
    else
    {
      lifter.set(ControlMode.PercentOutput, 0);
    }
  //Intake
  
    //Intake in

    if(controller.getBumper(Hand.kRight))
    {
      intake.set( 1);
    }
    else 
    {
      intake.set( 0);
    }
    if(controller.getAButton())
    {
      Winch.set(.25);
    }
    else if(controller.getYButton())
    {
      Winch.set(-.25);
    }
    else
    {
      Winch.set(0);
    }
    //drive.tankDrive(controller.getRawAxis(1) , controller.getRawAxis(5));
    arcadeDrive.arcadeDrive(controller.getRawAxis(1), controller.getRawAxis(4));

    //limelight 
  /*  Update_Limelight_Tracking();

        double steer = controller.getX(Hand.kRight);
        double drive = -controller.getY(Hand.kLeft);
        boolean auto = controller.getAButton();

        steer *= 0.70;
        drive *= 0.70;


        float KpAim = -0.1f;
float KpDistance = -0.1f;
float min_aim_command = 0.05f;




        /*if (auto)
        {
          if(m_LimelightHasValidTarget)
          {

          }



        if (auto)
        {
          if (m_LimelightHasValidTarget)
          {
                arcadeDrive.arcadeDrive(m_LimelightDriveCommand,m_LimelightSteerCommand);
          }
          else
          {
                arcadeDrive.arcadeDrive(0.0,0.0);
          }
        }
        else
        {
              arcadeDrive.arcadeDrive(drive,steer);
        }
      }*/
      

}
  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    teleopPeriodic();
    
  }
  /*
  public void Update_Limelight_Tracking()
  {
        // These numbers must be tuned for your Robot!  Be careful!
        final double STEER_K = 0.03;                    // how hard to turn toward the target
        final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
        final double DESIRED_TARGET_AREA = 5.0;        // Area of the target when the robot reaches the wall
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
  }*/
}

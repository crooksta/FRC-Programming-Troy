/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  

  //Instantiate:

  int STOP = 0;
  int RUN_100 = 1;
  int IT_AINT_GOING_TO_THE_MOON = 0;
  int IT_IS_GOING_TO_THE_MOON = RUN_100;
  int NO_SEND = STOP;
  int FULL_SEND = IT_IS_GOING_TO_THE_MOON;
  double RUN_75 = .75;
  double RUN_HALF = .5;
  double RUN_25 = .25;


  //CONTROLLERS
  

  

  XboxController driver1Controller = new XboxController(RobotMap.XBOX0);
  XboxController driver2Controller = new XboxController(RobotMap.XBOX1);

  //DRIVE WHEELS AND BINDINGS

  CANSparkMax leftFront = new CANSparkMax(RobotMap.DRIVE_LEFT_FRONT_CSM, MotorType.kBrushless);
  CANSparkMax leftRear = new CANSparkMax(RobotMap.DRIVE_LEFT_REAR_CSM, MotorType.kBrushless);

  CANSparkMax rightFront = new CANSparkMax(RobotMap.DRIVE_RIGHT_FRONT_CSM, MotorType.kBrushless);
  CANSparkMax rightRear = new CANSparkMax(RobotMap.DRIVE_RIGHT_REAR_CSM, MotorType.kBrushless);

  SpeedControllerGroup leftControllerGroup = new SpeedControllerGroup(leftFront, leftRear);
  SpeedControllerGroup rightControllerGroup = new SpeedControllerGroup(rightFront, rightRear);

  DifferentialDrive mDifferentialDrive = new DifferentialDrive(leftControllerGroup, rightControllerGroup);

  double DRIVE_FORWARD = driver1Controller.getRawAxis(RobotMap.RIGHT_TRIGGER);
  double DRIVE_REVERSE = driver1Controller.getRawAxis(RobotMap.LEFT_TRIGGER);
  double DRIVE_ROTATE = driver1Controller.getRawAxis(RobotMap.RIGHT_JOYSTICK_X);
  double DRIVE_COMBINED = driver1Controller.getRawAxis(RobotMap.LEFT_JOYSTICK_Y);

  boolean TOGGLE_DRIVE = false;
  boolean TOGGLE_DRIVE_PRESSED = false;

  boolean DRIVE_TOGGLE_C1 = driver1Controller.getRawButton(RobotMap.LEFT_JOYSTICK_BUTTON);

  //INTAKE AND BINDINGS

  
  WPI_VictorSPX intake = new WPI_VictorSPX(RobotMap.INTAKE_SPX);

  boolean TOGGLE_INTAKE = false;
  boolean TOGGLE_INTAKE_PRESSED =  false;



  boolean INTAKE_TOGGLE_C1 = driver1Controller.getRawButton(RobotMap.LEFT_BUMPER);
  boolean INTAKE_TOGGLE_C2 = driver1Controller.getRawButton(RobotMap.LEFT_BUMPER);


  //FEED AND BINDINGS

  boolean TOGGLE_FEED= false;
  boolean TOGGLE_FEED_PRESSED =  false;

  CANSparkMax feed = new CANSparkMax(RobotMap.FEEDER_CSM, MotorType.kBrushless);

  boolean FEED_TOGGLE_C1 = driver1Controller.getRawButton(RobotMap.RIGHT_BUMPER);
  boolean FEED_TOGGLE_C2 = driver2Controller.getRawButton(RobotMap.RIGHT_BUMPER);


  //LAUNCH AND BINDINGS

  boolean TOGGLE_LAUNCH = false;
  boolean TOGGLE_LAUNCH_PRESSED =  false;

  WPI_VictorSPX lowerLaunch = new WPI_VictorSPX(RobotMap.LOWER_LAUNCH_WHEELS_SPX);
  WPI_VictorSPX upperLaunch = new WPI_VictorSPX(RobotMap.UPPER_LAUNCH_WHEELS_SPX);
  WPI_VictorSPX angleControl = new WPI_VictorSPX(RobotMap.ANGLE_SPX);

  boolean LAUNCH_TOGGLE_C1 = driver1Controller.getRawButton(RobotMap.RIGHT_JOYSTICK_BUTTON);
  boolean INCREASE_ANGLE_C1 = driver1Controller.getRawButton(RobotMap.X_BUTTON);
  boolean DECREASE_ANGLE_C1 = driver1Controller.getRawButton(RobotMap.Y_BUTTON);

  boolean LAUNCH_TOGGLE_C2 = driver2Controller.getRawButton(RobotMap.RIGHT_JOYSTICK_BUTTON);
  boolean INCREASE_ANGLE_C2 = driver2Controller.getRawButton(RobotMap.X_BUTTON);
  boolean DECREASE_ANGLE_C2 = driver2Controller.getRawButton(RobotMap.Y_BUTTON);



  //CLIMB AND BINDINGS

  WPI_VictorSPX Cascade = new WPI_VictorSPX(RobotMap.CASCADE_SPX);
  CANSparkMax Winch  = new CANSparkMax(RobotMap.WINCH_CSM, MotorType.kBrushless);

  boolean LIFT_CASCADE_C1 =  driver1Controller.getRawButton(RobotMap.A_BUTTON);
  boolean LIFT_CASCADE_C2 =  driver2Controller.getRawButton(RobotMap.A_BUTTON);

  boolean LOWER_CASCADE_C1= driver1Controller.getRawButton(RobotMap.B_BUTTON);
  boolean LOWER_CASCADE_C2= driver2Controller.getRawButton(RobotMap.B_BUTTON);

  boolean WINCH_UP_C1 = driver1Controller.getRawButton(RobotMap.SELECT_BUTTON);
  boolean WINCH_DOWN_C1 = driver1Controller.getRawButton(RobotMap.START_BUTTON);

  boolean WINCH_UP_C2 = driver2Controller.getRawButton(RobotMap.SELECT_BUTTON);
  boolean WINCH_DOWN_C2 = driver2Controller.getRawButton(RobotMap.START_BUTTON);

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
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
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
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

    //SYSTEMS:
      //Drive
      //Intake
      //Feed
      //Launch
      //Climb


      //Drive
      if(TOGGLE_DRIVE)
      {
        DriveForward();
        DriveReverse();
      }
      else
      {
        DriveCombined();;
      }

      Stop(); // THIS MAY CAUSE PROBLEMS


      //Intake
        IntakeToggle();

        if(TOGGLE_INTAKE)
        {
          intake.set(FULL_SEND);
        }
        else{
            intake.set(NO_SEND);
        }
  


      //Feed
        FeedToggle();

        if(TOGGLE_FEED)
        {
          feed.set(FULL_SEND);
        }
        else{
          feed.set(NO_SEND);
        }


      //Launch
        LaunchToggle();
        SetAngle();

        if(TOGGLE_LAUNCH)
        {
          lowerLaunch.set(IT_IS_GOING_TO_THE_MOON);
          upperLaunch.set(-IT_IS_GOING_TO_THE_MOON);
        }
        else{
          lowerLaunch.set(IT_AINT_GOING_TO_THE_MOON);
          upperLaunch.set(IT_AINT_GOING_TO_THE_MOON);
        }


      //Climb
      CascadeControl();
      WinchControl();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  //CLASSES USED FOR TELEOP

  public void DriveToggle()
  {
    if(DRIVE_TOGGLE_C1)
    {
        if(!TOGGLE_DRIVE_PRESSED)
        {
            TOGGLE_DRIVE = !TOGGLE_DRIVE;
            TOGGLE_DRIVE = true;
        }
      }
    else
    {
      TOGGLE_DRIVE = false;
    }
  }

  public void DriveCombined()
  {
    mDifferentialDrive.arcadeDrive(DRIVE_COMBINED, DRIVE_ROTATE);
  }

  public void DriveForward(){
    mDifferentialDrive.arcadeDrive(DRIVE_FORWARD, DRIVE_ROTATE); 

  }

  public void DriveReverse() {

    mDifferentialDrive.arcadeDrive(DRIVE_REVERSE, DRIVE_ROTATE);
  }
  
  public void Stop()
  {
    if(driver1Controller.getRawAxis(RobotMap.RIGHT_TRIGGER) == 0 && driver1Controller.getRawAxis(RobotMap.LEFT_TRIGGER) == 0)
    {
      leftControllerGroup.set(0);
      rightControllerGroup.set(0);
    }
  }

  

  public void SetAngle()
  {
    if(INCREASE_ANGLE_C1 || INCREASE_ANGLE_C2)
    {
      angleControl.set(RUN_HALF);
    }
    else if(DECREASE_ANGLE_C1 || DECREASE_ANGLE_C2)
    {
      angleControl.set(-RUN_HALF);
    }
    else
    {
      angleControl.set(NO_SEND);
    }

  }

  public void CascadeControl(){
    if(LIFT_CASCADE_C1 || LIFT_CASCADE_C2)
    {
      Cascade.set(RUN_HALF);
    }
    else if(LOWER_CASCADE_C1 || LOWER_CASCADE_C2)
    {
      Cascade.set(-RUN_HALF);
    }
    else
    {
      Cascade.set(0);
    }

  }
  
  

  public void WinchControl()
  {
    if(WINCH_UP_C1 || WINCH_UP_C2)
    {
      Winch.set(FULL_SEND);
    }
    else if(WINCH_DOWN_C1 || WINCH_DOWN_C2)
    {
      Winch.set(-FULL_SEND);
    }
    else
    {
      Winch.set(NO_SEND);
    }

  }

  public void IntakeToggle()
  {
    if(INTAKE_TOGGLE_C1 || INTAKE_TOGGLE_C2)
    {
        if(!TOGGLE_INTAKE_PRESSED)
        {
            TOGGLE_INTAKE = !TOGGLE_INTAKE;
            TOGGLE_INTAKE = true;
        }
      }
    else
    {
      TOGGLE_INTAKE = false;
    }

  }

  public void LaunchToggle(){
    if(LAUNCH_TOGGLE_C1 || LAUNCH_TOGGLE_C2)
    {
        if(!TOGGLE_LAUNCH_PRESSED)
        {
            TOGGLE_LAUNCH = !TOGGLE_LAUNCH;
            TOGGLE_LAUNCH = true;
        }
      }
    else
    {
      TOGGLE_LAUNCH = false;
    }

  }

  public void FeedToggle(){
    if(FEED_TOGGLE_C1 || FEED_TOGGLE_C2)
    {
        if(!TOGGLE_FEED_PRESSED)
        {
            TOGGLE_FEED = !TOGGLE_FEED;
            TOGGLE_FEED = true;
        }
      }
    else
    {
      TOGGLE_FEED = false;
    }

  }

  public void Intake(){
    //deprecated
  }
  public void Feed() {
    //deprecated
  }

  public void Launch()
  {
    //deprecated
  }

  /*
  //TOGGLE CODE

  boolean toggleOn = false;
  boolean togglePressed = false;

    public void teleopPeriodic(){
        updateToggle();

        if(toggleOn){
            // Do something when toggled on
        }else{
            // Do something when toggled off
        }
    }

    public void updateToggle()
    {
        if(joystick.getRawButton(1)){
            if(!togglePressed){
                toggleOn = !toggleOn;
                togglePressed = true;
            }
        }else{
            togglePressed = false;
        }
    }

  */
  
}

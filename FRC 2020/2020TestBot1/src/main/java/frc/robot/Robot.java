/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.function.Function;

import javax.lang.model.util.ElementScanner6;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.AlternateEncoderType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.EncoderType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;


public class Robot extends TimedRobot {
  
  //DriveTrain 

  CANSparkMax leftFront = new CANSparkMax(RobotMap.leftFront, MotorType.kBrushless);
  CANSparkMax leftRear = new CANSparkMax(RobotMap.leftRear, MotorType.kBrushless);
  CANSparkMax rightRear = new CANSparkMax(RobotMap.rightRear, MotorType.kBrushless);
  CANSparkMax rightFront = new CANSparkMax(RobotMap.rightFront, MotorType.kBrushless);

  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftFront, leftRear);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightFront, rightRear);

  DifferentialDrive Drive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

  //Shooter
  VictorSPX shooterTop = new VictorSPX(RobotMap.shooterTop);
  VictorSPX shooterBottom = new VictorSPX(RobotMap.shooterBottom);
  VictorSPX shootAngle = new VictorSPX(RobotMap.shootAngle);
  CANSparkMax shoot = new CANSparkMax(32, MotorType.kBrushless);


  //Intake
  VictorSPX intake = new VictorSPX(RobotMap.intake);

  //lift
  VictorSPX telescope = new VictorSPX(RobotMap.telescope);
  CANSparkMax winch = new CANSparkMax(RobotMap.winch, MotorType.kBrushless);

  CANEncoder encoderLeft = new CANEncoder(leftFront);
  CANEncoder encoderRight = new CANEncoder(rightFront);

  //Wheel of Fortune 
  VictorSPX wheelofFortuneCascade = new VictorSPX(RobotMap.WheelofFortuneCascade);
  VictorSPX wheelofFortuneSpin = new VictorSPX(RobotMap.WheelofFortuneSpin);
  //USB
  XboxController controller = new XboxController(RobotMap.controller);
  XboxController controller2 = new XboxController(1);
  Spark LED = new Spark(5);
  //Analog
  Timer timer = new Timer();
  private static final double kAngleSetpoint = 0.0;
  private static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;
	private static final double kP = 0.005; // propotional turning constant
  
  // gyro calibration constant, may need to be adjusted;
	// gyro value of 360 is set to correspond to one full revolution
	private static final double kVoltsPerDegreePerSecond = 0.0128;
  private ADXRS450_Gyro m_gyro = new ADXRS450_Gyro(kGyroPort);
  

  boolean shooterOn = false;
  boolean shooterToggle = false;
  boolean intakeOn = false;
  boolean intakeToggle = false;

  

  //uint Convertion Converting the encoder value to feet
  
  private final double kDriveTicks2Feet = 1/42*1/8*6*Math.PI/12;
  
   //LimeLight
    
   private boolean m_LimelightHasValidTarget = false;
   private double m_LimelightDriveCommand = 0.0;
   private double m_LimelightSteerCommand = 0.0;

   private CANPIDController pidController;
  public double  kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

float Kp = -0.1f;
float min_command = 0.05f;



  @Override
  public void robotInit() {

    timer.reset();
    encoderRight.getInverted();
    m_gyro.calibrate();
    shooterBottom.setInverted(false);
    shooterTop.setInverted(true);
    shoot.setInverted(true);
    LED.stopMotor();

  
    
    
    //shuffeboard

    //LimeLight
    
   boolean m_LimelightHasValidTarget = false;
   double m_LimelightDriveCommand = 0.0;
   double m_LimelightSteerCommand = 0.0;

    
  }

 
  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Left Encoder ", encoderLeft.getPosition());
    SmartDashboard.putNumber("right Encoder", encoderRight.getPosition());
    SmartDashboard.putNumber("Gyro", m_gyro.getAngle());
  }

  
  @Override
  public void autonomousInit() {
   
    encoderLeft.setPosition(0);
    encoderRight.setPosition(0);
    encoderLeft.getInverted();
    timer.reset();
    m_gyro.calibrate();
  }

  
  @Override
  public void autonomousPeriodic() {
    
    timer.start();
    double leftEncoderPosition = encoderLeft.getPosition();
    double rightEncoderPosition = encoderRight.getPosition();
    double distance = (rightEncoderPosition + leftEncoderPosition)/2 ;
    
    if(timer.get() > 0)
    {

    }

    if(distance < 20)
    {
        Drive.arcadeDrive(-.5, 0);
    }
    else
    {
      Drive.arcadeDrive(0, 0);
    }
   
   
    /* // Drive for 2 seconds
    if (timer.get() < 2.0) {
      Drive.arcadeDrive( -0.5, 0.0); // drive forwards half speed
    }
    else if(timer.get()<3.0)
    {
      shooterTop.set(ControlMode.PercentOutput, .5);
      shooterBottom.set(ControlMode.PercentOutput, 1);
    }
    else if (timer.get()> 15.0)
  {
    shooterBottom.set(ControlMode.PercentOutput, 0);
    shooterTop.set(ControlMode.PercentOutput, 0);
  }
   else if (timer.get()< 8.0)
   {
     shoot.set(.15);
   }
   else if (timer.get() > 15.0)
   {
     shoot.set(0);
   }*/
  }
  
  
  @Override
  public void teleopPeriodic() {

   // Drive.arcadeDrive(controller.getRawAxis(1), controller.getRawAxis(4));
    double left_command = controller.getRawAxis(1);
    double right_command = controller.getRawAxis(4);

    Update_Limelight_Tracking();
    
    LED.set(-.63);


    double encoderOutput = encoderLeft.getPosition(); 
  
    System.out.println(encoderOutput); 
    updateToggle();
    /*
    left trigger =
    right trigger = 
    left bumper = spool
    right bumper = intake 
    left stick = Drive
    rightstick = Drive
    A button = shoot
    B button = angle down
    Y button = 
    x button = angle up
    start button =
    menu button =
    up on the dpad = telescope up 
    down on the dpad = telescope down 
    right on the dpad =
    left on the dpad =


    */
  //intake
      double triggerValue = controller.getTriggerAxis(Hand.kRight); 
      intake.set(ControlMode.PercentOutput, triggerValue);
      double triggerValue2 = controller.getTriggerAxis(Hand.kLeft);
      intake.set(ControlMode.PercentOutput, - triggerValue2);


  //shooter
    if(controller.getBumper(Hand.kLeft))
    {
      shooterTop.set(ControlMode.PercentOutput, 1);
      shooterBottom.set(ControlMode.PercentOutput, 1);
    } 

    else
    {
      shooterTop.set(ControlMode.PercentOutput, 0);
      shooterBottom.set(ControlMode.PercentOutput, 0);
    }

    if(shooterOn){
      shooterTop.set(ControlMode.PercentOutput, 1);
      shooterBottom.set(ControlMode.PercentOutput, 1);
   }
   else{
    shooterTop.set(ControlMode.PercentOutput, 0);
    shooterBottom.set(ControlMode.PercentOutput, 0);
    }

    if(intakeOn){

      intake.set(ControlMode.PercentOutput, -1);
    }
      else{
        intake.set(ControlMode.PercentOutput, 0);

    }


    //shooter test


    
    if(controller2.getAButton())
    {
      shooterTop.set(ControlMode.PercentOutput, 1);
      shooterBottom.set(ControlMode.PercentOutput, 1);
    } 

    else if(controller2.getYButton())
    {
      shooterTop.set(ControlMode.PercentOutput, .50);
      shooterBottom.set(ControlMode.PercentOutput, 1);
    } 
    else if(controller2.getXButton())
    {
      shooterTop.set(ControlMode.PercentOutput, .25);
      shooterBottom.set(ControlMode.PercentOutput, 1);
    } 
    else if(controller2.getBButton())
    {
      shooterTop.set(ControlMode.PercentOutput, .75);
      shooterBottom.set(ControlMode.PercentOutput, 1);
    } 
    else
    {
      shooterTop.set(ControlMode.PercentOutput, 0);
      shooterBottom.set(ControlMode.PercentOutput, 0);
    }

  //shoot / ball release
    if(controller.getAButton())
    {
      shoot.set(.2);
    }
    
    else
    {
      shoot.set(0);
    }


  //changing the angle of the shooter
    if(controller.getXButton())
    {
      shootAngle.set(ControlMode.PercentOutput, .25);
    }
    
    else if(controller.getBButton())
    {
      shootAngle.set(ControlMode.PercentOutput, -.25);
    }

    else
    {
      shootAngle.set(ControlMode.PercentOutput, 0);
    }


    //lifing
    if(controller.getYButton())
    {
      winch.set(.8);
    }
    else if(controller.getStartButton())
    {
      winch.set(-.8);
    }

    else
    {
      winch.set(0);
    }

    if(controller.getPOV() == 0)
    {
      telescope.set(ControlMode.PercentOutput, 1);
    }
    else if(controller.getPOV() ==  180)
    {
      telescope.set(ControlMode.PercentOutput, -1);
    }
    else{
      telescope.set(ControlMode.PercentOutput, 0);
    }

   // Wheel of fortune up and down 
    if(controller2.getPOV()== 0)
    {
      wheelofFortuneCascade.set(ControlMode.PercentOutput, .5);
    }
    else if(controller2.getPOV() == 180)
    {
      wheelofFortuneCascade.set(ControlMode.PercentOutput, -.5);
    }
    else 
    {
      wheelofFortuneCascade.set(ControlMode.PercentOutput, 0);
    }


    // wheel of fortune spin 

    if(controller2.getPOV()== 90)
    {
      wheelofFortuneSpin.set(ControlMode.PercentOutput, .5);
    }
    else if (controller2.getPOV()== 270)
    {
      wheelofFortuneSpin.set(ControlMode.PercentOutput, -.5);
    }
    else 
    {
      wheelofFortuneSpin.set(ControlMode.PercentOutput, 0);
    }

  //Drive
 
  
  
  
  //double turningValue = (kAngleSetpoint - m_gyro.getAngle()) * kP;
		// Invert the direction of the turn if we are going backwards
		//turningValue = Math.copySign(turningValue, controller.getY());
		//Drive.arcadeDrive(controller.getY(), turningValue);
    Update_Limelight_Tracking();


    
        double steer = controller.getX(Hand.kRight);
        double drive = -controller.getY(Hand.kLeft);
        boolean auto = controller2.getBumper(Hand.kRight);

        steer *= 1.0;
        drive *= 1.0;





        

        if (auto)
        {
          if (m_LimelightHasValidTarget)
          {
       
                Drive.arcadeDrive(-drive,m_LimelightSteerCommand);
          }
          else
          {
                Drive.arcadeDrive(0.0,0.2);
          }
        }
        else
        {
              Drive.arcadeDrive(-drive,steer);
        } 
        
            /*
          double Kp1 = -0.1f;
          double min_command1 = 0.05f;
          
          double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
          
          if (controller2.getAButton())
          {
                  double heading_error = tx;
                  double steering_adjust = 0.1f;
                  if (tx > 1.0)
                  {
                          steering_adjust = Kp1*heading_error - min_command1;
                  }
                  else if (tx < 1.0)
                  {
                          steering_adjust = Kp1*heading_error + min_command1;
                  }
                  left_command += steering_adjust;
                  right_command -= steering_adjust;
          } */

  }

 
  public void testPeriodic() {
    LED.set(.99);
    winch.set(0);


    Drive.arcadeDrive(controller.getRawAxis(1), controller.getRawAxis(4));

    if(controller.getAButton()){
      LED.set(.97);
    }

    else if(controller.getBButton())
    {
      LED.set(.93);
     }
    else if(controller.getXButton())
    {
      LED.set(-.21);
      
    }
    

    else if(controller.getYButton())
    {
      winch.set(.8);
      LED.set(-.57);
    }
    else if(controller.getStartButton())
    {
      winch.set(-.8);
    }
    else{
      
    }
    





    //LED.set(.99);
    
    }
    

  

  public void updateToggle()
    {
        if(controller.getBumper(Hand.kRight)){
            if(!shooterToggle){
                shooterOn = !shooterOn;
                shooterToggle = true;
            }
        }else{
            shooterToggle = false;
        }
        if(controller.getBumper(Hand.kLeft)){
          if(!intakeToggle){
              intakeOn = !intakeOn;
              intakeToggle = true;
          }
      }else{
          intakeToggle = false;
      }
    }
    public void Update_Limelight_Tracking()
  {
        // These numbers must be tuned for your Robot!  Be careful!
        final double STEER_K = 0.09;                    // how hard to turn toward the target
        final double DRIVE_K = 0.26;                    // how hard to drive fwd toward the target
        final double DESIRED_TARGET_AREA = 5.0;        // Area of the target when the robot reaches the wall
        final double MAX_DRIVE = 0.7;                   // Simple speed limit so we don't drive too fast
        final double ANGLE_ADJUST_K = 0.2;
        
        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
         
        final double adjust_speed = ty*ANGLE_ADJUST_K;
        if (tv < 0.9)
        {
          m_LimelightHasValidTarget = false;
          m_LimelightDriveCommand = 0.0;
          m_LimelightSteerCommand = 0.0;
          return;
        }

        if(ty > -8)
        {
          shootAngle.set(ControlMode.PercentOutput, -adjust_speed);
        }
        else if (ty < -11)
        {
          shootAngle.set(ControlMode.PercentOutput, adjust_speed);

        }
        else
        {
          shootAngle.set(ControlMode.PercentOutput, 0);

        }

       
       
       /* if(ta > .5)
        {
          shootAngle.set(ControlMode.PercentOutput, -adjust_speed);
        }
        else if (ta < .4)
        {
          shootAngle.set(ControlMode.PercentOutput, adjust_speed);

        }
        else
        {
          shootAngle.set(ControlMode.PercentOutput, 0);

        }
      */

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





  


}

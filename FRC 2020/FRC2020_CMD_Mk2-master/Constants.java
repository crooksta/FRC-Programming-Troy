/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {




  //motor names + channels
  //csm = can spark max
  //spx  = victor spx
  /**
   _______________
   |1X|       |4X|
   |  |       |  |
   |  |       |  |
   |  |_______|  |
   |  |-------|  |
   |  |  5X   |  |
   |2X|_______|3X|
   ---------------
   
   */
  public static int DRIVE_LEFT_FRONT_CSM        = 11;
  public static int DRIVE_LEFT_REAR_CSM         = 21;
  public static int DRIVE_RIGHT_FRONT_CSM       = 41;
  public static int DRIVE_RIGHT_REAR_CSM        = 31;

  public static int UPPER_LAUNCH_WHEELS_SPX     = 12;
  public static int LOWER_LAUNCH_WHEELS_SPX     = 13;

  public static int INTAKE_SPX                  = 23;

  public static int WINCH_CSM                   = 51;
  public static int CASCADE_CSM                 = 22;
  public static int CASCADE_SPX                 = 22;

  public static int FEEDER_CSM                  = 32;
  public static int ANGLE_SPX                   = 42;

  public static int LED                          =5;

  public static int SHIELD_GENERATOR_LIFT       = 43;
  public static int SHIELD_GENERATOR_SPIN      = 44;


  //xbox controller numbers

  public static int XBOX0                       = 0;
  public static int XBOX1                       = 1;

  //XBOX BUTTONS

  public static int   A_BUTTON                  = 1;
  public static int   B_BUTTON                  = 2;
  public static int   X_BUTTON                  = 3;
  public static int   Y_BUTTON                  = 4;
  public static int   LEFT_BUMPER               = 5;
  public static int   RIGHT_BUMPER              = 6;
  public static int   START_BUTTON              = 7;
  public static int   SELECT_BUTTON             = 8;
  public static int   LEFT_JOYSTICK_BUTTON      = 9;
  public static int   RIGHT_JOYSTICK_BUTTON     = 10;

  //XBOX AXES
  public static int   LEFT_JOYSTICK_X           = 0;//AXIS
  public static int   LEFT_JOYSTICK_Y           = 1;//AXIS
  public static int   LEFT_TRIGGER              = 2;
  public static int   RIGHT_TRIGGER             =  3;
  public static int   RIGHT_JOYSTICK_X          = 4;//AXIS
  public static int   RIGHT_JOYSTICK_Y          = 5;//AXIS


  //constants
  public static int STOP = 0;
  public static int FULL_POWER = 1;
  public static double RUN_75 = .75;
  public static double RUN_HALF = .5;
  public static double RUN_25 = .25;
  public static double RUN_VARIABLE = 1.00; //use this anywhere where we may change
  

  public static double encoderTicks = 42;
  public static double wheelDiameter = 6;
  public static double wheelCircumference = wheelDiameter * Math.PI;
  public static double gearReduction = 7;
  public static double KinchesPerRev = (encoderTicks*gearReduction)/wheelCircumference;
  public static double kfootPerRev = KinchesPerRev/12;

  //PID

public static double kP;
public static double kI;
public static double kD;
public static double kIz;
public static double kFF;
public static double kMaxOutput;
public static double kMinOutput;


}

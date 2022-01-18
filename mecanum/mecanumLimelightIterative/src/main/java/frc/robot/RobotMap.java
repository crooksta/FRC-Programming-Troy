/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;


  //drive motors

  public static int leftFront = 11;
  public static int leftRear = 21;
  public static int rightRear = 31;
  public static int rightFront = 41;


  public static int XBOX_CONTROLLER_PORT_0 = 0;
  public static int XBOX_CONTROLLER_PORT_1 = 1;


  
  public static int LEFT_X_AXIS = 0;
  public static int LEFT_Y_AXIS = 1;
  public static int RIGHT_X_AXIS = 4;
  public static int RIGHT_Y_AXIS = 5;

  public static int A_BUTTON = 0;
  public static int B_BUTTON = 1;
  public static int X_BUTTON = 2;
  public static int Y_BUTTON = 3;
  public static int LEFT_TRIGGER = 4;
  public static int RIGHT_TRIGGER = 5;
  public static int SELECT = 6;
  public static int START = 7;
  public static int LEFT_STICK_BUTTON = 8;
  public static int RIGHT_STICK_BUTTON = 9;

  public static int compressor = 0;
  


  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}

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
  
  // Controllers
  public static final int XBOX_CONTROLLER_PORT0 = 0; //Primary Xbox controller
  public static final int XBOX_CONTROLLER_PORT1 = 1; // for second Xbox Controller

  // Axes

  public static final int LEFT_STICK_Y = 1;

  public static final int RIGHT_STICK_Y = 5;

  // Buttons (X Mode)

  public static final int A_BUTTON = 1;

  public static final int B_BUTTON = 2;

  public static final int X_BUTTON = 3;

  public static final int Y_BUTTON = 4;

  public static final int LEFT_BUMPER = 5;

  public static final int RIGHT_BUMPER = 6;

  public static final int BACK_BUTTON = 7;

  public static final int START_BUTTON = 8;

  public static final int LEFT_JOYSTICK_TRIGGER = 9;

  public static final int RIGHT_JOYSTICK_TRIGGER = 10;

  // Drive
  public static final int RIGHT_REAR_WHEEL_PORT = 2; 

  public static final int RIGHT_FRONT_WHEEL_PORT = 5; 

  public static final int LEFT_REAR_WHEEL_PORT = 3;

  public static final int LEFT_FRONT_WHEEL_PORT = 4;

  // Pneumatics
  public static final int COMPRESSOR_MODULE = 0;

  public static final int FRONT_SOLENOID_VALVE = 0;
  public static final int BACK_SOLENOID_VALVE = 1;


  
}

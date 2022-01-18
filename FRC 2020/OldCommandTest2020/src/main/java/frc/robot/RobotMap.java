/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
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

	public static final int leftFront = 11;
	public static final int leftRear = 21;
	public static final int rightRear = 31;
  public static final int rightFront = 41;
  
  public static final int topShooter = 13;
  public static final int bottomShooter = 14;
  public static final int winch = 12;
  public static final int ballRelease = 22;
  public static final int flyUp = 23;
  public static final int intake = 24;
  public static final int shootAngle = 42;
  public static final int driver1 = 0;
  
  public static final int BUTTON_A = 1;
	public static final int BUTTON_B = 2;
	public static final int BUTTON_X = 3;
	public static final int BUTTON_Y = 4;
	public static final int LEFT_BUMPER = 5;
	public static final int RIGHT_BUMPER = 6;
	public static final int BACK_BUTTON = 7;
	public static final int START_BUTTON = 8;
	public static final int LEFT_STICK_BUTTON = 9;
  public static final int RIGHT_STICK_BUTTON = 10;
  
  public static final int DPAD_UP = 0;
  public static final int DPAD_RIGHT = 90;
  public static final int DPAD_DOWN = 180;
  public static final int DPAD_LEFT = 270;
	
	public static final int LEFT_STICK_X = 0;
	public static final int LEFT_STICK_Y = 1;
	public static final int RIGHT_STICK_Y = 5;
	public static final int RIGHT_STICK_X = 4;
	
	public static final int LEFT_TRIGGER = 2;
  public static final int RIGHT_TRIGGER = 3;
  
 public static final int STOP = 0;
 public int RUN_100 = 1;
 public int IT_AINT_GOING_TO_THE_MOON = 0;
 public int IT_IS_GOING_TO_THE_MOON = RUN_100;
 public int NO_SEND = STOP;
 public int FULL_SEND = IT_IS_GOING_TO_THE_MOON;
 public double RUN_75 = .75;
 public double RUN_HALF = .5;
 public double RUN_25 = .25;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}

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
    public static int leftFrontDrive = 10;
    public static int leftFrontRotate = 11;
    public static int leftRearDrive = 20;
    public static int leftRearRotate = 21;
    public static int rightRearDrive = 30;
    public static int rightRearRotate = 31;
    public static int rightFrontDrive = 40;
    public static int rightFrontRotate = 41;


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
}


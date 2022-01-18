/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.airSystem.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  
  XboxController xbox0 = new XboxController(RobotMap.XBOX_CONTROLLER_PORT0);

  JoystickButton A = new JoystickButton(xbox0,RobotMap.A_BUTTON);
  JoystickButton B = new JoystickButton(xbox0,RobotMap.B_BUTTON);
  JoystickButton X = new JoystickButton(xbox0,RobotMap.X_BUTTON);
  JoystickButton Y = new JoystickButton(xbox0,RobotMap.Y_BUTTON);
  JoystickButton LB = new JoystickButton(xbox0,RobotMap.LEFT_BUMPER);
  JoystickButton RB = new JoystickButton(xbox0,RobotMap.RIGHT_BUMPER);
  //JoystickButton RT = new JoystickButton(xbox0,RobotMap.A_BUTTON);
  //JoystickButton LT = new JoystickButton(xbox0,RobotMap.A_BUTTON);
  JoystickButton BACK = new JoystickButton(xbox0,RobotMap.BACK_BUTTON);
  JoystickButton START = new JoystickButton(xbox0,RobotMap.START_BUTTON);
  JoystickButton R3 = new JoystickButton(xbox0,RobotMap.RIGHT_JOYSTICK_TRIGGER);
  JoystickButton L3 = new JoystickButton(xbox0,RobotMap.LEFT_JOYSTICK_TRIGGER);

  //A.whenPressed(new extend());



}

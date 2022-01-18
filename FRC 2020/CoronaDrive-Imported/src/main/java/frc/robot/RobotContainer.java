/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.DriveWithjoysticksCommand;
import frc.robot.commands.EncoderToSetPostion;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final DriveTrain driveTrain = new DriveTrain();

  private final DriveWithjoysticksCommand m_DriveWithjoysticksCommand = new DriveWithjoysticksCommand(driveTrain);
  private final EncoderToSetPostion m_encoderToSetPostion = new EncoderToSetPostion(driveTrain);
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  public static XboxController driver1 =  new XboxController(0);

  JoystickButton aButton1 = new JoystickButton(driver1, Constants.A_BUTTON);
  JoystickButton bButton1 = new JoystickButton(driver1, Constants.B_BUTTON);
  JoystickButton xButton1 = new JoystickButton(driver1, Constants.X_BUTTON);
  JoystickButton yButton1 = new JoystickButton(driver1, Constants.Y_BUTTON);
  JoystickButton startButton1 = new JoystickButton(driver1, Constants.START_BUTTON);
  JoystickButton selectButton1 = new JoystickButton(driver1, Constants.SELECT_BUTTON);
  JoystickButton leftBumper1 = new JoystickButton(driver1, Constants.LEFT_BUMPER);
  JoystickButton rightBumper1 = new JoystickButton(driver1, Constants.RIGHT_BUMPER);
  JoystickButton leftJoystickButton1 = new JoystickButton(driver1, Constants.LEFT_JOYSTICK_BUTTON);
  JoystickButton rightJoystickButton1 = new JoystickButton(driver1, Constants.RIGHT_JOYSTICK_BUTTON);
  POVButton upPOV1 = new POVButton(driver1, 0);
  POVButton downPOV1 = new POVButton(driver1, 180);
  POVButton leftPOV1 = new POVButton(driver1, 270);
  POVButton rightPOV1 = new POVButton(driver1, 90);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    
    driveTrain.setDefaultCommand(m_DriveWithjoysticksCommand);

  
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    driver1.getRawAxis(1);
    
   aButton1.whileHeld(new EncoderToSetPostion(driveTrain));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
  public Command getDefaultDrive()
  {
    return m_DriveWithjoysticksCommand;
  }
}

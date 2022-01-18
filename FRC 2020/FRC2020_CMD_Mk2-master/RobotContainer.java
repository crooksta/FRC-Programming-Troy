/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
//import frc.robot.commands.ExampleCommand;
import frc.robot.commands.climbCommand;
import frc.robot.commands.driveCommand;
import frc.robot.commands.extendCommand;
import frc.robot.commands.intakeCommand;
import frc.robot.commands.feedCommand;
import frc.robot.commands.launchCommand;
import frc.robot.commands.retractCommand;
import frc.robot.subsystems.climberSubsystem;
import frc.robot.subsystems.driveSubsystem;
import frc.robot.subsystems.intakeSubsystem;
import frc.robot.subsystems.launcherSubsystem;
import frc.robot.subsystems.shieldGeneratorSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...


  public final driveSubsystem m_driveSubsystem =  new driveSubsystem();
  public final driveSubsystem m_autonSubsystem =  new driveSubsystem();
  public final intakeSubsystem m_intakeSubsystem = new intakeSubsystem();
  public final launcherSubsystem m_launcherSubsystem = new launcherSubsystem();
  public final climberSubsystem m_climberSubsystem =  new climberSubsystem();
  public final shieldGeneratorSubsystem m_ShieldGeneratorSubsystem = new shieldGeneratorSubsystem();
  private double power;

  public final climbCommand m_climb = new climbCommand(m_climberSubsystem, power);
  public final intakeCommand m_intake = new intakeCommand(m_intakeSubsystem, power);
  public final extendCommand m_extend = new extendCommand(m_climberSubsystem, power);
  public final retractCommand m_retract = new retractCommand(m_climberSubsystem, power);

  public final feedCommand m_feed =  new feedCommand(m_launcherSubsystem, power);

  public final launchCommand m_launch = new launchCommand(m_launcherSubsystem, power);

  public final driveCommand defaultDrive =  new driveCommand(m_driveSubsystem);

  public static XboxController driver1 =  new XboxController(Constants.XBOX0);
  XboxController driver2 = new XboxController(Constants.XBOX1);

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
  JoystickButton upPOV1 = new JoystickButton(driver1, Constants.A_BUTTON);
  JoystickButton downPOV1 = new JoystickButton(driver1, Constants.A_BUTTON);
  JoystickButton leftPOV1 = new JoystickButton(driver1, Constants.A_BUTTON);
  JoystickButton rightPOV1 = new JoystickButton(driver1, Constants.A_BUTTON);

  JoystickButton aButton2 = new JoystickButton(driver2, Constants.A_BUTTON);
  JoystickButton bButton2 = new JoystickButton(driver2, Constants.B_BUTTON);
  JoystickButton xButton2 = new JoystickButton(driver2, Constants.X_BUTTON);
  JoystickButton yButton2 = new JoystickButton(driver2, Constants.Y_BUTTON);
  JoystickButton startButton2 = new JoystickButton(driver2, Constants.START_BUTTON);
  JoystickButton selectButton2 = new JoystickButton(driver2, Constants.SELECT_BUTTON);
  JoystickButton leftBumper2 = new JoystickButton(driver2, Constants.LEFT_BUMPER);
  JoystickButton rightBumper2 = new JoystickButton(driver2, Constants.RIGHT_BUMPER);
  JoystickButton leftJoystickButton2 = new JoystickButton(driver2, Constants.LEFT_JOYSTICK_BUTTON);
  JoystickButton rightJoystickButton2 = new JoystickButton(driver2, Constants.RIGHT_JOYSTICK_BUTTON);
  JoystickButton upPOV2 = new JoystickButton(driver2, Constants.A_BUTTON);
  JoystickButton downPOV2 = new JoystickButton(driver2, Constants.A_BUTTON);
  JoystickButton leftPOV2 = new JoystickButton(driver2, Constants.A_BUTTON);
  JoystickButton rightPOV2 = new JoystickButton(driver2, Constants.A_BUTTON);
  

  



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_driveSubsystem.setDefaultCommand(defaultDrive);

  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings(){

    //Climb
    aButton2.whileHeld(new climbCommand(m_climberSubsystem, Constants.FULL_POWER));
    xButton2.whileHeld(new extendCommand(m_climberSubsystem,Constants.FULL_POWER));
    yButton2.whileHeld(new retractCommand(m_climberSubsystem,Constants.FULL_POWER));


    //intake
    xButton1.toggleWhenPressed(new intakeCommand(m_intakeSubsystem, Constants.FULL_POWER));

    //feed
    //aButton1.toggleWhenPressed(new feedCommand(m_launcherSubsystem, Constants.RUN_75));

    //shoot

    leftBumper1.toggleWhenPressed(new launchCommand(m_launcherSubsystem, Constants.RUN_75));

    //drive

    driver1.getRawAxis(Constants.LEFT_JOYSTICK_Y);



    
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  /*public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }*/

  public Command getDefaultDrive()
  {
    return defaultDrive;
  }
}


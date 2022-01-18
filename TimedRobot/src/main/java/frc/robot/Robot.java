/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

   DoubleSolenoid extend = new DoubleSolenoid(1, 2);
   DoubleSolenoid contract = new DoubleSolenoid(3, 4);
   double startime;
   Talon leftMotor = new Talon(11);
  @Override
  public void robotInit() {
  }

  @Override
  public void autonomousInit() {
    startime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();

    if(time - startime < 3 ){
      leftMotor.set(1);
    
    }
    
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

}

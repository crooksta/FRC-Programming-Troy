/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class IntakeSubsystem extends Subsystem {
  private static final int IT_IS_GOING_TO_THE_MOON = 1;
  private static final int STOP = 0;

  VictorSPX intake = new VictorSPX(RobotMap.intake);

 public int NO_SEND = STOP;
 public int FULL_SEND = IT_IS_GOING_TO_THE_MOON;

 boolean TOGGLE_INTAKE = false;
 boolean TOGGLE_INTAKE_PRESSED =  false;

 

  @Override
  public void initDefaultCommand() {
    

  }
  
    public void IntakeToggle()
    {
      if(INTAKE_TOGGLE_C1 || INTAKE_TOGGLE_C2)
      {
          if(!TOGGLE_INTAKE_PRESSED)
          {
              TOGGLE_INTAKE = !TOGGLE_INTAKE;
              TOGGLE_INTAKE = true;
          }
        }
      else
      {
        TOGGLE_INTAKE = false;
      }
  
    }

    
  
      
}

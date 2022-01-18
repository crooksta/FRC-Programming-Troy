package frc.robot.airSystem;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
import frc.robot.airSystem.*;
import frc.robot.RobotMap;
import frc.robot.OI;


public class airCommand extends Command{
   
    private final XboxController xbox0 = new XboxController(RobotMap.XBOX_CONTROLLER_PORT0);


    public airCommand(){

        requires(Robot.Air);
    }
    @Override
    protected void initialize() {
                System.out.println("Initialized Air System");

    }
    @Override
    protected void execute() {
        super.execute();
        Robot.Air.airLoop(xbox0.getRawButton(RobotMap.RIGHT_JOYSTICK_TRIGGER), xbox0.getRawButton(RobotMap.LEFT_JOYSTICK_TRIGGER));
        System.out.print("You should have control over solenoids");    }
    @Override
    protected boolean isFinished() {
        return false;
    }
    @Override
    protected void end() {
        super.end();
    }
    @Override
    protected void interrupted() {
        super.interrupted();
    }

}


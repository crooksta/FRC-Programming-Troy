package frc.robot.driveTrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;


public class driveCom extends Command{

    private final XboxController xbox0 = new XboxController(RobotMap.XBOX_CONTROLLER_PORT0);

    public driveCom(){

        requires(Robot.Drive);
    }
    
    @Override
    protected void initialize() {
        super.initialize();
        Robot.Drive.stop();
        System.out.println("Initialized");
    }
    @Override
    protected void execute() {
        super.execute();
        Robot.Drive.tankDrive(xbox0.getRawAxis(1), xbox0.getRawAxis(5));
        System.out.println("Executing: You should have control of drive wheels.");
    }

    @Override
    protected boolean isFinished() {
        return false;
        //this loop should never finish, as we always want control over the wheels, ergo, return false;
    }

   

    @Override
    protected void end() {
        super.end();
        Robot.Drive.stop();
        System.out.println("Ended");
    }

    @Override
    protected void interrupted() {
        super.interrupted();
        Robot.Drive.stop();
        System.out.println("The program has been interrupted!");
    }

}
package frc.robot.airSystem;

//mport static org.junit.Assume.assumeTrue;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Constants;

public class airSubsystem extends Subsystem{

    Compressor c = new Compressor();

    DoubleSolenoid doubleSol = new DoubleSolenoid(RobotMap.FRONT_SOLENOID_VALVE, RobotMap.BACK_SOLENOID_VALVE);
    

    
    public void retract() {
        doubleSol.set(DoubleSolenoid.Value.kReverse);

    }
    public void extend() {

        doubleSol.set(DoubleSolenoid.Value.kReverse);
    }

    public void airLoop(boolean push, boolean pull)
    {

        if(pull == true)
        {
            doubleSol.set(DoubleSolenoid.Value.kReverse);

        }
        else if(push == true){
            doubleSol.set(DoubleSolenoid.Value.kForward);

        }
        else{
            doubleSol.set(DoubleSolenoid.Value.kOff);
        }
    
    }




    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new airCommand());
    }


}
package frc.robot.driveTrain;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

public class driveSub extends Subsystem {

    private CANSparkMax right1 = new CANSparkMax(RobotMap.RIGHT_REAR_WHEEL_PORT, MotorType.kBrushless);
    private CANSparkMax right2 = new CANSparkMax(RobotMap.RIGHT_FRONT_WHEEL_PORT, MotorType.kBrushless);
    private CANSparkMax left1 = new CANSparkMax(RobotMap.LEFT_REAR_WHEEL_PORT, MotorType.kBrushless);
    private CANSparkMax left2 = new CANSparkMax(RobotMap.LEFT_FRONT_WHEEL_PORT, MotorType.kBrushless);

    SpeedControllerGroup leftSCG = new SpeedControllerGroup(left1, left2);
    SpeedControllerGroup rightSCG = new SpeedControllerGroup(right1, right2);

    private DifferentialDrive roboDrive = new DifferentialDrive(leftSCG, rightSCG);

    public void tankDrive(double leftControl, double rightControl) {

        roboDrive.tankDrive(leftControl, rightControl);
    }

    public void stop() {
        roboDrive.tankDrive(0, 0);
    }

    @Override

    protected void initDefaultCommand() {
        setDefaultCommand(new driveCom());
    }


}
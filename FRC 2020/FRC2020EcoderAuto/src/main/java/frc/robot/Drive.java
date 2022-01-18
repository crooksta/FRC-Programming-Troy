package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drive {
    int P, I, D = 1;
    int integral, previous_error, setpoint = 0;
    private ADXRS450_Gyro m_gyro = new ADXRS450_Gyro(kGyroPort);
    private static final SPI.Port kGyroPort = SPI.Port.kOnboardCS0;
    
    
    CANSparkMax leftFront = new CANSparkMax(10, MotorType.kBrushless);
  CANSparkMax leftRear = new CANSparkMax(11, MotorType.kBrushless);
  CANSparkMax rightRear = new CANSparkMax(21, MotorType.kBrushless);
  CANSparkMax rightFront = new CANSparkMax(20, MotorType.kBrushless);

  SpeedControllerGroup leftMotorGroup = new SpeedControllerGroup(leftFront, leftRear);
  SpeedControllerGroup rightMotorGroup = new SpeedControllerGroup(rightFront, rightRear);

  DifferentialDrive robotDrive = new DifferentialDrive(leftMotorGroup, rightMotorGroup);

  XboxController controller = new XboxController(0);
    private double rcw;


    public Drive(final ADXRS450_Gyro gyro) {
        this.m_gyro = gyro;
    }

    public void setSetpoint(final int setpoint)
    {
        this.setpoint = setpoint;
    }

    public void PID(){
       double  error = setpoint - m_gyro.getAngle(); // Error = Target - Actual
        this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
       double  derivative = (error - this.previous_error) / .02;
        this.rcw = P*error + I*this.integral + D*derivative;
    }

    public void execute()
    {
        PID();
        //robotDrive.arcadeDrive(0, rcw);
        
        robotDrive.arcadeDrive(controller.getRawAxis(1), controller.getRawAxis(4));
    }

}

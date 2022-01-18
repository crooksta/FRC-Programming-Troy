package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;

/**
 * Stores all of the variables outside of those needed for operator controls ({@link OI}) or motor 
 * controllers ({@link RobotMap}).
 * 
 * @see LimelightSubsystem
 * @see CalculateTargetDistance
 */
public class Constants {

    private static XboxController xbox0 = new XboxController(RobotMap.XBOX_CONTROLLER_PORT0);

    //THIS IS ALL FOR THE LIMELIGHT- WILL DEV THIS ONCE I GET DRIVE WORKING
    public static final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        
    public static final NetworkTableEntry tx = table.getEntry("tx");
    public static final NetworkTableEntry ty = table.getEntry("ty");
    public static final NetworkTableEntry ta = table.getEntry("ta");
    public static final NetworkTableEntry tv = table.getEntry("tv");

    public static final NetworkTableEntry ledMode = table.getEntry("ledMode");
    public static final NetworkTableEntry camMode = table.getEntry("camMode");
    public static final NetworkTableEntry pipeline = table.getEntry("pipeline");
}
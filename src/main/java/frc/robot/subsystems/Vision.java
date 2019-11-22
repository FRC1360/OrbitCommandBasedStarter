package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.math.Triangle;

/**
 * Add your docs here.
 */
public class Vision extends Subsystem {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tvert = table.getEntry("tvert");

    private Triangle currentTriangle = null;

    private final double SCORE_DISTANCE = 36.0;

    @Override
    public void initDefaultCommand() {

    }

    public double getX() {
        return tx.getDouble(0.0);
    }

    public double getVertHight() {
        return tvert.getDouble(Double.NaN);
    }

    public double getY() {
        return ty.getDouble(0.0);
    }

    public double getArea() {
        return ta.getDouble(0.0);
    }

    public double calculateDistance() {
        return 954 * Math.pow(getVertHight(), -0.765);
    }

    public double calculateAngle() {
        return Double.NaN;
    }

    public void createTriangle() {
        currentTriangle = new Triangle(SCORE_DISTANCE, calculateDistance(), calculateAngle());
    }

    public double[] getTriangleAngles() {
        return currentTriangle.angles;
    }

    public double[] getTriangleDistances() {
        return currentTriangle.sideLength;
    }
}

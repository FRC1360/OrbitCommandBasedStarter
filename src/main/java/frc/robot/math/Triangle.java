package frc.robot.math;

/**
 * Creates a triangle ABC
 * A - distance from set point to vision target
 * B - distance from robot to vision target
 * C - distance from robot to set point
 */
public class Triangle {
    public double[] sideLength = new double[3];
    public double[] angles = new double[3];

    //Assumes your robot is facing the vision target
    //Angles are in degrees
    public Triangle(double distanceA, double distanceB, double angleC) {
        sideLength[0] = distanceA;
        sideLength[1] = distanceB;

        angles[2] = angleC;

        sideLength[2] = Math.sqrt(Math.pow(sideLength[0], 2) + Math.pow(sideLength[1], 2) - 2 *
                sideLength[0] * sideLength[1] * Math.cos(angles[2] * (Math.PI / 180)));

        angles[0] = Math.asin((sideLength[0] * Math.sin(angles[2] * (Math.PI / 180)) / sideLength[2])) * (180 / Math.PI);

        angles[1] = 180 - angles[0] - angles[2];
    }
}

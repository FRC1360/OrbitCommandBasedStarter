package frc.robot.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TriangleTest {

    @Test
    public void testTriangle() {
        Triangle triangle = new Triangle(36.0, 100.0, 45);

        double[] actualDistance = {36.0, 100.0, 78.77};
        double[] actualAngle = {18.85, 116.15, 45};

        for (int i = 0; i < 3; i++) {
            assertEquals(actualDistance[i], triangle.sideLength[i], 0.1);
            assertEquals(actualAngle[i], triangle.angles[i], 0.1);
        }
    }
}

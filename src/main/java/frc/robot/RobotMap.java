package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;'

    public static final int DRIVETRAIN_CAN_ID_LEFT_FRONT_MASTER = 21;
    public static final int DRIVETRAIN_CAN_ID_LEFT_REAR_SLAVE = 22;
    public static final int DRIVETRAIN_CAN_ID_RIGHT_FRONT_MASTER = 31;
    public static final int DRIVETRAIN_CAN_ID_RIGHT_REAR_SLAVE = 32;

    public static final int ELEVATOR_CAN_ID_LEFT_FRONT_MASTER = 41;
    public static final int ELEVATOR_CAN_ID_LEFT_REAR_SLAVE = 42;
    public static final int ELEVATOR_CAN_ID_RIGHT_FRONT_SLAVE = 44;
    public static final int ELEVATOR_CAN_ID_RIGHT_REAR_SLAVE = 43;

    public static final int INTAKE_PWM_CHANNEL_LEFT = 0;
    public static final int INTAKE_PWM_CHANNEL_RIGHT = 1;
    public static final int INTAKE_SOLENOID_CHANNEL_RAISE_LOWER = 3;


    public static final int CLAW_PWM_CHANNEL_TOP_MOTOR = 5; 
    public static final int CLAW_PWM_CHANNEL_BOTTOM_MOTOR = 4;
    public static final int CLAW_SOLENOID_CHANNEL_TOP_PISTON = 1;
    public static final int CLAW_SOLENOID_CHANNEL_BOTTOM_PISTON = 2;
    public static final int CLAW_SOLENOID_CHANNEL_WRIST_PISTON = 0;
    public static final int CLAW_DIO_CHANNEL_HATCH_DETECT_TOP = 0;
    public static final int CLAW_DIO_CHANNEL_HATCH_DETECT_BOTTOM = 1;
    public static final int CLAW_DIO_CHANNEL_CARGO_DETECT = 2;

    public static final int LED_SOLENOID_CHANNEL_RED = 5;
    public static final int LED_SOLENOID_CHANNEL_GREEN = 6;
    public static final int LED_SOLENOID_CHANNEL_BLUE = 7;

    public static final int CONTROLLER_PORT_DRIVER = 0;
    public static final int CONTROLLER_PORT_OPERATOR = 1;
}

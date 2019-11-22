package frc.robot.util;

/**
 * Add your docs here.
 */
public class Constants {
    public static final int LEFT_TALON_DRIVE_SLOT_INDEX = 0;
    public static final int RIGHT_TALON_DRIVE_SLOT_INDEX = 0;
    
    public static final int LEFT_TALON_TURN_SLOT_INDEX = 1;
    public static final int RIGHT_TALON_TURN_SLOT_INDEX = 1;

    public static final int LEFT_TALON_DRIVE_LOOP_INDEX = 0;
    public static final int RIGHT_TALON_DRIVE_LOOP_INDEX = 0;

    public static final int LEFT_TALON_TURN_LOOP_INDEX = 1;
    public static final int RIGHT_TALON_TURN_LOOP_INDEX = 1;

    public static final Gains LEFT_TALON_GAINS_DRIVE = new Gains(1.8, 0.0, 60.0, 3.4, 0, 1.0);
    public static final Gains RIGHT_TALON_GAINS_DRIVE = new Gains(1.8, 0.0, 60.0, 3.4, 0, 1.0);


    public static final int PID_PRIMARY = 0;
    public static final int PID_TURN = 1;
    public static final int PID_ELEVATOR = 0;
    public static final int DISTANCE_SLOT = 0;
    public static final int TURN_SLOT = 1;
    public static final int ELEVATOR_SLOT = 0;
    public static final Gains DISTANCE_GAINS = new Gains(1, 0.0, 0, 1.205, 0, 1);
    public static final Gains TURN_GAINS = new Gains(0, 0, 0, 0, 0, 1.0);
    public static final Gains ELEVATOR_GAINS = new Gains(1.765,0,115,2.932,0,1);
    public static final double kNeutralDeadband = 0.001;
    public static final Gains VISION_TURN = new Gains(5,0,0,0,0,0);

    public static final int TOP = 1500;
    public static final int HATCH_1 = 900;
    public static final int HATCH_2 = 7500; //Just a guess - Aadi
    public static final int HATCH_3 = 14000; //Just a guess - Aadi
    public static final int CARGO_1 = 1670; //Just a guess - Aadi
    public static final int CARGO_2 = 8600; //Just a guess - Aadi
    public static final int CARGO_3 = 15200; //Just a guess - Aadi
    public static final int CARGOSHIP = 6300; //Just a guess - Aadi
    public static final int INTAKE = 900; 
    public static final int DEFAULT = 700;

    public static final int REMOTE_0 = 0;
    public static final int REMOTE_1 = 1;
    //public static final Gains

    public static final int TIMEOUT_MS = 30;

    //TODO: Add real width
    public static final double DRIVE_TRAIN_DIAMETER = 24.6;  

    //TODO: Add real TICKS_PER_INCH
    public static final double TICKS_PER_INCH = 5.30516;

    public static final double INCHES_PER_TICK = 1 / TICKS_PER_INCH;

    public static int DRIVE_MAX_VELOCITY = 180;

    public static int LIMELIGHT_MAX_DISTANCE = 168;

    public static final byte LED_COLOUR_RED = 0b00000001;
    public static final byte LED_COLOUR_GREEN = 0b00000010;
    public static final byte LED_COLOUR_BLUE = 0b00000100;
    public static final byte LED_COLOUR_YELLOW = 0b00000011;
    public static final byte LED_COLOUR_PURPLE = 0b00000101;
    public static final byte LED_COLOUR_TEAL = 0b00000110;
    public static final byte LED_COLOUR_WHITE = 0b00000111;
    public static final byte LED_COLOUR_NONE = 0b00000000;
    
    public static final int ELEVATOR_SWING = -500;


}
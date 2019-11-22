package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.led.LEDCommand;
import frc.robot.util.Constants;

public class LED extends Subsystem {
    public static Solenoid solLEDRed;
    public static Solenoid solLEDGreen;
    public static Solenoid solLEDBlue;



    public LED() {
        solLEDRed = new Solenoid(RobotMap.LED_SOLENOID_CHANNEL_RED);
        solLEDGreen = new Solenoid(RobotMap.LED_SOLENOID_CHANNEL_GREEN);
        solLEDBlue = new Solenoid(RobotMap.LED_SOLENOID_CHANNEL_BLUE);
    }

    public void setColour(byte colour) {
        solLEDRed.set((colour & Constants.LED_COLOUR_RED) == Constants.LED_COLOUR_RED ? true : false);
        solLEDGreen.set((colour & Constants.LED_COLOUR_GREEN) == Constants.LED_COLOUR_GREEN ? true : false);
        solLEDBlue.set((colour & Constants.LED_COLOUR_BLUE) == Constants.LED_COLOUR_BLUE ? true : false);
    }

    @Override
    public void initDefaultCommand() {
        //setDefaultCommand(new LEDCommand());
    }
}
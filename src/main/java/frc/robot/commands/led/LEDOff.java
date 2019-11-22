package frc.robot.commands.led;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.util.Constants;


public class LEDOff extends InstantCommand {
    public LEDOff() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.led);
    }
    protected void execute() {
        Robot.led.setColour(Constants.LED_COLOUR_WHITE);
    }

    @Override
    protected void end() {

    }

}

package frc.robot.commands.led;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.util.Constants;

import java.time.Instant;


public class GreenLED extends InstantCommand {
    public GreenLED() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.led);
    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute() {
        Robot.led.setColour(Constants.LED_COLOUR_GREEN);
    }

    @Override
    protected void interrupted() {
        super.interrupted();
    }
}

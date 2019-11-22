package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.groups.KillIntake;
import frc.robot.commands.intake.killIntakeCommand;

public class Intake extends Subsystem {
    public enum Position {
        UP(false),
        DOWN(true);

        private final boolean value;
        private Position(boolean value)
        {
            this.value = value;
        }

        public boolean getValue()
        {
            return this.value;
        }
    }

    private Solenoid intakePiston = new Solenoid(RobotMap.INTAKE_SOLENOID_CHANNEL_RAISE_LOWER);

    private Victor intakeRollerLeft = new Victor(RobotMap.INTAKE_PWM_CHANNEL_LEFT);
    private Victor intakeRollerRight = new Victor(RobotMap.INTAKE_PWM_CHANNEL_RIGHT);

    private Position currentPosition = Position.UP;

    public void setRollers(double speed) {
        intakeRollerLeft.set(speed);
        intakeRollerRight.set(-speed);
    }

    public void setPosition(Position intakePosition) {
        this.currentPosition = intakePosition;
        intakePiston.set(intakePosition.getValue());
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Position otherPosition() {
        if(currentPosition == Position.UP) return Position.DOWN;
        else return Position.UP;
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
         setDefaultCommand(new killIntakeCommand());
    }
}



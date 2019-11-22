package frc.robot.commands.drive;

import static frc.robot.Robot.oi;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.TalonConfig.TalonConfigDriveTeleop;
import frc.robot.util.Constants;

public class DrivetrainCommand extends Command {
    public static final double TURN_WEIGHT_FACTOR = 0.2;

    public  DrivetrainCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double left;
        double right;

        double speed = oi.throttle();

        if(Robot.elevator.getHoldPos() > Constants.CARGO_1 + 100 || Robot.elevator.elevatorMotorMaster.getSelectedSensorPosition() > Constants.HATCH_1 + 100)
        {
            speed *= 0.3;
        }
        
        double turn = oi.getLeftJoystickDriver();
        turn *= 0.8;


        //System.out.println("Speed = " + speed);

        //SmartDashboard.putNumber("drive enc", Robot.drivetrain.talonR.getSelectedSensorPosition());
        if (turn > 0) {
            left = (speed) + ((Math.exp(TURN_WEIGHT_FACTOR * turn) * turn));
            right = (speed) + ((Math.exp(TURN_WEIGHT_FACTOR * turn) * -turn));
        } else if (turn < 0) {
            left = (speed) + ((Math.exp(TURN_WEIGHT_FACTOR * -turn) * turn));
            right = (speed) + ((Math.exp(TURN_WEIGHT_FACTOR * -turn) * -turn));
        } else {
            left = speed;
            right = speed;
        }

            
            TalonConfigDriveTeleop.config(Robot.drivetrain.talonL, Robot.drivetrain.talonR);
            Robot.drivetrain.tankDrive(left, right);
            
            
        
    }


    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.drivetrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}

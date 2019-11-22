/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Command;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.OI;


public class DriveTrainCommand extends Command {
  public DriveTrainCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

 // Robot.drive.drive(speed, turn);

 if(Robot.oi.getTriggerLeft() == 0)
 {
    Robot.drivetrain.arcadeDrive(Robot.oi.getTriggerRight(), Robot.oi.getJoyStickLeft());
 }
 else{
  Robot.drivetrain.arcadeDrive(-Robot.oi.getTriggerLeft(), Robot.oi.getJoyStickLeft());
 }


  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  @Override
  protected boolean isFinished() {
    // TODO Auto-generated method stub
    return false;
  }
}

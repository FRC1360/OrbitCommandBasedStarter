/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.claw;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Elevator;

public class ClawSetup extends Command {
  public ClawSetup() {
    requires(Robot.elevator);
    requires(Robot.claw);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Robot.elevator.setHoldPos(Elevator.Position.HATCH1.getValue());
    // Robot.elevator.moveToPosition();
    Robot.claw.setClaw(Claw.Position.OPEN_BOTH);
    Robot.claw.setIgnoreSensor(true);
    
    // try {
    //   Thread.sleep(5000, 0);
    // } catch (InterruptedException e) {
    //   // TODO Auto-generated catch block
    //  throw new RuntimeException(e);
    // }
    Robot.claw.setWrist(true);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
}

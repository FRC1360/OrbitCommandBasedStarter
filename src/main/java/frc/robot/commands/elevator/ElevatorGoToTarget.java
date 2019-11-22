/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;
import frc.robot.util.Constants;

public class ElevatorGoToTarget extends InstantCommand {
  Elevator.Position target;
  

  public ElevatorGoToTarget( Elevator.Position target) {
      
    requires(Robot.elevator);
    this.target = target;

  }

  @Override
  public void execute() {
    int jExtra = (int)(Robot.oi.getLeftJoystickYOperator() * Constants.ELEVATOR_SWING);
    Robot.elevator.target = target.getValue();

    Robot.elevator.setHoldPos(target.getValue() + jExtra);
    Robot.elevator.moveToPosition();
    //  System.out.println("move to target");
    
  }

  @Override
  protected boolean isFinished() {
      return true;
  }
  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
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

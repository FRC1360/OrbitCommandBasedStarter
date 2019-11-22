/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.util.Constants;
import frc.robot.util.OrbitPID;

public class TurnToVisionTarget extends InstantCommand {
  public TurnToVisionTarget() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
    requires(Robot.vision);
    turnPID = new OrbitPID(0.05, 0.0, 1);
  }
  OrbitPID turnPID;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double turn = -turnPID.calculate(0.0, Robot.vision.getX());
    SmartDashboard.putNumber("Vision X", Robot.vision.getX());
    SmartDashboard.putNumber("Turn", turn);
    double speed = Robot.oi.throttle();
    Robot.drivetrain.arcadeDrive(speed, turn);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true; //after the thing is tuned make sure that this is return getX is within -0.5 and 0.5
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

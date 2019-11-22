/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.claw.ClawRollerCommand;
import frc.robot.commands.claw.SetClawCommand;
import frc.robot.commands.intake.IntakePistonCommand;
import frc.robot.commands.intake.IntakeRollerCommand;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Claw.IntakeState;
import frc.robot.subsystems.Intake;

public class KillIntake extends CommandGroup {

  public KillIntake(boolean run) {
    System.out.print("kill intake");
    requires(Robot.intake);
    
    if(run)
    {
     // addSequential(new SetClawCommand(Claw.Position.CLOSED_CLAW));
     // addSequential(new ClawRollerCommand(0));
      addSequential(new IntakeRollerCommand(0));
      addSequential(new IntakePistonCommand(Intake.Position.UP));
    } else if (Robot.claw.hasCargo() == Claw.IntakeState.BALL_IN_ROLLERS_OFF) {
     // addSequential(new SetClawCommand(Claw.Position.CLOSED_CLAW));
      addSequential(new IntakeRollerCommand(0.0));
      //addSequential(new ClawRollerCommand(0.0));
    }
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.claw.ClawRollerCommand;
import frc.robot.commands.claw.SetClawCommand;
import frc.robot.subsystems.Claw;

public class killClaw extends CommandGroup {
  /**
   * Add your docs here.
   */
  public killClaw(boolean run) {
  
    requires(Robot.claw);

    //System.out.println(" eariler");

    if(Robot.claw.hasHatchTop())
    {
      //System.out.println("if statement");
      //addSequential(new SetClawCommand(Claw.Position.OPEN_HATCH));
      //addSequential(new ClawRollerCommand(0));

    }
    
    //addSequential(new IntakeRollerCommand(0));
    //addSequential(new IntakePistonCommand(Intake.Position.UP));
   else{

    //System.out.println("else statement");
    //addSequential(new SetClawCommand(Claw.Position.CLOSED_CLAW));
   // addSequential(new IntakeRollerCommand(0.0));
    //addSequential(new ClawRollerCommand(0.0));
  }
  }
}

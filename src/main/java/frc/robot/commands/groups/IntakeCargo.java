/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.claw.TopClawPositionCommand;
import frc.robot.commands.claw.BottomClawPositionCommand;
import frc.robot.commands.claw.ClawRollerCommand;
import frc.robot.commands.claw.SetClawCommand;
import frc.robot.commands.elevator.ElevatorGoToTarget;
import frc.robot.commands.intake.IntakePistonCommand;
import frc.robot.commands.intake.IntakeRollerCommand;
import frc.robot.commands.sensor.WaitForCargo;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Claw.Position;

public class IntakeCargo extends CommandGroup {
  /**
   * Add your docs here.
   */
  public IntakeCargo(boolean input) {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
    requires(Robot.claw);
    requires(Robot.intake);

    //System.out.print("yeet");
    if(input && Robot.claw.hasCargo() == Claw.IntakeState.NONE  && !Robot.claw.hasBall())
    {
      addSequential(new IntakePistonCommand(Intake.Position.DOWN));
      addSequential(new SetClawCommand(Claw.Position.OPEN_CARGO));
      addSequential(new IntakeRollerCommand(0.4));
      addSequential(new ClawRollerCommand(0.6));
    } else if (Robot.claw.hasCargo() == Claw.IntakeState.BALL_IN_ROLLERS_ON){
      addSequential(new SetClawCommand(Claw.Position.CLOSED_CLAW));
      addSequential(new IntakeRollerCommand(0.0)); 
      addSequential(new ClawRollerCommand(0.25));
      addSequential(new IntakePistonCommand(Intake.Position.UP));
    }

    

    //addSequential(new WaitForCargo());

    //addParallel(new IntakeRollerCommand(false));
    //addParallel(new ClawRollerCommand(0.0));
    //addSequential(new IntakePistonCommand(Intake.Position.UP));
  }
}

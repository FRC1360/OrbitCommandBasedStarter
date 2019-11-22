/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.elevator.ElevatorGoToTarget;
import frc.robot.subsystems.Elevator;

public class ElevatorGoToTargetGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ElevatorGoToTargetGroup(boolean input, Elevator.Position pos) {
    //requires(Robot.elevator);
    if(input) {
      addSequential(new ElevatorGoToTarget(pos));
    }
    
  }
}

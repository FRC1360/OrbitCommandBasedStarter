/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

public class IntakeGroup extends CommandGroup {

  public IntakeGroup() {
    if(Robot.oi.getOperatorLeftTrigger() > 0)
    {   
      addSequential(new IntakeCargo(true));
    }
    else if(Robot.oi.getOperatorRightTrigger() > 0)
    {
      addSequential(new IntakeHatch());
    }
  }
}

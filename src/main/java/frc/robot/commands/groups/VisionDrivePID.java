/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.TurnToVisionTarget;

public class VisionDrivePID extends CommandGroup {
  /**
   * Add your docs here.
   */
  public VisionDrivePID(boolean input) {
    System.out.println("yeet");
    if(input)
    {
      System.out.println("yeet but in an if");
      addSequential(new TurnToVisionTarget());
    }
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.elevator.ElevatorManualCommand;


public class ElevatorManualGroup extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ElevatorManualGroup() {
    
    if(Robot.oi.getRightJoystickYOperator() != 0)
    {
      //new ElevatorManualCommand().start(); 
      addSequential(new ElevatorManualCommand());
    }
    
  }
}

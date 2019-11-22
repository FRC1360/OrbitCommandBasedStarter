/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.claw.SetClawCommand;
import frc.robot.commands.intake.IntakePistonCommand;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Intake;

public class IntakeHatch extends CommandGroup {
  /**
   * Add your docs here.
   */
  public IntakeHatch() {
    requires(Robot.claw);
    //requires(Robot.intake);
    
    //System.out.println("yeet but with a trigger");
      
         if(Robot.claw.hasHatchTop())
         {
           //System.out.println("yeet");
           addSequential(new SetClawCommand(Claw.Position.OPEN_HATCH));
           //addSequential(new IntakePistonCommand(Intake.Position.UP));
         }
         else {
        //  addSequential(new IntakePistonCommand(Intake.Position.UP));
          addSequential(new SetClawCommand(Claw.Position.OPEN_HATCH));
        
         }

       
    
  }

    
    
  }


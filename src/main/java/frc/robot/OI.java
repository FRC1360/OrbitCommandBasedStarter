package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
<<<<<<< HEAD

=======
import frc.robot.commands.*;
import frc.robot.commands.elevator.*;
import frc.robot.commands.claw.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.claw.BottomClawPositionCommand;
import frc.robot.commands.claw.TopClawPositionCommand;  
import frc.robot.commands.elevator.ElevatorGoToTarget;
import frc.robot.commands.groups.ElevatorDefaultGroup;
import frc.robot.commands.groups.ElevatorGoToTargetGroup;
import frc.robot.commands.groups.IntakeCargo;
import frc.robot.commands.groups.IntakeGroup;
import frc.robot.commands.groups.IntakeHatch;
import frc.robot.commands.groups.KillIntake;
import frc.robot.commands.groups.OutakeCargo;
import frc.robot.commands.groups.OuttakeGroup;
import frc.robot.commands.groups.VisionDrivePID;
import frc.robot.commands.intake.IntakePistonCommand;
import frc.robot.subsystems.Claw;
>>>>>>> f6b75dc421d9dbdc0519095bcb8b6cfae154a731
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.util.Constants;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  // Creating the controllers
  XboxController driverController = new XboxController(0);
  XboxController operatorController = new XboxController(1);

  // Initializing Deadzone
  public double deadzone(double input, double deadzone)
  {
    if(Math.abs(input) < deadzone)
    {
      return 0;
    }

    else{
      return input;
    }
  }

  public double getTriggerRight()
  {
    return deadzone(driverController.getTriggerAxis(Hand.kRight), 0.3);

  }

  public double getTriggerLeft()
  {
    return deadzone(driverController.getTriggerAxis(Hand.kLeft), 0.3);

  }

  public double getJoyStickLeft()
  {
    return deadzone(driverController.getX(Hand.kLeft), 0.2);
    
  }

    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a
    //// joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
   
}

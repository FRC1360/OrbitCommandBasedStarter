/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Claw extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // Declare all pistons, rollers motors, and sensors
  private Victor rollerTop = new Victor(RobotMap.CLAW_PWM_CHANNEL_TOP_MOTOR);
  private Victor rollerBottom = new Victor(RobotMap.CLAW_PWM_CHANNEL_BOTTOM_MOTOR);

  private Solenoid pistonTop = new Solenoid(RobotMap.CLAW_SOLENOID_CHANNEL_TOP_PISTON);
  private Solenoid pistonBottom = new Solenoid(RobotMap.CLAW_SOLENOID_CHANNEL_BOTTOM_PISTON);
  private Solenoid wrist = new Solenoid(RobotMap.CLAW_SOLENOID_CHANNEL_WRIST_PISTON);

  private DigitalInput cargoSensor = new DigitalInput(RobotMap.CLAW_DIO_CHANNEL_CARGO_DETECT);

  private boolean haveBall = false;

  // What other states do we need?
  public enum Position{
    OPEN_CARGO,
    DEFAULT;
  }

  public void setRollers(double wheelSpeed){
    rollerTop.set(wheelSpeed);
    rollerBottom.set(wheelSpeed);
  }

  // Don't know if true is engaged or not
  public void setClawPosition(Position pos){
    if(pos == Position.OPEN_CARGO){
      pistonBottom.set(true);
      pistonTop.set(true);
    }
    else{
      pistonBottom.set(false);
      pistonTop.set(false);
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

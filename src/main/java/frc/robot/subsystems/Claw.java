/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.claw.killClawCommand;
import frc.robot.commands.groups.killClaw; 

/**
 * Add your docs here.
 */
public class Claw extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private Victor clawVictor1 = new Victor(RobotMap.CLAW_PWM_CHANNEL_TOP_MOTOR);
  private Victor clawVictor2 = new Victor(RobotMap.CLAW_PWM_CHANNEL_BOTTOM_MOTOR);

  private Solenoid clawSolenoid1 = new Solenoid(RobotMap.CLAW_SOLENOID_CHANNEL_TOP_PISTON);
  private Solenoid clawSolenoid2 = new Solenoid(RobotMap.CLAW_SOLENOID_CHANNEL_BOTTOM_PISTON);
  private Solenoid clawWrist = new Solenoid(RobotMap.CLAW_SOLENOID_CHANNEL_WRIST_PISTON);

  private DigitalInput  cargoSensor = new DigitalInput(RobotMap.CLAW_DIO_CHANNEL_CARGO_DETECT);

  private int sensorIgnoreTime = 0;

  private boolean BallSecure = false;
  private boolean ignoreSensor = true;


  public Claw()
  {
      clawVictor2.setInverted(true);
      //setClawBottom(true);
      //setClawTop(false);
      //setWrist(true);

  }

  public enum Position {
      OPEN_HATCH,
      OPEN_CARGO,
      CLOSED_CLAW,
      OPEN_BOTH;
      
  }

  public enum IntakeState {
      NONE,
      BALL_IN_ROLLERS_ON,
      BALL_IN_ROLLERS_OFF;
  }

  //TODO Clarify what sensors are being used
  public IntakeState hasCargo(){
      if(cargoSensor.get()) {
          setHasBall(true);
          sensorIgnoreTime++;
          SmartDashboard.putNumber("has cargo", sensorIgnoreTime);
          if(sensorIgnoreTime >= 50) {
              return IntakeState.BALL_IN_ROLLERS_OFF;
          } else if(sensorIgnoreTime >= 10) {
              return IntakeState.BALL_IN_ROLLERS_ON;
          }
          return IntakeState.NONE;
      } 
      sensorIgnoreTime = 0;
      return IntakeState.NONE;
  }
  public void setIgnoreSensor(boolean set)
  {
      ignoreSensor = set;
  }
  // public boolean hasHatch(){
  //     SmartDashboard.putBoolean("has hatch both", (hatchSensortop.get() && hatchSensorbottom.get()));
  //     return(hatchSensortop.get() && hatchSensorbottom.get());
  // }
  public void toggleIgnoreSensor()
  {
      ignoreSensor = !ignoreSensor;
  }

  public boolean hasHatchTop(){
      //(hatchSensortop.get());
      SmartDashboard.putBoolean(" ignoring sensor", ignoreSensor);
      return ignoreSensor; //||  hatchSensortop.get();

  }

  public void setRollers(double wheelSpeed){
      clawVictor1.set(wheelSpeed);
      clawVictor2.set(wheelSpeed);
  }
    public void setWrist(boolean wristState)
  {
  clawWrist.set(wristState);
  }
    public boolean getWrist()
    {
        return clawWrist.get();
    }
  public void setClawTop(boolean clawState){
      clawSolenoid1.set(clawState);
  }
  public void setClawBottom(boolean clawState){
      clawSolenoid2.set(clawState);
  }
  public void setClaw(Position pos)
  {
      if(pos == Position.CLOSED_CLAW)
      {
          setClawTop(false);
          setClawBottom(false);
        //  //("Closed");
      }
      else if(pos == Position.OPEN_CARGO)
      {
          setClawTop(true);
          setClawBottom(true);
        //  //("Open Cargo");
      }
      else if(pos == Position.OPEN_HATCH)
      {
          setClawTop(false);
          setClawBottom(true);
          
      }
      else{
          setClawTop(true);
          setClawBottom(true);  
           ////("Else"); 
      }
  }
  public boolean getClaw(){
      return clawSolenoid1.get();
  }
  public double getClawRoller()
  {
      return clawVictor1.getSpeed();
  }

  public boolean  hasBall() {
      return cargoSensor.get();
      //return false;
  }

  public void setHasBall(boolean ball) {
      BallSecure = ball;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

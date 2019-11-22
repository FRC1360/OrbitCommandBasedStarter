/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import java.sql.Time;
import java.util.concurrent.TimeUnit;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.hal.can.CANJNI;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.commands.drive.DrivetrainCommand;
//import frc.robot.subsystems.drive.TalonConfigAuxPidProfile;
import frc.robot.subsystems.TalonConfig.TalonConfigDrive;
//import frc.robot.subsystems.drive.TaloionMagicProfile;
import frc.robot.util.Constants;
//import frc.robot.util.Instrum;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  
  public WPI_TalonSRX talonL;
  public WPI_TalonSRX talonR;

  public WPI_VictorSPX victorL;
  public WPI_VictorSPX victorR;

  StringBuilder sb = new StringBuilder();

  public Drivetrain(int masterL, int masterR, int slaveL, int slaveR) {
      System.out.print("Init");

      this.talonL = new WPI_TalonSRX(masterL);
      this.talonR = new WPI_TalonSRX(masterR);
      addChild(talonL);
      addChild(talonR);
      

      this.victorL = new WPI_VictorSPX(slaveL);
      this.victorR = new WPI_VictorSPX(slaveR);
      addChild(victorL);
      addChild(victorR);

      this.talonR.setInverted(true); 
      victorR.setInverted(true);
      this.victorL.follow(this.talonL);
      this.victorR.follow(this.talonR);

    
      
  }

  @Override
  public void initDefaultCommand() {
      setDefaultCommand(new DrivetrainCommand());
  }

  public void arcadeDrive(double speed, double turn)
  {
      talonL.set(ControlMode.PercentOutput, speed + turn);
      talonR.set(ControlMode.PercentOutput, speed - turn);
  }

  public void tankDrive(double left, double right)
  {
      talonL.set(ControlMode.PercentOutput, left);
      talonR.set(ControlMode.PercentOutput, right);
  }



  public void motionMagicTune(boolean switchModes, boolean input)
  {
      double motorOutput = talonR.getMotorOutputPercent();
      sb.append("\tOut%:");
  sb.append(motorOutput);
  sb.append("\tVel:");
      sb.append(talonR.getSelectedSensorVelocity(Constants.RIGHT_TALON_DRIVE_LOOP_INDEX));
      
      if(switchModes)
      {
          double target = 1 * 3.5 * 1000;
          talonL.set(ControlMode.MotionMagic, target);
          talonR.set(ControlMode.MotionMagic, target);

          sb.append("\terr:");
          sb.append(talonR.getClosedLoopError(Constants.RIGHT_TALON_DRIVE_LOOP_INDEX));
    sb.append("\ttrg:");
          sb.append(target);
      }
      else if(input)
      {
          double target = 0;
          talonL.set(ControlMode.MotionMagic, target);
          talonR.set(ControlMode.MotionMagic, target);


          sb.append("\terr:");
          sb.append(talonR.getClosedLoopError(Constants.RIGHT_TALON_DRIVE_LOOP_INDEX));
          sb.append("\ttrg:");
          sb.append(target);
      }

      //Instrum.Process(talonR, sb);
      SmartDashboard.putNumber("Talon L CLE", talonL.getClosedLoopError(Constants.LEFT_TALON_DRIVE_LOOP_INDEX));
      SmartDashboard.putNumber("Talon L Sens Vel", talonL.getSelectedSensorVelocity(Constants.LEFT_TALON_DRIVE_LOOP_INDEX));
  }

  public void resetTalonEncoders()
  {
      //talonL.setSelectedSensorPosition(0, Constants.LEFT_TALON_DRIVE_LOOP_INDEX, Constants.TIMEOUT_MS);
      //talonR.setSelectedSensorPosition(0, Constants.RIGHT_TALON_DRIVE_LOOP_INDEX, Constants.TIMEOUT_MS);
      talonL.getSensorCollection().setQuadraturePosition(0, Constants.TIMEOUT_MS);
      talonR.getSensorCollection().setQuadraturePosition(0, Constants.TIMEOUT_MS);
  }

  public int getLeftEncoder()
  {
      return 1;//talonL.getSelectedSensorPosition(Constants.LEFT_TALON_DRIVE_LOOP_INDEX);
  }

  public int getRightEncoder()
  {
      return talonR.getSelectedSensorPosition(Constants.RIGHT_TALON_DRIVE_LOOP_INDEX);
  }

  public void motionMagicTarget(double leftTarget, double rightTarget)
  {
      talonL.set(ControlMode.MotionMagic, leftTarget);
      talonR.set(ControlMode.MotionMagic, rightTarget);
  }

  public void setMotionMagicVelocity(int leftVelocity, int rightVeloctiy)
  {
      talonL.configMotionCruiseVelocity(leftVelocity, Constants.TIMEOUT_MS);
      talonR.configMotionCruiseVelocity(rightVeloctiy, Constants.TIMEOUT_MS);
  }

  

  public void driveStraight(double target)
  {
    
    //  System.out.println("driveStraight: " + target + " time: " + System.currentTimeMillis());
      talonR.set(ControlMode.MotionMagic, target, DemandType.AuxPID, 0);
      //System.out.println(talonR.getClosedLoopTarget() + " <-target " + talonR.getClosedLoopError(Constants.PID_PRIMARY));
      talonL.follow(talonR, FollowerType.AuxOutput1);
    
  }



  public void callAuxPIDProfile()
  {
      this.talonR.selectProfileSlot(Constants.DISTANCE_SLOT, Constants.PID_PRIMARY);
      this.talonR.selectProfileSlot(Constants.TURN_SLOT, Constants.PID_TURN);
  }


}
  


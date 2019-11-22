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
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
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
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Command.DriveTrainCommand;
//import frc.robot.subsystems.drive.TaloionMagicProfile;
import frc.robot.util.Constants;
//import frc.robot.util.Instrum;
import edu.wpi.first.wpilibj.SPI;

/**
 * Add your docs here.
 */
public class drivetrain2 extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

public class Drivetrain extends Subsystem {


  public WPI_TalonSRX talonL;
  public WPI_TalonSRX talonR;

  public WPI_VictorSPX victorR;
  public WPI_VictorSPX victorL;

  public void drivetrain2 (int masterL, int masterR, int slaveL, int slaveR) {
    this. talonL = new WPI_TalonSRX(masterL);
    this. talonR = new WPI_TalonSRX(masterR);

    talonR.configFactoryDefault();
    talonL.configFactoryDefault();

    talonL.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    talonL.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    talonR.configForwardLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
    talonR.configReverseLimitSwitchSource(LimitSwitchSource.Deactivated, LimitSwitchNormal.Disabled);
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
  public void arcadeDrive(double speed, double turn)
  {
      talonL.set(ControlMode.PercentOutput, speed + turn);
      talonR.set(ControlMode.PercentOutput, speed - turn);
  }

    @Override
    protected void initDefaultCommand() {
      // TODO Auto-generated method stub
      setDefaultCommand(new DriveTrainCommand());

    }

}
}


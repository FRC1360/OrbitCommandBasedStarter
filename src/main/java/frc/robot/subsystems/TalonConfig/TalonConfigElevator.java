package frc.robot.subsystems.TalonConfig;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.util.Constants;

public class TalonConfigElevator {

    public static void config( WPI_TalonSRX talonE, WPI_VictorSPX slave1, WPI_VictorSPX slave2, WPI_VictorSPX slave3)
    {
        talonE.set(ControlMode.PercentOutput, 0);
        slave1.set(ControlMode.PercentOutput, 0);
        slave2.set(ControlMode.PercentOutput, 0);
        slave3.set(ControlMode.PercentOutput, 0);
		
        talonE.configFactoryDefault();
        slave1.configFactoryDefault();
        slave2.configFactoryDefault();
        slave3.configFactoryDefault();
        
		
		/* Set Neutral Mode */
        talonE.setNeutralMode(NeutralMode.Brake);
        slave1.setNeutralMode(NeutralMode.Brake);
        slave2.setNeutralMode(NeutralMode.Brake);
        slave3.setNeutralMode(NeutralMode.Brake);

        talonE.set(ControlMode.MotionMagic,0);
		
		
		talonE.configSelectedFeedbackSensor(	FeedbackDevice.QuadEncoder,				// Local Feedback Source
												Constants.PID_ELEVATOR,					// PID Slot for Source [0, 1]
                                                Constants.TIMEOUT_MS);
        talonE.setInverted(false);
        talonE.setSensorPhase(false);
        
        talonE.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.TIMEOUT_MS);
        talonE.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.TIMEOUT_MS);
        
        talonE.configNominalOutputForward(0.12, Constants.TIMEOUT_MS);
    	talonE.configNominalOutputReverse(0, Constants.TIMEOUT_MS);
	    talonE.configPeakOutputForward(1, Constants.TIMEOUT_MS);
        talonE.configPeakOutputReverse(-1, Constants.TIMEOUT_MS);
        
        talonE.selectProfileSlot(Constants.ELEVATOR_SLOT, Constants.PID_ELEVATOR);
		talonE.config_kF(Constants.ELEVATOR_SLOT, Constants.ELEVATOR_GAINS.kF, Constants.TIMEOUT_MS);
		talonE.config_kP(Constants.ELEVATOR_SLOT, Constants.ELEVATOR_GAINS.kP, Constants.TIMEOUT_MS);
		talonE.config_kI(Constants.ELEVATOR_SLOT, Constants.ELEVATOR_GAINS.kI, Constants.TIMEOUT_MS);
		talonE.config_kD(Constants.ELEVATOR_SLOT, Constants.ELEVATOR_GAINS.kD, Constants.TIMEOUT_MS);
        System.out.println("config");

        talonE.configMotionCruiseVelocity(3000, Constants.TIMEOUT_MS);
        talonE.configMotionAcceleration(3000, Constants.TIMEOUT_MS);
        //talonE.enableVoltageCompensation(true); 
        //talonE.configVoltageCompSaturation(3);
        talonE.setSelectedSensorPosition(0, Constants.PID_ELEVATOR, Constants.TIMEOUT_MS);
        
        talonE.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        talonE.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
/*
        slave1.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        slave1.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

        slave2.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        slave2.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

        slave3.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
        slave3.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);

        */
    }
}

package frc.robot.subsystems.TalonConfig;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.RemoteSensorSource;
import com.ctre.phoenix.motorcontrol.SensorTerm;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.util.Constants;

/**
 * Add your docs here.
 */
public class TalonConfigDrive {

    public static void config(WPI_TalonSRX talonL, WPI_TalonSRX talonR)
    {
        talonR.set(ControlMode.PercentOutput, 0);
		talonL.set(ControlMode.PercentOutput, 0);

		/* Factory Default all hardware to prevent unexpected behaviour */
		talonR.configFactoryDefault();
		talonL.configFactoryDefault();
		
		/* Set Neutral Mode */
		talonL.setNeutralMode(NeutralMode.Brake);
		talonR.setNeutralMode(NeutralMode.Brake);
		
		/** Feedback Sensor Configuration */
		
		/* Configure the left Talon's selected sensor as local QuadEncoder */
		talonL.configSelectedFeedbackSensor(	FeedbackDevice.QuadEncoder,				// Local Feedback Source
													Constants.PID_PRIMARY,					// PID Slot for Source [0, 1]
													Constants.TIMEOUT_MS);					// Configuration Timeout

		/* Configure the Remote Talon's selected sensor as a remote sensor for the right Talon */
		talonR.configRemoteFeedbackFilter(talonL.getDeviceID(),					// Device ID of Source
												RemoteSensorSource.TalonSRX_SelectedSensor,	// Remote Feedback Source
												Constants.REMOTE_0,							// Source number [0, 1]
												Constants.TIMEOUT_MS);						// Configuration Timeout
		
		/* Setup Sum signal to be used for Distance */
		talonR.configSensorTerm(SensorTerm.Sum0, FeedbackDevice.RemoteSensor0, Constants.TIMEOUT_MS);				// Feedback Device of Remote Talon
		talonR.configSensorTerm(SensorTerm.Sum1, FeedbackDevice.CTRE_MagEncoder_Relative, Constants.TIMEOUT_MS);	// Quadrature Encoder of current Talon
		
		/* Setup Difference signal to be used for Turn */
		talonR.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor0, Constants.TIMEOUT_MS);
		talonR.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.CTRE_MagEncoder_Relative, Constants.TIMEOUT_MS);
		
		/* Configure Sum [Sum of both QuadEncoders] to be used for Primary PID Index */
		talonR.configSelectedFeedbackSensor(	FeedbackDevice.SensorSum, 
													Constants.PID_PRIMARY,
													Constants.TIMEOUT_MS);
		
		/* Scale Feedback by 0.5 to half the sum of Distance */
		talonR.configSelectedFeedbackCoefficient(	0.5, 						// Coefficient
														Constants.PID_PRIMARY,		// PID Slot of Source 
														Constants.TIMEOUT_MS);		// Configuration Timeout
		
		/* Configure Difference [Difference between both QuadEncoders] to be used for Auxiliary PID Index */
		talonR.configSelectedFeedbackSensor(	FeedbackDevice.SensorDifference, 
													Constants.PID_TURN, 
													Constants.TIMEOUT_MS);
		
		/* Scale the Feedback Sensor using a coefficient */
		talonR.configSelectedFeedbackCoefficient(	1,
														Constants.PID_TURN, 
														Constants.TIMEOUT_MS);
		/* Configure output and sensor direction */
		talonL.setInverted(false);
		talonL.setSensorPhase(false);
		talonR.setInverted(true);
		talonR.setSensorPhase(false);
		
		/* Set status frame periods to ensure we don't have stale data */
		talonR.setStatusFramePeriod(StatusFrame.Status_12_Feedback1, 20, Constants.TIMEOUT_MS);
		talonR.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 20, Constants.TIMEOUT_MS);
        talonR.setStatusFramePeriod(StatusFrame.Status_14_Turn_PIDF1, 20, Constants.TIMEOUT_MS);
		talonR.setStatusFramePeriod(StatusFrame.Status_10_Targets, 20, Constants.TIMEOUT_MS);
		talonL.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 5, Constants.TIMEOUT_MS);

		/* Configure neutral deadband */
		talonR.configNeutralDeadband(Constants.kNeutralDeadband, Constants.TIMEOUT_MS);
		talonL.configNeutralDeadband(Constants.kNeutralDeadband, Constants.TIMEOUT_MS);
		
		/* Motion Magic Configurations */
		talonR.configMotionAcceleration(2000, Constants.TIMEOUT_MS);
		talonR.configMotionCruiseVelocity(2000, Constants.TIMEOUT_MS);

		/**
		 * Max out the peak output (for all modes).  
		 * However you can limit the output of a given PID object with configClosedLoopPeakOutput().
		 */
		/*
		talonL.configPeakOutputForward(1.0, Constants.TIMEOUT_MS);
		talonL.configPeakOutputReverse(-1.0, Constants.TIMEOUT_MS);
		talonR.configPeakOutputForward(1.0, Constants.TIMEOUT_MS);
		talonR.configPeakOutputReverse(-1.0, Constants.TIMEOUT_MS);
		*/
		/* FPID Gains for distance servo */
		talonR.config_kP(Constants.DISTANCE_SLOT, Constants.DISTANCE_GAINS.kP, Constants.TIMEOUT_MS);
		talonR.config_kI(Constants.DISTANCE_SLOT, Constants.DISTANCE_GAINS.kI, Constants.TIMEOUT_MS);
		talonR.config_kD(Constants.DISTANCE_SLOT, Constants.DISTANCE_GAINS.kD, Constants.TIMEOUT_MS);
		talonR.config_kF(Constants.DISTANCE_SLOT, Constants.DISTANCE_GAINS.kF, Constants.TIMEOUT_MS);
		talonR.config_IntegralZone(Constants.DISTANCE_SLOT, Constants.DISTANCE_GAINS.kIzone, Constants.TIMEOUT_MS);
		talonR.configClosedLoopPeakOutput(Constants.DISTANCE_SLOT, Constants.DISTANCE_GAINS.kPeakOutput, Constants.TIMEOUT_MS);
		talonR.configAllowableClosedloopError(Constants.DISTANCE_SLOT, 0, Constants.TIMEOUT_MS);

		/* FPID Gains for turn servo */
		talonR.config_kP(Constants.TURN_SLOT, Constants.TURN_GAINS.kP, Constants.TIMEOUT_MS);
		talonR.config_kI(Constants.TURN_SLOT, Constants.TURN_GAINS.kI, Constants.TIMEOUT_MS);
		talonR.config_kD(Constants.TURN_SLOT, Constants.TURN_GAINS.kD, Constants.TIMEOUT_MS);
		talonR.config_kF(Constants.TURN_SLOT, Constants.TURN_GAINS.kF, Constants.TIMEOUT_MS);
		talonR.config_IntegralZone(Constants.TURN_SLOT, (int)Constants.TURN_GAINS.kIzone, Constants.TIMEOUT_MS);
		talonR.configClosedLoopPeakOutput(Constants.TURN_SLOT, Constants.TURN_GAINS.kPeakOutput, Constants.TIMEOUT_MS);
		talonR.configAllowableClosedloopError(Constants.TURN_SLOT, 0, Constants.TIMEOUT_MS);

		/**
		 * 1ms per loop.  PID loop can be slowed down if need be.
		 * For example,
		 * - if sensor updates are too slow
		 * - sensor deltas are very small per update, so derivative error never gets large enough to be useful.
		 * - sensor movement is very slow causing the derivative error to be near zero.
		 */
		int closedLoopTimeMs = 1;
		talonR.configClosedLoopPeriod(0, closedLoopTimeMs, Constants.TIMEOUT_MS);
		talonR.configClosedLoopPeriod(1, closedLoopTimeMs, Constants.TIMEOUT_MS);

		/**
		 * configAuxPIDPolarity(boolean invert, int timeoutMs)
		 * false means talon's local output is PID0 + PID1, and other side Talon is PID0 - PID1
		 * true means talon's local output is PID0 - PID1, and other side Talon is PID0 + PID1
		 */
		talonR.configAuxPIDPolarity(false, Constants.TIMEOUT_MS);

		
		talonR.setStatusFramePeriod(StatusFrameEnhanced.Status_10_Targets, 10);
   
		talonR.configNominalOutputForward(0.21);
		talonL.configNominalOutputForward(0.21);
		talonR.configNominalOutputReverse(0.21);
		talonL.configNominalOutputReverse(0.21);
	
	}
}
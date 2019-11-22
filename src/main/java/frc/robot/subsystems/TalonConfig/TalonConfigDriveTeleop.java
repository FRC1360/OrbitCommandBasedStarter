package frc.robot.subsystems.TalonConfig;


import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class TalonConfigDriveTeleop {

    public static void config(WPI_TalonSRX talonL, WPI_TalonSRX talonR)
    {
        talonR.configOpenloopRamp(0);
        talonL.configOpenloopRamp(0);
        talonR.setNeutralMode(NeutralMode.Coast);
        talonL.setNeutralMode(NeutralMode.Coast);
        
    }
}
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

    public WPI_TalonSRX talonL;
    public WPI_TalonSRX talonR;
    
    public WPI_VictorSPX victorL;
    public WPI_VictorSPX victorR;

    public DriveTrain () {
        
    }
    
    @Override
    protected void initDefaultCommand() {

    }
    
}
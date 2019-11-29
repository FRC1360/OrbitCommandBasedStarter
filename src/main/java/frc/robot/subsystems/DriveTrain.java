package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {

    private WPI_TalonSRX talonL;
    private WPI_TalonSRX talonR;

    private DifferentialDrive drive;

    public DriveTrain(int talonPortL, int talonPortR){
        this.talonL = new WPI_TalonSRX(talonPortL);
        this.talonR = new WPI_TalonSRX(talonPortR);

        this.drive = new DifferentialDrive(talonL, talonR);
    }

    public void arcadeDrive(double speed, double rotation){
        this.drive.arcadeDrive(speed, rotation);
    }

    @Override
    protected void initDefaultCommand() {

    }
    
}
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.elevator.DefaultDefaultElevatorCommand;
import frc.robot.commands.elevator.DefaultElevator;
import frc.robot.commands.elevator.ElevatorManualCommand;
import frc.robot.commands.groups.ElevatorManualGroup;
import frc.robot.subsystems.TalonConfig.TalonConfigElevator;
import frc.robot.util.Constants;
/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {

    public WPI_TalonSRX elevatorMotorMaster = new WPI_TalonSRX(RobotMap.ELEVATOR_CAN_ID_LEFT_FRONT_MASTER);
    private WPI_VictorSPX elevatorMotorSlave0 = new WPI_VictorSPX(RobotMap.ELEVATOR_CAN_ID_RIGHT_FRONT_SLAVE);
    private WPI_VictorSPX elevatorMotorSlave1 = new WPI_VictorSPX(RobotMap.ELEVATOR_CAN_ID_LEFT_REAR_SLAVE);
    private WPI_VictorSPX elevatorMotorSlave2 = new WPI_VictorSPX(RobotMap.ELEVATOR_CAN_ID_RIGHT_REAR_SLAVE);
    
    private int holdPos;
    public int target;
    public Position currentPosition = Position.DEFAULT;

    public int timer = 0;

    /*public static final int TOP = 1500; //cargo ground intake
    public static final int HATCH1 = 100; //bottom hatch level
    public static final int HATCH2 = 600; //middle hatch rocket
    public static final int HATCH3 = 1100; //top hatch rocket
    public static final int CARGO1 = 200; //bottom cargo rocket
    public static final int CARGO2 = 700; //middle cargo rocket
    public static final int CARGO3 = 1200; //top cargo rocket
    public static final int CARGOSHIP = 100; //cargo in cargo ship*/

    //TODO - Make sure positions are correct
    public enum Position {
        TOP(Constants.TOP),
        HATCH1(Constants.HATCH_1), 
        HATCH2(Constants.HATCH_2), 
        HATCH3(Constants.HATCH_3),
        CARGO1(Constants.CARGO_1),
        CARGO2(Constants.CARGO_2),
        CARGO3(Constants.CARGO_3),
        CARGOSHIP(Constants.CARGOSHIP),
        INTAKE(Constants.INTAKE),
        DEFAULT(Constants.DEFAULT);
        

        
        private final int value;
        private Position(int value) {
           
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
 
    public Elevator() {
        //elevatorMotorMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
        //elevatorMotorMaster.configForwardLimitSwitchSource(, true);
        //elevatorMotorMaster.configForwardLimitSwitchSource(LimitSwitchSource.RemoteTalonSRX, LimitSwitchNormal.NormallyOpen, RobotMap.ELEVATOR_CAN_ID_LEFT_FRONT_MASTER);
        TalonConfigElevator.config(elevatorMotorMaster, elevatorMotorSlave0, elevatorMotorSlave1,elevatorMotorSlave2);
        elevatorMotorSlave0.follow(elevatorMotorMaster);
        elevatorMotorSlave0.setInverted(true); 
        

        elevatorMotorSlave2.follow(elevatorMotorMaster);
        elevatorMotorSlave2.setInverted(true); 

        elevatorMotorSlave1.follow(elevatorMotorMaster);
      
       // elevatorMotorMaster.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
       // elevatorMotorMaster.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
       
        elevatorMotorMaster.configForwardSoftLimitEnable(false);
        elevatorMotorMaster.configForwardSoftLimitThreshold(2000);

        

    }
    public void configElevatorTel()
    {
        TalonConfigElevator.config(elevatorMotorMaster, elevatorMotorSlave0, elevatorMotorSlave1,elevatorMotorSlave2);
    }
    public void setHoldPos(int holdPos)
    {
        this.holdPos = holdPos;
    }

    public int getHoldPos()
    {
        return holdPos;
    }

    public void moveManual(double speed) {
        SmartDashboard.putNumber("Elev pos", elevatorMotorMaster.getSelectedSensorPosition());
        elevatorMotorMaster.set(ControlMode.PercentOutput, speed);
        elevatorMotorSlave0.follow(elevatorMotorMaster);
        elevatorMotorSlave1.follow(elevatorMotorMaster);
        elevatorMotorSlave2.follow(elevatorMotorMaster);
       
       // elevatorMotorSlave0.set(ControlMode.PercentOutput, speed);
        //elevatorMotorSlave1.set(ControlMode.PercentOutput, speed);
        //elevatorMotorSlave2.set(ControlMode.PercentOutput, speed);
    }

    public int getElevatorEnc() {
        return elevatorMotorMaster.getSelectedSensorPosition();
    }

    public void moveToPosition()
    {
     
        elevatorMotorMaster.set(ControlMode.MotionMagic, holdPos);
        elevatorMotorSlave0.follow(elevatorMotorMaster);
        elevatorMotorSlave1.follow(elevatorMotorMaster);
        elevatorMotorSlave2.follow(elevatorMotorMaster);
       
        //System.out.println(holdPos + "   " + elevatorMotorMaster.getClosedLoopTarget());
       
    }
    public void configElevator()
    {
        TalonConfigElevator.config(elevatorMotorMaster, elevatorMotorSlave0, elevatorMotorSlave1,elevatorMotorSlave2);
    }

    public void setToBottom()
    {
        
        if( /*Robot.oi.noOperatorButtonPressed() && */ (elevatorMotorMaster.getSelectedSensorPosition() < Position.DEFAULT.getValue() + 100)) {
            elevatorMotorMaster.set(ControlMode.PercentOutput, 0.0);
            elevatorMotorSlave0.follow(elevatorMotorMaster);
            elevatorMotorSlave1.follow(elevatorMotorMaster);
            elevatorMotorSlave2.follow(elevatorMotorMaster);
            timer = 0;
        }  
        else{
            if(timer < 50) {
                setHoldPos(holdPos);
                moveToPosition();
                timer++;
            } else {
            setHoldPos(Elevator.Position.DEFAULT.getValue());
            moveToPosition();
            }
        }
    }

    public void dropElevator()
    {
        if( holdPos == Position.DEFAULT.getValue() &&  (elevatorMotorMaster.getSelectedSensorPosition() < Position.DEFAULT.getValue() + 200)) {
            
          

            elevatorMotorMaster.set(ControlMode.PercentOutput, 0.0);
            elevatorMotorSlave0.set(ControlMode.PercentOutput, 0.0);
            elevatorMotorSlave1.set(ControlMode.PercentOutput, 0.0);
            elevatorMotorSlave2.set(ControlMode.PercentOutput, 0.0);
            
        }  

//        System.out.println("Elevator is after the if");
  //      System.out.println(Robot.oi.noOperatorButtonPressed());
    //    System.out.println(elevatorMotorMaster.getSelectedSensorPosition() < Position.DEFAULT.getValue() + 200);
    }

    private boolean isBottomLimitHit() {
        
        return false;
    }

    

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
                                                                //setDefaultCommand(new DefaultElevator());
        //setDefaultCommand(new ElevatorManualGroup());
        setDefaultCommand(new DefaultDefaultElevatorCommand());
    }
}

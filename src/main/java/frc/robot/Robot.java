package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.claw.TopClawPositionCommand;
import frc.robot.subsystems.*;
import frc.robot.subsystems.TalonConfig.TalonConfigDrive;
import frc.robot.subsystems.TalonConfig.TalonConfigElevator;
import frc.robot.util.Constants;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  public static drivetrain2 drivetrain = 
  new drivetrain2(
          RobotMap.DRIVETRAIN_CAN_ID_LEFT_FRONT_MASTER,
          RobotMap.DRIVETRAIN_CAN_ID_RIGHT_FRONT_MASTER,
          RobotMap.DRIVETRAIN_CAN_ID_LEFT_REAR_SLAVE,
          RobotMap.DRIVETRAIN_CAN_ID_RIGHT_REAR_SLAVE);



  public static OI oi;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    oi = new OI();
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

  }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
         Robot.Drivetrain2.talonL.setNeutralMode(NeutralMode.Coast);
		 Robot.Drivetrain2.talonR.setNeutralMode(NeutralMode.Coast);
    }

    @Override
    public void disabledPeriodic() {
        //Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_chooser.getSelected();

        /*
         * String autoSelected = SmartDashboard.getString("Auto Selector",
         * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
         * = new MyAutoCommand(); break; case "Default Auto": default:
         * autonomousCommand = new ExampleCommand(); break; }
         */

        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
        //elevator.setHoldPos(0);
         //elevator.setToBottom();
      
    //    // drivetrain.driveStraight(3000);
    //     led.setColour(Constants.LED_COLOUR_NONE);
        elevator.configElevatorTel();
         Robot.Drivetrain2.talonL.setNeutralMode(NeutralMode.Brake);
         Robot.Drivetrain2.talonR.setNeutralMode(NeutralMode.Brake);
         claw.setIgnoreSensor(false);
    }

    /**
     * 
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        oi.oiCycle();
       //elevator.moveToPosition(1000);
      //  System.out.println(elevator.elevatorMotorMaster.getClosedLoopError());
      //drivetrain.tankDrive(0, 0);
        Scheduler.getInstance().run();
      
        //SmartDashboard.putBoolean("hatch sensor check this one", claw.hasHatchTop());
    //    SmartDashboard.putNumber("target elevator", elevator.elevatorMotorMaster.getClosedLoopTarget());
    //     SmartDashboard.putBoolean("forward lim", Robot.elevator.elevatorMotorMaster.getSensorCollection().isFwdLimitSwitchClosed());
    //     SmartDashboard.putBoolean("reverse lim", Robot.elevator.elevatorMotorMaster.getSensorCollection().isRevLimitSwitchClosed());
          SmartDashboard.putNumber("encoder elevator", Robot.elevator.getElevatorEnc());
         //SmartDashboard.putNumber("error elevator", Robot.elevator.elevatorMotorMaster.getClosedLoopError());
    //     SmartDashboard.putNumber("joystcik val", Robot.elevator.elevatorMotorMaster.getSelectedSensorVelocity());
      //  System.out.println( Robot.elevator.getElevatorEnc());
        SmartDashboard.putNumber("target e velocity", Robot.elevator.elevatorMotorMaster.getActiveTrajectoryVelocity());
        SmartDashboard.putNumber("elevator velocity", Robot.elevator.elevatorMotorMaster.getSelectedSensorVelocity());
        //elevator.setToBottom(); 
        //drivetrain.driveStraight(3000);
        //SmartDashboard.putNumber("left Velocity", Robot.drivetrain.talonL.getSelectedSensorVelocity());
         SmartDashboard.putNumber("distance error", drivetrain2.talonR.getClosedLoopTarget(Constants.PID_PRIMARY));
         SmartDashboard.putNumber("current", drivetrain2.talonR.getOutputCurrent());
         SmartDashboard.putNumber("target velocity", drivetrain2.talonR.getActiveTrajectoryVelocity());
         SmartDashboard.putNumber("right velocity", drivetrain2.talonR.getSelectedSensorVelocity());
         SmartDashboard.putNumber("drive POS", drivetrain2.talonR.getSelectedSensorPosition());
         SmartDashboard.putNumber("diff velocity", drivetrain2.talonR.getSelectedSensorVelocity() - drivetrain2.talonL.getSelectedSensorVelocity());
         SmartDashboard.putNumber("turn error", drivetrain2.talonR.getClosedLoopError(Constants.PID_TURN));
         SmartDashboard.putNumber("drive error", drivetrain2.talonR.getClosedLoopError(Constants.PID_PRIMARY));
        //System.out.println(oi.operatorController.getY(Hand.kLeft));
       // System.out.println(oi.operatorController.getRawAxis(1));

      
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}

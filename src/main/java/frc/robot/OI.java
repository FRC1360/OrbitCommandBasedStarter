package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.commands.elevator.*;
import frc.robot.commands.claw.*;
import frc.robot.commands.intake.*;
import frc.robot.commands.claw.BottomClawPositionCommand;
import frc.robot.commands.claw.TopClawPositionCommand;
import frc.robot.commands.elevator.ElevatorGoToTarget;
import frc.robot.commands.groups.ElevatorDefaultGroup;
import frc.robot.commands.groups.ElevatorGoToTargetGroup;
import frc.robot.commands.groups.IntakeCargo;
import frc.robot.commands.groups.IntakeGroup;
import frc.robot.commands.groups.IntakeHatch;
import frc.robot.commands.groups.KillIntake;
import frc.robot.commands.groups.OutakeCargo;
import frc.robot.commands.groups.OuttakeGroup;
import frc.robot.commands.groups.VisionDrivePID;
import frc.robot.commands.intake.IntakePistonCommand;
import frc.robot.subsystems.Claw;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.subsystems.Elevator;
import frc.robot.util.Constants;



/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a
    //// joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    public enum Mode {
        MANUAL(0, "Manual"),
        ASSIST(1,"Assist"),
        DEFENSE(2,"Defense"),
        CLIMB(3,"Climb");
        private String mode;
        private int value;
        private Mode(int x, String val)
        {
            this.mode = val;
            this.value = x;
        }

        public String getMode()
        {
            return mode;
        }

        public int getValue()
        {
            return value;
        }
        
    }
    private XboxController driverController = new XboxController(RobotMap.CONTROLLER_PORT_DRIVER);
    public XboxController operatorController = new XboxController(RobotMap.CONTROLLER_PORT_OPERATOR);
    private boolean manualOverride = true;
    

    //TODO : Make sure button values are correct
    private Button driverA = new JoystickButton(driverController, 0);
    private Button driverB = new JoystickButton(driverController, 1);
    private Button driverX = new JoystickButton(driverController, 2);
    private Button driverY = new JoystickButton(driverController, 3);

    private Button operatorA = new JoystickButton(operatorController, 1);
    private Button operatorB = new JoystickButton(operatorController, 2);
    private Button operatorX = new JoystickButton(operatorController, 3);
    private Button operatorY = new JoystickButton(operatorController, 4);
    public int controlMode = Mode.MANUAL.getValue();
    private String currentMode = Mode.MANUAL.getMode();


    
    public OI() {

    }

    public void oiCycle()
    {
        SmartDashboard.putString("Current Mode", currentMode);
        CommandGroup group = new CommandGroup();
/*
        //--------DRIVER TRYOUTS------------
        //group.addSequential(new IntakeCargo(AButtonPressedOperator()));
        group.addSequential(new IntakeHatch(BButtonPressedOperator()));
        //group.addSequential(new KillIntake(noOperatorButtonPressed()));

        group.addSequential(new OutakeCargo(getOperatorRightTrigger()> 0.5));
        group.addSequential(new IntakeCargo(operatorController.getBumper(Hand.kRight)));


        */
        
        if(getLeftJoystickButtonOP())
        {
            Robot.claw.toggleIgnoreSensor();
        }

        if(dPadRightPressed() || dPadRightPressedD())
        {
            currentMode = Mode.DEFENSE.getMode();
            controlMode = Mode.DEFENSE.getValue();
            SmartDashboard.putString("Current Mode", currentMode);
        }
        if(dPadLeftPressed() || dPadLeftPressedD())
        {
            currentMode = Mode.MANUAL.getMode();
            controlMode = Mode.MANUAL.getValue();
            SmartDashboard.putString("Current Mode", currentMode);
        }
        if(dPadDownPressed() || dPadDownPressedD())
        {
            currentMode = Mode.CLIMB.getMode();
            controlMode = Mode.CLIMB.getValue();
            SmartDashboard.putString("Current Mode", currentMode);
        }
        if(dPadUpPressed() || dPadUpPressedD())
        {
            currentMode = Mode.ASSIST.getMode();
            controlMode = Mode.ASSIST.getValue();
            SmartDashboard.putString("Current Mode", currentMode);
        }
        group.addSequential(new OuttakeGroup());
        group.addSequential(new IntakeGroup());
        //-----------ACTUAL STUFF-------------

        if(/*controlMode == Mode.MANUAL.getValue()*/true) {
            Robot.claw.setWrist(true);
         if(Robot.claw.hasHatchTop()) {
            Robot.led.setColour(Constants.LED_COLOUR_YELLOW);
            //group.addSequential(new ElevatorGoToTargetGroup(AButtonPressedOperator(), Elevator.Position.INTAKE));
            group.addSequential(new ElevatorGoToTargetGroup(BButtonPressedOperator(), Elevator.Position.HATCH1));
            group.addSequential(new ElevatorGoToTargetGroup(XButtonPressedOperator(), Elevator.Position.HATCH2));
            group.addSequential(new ElevatorGoToTargetGroup(YButtonPressedOperator(), Elevator.Position.HATCH3));
            
        }
         else if(Robot.claw.hasBall()) {
            Robot.led.setColour(Constants.LED_COLOUR_RED);
           // group.addSequential(new ElevatorGoToTargetGroup(AButtonPressedOperator(), Elevator.Position.CARGOSHIP));
            group.addSequential(new ElevatorGoToTargetGroup(BButtonPressedOperator(), Elevator.Position.CARGO1));
            group.addSequential(new ElevatorGoToTargetGroup(XButtonPressedOperator(), Elevator.Position.CARGO2));
            group.addSequential(new ElevatorGoToTargetGroup(YButtonPressedOperator(), Elevator.Position.CARGO3));
            
        } else {
            Robot.led.setColour(Constants.LED_COLOUR_BLUE);
            group.addSequential(new ElevatorGoToTargetGroup(AButtonPressedOperator(), Elevator.Position.INTAKE));
          //  group.addSequential(new ElevatorGoToTargetGroup(BButtonPressedOperator(), Elevator.Position.CARGOSHIP));
        }
        group.addSequential(new ElevatorDefaultGroup(AButtonPressedOperator()));
        group.addSequential(new VisionDrivePID(getDriverAButton()));
   }
    else if(controlMode == Mode.DEFENSE.getValue())
    {
        Robot.claw.setWrist(false);
    }

    else if(controlMode == Mode.ASSIST.getValue())
    {
        Robot.claw.setWrist(true);
    }

    else if(controlMode == Mode.CLIMB.getValue())
    {
        
    }

        
        group.start();
        group.close();
    }


    //DRIVER CONTROLS
    public boolean getDriverAButton()
    {
        return driverController.getAButton();
    }
    public boolean getDriverBButton()
    {
        return driverController.getBButton();
    }
    public boolean getDriverXButton()
    {
        return driverController.getXButton();
    }
    public boolean getDriverYButton()
    {
        return driverController.getYButton();
    }

    public double getRightTriggerDriver() {
        return deadzone(driverController.getTriggerAxis(GenericHID.Hand.kRight), 0.1);
    }

    public double getLeftTriggerDriver() {
        return deadzone(driverController.getTriggerAxis(GenericHID.Hand.kLeft), 0.1);
    }
    public boolean getLeftJoystickButtonOP()
    {
        return operatorController.getStickButtonPressed(Hand.kLeft);
    }
    public boolean getRightJoystickButtonOp()
    {
        return operatorController.getStickButton(Hand.kRight);
    }
    public double getLeftJoystickDriver() {
        return deadzone(driverController.getX(GenericHID.Hand.kLeft), 0.3);
    }
    public boolean getLeftOperatorBumper()
    {
        return operatorController.getBumper(Hand.kLeft);
    }

    public boolean getRightOperatorBumper()
    {
        return operatorController.getBumper(Hand.kRight);
    }

    public double throttle() {
        return getRightTriggerDriver() - getLeftTriggerDriver();

    }
    public boolean isAButtonPressedDriver(){
        return driverA.get();
    }

    public boolean isBButtonPressedDriver(){
        return driverB.get();
    }

    public boolean isXButtonPressedDriver(){
        return driverX.get();
    }

    public boolean isYButtonPressedDriver(){
        return driverY.get();
    }

    private double deadzone(double Input, double deadzone) {
        if (Math.abs(Input) > deadzone) {
            return Input;
        }
        return 0;
    }



    //OPERATOR CONTROLS

    public double getOperatorLeftTrigger(){
        return deadzone(operatorController.getTriggerAxis(Hand.kLeft),0.3);
    } 
    
    public double getOperatorRightTrigger(){
        return deadzone(operatorController.getTriggerAxis(Hand.kRight),0.3);
        
    }

    public boolean AButtonPressedOperator(){
        //return operatorA.get();
        
        return operatorController.getAButton();
    }

    public boolean BButtonPressedOperator(){
        //return operatorB.get();
       
        return operatorController.getBButton();
    }

    public boolean XButtonPressedOperator(){
        //return operatorX.get();
        
        return operatorController.getXButton();
    }

    public boolean YButtonPressedOperator(){
        //return operatorY.get();
         
        return operatorController.getYButton();
    }

    public double getLeftJoystickYOperator() {
        //System.out.println(operatorController.getY(GenericHID.Hand.kLeft));
        return deadzone(operatorController.getY(Hand.kLeft), 0.3);
    }

    public double getLeftJoystickXOperator() {
        return deadzone(operatorController.getX(GenericHID.Hand.kRight), 0.1);
    }

    public double getRightJoystickYOperator()
    {
        return deadzone(operatorController.getY(GenericHID.Hand.kRight), 0.2);
    }

    public double getRightJoystickXOperator()
    {
        return deadzone(operatorController.getX(Hand.kRight), 0.1);
    }

    public boolean dPadUpPressed()
    {
        return (operatorController.getPOV() < 45 || operatorController.getPOV() > 315) && operatorController.getPOV() != -1;
    }    

    public boolean dPadDownPressed()
    {
        return (operatorController.getPOV() < 225 && operatorController.getPOV() > 135) && operatorController.getPOV() != -1;
    }

    public boolean dPadLeftPressed()
    {
        return (operatorController.getPOV() < 315 && operatorController.getPOV() > 225) && operatorController.getPOV() != -1;
    }

    public boolean dPadRightPressed()
    {
        return (operatorController.getPOV() < 135 && operatorController.getPOV() > 45) && operatorController.getPOV() != -1;
    }
    public boolean dPadUpPressedD()
    {
        return (driverController.getPOV() < 45 || driverController.getPOV() > 315) && driverController.getPOV() != -1;
    }    

    public boolean dPadDownPressedD()
    {
        return (driverController.getPOV() < 225 && driverController.getPOV() > 135) && driverController.getPOV() != -1;
    }

    public boolean dPadLeftPressedD()
    {
        return (driverController.getPOV() < 315 && driverController.getPOV() > 225) && driverController.getPOV() != -1;
    }

    public boolean dPadRightPressedD()
    {
        return (driverController.getPOV() < 135 && driverController.getPOV() > 45) && driverController.getPOV() != -1;
    }

    public boolean noOperatorButtonPressed()
    {
        return !AButtonPressedOperator() && !BButtonPressedOperator() && !YButtonPressedOperator() && !XButtonPressedOperator() ;// && !operatorController.getBumper(Hand.kRight);
    }

    public boolean StartButtonPressedOperator()
    {
        return operatorController.getStartButton();
    }
}

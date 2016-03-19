package com.qualcomm.ftcrobotcontroller.opmodes;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Jarred on 10/18/2015.
 */
public class CompTeleScranton extends OpMode {


    UltrasonicSensor distance;
    UltrasonicSensor distance2;

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;

    ColorSensor color;


    Servo leftGo;
    Servo rightGo;
    Servo pivot;
    Servo swoop;
    Servo screw;
    Servo elbow;
    //Servo extend;


    //TouchSensor posOne;
    //TouchSensor posTwo;
    TouchSensor limit;


    int direction;

    ElapsedTime shiftHelp;

    double drive;

    AnalogInput posOne;
    AnalogInput posTwo;

    int currentPos;
    int directionGo;
    double stager;

    ElapsedTime timer;

    boolean stateOne;
    boolean stateTwo;


    int stateOneTest;
    int stateTwoTest;

    Servo extend;
/*
    above we declare all of the variables, sensors, and motors, we are using
 */



/*
        double drive = 1;
        direction = 1;

        pivot.setPosition(.77);
        screw.setPosition(.5);

        hook.setPosition(1);

        leftGo.setPosition(0);
          rightGo.setPosition(.8);
*/


    /*
    Above, in the init phase, we map all of the hardware.
     */

    public int updateState() {

        stateOneTest = posOne.getValue();
        stateTwoTest = posTwo.getValue();

        telemetry.addData("state1", stateOneTest);
        telemetry.addData("state2", stateTwoTest);

        stateOne = stateOneTest >= 1020;
        stateTwo = stateTwoTest >= 1020;

        if (stateOne == false && stateTwo == true) {
            currentPos = 1;

        } else if (stateOne == true && stateTwo == false) {
            currentPos = 2;
        } else if (stateOne == false && stateTwo == false) {
            currentPos = 3;
        }

        return currentPos;

    }
    /*
    The above method checks the states of both of the limit switches
    It then interprets these states into positions.
     */

    public void moveNet(double stager) {


        currentPos = updateState();

        if (stager != currentPos) {
            swoop.setPosition(0);

        }// else if (stager < currentPos) {
//
        //          swoop.setPosition(1);
        else if (stager == currentPos) {
            swoop.setPosition(.502);

        }

    }
    /*
    The above custom method compares the current position and the needed
     position of the debris scoop to decide if the scoop needs to be rotated.
     */

    public void init() {
        motor1 = hardwareMap.dcMotor.get("motor1");//motor1 on AL00VTH7
        motor2 = hardwareMap.dcMotor.get("motor2");//motor2 on AL00VTH7
        motor3 = hardwareMap.dcMotor.get("motor3");//motor3 on AL00YC5Z
        motor4 = hardwareMap.dcMotor.get("motor4");//motor4 on AL00YC5Z
        motor5 = hardwareMap.dcMotor.get("motor5");
        motor6 = hardwareMap.dcMotor.get("motor6");
        color = hardwareMap.colorSensor.get("color");//beacon sensor
        screw = hardwareMap.servo.get("screw");
        pivot = hardwareMap.servo.get("pivot");
        leftGo = hardwareMap.servo.get("backGo");
        rightGo =hardwareMap.servo.get("frontGo");
        distance = hardwareMap.ultrasonicSensor.get("distance");
        distance2 = hardwareMap.ultrasonicSensor.get("distance2");
        swoop = hardwareMap.servo.get("swoop");
        elbow = hardwareMap.servo.get("elbow");
        posOne = hardwareMap.analogInput.get("A0");
        posTwo = hardwareMap.analogInput.get("A1");
        limit = hardwareMap.touchSensor.get("limit");
        extend = hardwareMap.servo.get("swing");

        motor1.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor2.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor3.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor4.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        motor1.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor2.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor3.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);
        motor4.setChannelMode(DcMotorController.RunMode.RUN_USING_ENCODERS);

        pivot.setPosition(.9);
        screw.setPosition(.5);
        leftGo.setPosition(0.0);
        rightGo.setPosition(.8);

        extend.setPosition(1);

        //  swoop.setPosition(.5);
        //   elbow.setPosition(1);
        drive = 1;
        direction=1;
//
        shiftHelp = new ElapsedTime();
        shiftHelp .startTime();

        stager = 2;
        elbow.setPosition(.823);
        swoop.setPosition(0.502);

        timer = new ElapsedTime();
        timer.startTime();


        /*
        Above we align all of our motors, servos, and sensors, to the config file and set all of the
        timers and servos to their required starting positions.
         */
    }
    @Override
    public void loop() {



        updateState();
        telemetry.addData("stager", stager);
        telemetry.addData("current", currentPos);
        telemetry.addData("timer", timer.toString());
        telemetry.addData(("spin"), swoop.getPosition());


        if (gamepad1.dpad_right && timer.time() >= 1) {
            stager += 1;
            telemetry.addData("shifted", "up");
            timer.reset();
        } else if (gamepad1.dpad_left && timer.time() >= 1) {
            stager -= 1;
            telemetry.addData("shifted", "down");
            timer.reset();
        }
        if (stager > 3) {
            stager = 3;
        }
        if (stager < 1) {
            stager = 1;
        }

        /*
        Above is the stage shifter for the debris scoop. It prevents the need stage
        value from exceeding it's range. There is also a timer that prevents the robot from
        moving multiple stages when a button is pressed.

         */

        if (gamepad2.y == true) {

            elbow.setPosition(.286);

        } else if (gamepad2.x == true && currentPos == 2) {
            elbow.setPosition(.823);
        }

        /*
        Above is the controls for the servo that lifts and drops the debris scoop. However id the
        scoop is not at position 2, the scoop will not be lifted into the robot. This prevents
         damage to the scoop.
         */


            telemetry.addData("drive is ", drive);
            telemetry.addData("screw", screw.getDirection());
            telemetry.addData("pivot is at", pivot.getPosition());
            telemetry.addData("leftGo (P1.LT=down)", leftGo.getPosition());
            telemetry.addData("right toucher is at", rightGo.getPosition());

            if (direction == 1) {
                telemetry.addData("direction", "forward");
            }
            if (direction == -1) {
                telemetry.addData("direction", "reverse");

            }



            motor4.setPower((gamepad1.left_stick_y* -1*drive*direction));
            motor3.setPower((gamepad1.left_stick_y* -1*drive*direction));
            motor2.setPower((gamepad1.right_stick_y*drive*direction));
            motor1.setPower((gamepad1.right_stick_y*drive*direction ));


            if (gamepad1.right_bumper == true && shiftHelp.time() >= 1) {
                drive += .25;
                telemetry.addData("shifted", "up");
                shiftHelp.reset();
            }
            if (gamepad1.left_bumper == true && shiftHelp.time() >= 1) {
                drive -= .25;
                telemetry.addData("shifted", "down");
                shiftHelp.reset();
            }
            if (drive > 1) {
                drive = 1;
            }
            if (drive < .25) {
                drive = .25;
            }
            if (gamepad1.dpad_up == true) {
                direction = 1;
            } else if (gamepad1.dpad_down == true) {
                direction = -1;
            }

            telemetry.addData("Left drive control", gamepad1.left_stick_y);
            telemetry.addData("Right drive control", gamepad1.right_stick_y);

/*
above is the code that is used to drive the robot using the left and right sticks on the first controller.
Also, above is the the shifter for the drive train that allows the drive train to run at 4 different speeds
 with the default speed being 1. There is also a default direction toggle that controls which way the robot
  is moving when the y values of the joysticks on the first controller are positive.
 */
            int armExtend = Math.round(gamepad2.left_stick_y);
            float armRotate = gamepad2.right_stick_y;

            if (limit.isPressed() == true) {
                motor6.setPower((-1 *(Math.abs(gamepad2.right_stick_y))) / 2);
            } else {
                motor6.setPower(armExtend);
            }

            motor5.setPower(armRotate);

            if (armExtend >= .25) {
                telemetry.addData("arm", "extending");
            }
            if (armExtend <= -.25) {
                telemetry.addData("arm", "retracting");
            }



        /*
            In the above 16 lines, we have telemetry data for the main arm.
            There is also controls for the main arm rotation as well as
            controls for the main arm extension and a limiter that controls
            how farm the main arm can extend.
        */

            if (gamepad2.right_bumper == true) {
                pivot.setPosition(.81);
            }

            if (gamepad2.right_trigger >= .75) {
                pivot.setPosition(Servo.MIN_POSITION);
            }

            if (gamepad2.left_bumper == true) {
                screw.setPosition(0.0);
            }
            else {

                screw.setPosition(.5);
            }

        /*
        The above lines of code control the corkscrew and the arm it is mounted on.
        The arm moves from maximum to minimum positions and also toggles the corkscrew
        */

            if (gamepad1.left_trigger >= .5) {
                leftGo.setPosition((gamepad1.left_trigger * .9));
                telemetry.addData("P1.LT Pressed", "true");
            } else {
                telemetry.addData("P1.LT Pressed", "false");
                leftGo.setPosition(.0);
            }

            if(gamepad1.right_trigger >= .5) {
                rightGo.setPosition((gamepad1.right_trigger * -.7) + .9);
            }
            else{
                rightGo.setPosition(.8);
            }
 /*
            The abovelines controls the two zip-line trippers.
         */

        moveNet(stager);

        // Above we call for the position of the scoop to be checks and for needed
        //actions be taken to fix it's position


        }
    }

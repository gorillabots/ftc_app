package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.movement;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by Jarred on 10/18/2015.
 */
public class CompTeleScranton extends movement {


    UltrasonicSensor distance;
    UltrasonicSensor distance2;

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;

    ColorSensor color;

    Servo screw;
    Servo leftGo;
    Servo rightGo;
    Servo pivot;
    Servo swoop;
    Servo elbow;
    Servo hook;

    TouchSensor posOne;
    TouchSensor posTwo;
    TouchSensor posThree;
    TouchSensor limit;

    boolean stateOne;
    boolean stateTwo;
    boolean stateThree;
    int currentPos;
    int directionGo;
    int direction;

    double drive;


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
    @Override
    public void runOpMode() {
        _init();

        /*
         above we run the init function from our parent class, this _init function is the same
        function used across all of the competetition
          */

        while (opModeIsActive()) {
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


            float throttleLeft = gamepad1.left_stick_y;
            float throttleRight = gamepad1.right_stick_y;

            motor4.setPower((gamepad1.left_stick_y * drive * -1) * direction);
            motor3.setPower((throttleLeft * drive * -1) * direction);
            motor2.setPower((gamepad1.right_stick_y * drive) * direction);
            motor1.setPower((throttleRight * drive) * direction);


            if (gamepad1.right_bumper == true) {
                drive += .25;
                telemetry.addData("shifted", "up");
            }
            if (gamepad1.left_bumper == true) {
                drive -= .25;
                telemetry.addData("shifted", "down");
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
            int armExtend = Math.round(gamepad2.right_stick_y);
            float armRotate = gamepad2.left_stick_y;

            if (limit.isPressed() == true) {
                motor5.setPower((-1 * (Math.abs(gamepad2.right_stick_y))) / 2);
            } else {
                motor5.setPower(armExtend);
            }
            motor6.setPower(armRotate);

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
            } else if (gamepad2.left_trigger >= .75) {
                telemetry.addData("screw", "off");
                screw.setPosition(.5);
            }

        /*
        The above lines of code control the corkscrew and the arm it is mounted on.
        The arm moves from maximum to minimum positions and also toggles the corkscrew
         */

            if (gamepad1.left_trigger >= .5) {
                leftGo.setPosition((gamepad1.left_trigger * .7));
                telemetry.addData("P1.LT Pressed", "true");
            } else {
                telemetry.addData("P1.LT Pressed", "false");
                leftGo.setPosition(.0);
            }
            if (gamepad1.right_trigger >= .5) {
                rightGo.setPosition((gamepad1.right_trigger * -.7) + .8);
            } else {
                rightGo.setPosition(0.8);
            }

        /*
            The above 13 lines contr0ols the two zip-line trippers.
         */


            if (gamepad1.a == true) {
                hook.setPosition(0.0);
            } else {
                hook.setPosition(1);
            }


        }

    }
}
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by Jarred on 12/27/2015.
 */
public class TapeTest extends OpMode {

    Servo tape;
    Servo rotate;
    Servo tilt;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;

    @Override
    public void init() {

        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");
        motor5 = hardwareMap.dcMotor.get("motor5");
        motor6 = hardwareMap.dcMotor.get("motor6");

        tape = hardwareMap.servo.get("tape");
        tilt = hardwareMap.servo.get("tilt");
        rotate = hardwareMap.servo.get("rotate");

        tape.setPosition(.5);
        rotate.setPosition(.5);
        tilt.setPosition(.5);
    }

    @Override
    public void loop() {

        if (gamepad1.a == true) {
            tape.setPosition(0);

        }
        else if (gamepad1.y == true) {
            tape.setPosition(1);
        }
        else {
            tape.setPosition(.5);
        }


        if (gamepad2.b == true) {
            tilt.setPosition(0);


        }
        else if (gamepad2.x == true) {

            tilt.setPosition(1);

        }
        else {
            tilt.setPosition(.5);
        }


        if (gamepad2.dpad_right == true) {

            rotate.setPosition(0);

        }
        else if (gamepad2.dpad_left == true) {

            rotate.setPosition(1);

        }
        else {
            rotate.setPosition(.5);
        }

    }
}
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by Jarred on 10/18/2015.
 */
public class LinOpTestTele extends LinearOpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;


    public void driveSide(String side, float power) {
        if (side == "left") {
            motor1.setPower((double) power);
            motor2.setPower((double) power);


        } else {
            motor3.setPower((double) power);
            motor4.setPower((double) power);

        }

    }



    @Override
    public void runOpMode() throws InterruptedException {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");
        motor5 = hardwareMap.dcMotor.get("motor5");
        motor6 = hardwareMap.dcMotor.get("motor6");


        waitForStart();
        while(opModeIsActive()) {
            float throttleLeft = gamepad1.left_stick_y;
            float throttleRight = gamepad1.right_stick_y;
            //boolean speedOne = gamepad1.right_bumper;
            //float speedTwo = gamepad1.right_trigger;
            //boolean  speedThree = gamepad1.left_bumper;
            //float speedFour = gamepad1.left_trigger;

            motor1.setPower((throttleLeft * -1));
            motor2.setPower((throttleLeft * -1));
            motor3.setPower((throttleRight));
            motor4.setPower((throttleRight));


            boolean forward = gamepad2.right_bumper;
            float downward = gamepad2.right_trigger;

            boolean rotateOne = gamepad2.left_bumper;
            float rotateTwo = gamepad2.left_trigger;

            if (forward == true) {
                motor5.setPower(.5);
            }

            if (downward == 1.0) {
                motor5.setPower((-.5));
            }

            if (rotateOne == true) {
                motor6.setPower(.5);
            }

            if (rotateTwo == 1.0) {
                motor6.setPower(-.5);
            }

            waitOneFullHardwareCycle();
        }
    }
}
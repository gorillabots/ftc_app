package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by Jarred on 10/6/2015.
 */
public class drivercontrol extends OpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;


    @Override

    public void init() {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor4");

    }
    @Override
    public void loop() {


float throttleLeft = gamepad1.left_stick_y;
        float throttleRight = gamepad1.right_stick_y;
        motor1.setPower(throttleLeft);
        motor2.setPower(throttleLeft);
        motor3.setPower(throttleRight);
        motor4.setPower(throttleRight);




    }
}

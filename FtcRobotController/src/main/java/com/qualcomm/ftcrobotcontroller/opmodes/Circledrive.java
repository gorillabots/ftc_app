package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
/*
 * Created by Jarred on 10/4/2015.
 */
public class Circledrive extends OpMode {
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    boolean wasPressed;
    ElapsedTime clockRun;

    TouchSensor finger;


    @Override
    public void init() {

        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor4");
        finger = hardwareMap.touchSensor.get("touchsensor");
        boolean wasPressed = false;
    }

    @Override
    public void loop() {
        if (finger.isPressed()) {
            wasPressed = true;
            clockRun = new ElapsedTime();
            clockRun.startTime();
        }
        if (wasPressed == true) {
            motor1.setPower(.45);
            motor2.setPower(.45);
            motor3.setPower(-.15);
            motor4.setPower(-.15);


        } else {
            motor1.setPower(.45);
            motor2.setPower(.45);
            motor3.setPower(-.453);
            motor4.setPower(-.453);
        }
    }
}
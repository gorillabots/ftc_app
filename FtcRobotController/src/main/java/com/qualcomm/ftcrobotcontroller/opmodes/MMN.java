package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;


/**
 * Created by Owner on 11/20/2015.
 */
public class MMN extends OpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    ColorSensor colorSensor;
    @Override
    public void init() {

        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");
        colorSensor = hardwareMap.colorSensor.get("ColorSensor");

    }

    @Override
    public void loop() {
        motor1.setPower(0.25);
        motor2.setPower(0.25);
        motor3.setPower(-0.25);
        motor4.setPower(-0.25);

        if (colorSensor.blue() >35) {

           int i = 0;
            while (i<10) {
                motor1.setPower(0.25);
                motor2.setPower(0.25);
                motor3.setPower(0.25);
                motor4.setPower(0.25);
                i = i+1;
            }
            motor1.setPower(0.25);
            motor2.setPower(0.25);
            motor3.setPower(-0.25);
            motor4.setPower(-0.25);
        }
    }
}
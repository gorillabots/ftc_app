package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.LED;
import com.qualcomm.robotcore.hardware.TouchSensor;


/**
 * Created by Michelle on 12/1/2015.
 */
public class Comp_Tst extends LinearOpMode{


    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;
    TouchSensor finger;
    ColorSensor colorSensor;
    Servo pivot;
    Servo screw;
    Servo backGo;
    Servo frontGo;

    public void _init() {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");
        motor5 = hardwareMap.dcMotor.get("motor5");
        motor6 = hardwareMap.dcMotor.get("motor6");
        finger = hardwareMap.touchSensor.get("touchsensor");
        colorSensor = hardwareMap.colorSensor.get("ColorSensor");
        pivot = hardwareMap.servo.get("pivot");
        screw = hardwareMap.servo.get("screw");
        backGo = hardwareMap.servo.get("backGo");
        frontGo = hardwareMap.servo.get("frontGo");
    }

    @Override
    public void runOpMode() throws InterruptedException{

        _init();
        waitForStart();
        motor1.setPower(.45);
        motor2.setPower(.45);
        motor3.setPower(-.45);
        motor4.setPower(-.45);
        sleep(1500);
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);

        motor5.setPower(.25);
        motor6.setPower(.25);
        sleep(10000);

        motor5.setPower(.5);
        sleep(2500);

        motor1.setPower(.45);
        motor2.setPower(.45);
        motor3.setPower(-.15);
        motor4.setPower(-.15);
        sleep(1000);



    }

}
package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


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
    Servo pivot;
    Servo screw;
    Servo leftGo;
    Servo rightGo;

    public void _init() {

        telemetry.addData("Step", "We are in init");

        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");
        motor5 = hardwareMap.dcMotor.get("motor5");
        motor6 = hardwareMap.dcMotor.get("motor6");
        pivot = hardwareMap.servo.get("pivot");
        screw = hardwareMap.servo.get("screw");
        leftGo = hardwareMap.servo.get("backGo");
        rightGo = hardwareMap.servo.get("frontGo");
    }

    @Override
    public void runOpMode() throws InterruptedException{

        _init();
        telemetry.addData("Step", "We are waiting for program to start");
        waitForStart();
        telemetry.addData("Step", "We are going straight forward for 1.5 seconds");
        motor1.setPower(.45);
        motor2.setPower(.45);
        motor3.setPower(-.45);
        motor4.setPower(-.45);
        sleep(1500);

        telemetry.addData("Step", "We stop");
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);

        telemetry.addData("Step", "We are extending both arm motors for 10 seconds");
        motor5.setPower(.25);
        motor6.setPower(.25);
        sleep(10000);

        telemetry.addData("Step", "We are retracting motor 5 for 2.5 seconds");
        motor5.setPower(-.5);
        sleep(2500);

        telemetry.addData("Step", "We are going in circles for 1 second");
        motor1.setPower(.25);
        motor2.setPower(.25);
        motor3.setPower(-.15);
        motor4.setPower(-.15);
        sleep(1000);
        // sleep(1000) is just a guess for now.

        telemetry.addData("Step", "We are retracting motor 5 for 2.5 seconds");
        motor5.setPower(-.5);
        sleep(2500);

        telemetry.addData("Step", "We are retracting motor 6 for 10 seconds");
        motor6.setPower(-.25);
        sleep(10000);

        telemetry.addData("Step", "We are going in reverse circle");
        motor1.setPower(-.25);
        motor2.setPower(-.25);
        motor3.setPower(.15);
        motor4.setPower(.15);
        sleep(1000);

        telemetry.addData("Step", "We are setting pivot to max position for .7 seconds and going back");
        pivot.setPosition(Servo.MAX_POSITION);
        sleep(700);
        pivot.setPosition(Servo.MIN_POSITION);

        telemetry.addData("Step", "We are spinning the corkscrew for .7 seconds each, counterclockwise, stop, clockwise, and stop");
        screw.setPosition(0.0);
        sleep(700);
        screw.setPosition(.5);
        sleep(700);
        screw.setPosition(1.0);
        sleep(700);
        screw.setPosition(.5);

        telemetry.addData("Step", "We are moving backwards while flapping wings for 10 times");
        motor1.setPower(-.45);
        motor2.setPower(-.45);
        motor3.setPower(.45);
        motor4.setPower(.45);

        for(int i = 0; i <= 10; i = i + 1) {
            leftGo.setPosition(.6);
            rightGo.setPosition(.2);
            sleep(150);
            leftGo.setPosition(.0);
            rightGo.setPosition(.8);
            sleep(150);
        }

        telemetry.addData("Step", "We are setting motors off");
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);

    }


}
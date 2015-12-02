package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Jarred on 11/21/2015.
 */
public class CorkscrewTest extends OpMode {

    Servo pivot;
    Servo screw;
    DcMotor motor1;
    Servo backGo;
    Servo frontGo;
    @Override

    public void init () {

        pivot =hardwareMap.servo.get("pivot");
        screw = hardwareMap.servo.get("screw");
        backGo = hardwareMap.servo.get("backGo");
        frontGo = hardwareMap.servo.get("frontGo");

    }
    @Override
    public void loop() {

        boolean pivotP = gamepad2.right_bumper;
        float pivotB = gamepad2.right_trigger;

        boolean corkGo = gamepad2.left_bumper;


        double pivotMin = 0.0;
        double pivotMax = 1.0;
        float corkRun = 1;


        if(gamepad2.right_bumper == true) {
            pivot.setPosition(pivotMax);
        }

        if(gamepad2.right_trigger>= .75) {
            pivot.setPosition(pivotMin);
        }

        if(gamepad2.left_bumper == true) {
            telemetry.addData("screw",screw.getDirection());
            screw.setDirection(Servo.Direction.FORWARD);
        }
        else if(gamepad2.left_trigger >= .75) {
            telemetry.addData("screw","off");
            screw.close();
        }


        boolean toucherEngage = gamepad1.a;
        boolean toucherDisengage = gamepad1.b;


        double touchGo = 0.4;
        double touchReturn = .9;


        if(gamepad1.a == true) {
            telemetry.addData("touchers","down");
            backGo.setPosition(.4);
            frontGo.setPosition(.9);

        }
        else if(gamepad1.b == true) {
            telemetry.addData("touchers","up");
            frontGo.setPosition(.9);
            backGo.setPosition(.4);
        }
/*
        double blocker = 1;
        if(frontGo.getPosition()== 0.0) {
            blocker =1;
        }
        else{
            blocker=.0;
        }
*/






    }


}

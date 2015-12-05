package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Jarred on 11/21/2015.
 */
public class servotestPos extends OpMode {
    Servo backGo;
    Servo frontGo;

    final double rightMaxPos = .3;
    final double leftMaxPos = .3;

    final double rightMinPos = .5;
    final double leftMinPos = .5;

    @Override
    public void init(){
        backGo = hardwareMap.servo.get("backGo");
        frontGo = hardwareMap.servo.get("frontGo");

    }
    @Override
    public void loop(){

        boolean toucherEngage = gamepad1.a;
        boolean toucherDisengage = gamepad1.b;

        telemetry.addData("right", frontGo.getPosition());
        telemetry.addData("left", backGo.getPosition());

        if(toucherDisengage == true) {
            frontGo.setPosition(leftMinPos);
            backGo.setPosition(rightMinPos);
        }
        else {
            frontGo.setPosition(leftMaxPos);
            backGo.setPosition(rightMaxPos);
        }


        telemetry.addData( "pusher1: ", frontGo.getPosition());
        telemetry.addData( "pusher2: ", backGo.getPosition());

    }





}

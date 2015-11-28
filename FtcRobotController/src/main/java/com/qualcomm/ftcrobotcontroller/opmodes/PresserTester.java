package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Jarred on 11/21/2015.
 */
public class PresserTester extends OpMode {
    Servo backGo;
    Servo frontGo;
    final double minPos = .9;
    final double maxPos = .4;

@Override
    public void init(){
    backGo = hardwareMap.servo.get("backGo");
    frontGo = hardwareMap.servo.get("frontGo");

}
    @Override
    public void loop(){

        boolean toucherEngage = gamepad1.a;
        boolean toucherDisengage = gamepad1.b;


        if(toucherDisengage == true) {
            frontGo.setPosition(maxPos);
        }
        else if(toucherEngage == true) {
            backGo.setPosition(minPos);
        }




        telemetry.addData( "pusher1: ", frontGo.getPosition());
        telemetry.addData( "pusher2: ", backGo.getPosition());

    }





}

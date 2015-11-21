package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Jarred on 11/21/2015.
 */
public class PresserTester extends OpMode {
    Servo backGo;
    Servo frontGo;

@Override
    public void init(){
    backGo = hardwareMap.servo.get("backGo");
    frontGo = hardwareMap.servo.get("frontGo");

}
    @Override
    public void loop(){

        boolean toucherEngage = gamepad1.a;
        boolean toucherDisengage = gamepad1.b;


        if(toucherEngage == true) {
            backGo.setPosition(1);
        }
        if(toucherDisengage == true) {
            frontGo.setPosition(-1);
        }


    }





}

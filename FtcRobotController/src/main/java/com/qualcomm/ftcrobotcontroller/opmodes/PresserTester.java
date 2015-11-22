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


        float touchGo = 1;
        float touchReturn = -1;


        if(toucherEngage == true) {
            backGo.setPosition(touchGo);
        }
        if(toucherDisengage == true) {
            frontGo.setPosition(touchReturn);
        }


    }





}

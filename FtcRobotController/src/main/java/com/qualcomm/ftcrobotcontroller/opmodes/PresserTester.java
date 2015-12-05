package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Jarred on 11/21/2015.
 */
public class PresserTester extends OpMode {
    Servo leftGo;
    Servo rightGo;

    final double rightMaxPos = .333;
    final double leftMaxPos = .666;

    final double rightMinPos = 1.0;
    final double leftMinPos = .0;

@Override
    public void init(){
    leftGo = hardwareMap.servo.get("backGo");
    rightGo = hardwareMap.servo.get("frontGo");

    rightGo.setPosition(.8);
    leftGo.setPosition(.0);

}
    @Override
    public void loop(){

        boolean toucherEngage = gamepad1.a;
        boolean toucherDisengage = gamepad1.b;


        if(gamepad1.left_trigger >= .5) {

            leftGo.setPosition(.6);
        }
        else{
            leftGo.setPosition(.0);



        if (gamepad1.right_trigger >= .5) {
                rightGo.setPosition(.2);

        }
            else{
                rightGo.setPosition(0.8);
            }
        }



    }





}

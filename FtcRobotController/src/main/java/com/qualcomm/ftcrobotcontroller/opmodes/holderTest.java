package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.ftcrobotcontroller.opmodes.Comp2Red;
/**
 * Created by Jarred on 2/2/2016.
 */
public class holderTest extends Comp2Red {

    Servo leftHold;
    Servo rightHold;

    ElapsedTime timer;

    double leftPos;
    double rightPos;

    @Override

    public void init(){
        leftHold = hardwareMap.servo.get("leftHold");
        rightHold = hardwareMap.servo.get("rightHold");
        timer = new ElapsedTime();

        leftPos = 1;
        rightPos = 1;

    }
    @Override

    public void loop(){

        if (gamepad2.left_trigger == 1 &&  timer.time() >  1.0){

            leftHold.setPosition(leftPos);
            rightHold.setPosition(rightPos);

            timer.reset();

        }



    }


}

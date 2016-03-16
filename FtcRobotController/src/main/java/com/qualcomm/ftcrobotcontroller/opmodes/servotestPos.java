package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Jarred on 11/21/2015.
 */
public class servotestPos extends OpMode {
    Servo elbow;

    final double rightMaxPos = .3;
    final double leftMaxPos = .3;

    final double rightMinPos = .5;
    final double leftMinPos = .5;

    @Override
    public void init(){
        elbow = hardwareMap.servo.get("elbow");

    }
    @Override
    public void loop(){



    }





}

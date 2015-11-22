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
    @Override

    public void init () {

        pivot =hardwareMap.servo.get("pivot");
        screw = hardwareMap.servo.get("screw");


    }
    @Override
    public void loop() {

        boolean pivotP = gamepad2.right_bumper;
        float pivotB = gamepad2.right_trigger;

        boolean corkGo = gamepad2.left_bumper;


        float pivotMin = -1;
        float pivotMax = 1;
        float corkRun = 1;




        if(pivotP == true) {
            pivot.setPosition(pivotMax);
        }

        if(pivotB>= .75) {
            pivot.setPosition(pivotMin);
        }

        if(corkGo == true) {
            screw.setPosition(corkRun);
        }


    }


}

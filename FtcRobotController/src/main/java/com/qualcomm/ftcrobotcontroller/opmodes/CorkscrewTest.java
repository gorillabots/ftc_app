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
        double pivotMax = 255.0;
        float corkRun = 1;




        if(pivotP == true) {
            pivot.setPosition(pivotMax);
        }

        if(pivotB>= .75) {
            pivot.setPosition(pivotMin);
        }

        if(corkRun == 1) {
            pivot.setDirection(Servo.Direction.FORWARD);
        }


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

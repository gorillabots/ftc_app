package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Jarred on 2/5/2016.
 */
public class BALL extends OpMode {

    Servo swoop;
    Servo elbow;
    double pickUp;
    double retract;


    public void init() {
        swoop = hardwareMap.servo.get("swoop");
        elbow = hardwareMap.servo.get("elbow");
        retract = 1;
        pickUp = 0.;

        swoop.setPosition(.5);
        elbow.setPosition(1);


    }

    public void loop() {


        if (gamepad1.a == true) {
            swoop.setPosition(1);
        } else {

            swoop.setPosition(.5);


            if (gamepad1.y == true) {

                elbow.setPosition(1);

            } else {
                elbow.setPosition(.43);
            }
        }
    }
}

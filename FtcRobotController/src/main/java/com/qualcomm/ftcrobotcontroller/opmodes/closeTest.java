package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by emper on 11/15/2015.
 */

public class closeTest extends LinearOpMode{
    Servo pivot;
    @Override
    public void runOpMode() throws InterruptedException {
            pivot =hardwareMap.servo.get("pivot");
        while(opModeIsActive()) {




            pivot.setPosition(0.0);
            sleep(1000);

            pivot.setPosition(0.1);
            sleep(1000);

            pivot.setPosition(0.2);
            sleep(1000);

            pivot.setPosition(0.3);
            sleep(1000);

            pivot.setPosition(0.4);
            sleep(1000);

            pivot.setPosition(0.5);
            sleep(1000);

            pivot.setPosition(0.6);
            sleep(1000);

            pivot.setPosition(0.7);
            sleep(1000);

            pivot.setPosition(0.8);
            sleep(1000);

            pivot.setPosition(0.9);
            sleep(1000);

            pivot.setPosition(1.0);
            sleep(1000);
        }

        waitForStart();

    }
}
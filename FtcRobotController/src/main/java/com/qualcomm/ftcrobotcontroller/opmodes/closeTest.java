package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
/**
 * Created by emper on 11/15/2015.
 */

public class closeTest extends LinearOpMode{
    Servo backGo;
    Servo frontGo;
    @Override
    public void runOpMode() throws InterruptedException {
            backGo =hardwareMap.servo.get("backGo");
        frontGo = hardwareMap.servo.get("frontGo");
        while(opModeIsActive()) {




            frontGo.setPosition(0.0);
            backGo.setPosition(0.0);
            sleep(1000);


            frontGo.setPosition(0.1);
            backGo.setPosition(0.1);
            sleep(2000);

            frontGo.setPosition(0.2);
            backGo.setPosition(0.2);
            sleep(2000);

            frontGo.setPosition(0.3);
            backGo.setPosition(0.3);
            sleep(2000);

            frontGo.setPosition(0.4);
            backGo.setPosition(0.4);
            sleep(2000);

            frontGo.setPosition(0.5);
            backGo.setPosition(0.5);
            sleep(2000);

            frontGo.setPosition(0.6);
            backGo.setPosition(0.6);
            sleep(2000);

            frontGo.setPosition(0.7);
            backGo.setPosition(0.7);
            sleep(2000);

            frontGo.setPosition(0.8);
            backGo.setPosition(0.8);
            sleep(2000);

            frontGo.setPosition(0.9);
            backGo.setPosition(0.9);
            sleep(2000);

            frontGo.setPosition(1.0);
            backGo.setPosition(1.0);
            sleep(2000);
        }

        waitForStart();

    }
}
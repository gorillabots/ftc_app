package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class ArmFInd extends LinearOpMode{

    Servo pivot;
    Servo screw;

    @Override
    public void runOpMode() throws InterruptedException {

        pivot = hardwareMap.servo.get("pivot");
        screw = hardwareMap.servo.get("screw");

        while (opModeIsActive()) {
            while(true){

                telemetry.addData("Servo is at", pivot.getPosition());
                sleep(1000);

                pivot.setPosition(0.0);
                sleep(2000);

                telemetry.addData("Servo is at", pivot.getPosition());
                sleep(1000);


                pivot.setPosition(0.1);
                sleep(2000);

                telemetry.addData("Servo is at", pivot.getPosition());
                sleep(1000);


                pivot.setPosition(0.2);
                sleep(2000);

                telemetry.addData("Servo is at", pivot.getPosition());
                sleep(1000);


                pivot.setPosition(0.3);
                sleep(2000);

                telemetry.addData("Servo is at", pivot.getPosition());
                sleep(1000);

                pivot.setPosition(0.4);
                sleep(2000);

                telemetry.addData("Servo is at", pivot.getPosition());
                sleep(1000);

                pivot.setPosition(0.5);
                sleep(2000);

                telemetry.addData("Servo is at", pivot.getPosition());
                sleep(1000);

                pivot.setPosition(0.6);
                sleep(2000);

                telemetry.addData("Servo is at", pivot.getPosition());
                sleep(1000);

                pivot.setPosition(0.7);
                sleep(2000);

                telemetry.addData("Servo is at", pivot.getPosition());
                sleep(1000);

                pivot.setPosition(0.8);
                sleep(2000);

                telemetry.addData("Servo is at", pivot.getPosition());
                sleep(1000);

                pivot.setPosition(0.9);
                sleep(2000);

                telemetry.addData("Servo is at", pivot.getPosition());
                sleep(1000);

                pivot.setPosition(1.0);
                sleep(2000);

                telemetry.addData("Servo is at", pivot.getPosition());
                sleep(1000);
            }

        }

    }
}



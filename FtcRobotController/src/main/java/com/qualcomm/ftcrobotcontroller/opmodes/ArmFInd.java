package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class ArmFInd extends LinearOpMode {

    Servo elbow;

    @Override
    public void runOpMode() throws InterruptedException {

        elbow = hardwareMap.servo.get("elbow");
        elbow.setPosition(0.0);

        while (opModeIsActive()) {
            while (true) {

                telemetry.addData("Servo is at", elbow.getPosition());
                sleep(1000);

                elbow.setPosition(0.0);
                sleep(2000);

                telemetry.addData("Servo is at", elbow.getPosition());
                sleep(1000);


                elbow.setPosition(0.5);
                sleep(2000);

                telemetry.addData("Servo is at", elbow.getPosition());
                sleep(1000);


                elbow.setPosition(1.0);
                sleep(2000);

                telemetry.addData("Servo is at", elbow.getPosition());
                sleep(1000);


            }

        }
    }
}



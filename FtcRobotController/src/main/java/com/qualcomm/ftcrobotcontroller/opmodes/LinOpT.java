package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by Jarred on 10/6/2015.
 */
public class LinOpT extends LinearOpMode {


    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    boolean wasPressed;

    TouchSensor finger;





    public void runOpMode() throws InterruptedException {

        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor4");
        finger = hardwareMap.touchSensor.get("touchsensor");
        boolean wasPressed = false;


        waitForStart();

        for(int i = 0; i<4; i++) {

            ;


            motor1.setPower(-.5);
            motor2.setPower(-.5);
            motor3.setPower(0.0);
            motor4.setPower(0.0);
            sleep(5000);
/*
            motor1.setPower(.5);
            motor2.setPower(.5);
            motor3.setPower(-.5);
            motor4.setPower(-.5);
            sleep(5000);
*/
            motor1.setPower((0.0));
            motor2.setPower((0.0));
            motor3.setPower((0.5));
            motor4.setPower((0.5));
            sleep(5000);

            motor1.setPower((0.0));
            motor2.setPower((0.0));
            motor3.setPower((0.0));
            motor4.setPower((0.0));





        }
        motor1.setPower((0.0));
        motor2.setPower((0.0));
        motor3.setPower((0.0));
        motor4.setPower((0.0));
    }

}



package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by Jarred on 10/6/2015.
 */
public class RemoteControl extends OpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;


    @Override
    public void init() {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor4 = hardwareMap.dcMotor.get("motor4");

    }
    @Override
    public void loop() {




        float throttleLeft = gamepad1.left_stick_y;
        float throttleRight = gamepad1.right_stick_y;

           motor4.setPower(throttleLeft);
           motor3.setPower(throttleLeft);
           motor1.setPower(-1*throttleRight);
           motor2.setPower(-1*throttleRight);
      // }



        }




    }


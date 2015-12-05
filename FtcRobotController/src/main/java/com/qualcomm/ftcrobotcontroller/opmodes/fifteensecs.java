package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by Michelle on 12/4/2015.
 */
public class fifteensecs extends LinearOpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;

    public void _init() {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");

    }

    @Override
    public void runOpMode() throws InterruptedException {

        _init();
        waitForStart();
        motor1.setPower(1.0);
        motor2.setPower(1.0);
        motor3.setPower(-1.0);
        motor4.setPower(-1.0);
        sleep(15000);

        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);

    }
}

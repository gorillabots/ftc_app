package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

public class HelloWorld extends OpMode {
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    final double MOTOR_SPEED = 0.45;

    TouchSensor finger;

    @Override
    public void init() {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor4");
        finger = hardwareMap.touchSensor.get("touchsensor");
    }

    @Override
    public void loop() {
        if(finger.isPressed())

        {
            motor1.setPower(MOTOR_SPEED);
            motor2.setPower(MOTOR_SPEED);
            motor4.setPower(MOTOR_SPEED);
            motor3.setPower(MOTOR_SPEED);
        }
        else {
            motor1.setPower(MOTOR_SPEED);
            motor2.setPower(MOTOR_SPEED);
            motor3.setPower(MOTOR_SPEED);
            motor4.setPower(MOTOR_SPEED);
        }
    }
    @Override
    public void stop() {

    }
}


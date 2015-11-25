package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by emper on 11/15/2015.
 */

public class Autonomous_Program extends OpMode{
    ColorSensor color;
    ColorSensor color2;
    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    DcMotor motor5;
    DcMotor motor6;
    Servo servo1;
    Servo servo2;
    public void init() {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        /*motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");
        motor5 = hardwareMap.dcMotor.get("motor5");
        motor6 = hardwareMap.dcMotor.get("motor6");*/
        color = hardwareMap.colorSensor.get("color");
        color2 = hardwareMap.colorSensor.get("color2");
        //servo1 = hardwareMap.servo.get("servo1");
        //servo2 = hardwareMap.servo.get("servo2");
    }
    public void loop() {
motor1.setPower(0.5);
        motor2.setPower(0.5);
    }
    public void stop(){

    }
}

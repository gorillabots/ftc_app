package com.qualcomm.ftcrobotcontroller.opmodes;
import android.hardware.Sensor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by Jarred on 12/26/2015.
 */
public class BrakeTest extends LinearOpMode{

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    UltrasonicSensor distance;
    UltrasonicSensor distance2;
    double brake;
    double brakeTwo;

    public void _init(){
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");

        distance = hardwareMap.ultrasonicSensor.get("distance");
        distance2 = hardwareMap.ultrasonicSensor.get("distance2");
        brake = 1;
        brakeTwo =1;
    }

    public void runOpMode() throws InterruptedException {

        _init();
        while(opModeIsActive()){

            if(distance.getUltrasonicLevel() <= brake && distance2.getUltrasonicLevel() <= brakeTwo){
                motor1.setPower(0);
                motor2.setPower(0);
                motor3.setPower(0);
                motor4.setPower(0);
            }
            else{
                motor1.setPower(1);
                motor2.setPower(1);
                motor3.setPower(-1);
                motor4.setPower(-1);
            }

        }

    }
}

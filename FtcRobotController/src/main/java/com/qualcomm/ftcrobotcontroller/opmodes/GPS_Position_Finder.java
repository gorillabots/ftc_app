package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
/**
 * Created by Jarred on 12/13/2015.
 */
public class GPS_Position_Finder extends OpMode {

    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;
    UltrasonicSensor distance1;
    UltrasonicSensor distance2;

    @Override
    public void init(){


        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");
        distance2 = hardwareMap.ultrasonicSensor.get("distance2");
        distance1 = hardwareMap.ultrasonicSensor.get("distance");


    }

    @Override
    public void loop(){

        telemetry.addData("PORT", distance1.getUltrasonicLevel());
        telemetry.addData("PORT1", distance2.getUltrasonicLevel());

        if(gamepad2.a == true) {
            motor1.setPower(0.0);
            motor2.setPower(0.0);
            motor3.setPower(0.0);
            motor4.setPower(0.0);
        }
        else{
            motor1.setPower(gamepad1.left_stick_y);
            motor2.setPower(gamepad1.left_stick_y);
            motor3.setPower(gamepad1.right_stick_y*-1);
            motor4.setPower(gamepad1.right_stick_y*-1);
        }

    }






}

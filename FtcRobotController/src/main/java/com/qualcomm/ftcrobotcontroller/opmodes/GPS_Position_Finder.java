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
    UltrasonicSensor frontLeft;
    UltrasonicSensor frontRight;
    UltrasonicSensor backLeft;
    UltrasonicSensor backRight;


    @Override
    public void init(){


        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");
        frontLeft = hardwareMap.ultrasonicSensor.get("frontLeft");
        frontRight = hardwareMap.ultrasonicSensor.get("frontRight");
        backLeft = hardwareMap.ultrasonicSensor.get("backLeft");
        backRight = hardwareMap.ultrasonicSensor.get("backRight");

    }

    @Override
    public void loop(){

        telemetry.addData("frontLeft", frontLeft.getUltrasonicLevel());
        telemetry.addData("frontRight", frontRight.getUltrasonicLevel());
        telemetry.addData("backLeft", backLeft.getUltrasonicLevel());
        telemetry.addData("backRight", backRight.getUltrasonicLevel());

        if(gamepad2.a == true) {
            motor1.setPower(0.0);
            motor2.setPower(0.0);
            motor3.setPower(0.0);
            motor4.setPower(0.0);
        }
        else{
            motor1.setPower(gamepad1.left_stick_y);
            motor2.setPower(gamepad1.left_stick_y);
            motor3.setPower(gamepad1.right_stick_y);
            motor4.setPower(gamepad1.right_stick_y);
        }

    }






}

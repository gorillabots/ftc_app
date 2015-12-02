package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorController.RunMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
/**
 * Created by emper on 11/15/2015.
 */

public class closeTest extends LinearOpMode{
    Servo backGo;
    Servo frontGo;
    @Override
    public void runOpMode() throws InterruptedException {
            backGo =hardwareMap.servo.get("backGo");
            frontGo = hardwareMap.servo.get("frontGo");
                waitForStart();
        while(opModeIsActive()) {

            backGo.setPosition(0.0);
            frontGo.setPosition(0.0);
        }


    }
}
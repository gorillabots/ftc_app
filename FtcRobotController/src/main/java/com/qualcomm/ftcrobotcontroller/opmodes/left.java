package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
/**
 * Created by Jarred on 3/8/2016.
 */
public class left extends LinearOpMode {
    Servo leftGo;


    void _init(){
        leftGo= hardwareMap.servo.get("backGo");
    }

    public void runOpMode() throws InterruptedException{
        _init();
        while(opModeIsActive()){
            if (gamepad1.left_trigger >= .5) {
                leftGo.setPosition((gamepad1.left_trigger * .7));
                telemetry.addData("P1.LT Pressed", "true");
            } else {
                telemetry.addData("P1.LT Pressed", "false");
                leftGo.setPosition(.0);
            }
        }
    }
}

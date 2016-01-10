package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by Jarred on 12/31/2015.
 */
public class ServoFinder_V2 extends OpMode{
    Servo extend;
    Servo rotate;
    Servo swing;


    @Override

    public void init(){
        extend = hardwareMap.servo.get("extend");
        swing = hardwareMap.servo.get("swing");
        rotate = hardwareMap.servo.get("rotate");
    }
    @Override

    public void loop(){

            telemetry.addData("extend", extend.getPosition());
            telemetry.addData("swing", extend.getPosition());
            telemetry.addData("rotate", rotate.getPosition());

            extend.setPosition(.5*(gamepad1.left_stick_y) + .5);
        rotate.setPosition(.5*(gamepad1.right_stick_y) + .5);
        swing.setPosition(.5*(gamepad2.left_stick_y)+.5);
    }
}
